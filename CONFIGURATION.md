# Travlogue Configuration Guide

This document describes how to configure the Travlogue app for development and production.

## Google OAuth Configuration

### Android Setup

1. **Get your Google OAuth Client ID**:
   - Go to [Google Cloud Console](https://console.cloud.google.com/)
   - Select your project (or create a new one)
   - Navigate to **APIs & Services** > **Credentials**
   - Create an **OAuth 2.0 Client ID** for Android
   - Note your **Client ID** (format: `xxxxx.apps.googleusercontent.com`)

2. **Configure the app**:

   **Option 1: Via gradle.properties (Recommended)**

   Add to `gradle.properties` (local, not committed):
   ```properties
   GOOGLE_OAUTH_CLIENT_ID=xxxxx.apps.googleusercontent.com
   ```

   Then update `app/build.gradle.kts`:
   ```kotlin
   buildConfigField(
       "String",
       "GOOGLE_OAUTH_CLIENT_ID",
       "\"${project.findProperty("GOOGLE_OAUTH_CLIENT_ID") ?: "YOUR_GOOGLE_CLIENT_ID"}\""
   )
   ```

   **Option 2: Hardcode in build.gradle.kts (For testing only)**

   Update the buildConfigField in `app/build.gradle.kts`:
   ```kotlin
   buildConfigField("String", "GOOGLE_OAUTH_CLIENT_ID", "\"YOUR_ACTUAL_CLIENT_ID\"")
   ```

3. **Add google-services.json** (if using Firebase):
   - Download `google-services.json` from Firebase Console
   - Place in `app/` directory
   - Add to `.gitignore` (already configured)

### iOS Setup

1. **Get your iOS OAuth Client ID**:
   - Go to [Google Cloud Console](https://console.cloud.google.com/)
   - Create an **OAuth 2.0 Client ID** for iOS
   - Download `GoogleService-Info.plist`
   - Add to iOS project root

2. **Configure Info.plist**:
   - Add URL scheme with reversed client ID
   - See `docs/IOS_OAUTH_IMPLEMENTATION.md` for details

## Backend API Configuration

The app connects to the Logbook backend API:
- **Production**: https://api.travlogue.in
- **Staging**: (if available)

To change the API endpoint, update `shared/src/commonMain/kotlin/com/aurora/travlogue/core/data/remote/LogbookApiClient.kt`:

```kotlin
private const val BASE_URL = "https://api.travlogue.in"
```

For multiple environments, consider adding build variants.

## Background Sync Configuration

Background sync runs every 6 hours by default. To change:

Update `app/src/main/java/com/aurora/travlogue/App.kt`:

```kotlin
syncScheduler.schedulePeriodicSync(
    intervalHours = 6,        // Change interval here
    requireWifi = false,      // Set true for WiFi-only sync
    requireCharging = false   // Set true for charging-only sync
)
```

## Database Configuration

The app uses SQLDelight for local storage:
- **Database name**: `travlogue.db`
- **Location**: App's internal storage
- **Schema**: `shared/src/commonMain/sqldelight/`

No configuration needed - database is created automatically.

## Security Notes

### Never commit:
- `google-services.json`
- `GoogleService-Info.plist`
- `gradle.properties` with secrets
- API keys or OAuth tokens

### Use environment variables for CI/CD:
```bash
export GOOGLE_OAUTH_CLIENT_ID=xxxxx.apps.googleusercontent.com
```

Then in CI build script:
```bash
echo "GOOGLE_OAUTH_CLIENT_ID=$GOOGLE_OAUTH_CLIENT_ID" >> gradle.properties
```

## Troubleshooting

### "GoogleSignIn not configured" error
- Verify `GOOGLE_OAUTH_CLIENT_ID` is set correctly
- Check that `google-services.json` exists (if using Firebase)
- Ensure Client ID matches your package name in Google Cloud Console

### Backend authentication fails
- Verify backend is running: https://api.travlogue.in/docs
- Check that ID token is valid (not expired)
- Ensure backend has correct OAuth client ID configured

### Background sync not working
- Check network connectivity
- Verify battery optimization is not blocking WorkManager
- Check Logcat for WorkManager logs: `adb logcat | grep WorkManager`

## Development vs Production

### Development
```kotlin
buildConfigField("String", "API_ENVIRONMENT", "\"dev\"")
buildConfigField("String", "API_BASE_URL", "\"http://10.0.2.2:8000\"")
```

### Production
```kotlin
buildConfigField("String", "API_ENVIRONMENT", "\"prod\"")
buildConfigField("String", "API_BASE_URL", "\"https://api.travlogue.in\"")
```

## Additional Resources

- [Android OAuth Setup](https://developers.google.com/identity/sign-in/android/start)
- [iOS OAuth Setup](docs/IOS_OAUTH_IMPLEMENTATION.md)
- [API Documentation](https://api.travlogue.in/docs)
- [Logbook Backend Repository](https://github.com/your-org/logbook-backend)
