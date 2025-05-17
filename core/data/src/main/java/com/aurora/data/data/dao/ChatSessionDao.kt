package com.aurora.data.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.aurora.data.data.entity.CHATS_TABLE_NAME
import com.aurora.data.data.entity.ChatSessionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatSessionDao {

    /**
     * Inserts a new chat session into the database. If the session already exists, it will be replaced.
     *
     * @param chatSession The chat session to insert.
     * @return The row ID of the newly inserted session.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSession(chatSession: ChatSessionEntity): Long

    /**
     * Updates an existing chat session in the database.
     *
     * @param chatSession The chat session to update.
     */
    @Update
    suspend fun updateSession(chatSession: ChatSessionEntity)

    /**
     * Retrieves a specific chat session by its ID.
     *
     * @param sessionId The ID of the chat session.
     * @return A Flow emitting the chat session with the given ID, or null if not found.
     */
    @Query("SELECT * FROM $CHATS_TABLE_NAME WHERE id = :sessionId")
    fun getSessionById(sessionId: Long): Flow<ChatSessionEntity?>

    /**
     * Retrieves all chat sessions, ordered by their start time in descending order (newest first).
     *
     * @return A Flow emitting a list of all chat sessions.
     */
    @Query("SELECT * FROM $CHATS_TABLE_NAME ORDER BY start_time DESC")
    fun getAllSessions(): Flow<List<ChatSessionEntity>>

    /**
     * Retrieves all chat sessions associated with a specific trip ID,
     * ordered by their start time in descending order (newest first).
     *
     * @param tripId The ID of the trip.
     * @return A Flow emitting a list of chat sessions for the given trip.
     */
    @Query("SELECT * FROM $CHATS_TABLE_NAME WHERE trip_id = :tripId ORDER BY start_time DESC")
    fun getSessionsForTrip(tripId: Long): Flow<List<ChatSessionEntity>>

    /**
     * Deletes a specific chat session from the database.
     *
     * @param chatSession The chat session to delete.
     */
    @Delete
    suspend fun deleteSession(chatSession: ChatSessionEntity)

    /**
     * Deletes a chat session by its ID.
     *
     * @param sessionId The ID of the session to delete.
     * @return The number of sessions deleted (should be 0 or 1).
     */
    @Query("DELETE FROM $CHATS_TABLE_NAME WHERE id = :sessionId")
    suspend fun deleteSessionById(sessionId: Long): Int

    /**
     * Deletes all chat sessions from the table.
     */
    @Query("DELETE FROM $CHATS_TABLE_NAME")
    suspend fun deleteAllSessions()



}