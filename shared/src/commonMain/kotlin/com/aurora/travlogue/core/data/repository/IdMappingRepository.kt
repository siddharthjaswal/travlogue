package com.aurora.travlogue.core.data.repository

import com.aurora.travlogue.core.data.local.TravlogueDb
import com.aurora.travlogue.database.Id_mappings

/**
 * Repository for managing ID mappings between local UUIDs and backend Integer IDs
 *
 * This is critical infrastructure for offline-first sync. It allows us to:
 * 1. Create entities offline with UUID
 * 2. Sync to backend and get Integer ID
 * 3. Store the mapping UUID ↔ Integer
 * 4. Use the mapping for all future sync operations
 *
 * Entity types:
 * - "trip"
 * - "activity"
 * - "booking"
 * - "trip_day" (future)
 */
class IdMappingRepository(private val database: TravlogueDb) {

    /**
     * Save or update a mapping
     *
     * @param localId Local UUID (e.g., "550e8400-e29b-41d4-a716-446655440000")
     * @param backendId Backend integer ID (e.g., 123)
     * @param entityType Entity type ("trip", "activity", "booking", etc.)
     */
    suspend fun saveMapping(localId: String, backendId: Int, entityType: EntityType) {
        val now = System.currentTimeMillis()
        database.idMappingQueries.insertOrReplace(
            local_id = localId,
            backend_id = backendId.toLong(),
            entity_type = entityType.value,
            created_at = now,
            updated_at = now
        )
    }

    /**
     * Get backend ID from local ID
     *
     * @return Backend ID if mapping exists, null otherwise
     */
    suspend fun getBackendId(localId: String, entityType: EntityType): Int? {
        return database.idMappingQueries
            .getBackendId(localId, entityType.value)
            .executeAsOneOrNull()
            ?.toInt()
    }

    /**
     * Get local ID from backend ID
     *
     * @return Local UUID if mapping exists, null otherwise
     */
    suspend fun getLocalId(backendId: Int, entityType: EntityType): String? {
        return database.idMappingQueries
            .getLocalId(backendId.toLong(), entityType.value)
            .executeAsOneOrNull()
    }

    /**
     * Get full mapping by local ID
     */
    suspend fun getMappingByLocalId(localId: String, entityType: EntityType): Id_mappings? {
        return database.idMappingQueries
            .getMappingByLocalId(localId, entityType.value)
            .executeAsOneOrNull()
    }

    /**
     * Get full mapping by backend ID
     */
    suspend fun getMappingByBackendId(backendId: Int, entityType: EntityType): Id_mappings? {
        return database.idMappingQueries
            .getMappingByBackendId(backendId.toLong(), entityType.value)
            .executeAsOneOrNull()
    }

    /**
     * Check if mapping exists for local ID
     */
    suspend fun existsByLocalId(localId: String, entityType: EntityType): Boolean {
        return database.idMappingQueries
            .existsByLocalId(localId, entityType.value)
            .executeAsOne()
    }

    /**
     * Check if mapping exists for backend ID
     */
    suspend fun existsByBackendId(backendId: Int, entityType: EntityType): Boolean {
        return database.idMappingQueries
            .existsByBackendId(backendId.toLong(), entityType.value)
            .executeAsOne()
    }

    /**
     * Delete mapping by local ID
     */
    suspend fun deleteByLocalId(localId: String, entityType: EntityType) {
        database.idMappingQueries.deleteByLocalId(localId, entityType.value)
    }

    /**
     * Delete mapping by backend ID
     */
    suspend fun deleteByBackendId(backendId: Int, entityType: EntityType) {
        database.idMappingQueries.deleteByBackendId(backendId.toLong(), entityType.value)
    }

    /**
     * Get all mappings for an entity type
     */
    suspend fun getAllMappingsForType(entityType: EntityType): List<Id_mappings> {
        return database.idMappingQueries
            .getAllMappingsForType(entityType.value)
            .executeAsList()
    }

    /**
     * Get all local IDs that don't have backend mappings (pending sync)
     */
    suspend fun getAllLocalOnlyIds(): List<String> {
        return database.idMappingQueries
            .getAllLocalOnlyIds()
            .executeAsList()
    }

    /**
     * Count mappings by entity type
     */
    suspend fun countMappingsByType(): Map<String, Long> {
        return database.idMappingQueries
            .countMappingsByType()
            .executeAsList()
            .associate { it.entity_type to (it.count ?: 0) }
    }

    /**
     * Delete all mappings (use with caution!)
     */
    suspend fun deleteAll() {
        database.idMappingQueries.deleteAll()
    }

    // ==================== Helper Methods ====================

    /**
     * Resolve ID to backend ID
     * If the ID is already an Int, return it. Otherwise, look up the mapping.
     *
     * @param id Either a UUID string or an Int string
     * @param entityType Entity type for lookup
     * @return Backend ID if found, null otherwise
     */
    suspend fun resolveToBackendId(id: String, entityType: EntityType): Int? {
        // Try to parse as Int first
        val intId = id.toIntOrNull()
        if (intId != null) {
            return intId
        }

        // It's a UUID, look up the mapping
        return getBackendId(id, entityType)
    }

    /**
     * Resolve ID to local ID
     * If the ID is a UUID, return it. Otherwise, look up the mapping.
     *
     * @param id Either a UUID string or an Int string
     * @param entityType Entity type for lookup
     * @return Local UUID if found, original ID if it's already a UUID
     */
    suspend fun resolveToLocalId(id: String, entityType: EntityType): String {
        // Try to parse as Int
        val intId = id.toIntOrNull()
        if (intId != null) {
            // Look up the local ID
            return getLocalId(intId, entityType) ?: id
        }

        // It's already a UUID
        return id
    }

    /**
     * Check if an ID is synced (has backend mapping)
     *
     * @param id Local UUID
     * @param entityType Entity type
     * @return true if the entity has been synced to backend
     */
    suspend fun isSynced(id: String, entityType: EntityType): Boolean {
        return existsByLocalId(id, entityType)
    }
}

/**
 * Entity types for ID mapping
 */
enum class EntityType(val value: String) {
    TRIP("trip"),
    ACTIVITY("activity"),
    BOOKING("booking"),
    TRIP_DAY("trip_day"),
    LOCATION("location");

    companion object {
        fun fromString(value: String): EntityType? {
            return values().find { it.value == value }
        }
    }
}
