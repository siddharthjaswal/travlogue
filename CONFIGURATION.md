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

Background sync is now configurable via user preferences using DataStore.

### Default Settings
- **Sync Interval**: 6 hours
- **Require WiFi**: false (sync on any network)
- **Require Charging**: false (sync even when not charging)

### Changing Sync Settings Programmatically

The app reads sync preferences from `AppPreferences`:

```kotlin
// In your settings screen or ViewModel
class SettingsViewModel(private val appPreferences: AppPreferences) {
    suspend fun updateSyncInterval(hours: Long) {
        appPreferences.setSyncInterval(hours)
        // Reschedule sync with new interval
        syncScheduler.schedulePeriodicSync(
            intervalHours = hours,
            requireWifi = appPreferences.getRequireWifiForSync(),
            requireCharging = appPreferences.getRequireChargingForSync()
        )
    }
}
```

### Available Sync Intervals
- 1 hour - "Every hour"
- 3 hours - "Every 3 hours"
- 6 hours - "Every 6 hours (recommended)" ⭐
- 12 hours - "Every 12 hours"
- 24 hours - "Once daily"

### DataStore Storage
Settings are stored in `app_preferences` DataStore:
- Location: `/data/data/com.aurora.travlogue/datastore/`
- Format: Preferences DataStore (key-value pairs)
- Persists across app restarts

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

The app automatically configures the API endpoint based on build variant:

### Debug Build (Development)
- **API URL**: `http://10.0.2.2:8000` (Android emulator localhost)
- **Environment**: `development`
- **Usage**: `./gradlew assembleDebug` or Run from Android Studio

```kotlin
// Automatically set in debug builds
BuildConfig.API_BASE_URL = "http://10.0.2.2:8000"
BuildConfig.API_ENVIRONMENT = "development"
```

### Release Build (Production)
- **API URL**: `https://api.travlogue.in`
- **Environment**: `production`
- **Usage**: `./gradlew assembleRelease`

```kotlin
// Automatically set in release builds
BuildConfig.API_BASE_URL = "https://api.travlogue.in"
BuildConfig.API_ENVIRONMENT = "production"
```

### Custom API Endpoint

To override the API endpoint, update `app/build.gradle.kts`:

```kotlin
buildTypes {
    debug {
        buildConfigField("String", "API_BASE_URL", "\"https://staging.travlogue.in\"")
        buildConfigField("String", "API_ENVIRONMENT", "\"staging\"")
    }
}
```

**Note**: The KMP shared module currently uses a hardcoded API URL. To support dynamic endpoints, you'll need to pass `BuildConfig.API_BASE_URL` when creating `LogbookApiClient` in the platform module.

## Additional Resources

- [Android OAuth Setup](https://developers.google.com/identity/sign-in/android/start)
- [iOS OAuth Setup](docs/IOS_OAUTH_IMPLEMENTATION.md)
- [API Documentation](https://api.travlogue.in/docs)
- [Logbook Backend Repository](https://github.com/your-org/logbook-backend)
