package com.aurora.travlogue.core.sync

import android.content.Context
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import java.util.concurrent.TimeUnit

/**
 * Manages scheduling of background sync operations
 * Configures WorkManager with battery and network constraints
 */
class SyncScheduler(private val context: Context) {

    private val workManager = WorkManager.getInstance(context)

    /**
     * Schedule periodic background sync
     *
     * @param intervalHours Sync interval in hours (minimum 15 minutes for PeriodicWork)
     * @param requireWifi If true, only sync when connected to WiFi (saves cellular data)
     * @param requireCharging If true, only sync when device is charging (saves battery)
     */
    fun schedulePeriodicSync(
        intervalHours: Long = DEFAULT_SYNC_INTERVAL_HOURS,
        requireWifi: Boolean = false,
        requireCharging: Boolean = false
    ) {
        Timber.d(
            "Scheduling periodic sync: interval=${intervalHours}h, " +
                    "requireWifi=$requireWifi, requireCharging=$requireCharging"
        )

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(
                if (requireWifi) NetworkType.UNMETERED else NetworkType.CONNECTED
            )
            .setRequiresBatteryNotLow(true) // Don't sync when battery is low
            .apply {
                if (requireCharging) {
                    setRequiresCharging(true)
                }
            }
            .build()

        val syncRequest = PeriodicWorkRequestBuilder<SyncWorker>(
            intervalHours,
            TimeUnit.HOURS,
            flexTimeInterval = 15, // Allow 15 minute flex for optimization
            flexTimeUnit = TimeUnit.MINUTES
        )
            .setConstraints(constraints)
            .addTag(TAG_SYNC)
            .build()

        // Use KEEP policy to preserve existing work
        workManager.enqueueUniquePeriodicWork(
            SyncWorker.WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            syncRequest
        )

        Timber.i("Periodic sync scheduled successfully")
    }

    /**
     * Cancel all scheduled sync work
     */
    fun cancelPeriodicSync() {
        Timber.d("Cancelling periodic sync")
        workManager.cancelUniqueWork(SyncWorker.WORK_NAME)
    }

    /**
     * Cancel all sync work (including running work)
     */
    fun cancelAllSync() {
        Timber.d("Cancelling all sync work")
        workManager.cancelAllWorkByTag(TAG_SYNC)
    }

    /**
     * Trigger an immediate sync (one-time)
     * Ignores periodic sync constraints for user-initiated sync
     */
    fun triggerImmediateSync() {
        Timber.d("Triggering immediate one-time sync")

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val syncRequest = androidx.work.OneTimeWorkRequestBuilder<SyncWorker>()
            .setConstraints(constraints)
            .addTag(TAG_SYNC)
            .addTag(TAG_IMMEDIATE_SYNC)
            .build()

        workManager.enqueue(syncRequest)
    }

    /**
     * Get the current sync work status as a Flow
     * Emits updates whenever sync status changes
     */
    fun getSyncWorkStatus(): Flow<WorkInfo?> {
        return workManager.getWorkInfosForUniqueWorkFlow(SyncWorker.WORK_NAME)
            .map { workInfos -> workInfos.firstOrNull() }
    }

    /**
     * Check if periodic sync is currently scheduled
     */
    suspend fun isPeriodicSyncScheduled(): Boolean {
        val workInfos = workManager.getWorkInfosForUniqueWork(SyncWorker.WORK_NAME).await()
        return workInfos.any { !it.state.isFinished }
    }

    /**
     * Get the last sync timestamp from work data
     */
    suspend fun getLastSyncTimestamp(): Long? {
        val workInfos = workManager.getWorkInfosForUniqueWork(SyncWorker.WORK_NAME).await()
        return workInfos.firstOrNull()?.outputData?.getLong(SyncWorker.KEY_TIMESTAMP, -1)
            ?.takeIf { it != -1L }
    }

    companion object {
        private const val TAG_SYNC = "sync"
        private const val TAG_IMMEDIATE_SYNC = "immediate_sync"
        private const val DEFAULT_SYNC_INTERVAL_HOURS = 6L // Sync every 6 hours by default
    }
}
