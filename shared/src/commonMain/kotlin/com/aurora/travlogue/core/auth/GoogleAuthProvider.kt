package com.aurora.travlogue.core.auth

import com.aurora.travlogue.core.data.remote.dto.AuthUserResponse

/**
 * Google OAuth authentication provider interface
 *
 * Platform-specific implementations:
 * - Android: Uses Google Sign-In SDK / Credential Manager
 * - iOS: Uses GoogleSignIn SDK
 */
interface GoogleAuthProvider {
    /**
     * Initiate Google Sign-In flow
     * Returns auth result with tokens and user info
     */
    suspend fun signIn(): Result<AuthUserResponse>

    /**
     * Sign out from Google
     */
    suspend fun signOut(): Result<Unit>

    /**
     * Check if user is signed in
     */
    suspend fun isSignedIn(): Boolean

    /**
     * Get ID token for backend authentication
     * This token can be used to authenticate with the backend
     */
    suspend fun getIdToken(): String?
}

/**
 * Auth result wrapper
 */
sealed class AuthResult {
    data class Success(val response: AuthUserResponse) : AuthResult()
    data class Error(val message: String, val exception: Exception? = null) : AuthResult()
    object Cancelled : AuthResult()
}

/**
 * Mock implementation for testing/development
 */
class MockGoogleAuthProvider : GoogleAuthProvider {
    override suspend fun signIn(): Result<AuthUserResponse> {
        return Result.failure(NotImplementedError("Mock implementation - use platform-specific provider"))
    }

    override suspend fun signOut(): Result<Unit> {
        return Result.success(Unit)
    }

    override suspend fun isSignedIn(): Boolean = false

    override suspend fun getIdToken(): String? = null
}
