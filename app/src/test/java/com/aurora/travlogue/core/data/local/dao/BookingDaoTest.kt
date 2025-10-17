package com.aurora.travlogue.core.data.local.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import app.cash.turbine.test
import com.aurora.travlogue.core.data.local.database.TravlogueDatabase
import com.aurora.travlogue.core.data.local.entities.Booking
import com.aurora.travlogue.core.data.local.entities.BookingType
import com.aurora.travlogue.core.data.local.entities.DateType
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
 * Unit tests for BookingDao using in-memory Room database.
 * Tests timezone-aware booking storage.
 */
@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(RobolectricTestRunner::class)
class BookingDaoTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var database: TravlogueDatabase
    private lateinit var bookingDao: BookingDao
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

    // Test bookings
    private val booking1 = Booking(
        id = "booking-1",
        tripId = "trip-1",
        type = BookingType.FLIGHT,
        confirmationNumber = "JAL123",
        provider = "Japan Airlines",
        startDateTime = "2025-07-01T10:00:00-07:00",
        endDateTime = "2025-07-01T14:00:00+09:00",
        timezone = "Asia/Tokyo",
        fromLocation = "San Francisco (SFO)",
        toLocation = "Tokyo (NRT)",
        price = 800.0,
        currency = "USD",
        notes = "Window seat",
        imageUri = null
    )

    private val booking2 = Booking(
        id = "booking-2",
        tripId = "trip-1",
        type = BookingType.HOTEL,
        confirmationNumber = "HTL456",
        provider = "Tokyo Inn",
        startDateTime = "2025-07-01T15:00:00+09:00",
        endDateTime = "2025-07-04T11:00:00+09:00",
        timezone = "Asia/Tokyo",
        fromLocation = null,
        toLocation = "Tokyo Shibuya",
        price = 450.0,
        currency = "USD",
        notes = "Late check-in arranged",
        imageUri = null
    )

    @Before
    fun setup() = runTest {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            TravlogueDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()

        bookingDao = database.bookingDao()
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
    fun `insertBooking adds booking to database`() = runTest {
        // Act
        bookingDao.insertBooking(booking1)

        // Assert
        val result = bookingDao.getBookingById("booking-1")
        assertThat(result).isEqualTo(booking1)
    }

    @Test
    fun `insertBooking with REPLACE strategy updates existing booking`() = runTest {
        // Arrange
        bookingDao.insertBooking(booking1)

        // Act
        val updated = booking1.copy(confirmationNumber = "JAL456-UPDATED")
        bookingDao.insertBooking(updated)

        // Assert
        val result = bookingDao.getBookingById("booking-1")
        assertThat(result?.confirmationNumber).isEqualTo("JAL456-UPDATED")
    }

    @Test
    fun `insertBookings adds multiple bookings`() = runTest {
        // Act
        bookingDao.insertBookings(listOf(booking1, booking2))

        // Assert
        val results = bookingDao.getBookingsByTripIdSync("trip-1")
        assertThat(results).hasSize(2)
    }

    // ==================== Query Tests ====================

    @Test
    fun `getBookingById returns booking when exists`() = runTest {
        // Arrange
        bookingDao.insertBooking(booking1)

        // Act
        val result = bookingDao.getBookingById("booking-1")

        // Assert
        assertThat(result).isNotNull()
        assertThat(result?.provider).isEqualTo("Japan Airlines")
    }

    @Test
    fun `getBookingById returns null when booking does not exist`() = runTest {
        // Act
        val result = bookingDao.getBookingById("non-existent")

        // Assert
        assertThat(result).isNull()
    }

    @Test
    fun `getBookingsByTripIdSync returns bookings for specific trip`() = runTest {
        // Arrange
        bookingDao.insertBooking(booking1)
        bookingDao.insertBooking(booking2)

        // Act
        val results = bookingDao.getBookingsByTripIdSync("trip-1")

        // Assert
        assertThat(results).hasSize(2)
        assertThat(results.map { it.provider }).containsExactly(
            "Japan Airlines",
            "Tokyo Inn"
        ).inOrder()
    }

    @Test
    fun `getBookingsByTripIdSync returns bookings ordered by startDateTime`() = runTest {
        // Arrange
        val b1 = booking1.copy(id = "b1", startDateTime = "2025-07-03T10:00:00+09:00")
        val b2 = booking2.copy(id = "b2", startDateTime = "2025-07-01T15:00:00+09:00")
        val b3 = booking1.copy(id = "b3", startDateTime = "2025-07-02T12:00:00+09:00")

        bookingDao.insertBookings(listOf(b1, b2, b3))

        // Act
        val results = bookingDao.getBookingsByTripIdSync("trip-1")

        // Assert - Should be ordered by startDateTime ASC
        assertThat(results).hasSize(3)
        assertThat(results[0].id).isEqualTo("b2")
        assertThat(results[1].id).isEqualTo("b3")
        assertThat(results[2].id).isEqualTo("b1")
    }

    @Test
    fun `getBookingsByTripIdSync returns empty list when no bookings`() = runTest {
        // Act
        val results = bookingDao.getBookingsByTripIdSync("trip-1")

        // Assert
        assertThat(results).isEmpty()
    }

    @Test
    fun `getBookingsByTripId flow emits bookings`() = runTest {
        // Arrange
        bookingDao.insertBooking(booking1)

        // Act & Assert
        bookingDao.getBookingsByTripId("trip-1").test {
            val results = awaitItem()
            assertThat(results).hasSize(1)
            assertThat(results[0]).isEqualTo(booking1)
            cancel()
        }
    }

    // ==================== Update Tests ====================

    @Test
    fun `updateBooking modifies existing booking`() = runTest {
        // Arrange
        bookingDao.insertBooking(booking1)

        // Act
        val updated = booking1.copy(
            confirmationNumber = "NEW123",
            price = 900.0
        )
        bookingDao.updateBooking(updated)

        // Assert
        val result = bookingDao.getBookingById("booking-1")
        assertThat(result?.confirmationNumber).isEqualTo("NEW123")
        assertThat(result?.price).isEqualTo(900.0)
    }

    // ==================== Delete Tests ====================

    @Test
    fun `deleteBooking removes booking from database`() = runTest {
        // Arrange
        bookingDao.insertBooking(booking1)
        bookingDao.insertBooking(booking2)

        // Act
        bookingDao.deleteBooking(booking1)

        // Assert
        val b1 = bookingDao.getBookingById("booking-1")
        val b2 = bookingDao.getBookingById("booking-2")
        assertThat(b1).isNull()
        assertThat(b2).isNotNull()
    }

    @Test
    fun `deleteBookingById removes booking from database`() = runTest {
        // Arrange
        bookingDao.insertBooking(booking1)

        // Act
        bookingDao.deleteBookingById("booking-1")

        // Assert
        val result = bookingDao.getBookingById("booking-1")
        assertThat(result).isNull()
    }

    @Test
    fun `deleteBookingsByTripId removes all bookings for trip`() = runTest {
        // Arrange
        bookingDao.insertBooking(booking1)
        bookingDao.insertBooking(booking2)

        // Act
        bookingDao.deleteBookingsByTripId("trip-1")

        // Assert
        val results = bookingDao.getBookingsByTripIdSync("trip-1")
        assertThat(results).isEmpty()
    }

    // ==================== Foreign Key Tests ====================

    @Test
    fun `cascade delete removes bookings when trip is deleted`() = runTest {
        // Arrange
        bookingDao.insertBooking(booking1)
        bookingDao.insertBooking(booking2)

        // Act - Delete parent trip
        tripDao.deleteTrip(testTrip)

        // Assert - Bookings should be cascade deleted
        val results = bookingDao.getBookingsByTripIdSync("trip-1")
        assertThat(results).isEmpty()
    }

    // ==================== Timezone Storage Tests ====================

    @Test
    fun `booking stores timezone correctly`() = runTest {
        // Act
        bookingDao.insertBooking(booking1)

        // Assert
        val result = bookingDao.getBookingById("booking-1")
        assertThat(result?.timezone).isEqualTo("Asia/Tokyo")
        assertThat(result?.startDateTime).contains("-07:00") // SF timezone
        assertThat(result?.endDateTime).contains("+09:00") // Tokyo timezone
    }

    @Test
    fun `booking stores different timezones correctly`() = runTest {
        // Arrange
        val bookings = listOf(
            booking1.copy(id = "b1", timezone = "America/New_York", startDateTime = "2025-07-01T10:00:00-05:00"),
            booking1.copy(id = "b2", timezone = "Europe/London", startDateTime = "2025-07-01T15:00:00+01:00"),
            booking1.copy(id = "b3", timezone = "Asia/Tokyo", startDateTime = "2025-07-01T23:00:00+09:00")
        )

        // Act
        bookingDao.insertBookings(bookings)

        // Assert
        assertThat(bookingDao.getBookingById("b1")?.timezone).isEqualTo("America/New_York")
        assertThat(bookingDao.getBookingById("b2")?.timezone).isEqualTo("Europe/London")
        assertThat(bookingDao.getBookingById("b3")?.timezone).isEqualTo("Asia/Tokyo")
    }

    // ==================== Enum Storage Tests ====================

    @Test
    fun `BookingType enum stores and retrieves correctly`() = runTest {
        // Test all booking types
        val bookings = listOf(
            booking1.copy(id = "b1", type = BookingType.FLIGHT),
            booking1.copy(id = "b2", type = BookingType.HOTEL),
            booking1.copy(id = "b3", type = BookingType.TRAIN),
            booking1.copy(id = "b4", type = BookingType.BUS),
            booking1.copy(id = "b5", type = BookingType.TICKET),
            booking1.copy(id = "b6", type = BookingType.OTHER)
        )

        bookingDao.insertBookings(bookings)

        assertThat(bookingDao.getBookingById("b1")?.type).isEqualTo(BookingType.FLIGHT)
        assertThat(bookingDao.getBookingById("b2")?.type).isEqualTo(BookingType.HOTEL)
        assertThat(bookingDao.getBookingById("b3")?.type).isEqualTo(BookingType.TRAIN)
        assertThat(bookingDao.getBookingById("b4")?.type).isEqualTo(BookingType.BUS)
        assertThat(bookingDao.getBookingById("b5")?.type).isEqualTo(BookingType.TICKET)
        assertThat(bookingDao.getBookingById("b6")?.type).isEqualTo(BookingType.OTHER)
    }

    // ==================== Edge Cases ====================

    @Test
    fun `booking with null optional fields stores correctly`() = runTest {
        // Arrange
        val minimalBooking = booking1.copy(
            confirmationNumber = null,
            fromLocation = null,
            price = null,
            currency = null,
            notes = null,
            imageUri = null,
            endDateTime = null
        )

        // Act
        bookingDao.insertBooking(minimalBooking)

        // Assert
        val result = bookingDao.getBookingById("booking-1")
        assertThat(result?.confirmationNumber).isNull()
        assertThat(result?.fromLocation).isNull()
        assertThat(result?.price).isNull()
        assertThat(result?.currency).isNull()
        assertThat(result?.notes).isNull()
        assertThat(result?.imageUri).isNull()
        assertThat(result?.endDateTime).isNull()
    }

    @Test
    fun `booking with image URI stores correctly`() = runTest {
        // Arrange
        val bookingWithImage = booking1.copy(
            imageUri = "content://media/external/images/media/123"
        )

        // Act
        bookingDao.insertBooking(bookingWithImage)

        // Assert
        val result = bookingDao.getBookingById("booking-1")
        assertThat(result?.imageUri).isEqualTo("content://media/external/images/media/123")
    }

    @Test
    fun `multiple operations maintain data consistency`() = runTest {
        // Act - Complex sequence
        bookingDao.insertBooking(booking1)
        bookingDao.insertBooking(booking2)
        val booking3 = booking1.copy(id = "booking-3", provider = "Third Booking")
        bookingDao.insertBooking(booking3)
        bookingDao.deleteBooking(booking2)
        bookingDao.updateBooking(booking1.copy(provider = "Updated Airlines"))

        // Assert
        val results = bookingDao.getBookingsByTripIdSync("trip-1")
        assertThat(results).hasSize(2)
        assertThat(results[0].provider).isEqualTo("Updated Airlines")
        assertThat(results[1].provider).isEqualTo("Third Booking")
    }
}
