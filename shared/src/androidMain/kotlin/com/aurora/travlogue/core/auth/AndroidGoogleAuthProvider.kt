package com.aurora.travlogue.core.auth

import android.app.Activity
import android.content.Context
import android.util.Log
import com.aurora.travlogue.core.data.remote.dto.AuthUserResponse
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

/**
 * Android implementation of GoogleAuthProvider
 * Uses Google Sign-In SDK
 *
 * Note: This requires Google Sign-In SDK to be configured in the Android app.
 * Add to build.gradle: implementation("com.google.android.gms:play-services-auth:20.7.0")
 *
 * Also requires google-services.json with OAuth client ID configured.
 */
class AndroidGoogleAuthProvider(
    private val context: Context,
    private val serverClientId: String,
    private val apiClient: com.aurora.travlogue.core.data.remote.LogbookApiClient
) : GoogleAuthProvider {

    init {
        Log.d(TAG, "AndroidGoogleAuthProvider initialized with serverClientId: $serverClientId")
    }

    private val googleSignInClient: GoogleSignInClient by lazy {
        Log.d(TAG, "Building GoogleSignInOptions with serverClientId: $serverClientId")
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(serverClientId)
            .requestEmail()
            .requestProfile()
            .build()

        GoogleSignIn.getClient(context, gso)
    }

    companion object {
        private const val TAG = "GoogleAuth"
    }

    /**
     * Sign in with Google
     *
     * This should be called AFTER getting the Google ID token from handleSignInResult()
     * It sends the ID token to the backend for validation and JWT token exchange
     */
    override suspend fun signIn(): Result<AuthUserResponse> {
        Log.d(TAG, "🔐 signIn() called - Preparing to authenticate with backend")

        // Get the last signed-in account (should have ID token from recent sign-in)
        val account = GoogleSignIn.getLastSignedInAccount(context)
        val idToken = account?.idToken

        Log.d(TAG, "📧 Account: ${account?.email}")
        Log.d(TAG, "🎫 ID Token present: ${idToken != null}")
        Log.d(TAG, "🎫 ID Token (first 50 chars): ${idToken?.take(50)}")

        if (idToken == null) {
            Log.e(TAG, "❌ No ID token available!")
            return Result.failure(
                IllegalStateException(
                    "No ID token available. Call getSignInIntent() and handleSignInResult() first"
                )
            )
        }

        Log.d(TAG, "🌐 Calling backend API: authenticateWithGoogle()")
        // Send ID token to backend for validation and JWT exchange
        val result = apiClient.authenticateWithGoogle(idToken)

        result.fold(
            onSuccess = { response ->
                Log.d(TAG, "✅ Backend authentication successful!")
                Log.d(TAG, "👤 User: ${response.user.email}")
                Log.d(TAG, "🎟️  Access token received: ${response.accessToken.take(20)}...")
            },
            onFailure = { error ->
                Log.e(TAG, "❌ Backend authentication failed: ${error.message}")
                Log.e(TAG, "Full error: ${error.stackTraceToString()}")
            }
        )

        return result
    }

    /**
     * Silent sign-in - attempts to sign in without user interaction
     * Use this to check if user is already signed in
     */
    suspend fun silentSignIn(): Result<GoogleSignInAccount> = suspendCancellableCoroutine { continuation ->
        googleSignInClient.silentSignIn()
            .addOnSuccessListener { account ->
                continuation.resume(Result.success(account))
            }
            .addOnFailureListener { exception ->
                continuation.resume(Result.failure(exception))
            }
    }

    override suspend fun signOut(): Result<Unit> = suspendCancellableCoroutine { continuation ->
        googleSignInClient.signOut()
            .addOnSuccessListener {
                continuation.resume(Result.success(Unit))
            }
            .addOnFailureListener { exception ->
                continuation.resume(Result.failure(exception))
            }
    }

    override suspend fun isSignedIn(): Boolean {
        val account = GoogleSignIn.getLastSignedInAccount(context)
        return account != null
    }

    override suspend fun getIdToken(): String? {
        val account = GoogleSignIn.getLastSignedInAccount(context)
        return account?.idToken
    }

    /**
     * Get the sign-in intent to launch from an Activity
     * Call this from your Activity/Composable and start the intent with ActivityResultLauncher
     *
     * Usage in Compose:
     * ```
     * val launcher = rememberLauncherForActivityResult(StartActivityForResult()) { result ->
     *     // Handle result with handleSignInResult()
     * }
     * launcher.launch(googleAuthProvider.getSignInIntent())
     * ```
     */
    fun getSignInIntent(): android.content.Intent {
        Log.d(TAG, "Creating Google Sign-In intent with Client ID: $serverClientId")
        Log.d(TAG, "Package: ${context.packageName}")
        return googleSignInClient.signInIntent
    }

    /**
     * Handle sign-in result from Activity
     * Call this from your ActivityResultLauncher callback
     *
     * This extracts the GoogleSignInAccount from the intent result.
     * After this, call signIn() on AuthManager to send the ID token to the backend.
     *
     * @param data The intent data from ActivityResult
     * @return Result containing GoogleSignInAccount with ID token, or error
     */
    suspend fun handleSignInResult(data: android.content.Intent?): Result<GoogleSignInAccount> {
        return try {
            Log.d(TAG, "Handling sign-in result")
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val account = task.getResult(ApiException::class.java)
            Log.d(TAG, "Sign-in successful! Account: ${account.email}, ID Token available: ${account.idToken != null}")
            Result.success(account)
        } catch (e: ApiException) {
            Log.e(TAG, "Google Sign-In failed - Status Code: ${e.statusCode}, Message: ${e.message}")
            Log.e(TAG, "Full error details: ${e.stackTraceToString()}")
            Result.failure(Exception("Google Sign-In failed: ${e.statusCode} - ${e.message}"))
        }
    }
}

/**
 * Extension function to await Google Sign-In Task
 */
private suspend fun <T> Task<T>.await(): T = suspendCancellableCoroutine { continuation ->
    addOnSuccessListener { result ->
        continuation.resume(result)
    }
    addOnFailureListener { exception ->
        throw exception
    }
}
