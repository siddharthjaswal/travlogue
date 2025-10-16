# Travlogue 🌍

**Intelligent Travel Planning for Android**

An Android app that consolidates trip planning, detects gaps in your itinerary, and suggests smart solutions with real pricing and timing.

---

## 🚀 Quick Links

- 📋 **[Product Requirements (PRD)](docs/PRD.md)** - Full product vision and features
- 🏗️ **[Architecture Guide](docs/ARCHITECTURE.md)** - Technical architecture and best practices
- ⚡ **[Developer Quick Start](docs/QuickStart.md)** - Get started in minutes
- 📝 **[Changelog](docs/CHANGELOG.md)** - Version history and progress

---

## 📊 Current Status

**Version:** 0.2.0 (MVP Phase 1 - In Progress)
**Last Updated:** January 2025

### ✅ Completed
- ✅ Feature-First Clean Architecture
- ✅ Room database (6 entities)
- ✅ Hilt dependency injection
- ✅ Home Screen with trip management
- ✅ Material 3 design system
- ✅ Comprehensive DateTimeUtils
- ✅ Full preview system

### 🚧 In Progress
- 🚧 Trip detail screen
- 🚧 Itinerary builder

### ⏳ Next Up
- Gap detection (key feature!)
- API integrations (Weather, Places)
- Transit suggestions

---

## 🛠️ Tech Stack

- **Language:** Kotlin
- **Architecture:** Feature-First Clean Architecture (MVVM)
- **Database:** Room 2.8.2
- **DI:** Hilt
- **UI:** Jetpack Compose + Material 3
- **Build:** Gradle Kotlin DSL + Version Catalog
- **Async:** Coroutines + Flow

---

## 🎯 Key Features

### Current
- 📝 Create trips (Fixed or Flexible dates)
- 📋 View all trips
- 🗑️ Delete trips
- 🎨 Beautiful Material 3 UI
- 💾 Offline-first with Room

### Planned
- ⭐ **Intelligent Gap Detection** - Identify missing transits and unplanned days
- 🚆 Transit Suggestions - Train, flight, bus options with pricing
- ☀️ Weather Integration - Best time to visit recommendations
- 🗺️ Attractions Discovery - Top things to do at each location
- ✈️ Flight Price Monitoring - Track prices from origin city
- 📱 Complete Offline Support - Access everything without internet

---

## 🏗️ Project Structure

```
app/src/main/java/com/aurora/travlogue/
├── core/
│   ├── data/               # Database, repositories
│   ├── common/             # Utilities (DateTimeUtils, etc.)
│   └── design/             # Material 3 theme
├── feature/
│   └── home/               # Home feature
│       ├── presentation/   # Screens, ViewModels
│       ├── domain/         # Business logic
│       └── components/     # UI components
├── di/                     # Hilt modules
└── navigation/             # App navigation
```

---

## 🚀 Getting Started

### Prerequisites
- Android Studio Hedgehog (2023.1.1) or later
- JDK 17
- Android SDK 26+

### Setup
1. Clone the repository
2. Open in Android Studio
3. Sync Gradle
4. Run on emulator or device

### First Steps
1. Read **[Quick Start Guide](docs/QuickStart.md)** for detailed setup
2. Review **[Architecture Guide](docs/ARCHITECTURE.md)** to understand structure
3. Check **[PRD](docs/PRD.md)** for feature roadmap

---

## 📖 Documentation

All documentation is in the [`docs/`](docs/) folder:

| Document | Description |
|----------|-------------|
| [PRD.md](docs/PRD.md) | Product vision, requirements, and roadmap |
| [ARCHITECTURE.md](docs/ARCHITECTURE.md) | Technical architecture and best practices |
| [QuickStart.md](docs/QuickStart.md) | Developer onboarding and setup guide |
| [CHANGELOG.md](docs/CHANGELOG.md) | Version history and progress tracking |

---

## 🎨 Key Differentiator

**Intelligent Gap Detection** - The app automatically identifies:
- 🚆 Location jumps without transit plans
- 📅 Unplanned days between locations
- ⏰ Time conflicts in bookings

Then suggests smart solutions:
- 🚄 Train options (price, duration, provider)
- ✈️ Flight options (price, duration, airline)
- 🚌 Bus/road options

---

## 🗺️ Development Roadmap

### Phase 1: MVP Foundation (Current)
- [x] Architecture and database ✅
- [x] Home screen ✅
- [ ] Trip details 🚧
- [ ] Itinerary builder ⏳
- [ ] Gap detection ⏳

### Phase 2: Intelligence
- [ ] API integrations (Weather, Places)
- [ ] Transit suggestions
- [ ] Flight price monitoring
- [ ] AI recommendations

### Phase 3: Polish
- [ ] Booking management with images
- [ ] Enhanced gap detection
- [ ] Offline optimization
- [ ] UI/UX refinements

---

## 📝 Recent Updates

### v0.2.0 - Home Screen Complete
- Created complete Home Screen with Material 3
- Implemented trip creation (Fixed & Flexible dates)
- Built reusable UI components (TripCard, TripList, etc.)
- Added comprehensive preview system
- Created TripMockData for testing

### v0.1.0 - Foundation
- Set up Feature-First Clean Architecture
- Implemented all 6 core entities
- Created repositories and DAOs
- Set up Hilt dependency injection
- Built DateTimeUtils with 30+ helpers

See [CHANGELOG.md](docs/CHANGELOG.md) for full history.

---

## 🤝 Contributing

This is currently a personal project, but contributions are welcome!

1. Check the [PRD](docs/PRD.md) for planned features
2. Review the [Architecture Guide](docs/ARCHITECTURE.md)
3. Follow the existing code structure
4. Add @Preview annotations to all composables
5. Update documentation for significant changes

---

## 📄 License

This project is for personal use and learning purposes.

---

## 👤 Author

**Sid**

- Building in public
- Learning Android development
- Solving real travel planning problems

---

## 🙏 Acknowledgments

- Built with Claude Code
- Inspired by personal travel planning pain points
- Powered by modern Android technologies

---

**⭐ Star this repo if you find it helpful!**
