# iOS Google Sign-In Implementation Guide

**Last Updated**: 2025-11-13
**Status**: Implementation Pending
**Target**: Travlogue iOS App (KMP)

---

## Overview

This document provides a complete guide for implementing Google Sign-In on iOS to match the Android implementation completed in Phase 4B.

The iOS implementation will:
1. Use the **GoogleSignIn SDK** for iOS
2. Send the ID token to the Logbook backend (`/auth/google`)
3. Receive and store JWT tokens securely in Keychain
4. Integrate with the shared KMP `AuthManager`

---

## Prerequisites

### 1. Add GoogleSignIn SDK

Add to your iOS project's `Podfile`:

```ruby
pod 'GoogleSignIn'
```

Then run:
```bash
pod install
```

### 2. Configure OAuth Client

1. Go to [Google Cloud Console](https://console.cloud.google.com/)
2. Create iOS OAuth 2.0 Client ID
3. Download `GoogleService-Info.plist`
4. Add to iOS project root

### 3. Configure URL Scheme

In `Info.plist`, add:

```xml
<key>CFBundleURLTypes</key>
<array>
    <dict>
        <key>CFBundleTypeRole</key>
        <string>Editor</string>
        <key>CFBundleURLSchemes</key>
        <array>
            <string>YOUR_REVERSED_CLIENT_ID</string>
        </array>
    </dict>
</array>
```

Replace `YOUR_REVERSED_CLIENT_ID` with the reversed client ID from `GoogleService-Info.plist`.

---

## Implementation

### Step 1: Update IOSGoogleAuthProvider

Currently, `IOSGoogleAuthProvider` is a stub. We need to implement it using the GoogleSignIn SDK.

**File**: `shared/src/iosMain/kotlin/com/aurora/travlogue/core/auth/IOSGoogleAuthProvider.kt`

```kotlin
package com.aurora.travlogue.core.auth

import com.aurora.travlogue.core.data.remote.LogbookApiClient
import com.aurora.travlogue.core.data.remote.dto.AuthUserResponse
import cocoapods.GoogleSignIn.GIDSignIn
import platform.UIKit.UIApplication

/**
 * iOS implementation of GoogleAuthProvider
 * Uses GoogleSignIn SDK for iOS
 */
class IOSGoogleAuthProvider(
    private val apiClient: LogbookApiClient
) : GoogleAuthProvider {

    override suspend fun signIn(): Result<AuthUserResponse> {
        // Get the current signed-in user
        val currentUser = GIDSignIn.sharedInstance.currentUser
        val idToken = currentUser?.idToken?.tokenString

        if (idToken == null) {
            return Result.failure(
                IllegalStateException(
                    "No ID token available. Call signInWithViewController() first"
                )
            )
        }

        // Send ID token to backend for validation and JWT exchange
        return apiClient.authenticateWithGoogle(idToken)
    }

    override suspend fun signOut(): Result<Unit> {
        return try {
            GIDSignIn.sharedInstance.signOut()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun isSignedIn(): Boolean {
        return GIDSignIn.sharedInstance.currentUser != null
    }

    override suspend fun getIdToken(): String? {
        return GIDSignIn.sharedInstance.currentUser?.idToken?.tokenString
    }

    /**
     * Sign in with presenting view controller
     * Call this from SwiftUI/UIKit with the presenting view controller
     */
    suspend fun signInWithViewController(viewController: UIViewController): Result<Unit> {
        return suspendCoroutine { continuation ->
            val clientID = GIDSignIn.sharedInstance.configuration?.clientID
            if (clientID == null) {
                continuation.resume(Result.failure(Exception("GoogleSignIn not configured")))
                return@suspendCoroutine
            }

            GIDSignIn.sharedInstance.signInWithPresentingViewController(viewController) { result, error ->
                if (error != null) {
                    continuation.resume(Result.failure(Exception(error.localizedDescription)))
                } else {
                    continuation.resume(Result.success(Unit))
                }
            }
        }
    }

    /**
     * Restore previous sign-in (silent sign-in)
     */
    suspend fun restorePreviousSignIn(): Result<Unit> {
        return suspendCoroutine { continuation ->
            GIDSignIn.sharedInstance.restorePreviousSignInWithCompletion { user, error ->
                if (error != null) {
                    continuation.resume(Result.failure(Exception(error.localizedDescription)))
                } else if (user != null) {
                    continuation.resume(Result.success(Unit))
                } else {
                    continuation.resume(Result.failure(Exception("No previous sign-in")))
                }
            }
        }
    }
}
```

### Step 2: Update iOS Platform Module

**File**: `shared/src/iosMain/kotlin/com/aurora/travlogue/di/PlatformModule.ios.kt`

```kotlin
package com.aurora.travlogue.di

import com.aurora.travlogue.core.auth.GoogleAuthProvider
import com.aurora.travlogue.core.auth.IOSGoogleAuthProvider
import com.aurora.travlogue.core.data.local.DatabaseDriverFactory
import com.aurora.travlogue.core.data.local.IOSTokenStorage
import com.aurora.travlogue.core.data.local.TokenStorage
import org.koin.dsl.module

actual val platformModule = module {
    single { DatabaseDriverFactory() }

    // Token Storage
    single<TokenStorage> { IOSTokenStorage() }

    // Google Auth Provider
    single<GoogleAuthProvider> {
        IOSGoogleAuthProvider(
            apiClient = get() // LogbookApiClient for backend auth
        )
    }
}
```

### Step 3: Create iOS Sign-In Screen (SwiftUI)

**File**: `iosApp/iosApp/Features/SignIn/SignInView.swift`

```swift
import SwiftUI
import GoogleSignIn
import shared

struct SignInView: View {
    @StateObject private var viewModel = SignInViewModel()
    @Environment(\.dismiss) private var dismiss

    var body: some View {
        VStack(spacing: 32) {
            Spacer()

            // App Branding
            VStack(spacing: 16) {
                Image(systemName: "airplane.departure")
                    .font(.system(size: 80))
                    .foregroundColor(.blue)

                Text("Travlogue")
                    .font(.largeTitle)
                    .fontWeight(.bold)

                Text("Plan your perfect journey")
                    .font(.body)
                    .foregroundColor(.secondary)
            }

            Spacer()

            // Sign In Button
            Button(action: {
                viewModel.signIn()
            }) {
                HStack {
                    if viewModel.isLoading {
                        ProgressView()
                            .progressViewStyle(CircularProgressViewStyle())
                    } else {
                        Image(systemName: "person.circle.fill")
                    }
                    Text("Sign in with Google")
                }
                .frame(maxWidth: .infinity)
                .padding()
                .background(Color.blue)
                .foregroundColor(.white)
                .cornerRadius(12)
            }
            .disabled(viewModel.isLoading)

            // Error Message
            if let error = viewModel.errorMessage {
                Text(error)
                    .font(.caption)
                    .foregroundColor(.red)
                    .multilineTextAlignment(.center)
                    .padding()
            }

            // Skip Sign-In (for testing)
            Button("Continue without signing in") {
                dismiss()
            }
            .font(.caption)
            .foregroundColor(.secondary)

            Spacer()
        }
        .padding()
        .onChange(of: viewModel.isAuthenticated) { isAuth in
            if isAuth {
                dismiss()
            }
        }
    }
}

@MainActor
class SignInViewModel: ObservableObject {
    @Published var isLoading = false
    @Published var errorMessage: String?
    @Published var isAuthenticated = false

    private let authManager: AuthManager
    private let googleAuthProvider: IOSGoogleAuthProvider

    init() {
        // Get from Koin DI
        self.authManager = KoinHelper.shared.get(objCClass: AuthManager.self)
        self.googleAuthProvider = KoinHelper.shared.get(objCClass: IOSGoogleAuthProvider.self)
    }

    func signIn() {
        guard let viewController = UIApplication.shared.currentUIWindow()?.rootViewController else {
            errorMessage = "Unable to get view controller"
            return
        }

        isLoading = true
        errorMessage = nil

        Task {
            do {
                // Step 1: Sign in with Google
                try await googleAuthProvider.signInWithViewController(viewController: viewController)

                // Step 2: Send ID token to backend
                try await authManager.signInWithGoogle()

                // Success
                isAuthenticated = true
            } catch {
                errorMessage = error.localizedDescription
            }

            isLoading = false
        }
    }
}

// Helper extension to get current window
extension UIApplication {
    func currentUIWindow() -> UIWindow? {
        let connectedScenes = UIApplication.shared.connectedScenes
            .filter { $0.activationState == .foregroundActive }
            .compactMap { $0 as? UIWindowScene }

        let window = connectedScenes.first?
            .windows
            .first { $0.isKeyWindow }

        return window
    }
}
```

### Step 4: Configure GIDSignIn on App Launch

**File**: `iosApp/iosApp/iOSApp.swift`

```swift
import SwiftUI
import GoogleSignIn
import shared

@main
struct iOSApp: App {
    @StateObject private var appState = AppState()

    init() {
        // Initialize Koin
        KoinInitializer().initialize()

        // Configure GoogleSignIn
        if let clientID = FirebaseApp.app()?.options.clientID {
            GIDSignIn.sharedInstance.configuration = GIDConfiguration(clientID: clientID)
        }
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
                .environmentObject(appState)
                .onOpenURL { url in
                    GIDSignIn.sharedInstance.handle(url)
                }
        }
    }
}

class AppState: ObservableObject {
    @Published var isAuthenticated = false

    private let authManager: AuthManager

    init() {
        self.authManager = KoinHelper.shared.get(objCClass: AuthManager.self)

        // Initialize auth on app start
        Task {
            await authManager.initialize()

            // Observe auth state
            authManager.authState.collect { [weak self] state in
                self?.isAuthenticated = (state is AuthStateAuthenticated)
            }
        }
    }
}
```

### Step 5: Update ContentView for Auth Navigation

**File**: `iosApp/iosApp/ContentView.swift`

```swift
import SwiftUI

struct ContentView: View {
    @EnvironmentObject var appState: AppState

    var body: some View {
        if appState.isAuthenticated {
            HomeView()
        } else {
            SignInView()
        }
    }
}
```

---

## Testing

### 1. Test Sign-In Flow

1. Launch iOS app
2. Should see SignInView (unauthenticated)
3. Tap "Sign in with Google"
4. Google account picker appears
5. Select account
6. App receives ID token
7. Backend validates and returns JWT
8. Tokens stored in Keychain
9. Navigate to HomeView

### 2. Test Sign-Out

1. Add sign-out button in HomeView
2. Tap sign-out
3. Should navigate back to SignInView
4. Tokens cleared from Keychain

### 3. Test Silent Sign-In

1. Sign in successfully
2. Kill app (swipe up in app switcher)
3. Relaunch app
4. Should automatically restore previous sign-in
5. Navigate directly to HomeView

---

## Troubleshooting

### Issue: "GoogleSignIn not configured"

**Solution**: Ensure `GoogleService-Info.plist` is in the project and `GIDConfiguration` is set on app launch.

### Issue: "No ID token available"

**Solution**: Make sure `requestIdToken` is included in the sign-in configuration.

### Issue: Backend authentication fails

**Solution**:
- Check that backend `/auth/google` endpoint is working
- Verify ID token is valid (not expired)
- Check backend logs for validation errors

---

## Matching Android Implementation

The iOS implementation mirrors the Android flow:

| Step | Android | iOS |
|------|---------|-----|
| **1. Launch Sign-In** | `ActivityResultLauncher` + `getSignInIntent()` | `signInWithViewController()` |
| **2. Get Account** | `handleSignInResult()` | `GIDSignIn.sharedInstance.currentUser` |
| **3. Extract ID Token** | `account.idToken` | `currentUser.idToken.tokenString` |
| **4. Backend Auth** | `apiClient.authenticateWithGoogle()` | `apiClient.authenticateWithGoogle()` |
| **5. Store Tokens** | `EncryptedSharedPreferences` | `Keychain` (IOSTokenStorage) |
| **6. Navigate** | `navController.navigate(Home)` | `dismiss()` → `ContentView` updates |

---

## Next Steps

1. Implement the code above
2. Test sign-in flow end-to-end
3. Test offline/online behavior
4. Verify token refresh works
5. Test sign-out and re-authentication

---

## References

- [GoogleSignIn iOS SDK](https://developers.google.com/identity/sign-in/ios)
- [Kotlin Multiplatform iOS Integration](https://kotlinlang.org/docs/multiplatform-mobile-understand-project-structure.html)
- [Travlogue Backend API](https://api.travlogue.in/docs)

---

*This guide matches the Android implementation completed in Phase 4B (Commit: `0b68f89`)*
