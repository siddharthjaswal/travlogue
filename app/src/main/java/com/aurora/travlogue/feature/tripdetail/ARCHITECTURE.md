# Trip Detail Feature - Architecture

## Package Structure

```
feature/tripdetail/
├── presentation/
│   ├── TripDetailScreen.kt          # Main screen composable
│   ├── TripDetailViewModel.kt       # Business logic & state
│   ├── TripDetailUiState.kt         # UI state & events
│   └── TripDetailTab.kt             # Tab enum
│
├── components/
│   ├── header/
│   │   ├── TripHeaderSection.kt     # Trip overview header
│   │   └── TripStatistics.kt        # Stats chips (locations, activities, etc.)
│   │
│   ├── tabs/
│   │   ├── TimelineTab.kt           # Timeline view
│   │   ├── LocationsTab.kt          # Locations list
│   │   ├── BookingsTab.kt           # Bookings list
│   │   └── OverviewTab.kt           # Trip notes & info
│   │
│   ├── timeline/
│   │   ├── DayCard.kt               # Day container
│   │   ├── ActivityItem.kt          # Activity card
│   │   ├── TimeSlotSection.kt       # Morning/Afternoon/Evening sections
│   │   └── DayNotes.kt              # Notes for specific day
│   │
│   ├── location/
│   │   └── LocationCard.kt          # Location list item
│   │
│   ├── booking/
│   │   ├── BookingCard.kt           # Booking list item
│   │   └── BookingTypeIcon.kt       # Icon for booking type
│   │
│   └── dialogs/
│       ├── AddActivityDialog.kt     # Add/Edit activity
│       └── AddBookingDialog.kt      # Add/Edit booking
│
├── domain/
│   └── models/
│       ├── DaySchedule.kt           # Day with activities
│       └── TripDetailData.kt        # Aggregated trip data
│
└── TRIP_DETAIL_PRD.md               # This file
```

## Data Flow

```
┌─────────────────────────────────────────────────────┐
│                  TripDetailScreen                   │
│                                                     │
│  ┌──────────────────────────────────────────────┐  │
│  │            TripDetailViewModel               │  │
│  │                                              │  │
│  │  State:                                      │  │
│  │  - trip: Trip                                │  │
│  │  - locations: List<Location>                 │  │
│  │  - activitiesByDay: Map<String, List<Act>>   │  │
│  │  - bookings: List<Booking>                   │  │
│  │  - selectedTab: TripDetailTab                │  │
│  │  - expandedDays: Set<String>                 │  │
│  └──────────────────────────────────────────────┘  │
│                        ↓                            │
│  ┌──────────────────────────────────────────────┐  │
│  │              Tab Content                     │  │
│  │  ┌────────┬────────┬────────┬────────────┐  │  │
│  │  │Timeline│Location│Bookings│  Overview  │  │  │
│  │  └────────┴────────┴────────┴────────────┘  │  │
│  └──────────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────┘
                        ↓
┌─────────────────────────────────────────────────────┐
│                TripRepository                       │
│                                                     │
│  getTripWithDetails(tripId) → Flow<TripDetailData>  │
│  getActivitiesForDay(tripId, date) → List<Activity>│
│  getAllBookings(tripId) → Flow<List<Booking>>      │
└─────────────────────────────────────────────────────┘
                        ↓
┌─────────────────────────────────────────────────────┐
│                  Room Database                      │
│                                                     │
│  trips, locations, activities, bookings tables      │
└─────────────────────────────────────────────────────┘
```

## Component Hierarchy

```
TripDetailScreen
├── Scaffold
│   ├── TopAppBar
│   │   ├── Back Button
│   │   ├── Trip Name
│   │   └── More Options Menu
│   │
│   └── Content
│       ├── TripHeaderSection
│       │   ├── Cover Image Placeholder
│       │   ├── Origin City
│       │   ├── Date Range Display
│       │   └── TripStatistics
│       │       ├── Locations Count
│       │       ├── Activities Count
│       │       ├── Bookings Count
│       │       └── Notes Count
│       │
│       ├── TabRow
│       │   ├── Timeline Tab
│       │   ├── Locations Tab
│       │   ├── Bookings Tab
│       │   └── Overview Tab
│       │
│       └── TabContent (based on selectedTab)
│           │
│           ├── TimelineTab
│           │   └── LazyColumn
│           │       └── DayCard (for each day)
│           │           ├── Day Header
│           │           ├── Location Badge
│           │           ├── Expand/Collapse Icon
│           │           └── When Expanded:
│           │               ├── TimeSlotSection (Morning)
│           │               │   └── ActivityItem[]
│           │               ├── TimeSlotSection (Afternoon)
│           │               │   └── ActivityItem[]
│           │               ├── TimeSlotSection (Evening)
│           │               │   └── ActivityItem[]
│           │               ├── DayNotes
│           │               └── Action Buttons
│           │                   ├── Add Activity
│           │                   └── Add Note
│           │
│           ├── LocationsTab
│           │   └── LazyColumn
│           │       └── LocationCard (for each location)
│           │           ├── Order Number
│           │           ├── Location Name & Country
│           │           ├── Date Range
│           │           ├── Activity Count
│           │           └── Drag Handle
│           │
│           ├── BookingsTab
│           │   └── LazyColumn
│           │       └── BookingCard (for each booking)
│           │           ├── BookingTypeIcon
│           │           ├── Provider & Number
│           │           ├── Date & Time
│           │           ├── From → To
│           │           ├── Confirmation
│           │           ├── Price
│           │           └── Action Buttons
│           │
│           └── OverviewTab
│               ├── TripStatisticsCard
│               ├── TripNotesCard
│               └── FutureEnhancementCards
```

## State Management

### TripDetailUiState
```kotlin
data class TripDetailUiState(
    val trip: Trip? = null,
    val locations: List<Location> = emptyList(),
    val daySchedules: List<DaySchedule> = emptyList(),
    val bookings: List<Booking> = emptyList(),
    val selectedTab: TripDetailTab = TripDetailTab.TIMELINE,
    val expandedDays: Set<String> = emptySet(),
    val isLoading: Boolean = false,
    val error: String? = null
)

data class DaySchedule(
    val date: String,
    val dayNumber: Int,
    val location: Location?,
    val activitiesByTimeSlot: Map<TimeSlot, List<Activity>>,
    val dayNotes: String? = null
)

enum class TripDetailTab {
    TIMELINE,
    LOCATIONS,
    BOOKINGS,
    OVERVIEW
}
```

### UI Events
```kotlin
sealed interface TripDetailUiEvent {
    data object NavigateBack : TripDetailUiEvent
    data class ShowError(val message: String) : TripDetailUiEvent
    data class NavigateToEditTrip(val tripId: String) : TripDetailUiEvent
}
```

### ViewModel Actions
```kotlin
class TripDetailViewModel {
    // Tab selection
    fun onTabSelected(tab: TripDetailTab)

    // Day expansion
    fun onDayClicked(date: String)
    fun expandDay(date: String)
    fun collapseDay(date: String)

    // Activity management
    fun onAddActivity(date: String, timeSlot: TimeSlot)
    fun onEditActivity(activity: Activity)
    fun onDeleteActivity(activityId: String)

    // Booking management
    fun onAddBooking()
    fun onEditBooking(booking: Booking)
    fun onDeleteBooking(bookingId: String)

    // Navigation
    fun onBackPressed()
    fun onEditTrip()
}
```

## Implementation Phases

### Phase 1: Basic Structure (MVP)
1. ✅ Create package structure
2. Create TripDetailUiState & ViewModel
3. Create TripDetailScreen with tab navigation
4. Implement TripHeaderSection
5. Implement TimelineTab with DayCard (read-only)
6. Wire up navigation from Home

### Phase 2: Core Features
1. Implement LocationsTab
2. Implement BookingsTab
3. Implement OverviewTab
4. Add expand/collapse for day cards
5. Display activities grouped by time slot

### Phase 3: Add/Edit Functionality
1. Create AddActivityDialog
2. Create AddBookingDialog
3. Implement create/update/delete operations
4. Add day notes functionality

### Phase 4: Enhanced UX
1. Add drag-to-reorder for locations
2. Add filtering and search
3. Add quick actions menu
4. Implement loading states and error handling

## Database Queries

### Required Repository Methods
```kotlin
interface TripRepository {
    // Get trip with all related data
    fun getTripWithDetails(tripId: String): Flow<TripDetailData>

    // Get activities for specific day
    fun getActivitiesForDay(tripId: String, date: String): Flow<List<Activity>>

    // Get all activities grouped by location
    fun getActivitiesByLocation(tripId: String): Flow<Map<Location, List<Activity>>>

    // Get bookings
    fun getBookingsForTrip(tripId: String): Flow<List<Booking>>

    // CRUD operations
    suspend fun addActivity(activity: Activity)
    suspend fun updateActivity(activity: Activity)
    suspend fun deleteActivity(activityId: String)

    suspend fun addBooking(booking: Booking)
    suspend fun updateBooking(booking: Booking)
    suspend fun deleteBooking(bookingId: String)
}

data class TripDetailData(
    val trip: Trip,
    val locations: List<Location>,
    val activities: List<Activity>,
    val bookings: List<Booking>
)
```

## Design Tokens

### Colors (Material 3)
- Primary: Trip actions and highlights
- Secondary: Stats and metadata
- Surface: Card backgrounds
- Outline: Borders and dividers

### Typography
- displaySmall: Trip name
- titleLarge: Tab headers
- titleMedium: Day headers, section titles
- bodyLarge: Activity titles
- bodyMedium: Activity descriptions, booking details
- labelSmall: Time slots, metadata

### Spacing
- xxs: 4.dp (icon spacing)
- xs: 8.dp (item padding)
- sm: 12.dp (card inner padding)
- md: 16.dp (standard padding)
- lg: 20.dp (section spacing)
- xl: 24.dp (major sections)

### Icons
- Timeline: schedule, calendar_today
- Locations: place, map
- Bookings: confirmation_number, flight, hotel
- Activities: category icons based on type
- Actions: add, edit, delete, more_vert

## Testing Strategy

### Unit Tests
- ViewModel state transformations
- Day schedule generation logic
- Date formatting utilities
- Activity grouping by time slot

### Integration Tests
- Repository data aggregation
- Database queries with foreign keys
- Flow transformations

### UI Tests
- Tab navigation
- Day expand/collapse
- Add/edit dialogs
- Delete confirmations

## Performance Considerations

1. **Lazy Loading**: Load activities only when day is expanded
2. **Pagination**: For long trips (20+ days)
3. **Caching**: Cache trip details for offline access
4. **Debouncing**: Debounce expand/collapse animations
5. **Incremental Loading**: Load visible days first, then off-screen

## Accessibility

- Semantic labels for all interactive elements
- Proper heading hierarchy
- Screen reader announcements for day expansion
- Keyboard navigation support
- Minimum touch target sizes (48dp)
- High contrast support for time/date displays
