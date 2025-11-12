package com.aurora.travlogue.core.data.local

/**
 * Token storage interface for managing authentication tokens
 *
 * Platform-specific implementations will handle secure storage:
 * - Android: EncryptedSharedPreferences
 * - iOS: Keychain
 */
interface TokenStorage {
    suspend fun saveAccessToken(token: String)
    suspend fun getAccessToken(): String?
    suspend fun saveRefreshToken(token: String)
    suspend fun getRefreshToken(): String?
    suspend fun clearTokens()
    suspend fun hasValidTokens(): Boolean
}

/**
 * In-memory token storage for development/testing
 * Note: Not secure, use platform-specific implementations in production
 */
class InMemoryTokenStorage : TokenStorage {
    private var accessToken: String? = null
    private var refreshToken: String? = null

    override suspend fun saveAccessToken(token: String) {
        accessToken = token
    }

    override suspend fun getAccessToken(): String? = accessToken

    override suspend fun saveRefreshToken(token: String) {
        refreshToken = token
    }

    override suspend fun getRefreshToken(): String? = refreshToken

    override suspend fun clearTokens() {
        accessToken = null
        refreshToken = null
    }

    override suspend fun hasValidTokens(): Boolean {
        return accessToken != null && refreshToken != null
    }
}
