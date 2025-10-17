package com.aurora.travlogue.core.data.local.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import app.cash.turbine.test
import com.aurora.travlogue.core.data.local.database.TravlogueDatabase
import com.aurora.travlogue.core.data.local.entities.DateType
import com.aurora.travlogue.core.data.local.entities.Location
import com.aurora.travlogue.core.data.local.entities.Trip
import com.aurora.travlogue.testutil.MainDispatcherRule
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

/**
 * Unit tests for LocationDao using in-memory Room database.
 */
@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(RobolectricTestRunner::class)
class LocationDaoTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var database: TravlogueDatabase
    private lateinit var locationDao: LocationDao
    private lateinit var tripDao: TripDao

    // Test trip (required for foreign key)
    private val testTrip = Trip(
        id = "trip-1",
        name = "Japan Adventure",
        originCity = "San Francisco",
        dateType = DateType.FIXED,
        startDate = "2025-07-01",
        endDate = "2025-07-07",
        flexibleMonth = null,
        flexibleDuration = null
    )

    // Test locations
    private val location1 = Location(
        id = "loc-1",
        tripId = "trip-1",
        name = "Tokyo",
        country = "Japan",
        date = "2025-07-01",
        latitude = 35.6762,
        longitude = 139.6503,
        order = 1
    )

    private val location2 = Location(
        id = "loc-2",
        tripId = "trip-1",
        name = "Kyoto",
        country = "Japan",
        date = "2025-07-04",
        latitude = 35.0116,
        longitude = 135.7681,
        order = 2
    )

    @Before
    fun setup() = runTest {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            TravlogueDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()

        locationDao = database.locationDao()
        tripDao = database.tripDao()

        // Insert test trip (required for foreign key)
        tripDao.insertTrip(testTrip)
    }

    @After
    fun tearDown() {
        database.close()
    }

    // ==================== Insert Tests ====================

    @Test
    fun `insertLocation adds location to database`() = runTest {
        // Act
        locationDao.insertLocation(location1)

        // Assert
        val result = locationDao.getLocationById("loc-1")
        assertThat(result).isEqualTo(location1)
    }

    @Test
    fun `insertLocation with REPLACE strategy updates existing location`() = runTest {
        // Arrange
        locationDao.insertLocation(location1)

        // Act - Insert with same ID but different name
        val updatedLocation = location1.copy(name = "Tokyo Updated")
        locationDao.insertLocation(updatedLocation)

        // Assert
        val result = locationDao.getLocationById("loc-1")
        assertThat(result?.name).isEqualTo("Tokyo Updated")
    }

    @Test
    fun `insertLocations adds multiple locations`() = runTest {
        // Act
        locationDao.insertLocations(listOf(location1, location2))

        // Assert
        val results = locationDao.getLocationsByTripIdSync("trip-1")
        assertThat(results).hasSize(2)
    }

    // ==================== Query Tests ====================

    @Test
    fun `getLocationById returns location when exists`() = runTest {
        // Arrange
        locationDao.insertLocation(location1)

        // Act
        val result = locationDao.getLocationById("loc-1")

        // Assert
        assertThat(result).isNotNull()
        assertThat(result?.name).isEqualTo("Tokyo")
    }

    @Test
    fun `getLocationById returns null when location does not exist`() = runTest {
        // Act
        val result = locationDao.getLocationById("non-existent")

        // Assert
        assertThat(result).isNull()
    }

    @Test
    fun `getLocationsByTripIdSync returns locations for specific trip`() = runTest {
        // Arrange
        locationDao.insertLocation(location1)
        locationDao.insertLocation(location2)

        // Act
        val results = locationDao.getLocationsByTripIdSync("trip-1")

        // Assert
        assertThat(results).hasSize(2)
        assertThat(results.map { it.name }).containsExactly("Tokyo", "Kyoto").inOrder()
    }

    @Test
    fun `getLocationsByTripIdSync returns locations ordered by order field`() = runTest {
        // Arrange - Insert in reverse order
        val loc1 = location1.copy(order = 3)
        val loc2 = location2.copy(order = 1)
        val loc3 = location1.copy(id = "loc-3", name = "Osaka", order = 2)

        locationDao.insertLocation(loc1)
        locationDao.insertLocation(loc2)
        locationDao.insertLocation(loc3)

        // Act
        val results = locationDao.getLocationsByTripIdSync("trip-1")

        // Assert - Should be ordered by 'order' field (1, 2, 3)
        assertThat(results).hasSize(3)
        assertThat(results[0].order).isEqualTo(1)
        assertThat(results[0].name).isEqualTo("Kyoto")
        assertThat(results[1].order).isEqualTo(2)
        assertThat(results[1].name).isEqualTo("Osaka")
        assertThat(results[2].order).isEqualTo(3)
        assertThat(results[2].name).isEqualTo("Tokyo")
    }

    @Test
    fun `getLocationsByTripIdSync returns empty list when no locations`() = runTest {
        // Act
        val results = locationDao.getLocationsByTripIdSync("trip-1")

        // Assert
        assertThat(results).isEmpty()
    }

    @Test
    fun `getLocationsByTripId flow emits locations`() = runTest {
        // Arrange
        locationDao.insertLocation(location1)

        // Act & Assert
        locationDao.getLocationsByTripId("trip-1").test {
            val results = awaitItem()
            assertThat(results).hasSize(1)
            assertThat(results[0]).isEqualTo(location1)
            cancel()
        }
    }

    // ==================== Update Tests ====================

    @Test
    fun `updateLocation modifies existing location`() = runTest {
        // Arrange
        locationDao.insertLocation(location1)

        // Act
        val updatedLocation = location1.copy(
            name = "Tokyo Metropolitan Area",
            latitude = 35.6897,
            longitude = 139.6922
        )
        locationDao.updateLocation(updatedLocation)

        // Assert
        val result = locationDao.getLocationById("loc-1")
        assertThat(result?.name).isEqualTo("Tokyo Metropolitan Area")
        assertThat(result?.latitude).isEqualTo(35.6897)
    }

    @Test
    fun `updateLocation changes order`() = runTest {
        // Arrange
        locationDao.insertLocations(listOf(location1, location2))

        // Act - Swap orders
        locationDao.updateLocation(location1.copy(order = 2))
        locationDao.updateLocation(location2.copy(order = 1))

        // Assert
        val results = locationDao.getLocationsByTripIdSync("trip-1")
        assertThat(results[0].name).isEqualTo("Kyoto")
        assertThat(results[1].name).isEqualTo("Tokyo")
    }

    // ==================== Delete Tests ====================

    @Test
    fun `deleteLocation removes location from database`() = runTest {
        // Arrange
        locationDao.insertLocation(location1)
        locationDao.insertLocation(location2)

        // Act
        locationDao.deleteLocation(location1)

        // Assert
        val loc1 = locationDao.getLocationById("loc-1")
        val loc2 = locationDao.getLocationById("loc-2")
        assertThat(loc1).isNull()
        assertThat(loc2).isNotNull()
    }

    @Test
    fun `deleteLocationById removes location from database`() = runTest {
        // Arrange
        locationDao.insertLocation(location1)

        // Act
        locationDao.deleteLocationById("loc-1")

        // Assert
        val result = locationDao.getLocationById("loc-1")
        assertThat(result).isNull()
    }

    @Test
    fun `deleteLocationsByTripId removes all locations for trip`() = runTest {
        // Arrange
        locationDao.insertLocation(location1)
        locationDao.insertLocation(location2)

        // Act
        locationDao.deleteLocationsByTripId("trip-1")

        // Assert
        val results = locationDao.getLocationsByTripIdSync("trip-1")
        assertThat(results).isEmpty()
    }

    // ==================== Foreign Key Tests ====================

    @Test
    fun `cascade delete removes locations when trip is deleted`() = runTest {
        // Arrange
        locationDao.insertLocation(location1)
        locationDao.insertLocation(location2)

        // Act - Delete parent trip
        tripDao.deleteTrip(testTrip)

        // Assert - Locations should be cascade deleted
        val results = locationDao.getLocationsByTripIdSync("trip-1")
        assertThat(results).isEmpty()
    }

    // ==================== Edge Cases ====================

    @Test
    fun `location with null coordinates stores correctly`() = runTest {
        // Arrange
        val locationWithoutCoords = location1.copy(latitude = null, longitude = null)

        // Act
        locationDao.insertLocation(locationWithoutCoords)

        // Assert
        val result = locationDao.getLocationById("loc-1")
        assertThat(result?.latitude).isNull()
        assertThat(result?.longitude).isNull()
    }

    @Test
    fun `location with null date stores correctly`() = runTest {
        // Arrange
        val locationWithoutDate = location1.copy(date = null)

        // Act
        locationDao.insertLocation(locationWithoutDate)

        // Assert
        val result = locationDao.getLocationById("loc-1")
        assertThat(result?.date).isNull()
    }

    @Test
    fun `multiple operations maintain data consistency`() = runTest {
        // Act - Complex sequence
        locationDao.insertLocation(location1)
        locationDao.insertLocation(location2)
        val loc3 = location1.copy(id = "loc-3", name = "Osaka", order = 3)
        locationDao.insertLocation(loc3)
        locationDao.deleteLocation(location2)
        locationDao.updateLocation(location1.copy(name = "New Tokyo"))

        // Assert
        val results = locationDao.getLocationsByTripIdSync("trip-1")
        assertThat(results).hasSize(2)
        assertThat(results[0].name).isEqualTo("New Tokyo")
        assertThat(results[1].name).isEqualTo("Osaka")
    }
}
