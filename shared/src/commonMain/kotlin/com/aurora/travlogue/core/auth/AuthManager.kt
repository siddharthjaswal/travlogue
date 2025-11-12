package com.aurora.travlogue.core.auth

import com.aurora.travlogue.core.data.local.TokenStorage
import com.aurora.travlogue.core.data.remote.LogbookApiClient
import com.aurora.travlogue.core.data.remote.dto.AuthUserResponse
import com.aurora.travlogue.core.data.remote.dto.UserDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Centralized authentication manager
 * Handles OAuth flow, token management, and auth state
 */
class AuthManager(
    private val googleAuthProvider: GoogleAuthProvider,
    private val tokenStorage: TokenStorage,
    private val apiClient: LogbookApiClient
) {
    private val _authState = MutableStateFlow<AuthState>(AuthState.Unauthenticated)
    val authState: StateFlow<AuthState> = _authState.asStateFlow()

    private val _currentUser = MutableStateFlow<UserDto?>(null)
    val currentUser: StateFlow<UserDto?> = _currentUser.asStateFlow()

    /**
     * Initialize auth manager
     * Checks for existing tokens and validates them
     */
    suspend fun initialize() {
        if (tokenStorage.hasValidTokens()) {
            _authState.value = AuthState.Loading

            // Try to get user profile to validate tokens
            apiClient.getMe().fold(
                onSuccess = { user ->
                    _currentUser.value = user
                    _authState.value = AuthState.Authenticated(user)
                },
                onFailure = {
                    // Token might be expired, try refresh
                    refreshAuthToken()
                }
            )
        } else {
            _authState.value = AuthState.Unauthenticated
        }
    }

    /**
     * Sign in with Google
     */
    suspend fun signInWithGoogle(): Result<UserDto> {
        _authState.value = AuthState.Loading

        return googleAuthProvider.signIn().fold(
            onSuccess = { authResponse ->
                // Save tokens
                tokenStorage.saveAccessToken(authResponse.accessToken)
                tokenStorage.saveRefreshToken(authResponse.refreshToken)

                // Update state
                _currentUser.value = authResponse.user
                _authState.value = AuthState.Authenticated(authResponse.user)

                Result.success(authResponse.user)
            },
            onFailure = { error ->
                _authState.value = AuthState.Error(error.message ?: "Authentication failed")
                Result.failure(error)
            }
        )
    }

    /**
     * Sign out
     */
    suspend fun signOut(): Result<Unit> {
        return try {
            // Sign out from Google
            googleAuthProvider.signOut()

            // Call logout API
            apiClient.logout()

            // Clear local state
            _currentUser.value = null
            _authState.value = AuthState.Unauthenticated

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Refresh authentication token
     */
    suspend fun refreshAuthToken(): Result<String> {
        return apiClient.refreshToken().fold(
            onSuccess = { response ->
                // Token is already saved by the API client
                // Validate by fetching user profile
                apiClient.getMe().fold(
                    onSuccess = { user ->
                        _currentUser.value = user
                        _authState.value = AuthState.Authenticated(user)
                        Result.success(response.accessToken)
                    },
                    onFailure = { error ->
                        _authState.value = AuthState.Error("Failed to refresh token")
                        Result.failure(error)
                    }
                )
            },
            onFailure = { error ->
                // Refresh failed, sign out
                _authState.value = AuthState.Unauthenticated
                tokenStorage.clearTokens()
                Result.failure(error)
            }
        )
    }

    /**
     * Check if user is authenticated
     */
    fun isAuthenticated(): Boolean {
        return _authState.value is AuthState.Authenticated
    }
}

/**
 * Authentication state
 */
sealed class AuthState {
    object Unauthenticated : AuthState()
    object Loading : AuthState()
    data class Authenticated(val user: UserDto) : AuthState()
    data class Error(val message: String) : AuthState()
}
