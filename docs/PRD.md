# Travlogue - Product Requirements Document

**Version:** 1.0  
**Date:** October 15, 2025  
**Platform:** Android (Kotlin)  
**Type:** Personal Travel Planning Assistant

---

## 1. Vision & Problem Statement

### The Problem
Planning travel involves juggling multiple concerns across different platforms:
- Understanding destinations and what to see
- Weather considerations affecting location choices
- Flight price monitoring
- Day-by-day itinerary planning
- Transit connections between locations
- Managing booking details

### The Solution
Travlogue is an intelligent travel planning app that:
- Consolidates all trip planning in one place
- Identifies gaps in your itinerary (missing transits, unplanned days)
- Suggests solutions with real pricing and timing
- Works offline once your trip is planned
- Helps you make informed decisions about where to go and when

---

## 2. Core User Flow

### Primary Journey: Planning a Trip

```
1. Create Trip
   ├─ Option A: Fixed dates ("Spain, Nov 10-20")
   └─ Option B: Flexible ("2 weeks in November, Europe")

2. Research Phase
   ├─ Discover attractions/things to do
   ├─ Check weather forecasts for timeframe
   ├─ Get flight price suggestions
   └─ AI-powered destination recommendations

3. Build Itinerary
   ├─ Add locations by date
   ├─ Note activities/bookings
   └─ Save booking details (flights, hotels, tickets)

4. Gap Detection & Resolution
   ├─ App identifies: "Barcelona (Nov 21) → Madrid (Nov 22)"
   ├─ App suggests: Train (€30, 2.5hr) | Flight (€45, 1hr) | Bus (€20, 6hr)
   └─ User selects and adds to itinerary

5. Offline Access
   └─ View complete trip details without internet
```

---

## 3. MVP Feature Set

### 3.1 Trip Creation & Management
- **Create New Trip**
    - Name trip (e.g., "Spain Adventure 2025")
    - Set destination(s)
    - Choose date type: Fixed dates OR Flexible (month/duration)
    - Set origin city (for flight searches)

- **Trip Dashboard**
    - List of all trips (upcoming, past)
    - Quick stats: Days, locations, budget (future)

### 3.2 Research & Discovery
- **Destination Intelligence**
    - Top attractions API integration (Google Places / TripAdvisor)
    - Weather forecast for selected dates/locations
    - Best time to visit recommendations

- **Flight Price Monitoring**
    - Check prices from origin city
    - Show price trends (if API supports)
    - Multiple departure/return date options for flexible trips

### 3.3 Itinerary Building
- **Day-by-Day Planner**
    - Timeline view of trip
    - Add locations by date
    - Add activities/notes per day
    - Time blocking (Morning/Afternoon/Evening)

- **Booking Management**
    - Save flight details (confirmation #, time, airline)
    - Save hotel/accommodation details
    - Save tickets/reservations
    - Attach photos of bookings

### 3.4 Intelligent Gap Detection ⭐ **Key Feature**
- **Auto-detect gaps:**
    - Location jumps without transit plan
    - Unplanned days between locations
    - Time conflicts (overlapping bookings)

- **Smart Suggestions:**
    - Train options (price, duration, provider)
    - Flight options (price, duration, airline)
    - Bus/road options
    - Data from Rome2Rio API or similar

### 3.5 Offline Support
- **Offline Mode:**
    - View complete itinerary without internet
    - Access booking details
    - View saved attraction info
    - View cached maps (future enhancement)

- **Sync Requirements:**
    - Need internet for gap detection
    - Need internet for price updates
    - Need internet for weather refresh

---

## 4. Technical Architecture

### 4.1 Tech Stack
- **Language:** Kotlin
- **Architecture:** MVVM (Model-View-ViewModel)
- **Database:** Room (SQLite) - for offline storage
- **API Calls:** Retrofit + OkHttp
- **Async Operations:** Coroutines + Flow
- **UI:** Jetpack Compose (modern) OR XML layouts (if preferred)
- **Navigation:** Jetpack Navigation Component

### 4.2 Data Models (Core Entities)

```kotlin
@Entity
data class Trip(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val name: String,
    val originCity: String,
    val dateType: DateType, // FIXED or FLEXIBLE
    val startDate: LocalDate?,
    val endDate: LocalDate?,
    val flexibleMonth: String?, // "November 2025"
    val flexibleDuration: Int?, // days
    val createdAt: Long,
    val updatedAt: Long
)

enum class DateType { FIXED, FLEXIBLE }

@Entity
data class Location(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val tripId: String,
    val name: String,
    val country: String,
    val date: LocalDate?,
    val latitude: Double?,
    val longitude: Double?,
    val order: Int
)

@Entity
data class Activity(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val locationId: String,
    val title: String,
    val description: String?,
    val date: LocalDate?,
    val timeSlot: TimeSlot?, // MORNING, AFTERNOON, EVENING
    val type: ActivityType // ATTRACTION, FOOD, BOOKING, TRANSIT
)

enum class TimeSlot { MORNING, AFTERNOON, EVENING, FULL_DAY }
enum class ActivityType { ATTRACTION, FOOD, BOOKING, TRANSIT, OTHER }

@Entity
data class Booking(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val tripId: String,
    val type: BookingType, // FLIGHT, HOTEL, TRAIN, BUS, TICKET
    val confirmationNumber: String?,
    val provider: String,
    val startDate: LocalDateTime,
    val endDate: LocalDateTime?,
    val fromLocation: String?,
    val toLocation: String?,
    val price: Double?,
    val currency: String?,
    val notes: String?,
    val imageUri: String? // for saved screenshots
)

enum class BookingType { FLIGHT, HOTEL, TRAIN, BUS, TICKET, OTHER }

@Entity
data class Gap(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val tripId: String,
    val gapType: GapType,
    val fromLocationId: String,
    val toLocationId: String,
    val fromDate: LocalDate,
    val toDate: LocalDate,
    val isResolved: Boolean = false
)

enum class GapType { MISSING_TRANSIT, UNPLANNED_DAY, TIME_CONFLICT }

@Entity
data class TransitOption(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val gapId: String,
    val mode: TransitMode,
    val provider: String?,
    val duration: Int, // minutes
    val price: Double?,
    val currency: String?,
    val departureTime: String?,
    val arrivalTime: String?,
    val fetchedAt: Long
)

enum class TransitMode { FLIGHT, TRAIN, BUS, CAR, FERRY }
```

### 4.3 API Integrations (Day 1)

| Feature | API Option | Notes |
|---------|-----------|-------|
| **Attractions** | Google Places API | Free tier: 1000 req/day |
| **Weather** | OpenWeatherMap | Free tier: 1000 calls/day |
| **Flights** | Skyscanner API / Amadeus | Check free tiers |
| **Transit Options** | Rome2Rio API | Alternative: Google Directions |
| **AI Suggestions** | Claude API or Gemini | For smart recommendations |

### 4.4 No Backend Architecture
- All data stored locally in Room database
- API calls made directly from Android app
- API keys stored securely (encrypted SharedPreferences or KeyStore)
- Consider rate limiting on client side

---

## 5. Key Screens (MVP)

1. **Home Screen**
    - List of trips
    - "Create New Trip" button
    - Quick filters (upcoming/past)

2. **Create Trip Screen**
    - Trip name input
    - Destination input
    - Date type selector (Fixed/Flexible)
    - Date picker OR month + duration selector
    - Origin city input

3. **Trip Detail Screen** (Main workspace)
    - Tab 1: Timeline/Itinerary
    - Tab 2: Bookings
    - Tab 3: Research (attractions, weather)
    - Tab 4: Gaps & Suggestions
    - Floating Action Button: Add location/activity

4. **Timeline View**
    - Scrollable day-by-day view
    - Each day shows: Location, activities, transits
    - Visual indicators for gaps

5. **Gap Resolution Screen**
    - Show detected gap clearly
    - List transit options with prices/times
    - "Add to itinerary" action

6. **Research Screen**
    - Search attractions by location
    - Weather cards for planned dates
    - Flight price checker

7. **Booking Detail Screen**
    - Form to add/edit booking info
    - Option to attach image
    - Display confirmation details

---

## 6. Feature Roadmap

### Phase 1: MVP (Months 1-2)
- ✅ Trip CRUD operations
- ✅ Basic itinerary builder (locations + activities)
- ✅ Offline storage with Room
- ✅ Simple gap detection (location jumps)
- ✅ Basic UI with essential screens

### Phase 2: Intelligence (Month 3)
- ✅ API integrations (weather, attractions)
- ✅ Transit option suggestions
- ✅ Flight price integration
- ✅ AI-powered recommendations

### Phase 3: Polish (Month 4)
- ✅ Booking management with image uploads
- ✅ Enhanced gap detection (time conflicts)
- ✅ Better offline experience
- ✅ Refined UI/UX

### Phase 4: Future Enhancements
- 🔮 Budget tracking
- 🔮 Collaboration (share trips)
- 🔮 Map integration
- 🔮 Currency converter
- 🔮 Packing list generator
- 🔮 Weather alerts
- 🔮 Trip export (PDF)

---

## 7. Success Metrics (Personal Use)

Since this is a personal app initially:
- **Primary:** Does it solve YOUR planning pain points?
- **Usage:** Do you use it for every trip?
- **Gaps Found:** Does it catch things you would have missed?
- **Time Saved:** Faster than juggling multiple apps/sites?

---

## 8. Technical Challenges & Solutions

### Challenge 1: API Rate Limits
**Solution:**
- Cache API responses in Room
- Set TTL (time-to-live) for cached data
- Only refresh when user explicitly requests

### Challenge 2: Multiple API Keys Management
**Solution:**
- Use BuildConfig for API keys (not in version control)
- Encrypt keys at rest
- Consider API key rotation strategy

### Challenge 3: Offline Gap Detection
**Solution:**
- Gap detection logic runs locally
- Only fetch transit options when online
- Cache last fetched options with timestamp

### Challenge 4: Date/Time Across Timezones
**Solution:**
- Use `java.time` (java.time.LocalDate, ZonedDateTime)
- Store timezone info with locations
- Display times in local timezone of each location

---

## 9. Development Setup

### Initial Project Structure
```
app/
├── data/
│   ├── local/
│   │   ├── dao/
│   │   ├── database/
│   │   └── entities/
│   ├── remote/
│   │   ├── api/
│   │   └── dto/
│   └── repository/
├── domain/
│   ├── model/
│   └── usecase/
├── ui/
│   ├── screens/
│   ├── components/
│   └── theme/
└── utils/
```

### Dependencies (build.gradle.kts)
```kotlin
// Room
implementation("androidx.room:room-runtime:2.6.0")
implementation("androidx.room:room-ktx:2.6.0")
kapt("androidx.room:room-compiler:2.6.0")

// Retrofit
implementation("com.squareup.retrofit2:retrofit:2.9.0")
implementation("com.squareup.retrofit2:converter-gson:2.9.0")

// Coroutines
implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

// Jetpack Compose (if using)
implementation("androidx.compose.ui:ui:1.5.4")
implementation("androidx.compose.material3:material3:1.1.2")
implementation("androidx.navigation:navigation-compose:2.7.5")

// ViewModel
implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
```

---

## 10. Next Steps

1. **Set up Android project** with MVVM architecture
2. **Create Room database** with core entities (Trip, Location, Activity)
3. **Build basic UI** for trip creation and listing
4. **Implement local gap detection** logic (before APIs)
5. **Add first API integration** (start with weather - easiest)
6. **Iterate and test** with real trip planning

---

## Notes & Decisions Log

- **Architecture:** Chose MVVM for clear separation and testability
- **Database:** Room for robust offline support
- **No Backend:** All processing client-side, direct API calls
- **Date Handling:** Using java.time for modern date/time operations
- **API First:** Prioritizing API integrations from MVP for real value

---

**Document Owner:** You  
**Last Updated:** October 15, 2025  
**Status:** Foundation - Ready to Build 🚀