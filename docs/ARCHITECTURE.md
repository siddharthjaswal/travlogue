# Travlogue Architecture

## Overview

Travlogue follows **Feature-First Clean Architecture** - a pragmatic approach that balances clean architecture principles with development velocity.

## Architecture Principles

1. **Feature-First Organization**: Code is organized by feature, not by technical layer
2. **Separation of Concerns**: Clear boundaries between presentation, domain, and data layers
3. **Dependency Rule**: Dependencies point inward (presentation → domain → data)
4. **Pragmatic Complexity**: Add architectural layers only when needed

## Project Structure

```
app/src/main/java/com/aurora/travlogue/
│
├── core/                           # Shared across all features
│   ├── data/                      # Data layer
│   │   ├── local/                 # Room database
│   │   │   ├── entities/         # Database entities
│   │   │   ├── dao/              # Data access objects
│   │   │   └── database/         # Database configuration
│   │   └── repository/           # Repository implementations
│   ├── domain/                    # Shared business logic (future)
│   │   └── model/                # Domain models
│   ├── common/                    # Utilities and helpers
│   │   └── DateTimeUtils.kt
│   └── design/                    # UI theme and design system
│       ├── Color.kt
│       ├── Theme.kt
│       └── Type.kt
│
├── feature/                       # Feature modules
│   └── home/                      # Home feature
│       ├── presentation/          # UI layer
│       │   ├── HomeScreen.kt     # Composable UI
│       │   ├── HomeViewModel.kt  # State management
│       │   └── HomeUiState.kt    # UI state models
│       ├── domain/                # Feature-specific business logic
│       │   ├── usecase/          # Use cases (when needed)
│       │   └── model/            # Domain models (when needed)
│       └── components/            # Reusable UI components
│           ├── TripCard.kt
│           └── CreateTripDialog.kt
│
├── di/                            # Dependency injection
│   ├── DatabaseModule.kt
│   └── NetworkModule.kt
│
└── navigation/                    # App navigation
    ├── AppNavHost.kt
    └── Screen.kt
```

## Layers Explained

### 1. Presentation Layer (`feature/{name}/presentation/`)

**Responsibility**: UI and user interaction

**Contains**:
- Composable functions (screens)
- ViewModels (state management)
- UI State models
- UI Event models

**Example**:
```kotlin
// HomeScreen.kt - UI
@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val trips by viewModel.allTrips.collectAsState()
    // UI rendering
}

// HomeViewModel.kt - State management
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val tripRepository: TripRepository
) : ViewModel() {
    val allTrips: StateFlow<List<Trip>> = tripRepository.allTrips
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
}
```

### 2. Domain Layer (`feature/{name}/domain/`)

**Responsibility**: Business logic specific to the feature

**Contains**:
- Use cases (complex business operations)
- Domain models (feature-specific)
- Business rules

**When to use**:
- Complex logic that doesn't fit in ViewModel
- Operations combining multiple repositories
- Reusable business logic

**Example** (future gap detection):
```kotlin
// domain/usecase/DetectGapsUseCase.kt
class DetectGapsUseCase @Inject constructor(
    private val gapRepository: GapRepository,
    private val locationRepository: LocationRepository
) {
    suspend operator fun invoke(tripId: String): List<Gap> {
        // Complex gap detection logic
    }
}
```

### 3. Components Layer (`feature/{name}/components/`)

**Responsibility**: Reusable UI components for the feature

**Contains**:
- Composable components used by multiple screens
- Feature-specific UI widgets

**Example**:
```kotlin
// components/TripCard.kt
@Composable
fun TripCard(trip: Trip, onClick: () -> Unit, onDelete: () -> Unit) {
    Card(onClick = onClick) {
        // Trip display logic
    }
}
```

### 4. Core Data Layer (`core/data/`)

**Responsibility**: Data access and persistence

**Contains**:
- Room entities
- DAOs
- Repositories
- Network models (future)

**Example**:
```kotlin
// core/data/repository/TripRepository.kt
@Singleton
class TripRepository @Inject constructor(
    private val tripDao: TripDao
) {
    val allTrips: Flow<List<Trip>> = tripDao.getAllTrips()
    suspend fun insertTrip(trip: Trip) = tripDao.insertTrip(trip)
}
```

## Data Flow

```
┌──────────────┐
│  HomeScreen  │  Observes StateFlow, renders UI
└──────┬───────┘
       │ User Action
       ▼
┌──────────────┐
│ HomeViewModel│  Handles UI logic, manages state
└──────┬───────┘
       │ Calls repository
       ▼
┌──────────────┐
│TripRepository│  Abstracts data source
└──────┬───────┘
       │ Queries database
       ▼
┌──────────────┐
│   TripDao    │  Room database access
└──────┬───────┘
       │
       ▼
┌──────────────┐
│ Room Database│  Persistent storage
└──────────────┘
```

## Technology Stack

### Core
- **Language**: Kotlin
- **Build System**: Gradle with Kotlin DSL
- **Dependency Injection**: Hilt

### UI
- **Framework**: Jetpack Compose
- **Design System**: Material 3
- **Navigation**: Compose Navigation

### Data
- **Local Database**: Room 2.8.2
- **Networking**: Retrofit 2.11.0 (future use)
- **Reactive**: Kotlin Coroutines + Flow

### Architecture
- **Pattern**: MVVM with Clean Architecture
- **State Management**: StateFlow/SharedFlow
- **Organization**: Feature-first modules

## Best Practices

### 1. State Management

Use StateFlow for UI state:
```kotlin
private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()
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

### 3. Date/Time Handling

Use appropriate formats:
- **Calendar dates** (trip dates): ISO strings `"2025-11-15"`
- **Date-times with timezone** (bookings): ISO 8601 `"2025-11-15T14:30:00+01:00"`
- **System timestamps** (created/updated): Long (milliseconds)

Use `DateTimeUtils` for conversions:
```kotlin
import com.aurora.travlogue.core.common.DateTimeUtils.toLocalDate
import com.aurora.travlogue.core.common.DateTimeUtils.formatDateForDisplay

val date = trip.startDate.toLocalDate()
val displayDate = trip.startDate.formatDateForDisplay()
```

### 4. Repository Pattern

Always access data through repositories, never directly from DAOs:
```kotlin
// ✅ Good
class HomeViewModel @Inject constructor(
    private val tripRepository: TripRepository
)

// ❌ Bad
class HomeViewModel @Inject constructor(
    private val tripDao: TripDao  // Don't inject DAOs directly
)
```

### 5. Component Reusability

Extract reusable components to `components/` package:
```kotlin
// components/TripCard.kt - Reusable across home feature
@Composable
fun TripCard(trip: Trip, onClick: () -> Unit) { ... }
```

## When to Add Use Cases

Start with ViewModel → Repository pattern. Add use cases when:

1. **Complex business logic** that doesn't fit in ViewModel
2. **Multiple repositories** need to be coordinated
3. **Reusable operations** across multiple ViewModels
4. **Testable business logic** separate from Android framework

Example for gap detection:
```kotlin
// domain/usecase/DetectGapsUseCase.kt
class DetectGapsUseCase @Inject constructor(
    private val gapRepository: GapRepository,
    private val locationRepository: LocationRepository,
    private val bookingRepository: BookingRepository
) {
    suspend operator fun invoke(tripId: String): GapAnalysis {
        val locations = locationRepository.getLocationsForTrip(tripId)
        val bookings = bookingRepository.getBookingsForTrip(tripId)

        // Complex gap detection algorithm
        return analyzeGaps(locations, bookings)
    }
}
```

## Migration Path

The current architecture is **module-ready**:

### Current: Single Module
```
app/
└── src/main/java/com/aurora/travlogue/
    ├── core/
    ├── feature/
    └── di/
```

### Future: Multi-Module (when needed)
```
core-data/
core-domain/
core-design/
feature-home/
feature-plan/      # Future feature
feature-discover/  # Future feature
app/
```

To modularize later:
1. Each `feature/{name}` becomes a Gradle module
2. `core/*` becomes shared library modules
3. Package structure remains the same
4. Only build.gradle files change

## Folder Naming Conventions

- **Packages**: lowercase, no underscores (`feature.home.presentation`)
- **Files**: PascalCase (`HomeScreen.kt`, `TripRepository.kt`)
- **Composables**: PascalCase matching file name
- **Functions**: camelCase (`createTrip()`, `deleteTrip()`)

## References

- **Clean Architecture**: Robert C. Martin (Uncle Bob)
- **MVVM Pattern**: Microsoft documentation
- **Jetpack Compose**: Android official guide
- **Room Database**: Android official guide
- **Hilt**: Dagger Hilt documentation
