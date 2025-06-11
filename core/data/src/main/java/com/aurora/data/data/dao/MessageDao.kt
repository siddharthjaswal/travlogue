package com.aurora.data.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aurora.data.data.entity.message.MESSAGES_TABLE_NAME
import com.aurora.data.data.entity.message.MessageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MessageDao {

    /**
     * Inserts a new message into the database. If the message already exists, it will be replaced.
     *
     * @param message The message to insert.
     * @return The row ID of the newly inserted message.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(message: MessageEntity): Long

    /**
     * Retrieves all messages for a specific chat session, ordered by timestamp.
     *
     * @param tripId The ID of the chat session.
     * @return A Flow emitting a list of messages for the given session.
     */
    @Query("SELECT * FROM $MESSAGES_TABLE_NAME WHERE trip_id = :tripId ORDER BY timestamp ASC")
    fun getMessagesFlowForTripId(tripId: Long): Flow<List<MessageEntity>>

    @Query("SELECT * FROM $MESSAGES_TABLE_NAME WHERE trip_id = :tripId ORDER BY timestamp ASC")
    suspend fun getMessagesForTripId(tripId: Long): List<MessageEntity>

    /**
     * Retrieves a specific message by its ID.
     *
     * @param messageId The ID of the message.
     * @return A Flow emitting the message with the given ID, or null if not found.
     */
    @Query("SELECT * FROM $MESSAGES_TABLE_NAME WHERE id = :messageId")
    fun getMessageById(messageId: Long): Flow<MessageEntity?>

    /**
     * Deletes a specific message from the database.
     *
     * @param message The message to delete.
     */
    @Delete
    suspend fun deleteMessage(message: MessageEntity)

    /**
     * Deletes all messages associated with a specific chat session.
     *
     * @param tripId The ID of the chat session whose messages are to be deleted.
     * @return The number of messages deleted.
     */
    @Query("DELETE FROM $MESSAGES_TABLE_NAME WHERE trip_id = :tripId")
    suspend fun deleteMessagesForSession(tripId: Long): Int

    /**
     * Deletes all messages from the table.
     */
    @Query("DELETE FROM $MESSAGES_TABLE_NAME")
    suspend fun deleteAllMessages()


}