# Travlogue iOS App

This is the iOS application for Travlogue, built using SwiftUI and sharing business logic with the Android app through Kotlin Multiplatform (KMP).

## Prerequisites

- macOS with Xcode 15.0 or later
- iOS Simulator or physical iOS device running iOS 17.0+
- Gradle (installed via the project's gradlew)

## Project Structure

```
iosApp/
├── iosApp.xcodeproj/          # Xcode project configuration
└── iosApp/
    ├── TravlogueApp.swift     # App entry point with Koin initialization
    ├── ContentView.swift      # Root navigation view
    ├── Views/
    │   ├── HomeView.swift     # Trip list screen
    │   ├── CreateTripView.swift  # Create trip form
    │   └── TripDetailView.swift  # Trip details screen
    └── Assets.xcassets/       # App assets
```

## Architecture

The iOS app follows the **Model-View-ViewModel (MVVM)** pattern with shared ViewModels:

- **Shared Module** (Kotlin): Contains ViewModels, repositories, database, and business logic
- **iOS App** (Swift): Contains SwiftUI views and iOS-specific UI logic
- **Bridge**: `KoinHelper` provides access to shared ViewModels from Swift

### Key Components

1. **KoinInitializer**: Initializes dependency injection on app startup
2. **KoinHelper**: Singleton that provides shared ViewModels to SwiftUI
3. **ViewModel Wrappers**: SwiftUI `@ObservableObject` classes that wrap shared ViewModels
4. **SwiftUI Views**: Pure UI layer that observes ViewModel state

## How to Run

### Option 1: Using Xcode (Recommended)

1. **Build the shared framework**:
   ```bash
   cd /path/to/Travlogue
   ./gradlew :shared:embedAndSignAppleFrameworkForXcode
   ```

2. **Open the project in Xcode**:
   ```bash
   open iosApp/iosApp.xcodeproj
   ```

3. **Select a simulator**:
   - In Xcode, select a device from the toolbar (e.g., iPhone 15 Pro)

4. **Run the app**:
   - Click the Play button (▶) or press `Cmd + R`
   - The shared framework will be automatically built via a build phase script

### Option 2: Using Command Line

1. **Build the shared framework**:
   ```bash
   ./gradlew :shared:linkDebugFrameworkIosSimulatorArm64
   ```

2. **Build and run with xcodebuild**:
   ```bash
   cd iosApp
   xcodebuild -project iosApp.xcodeproj \
     -scheme iosApp \
     -destination 'platform=iOS Simulator,name=iPhone 15 Pro' \
     -configuration Debug \
     build
   ```

3. **Launch the simulator**:
   ```bash
   xcrun simctl boot "iPhone 15 Pro"  # Boot simulator
   xcrun simctl install booted <path-to-app>  # Install app
   xcrun simctl launch booted com.aurora.travlogue  # Launch app
   ```

## Features Implemented

✅ **Home Screen**
- Display list of trips from shared database
- Empty state with CTA to create trip
- Pull to refresh
- Delete trips with swipe action

✅ **Create Trip Screen**
- Create trips with name, origin city, and dates
- Support for fixed and flexible date types
- Form validation
- Integration with shared ViewModel

✅ **Trip Detail Screen**
- Display trip information
- Show travel dates
- Placeholder for bookings and activities (to be implemented)

## Features To Be Implemented

- [ ] Add bookings to trips
- [ ] Add activities to trips
- [ ] Add locations to trips
- [ ] Timeline view with bookings/activities
- [ ] Gap detection visualization
- [ ] Booking sync from emails
- [ ] Search and filters
- [ ] Settings screen

## Shared Dependencies

The iOS app uses these shared dependencies via the Shared framework:

- **Koin**: Dependency injection
- **SQLDelight**: Local database (shared with Android)
- **Ktor**: Networking (for future API calls)
- **kotlinx.datetime**: Cross-platform date handling
- **kotlinx.serialization**: JSON serialization
- **Kermit**: Logging

## Development Notes

### Working with Shared ViewModels

The shared ViewModels use Kotlin's `StateFlow` for reactive state. In Swift, we observe these using `AsyncSequence`:

```swift
@MainActor
class HomeViewModel: ObservableObject {
    @Published var trips: [Trip] = []
    private let sharedViewModel: com.aurora.travlogue.feature.home.presentation.HomeViewModel

    init() {
        self.sharedViewModel = KoinHelper.shared.getHomeViewModel()
        observeTrips()
    }

    private func observeTrips() {
        Task {
            for await state in sharedViewModel.uiState {
                self.trips = state.trips
            }
        }
    }
}
```

### Building for Different Targets

- **iOS Simulator (Apple Silicon)**: `./gradlew :shared:linkDebugFrameworkIosSimulatorArm64`
- **iOS Simulator (Intel)**: `./gradlew :shared:linkDebugFrameworkIosX64`
- **iOS Device**: `./gradlew :shared:linkDebugFrameworkIosArm64`

The Xcode build script automatically selects the correct target based on the active configuration.

### Debugging

- **Shared Code**: Add breakpoints in Kotlin code (requires Kotlin plugin in Xcode or Android Studio)
- **SwiftUI**: Standard Xcode debugging works normally
- **Logs**: Use `Logger.d()` in shared code (appears in Xcode console)

## Troubleshooting

### Framework Not Found

If you see "framework not found Shared":
```bash
./gradlew :shared:linkDebugFrameworkIosSimulatorArm64
```

### Koin Initialization Error

If the app crashes with Koin errors:
- Ensure `initKoin()` is called in `TravlogueApp.init()`
- Check that all dependencies are properly defined in `SharedModule.kt`

### Date Parsing Issues

The app uses ISO 8601 date format (`yyyy-MM-dd`). If dates don't display correctly:
- Verify the shared ViewModel is providing dates in ISO format
- Check the `formatDate()` function in the Swift views

## Related Documentation

- [KMP Migration Complete](../docs/KMP_MIGRATION_COMPLETE.md)
- [Architecture Documentation](../docs/ARCHITECTURE.md)
- [Android App](../app/README.md)

## License

Copyright © 2024 Aurora Travlogue
