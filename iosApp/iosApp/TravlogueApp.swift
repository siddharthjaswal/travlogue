import SwiftUI
import Shared

@main
struct TravlogueApp: App {

    init() {
        // Initialize Koin for dependency injection
        KoinHelper.companion.doInitKoin()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
