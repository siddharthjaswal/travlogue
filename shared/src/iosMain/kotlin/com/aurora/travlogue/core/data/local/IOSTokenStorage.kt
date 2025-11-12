package com.aurora.travlogue.core.data.local

import platform.Foundation.*
import platform.Security.*

/**
 * iOS implementation of TokenStorage using Keychain
 * Provides secure storage for authentication tokens
 */
class IOSTokenStorage : TokenStorage {

    companion object {
        private const val SERVICE_NAME = "com.aurora.travlogue"
        private const val KEY_ACCESS_TOKEN = "access_token"
        private const val KEY_REFRESH_TOKEN = "refresh_token"
    }

    override suspend fun saveAccessToken(token: String) {
        saveToKeychain(KEY_ACCESS_TOKEN, token)
    }

    override suspend fun getAccessToken(): String? {
        return getFromKeychain(KEY_ACCESS_TOKEN)
    }

    override suspend fun saveRefreshToken(token: String) {
        saveToKeychain(KEY_REFRESH_TOKEN, token)
    }

    override suspend fun getRefreshToken(): String? {
        return getFromKeychain(KEY_REFRESH_TOKEN)
    }

    override suspend fun clearTokens() {
        deleteFromKeychain(KEY_ACCESS_TOKEN)
        deleteFromKeychain(KEY_REFRESH_TOKEN)
    }

    override suspend fun hasValidTokens(): Boolean {
        val accessToken = getAccessToken()
        val refreshToken = getRefreshToken()
        return !accessToken.isNullOrEmpty() && !refreshToken.isNullOrEmpty()
    }

    private fun saveToKeychain(key: String, value: String) {
        // Delete existing item first
        deleteFromKeychain(key)

        // Create query
        val query = mapOf(
            kSecClass to kSecClassGenericPassword,
            kSecAttrService to SERVICE_NAME,
            kSecAttrAccount to key,
            kSecValueData to value.encodeToByteArray().toNSData()
        )

        val status = SecItemAdd(query as CFDictionaryRef, null)
        if (status != errSecSuccess) {
            println("Keychain save failed with status: $status")
        }
    }

    private fun getFromKeychain(key: String): String? {
        val query = mapOf(
            kSecClass to kSecClassGenericPassword,
            kSecAttrService to SERVICE_NAME,
            kSecAttrAccount to key,
            kSecReturnData to kCFBooleanTrue,
            kSecMatchLimit to kSecMatchLimitOne
        )

        val result = mutableListOf<Any?>()
        val status = SecItemCopyMatching(query as CFDictionaryRef, result as CFTypeRefVar)

        return if (status == errSecSuccess) {
            val data = result.firstOrNull() as? NSData
            data?.toByteArray()?.decodeToString()
        } else {
            null
        }
    }

    private fun deleteFromKeychain(key: String) {
        val query = mapOf(
            kSecClass to kSecClassGenericPassword,
            kSecAttrService to SERVICE_NAME,
            kSecAttrAccount to key
        )

        SecItemDelete(query as CFDictionaryRef)
    }

    // Helper extension to convert ByteArray to NSData
    private fun ByteArray.toNSData(): NSData {
        return NSData.create(bytes = this.refTo(0), length = this.size.toULong())
    }

    // Helper extension to convert NSData to ByteArray
    private fun NSData.toByteArray(): ByteArray {
        return ByteArray(this.length.toInt()).apply {
            usePinned {
                memcpy(it.addressOf(0), this@toByteArray.bytes, this@toByteArray.length)
            }
        }
    }
}
