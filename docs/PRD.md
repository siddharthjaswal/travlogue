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
- **Architecture:** Feature-First Clean Architecture with MVVM pattern
- **Database:** Room 2.8.2 (SQLite) - for offline storage
- **API Calls:** Retrofit 2.11.0 + OkHttp 4.12.0
- **Async Operations:** Kotlin Coroutines + Flow + StateFlow
- **UI:** Jetpack Compose with Material 3 Design
- **Navigation:** Jetpack Navigation Compose
- **Dependency Injection:** Hilt (Dagger)
- **Build System:** Gradle with Kotlin DSL + Version Catalog

### 4.2 Data Models (Core Entities)

**Implementation Status:** ✅ All entities implemented in `core/data/local/entities/`

```kotlin
@Entity(tableName = "trips")
data class Trip(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val name: String,
    val originCity: String,
    val dateType: DateType, // FIXED or FLEXIBLE
    val startDate: String?, // ISO format: yyyy-MM-dd
    val endDate: String?, // ISO format: yyyy-MM-dd
    val flexibleMonth: String?, // "November 2025"
    val flexibleDuration: Int?, // days
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)

enum class DateType { FIXED, FLEXIBLE }

// Mock data available in TripMockData object for previews and testing

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

@Entity(tableName = "bookings")
data class Booking(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val tripId: String,
    val type: BookingType, // FLIGHT, HOTEL, TRAIN, BUS, TICKET
    val confirmationNumber: String?,
    val provider: String,
    val startDateTime: String, // ISO 8601 with timezone: "2025-11-15T14:30:00+01:00"
    val endDateTime: String?,
    val timezone: String, // IANA timezone: "Europe/Madrid"
    val fromLocation: String?,
    val toLocation: String?,
    val price: Double?,
    val currency: String?,
    val notes: String?,
    val imageUri: String?, // for saved screenshots
    val createdAt: Long = System.currentTimeMillis()
)

enum class BookingType { FLIGHT, HOTEL, TRAIN, BUS, TICKET, OTHER }

// Note: Timezone-aware bookings with comprehensive DateTimeUtils support

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

### Phase 1: MVP (Months 1-2) - **IN PROGRESS** 🚧
- ✅ **COMPLETED:** Project architecture setup (Feature-First Clean Architecture)
- ✅ **COMPLETED:** Room database with all core entities (Trip, Location, Activity, Booking, Gap, TransitOption)
- ✅ **COMPLETED:** Hilt dependency injection setup
- ✅ **COMPLETED:** Repository pattern implementation
- ✅ **COMPLETED:** Home Screen with trip listing
- ✅ **COMPLETED:** Create Trip functionality (Fixed & Flexible dates)
- ✅ **COMPLETED:** Material 3 design system
- ✅ **COMPLETED:** Comprehensive preview system for all UI components
- ✅ **COMPLETED:** Timezone-aware booking system
- ✅ **COMPLETED:** Date/time utilities (DateTimeUtils)
- 🚧 **IN PROGRESS:** Trip detail screen
- 🚧 **IN PROGRESS:** Itinerary builder (locations + activities)
- ⏳ **TODO:** Basic gap detection (location jumps)
- ⏳ **TODO:** Offline storage optimization

### Phase 2: Intelligence (Month 3)
- ⏳ API integrations (weather, attractions)
- ⏳ Transit option suggestions
- ⏳ Flight price integration
- ⏳ AI-powered recommendations

### Phase 3: Polish (Month 4)
- ⏳ Booking management with image uploads
- ⏳ Enhanced gap detection (time conflicts)
- ⏳ Better offline experience
- ⏳ Refined UI/UX

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

### Implemented Project Structure ✅

```
app/src/main/java/com/aurora/travlogue/
│
├── core/                           # Shared across all features
│   ├── data/                      # Data layer
│   │   ├── local/                 # Room database
│   │   │   ├── entities/         # Trip, Location, Activity, Booking, Gap, TransitOption
│   │   │   ├── dao/              # DAOs with Flow support
│   │   │   └── database/         # TravlogueDatabase + TypeConverters
│   │   └── repository/           # Repository implementations (6 repositories)
│   ├── domain/                    # Shared business logic (ready for future use)
│   ├── common/                    # Utilities
│   │   ├── DateTimeUtils.kt      # 30+ date/time helper methods
│   │   └── BookingExamples.kt    # Usage examples
│   └── design/                    # UI theme and design system
│       ├── Color.kt
│       ├── Theme.kt
│       └── Type.kt
│
├── feature/                       # Feature modules
│   └── home/                      # Home feature ✅ IMPLEMENTED
│       ├── presentation/          # UI layer
│       │   ├── HomeScreen.kt     # Main screen with previews
│       │   ├── HomeViewModel.kt  # State management
│       │   └── HomeUiState.kt    # UI state models (reference)
│       ├── domain/                # Feature-specific business logic (ready)
│       │   ├── usecase/          # For gap detection (future)
│       │   └── model/            # Domain models (future)
│       └── components/            # Reusable UI components
│           ├── TripCard.kt       # Trip display card
│           ├── TripList.kt       # Trip listing
│           ├── EmptyState.kt     # Empty state UI
│           └── CreateTripDialog.kt # Trip creation dialog
│
├── di/                            # Dependency injection
│   ├── DatabaseModule.kt         # Room + DAOs
│   └── NetworkModule.kt          # Retrofit + OkHttp
│
└── navigation/                    # App navigation
    ├── AppNavHost.kt
    └── Screen.kt
```

**Key Features:**
- ✅ Feature-First Clean Architecture
- ✅ MVVM with StateFlow
- ✅ Complete separation of concerns
- ✅ All components have @Preview annotations
- ✅ TripMockData for testing and previews
- ✅ Comprehensive DateTimeUtils
- ✅ Timezone-aware booking system

### Implemented Dependencies ✅

**Using Gradle Version Catalog** (`gradle/libs.versions.toml`)

```toml
[versions]
room = "2.8.2"
retrofit = "2.11.0"
okhttp = "4.12.0"
hilt = "2.51.1"
hiltNavigationCompose = "1.2.0"

[libraries]
# Room (Local Database)
androidx-room-runtime = { group = "androidx.room", name = "room-runtime", version.ref = "room" }
androidx-room-ktx = { group = "androidx.room", name = "room-ktx", version.ref = "room" }
androidx-room-compiler = { group = "androidx.room", name = "room-compiler", version.ref = "room" }

# Retrofit (Networking)
retrofit = { group = "com.squareup.retrofit2", name = "retrofit", version.ref = "retrofit" }
retrofit-gson = { group = "com.squareup.retrofit2", name = "converter-gson", version.ref = "retrofit" }
okhttp = { group = "com.squareup.okhttp3", name = "okhttp", version.ref = "okhttp" }
okhttp-logging = { group = "com.squareup.okhttp3", name = "logging-interceptor", version.ref = "okhttp" }

# Hilt (Dependency Injection)
hilt-android = { group = "com.google.dagger", name = "hilt-android", version.ref = "hilt" }
hilt-compiler = { group = "com.google.dagger", name = "hilt-compiler", version.ref = "hilt" }
androidx-hilt-navigation-compose = { group = "androidx.hilt", name = "hilt-navigation-compose", version.ref = "hiltNavigationCompose" }
```

**Build Configuration:**
```kotlin
// app/build.gradle.kts
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}

dependencies {
    // Room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)

    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose)
}
```

---

## 10. Current Status & Next Steps

### ✅ Completed
1. ✅ Set up Android project with Feature-First Clean Architecture
2. ✅ Created Room database with all core entities (Trip, Location, Activity, Booking, Gap, TransitOption)
3. ✅ Built Home UI with trip creation and listing
4. ✅ Implemented comprehensive date/time utilities
5. ✅ Set up dependency injection with Hilt
6. ✅ Created repository pattern for all entities
7. ✅ Implemented preview system for all components
8. ✅ Added TripMockData for testing and previews

### 🚧 In Progress
1. Trip detail screen design and implementation
2. Location and activity management

### ⏳ Next Steps
1. **Build Trip Detail Screen** - Show trip information and navigate to plan
2. **Implement Itinerary Builder** - Add locations and activities to trips
3. **Implement local gap detection** logic
4. **Add first API integration** (weather or attractions)
5. **Iterate and test** with real trip planning

---

## Notes & Decisions Log

### Architecture Decisions
- **Architecture Pattern:** Feature-First Clean Architecture with MVVM for scalability and maintainability
- **Database:** Room 2.8.2 for robust offline-first support with Flow for reactive queries
- **No Backend:** All processing client-side, direct API calls to keep it simple
- **Date Handling:** Hybrid approach using java.time:
  - Trip dates: ISO strings ("2025-11-15")
  - Booking date-times: ISO 8601 with timezone ("2025-11-15T14:30:00+01:00")
  - System timestamps: Long (milliseconds)
- **Dependency Injection:** Hilt for type-safe, compile-time verified DI
- **Build System:** Gradle Version Catalog for centralized dependency management

### UI/UX Decisions
- **Design System:** Material 3 for modern Android look and feel
- **Preview Strategy:** All components have @Preview annotations for rapid development
- **Mock Data:** Centralized TripMockData object for consistent testing and previews
- **Component Structure:** Separated into presentation/domain/components for clear organization

### Development Workflow
- **Module Strategy:** Starting single-module, but architecture is "module-ready"
- **State Management:** StateFlow for UI state, SharedFlow for one-time events
- **Repository Pattern:** Always access data through repositories, never directly from DAOs
- **Testing Approach:** Mock data available, preview-driven development

### Implementation Notes
- All 6 core entities implemented with proper relationships and foreign keys
- DateTimeUtils provides 30+ helper methods for date/time operations
- HomeScreen fully functional with create, list, and delete operations
- All UI components are stateless and reusable
- Comprehensive documentation in ARCHITECTURE.md

---

**Document Owner:** Sid
**Last Updated:** January 2025
**Status:** Phase 1 MVP - In Active Development 🚀