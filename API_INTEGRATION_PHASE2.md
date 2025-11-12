# API Integration Phase 2: Repository Layer & Offline-First Sync ✅

## Overview

Phase 2 implements the repository layer that coordinates between local cache (SQLDelight) and remote API (Logbook backend), establishing an offline-first architecture for seamless data synchronization.

**Status**: ✅ Complete
**Branch**: `kmp-migration`
**Backend**: Production API at https://api.travlogue.in
**Previous Phase**: [API_INTEGRATION_PHASE1.md](./API_INTEGRATION_PHASE1.md)

---

## What Was Implemented

### 1. DTO ↔ Domain Model Mappers ✅

Created bidirectional mappers for seamless conversion between backend DTOs and local domain models.

**Location**: `shared/src/commonMain/kotlin/com/aurora/travlogue/core/data/remote/mapper/`

#### TripMapper.kt
Converts between Trip DTOs and Domain models:

```kotlin
// DTO → Domain Model
fun TripResponseDto.toDomainModel(): Trip
fun TripListResponseDto.toDomainModel(): Trip

// Domain Model → DTO
fun Trip.toCreateDto(...): TripCreateDto
fun Trip.toUpdateDto(...): TripUpdateDto

// Extended model for caching full backend data
data class TripExtended(
    val trip: Trip,
    val description: String?,
    val countriesVisited: List<String>,
    // ... all backend fields
)
```

**Key Features**:
- Automatic date type detection (FIXED vs FLEXIBLE)
- Trip status determination based on dates
- Extended model for caching additional backend fields
- ISO 8601 date/datetime parsing

#### ActivityMapper.kt
Converts between Activity DTOs and Domain models:

```kotlin
// DTO → Domain Model
fun ActivityResponseDto.toDomainModel(locationId: String): Activity
fun ActivityListResponseDto.toDomainModel(locationId: String): Activity

// Domain Model → DTO
fun Activity.toCreateDto(...): ActivityCreateDto
fun Activity.toUpdateDto(...): ActivityUpdateDto
```

**Key Features**:
- Activity type mapping (11 backend types → 6 domain types)
- TimeSlot determination from time strings
- Duration calculation between start/end times
- Graceful handling of missing fields

#### BookingMapper.kt
Converts between Booking DTOs and Domain models:

```kotlin
// DTO → Domain Model
fun BookingResponseDto.toDomainModel(): Booking
fun BookingListResponseDto.toDomainModel(): Booking

// Domain Model → DTO
fun Booking.toCreateDto(...): BookingCreateDto
fun Booking.toUpdateDto(): BookingUpdateDto
```

**Key Features**:
- Booking type mapping (9 backend types → 6 domain types)
- DateTime splitting/combining
- Well-aligned models (easiest mapping!)

### 2. TripSyncRepository ✅

**File**: `shared/src/commonMain/kotlin/com/aurora/travlogue/core/data/repository/TripSyncRepository.kt`

Implements offline-first pattern by coordinating between local cache and remote API.

**Architecture**:
```
┌─────────────────────────────────────────────┐
│         TripSyncRepository                  │
│  ┌──────────────┐      ┌─────────────────┐ │
│  │   Local      │      │     Remote      │ │
│  │ (SQLDelight) │      │ (LogbookAPI)    │ │
│  └──────┬───────┘      └────────┬────────┘ │
│         │                       │          │
│         └───────────────────────┘          │
│            Sync Coordination                │
└─────────────────────────────────────────────┘
```

**Key Methods**:

```kotlin
class TripSyncRepository {
    // Read with auto-sync
    fun getAllTrips(forceRefresh: Boolean): Flow<List<Trip>>
    fun getTripById(tripId: String): Flow<Trip?>

    // Write with offline-first
    suspend fun createTrip(trip: Trip): Result<Trip>
    suspend fun updateTrip(trip: Trip): Result<Trip>
    suspend fun deleteTrip(tripId: String): Result<Unit>

    // Sync operations
    suspend fun syncTripsFromRemote(): Result<Unit>
    suspend fun searchTrips(query: String): Result<List<Trip>>
}
```

**Offline-First Strategy**:

1. **Reads** (GET operations):
   - Return local data immediately via Flow
   - Trigger background sync from remote
   - Update local cache when sync completes
   - UI automatically updates via Flow

2. **Writes** (CREATE/UPDATE/DELETE):
   - Save to local database first
   - Return success immediately (optimistic UI)
   - Sync to remote in background
   - Handle failures gracefully

3. **Conflict Resolution**:
   - Last-write-wins strategy (for now)
   - Backend timestamps used for conflict detection
   - Future: User-selectable conflict resolution

### 3. SyncService ✅

**File**: `shared/src/commonMain/kotlin/com/aurora/travlogue/core/domain/service/SyncService.kt`

Centralized service for managing background synchronization.

**Features**:

```kotlin
class SyncService {
    val syncState: StateFlow<SyncState>
    val lastSyncTime: StateFlow<Long?>

    // Start monitoring auth state for sync opportunities
    fun startMonitoring(scope: CoroutineScope)

    // Manual sync trigger
    suspend fun syncAll(): Result<Unit>

    // Check if sync is needed
    fun needsSync(maxAgeMillis: Long): Boolean

    fun resetSyncState()
}
```

**Sync States**:
```kotlin
sealed class SyncState {
    object Idle
    data class Syncing(progress: Float, message: String)
    data class Success(message: String)
    data class Error(message: String)
}
```

**Automatic Sync Triggers**:
- ✅ When user authenticates
- ✅ On app start (if authenticated)
- ✅ Manual pull-to-refresh
- 🚧 When app comes online (TODO: NetworkMonitor)
- 🚧 Periodic background sync (TODO: WorkManager/BackgroundTasks)

**Conflict Resolution Types** (defined, not yet implemented):
```kotlin
sealed class ConflictResolution {
    object KeepLocal
    object KeepRemote
    object Merge
    data class UserDecision(val resolution: ConflictResolution)
}

data class SyncConflict(
    val id: String,
    val type: ConflictType,
    val localData: Any,
    val remoteData: Any,
    val localTimestamp: Long,
    val remoteTimestamp: Long
)
```

### 4. Updated Dependency Injection ✅

Updated Koin modules to provide new sync layer:

**SharedModule.kt**:
```kotlin
// Repositories
single { TripRepository(get()) } // Local repository
single { TripSyncRepository(get(), get(), get()) } // Sync repository

// Domain Services
single { SyncService(get(), get()) } // Sync service
```

---

## Architecture Overview

### Data Flow

**Read Operation (Offline-First)**:
```
User Request
    ↓
TripSyncRepository.getAllTrips()
    ├─→ Return local data immediately (Flow)
    │   ↓
    │   UI displays data
    │
    └─→ Trigger background sync
        ↓
        Fetch from Logbook API
        ↓
        Update local SQLDelight DB
        ↓
        Flow emits updated data
        ↓
        UI auto-updates
```

**Write Operation (Optimistic UI)**:
```
User Action (Create/Update/Delete)
    ↓
TripSyncRepository.createTrip(trip)
    ├─→ Save to local DB first
    │   ↓
    │   Return success immediately
    │   ↓
    │   UI updates optimistically
    │
    └─→ Sync to remote API
        ├─→ Success: Update local with backend ID
        └─→ Failure: Mark for retry, show error
```

### Repository Layering

```
┌────────────────────────────────────────────────────┐
│              UI Layer (ViewModels)                 │
└────────────────────┬───────────────────────────────┘
                     │
┌────────────────────▼───────────────────────────────┐
│           TripSyncRepository (New!)                │
│        Coordinates local + remote data             │
│                                                     │
│  ┌──────────────────┐      ┌──────────────────┐  │
│  │  TripRepository  │      │ LogbookApiClient │  │
│  │   (Local Only)   │      │  (Remote Only)   │  │
│  └────────┬─────────┘      └────────┬─────────┘  │
└───────────┼──────────────────────────┼────────────┘
            │                          │
┌───────────▼──────────┐  ┌───────────▼────────────┐
│  SQLDelight Database │  │   Production Backend   │
│  (Offline Cache)     │  │  api.travlogue.in      │
└──────────────────────┘  └────────────────────────┘
```

---

## Usage Examples

### 1. Basic Trip Operations with Auto-Sync

```kotlin
class HomeViewModel(
    private val tripSyncRepository: TripSyncRepository,
    private val syncService: SyncService
) : ViewModel() {

    // Get all trips with automatic sync
    val trips: StateFlow<List<Trip>> = tripSyncRepository
        .getAllTrips(forceRefresh = false)
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    // Observe sync state
    val syncState = syncService.syncState
        .stateIn(viewModelScope, SharingStarted.Lazily, SyncState.Idle)

    // Manual refresh
    fun refreshTrips() {
        viewModelScope.launch {
            syncService.syncAll()
        }
    }

    // Create new trip (offline-first)
    fun createTrip(name: String, startDate: String) {
        viewModelScope.launch {
            val trip = Trip(
                name = name,
                originCity = "New York",
                dateType = DateType.FIXED,
                startDate = startDate,
                endDate = null
            )

            tripSyncRepository.createTrip(trip)
                .onSuccess {
                    // Trip created successfully (locally and/or remotely)
                    println("Trip created: ${it.id}")
                }
                .onFailure { error ->
                    // Show error to user
                    println("Failed to create trip: ${error.message}")
                }
        }
    }
}
```

### 2. Monitoring Sync State in UI

```kotlin
@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val trips by viewModel.trips.collectAsState()
    val syncState by viewModel.syncState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Trips") },
                actions = {
                    // Show sync indicator
                    when (syncState) {
                        is SyncState.Syncing -> {
                            CircularProgressIndicator(
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        is SyncState.Error -> {
                            IconButton(onClick = { viewModel.refreshTrips() }) {
                                Icon(Icons.Default.Warning, "Sync error")
                            }
                        }
                        else -> {}
                    }
                }
            )
        }
    ) { padding ->
        // Pull-to-refresh
        SwipeRefresh(
            state = rememberSwipeRefreshState(
                isRefreshing = syncState is SyncState.Syncing
            ),
            onRefresh = { viewModel.refreshTrips() }
        ) {
            TripList(
                trips = trips,
                modifier = Modifier.padding(padding)
            )
        }
    }
}
```

### 3. Start Sync Monitoring on App Launch

```kotlin
class TravlogueApp : Application() {
    override fun onCreate() {
        super.onCreate()

        // Initialize Koin
        startKoin {
            modules(sharedModule, platformModule)
        }

        // Get SyncService and start monitoring
        val syncService: SyncService by inject()
        val scope = CoroutineScope(Dispatchers.Default + SupervisorJob())

        // Start monitoring for sync opportunities
        syncService.startMonitoring(scope)

        // Trigger initial sync if authenticated
        scope.launch {
            if (syncService.needsSync()) {
                syncService.syncAll()
            }
        }
    }
}
```

### 4. Handling Offline/Online Scenarios

```kotlin
// When user creates a trip while offline:
viewModelScope.launch {
    val trip = Trip(/* ... */)

    tripSyncRepository.createTrip(trip)
        .onSuccess { savedTrip ->
            // Trip saved locally, will sync when online
            _uiState.value = UiState.Success("Trip saved offline")
        }
        .onFailure { error ->
            // Even local save failed
            _uiState.value = UiState.Error(error.message)
        }
}

// When app comes back online:
// SyncService automatically detects auth state and triggers sync
// No manual intervention needed!
```

---

## Data Mapping Details

### Field Mapping: Local vs Backend

| Local Model | Backend Model | Mapping Strategy |
|-------------|---------------|------------------|
| **Trip** | **TripResponseDto** | |
| id: String (UUID) | id: Int | Convert Int → String for local storage |
| name | name | Direct mapping |
| originCity | originCity? | Fallback to empty string |
| dateType (enum) | - | Derived from startDate/endDate presence |
| startDate, endDate | startDate, endDate | Direct mapping |
| flexibleMonth, flexibleDuration | - | Not in backend (yet) |
| createdAt, updatedAt (Long) | createdAt, updatedAt (ISO 8601) | Parse ISO → epoch millis |
| - | description, destinations, countries_visited, etc. | Stored in TripExtended |

| **Activity** | **ActivityResponseDto** | |
| id: String | id: Int | Convert Int → String |
| title | name | Direct mapping |
| description | notes | Fallback to empty string |
| timeSlot (enum) | time | Derive from time string (HH:mm) |
| type (6 types) | activityType (11 types) | Map to closest domain type |
| startTime, endTime | time, duration | Calculate end from start + duration |

| **Booking** | **BookingResponseDto** | |
| id: String | id: Int | Convert Int → String |
| type (6 types) | bookingType (9 types) | Map appropriately |
| provider | provider | Direct mapping |
| startDateTime (ISO) | bookingDate + bookingTime | Combine date + time |
| fromLocation, toLocation | location | Backend has single location field |

### Type Mapping Examples

**Activity Types**:
```kotlin
Backend → Local:
SIGHTSEEING → SIGHTSEEING
DINING → DINING
ENTERTAINMENT → ENTERTAINMENT
SHOPPING → SHOPPING
RELAXATION → RELAXATION
ADVENTURE, CULTURAL, TRANSPORTATION, etc. → OTHER

Local → Backend:
SIGHTSEEING → SIGHTSEEING
DINING → DINING
ENTERTAINMENT → ENTERTAINMENT
SHOPPING → SHOPPING
RELAXATION → RELAXATION
OTHER → OTHER
```

**Booking Types**:
```kotlin
Backend → Local:
ACCOMMODATION → HOTEL
TRANSPORTATION → OTHER (generic)
TOUR → TICKET
RESTAURANT → OTHER
ENTERTAINMENT, ATTRACTION → TICKET
SERVICE, RENTAL, OTHER → OTHER

Local → Backend:
FLIGHT, TRAIN, BUS → TRANSPORTATION
HOTEL → ACCOMMODATION
TICKET → TOUR
OTHER → OTHER
```

---

## Known Limitations & TODOs

### Current Limitations

1. **ID Mapping** ⚠️
   - Local uses String UUIDs, backend uses Int IDs
   - Current approach: Convert Int → String for local storage
   - **Issue**: Can't reliably convert UUID back to Int
   - **Impact**: Trips created offline can't be synced to backend until ID mapping table is added
   - **TODO**: Add `trip_id_mapping` table (localId ↔ backendId)

2. **Partial Sync** ⚠️
   - Only Trip sync implemented
   - Activities, Bookings, TripDays not yet synchronized
   - **TODO**: Implement sync for all entities

3. **Conflict Resolution** ⚠️
   - Currently uses last-write-wins strategy
   - No user interaction for conflicts
   - **TODO**: Implement conflict detection and user-selectable resolution

4. **Network Monitoring** ⚠️
   - No automatic sync when device comes online
   - Requires manual refresh or app restart
   - **TODO**: Add NetworkMonitor to detect connectivity changes

5. **Background Sync** ⚠️
   - No periodic background sync
   - Sync only happens when app is open
   - **TODO**: Implement WorkManager (Android) / BackgroundTasks (iOS)

6. **Error Handling** ⚠️
   - Limited retry logic
   - No exponential backoff
   - **TODO**: Implement robust retry mechanism

7. **Sync Queue** ⚠️
   - No persistent queue for failed syncs
   - Failed operations not automatically retried
   - **TODO**: Add sync queue table with retry logic

### Missing Features

- [ ] **Incremental Sync**: Only sync changes since last sync (use `updated_at` timestamps)
- [ ] **Delta Sync**: Upload only changed fields, not entire entity
- [ ] **Batch Operations**: Sync multiple entities in single API call
- [ ] **Progress Tracking**: Detailed progress for large syncs
- [ ] **Sync History**: Log of all sync operations for debugging
- [ ] **Conflict UI**: Show conflicts to user, let them choose resolution
- [ ] **Selective Sync**: Let user choose which data to sync
- [ ] **Compression**: Compress large payloads before transmission

---

## Testing the Sync Layer

### Manual Testing

```kotlin
// Test 1: Create trip offline, sync when online
@Test
fun testOfflineCreate() = runTest {
    // 1. Create trip while "offline" (not authenticated)
    val trip = Trip(name = "Test Trip", /* ... */)
    val result = tripSyncRepository.createTrip(trip)
    assertTrue(result.isSuccess)

    // 2. Verify trip is in local DB
    val localTrip = localRepository.getTripByIdSync(trip.id)
    assertNotNull(localTrip)

    // 3. Authenticate
    authManager.signInWithGoogle()

    // 4. Trigger sync
    syncService.syncAll()

    // 5. Verify trip is now on backend
    // (Need to check backend ID mapping)
}

// Test 2: Sync from remote
@Test
fun testSyncFromRemote() = runTest {
    // 1. Authenticate
    authManager.signInWithGoogle()

    // 2. Create trip on backend directly
    val createDto = TripCreateDto(/* ... */)
    apiClient.createTrip(createDto)

    // 3. Trigger sync
    tripSyncRepository.syncTripsFromRemote()

    // 4. Verify trip appears in local DB
    val trips = localRepository.allTrips.first()
    assertTrue(trips.isNotEmpty())
}

// Test 3: Conflict resolution
@Test
fun testConflictResolution() = runTest {
    // 1. Create and sync trip
    val trip = Trip(name = "Original Name", /* ... */)
    tripSyncRepository.createTrip(trip)

    // 2. Update locally
    val updatedTrip = trip.copy(name = "Local Update")
    localRepository.updateTrip(updatedTrip)

    // 3. Update remotely (simulate another device)
    val backendId = trip.id.toInt()
    apiClient.updateTrip(backendId, TripUpdateDto(name = "Remote Update"))

    // 4. Trigger sync
    tripSyncRepository.syncTripsFromRemote()

    // 5. Verify conflict resolution (should be "Remote Update" - last write wins)
    val finalTrip = localRepository.getTripByIdSync(trip.id)
    assertEquals("Remote Update", finalTrip?.name)
}
```

---

## Performance Considerations

### Optimization Strategies

1. **Lazy Sync**:
   - Don't sync on every screen navigation
   - Only sync when data is stale (> 5 minutes by default)
   - Use `needsSync()` to check staleness

2. **Batching**:
   - Batch multiple local changes before syncing
   - Reduce number of API calls
   - TODO: Implement batch API endpoints on backend

3. **Caching**:
   - SQLDelight provides fast local reads
   - No need for in-memory cache
   - Flows provide reactive updates

4. **Pagination**:
   - Backend supports skip/limit pagination
   - TODO: Implement paginated loading for large datasets

5. **Compression**:
   - TODO: Add Gzip compression for large payloads
   - Reduce network bandwidth

### Memory Management

- Flows are memory-efficient (cold streams)
- No large in-memory collections
- SQLDelight queries are lazy
- Use `.take(n)` on flows to limit emissions

---

## Next Steps (Phase 3)

### 1. Complete OAuth Integration (Week 3)
- [ ] Implement Android Activity for Google Sign-In
- [ ] Implement iOS ViewController for Google Sign-In
- [ ] Handle OAuth errors and edge cases
- [ ] Test auth flow end-to-end

### 2. Implement Full Entity Sync (Week 3-4)
- [ ] Activity sync with TripDay coordination
- [ ] Booking sync with TripDay/Activity coordination
- [ ] TripDay creation and sync
- [ ] Location → TripDay migration strategy

### 3. Add Sync Queue & Retry Logic (Week 4)
- [ ] Create `sync_queue` table in SQLDelight
- [ ] Implement exponential backoff retry
- [ ] Persistent queue for failed operations
- [ ] Auto-retry when coming online

### 4. Implement Conflict Resolution UI (Week 4)
- [ ] Detect conflicts based on `updated_at` timestamps
- [ ] Show conflict dialog to user
- [ ] Let user choose resolution strategy
- [ ] Apply chosen resolution

### 5. Add Network Monitoring (Week 5)
- [ ] Android: ConnectivityManager listener
- [ ] iOS: NWPathMonitor
- [ ] Trigger sync when coming online
- [ ] Show offline indicator in UI

### 6. Background Sync (Week 5)
- [ ] Android: WorkManager periodic sync
- [ ] iOS: BackgroundTasks framework
- [ ] Respect system battery/data restrictions
- [ ] Configurable sync interval

---

## File Structure

```
shared/src/commonMain/kotlin/com/aurora/travlogue/
├── core/
│   ├── data/
│   │   ├── remote/
│   │   │   ├── mapper/
│   │   │   │   ├── TripMapper.kt            ✅ Trip DTO ↔ Domain
│   │   │   │   ├── ActivityMapper.kt        ✅ Activity DTO ↔ Domain
│   │   │   │   └── BookingMapper.kt         ✅ Booking DTO ↔ Domain
│   │   │   └── ...
│   │   └── repository/
│   │       ├── TripRepository.kt            ✅ Local-only repo
│   │       └── TripSyncRepository.kt        ✅ NEW: Sync coordinator
│   └── domain/
│       └── service/
│           ├── SyncService.kt               ✅ NEW: Sync management
│           └── ...
└── di/
    └── SharedModule.kt                      ✅ Updated with sync layer
```

---

## Summary

✅ **Phase 2 Complete!**

**What Works**:
- ✅ DTO ↔ Domain model mapping for Trip, Activity, Booking
- ✅ TripSyncRepository with offline-first reads
- ✅ Optimistic writes with background sync
- ✅ SyncService for centralized sync management
- ✅ Automatic sync on authentication
- ✅ Manual refresh support
- ✅ Sync state tracking (Idle, Syncing, Success, Error)

**What's Next (Phase 3)**:
- Complete Google OAuth flow in platform apps
- Implement Activity/Booking/TripDay sync
- Add conflict resolution UI
- Implement network monitoring
- Add background sync with WorkManager/BackgroundTasks
- Implement sync queue with retry logic

**Estimated Timeline**: 3-4 weeks for Phase 3

---

*Generated: 2025-11-12*
*Branch: kmp-migration*
*Backend Version: v0.1.0*
