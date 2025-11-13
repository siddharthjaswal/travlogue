package com.aurora.travlogue.feature.signin.presentation

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.aurora.travlogue.R
import com.aurora.travlogue.core.auth.AndroidGoogleAuthProvider
import com.aurora.travlogue.core.auth.AuthManager
import com.aurora.travlogue.core.auth.AuthState
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

/**
 * Sign in screen with Google Sign-In
 *
 * This screen provides a UI for users to sign in with Google OAuth.
 * Uses the AndroidGoogleAuthProvider to handle the sign-in flow.
 *
 * @param onSignInSuccess Callback invoked when sign-in is successful
 * @param onNavigateBack Callback to navigate back (if user cancels)
 */
@Composable
fun SignInScreen(
    onSignInSuccess: () -> Unit,
    onNavigateBack: (() -> Unit)? = null,
    authManager: AuthManager = koinInject(),
    googleAuthProvider: AndroidGoogleAuthProvider = koinInject()
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    // Auth state
    val authState by authManager.authState.collectAsState()

    // UI state
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    // Google Sign-In launcher
    val signInLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            scope.launch {
                isLoading = true
                errorMessage = null

                // Handle sign-in result from intent
                googleAuthProvider.handleSignInResult(result.data).fold(
                    onSuccess = { googleAccount ->
                        val idToken = googleAccount.idToken

                        if (idToken != null) {
                            // Sign in with AuthManager (sends token to backend)
                            authManager.signInWithGoogle().fold(
                                onSuccess = {
                                    isLoading = false
                                    onSignInSuccess()
                                },
                                onFailure = { error ->
                                    isLoading = false
                                    errorMessage = error.message ?: "Authentication failed"
                                }
                            )
                        } else {
                            isLoading = false
                            errorMessage = "Failed to get ID token from Google"
                        }
                    },
                    onFailure = { error ->
                        isLoading = false
                        errorMessage = error.message ?: "Sign-in failed"
                    }
                )
            }
        } else {
            // User cancelled
            isLoading = false
            errorMessage = "Sign-in cancelled"
        }
    }

    // Handle auth state changes
    LaunchedEffect(authState) {
        when (authState) {
            is AuthState.Authenticated -> {
                onSignInSuccess()
            }
            is AuthState.Error -> {
                isLoading = false
                errorMessage = (authState as AuthState.Error).message
            }
            is AuthState.Loading -> {
                isLoading = true
                errorMessage = null
            }
            else -> {
                // Unauthenticated - do nothing
            }
        }
    }

    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                // App branding
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // App icon/logo (placeholder)
                    Icon(
                        painter = painterResource(id = android.R.drawable.ic_dialog_info),
                        contentDescription = "App logo",
                        modifier = Modifier.size(80.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )

                    Text(
                        text = "Travlogue",
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "Plan your perfect journey",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Sign in button
                Button(
                    onClick = {
                        scope.launch {
                            isLoading = true
                            errorMessage = null

                            // Launch Google Sign-In intent
                            val signInIntent = googleAuthProvider.getSignInIntent()
                            signInLauncher.launch(signInIntent)
                        }
                    },
                    enabled = !isLoading,
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (isLoading) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(20.dp),
                                strokeWidth = 2.dp
                            )
                        } else {
                            Icon(
                                painter = painterResource(id = android.R.drawable.ic_menu_add),
                                contentDescription = "Google logo"
                            )
                        }
                        Text("Sign in with Google")
                    }
                }

                // Error message
                if (errorMessage != null) {
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.errorContainer
                        )
                    ) {
                        Text(
                            text = errorMessage!!,
                            modifier = Modifier.padding(16.dp),
                            color = MaterialTheme.colorScheme.onErrorContainer,
                            textAlign = TextAlign.Center
                        )
                    }
                }

                // Optional: Skip sign-in for testing
                if (onNavigateBack != null) {
                    TextButton(
                        onClick = onNavigateBack,
                        enabled = !isLoading
                    ) {
                        Text("Continue without signing in")
                    }
                }
            }
        }
    }
}
