# Travlogue Architecture

**Last Updated**: October 23, 2025
**Architecture**: Kotlin Multiplatform with Feature-First Clean Architecture

---

## Overview

Travlogue follows **Kotlin Multiplatform (KMP) with Feature-First Clean Architecture** - a modern approach that enables code sharing across Android, iOS, and Desktop platforms while maintaining clean architecture principles.

### Architecture Principles

1. **Multiplatform First**: Share business logic, data layer, and presentation logic across platforms
2. **Feature-First Organization**: Code organized by feature, not by technical layer
3. **Separation of Concerns**: Clear boundaries between presentation, domain, and data layers
4. **Dependency Rule**: Dependencies point inward (presentation → domain → data)
5. **Platform-Specific When Needed**: Platform code only where absolutely necessary

---

## Project Structure

### High-Level Architecture

```
Travlogue/
│
├── shared/                        # KMP Module (Android, iOS, Desktop)
│   ├── commonMain/               # Shared code for all platforms
│   ├── androidMain/              # Android-specific implementations
│   ├── iosMain/                  # iOS-specific implementations
│   └── desktopMain/              # Desktop-specific implementations
│
└── app/                          # Android Application
    └── Android-specific UI & features
```

### Detailed Structure

```
shared/src/
│
├── commonMain/kotlin/com/aurora/travlogue/
│   │
│   ├── core/
│   │   ├── common/                    # Utilities (multiplatform)
│   │   │   ├── UUID.kt
│   │   │   └── DateTimeUtils.kt
│   │   │
│   │   ├── data/
│   │   │   ├── local/
│   │   │   │   └── DatabaseDriverFactory.kt (expect)
│   │   │   └── repository/
│   │   │       └── TripRepository.kt  # Multiplatform repository
│   │   │
│   │   └── domain/
│   │       ├── model/                 # Domain models (data classes)
│   │       │   ├── Trip.kt
│   │       │   ├── Location.kt
│   │       │   ├── Activity.kt
│   │       │   ├── Booking.kt
│   │       │   ├── Gap.kt
│   │       │   └── ... (enums, types)
│   │       │
│   │       └── service/               # Business logic services
│   │           ├── BookingSyncService.kt
│   │           └── GapDetectionService.kt
│   │
│   ├── di/
│   │   └── SharedModule.kt           # Koin DI configuration
│   │
│   └── presentation/                  # Shared ViewModels
│       ├── home/
│       │   ├── HomeViewModel.kt
│       │   └── HomeUiState.kt
│       │
│       └── createtrip/
│           ├── CreateTripViewModel.kt
│           └── CreateTripUiState.kt
│
├── commonMain/sqldelight/com/aurora/travlogue/core/data/local/
│   ├── Trip.sq                       # SQLDelight schema
│   ├── Location.sq
│   ├── Activity.sq
│   ├── Booking.sq
│   ├── Gap.sq
│   └── TransitOption.sq
│
├── androidMain/kotlin/com/aurora/travlogue/
│   ├── core/data/local/
│   │   └── DatabaseDriverFactory.kt  # Android SQLite driver
│   └── di/
│       └── PlatformModule.kt         # Android-specific DI
│
├── iosMain/kotlin/com/aurora/travlogue/
│   ├── core/data/local/
│   │   └── DatabaseDriverFactory.kt  # iOS SQLite driver
│   └── di/
│       └── PlatformModule.kt         # iOS-specific DI
│
└── desktopMain/kotlin/com/aurora/travlogue/
    ├── core/data/local/
    │   └── DatabaseDriverFactory.kt  # Desktop SQLite driver
    └── di/
        └── PlatformModule.kt         # Desktop-specific DI

app/src/main/java/com/aurora/travlogue/
│
├── feature/                           # Android-specific features
│   ├── home/
│   │   ├── presentation/
│   │   │   └── HomeScreen.kt         # Jetpack Compose UI
│   │   └── components/
│   │       └── TripCard.kt
│   │
│   ├── createtrip/
│   │   ├── presentation/
│   │   │   └── CreateTripScreen.kt
│   │   └── components/
│   │       ├── DatePickerField.kt
│   │       └── TravelDatesCard.kt
│   │
│   └── tripdetail/
│       ├── presentation/
│       │   ├── TripDetailScreen.kt
│       │   ├── TripDetailViewModel.kt  # Still Hilt (uses bridge)
│       │   └── TripDetailUiState.kt
│       └── components/
│           └── ... (UI components)
│
├── core/
│   └── design/                        # Android Material theme
│       ├── Color.kt
│       ├── Theme.kt
│       └── Type.kt
│
├── di/
│   └── SharedModule.kt               # Hilt-Koin bridge
│
└── navigation/
    ├── AppNavHost.kt
    └── Screen.kt
```

---

## Architecture Layers

### 1. Shared Module (KMP)

#### Data Layer (`shared/core/data/`)

**Responsibility**: Data access and persistence (multiplatform)

**Contains**:
- SQLDelight database schema
- Platform-specific database drivers (expect/actual)
- Repository implementations
- Data models

**Example**:
```kotlin
// commonMain: TripRepository.kt
class TripRepository(private val database: TravlogueDb) {
    fun getAllTrips(): Flow<List<Trip>> =
        database.tripQueries
            .selectAll()
            .asFlow()
            .mapToList(Dispatchers.IO)
}

// androidMain: DatabaseDriverFactory.kt (actual)
actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(TravlogueDb.Schema, context, "travlogue.db")
    }
}
```

#### Domain Layer (`shared/core/domain/`)

**Responsibility**: Business logic (multiplatform)

**Contains**:
- Domain models (data classes with kotlinx.serialization)
- Business services
- Business rules

**Example**:
```kotlin
// model/Trip.kt
@Serializable
data class Trip(
    val id: String,
    val name: String,
    val dateType: DateType,
    val startDate: String? = null,
    val endDate: String? = null,
    val originCity: String,
    val coverImageUri: String? = null,
    val createdAt: Long,
    val updatedAt: Long
)

// service/BookingSyncService.kt
class BookingSyncService {
    fun syncBookingsWithLocations(
        locations: List<Location>,
        bookings: List<Booking>
    ): List<Location> {
        // Business logic to sync booking times with locations
    }
}
```

#### Presentation Layer (`shared/presentation/`)

**Responsibility**: Shared ViewModels and UI state

**Contains**:
- ViewModels (using kotlinx.coroutines)
- UI State models
- UI Event models

**Example**:
```kotlin
// presentation/home/HomeViewModel.kt
class HomeViewModel(
    private val tripRepository: TripRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    val allTrips: StateFlow<List<Trip>> = tripRepository
        .getAllTrips()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
}
```

### 2. Android App (Platform-Specific)

#### UI Layer (`app/feature/{name}/presentation/`)

**Responsibility**: Jetpack Compose UI (Android-specific)

**Contains**:
- Composable screens
- Android-specific UI components
- Material 3 theming

**Example**:
```kotlin
// feature/home/presentation/HomeScreen.kt
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel(),  // From shared module
    onNavigateToCreateTrip: () -> Unit
) {
    val trips by viewModel.allTrips.collectAsState()
    val uiState by viewModel.uiState.collectAsState()

    // Jetpack Compose UI
    LazyColumn {
        items(trips) { trip ->
            TripCard(
                trip = trip,
                onClick = { /* navigate */ }
            )
        }
    }
}
```

#### Dependency Injection Bridge (`app/di/SharedModule.kt`)

**Responsibility**: Bridge Koin (shared) and Hilt (Android)

**Example**:
```kotlin
@Module
@InstallIn(SingletonComponent::class)
object SharedModule : KoinComponent {

    @Provides
    @Singleton
    fun provideTripRepository(): TripRepository {
        val repository: TripRepository by inject()  // From Koin
        return repository
    }

    @Provides
    @Singleton
    fun provideGapDetectionService(): GapDetectionService {
        val service: GapDetectionService by inject()
        return service
    }
}
```

---

## Data Flow

### Cross-Platform Data Flow

```
┌─────────────────────────────────────────────────────┐
│  Platform UI Layer (Android/iOS/Desktop)           │
│  ┌───────────┐  ┌───────────┐  ┌───────────┐      │
│  │  Android  │  │    iOS    │  │  Desktop  │      │
│  │ Compose   │  │  SwiftUI  │  │ Compose   │      │
│  └─────┬─────┘  └─────┬─────┘  └─────┬─────┘      │
└────────┼──────────────┼──────────────┼────────────┘
         │              │              │
         ▼              ▼              ▼
┌─────────────────────────────────────────────────────┐
│  Shared Presentation Layer (KMP)                    │
│  ┌───────────────────────────────────────────┐     │
│  │        HomeViewModel (StateFlow)          │     │
│  │        CreateTripViewModel                │     │
│  └───────────────┬───────────────────────────┘     │
└──────────────────┼─────────────────────────────────┘
                   │ Calls repository
                   ▼
┌─────────────────────────────────────────────────────┐
│  Shared Domain Layer (KMP)                          │
│  ┌───────────────────────────────────────────┐     │
│  │        TripRepository                     │     │
│  │        BookingSyncService                 │     │
│  │        GapDetectionService                │     │
│  └───────────────┬───────────────────────────┘     │
└──────────────────┼─────────────────────────────────┘
                   │ Queries database
                   ▼
┌─────────────────────────────────────────────────────┐
│  Shared Data Layer (KMP)                            │
│  ┌───────────────────────────────────────────┐     │
│  │     SQLDelight Database (TravlogueDb)     │     │
│  └───────────────┬───────────────────────────┘     │
└──────────────────┼─────────────────────────────────┘
                   │ Uses platform driver
                   ▼
┌─────────────────────────────────────────────────────┐
│  Platform-Specific Drivers (expect/actual)          │
│  ┌───────────┐  ┌───────────┐  ┌───────────┐      │
│  │  Android  │  │    iOS    │  │  Desktop  │      │
│  │  SQLite   │  │  SQLite   │  │  SQLite   │      │
│  └───────────┘  └───────────┘  └───────────┘      │
└─────────────────────────────────────────────────────┘
```

---

## Technology Stack

### Shared Module (KMP)

#### Core
- **Language**: Kotlin 2.2.20
- **Build System**: Gradle 9.0.0 with Kotlin DSL
- **Dependency Injection**: Koin 4.0.1

#### Database
- **ORM**: SQLDelight 2.0.2
- **Driver**: Platform-specific (Android, iOS, Desktop)
- **Query Generation**: Compile-time type-safe SQL

#### Reactive
- **Coroutines**: kotlinx.coroutines 1.10.0
- **Serialization**: kotlinx.serialization 1.9.0
- **DateTime**: kotlinx.datetime 0.6.1

### Android App

#### Core
- **Language**: Kotlin 2.2.20
- **Dependency Injection**: Hilt (for Android-specific) + Koin (for shared)

#### UI
- **Framework**: Jetpack Compose
- **Design System**: Material 3
- **Navigation**: Compose Navigation

#### Data
- **Local Database**: SQLDelight (via shared module)
- **Networking**: Retrofit 2.11.0 (future use)
- **Reactive**: Kotlin Coroutines + Flow

---

## Dependency Injection

### Shared Module (Koin)

```kotlin
// shared/src/commonMain/kotlin/di/SharedModule.kt
fun sharedModule() = module {
    single<TravlogueDb> { createDatabase(get()) }
    single<TripRepository> { TripRepository(get()) }
    single<GapDetectionService> { GapDetectionService() }
    single<BookingSyncService> { BookingSyncService() }
}

// Platform-specific
expect fun platformModule(): Module
```

**Android Platform Module:**
```kotlin
// shared/src/androidMain/kotlin/di/PlatformModule.kt
actual fun platformModule() = module {
    single { DriverFactory(androidContext()) }
}
```

### Android App (Hilt + Koin Bridge)

**Application Initialization:**
```kotlin
@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()

        // Initialize Koin for KMP shared module
        startKoin {
            androidContext(this@App)
            modules(platformModule, sharedModule)
        }
    }
}
```

**Hilt-Koin Bridge:**
```kotlin
@Module
@InstallIn(SingletonComponent::class)
object SharedModule : KoinComponent {
    // Provides shared module dependencies to Hilt
    @Provides @Singleton
    fun provideTripRepository(): TripRepository {
        val repository: TripRepository by inject()
        return repository
    }
}
```

---

## Best Practices

### 1. State Management

#### Shared ViewModel Pattern
```kotlin
class HomeViewModel(
    private val tripRepository: TripRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    // Use StateFlow for reactive state
    val allTrips: StateFlow<List<Trip>> = tripRepository
        .getAllTrips()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
}
```

### 2. Error Handling

Handle errors in ViewModel, expose as UI state:
```kotlin
sealed interface HomeUiState {
    data object Loading : HomeUiState
    data class Success(val trips: List<Trip>) : HomeUiState
    data class Error(val message: String) : HomeUiState
}
```

### 3. Platform-Specific Code (expect/actual)

Use expect/actual pattern for platform differences:
```kotlin
// commonMain
expect class DriverFactory {
    fun createDriver(): SqlDriver
}

// androidMain
actual class DriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(TravlogueDb.Schema, context, "travlogue.db")
    }
}
```

### 4. Date/Time Handling

Use kotlinx.datetime for multiplatform compatibility:
```kotlin
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

val now = Clock.System.now()
val localDateTime = now.toLocalDateTime(TimeZone.currentSystemDefault())
```

### 5. Serialization

Use kotlinx.serialization for data classes:
```kotlin
@Serializable
data class Trip(
    val id: String,
    val name: String,
    val dateType: DateType
)

@Serializable
enum class DateType {
    FIXED, FLEXIBLE
}
```

### 6. Smart Cast Handling

Store nullable properties in local variables:
```kotlin
// ❌ Bad - Won't compile with KMP public API
if (trip.startDate != null) {
    Text(trip.startDate.format())  // Smart cast fails
}

// ✅ Good - Works with KMP
val startDate = trip.startDate
if (startDate != null) {
    Text(startDate.format())  // Smart cast succeeds
}
```

---

## Platform-Specific Implementation

### Android

**ViewModel Consumption:**
```kotlin
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel()  // From shared module
) {
    val trips by viewModel.allTrips.collectAsState()
    // Jetpack Compose UI
}
```

### iOS (Ready for Implementation)

**ViewModel Consumption:**
```swift
struct HomeView: View {
    @StateObject private var viewModel = HomeViewModel(
        tripRepository: KoinKt.koin.get()
    )

    var body: some View {
        // SwiftUI
    }
}
```

### Desktop (Ready for Implementation)

**ViewModel Consumption:**
```kotlin
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel()  // Same as Android
) {
    val trips by viewModel.allTrips.collectAsState()
    // Compose Desktop UI
}
```

---

## Testing Strategy

### Shared Module Tests

```kotlin
// commonTest
class TripRepositoryTest {
    @Test
    fun insertAndRetrieveTrip() = runTest {
        val repository = TripRepository(createTestDatabase())
        val trip = createTestTrip()

        repository.insertTrip(trip)
        val retrieved = repository.getTripById(trip.id).first()

        assertEquals(trip, retrieved)
    }
}
```

### Android Tests

```kotlin
// Android UI tests
@Test
fun homeScreen_displaysTrips() {
    composeTestRule.setContent {
        HomeScreen()
    }

    composeTestRule
        .onNodeWithText("My Trip")
        .assertIsDisplayed()
}
```

---

## Migration Status

| Component | Status | Platform |
|-----------|--------|----------|
| Database (SQLDelight) | ✅ Complete | Android, iOS, Desktop |
| TripRepository | ✅ Complete | Android, iOS, Desktop |
| Domain Models | ✅ Complete | Android, iOS, Desktop |
| Business Services | ✅ Complete | Android, iOS, Desktop |
| HomeViewModel | ✅ Complete | Android, iOS, Desktop |
| CreateTripViewModel | ✅ Complete | Android, iOS, Desktop |
| TripDetailViewModel | ⚠️ Hybrid | Android (uses Hilt bridge) |
| Android UI | ✅ Complete | Android |
| iOS UI | 📋 Planned | iOS |
| Desktop UI | 📋 Planned | Desktop |

---

## Future Architecture Plans

### Short Term
- Migrate remaining Android ViewModels to shared module
- Add comprehensive unit tests for shared code
- Begin iOS app development

### Medium Term
- Implement iOS app with SwiftUI
- Create Compose Desktop application
- Share more presentation logic

### Long Term
- Kotlin/JS for web platform
- Compose Multiplatform UI sharing
- Full feature parity across all platforms

---

## References

### Official Documentation
- [Kotlin Multiplatform](https://kotlinlang.org/docs/multiplatform.html)
- [SQLDelight](https://cashapp.github.io/sqldelight/)
- [Koin](https://insert-koin.io/)
- [kotlinx.datetime](https://github.com/Kotlin/kotlinx-datetime)
- [kotlinx.serialization](https://github.com/Kotlin/kotlinx.serialization)
- [Jetpack Compose](https://developer.android.com/compose)

### Project Documentation
- `docs/KMP_MIGRATION_COMPLETE.md` - Detailed migration documentation
- `docs/TESTING_STRATEGY.md` - Testing approach
- `docs/PRD.md` - Product requirements
- `shared/README.md` - Shared module documentation

---

**Architecture Version**: 2.0 (KMP)
**Last Updated**: October 23, 2025
**Status**: ✅ Fully Migrated to Kotlin Multiplatform
