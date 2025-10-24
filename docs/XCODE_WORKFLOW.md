# Xcode Workflow for iOS Development

## Overview

This guide explains how to work with Xcode for iOS UI development in a Kotlin Multiplatform project.

---

## Opening the Project

### Method 1: From Terminal
```bash
cd /Users/sid/Projects/Android/Travlogue/iosApp
open iosApp.xcodeproj
```

### Method 2: From Finder
1. Navigate to `Travlogue/iosApp/`
2. Double-click `iosApp.xcodeproj`

---

## Project Structure in Xcode

When you open the project, you'll see:

```
iosApp
├── iosApp.swift           # App entry point (contains Koin initialization)
├── ContentView.swift      # Root view
└── Views/                 # All your SwiftUI screens
    ├── HomeView.swift
    ├── CreateTripView.swift
    ├── MockView.swift
    └── TripDetail/
        └── TripDetailViewEnhanced.swift
```

### Important: Shared Framework

In the project navigator, you'll also see:
```
Frameworks/
└── shared.framework       # This is the compiled Kotlin code!
```

**This framework is generated from the `shared/` module and contains:**
- All ViewModels
- All data models (Trip, Activity, Booking, etc.)
- All repositories
- All business logic

---

## Adding New Swift Files in Xcode

### Step 1: Right-click on `Views/` Folder
1. In Xcode's project navigator (left sidebar)
2. Right-click on the `Views/` folder
3. Select **New File...**

### Step 2: Choose Template
1. Select **SwiftUI View**
2. Click **Next**

### Step 3: Name Your File
1. Enter name (e.g., `ProfileView`)
2. Make sure **Targets** has `iosApp` checked
3. Click **Create**

### Step 4: Xcode Automatically Updates `.pbxproj`
Xcode will automatically add the file to `iosApp.xcodeproj/project.pbxproj`. No manual editing needed!

---

## Working with Shared ViewModels

### Pattern: Create a Wrapper Class

For every shared ViewModel, create a SwiftUI wrapper:

```swift
import SwiftUI
import shared  // Import the shared framework

@MainActor
class ProfileViewModelWrapper: ObservableObject {
    private let sharedViewModel: ProfileViewModel

    // Published properties that SwiftUI observes
    @Published var user: User? = nil
    @Published var isLoading: Bool = false
    @Published var error: String? = nil

    init() {
        // Get ViewModel from KoinHelper
        self.sharedViewModel = KoinHelper.companion.shared.getProfileViewModel()
        observeState()

        // Trigger initial data load
        sharedViewModel.loadProfile()
    }

    // Poll the Kotlin StateFlow every 0.5 seconds
    private func observeState() {
        Task { @MainActor in
            while !Task.isCancelled {
                if let state = sharedViewModel.uiState.value as? ProfileUiState {
                    self.user = state.user
                    self.isLoading = state.isLoading
                    self.error = state.error
                }
                try? await Task.sleep(nanoseconds: 500_000_000)
            }
        }
    }

    // Expose ViewModel functions
    func updateProfile(name: String) {
        sharedViewModel.updateProfile(name: name)
    }
}

struct ProfileView: View {
    @StateObject private var viewModel = ProfileViewModelWrapper()

    var body: some View {
        VStack {
            if viewModel.isLoading {
                ProgressView()
            } else if let user = viewModel.user {
                Text(user.name)
                Button("Update") {
                    viewModel.updateProfile(name: "New Name")
                }
            }
        }
    }
}
```

---

## Running the App

### From Xcode

1. **Select a simulator**: Click the device dropdown next to the scheme (top-left)
   - Choose iPhone 15, iPad, etc.

2. **Run**: Press `Cmd+R` or click the Play button

3. **Build output**: You'll see build logs in the bottom panel

### What Happens During Build?

1. Xcode compiles all Swift files
2. Links against `shared.framework` (pre-built from Kotlin)
3. Packages the app
4. Installs on simulator/device

**Important**: If you modified Kotlin code (ViewModels, repositories), you must rebuild the shared framework FIRST:
```bash
cd /Users/sid/Projects/Android/Travlogue
./gradlew :shared:build
```

Then run the iOS app from Xcode.

---

## Debugging in Xcode

### Setting Breakpoints

1. Open a Swift file (e.g., `HomeView.swift`)
2. Click the line number gutter to set a breakpoint
3. Run the app (Cmd+R)
4. When the breakpoint hits, you can:
   - Inspect variables in the **Variables View** (bottom-left)
   - Step over (F6), step into (F7), step out (F8)
   - Use the **Debug Console** (bottom panel)

### Inspecting Shared Objects

You can inspect Kotlin objects in the debugger:

```swift
// Set breakpoint on this line
let trips = viewModel.trips  // Inspect this in Variables View
```

In the debugger, you'll see the Kotlin `List<Trip>` bridged to Swift.

### Using Print Statements

```swift
func loadTrips() {
    print("Loading trips...")
    sharedViewModel.loadTrips()
    print("Trips loaded: \(viewModel.trips.count)")
}
```

Logs appear in the **Console** (bottom panel).

---

## Xcode Interface Builder (Optional)

While this project uses **SwiftUI** (code-based UI), you can still use Interface Builder if needed:

1. Right-click `Views/` → New File → **Storyboard**
2. Drag UI elements from the Object Library
3. Connect to SwiftUI using `UIViewControllerRepresentable`

**Recommendation**: Stick with SwiftUI for consistency with the project.

---

## Managing Dependencies

### CocoaPods (if used)

If the project uses CocoaPods:

1. Close Xcode
2. Run:
   ```bash
   cd iosApp
   pod install
   ```
3. Open `iosApp.xcworkspace` (NOT `.xcodeproj`)

### Swift Package Manager (SPM)

To add a Swift package:

1. In Xcode: **File** → **Add Packages...**
2. Enter the package URL (e.g., `https://github.com/Alamofire/Alamofire.git`)
3. Select version and click **Add Package**

---

## Common Workflows

### Workflow 1: Pure UI Change (No Kotlin Changes)

**Scenario**: You want to change the layout of `HomeView.swift`

1. Open Xcode:
   ```bash
   open iosApp/iosApp.xcodeproj
   ```

2. Edit `Views/HomeView.swift` directly in Xcode

3. Press `Cmd+R` to run

4. No need to rebuild Kotlin framework!

---

### Workflow 2: Adding a New Screen

**Scenario**: You want to add a "Settings" screen

#### Step 1: Create ViewModel in Kotlin (Android Studio)

```kotlin
// shared/src/commonMain/kotlin/feature/settings/presentation/SettingsViewModel.kt
class SettingsViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState: StateFlow<SettingsUiState> = _uiState.asStateFlow()
}

data class SettingsUiState(
    val theme: Theme = Theme.SYSTEM,
    val notificationsEnabled: Boolean = true
)
```

#### Step 2: Register in Koin

```kotlin
// shared/src/commonMain/kotlin/di/SharedModule.kt
viewModelOf(::SettingsViewModel)
```

#### Step 3: Expose to iOS

```kotlin
// shared/src/iosMain/kotlin/KoinHelper.kt
fun getSettingsViewModel(): SettingsViewModel = get()
```

#### Step 4: Build Shared Framework

```bash
./gradlew :shared:build
```

#### Step 5: Create SwiftUI View in Xcode

1. Open Xcode: `open iosApp/iosApp.xcodeproj`

2. Right-click `Views/` → **New File** → **SwiftUI View**

3. Name it `SettingsView.swift`

4. Implement the wrapper:

```swift
import SwiftUI
import shared

@MainActor
class SettingsViewModelWrapper: ObservableObject {
    private let sharedViewModel: SettingsViewModel

    @Published var theme: Theme = .system
    @Published var notificationsEnabled: Bool = true

    init() {
        self.sharedViewModel = KoinHelper.companion.shared.getSettingsViewModel()
        observeState()
    }

    private func observeState() {
        Task { @MainActor in
            while !Task.isCancelled {
                if let state = sharedViewModel.uiState.value as? SettingsUiState {
                    self.theme = state.theme
                    self.notificationsEnabled = state.notificationsEnabled
                }
                try? await Task.sleep(nanoseconds: 500_000_000)
            }
        }
    }
}

struct SettingsView: View {
    @StateObject private var viewModel = SettingsViewModelWrapper()

    var body: some View {
        Form {
            Section("Appearance") {
                Picker("Theme", selection: $viewModel.theme) {
                    Text("System").tag(Theme.system)
                    Text("Light").tag(Theme.light)
                    Text("Dark").tag(Theme.dark)
                }
            }

            Section("Notifications") {
                Toggle("Enable Notifications", isOn: $viewModel.notificationsEnabled)
            }
        }
        .navigationTitle("Settings")
    }
}
```

5. Run the app (Cmd+R)

---

### Workflow 3: Kotlin + iOS Changes

**Scenario**: You modify a ViewModel and need to update iOS UI

1. **Edit Kotlin code** in Android Studio:
   ```kotlin
   // shared/.../HomeViewModel.kt
   fun deleteTrip(tripId: String) {  // New function
       viewModelScope.launch {
           tripRepository.deleteTrip(tripId)
           loadTrips()
       }
   }
   ```

2. **Rebuild shared framework**:
   ```bash
   ./gradlew :shared:build
   ```

3. **Open Xcode**:
   ```bash
   open iosApp/iosApp.xcodeproj
   ```

4. **Update Swift wrapper**:
   ```swift
   // Add this to HomeViewModelWrapper
   func deleteTrip(tripId: String) {
       sharedViewModel.deleteTrip(tripId: tripId)
   }
   ```

5. **Update SwiftUI view**:
   ```swift
   Button("Delete", role: .destructive) {
       viewModel.deleteTrip(tripId: trip.id)
   }
   ```

6. **Run** (Cmd+R)

---

## Xcode Build Errors

### Error: "No such module 'shared'"

**Cause**: The shared framework wasn't built or isn't linked.

**Fix**:
1. Build the shared framework:
   ```bash
   ./gradlew :shared:build
   ```

2. In Xcode, verify framework is linked:
   - Select project in navigator
   - Select **iosApp** target
   - Go to **Frameworks, Libraries, and Embedded Content**
   - Ensure `shared.framework` is listed

3. Clean build folder: **Product** → **Clean Build Folder** (Cmd+Shift+K)

4. Rebuild (Cmd+B)

---

### Error: "Cannot find 'KoinHelper' in scope"

**Cause**: The shared framework is outdated or KoinHelper wasn't added.

**Fix**:
1. Verify `KoinHelper.kt` exists in `shared/src/iosMain/kotlin/`

2. Rebuild:
   ```bash
   ./gradlew :shared:build
   ```

3. In Xcode: **Product** → **Clean Build Folder**

4. Rebuild

---

### Error: "Value of type 'HomeViewModel' has no member 'loadTrips'"

**Cause**: The Kotlin ViewModel doesn't have that function, or framework is outdated.

**Fix**:
1. Check the Kotlin ViewModel has the function:
   ```kotlin
   class HomeViewModel {
       fun loadTrips() { ... }  // Ensure this exists
   }
   ```

2. Rebuild framework:
   ```bash
   ./gradlew :shared:build
   ```

3. Clean and rebuild in Xcode

---

## Tips for Efficient Xcode Workflow

### 1. Use Xcode for UI Work Only

- Edit SwiftUI views
- Design layouts
- Test UI interactions
- Debug view hierarchy

**Don't use Xcode for**: Kotlin code (ViewModels, models, repositories)

---

### 2. Keep Shared Framework Up-to-Date

After any Kotlin changes:
```bash
./gradlew :shared:build
```

Consider creating an alias:
```bash
alias build-shared="cd /Users/sid/Projects/Android/Travlogue && ./gradlew :shared:build"
```

Then just run: `build-shared`

---

### 3. Use Xcode's Preview Canvas

SwiftUI previews let you see UI changes instantly:

```swift
struct HomeView: View {
    var body: some View {
        Text("Home")
    }
}

#Preview {
    HomeView()
}
```

Click **Resume** in the canvas (right panel) to see live preview.

**Note**: Previews work best for static UI. For ViewModels, run the full app.

---

### 4. Organize Views in Folders

In Xcode, create groups (folders) for organization:

```
Views/
├── Home/
│   ├── HomeView.swift
│   └── TripCard.swift
├── TripDetail/
│   ├── TripDetailViewEnhanced.swift
│   ├── OverviewTabView.swift
│   └── TimelineTabView.swift
└── Settings/
    └── SettingsView.swift
```

Right-click `Views/` → **New Group** → Name it → Drag files into it

---

### 5. Use Simulator Shortcuts

| Action                | Shortcut       |
|-----------------------|----------------|
| Toggle keyboard       | Cmd+K          |
| Rotate device         | Cmd+Arrow      |
| Shake device          | Ctrl+Cmd+Z     |
| Home button           | Cmd+Shift+H    |
| Screenshot            | Cmd+S          |

---

### 6. Version Control in Xcode

Xcode has built-in Git support:

- **View changes**: **Source Control** → **Commit**
- **Commit**: Select files → Write message → **Commit**
- **Push**: **Source Control** → **Push**

**Recommendation**: Use terminal or Android Studio for Git to stay consistent.

---

## When to Use Xcode vs Android Studio

| Task                          | Tool           |
|-------------------------------|----------------|
| Edit ViewModels               | Android Studio |
| Edit repositories             | Android Studio |
| Edit data models              | Android Studio |
| Edit Koin DI                  | Android Studio |
| Build shared framework        | Terminal       |
| Edit SwiftUI views            | Xcode          |
| Add new Swift files           | Xcode          |
| Run iOS app                   | Xcode          |
| Debug iOS UI                  | Xcode          |
| iOS-specific features (camera)| Xcode          |
| Git commits                   | Terminal/AS    |

---

## Summary

1. **Open Xcode** with: `open iosApp/iosApp.xcodeproj`
2. **Add Swift files** directly in Xcode (auto-updates project file)
3. **Use wrappers** to observe Kotlin StateFlows
4. **Rebuild shared framework** after Kotlin changes: `./gradlew :shared:build`
5. **Run and debug** from Xcode (Cmd+R)
6. **Keep UI work in Xcode**, business logic in Android Studio

Xcode is your tool for all iOS UI development. After the shared framework is built, you can work entirely in Xcode for SwiftUI screens without touching Kotlin code!
