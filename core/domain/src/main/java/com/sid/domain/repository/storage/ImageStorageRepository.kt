package com.sid.domain.repository.storage

import android.content.Context
import android.graphics.Bitmap
import android.os.Environment
import dagger.hilt.android.qualifiers.ApplicationContext
import timber.log.Timber
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository interface for saving images to device storage.
 */
interface ImageStorageRepository {
    /**
     * Saves a bitmap image to a publicly accessible pictures directory.
     *
     * @param bitmap The bitmap image to save.
     * @param fileNamePrefix A prefix to use for the generated filename (e.g., "Banner_").
     *                       A timestamp and extension will be appended.
     * @return The absolute file path of the saved image if successful, or null otherwise.
     */
    suspend fun saveImageToPictures(bitmap: Bitmap, fileNamePrefix: String): String?
}

// Logging constants specific to this implementation
private const val LOG_SAVING_BANNER_TO_FILE = "Attempting to save banner image to file: %s"
private const val LOG_BANNER_SAVED_SUCCESS_PATH = "Successfully saved banner image to: %s"
private const val LOG_BANNER_SAVING_ERROR = "Error saving banner image to file"
private const val LOG_EXTERNAL_STORAGE_UNAVAILABLE = "External storage unavailable or pictures directory is null. Cannot save banner."
private const val LOG_CREATE_IMAGE_FILE_DIR_ERROR = "Could not create directory for saving image: %s"

@Singleton // Make it a singleton if appropriate for your DI setup
class ImageStorageRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : ImageStorageRepository {

    override suspend fun saveImageToPictures(bitmap: Bitmap, fileNamePrefix: String): String? {
        val picturesDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        if (picturesDir == null) {
            Timber.e(LOG_EXTERNAL_STORAGE_UNAVAILABLE)
            return null
        }

        if (!picturesDir.exists()) {
            if (!picturesDir.mkdirs()) {
                Timber.e(LOG_CREATE_IMAGE_FILE_DIR_ERROR, picturesDir.absolutePath)
                return null
            }
        }

        val fileName = "${fileNamePrefix}${System.currentTimeMillis()}.jpg"
        val imageFile = File(picturesDir, fileName)
        Timber.d(LOG_SAVING_BANNER_TO_FILE, imageFile.absolutePath)

        return try {
            FileOutputStream(imageFile).use { fos ->
                bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fos) // quality 90
            }
            Timber.d(LOG_BANNER_SAVED_SUCCESS_PATH, imageFile.absolutePath)
            imageFile.absolutePath
        } catch (e: Exception) {
            Timber.e(e, LOG_BANNER_SAVING_ERROR)
            null
        }
    }
}
