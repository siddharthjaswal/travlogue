# Travlogue - Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/).

---

## [Unreleased]

### Planned
- Trip detail screen with full trip information
- Itinerary builder with location and activity management
- Gap detection feature (key differentiator)
- Weather API integration
- Attractions/Places API integration

---

## [0.2.0] - 2025-01-16

### Added - Home Screen & UI Components
- **HomeScreen** - Complete home screen with Material 3 design
  - Trip listing with `TripList` component
  - Empty state with `EmptyState` component
  - FAB for creating new trips
  - Stateful/stateless component separation
- **CreateTripDialog** - Full-featured trip creation
  - Fixed dates support with Material 3 DatePicker
  - Flexible dates support (month + optional duration)
  - Form validation
  - Date type selector with FilterChips
- **TripCard** - Rich trip display component
  - Shows trip name, origin city, and dates
  - Visual badges for Fixed/Flexible date types
  - Delete functionality
  - Duration calculation for fixed date trips
- **TripList** - Lazy column for efficient trip listing
- **EmptyState** - Friendly empty state with call-to-action
- **HomeViewModel** - State management with StateFlow
  - Trip CRUD operations
  - Dialog state management
  - Loading states
- **Navigation** - Jetpack Navigation Compose setup
  - AppNavHost with route definitions
  - Navigation helpers
- **Preview System** - Comprehensive @Preview annotations
  - All components have multiple preview variants
  - Light/dark mode previews where applicable
  - Different data state previews (empty, single item, multiple items)
- **TripMockData** - Centralized mock data
  - 5 pre-defined mock trips
  - `sampleTrips` list for typical usage
  - `allMockTrips` list for comprehensive testing
  - Reusable across previews and tests

### Changed - Architecture Improvements
- Organized home feature into Feature-First Clean Architecture
  - Created `feature/home/presentation/` for UI layer
  - Created `feature/home/domain/` for business logic (ready for gap detection)
  - Created `feature/home/components/` for reusable UI components
- Extracted HomeScreen composables into separate component files
- Improved code organization and reusability

### Documentation
- Created comprehensive **ARCHITECTURE.md**
  - Full explanation of Feature-First Clean Architecture
  - Layer responsibilities (presentation, domain, components, data)
  - Data flow diagrams
  - Best practices and guidelines
  - When to add use cases
  - Migration path to multi-module

---

## [0.1.0] - 2025-01-15

### Added - Foundation
- **Project Setup**
  - Feature-First Clean Architecture
  - Gradle Kotlin DSL with Version Catalog
  - Minimum SDK: API 26 (Android 8.0)

- **Dependencies**
  - Room 2.8.2 for local database
  - Retrofit 2.11.0 + OkHttp 4.12.0 for networking
  - Hilt for dependency injection
  - Jetpack Compose with Material 3
  - Kotlin Coroutines + Flow for async operations

- **Database Layer** (`core/data/local/`)
  - **Entities** (6 core entities):
    - `Trip` - Main trip entity with Fixed/Flexible date support
    - `Location` - Trip locations with coordinates
    - `Activity` - Activities per location with time slots
    - `Booking` - Timezone-aware bookings (flights, hotels, etc.)
    - `Gap` - Detected gaps in itinerary
    - `TransitOption` - Transit suggestions for gaps
  - **DAOs** - Data Access Objects with Flow support for reactive queries
    - TripDao, LocationDao, ActivityDao, BookingDao, GapDao, TransitOptionDao
  - **Database** - TravlogueDatabase with TypeConverters
    - Enum converters for DateType, BookingType, GapType, TimeSlot, ActivityType, TransitMode
    - Foreign key relationships with CASCADE delete

- **Repository Layer** (`core/data/repository/`)
  - TripRepository - Trip CRUD operations
  - LocationRepository - Location management
  - ActivityRepository - Activity management
  - BookingRepository - Booking management
  - GapRepository - Gap detection data
  - TransitOptionRepository - Transit options
  - All repositories use Flow for reactive data

- **Dependency Injection** (`di/`)
  - DatabaseModule - Provides Room database and all DAOs
  - NetworkModule - Provides Retrofit and OkHttp with logging interceptor
  - Singleton scoped dependencies

- **Utilities** (`core/common/`)
  - **DateTimeUtils** - Comprehensive date/time helpers (30+ methods)
    - ISO date string conversions
    - ISO 8601 datetime with timezone support
    - Display formatting for dates and bookings
    - Date arithmetic (days between, add days)
    - Validation helpers
  - **BookingExamples** - Usage examples for timezone-aware bookings

- **Design System** (`core/design/`)
  - Material 3 color scheme
  - Typography definitions
  - Theme configuration

### Technical Decisions
- **Date/Time Strategy**:
  - Trip dates: ISO strings ("2025-11-15") for calendar dates
  - Booking datetimes: ISO 8601 with timezone ("2025-11-15T14:30:00+01:00")
  - System timestamps: Long (milliseconds) for created/updated fields
- **Architecture**: Feature-First Clean Architecture for scalability
- **DI**: Hilt for compile-time verified dependency injection
- **Reactive**: Flow for reactive data streams, StateFlow for UI state
- **Build**: Gradle Version Catalog for centralized dependency management

---

## Project Milestones

### Phase 1: MVP Foundation (Current)
**Goal:** Basic trip planning functionality
- ✅ Architecture and database setup
- ✅ Home screen with trip management
- 🚧 Trip detail screen (next)
- ⏳ Itinerary builder
- ⏳ Gap detection

### Phase 2: Intelligence
**Goal:** Smart suggestions and integrations
- ⏳ API integrations (Weather, Places)
- ⏳ Transit option suggestions
- ⏳ Flight price integration
- ⏳ AI-powered recommendations

### Phase 3: Polish
**Goal:** Enhanced user experience
- ⏳ Booking management with images
- ⏳ Enhanced gap detection (time conflicts)
- ⏳ Offline optimization
- ⏳ UI/UX refinements

### Phase 4: Future
**Goal:** Advanced features
- 🔮 Budget tracking
- 🔮 Trip collaboration
- 🔮 Map integration
- 🔮 Currency converter
- 🔮 Packing list generator
- 🔮 Weather alerts
- 🔮 Trip export (PDF)

---

## Development Notes

### Code Quality
- All entities implemented with proper Room annotations
- All repositories follow consistent patterns
- Type-safe database queries with Flow
- Comprehensive error handling in ViewModels
- Stateless UI components for reusability

### Testing Support
- TripMockData provides realistic test data
- All components have @Preview annotations
- Preview-driven development workflow
- Easy to test with mock repositories

### Documentation
- Inline code documentation for complex logic
- Architecture documentation in ARCHITECTURE.md
- API usage examples in BookingExamples.kt
- PRD updated with implementation status

---

## Version History

- **0.2.0** - Home Screen with full UI components
- **0.1.0** - Foundation with database and architecture
- **0.0.1** - Initial project setup

---

**Maintained by:** Sid
**Project Status:** Active Development
**Next Release:** 0.3.0 (Trip Details)
