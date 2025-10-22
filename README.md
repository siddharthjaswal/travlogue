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

**Version:** 0.10.0 (UX Polish & Date Intelligence)
**Last Updated:** January 2025

### ✅ Completed (Phase 1 MVP + Phase 2 Intelligence + UX Polish)
- ✅ Feature-First Clean Architecture
- ✅ Room database (6 entities) with migrations
- ✅ Hilt dependency injection
- ✅ Home Screen with trip management
- ✅ Trip Detail Screen (Timeline, Locations, Bookings, Overview)
- ✅ Full CRUD operations (Create, Read, Update, Delete)
- ✅ Material 3 design system with Compose previews
- ✅ Comprehensive DateTimeUtils
- ✅ Gap Detection (MISSING_TRANSIT, UNPLANNED_DAY)
- ✅ Timezone Support for Locations
- ✅ Automatic Booking Sync (BookingSyncService)
- ✅ Origin Departure Cards (departures from home city)
- ✅ Transit Cards with Timezone Transitions
- ✅ Activity Time Validation (prevent scheduling conflicts)
- ✅ Feature-organized dialog components
- ✅ Empty State CTAs (guided user actions)
- ✅ Date Range Constraints (trip date validation)
- ✅ Smart Default Dates (trip start date defaulting)

### 🚧 In Progress (Phase 2)
- 🚧 Transit option suggestions (API integration)

### ⏳ Next Up
- API integrations (Weather, Places, Transit)
- Flight price monitoring
- AI-powered recommendations

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

### Current (v0.10.0)
- 📝 Create trips (Fixed or Flexible dates)
- 📋 View all trips with timeline visualization
- ✏️ Full CRUD for activities, locations, and bookings
- ⭐ **Intelligent Gap Detection** - Identifies missing transits and unplanned days
- 🌍 **Timezone-Aware Locations** - Track arrival/departure times with timezone transitions
- 🔄 **Automatic Booking Sync** - Times sync between bookings and locations
- ✈️ **Complete Journey Flow** - Origin departures, in-transit status, arrivals
- ⏰ **Activity Validation** - Prevents scheduling conflicts
- 📅 **Smart Date Constraints** - Date pickers constrained to trip date range
- 🎯 **Empty State Guidance** - Clear CTAs guide users to add content
- 🎨 Beautiful Material 3 UI with comprehensive previews
- 💾 Offline-first with Room

### Planned
- 🚆 Transit Suggestions - Train, flight, bus options with pricing (API integration)
- ☀️ Weather Integration - Best time to visit recommendations
- 🗺️ Attractions Discovery - Top things to do at each location
- ✈️ Flight Price Monitoring - Track prices from origin city
- 🤖 AI-powered recommendations
- 📱 Enhanced Offline Support - Cached maps and data

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

### v0.10.0 - UX Polish & Date Intelligence ⭐ **NEW**
- Empty state CTAs (action buttons guide users)
- Date range constraints (prevents selecting dates outside trip range)
- Smart default dates (date pickers default to trip start date)
- Improved loading states with descriptive messages
- Enhanced user guidance across all dialogs
- Complete documentation for date constraints

### v0.9.0 - Timeline Enhancements
- Origin departure cards (departures from home city)
- Transit cards with timezone transition indicators
- Activity time validation (prevent scheduling conflicts)
- Feature-organized dialog structure (activity/, booking/, location/)
- Delete buttons moved to top app bar
- Comprehensive Compose previews
- Timeline sorting bug fixes

### v0.8.0 - Timezone Support & Booking Sync
- Added timezone awareness to locations
- Implemented BookingSyncService for automatic time synchronization
- LocationCard shows arrival/departure times with visual icons
- Smart location name matching for transit bookings
- Database migration (v2→v3) with data preservation

### v0.6.0 - Gap Detection (Key Differentiator!)
- Intelligent gap detection (MISSING_TRANSIT, UNPLANNED_DAY)
- Beautiful gap UI components with Material 3
- Timeline and Overview tab integration

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
