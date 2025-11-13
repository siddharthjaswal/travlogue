package com.aurora.travlogue.core.sync

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.aurora.travlogue.core.domain.service.SyncService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import timber.log.Timber

/**
 * WorkManager worker for background synchronization
 * Syncs all local changes to the backend when network is available
 */
class SyncWorker(
    appContext: Context,
    params: WorkerParameters
) : CoroutineWorker(appContext, params), KoinComponent {

    private val syncService: SyncService by inject()
    private val networkMonitor = NetworkConnectivityMonitor(appContext)

    override suspend fun doWork(): Result {
        Timber.d("SyncWorker started - Attempt ${runAttemptCount + 1}")

        // Check network connectivity
        if (!networkMonitor.isCurrentlyConnected()) {
            Timber.w("SyncWorker: No network connection, rescheduling")
            return Result.retry()
        }

        return try {
            // Perform sync
            Timber.d("SyncWorker: Starting sync...")
            val result = syncService.syncAll()

            result.fold(
                onSuccess = {
                    Timber.i("SyncWorker: Sync completed successfully")
                    Result.success(
                        workDataOf(
                            KEY_SYNC_STATUS to "success",
                            KEY_TIMESTAMP to System.currentTimeMillis()
                        )
                    )
                },
                onFailure = { error ->
                    Timber.e(error, "SyncWorker: Sync failed")

                    // Retry on network errors, fail on other errors
                    if (isNetworkError(error) && runAttemptCount < MAX_RETRIES) {
                        Timber.w("SyncWorker: Network error, retrying (attempt ${runAttemptCount + 1}/$MAX_RETRIES)")
                        Result.retry()
                    } else {
                        Timber.e("SyncWorker: Giving up after ${runAttemptCount + 1} attempts")
                        Result.failure(
                            workDataOf(
                                KEY_SYNC_STATUS to "failed",
                                KEY_ERROR_MESSAGE to (error.message ?: "Unknown error"),
                                KEY_TIMESTAMP to System.currentTimeMillis()
                            )
                        )
                    }
                }
            )
        } catch (e: Exception) {
            Timber.e(e, "SyncWorker: Unexpected error during sync")
            Result.failure(
                workDataOf(
                    KEY_SYNC_STATUS to "failed",
                    KEY_ERROR_MESSAGE to (e.message ?: "Unexpected error"),
                    KEY_TIMESTAMP to System.currentTimeMillis()
                )
            )
        }
    }

    /**
     * Determine if error is network-related and should trigger retry
     */
    private fun isNetworkError(error: Throwable): Boolean {
        val message = error.message?.lowercase() ?: ""
        return message.contains("network") ||
                message.contains("timeout") ||
                message.contains("connection") ||
                message.contains("unreachable") ||
                error is java.net.UnknownHostException ||
                error is java.net.SocketTimeoutException ||
                error is java.net.ConnectException
    }

    companion object {
        const val WORK_NAME = "sync_worker"
        const val KEY_SYNC_STATUS = "sync_status"
        const val KEY_ERROR_MESSAGE = "error_message"
        const val KEY_TIMESTAMP = "timestamp"
        private const val MAX_RETRIES = 3
    }
}
