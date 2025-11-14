package com.aurora.travlogue.core.config

/**
 * Configuration object for Android-specific build configuration
 *
 * TODO: Replace with BuildKonfig or similar for proper multi-platform config management
 */
object Config {
    /**
     * Google OAuth Client ID for Android
     * This should match the OAuth client ID configured in Google Cloud Console
     *
     * NOTE: This is a placeholder. In production:
     * 1. Add the actual client ID from your google-services.json
     * 2. Or use BuildKonfig plugin to inject from local.properties
     * 3. Never commit the actual client ID to version control
     */
    const val GOOGLE_OAUTH_CLIENT_ID = "YOUR_GOOGLE_OAUTH_CLIENT_ID"
}
