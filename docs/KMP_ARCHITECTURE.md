# Kotlin Multiplatform (KMP) Architecture Guide

## Table of Contents
1. [Project Structure](#project-structure)
2. [Shared Module Architecture](#shared-module-architecture)
3. [iOS Integration](#ios-integration)
4. [State Management](#state-management)
5. [Dependency Injection](#dependency-injection)
6. [Working with Xcode](#working-with-xcode)
7. [Development Workflow](#development-workflow)

---

## Project Structure

```
Travlogue/
├── androidApp/                    # Android-specific UI (Jetpack Compose)
│   └── src/main/java/com/aurora/travlogue/
│       └── ui/                    # Android UI screens
│
├── iosApp/                        # iOS-specific UI (SwiftUI)
│   ├── iosApp/
│   │   └── Views/                 # SwiftUI screens
│   │       ├── HomeView.swift
│   │       ├── CreateTripView.swift
│   │       ├── MockView.swift
│   │       └── TripDetail/
│   │           └── TripDetailViewEnhanced.swift
│   └── iosApp.xcodeproj/          # Xcode project file
│
└── shared/                        # Shared KMP code (70-80% of app)
    ├── src/
    │   ├── commonMain/kotlin/     # Platform-agnostic code
    │   │   └── com/aurora/travlogue/
    │   │       ├── data/
    │   │       │   ├── local/     # Database (Room/SQLDelight)
    │   │       │   ├── remote/    # API clients
    │   │       │   └── repository/ # Repository implementations
    │   │       ├── domain/
    │   │       │   ├── models/    # Data classes (Trip, Activity, etc.)
    │   │       │   ├── repository/ # Repository interfaces
    │   │       │   └── services/  # Business logic services
    │   │       ├── feature/
    │   │       │   ├── home/
    │   │       │   │   └── presentation/
    │   │       │   │       ├── HomeViewModel.kt
    │   │       │   │       └── HomeUiState.kt
    │   │       │   ├── createtrip/
    │   │       │   │   └── presentation/
    │   │       │   │       ├── CreateTripViewModel.kt
    │   │       │   │       └── CreateTripUiState.kt
    │   │       │   ├── tripdetail/
    │   │       │   │   ├── presentation/
    │   │       │   │   │   ├── TripDetailViewModel.kt
    │   │       │   │   │   └── TripDetailUiState.kt
    │   │       │   │   └── domain/
    │   │       │   │       └── models/
    │   │       │   └── mock/
    │   │       │       └── presentation/
    │   │       │           └── MockViewModel.kt
    │   │       └── di/
    │   │           └── SharedModule.kt  # Koin DI module
    │   │
    │   ├── androidMain/kotlin/    # Android-specific implementations
    │   │   └── com/aurora/travlogue/
    │   │       └── Platform.kt
    │   │
    │   └── iosMain/kotlin/        # iOS-specific implementations
    │       └── com/aurora/travlogue/
    │           ├── Platform.kt
    │           └── KoinHelper.kt  # Bridge for iOS to access ViewModels
    │
    └── build/
        └── XCFrameworks/
            └── debug/
                └── shared.framework  # Generated iOS framework
```

---

## Shared Module Architecture

### **Where is the Logic?**

The shared module follows **Clean Architecture** principles:

#### 1. **Domain Layer** (`shared/src/commonMain/kotlin/domain/`)
- **Models**: Data classes shared across platforms
  ```kotlin
  // Example: Trip.kt
  data class Trip(
      val id: String,
      val name: String,
      val originCity: String,
      val startDate: String,
      val endDate: String
  )
  ```

- **Repository Interfaces**: Contracts for data access
  ```kotlin
  interface TripRepository {
      suspend fun getAllTrips(): List<Trip>
      suspend fun insertTrip(trip: Trip)
  }
  ```

- **Services**: Business logic (e.g., GapDetectionService, BookingSyncService)

#### 2. **Data Layer** (`shared/src/commonMain/kotlin/data/`)
- **Repository Implementations**: Concrete implementations of repository interfaces
  ```kotlin
  class TripRepositoryImpl(
      private val database: TravlogueDatabase,
      private val apiClient: ApiClient
  ) : TripRepository {
      override suspend fun getAllTrips(): List<Trip> {
          return database.tripDao().getAllTrips()
      }
  }
  ```

- **Local**: Database DAOs and entities (Room)
- **Remote**: API clients and DTOs

#### 3. **Presentation Layer** (`shared/src/commonMain/kotlin/feature/`)
- **ViewModels**: Platform-agnostic ViewModels
  ```kotlin
  class HomeViewModel(
      private val tripRepository: TripRepository
  ) : ViewModel() {

      private val _uiState = MutableStateFlow(HomeUiState())
      val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

      fun loadTrips() {
          viewModelScope.launch {
              val trips = tripRepository.getAllTrips()
              _uiState.update { it.copy(trips = trips) }
          }
      }
  }
  ```

- **UI States**: Data classes representing UI state
  ```kotlin
  data class HomeUiState(
      val trips: List<Trip> = emptyList(),
      val isLoading: Boolean = false,
      val error: String? = null
  )
  ```

---

## iOS Integration

### **Where Should iOS Files Be?**

All iOS-specific UI code goes in: `iosApp/iosApp/Views/`

```
iosApp/iosApp/
├── Views/
│   ├── HomeView.swift              # Home screen UI
│   ├── CreateTripView.swift        # Create trip UI
│   ├── MockView.swift              # Mock data generation UI
│   └── TripDetail/
│       └── TripDetailViewEnhanced.swift  # Trip detail UI
├── iosApp.swift                    # App entry point
└── ContentView.swift               # Root view
```

### **KoinHelper Bridge** (`shared/src/iosMain/kotlin/KoinHelper.kt`)

This is the **bridge** between Kotlin and Swift. It exposes ViewModels to iOS:

```kotlin
class KoinHelper : KoinComponent {
    companion object {
        val shared = KoinHelper()
    }

    // Simple ViewModels (no parameters)
    fun getHomeViewModel(): HomeViewModel = get()
    fun getCreateTripViewModel(): CreateTripViewModel = get()
    fun getMockViewModel(): MockViewModel = get()

    // Factory ViewModels (with parameters)
    fun getTripDetailViewModel(tripId: String): TripDetailViewModel {
        return TripDetailViewModel(
            tripId = tripId,
            tripRepository = get(),
            gapDetectionService = get(),
            bookingSyncService = get()
        )
    }
}
```

---

## State Management

### **How to Pass States from Kotlin to iOS**

Kotlin ViewModels use **StateFlow** for reactive state management. iOS observes these states using a **polling pattern**.

#### **Step 1: ViewModel in Kotlin (Shared Module)**

```kotlin
class HomeViewModel(
    private val tripRepository: TripRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun loadTrips() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val trips = tripRepository.getAllTrips()
            _uiState.update { it.copy(trips = trips, isLoading = false) }
        }
    }
}
```

#### **Step 2: SwiftUI Wrapper (iOS)**

Create an `@ObservableObject` wrapper that polls the Kotlin StateFlow:

```swift
@MainActor
class HomeViewModelWrapper: ObservableObject {
    private let sharedViewModel: HomeViewModel

    @Published var trips: [Trip] = []
    @Published var isLoading: Bool = false
    @Published var error: String? = nil

    init() {
        self.sharedViewModel = KoinHelper.companion.shared.getHomeViewModel()
        observeState()
        sharedViewModel.loadTrips()
    }

    private func observeState() {
        Task { @MainActor in
            while !Task.isCancelled {
                if let state = sharedViewModel.uiState.value as? HomeUiState {
                    self.trips = state.trips
                    self.isLoading = state.isLoading
                    self.error = state.error
                }
                try? await Task.sleep(nanoseconds: 500_000_000) // Poll every 0.5s
            }
        }
    }
}
```

#### **Step 3: SwiftUI View**

```swift
struct HomeView: View {
    @StateObject private var viewModel = HomeViewModelWrapper()

    var body: some View {
        List(viewModel.trips, id: \.id) { trip in
            Text(trip.name)
        }
        .refreshable {
            viewModel.sharedViewModel.loadTrips()
        }
    }
}
```

### **Why Polling Instead of Direct Flow Collection?**

- Kotlin Flows don't directly map to Swift's Combine framework
- Polling is simple, reliable, and 0.5s interval is imperceptible to users
- Alternative: Use `@Parcelize` or custom Combine wrappers (more complex)

---

## Dependency Injection

### **Koin Setup** (`shared/src/commonMain/kotlin/di/SharedModule.kt`)

```kotlin
val sharedModule = module {
    // Database
    single { createDatabase(get()) }
    single { get<TravlogueDatabase>().tripDao() }
    single { get<TravlogueDatabase>().activityDao() }

    // API
    single { createHttpClient() }
    single { ApiClient(get()) }

    // Repositories
    single<TripRepository> { TripRepositoryImpl(get(), get()) }
    single<ActivityRepository> { ActivityRepositoryImpl(get()) }

    // Services
    single { GapDetectionService(get(), get()) }
    single { BookingSyncService(get()) }

    // ViewModels (simple)
    viewModelOf(::HomeViewModel)
    viewModelOf(::CreateTripViewModel)
    viewModelOf(::MockViewModel)

    // Note: TripDetailViewModel requires factory pattern (handled in KoinHelper)
}
```

### **Initializing Koin**

#### Android (`androidApp/src/main/java/MainActivity.kt`):
```kotlin
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startKoin {
            androidContext(this@MainActivity)
            modules(sharedModule)
        }
    }
}
```

#### iOS (`iosApp/iosApp/iosApp.swift`):
```swift
@main
struct iOSApp: App {
    init() {
        KoinKt.doInitKoin()
    }
}
```

---

## Working with Xcode

### **Can I Open Xcode to Work on iOS Files?**

**YES!** You can (and should) use Xcode for all iOS UI development.

#### **Opening the Project in Xcode**

```bash
cd iosApp
open iosApp.xcodeproj
```

Or double-click `iosApp.xcodeproj` in Finder.

### **What Can You Do in Xcode?**

✅ **Edit SwiftUI views** (`Views/` folder)
✅ **Add new Swift files** to the project
✅ **Debug iOS app** with breakpoints
✅ **Use Interface Builder** (if needed)
✅ **Manage iOS dependencies** (CocoaPods/SPM)
✅ **Run on simulator or device**

### **What You CANNOT Do in Xcode**

❌ **Edit Kotlin code** (ViewModels, repositories, models)
  - Use Android Studio or IntelliJ IDEA for Kotlin

❌ **Build the shared framework**
  - Run `./gradlew :shared:build` from terminal
  - Or use Android Studio to build

### **Typical Workflow**

1. **Write business logic** in Android Studio (Kotlin)
   ```bash
   # In Android Studio
   - Edit ViewModels in shared/src/commonMain/kotlin/feature/
   - Add new models in shared/src/commonMain/kotlin/domain/models/
   - Update repositories in shared/src/commonMain/kotlin/data/
   ```

2. **Build shared framework**
   ```bash
   ./gradlew :shared:build
   ```

3. **Switch to Xcode** for UI
   ```bash
   open iosApp/iosApp.xcodeproj
   ```

4. **Create/Edit SwiftUI views**
   ```swift
   // In Xcode
   - Create new views in Views/ folder
   - Access shared ViewModels via KoinHelper
   - Poll StateFlow for reactive updates
   ```

5. **Run iOS app** from Xcode (Cmd+R)

---

## Development Workflow

### **Scenario 1: Adding a New Feature**

Let's say you want to add a "Favorites" feature.

#### Step 1: Shared Module (Android Studio)

1. **Create domain model**:
   ```kotlin
   // shared/src/commonMain/kotlin/domain/models/Favorite.kt
   data class Favorite(
       val id: String,
       val tripId: String,
       val userId: String,
       val createdAt: String
   )
   ```

2. **Create repository**:
   ```kotlin
   // shared/src/commonMain/kotlin/domain/repository/FavoriteRepository.kt
   interface FavoriteRepository {
       suspend fun getFavorites(userId: String): List<Favorite>
       suspend fun addFavorite(favorite: Favorite)
   }
   ```

3. **Implement repository**:
   ```kotlin
   // shared/src/commonMain/kotlin/data/repository/FavoriteRepositoryImpl.kt
   class FavoriteRepositoryImpl(
       private val database: TravlogueDatabase
   ) : FavoriteRepository {
       override suspend fun getFavorites(userId: String): List<Favorite> {
           return database.favoriteDao().getFavoritesByUser(userId)
       }
   }
   ```

4. **Create ViewModel**:
   ```kotlin
   // shared/src/commonMain/kotlin/feature/favorites/presentation/FavoritesViewModel.kt
   class FavoritesViewModel(
       private val favoriteRepository: FavoriteRepository
   ) : ViewModel() {

       private val _uiState = MutableStateFlow(FavoritesUiState())
       val uiState: StateFlow<FavoritesUiState> = _uiState.asStateFlow()

       fun loadFavorites(userId: String) {
           viewModelScope.launch {
               val favorites = favoriteRepository.getFavorites(userId)
               _uiState.update { it.copy(favorites = favorites) }
           }
       }
   }
   ```

5. **Register in Koin**:
   ```kotlin
   // shared/src/commonMain/kotlin/di/SharedModule.kt
   val sharedModule = module {
       // ... existing dependencies
       single<FavoriteRepository> { FavoriteRepositoryImpl(get()) }
       viewModelOf(::FavoritesViewModel)
   }
   ```

6. **Expose to iOS**:
   ```kotlin
   // shared/src/iosMain/kotlin/KoinHelper.kt
   fun getFavoritesViewModel(): FavoritesViewModel = get()
   ```

7. **Build framework**:
   ```bash
   ./gradlew :shared:build
   ```

#### Step 2: Android UI (Android Studio)

```kotlin
// androidApp/src/main/java/ui/favorites/FavoritesScreen.kt
@Composable
fun FavoritesScreen(viewModel: FavoritesViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    LazyColumn {
        items(uiState.favorites) { favorite ->
            FavoriteCard(favorite)
        }
    }
}
```

#### Step 3: iOS UI (Xcode)

```swift
// iosApp/iosApp/Views/FavoritesView.swift
@MainActor
class FavoritesViewModelWrapper: ObservableObject {
    private let sharedViewModel: FavoritesViewModel
    @Published var favorites: [Favorite] = []

    init(userId: String) {
        self.sharedViewModel = KoinHelper.companion.shared.getFavoritesViewModel()
        observeState()
        sharedViewModel.loadFavorites(userId: userId)
    }

    private func observeState() {
        Task { @MainActor in
            while !Task.isCancelled {
                if let state = sharedViewModel.uiState.value as? FavoritesUiState {
                    self.favorites = state.favorites
                }
                try? await Task.sleep(nanoseconds: 500_000_000)
            }
        }
    }
}

struct FavoritesView: View {
    @StateObject private var viewModel: FavoritesViewModelWrapper

    init(userId: String) {
        _viewModel = StateObject(wrappedValue: FavoritesViewModelWrapper(userId: userId))
    }

    var body: some View {
        List(viewModel.favorites, id: \.id) { favorite in
            FavoriteCard(favorite: favorite)
        }
    }
}
```

### **Scenario 2: Modifying Existing Shared Code**

If you need to modify a ViewModel or repository:

1. **Edit in Android Studio**:
   ```bash
   # Modify shared/src/commonMain/kotlin/feature/home/presentation/HomeViewModel.kt
   ```

2. **Rebuild framework**:
   ```bash
   ./gradlew :shared:build
   ```

3. **iOS automatically picks up changes** (no code changes needed if API is unchanged)

4. **Test on both platforms**

### **Scenario 3: iOS-Only UI Changes**

If you only need to change iOS UI (no shared logic changes):

1. **Open Xcode**:
   ```bash
   open iosApp/iosApp.xcodeproj
   ```

2. **Edit SwiftUI files** directly in Xcode

3. **Run app** (Cmd+R) - no Gradle build needed!

---

## Best Practices

### **1. When to Put Code in Shared Module**

✅ **DO** put in shared:
- ViewModels and business logic
- Data models (Trip, Activity, Booking, etc.)
- Repositories and data access
- API clients
- Database schemas
- Validation logic
- Date/time utilities

❌ **DON'T** put in shared:
- UI code (Compose for Android, SwiftUI for iOS)
- Platform-specific APIs (camera, notifications)
- Navigation logic (different patterns per platform)

### **2. Communication Between Platforms**

```
┌─────────────────────────────────────────────┐
│          Shared Module (Kotlin)             │
│  ┌────────────┐  ┌────────────┐            │
│  │ ViewModel  │  │ Repository │            │
│  │ (StateFlow)│  │            │            │
│  └────────────┘  └────────────┘            │
└─────────────┬───────────────────────────────┘
              │
    ┌─────────┴─────────┐
    │                   │
    ▼                   ▼
┌─────────┐       ┌─────────┐
│ Android │       │   iOS   │
│ (Compose)│       │(SwiftUI)│
└─────────┘       └─────────┘
```

### **3. File Naming Conventions**

- **ViewModels**: `FeatureViewModel.kt` (e.g., `HomeViewModel.kt`)
- **UI States**: `FeatureUiState.kt` (e.g., `HomeUiState.kt`)
- **Models**: Singular noun (e.g., `Trip.kt`, `Activity.kt`)
- **Repositories**: `EntityRepository.kt` (e.g., `TripRepository.kt`)
- **iOS Views**: `FeatureView.swift` (e.g., `HomeView.swift`)

---

## Quick Reference

### **Common Commands**

```bash
# Build shared framework for iOS
./gradlew :shared:build

# Clean build
./gradlew clean

# Run Android app
./gradlew :androidApp:installDebug

# Build iOS app (from iosApp directory)
xcodebuild -project iosApp.xcodeproj -scheme iosApp -sdk iphonesimulator

# Open Xcode
open iosApp/iosApp.xcodeproj
```

### **Directory Quick Links**

| What                  | Location                                              |
|-----------------------|-------------------------------------------------------|
| Shared ViewModels     | `shared/src/commonMain/kotlin/feature/*/presentation/` |
| Domain Models         | `shared/src/commonMain/kotlin/domain/models/`         |
| Repositories          | `shared/src/commonMain/kotlin/data/repository/`       |
| Koin DI               | `shared/src/commonMain/kotlin/di/SharedModule.kt`     |
| iOS Bridge            | `shared/src/iosMain/kotlin/KoinHelper.kt`             |
| iOS Views             | `iosApp/iosApp/Views/`                                |
| Android UI            | `androidApp/src/main/java/ui/`                        |

---

## Summary

1. **Business logic lives in `shared/`** - accessible to both platforms
2. **iOS UI lives in `iosApp/iosApp/Views/`** - SwiftUI code
3. **Use KoinHelper** to access shared ViewModels from iOS
4. **Poll StateFlow** in iOS wrappers to get reactive updates
5. **Use Xcode for iOS UI work** after shared framework is built
6. **Use Android Studio for Kotlin work** (ViewModels, repositories, models)
7. **Build shared framework** with `./gradlew :shared:build` after Kotlin changes

This architecture allows you to **write business logic once** and share it across platforms, while keeping platform-specific UI native and performant.
