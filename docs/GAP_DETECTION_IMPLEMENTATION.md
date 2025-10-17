# Gap Detection Implementation - Phase 2 (v0.6.0)

**Date:** October 17, 2025
**Status:** 🚧 In Progress (Core Complete, UI Integration Pending)
**Feature:** Gap Detection Foundation

---

## 🎯 Overview

Gap Detection is Travlogue's **key differentiator** - automatically identifying missing transits and unplanned days in trip itineraries. This makes trip planning truly intelligent.

---

## ✅ Completed Components

### 1. Core Business Logic ✅

**File:** `core/domain/GapDetectionService.kt` (250+ lines)

**Features:**
- ✅ Detects MISSING_TRANSIT gaps (location changes without transit bookings)
- ✅ Detects UNPLANNED_DAY gaps (days with no activities or locations)
- ✅ Smart algorithm that compares consecutive locations
- ✅ Checks for transit bookings (FLIGHT, TRAIN, BUS)
- ✅ Handles date ranges and gap duration calculation
- ✅ Helper methods for gap descriptions

**Key Methods:**
```kotlin
fun detectGaps(trip: Trip, locations: List<Location>, bookings: List<Booking>): List<Gap>
fun getGapDurationDays(gap: Gap): Long
fun getGapDescription(gap: Gap, fromLocation: Location?, toLocation: Location?): String
```

**Algorithm Logic:**

**MISSING_TRANSIT Detection:**
1. Sort locations by order
2. Compare consecutive location names
3. Check if transit booking exists connecting them
4. Create gap if no transit found

**UNPLANNED_DAY Detection:**
1. Iterate through trip date range
2. Check if each day has a location assigned
3. Group consecutive unplanned days into gaps

### 2. Repository Layer ✅

**File:** `core/data/repository/TripRepository.kt`

**Added Methods:**
```kotlin
// Gap queries
fun getGapsForTrip(tripId: String): Flow<List<Gap>>
fun getUnresolvedGapsForTrip(tripId: String): Flow<List<Gap>>
suspend fun getGapsForTripSync(tripId: String): List<Gap>

// Gap mutations
suspend fun insertGap(gap: Gap)
suspend fun insertGaps(gaps: List<Gap>)
suspend fun updateGap(gap: Gap)
suspend fun deleteGap(gap: Gap)
suspend fun markGapAsResolved(gapId: String)
suspend fun deleteAllGapsForTrip(tripId: String)
```

**Integration:**
- ✅ Injected `GapDao` into repository
- ✅ Added gap CRUD operations
- ✅ Flow-based gap observation

### 3. ViewModel Integration ✅

**File:** `feature/tripdetail/presentation/TripDetailViewModel.kt`

**Added Features:**
- ✅ Injected `GapDetectionService`
- ✅ Loads gaps in `loadTripDetails()` flow
- ✅ Automatic gap detection when trip data changes
- ✅ Gap detail dialog management
- ✅ Mark gap as resolved
- ✅ Dismiss gap

**New Methods:**
```kotlin
fun showGapDetailDialog(gap: Gap)
fun hideGapDetailDialog()
fun markGapAsResolved(gapId: String)
fun deleteGap(gap: Gap)
fun detectAndUpdateGaps()
```

### 4. UI State ✅

**File:** `feature/tripdetail/presentation/TripDetailUiState.kt`

**Added Properties:**
```kotlin
val gaps: List<Gap> = emptyList()
val showGapDetailDialog: Boolean = false
val selectedGap: Gap? = null
val unresolvedGapCount: Int get() = gaps.count { !it.isResolved }
```

### 5. UI Components ✅

#### **GapCard Component** ✅
**File:** `feature/tripdetail/presentation/components/GapCard.kt`

**Features:**
- ✅ Full-size gap card with icon, title, description, duration
- ✅ Compact gap card for inline timeline display
- ✅ Type-specific icons (DirectionsBus, CalendarToday, Warning)
- ✅ Color-coded error theme (red/error container)
- ✅ Duration badge
- ✅ From → To location display
- ✅ Date range formatting

**Components:**
```kotlin
@Composable fun GapCard(gap, fromLocation, toLocation, onClick)
@Composable fun CompactGapCard(gap, fromLocation, toLocation, onClick)
```

#### **GapDetailSheet Component** ✅
**File:** `feature/tripdetail/presentation/components/GapDetailSheet.kt`

**Features:**
- ✅ Modal bottom sheet for gap details
- ✅ Header with icon and title
- ✅ Detailed description based on gap type
- ✅ Action buttons:
  - "Add Transit Booking" (for MISSING_TRANSIT)
  - "Add Activity" (for UNPLANNED_DAY)
  - "Mark Resolved"
  - "Dismiss"
- ✅ InfoRow for structured data display
- ✅ Helper functions for date formatting

**Components:**
```kotlin
@Composable fun GapDetailSheet(gap, fromLocation, toLocation, sheetState, actions)
```

---

## 🚧 Pending Work

### 1. UI Integration (Next Step)

**Need to integrate gaps into existing screens:**

#### Timeline Tab Integration
- [ ] Display gaps between days in timeline
- [ ] Use `CompactGapCard` for inline display
- [ ] Add tap handler to show `GapDetailSheet`
- [ ] Show unresolved gap count badge

**File to modify:** `feature/tripdetail/components/tabs/TimelineTab.kt`

#### Overview Tab Integration
- [ ] Add "Gaps" section showing unresolved gaps
- [ ] Display gap count in statistics
- [ ] Link to timeline when gap is clicked

**File to modify:** `feature/tripdetail/components/tabs/OverviewTab.kt`

#### TripDetailScreen Integration
- [ ] Add `GapDetailSheet` to screen
- [ ] Handle sheet state
- [ ] Wire up action callbacks:
  - onAddTransit → show AddBookingDialog with pre-filled data
  - onAddActivity → show AddActivityDialog with pre-filled date
  - onMarkResolved → call viewModel.markGapAsResolved()
  - onDismissGap → call viewModel.deleteGap()

**File to modify:** `feature/tripdetail/presentation/TripDetailScreen.kt`

### 2. Testing

**Unit Tests Needed:**
- [ ] `GapDetectionServiceTest.kt` - Test gap detection algorithms
- [ ] Test MISSING_TRANSIT detection
- [ ] Test UNPLANNED_DAY detection
- [ ] Test edge cases (same-city moves, single-day gaps, etc.)

**Integration Tests:**
- [ ] Test gap detection with real trip data
- [ ] Verify gaps update when locations/bookings change
- [ ] Test gap resolution flow

### 3. Future Enhancements

**v0.7.0 - Transit Suggestions:**
- [ ] Integrate Rome2Rio API
- [ ] Fetch transit options for MISSING_TRANSIT gaps
- [ ] Display transit suggestions in GapDetailSheet
- [ ] One-click add transit from suggestions

**v0.8.0 - Enhanced Detection:**
- [ ] TIME_CONFLICT detection (overlapping activities)
- [ ] Activity time validation
- [ ] Booking time conflict detection

---

## 📊 Database Schema

**Gap Entity** (Already exists - No changes needed)

```kotlin
@Entity(tableName = "gaps")
data class Gap(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val tripId: String,
    val gapType: GapType,
    val fromLocationId: String,
    val toLocationId: String,
    val fromDate: String, // ISO format: yyyy-MM-dd
    val toDate: String,
    val isResolved: Boolean = false
)

enum class GapType {
    MISSING_TRANSIT,
    UNPLANNED_DAY,
    TIME_CONFLICT
}
```

**Foreign Keys:**
- ✅ `tripId` → Trip (CASCADE DELETE)
- ✅ `fromLocationId` → Location (CASCADE DELETE)
- ✅ `toLocationId` → Location (CASCADE DELETE)

**GapDao** (Already exists - No changes needed)
- ✅ Full CRUD operations
- ✅ Query by tripId (Flow and sync)
- ✅ Filter unresolved gaps
- ✅ Mark as resolved

---

## 🏗️ Architecture

```
┌─────────────────────────────────────────────────────┐
│                   TripDetailScreen                  │
│  (Shows GapCard in timeline, GapDetailSheet)        │
└────────────────────┬────────────────────────────────┘
                     │
                     ▼
┌─────────────────────────────────────────────────────┐
│              TripDetailViewModel                    │
│  - Loads gaps                                       │
│  - Triggers gap detection                           │
│  - Manages gap actions                              │
└────────────────────┬────────────────────────────────┘
                     │
      ┌──────────────┴──────────────┐
      ▼                             ▼
┌──────────────────┐    ┌──────────────────────────┐
│  TripRepository  │    │  GapDetectionService     │
│  - Gap CRUD      │    │  - detectGaps()          │
│  - Gap queries   │    │  - Algorithm logic       │
└────────┬─────────┘    └──────────────────────────┘
         │
         ▼
┌──────────────────┐
│     GapDao       │
│  - Database ops  │
└──────────────────┘
```

---

## 🎨 UI Flow

**1. Gap Detection (Automatic)**
```
User adds/updates location or booking
        ↓
ViewModel.detectAndUpdateGaps() triggered
        ↓
GapDetectionService.detectGaps() runs
        ↓
Gaps saved to database via Repository
        ↓
UI automatically updates via Flow
```

**2. User Views Gap**
```
User taps gap card in timeline
        ↓
GapDetailSheet opens
        ↓
Shows gap details, duration, locations
        ↓
User can take action
```

**3. User Resolves Gap**
```
User taps "Add Transit" or "Add Activity"
        ↓
Relevant dialog opens with pre-filled data
        ↓
User completes booking/activity
        ↓
Gap automatically re-detected
        ↓
Gap removed if resolved
```

---

## 💡 Key Design Decisions

1. **Automatic Detection**: Gaps are automatically detected whenever trip data changes. No manual "detect gaps" button needed.

2. **Non-Intrusive**: Gaps don't block the user. They're informative and actionable, but can be dismissed or marked as resolved.

3. **Smart Matching**: MISSING_TRANSIT uses fuzzy location name matching in booking from/to fields. "San Francisco (SFO)" matches "San Francisco".

4. **FIXED Dates Only**: Gap detection currently only works for trips with FIXED dates. FLEXIBLE date trips are skipped.

5. **Cascade Delete**: Gaps are automatically deleted when parent trip or locations are deleted.

6. **Flow-Based**: All gap data uses Kotlin Flow for reactive UI updates.

---

## 📈 Implementation Progress

| Component | Status | Lines of Code |
|-----------|--------|---------------|
| GapDetectionService | ✅ Complete | 250+ |
| TripRepository updates | ✅ Complete | 50+ |
| TripDetailViewModel updates | ✅ Complete | 80+ |
| TripDetailUiState updates | ✅ Complete | 20+ |
| GapCard component | ✅ Complete | 250+ |
| GapDetailSheet component | ✅ Complete | 350+ |
| Timeline Tab integration | 🚧 Pending | - |
| TripDetailScreen integration | 🚧 Pending | - |
| Unit tests | 🚧 Pending | - |
| **Total** | **75% Complete** | **1,000+** |

---

## 🧪 Testing Strategy

### Manual Testing Scenarios

**Scenario 1: Missing Transit**
1. Create trip: San Francisco → Tokyo → Kyoto
2. Add locations for each city
3. Don't add transit booking between SF and Tokyo
4. ✅ Gap should appear: "Missing transit from San Francisco to Tokyo"

**Scenario 2: Unplanned Days**
1. Create 7-day trip
2. Add location for Day 1 (Tokyo)
3. Add location for Day 5 (Kyoto)
4. Leave Days 2-4 unplanned
5. ✅ Gap should appear: "3 unplanned days"

**Scenario 3: Gap Resolution**
1. Tap gap card
2. Tap "Add Transit Booking"
3. Add flight booking
4. ✅ Gap should disappear automatically

**Scenario 4: Same City Movement**
1. Create trip with Tokyo (Day 1) → Tokyo (Day 3)
2. ✅ No gap should appear (same city)

---

## 🚀 Next Steps

**Immediate (This Session):**
1. ✅ Create core gap detection logic
2. ✅ Update repository and ViewModel
3. ✅ Create UI components
4. ⏳ Integrate into Timeline tab
5. ⏳ Test with sample data

**Short-term (v0.6.0 completion):**
- Integrate gaps into all relevant screens
- Write comprehensive tests
- Polish UI/UX
- Add user preferences (hide dismissed gaps, etc.)

**Medium-term (v0.7.0):**
- Rome2Rio API integration
- Transit suggestions
- One-click add transit

**Long-term (v0.8.0+):**
- TIME_CONFLICT detection
- AI-powered gap recommendations
- Weather-aware gap suggestions

---

## 📝 Files Created/Modified

### Created Files (4)
1. ✅ `core/domain/GapDetectionService.kt`
2. ✅ `feature/tripdetail/presentation/components/GapCard.kt`
3. ✅ `feature/tripdetail/presentation/components/GapDetailSheet.kt`
4. ✅ `docs/GAP_DETECTION_IMPLEMENTATION.md` (this file)

### Modified Files (3)
1. ✅ `core/data/repository/TripRepository.kt` - Added gap operations
2. ✅ `feature/tripdetail/presentation/TripDetailViewModel.kt` - Added gap detection
3. ✅ `feature/tripdetail/presentation/TripDetailUiState.kt` - Added gap state

### Existing Files (Used, not modified)
- ✅ `core/data/local/entities/Gap.kt` - Gap entity
- ✅ `core/data/local/dao/GapDao.kt` - Gap DAO

---

**Status:** Core gap detection complete! Ready for UI integration and testing.

**Phase 2 Progress:** Gap Detection Foundation - 75% Complete

*Last Updated: October 17, 2025*
