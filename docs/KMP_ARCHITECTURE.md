# Kotlin Multiplatform (KMP) Architecture Guide

## Table of Contents
1. [Project Structure](#project-structure)
2. [Shared Module Architecture](#shared-module-architecture)
3. [Platform Integration](#platform-integration)
   - [iOS Integration](#ios-integration)
   - [macOS Desktop Integration](#macos-desktop-integration)
4. [State Management](#state-management)
5. [Dependency Injection](#dependency-injection)
6. [Working with Xcode](#working-with-xcode)
7. [Working with Desktop](#working-with-desktop)
8. [Development Workflow](#development-workflow)

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
├── desktopApp/                    # macOS Desktop UI (Compose Multiplatform)
│   ├── build.gradle.kts           # Desktop build configuration
│   └── src/desktopMain/kotlin/com/aurora/travlogue/desktop/
│       ├── Main.kt                # Desktop app entry point
│       ├── TravlogueApp.kt        # Navigation
│       └── ui/screens/            # Compose Desktop screens
│           ├── HomeScreen.kt
│           ├── CreateTripScreen.kt
│           ├── MockScreen.kt
│           └── TripDetailScreen.kt
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

## Platform Integration

### **iOS Integration**

#### **Where Should iOS Files Be?**

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

### **macOS Desktop Integration**

#### **Where Should Desktop Files Be?**

All macOS desktop UI code goes in: `desktopApp/src/desktopMain/kotlin/com/aurora/travlogue/desktop/`

```
desktopApp/src/desktopMain/kotlin/com/aurora/travlogue/desktop/
├── Main.kt                        # Application entry point
├── TravlogueApp.kt               # Navigation and app root
└── ui/screens/                   # Screen composables
    ├── HomeScreen.kt
    ├── CreateTripScreen.kt
    ├── MockScreen.kt
    └── TripDetailScreen.kt
```

#### **Desktop App Entry Point**

The desktop app uses **Compose Multiplatform** for native desktop UI:

```kotlin
// desktopApp/src/desktopMain/kotlin/Main.kt
fun main() = application {
    // Initialize Koin
    startKoin {
        modules(sharedModule)
    }

    val windowState = rememberWindowState(
        size = DpSize(1200.dp, 800.dp)
    )

    Window(
        onCloseRequest = ::exitApplication,
        title = "Travlogue - Travel Planning Made Easy",
        state = windowState
    ) {
        TravlogueApp()
    }
}
```

#### **Desktop ViewModels Access**

Desktop uses **Compose Multiplatform's Koin integration** directly - no wrapper needed!

```kotlin
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel() // Direct access!
) {
    val uiState by viewModel.uiState.collectAsState()

    // Use uiState directly in Compose
    LazyColumn {
        items(uiState.trips) { trip ->
            TripCard(trip)
        }
    }
}
```

For ViewModels with parameters:

```kotlin
@Composable
fun TripDetailScreen(
    trip: Trip,
    viewModel: TripDetailViewModel = koinViewModel { parametersOf(trip.id) }
) {
    val uiState by viewModel.uiState.collectAsState()
    // ...
}
```

#### **Building Desktop App**

```bash
# Build the desktop application
./gradlew :desktopApp:build

# Run the desktop app
./gradlew :desktopApp:run

# Create distributable packages (DMG for macOS)
./gradlew :desktopApp:createDistributable

# Create native installers
./gradlew :desktopApp:packageDmg  # macOS DMG
./gradlew :desktopApp:packagePkg  # macOS PKG
```

---

## State Management

### **How to Pass States from Kotlin to Platforms**

Kotlin ViewModels use **StateFlow** for reactive state management.

### **iOS: Polling Pattern**

iOS observes these states using a **polling pattern** (due to Swift/Kotlin interop limitations):

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

**Why Polling for iOS?**
- Kotlin Flows don't directly map to Swift's Combine framework
- Polling is simple, reliable, and 0.5s interval is imperceptible to users
- Alternative: Use custom Combine wrappers (more complex)

### **Desktop: Direct StateFlow Collection**

Desktop uses Compose Multiplatform which has **native Kotlin Flow support**:

```kotlin
@Composable
fun HomeScreen(viewModel: HomeViewModel = koinViewModel()) {
    // Direct StateFlow collection - no polling needed!
    val uiState by viewModel.uiState.collectAsState()

    // Reactive updates automatically
    LazyColumn {
        items(uiState.trips) { trip ->
            TripCard(trip)
        }
    }
}
```

### **Android: Direct StateFlow Collection**

Android also uses direct collection (same as Desktop):

```kotlin
@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    // Automatically reactive
}
```

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

## Working with Desktop

### **Starting the macOS Desktop Application**

There are multiple ways to run the desktop application:

#### **Option 1: Using Gradle (Recommended for Development)**

```bash
# From project root
./gradlew :desktopApp:run
```

This starts the app in development mode with full debug support. The window will open at 1200x800 pixels.

#### **Option 2: Using IntelliJ IDEA / Android Studio**

1. Open the project in IntelliJ IDEA or Android Studio
2. Navigate to `desktopApp/src/desktopMain/kotlin/com/aurora/travlogue/desktop/Main.kt`
3. Click the green ▶️ play button next to the `main()` function
4. Or use the run configuration: Select "Main.kt" from the dropdown and click Run

#### **Option 3: Building a Distributable Package**

For sharing or deploying the app:

```bash
# Create a distributable application bundle
./gradlew :desktopApp:createDistributable

# The app will be created at:
# desktopApp/build/compose/binaries/main/app/

# Run the packaged app:
open desktopApp/build/compose/binaries/main/app/Travlogue.app
```

#### **Option 4: Creating Native Installers**

For distribution to end users:

```bash
# Create a DMG installer (macOS disk image)
./gradlew :desktopApp:packageDmg
# Output: desktopApp/build/compose/binaries/main/dmg/

# Create a PKG installer (macOS installer package)
./gradlew :desktopApp:packagePkg
# Output: desktopApp/build/compose/binaries/main/pkg/
```

### **Desktop App Features**

The desktop application has full feature parity with Android and iOS:

- ✅ **Home Screen**: View all trips, create new trips, access mock data generator
- ✅ **Create Trip**: Full form with date type selection (Fixed/Flexible), validation
- ✅ **Trip Details**: 4-tab interface (Overview, Timeline, Bookings, Locations)
- ✅ **Mock Data**: Generate sample trips (Complete Japan Trip, Incomplete Italy Trip)
- ✅ **Native Desktop Experience**: Window management, keyboard shortcuts, native UI controls

### **Desktop Development Workflow**

1. **Make changes** to desktop UI:
   ```bash
   # Edit files in desktopApp/src/desktopMain/kotlin/
   ```

2. **Build and run**:
   ```bash
   ./gradlew :desktopApp:run
   ```

3. **Hot reload** (when available):
   - Some changes can be picked up without restart
   - For major changes, stop and restart the app

4. **Debug** in IntelliJ IDEA:
   - Set breakpoints in desktop UI code
   - Run in debug mode for step-through debugging

### **Desktop Build Configuration**

The desktop app is configured in `desktopApp/build.gradle.kts`:

```kotlin
compose.desktop {
    application {
        mainClass = "com.aurora.travlogue.desktop.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Pkg)
            packageName = "Travlogue"
            packageVersion = "1.0.0"

            macOS {
                bundleID = "com.aurora.travlogue.desktop"
            }
        }
    }
}
```

### **System Requirements**

- **macOS**: 10.14 (Mojave) or later
- **Java**: JDK 11 or later
- **Memory**: Minimum 4GB RAM
- **Disk Space**: ~200MB for app + dependencies

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
┌──────────────────────────────────────────────────────┐
│           Shared Module (Kotlin)                     │
│   ┌────────────┐  ┌────────────┐  ┌──────────────┐ │
│   │ ViewModel  │  │ Repository │  │   Services   │ │
│   │ (StateFlow)│  │            │  │              │ │
│   └────────────┘  └────────────┘  └──────────────┘ │
└────────────┬─────────────────────────────────────────┘
             │
   ┌─────────┼──────────┬────────────┐
   │         │          │            │
   ▼         ▼          ▼            ▼
┌──────┐ ┌──────┐  ┌────────┐  ┌─────────┐
│Android│ │Desktop│  │  iOS   │  │   Web   │
│Compose│ │Compose│  │SwiftUI │  │(Future) │
└──────┘ └──────┘  └────────┘  └─────────┘
  Direct   Direct    Polling      Direct
  Flow     Flow      Pattern      Flow
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
# Build shared framework
./gradlew :shared:build

# Clean build
./gradlew clean

# Android
./gradlew :androidApp:installDebug            # Install on device/emulator
./gradlew :androidApp:assembleDebug           # Build APK

# iOS (from iosApp directory)
xcodebuild -project iosApp.xcodeproj -scheme iosApp -sdk iphonesimulator
open iosApp/iosApp.xcodeproj                  # Open in Xcode

# macOS Desktop
./gradlew :desktopApp:run                     # Run desktop app
./gradlew :desktopApp:build                   # Build desktop app
./gradlew :desktopApp:packageDmg              # Create DMG installer
./gradlew :desktopApp:packagePkg              # Create PKG installer
./gradlew :desktopApp:createDistributable     # Create distributable
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
| Desktop UI            | `desktopApp/src/desktopMain/kotlin/ui/screens/`       |

### **Platform Comparison**

| Feature               | Android | iOS | Desktop | Web (Future) |
|-----------------------|---------|-----|---------|--------------|
| UI Framework          | Compose | SwiftUI | Compose MP | Compose Web |
| ViewModel Access      | hiltViewModel() | KoinHelper + Wrapper | koinViewModel() | koinViewModel() |
| StateFlow Collection  | Direct  | Polling | Direct  | Direct |
| Navigation            | Navigation Component | SwiftUI Navigation | Sealed Class | TBD |
| DI Framework          | Hilt    | Koin (via bridge) | Koin | Koin |
| Build Tool            | Gradle  | Xcode | Gradle | Gradle |
| Distribution          | APK/AAB | IPA | DMG/PKG | Web Deploy |

---

## Summary

1. **Business logic lives in `shared/`** - accessible to all platforms
2. **Platform-specific UI**:
   - Android: `androidApp/src/main/java/ui/` (Jetpack Compose)
   - iOS: `iosApp/iosApp/Views/` (SwiftUI)
   - Desktop: `desktopApp/src/desktopMain/kotlin/ui/screens/` (Compose Multiplatform)
3. **ViewModel Access**:
   - Android: `hiltViewModel()` (Hilt DI)
   - iOS: `KoinHelper` bridge with polling wrappers
   - Desktop: `koinViewModel()` (direct Koin access)
4. **StateFlow Observation**:
   - Android & Desktop: Direct collection with `collectAsState()`
   - iOS: Polling pattern (0.5s interval)
5. **Development Tools**:
   - Kotlin code: Android Studio / IntelliJ IDEA
   - iOS UI: Xcode
   - Desktop UI: Android Studio / IntelliJ IDEA
6. **Build Commands**:
   - Shared: `./gradlew :shared:build`
   - Android: `./gradlew :androidApp:installDebug`
   - iOS: `open iosApp/iosApp.xcodeproj` then Cmd+R
   - Desktop: `./gradlew :desktopApp:run`

This architecture allows you to **write business logic once** and share it across **Android, iOS, and macOS Desktop**, while keeping platform-specific UI native and performant. Future expansion to Web is straightforward with Compose for Web!
