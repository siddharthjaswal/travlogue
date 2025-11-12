package com.aurora.travlogue.core.auth

import com.aurora.travlogue.core.data.remote.dto.AuthUserResponse

/**
 * iOS implementation of GoogleAuthProvider
 * Uses GoogleSignIn SDK for iOS
 *
 * Note: This requires GoogleSignIn SDK to be configured in the iOS app.
 * Add to Podfile: pod 'GoogleSignIn'
 *
 * Also requires GoogleService-Info.plist with OAuth client ID configured.
 *
 * Implementation notes:
 * - Google Sign-In on iOS requires view controller context
 * - This should be implemented in the iOS app layer, not in shared module
 * - The shared module provides the interface, iOS app provides the implementation
 */
class IOSGoogleAuthProvider : GoogleAuthProvider {

    override suspend fun signIn(): Result<AuthUserResponse> {
        return Result.failure(
            NotImplementedError(
                "Google Sign-In for iOS requires UIViewController context and " +
                "GoogleSignIn SDK. Implement this in your iOS app layer using GIDSignIn.sharedInstance"
            )
        )
    }

    override suspend fun signOut(): Result<Unit> {
        // Call GIDSignIn.sharedInstance.signOut() in iOS app
        return Result.failure(
            NotImplementedError(
                "Google Sign-Out for iOS should be implemented in iOS app layer " +
                "using GIDSignIn.sharedInstance.signOut()"
            )
        )
    }

    override suspend fun isSignedIn(): Boolean {
        // Check GIDSignIn.sharedInstance.currentUser in iOS app
        return false
    }

    override suspend fun getIdToken(): String? {
        // Get GIDSignIn.sharedInstance.currentUser?.idToken?.tokenString in iOS app
        return null
    }
}

/**
 * Swift code example for iOS app:
 *
 * ```swift
 * import GoogleSignIn
 *
 * class GoogleAuthService {
 *     func signIn(viewController: UIViewController) async throws -> GIDGoogleUser {
 *         let config = GIDConfiguration(clientID: "YOUR_CLIENT_ID")
 *         GIDSignIn.sharedInstance.configuration = config
 *
 *         return try await GIDSignIn.sharedInstance.signIn(withPresenting: viewController)
 *     }
 *
 *     func getIdToken() -> String? {
 *         return GIDSignIn.sharedInstance.currentUser?.idToken?.tokenString
 *     }
 *
 *     func signOut() {
 *         GIDSignIn.sharedInstance.signOut()
 *     }
 * }
 * ```
 */
