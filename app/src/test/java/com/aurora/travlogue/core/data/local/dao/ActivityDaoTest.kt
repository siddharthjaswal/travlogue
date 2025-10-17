package com.aurora.travlogue.core.data.local.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import app.cash.turbine.test
import com.aurora.travlogue.core.data.local.database.TravlogueDatabase
import com.aurora.travlogue.core.data.local.entities.Activity
import com.aurora.travlogue.core.data.local.entities.ActivityType
import com.aurora.travlogue.core.data.local.entities.DateType
import com.aurora.travlogue.core.data.local.entities.Location
import com.aurora.travlogue.core.data.local.entities.TimeSlot
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
 * Unit tests for ActivityDao using in-memory Room database.
 * Tests include JOIN query functionality.
 */
@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(RobolectricTestRunner::class)
class ActivityDaoTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var database: TravlogueDatabase
    private lateinit var activityDao: ActivityDao
    private lateinit var locationDao: LocationDao
    private lateinit var tripDao: TripDao

    // Test data
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

    private val testLocation = Location(
        id = "loc-1",
        tripId = "trip-1",
        name = "Tokyo",
        country = "Japan",
        date = "2025-07-01",
        latitude = 35.6762,
        longitude = 139.6503,
        order = 1
    )

    private val activity1 = Activity(
        id = "act-1",
        locationId = "loc-1",
        title = "Visit Senso-ji Temple",
        description = "Explore the oldest temple in Tokyo",
        date = "2025-07-01",
        timeSlot = TimeSlot.MORNING,
        type = ActivityType.ATTRACTION
    )

    private val activity2 = Activity(
        id = "act-2",
        locationId = "loc-1",
        title = "Shibuya Crossing",
        description = "Experience the famous intersection",
        date = "2025-07-02",
        timeSlot = TimeSlot.AFTERNOON,
        type = ActivityType.ATTRACTION
    )

    @Before
    fun setup() = runTest {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            TravlogueDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()

        activityDao = database.activityDao()
        locationDao = database.locationDao()
        tripDao = database.tripDao()

        // Insert test data (required for foreign keys)
        tripDao.insertTrip(testTrip)
        locationDao.insertLocation(testLocation)
    }

    @After
    fun tearDown() {
        database.close()
    }

    // ==================== Insert Tests ====================

    @Test
    fun `insertActivity adds activity to database`() = runTest {
        // Act
        activityDao.insertActivity(activity1)

        // Assert
        val result = activityDao.getActivityById("act-1")
        assertThat(result).isEqualTo(activity1)
    }

    @Test
    fun `insertActivity with REPLACE strategy updates existing activity`() = runTest {
        // Arrange
        activityDao.insertActivity(activity1)

        // Act
        val updated = activity1.copy(title = "Updated Temple Visit")
        activityDao.insertActivity(updated)

        // Assert
        val result = activityDao.getActivityById("act-1")
        assertThat(result?.title).isEqualTo("Updated Temple Visit")
    }

    @Test
    fun `insertActivities adds multiple activities`() = runTest {
        // Act
        activityDao.insertActivities(listOf(activity1, activity2))

        // Assert
        val results = activityDao.getActivitiesByLocationIdSync("loc-1")
        assertThat(results).hasSize(2)
    }

    // ==================== Query Tests ====================

    @Test
    fun `getActivityById returns activity when exists`() = runTest {
        // Arrange
        activityDao.insertActivity(activity1)

        // Act
        val result = activityDao.getActivityById("act-1")

        // Assert
        assertThat(result).isNotNull()
        assertThat(result?.title).isEqualTo("Visit Senso-ji Temple")
    }

    @Test
    fun `getActivityById returns null when activity does not exist`() = runTest {
        // Act
        val result = activityDao.getActivityById("non-existent")

        // Assert
        assertThat(result).isNull()
    }

    @Test
    fun `getActivitiesByLocationIdSync returns activities for specific location`() = runTest {
        // Arrange
        activityDao.insertActivity(activity1)
        activityDao.insertActivity(activity2)

        // Act
        val results = activityDao.getActivitiesByLocationIdSync("loc-1")

        // Assert
        assertThat(results).hasSize(2)
        assertThat(results.map { it.title }).containsExactly(
            "Visit Senso-ji Temple",
            "Shibuya Crossing"
        ).inOrder()
    }

    @Test
    fun `getActivitiesByLocationIdSync returns activities ordered by date`() = runTest {
        // Arrange
        val act1 = activity1.copy(date = "2025-07-03")
        val act2 = activity2.copy(date = "2025-07-01")
        val act3 = activity1.copy(id = "act-3", title = "Activity 3", date = "2025-07-02")

        activityDao.insertActivities(listOf(act1, act2, act3))

        // Act
        val results = activityDao.getActivitiesByLocationIdSync("loc-1")

        // Assert - Should be ordered by date ASC
        assertThat(results).hasSize(3)
        assertThat(results[0].date).isEqualTo("2025-07-01")
        assertThat(results[1].date).isEqualTo("2025-07-02")
        assertThat(results[2].date).isEqualTo("2025-07-03")
    }

    @Test
    fun `getActivitiesByTripIdSync returns activities via JOIN query`() = runTest {
        // Arrange
        activityDao.insertActivity(activity1)
        activityDao.insertActivity(activity2)

        // Act - Using JOIN to get activities by tripId
        val results = activityDao.getActivitiesByTripIdSync("trip-1")

        // Assert
        assertThat(results).hasSize(2)
        assertThat(results.map { it.title }).contains("Visit Senso-ji Temple")
    }

    @Test
    fun `getActivitiesByTripIdSync with multiple locations returns all activities`() = runTest {
        // Arrange - Add second location
        val location2 = testLocation.copy(id = "loc-2", name = "Kyoto", order = 2)
        locationDao.insertLocation(location2)

        val activityLoc1 = activity1
        val activityLoc2 = activity2.copy(locationId = "loc-2", title = "Kinkaku-ji Temple")

        activityDao.insertActivity(activityLoc1)
        activityDao.insertActivity(activityLoc2)

        // Act
        val results = activityDao.getActivitiesByTripIdSync("trip-1")

        // Assert - Should get activities from both locations
        assertThat(results).hasSize(2)
        assertThat(results.map { it.title }).containsExactly(
            "Visit Senso-ji Temple",
            "Kinkaku-ji Temple"
        )
    }

    @Test
    fun `getActivitiesByLocationId flow emits activities`() = runTest {
        // Arrange
        activityDao.insertActivity(activity1)

        // Act & Assert
        activityDao.getActivitiesByLocationId("loc-1").test {
            val results = awaitItem()
            assertThat(results).hasSize(1)
            assertThat(results[0]).isEqualTo(activity1)
            cancel()
        }
    }

    // ==================== Update Tests ====================

    @Test
    fun `updateActivity modifies existing activity`() = runTest {
        // Arrange
        activityDao.insertActivity(activity1)

        // Act
        val updated = activity1.copy(
            title = "Updated Title",
            timeSlot = TimeSlot.EVENING
        )
        activityDao.updateActivity(updated)

        // Assert
        val result = activityDao.getActivityById("act-1")
        assertThat(result?.title).isEqualTo("Updated Title")
        assertThat(result?.timeSlot).isEqualTo(TimeSlot.EVENING)
    }

    // ==================== Delete Tests ====================

    @Test
    fun `deleteActivity removes activity from database`() = runTest {
        // Arrange
        activityDao.insertActivity(activity1)
        activityDao.insertActivity(activity2)

        // Act
        activityDao.deleteActivity(activity1)

        // Assert
        val act1 = activityDao.getActivityById("act-1")
        val act2 = activityDao.getActivityById("act-2")
        assertThat(act1).isNull()
        assertThat(act2).isNotNull()
    }

    @Test
    fun `deleteActivityById removes activity from database`() = runTest {
        // Arrange
        activityDao.insertActivity(activity1)

        // Act
        activityDao.deleteActivityById("act-1")

        // Assert
        val result = activityDao.getActivityById("act-1")
        assertThat(result).isNull()
    }

    @Test
    fun `deleteActivitiesByLocationId removes all activities for location`() = runTest {
        // Arrange
        activityDao.insertActivity(activity1)
        activityDao.insertActivity(activity2)

        // Act
        activityDao.deleteActivitiesByLocationId("loc-1")

        // Assert
        val results = activityDao.getActivitiesByLocationIdSync("loc-1")
        assertThat(results).isEmpty()
    }

    // ==================== Foreign Key Tests ====================

    @Test
    fun `cascade delete removes activities when location is deleted`() = runTest {
        // Arrange
        activityDao.insertActivity(activity1)
        activityDao.insertActivity(activity2)

        // Act - Delete parent location
        locationDao.deleteLocation(testLocation)

        // Assert - Activities should be cascade deleted
        val results = activityDao.getActivitiesByLocationIdSync("loc-1")
        assertThat(results).isEmpty()
    }

    // ==================== Enum Storage Tests ====================

    @Test
    fun `TimeSlot enum stores and retrieves correctly`() = runTest {
        // Test all time slots
        val activities = listOf(
            activity1.copy(id = "a1", timeSlot = TimeSlot.MORNING),
            activity1.copy(id = "a2", timeSlot = TimeSlot.AFTERNOON),
            activity1.copy(id = "a3", timeSlot = TimeSlot.EVENING),
            activity1.copy(id = "a4", timeSlot = TimeSlot.FULL_DAY),
            activity1.copy(id = "a5", timeSlot = null)
        )

        activityDao.insertActivities(activities)

        assertThat(activityDao.getActivityById("a1")?.timeSlot).isEqualTo(TimeSlot.MORNING)
        assertThat(activityDao.getActivityById("a2")?.timeSlot).isEqualTo(TimeSlot.AFTERNOON)
        assertThat(activityDao.getActivityById("a3")?.timeSlot).isEqualTo(TimeSlot.EVENING)
        assertThat(activityDao.getActivityById("a4")?.timeSlot).isEqualTo(TimeSlot.FULL_DAY)
        assertThat(activityDao.getActivityById("a5")?.timeSlot).isNull()
    }

    @Test
    fun `ActivityType enum stores and retrieves correctly`() = runTest {
        // Test all activity types
        val activities = listOf(
            activity1.copy(id = "a1", type = ActivityType.ATTRACTION),
            activity1.copy(id = "a2", type = ActivityType.FOOD),
            activity1.copy(id = "a3", type = ActivityType.BOOKING),
            activity1.copy(id = "a4", type = ActivityType.TRANSIT),
            activity1.copy(id = "a5", type = ActivityType.OTHER)
        )

        activityDao.insertActivities(activities)

        assertThat(activityDao.getActivityById("a1")?.type).isEqualTo(ActivityType.ATTRACTION)
        assertThat(activityDao.getActivityById("a2")?.type).isEqualTo(ActivityType.FOOD)
        assertThat(activityDao.getActivityById("a3")?.type).isEqualTo(ActivityType.BOOKING)
        assertThat(activityDao.getActivityById("a4")?.type).isEqualTo(ActivityType.TRANSIT)
        assertThat(activityDao.getActivityById("a5")?.type).isEqualTo(ActivityType.OTHER)
    }

    // ==================== Edge Cases ====================

    @Test
    fun `activity with null description stores correctly`() = runTest {
        // Arrange
        val activityWithoutDesc = activity1.copy(description = null)

        // Act
        activityDao.insertActivity(activityWithoutDesc)

        // Assert
        val result = activityDao.getActivityById("act-1")
        assertThat(result?.description).isNull()
    }

    @Test
    fun `activity with null date stores correctly`() = runTest {
        // Arrange
        val activityWithoutDate = activity1.copy(date = null)

        // Act
        activityDao.insertActivity(activityWithoutDate)

        // Assert
        val result = activityDao.getActivityById("act-1")
        assertThat(result?.date).isNull()
    }
}
