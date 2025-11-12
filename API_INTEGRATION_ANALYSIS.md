# Logbook API Integration Analysis

**Existing Project**: Travlogue KMP (kmp-migration branch)
**Backend API**: Logbook FastAPI (https://api.travlogue.in)
**Analysis Date**: November 12, 2025

---

## Executive Summary

### ✅ **Good News:**
- Your KMP architecture is **excellent** and well-suited for API integration
- ~60-70% of your existing code can be **reused** or **enhanced**
- **NO major architectural rework needed** - just extend and connect!
- UI layer is **completely reusable** (Compose Multiplatform)

### ⚠️ **Required Changes:**
- Replace SQLDelight-only storage with **API + local cache**
- Extend data models to match backend schema (add ~20 fields)
- Add API client layer (Ktor - already in migration plan)
- Implement authentication (Google OAuth)
- Add sync logic for offline support

### 📊 **Estimated Rework: 30-40%**
- **Keep (60-70%)**: Architecture, UI, ViewModels structure, domain logic
- **Enhance (20-25%)**: Data models, add missing fields
- **New (10-15%)**: API layer, auth, sync service

---

## Data Model Comparison

### 1. Trip Model

| Field | Existing KMP | Backend API | Action |
|-------|-------------|-------------|---------|
| **id** | String (UUID) | Integer | ⚠️ **CHANGE**: Use backend's int ID |
| **name** | ✅ String | ✅ String | ✅ **KEEP** |
| **originCity** | ✅ String | ❌ Not in backend | ⚠️ **MAP**: Can use as note or remove |
| **dateType** | FIXED/FLEXIBLE | - | ⚠️ **DERIVE**: From dates_confirmed |
| **startDate** | String (ISO) | Integer (timestamp) | ⚠️ **CHANGE**: Use timestamps |
| **endDate** | String (ISO) | Integer (timestamp) | ⚠️ **CHANGE**: Use timestamps |
| **flexibleMonth** | String | Integer (month) | ⚠️ **CHANGE**: Use planned_start_month |
| **flexibleDuration** | Integer (days) | Integer (days) | ✅ **KEEP** |
| **createdAt** | Long (millis) | Timestamp | ✅ **KEEP** (compatible) |
| **updatedAt** | Long (millis) | Timestamp | ✅ **KEEP** (compatible) |
| - | ❌ Missing | **description** | 🆕 **ADD** |
| - | ❌ Missing | **cover_photo_url** | 🆕 **ADD** |
| - | ❌ Missing | **primary_destination_country** | 🆕 **ADD** |
| - | ❌ Missing | **primary_destination_city** | 🆕 **ADD** |
| - | ❌ Missing | **countries_visited** (array) | 🆕 **ADD** |
| - | ❌ Missing | **cities_visited** (array) | 🆕 **ADD** |
| - | ❌ Missing | **trip_type** (enum) | 🆕 **ADD** |
| - | ❌ Missing | **status** (enum) | 🆕 **ADD** |
| - | ❌ Missing | **visibility** (enum) | 🆕 **ADD** |
| - | ❌ Missing | **budget_total** | 🆕 **ADD** |
| - | ❌ Missing | **currency** | 🆕 **ADD** |
| - | ❌ Missing | **tags** (array) | 🆕 **ADD** |
| - | ❌ Missing | **created_by** (user_id) | 🆕 **ADD** |

**Impact**: Medium - Need to extend Trip model significantly

---

### 2. Location vs TripDay

Your **Location** model ≈ Backend's **TripDay** concept

| Existing Location | Backend TripDay | Match? |
|------------------|-----------------|---------|
| name | place | ✅ Similar |
| country | place_country | ✅ Match |
| date | date | ✅ Match |
| arrivalDateTime | - | ⚠️ Your model is more advanced! |
| departureDateTime | - | ⚠️ Your model is more advanced! |
| timezone | timezone | ✅ Match |

**Backend's TripDay has MORE:**
- day_number, day_type, title
- accommodation_name, accommodation_checkin
- transit_mode, transit_details
- activities (JSON), bookings (JSON)

**Impact**: Low - Your Location is simpler, backend is richer. Easy to extend.

---

### 3. Activity Model

| Field | Existing | Backend | Match? |
|-------|----------|---------|--------|
| id | String | Integer | ⚠️ Change |
| title | ✅ | name | ✅ Similar |
| description | ✅ | description | ✅ Match |
| date | ✅ | trip_day_id + date | ⚠️ Different approach |
| timeSlot | MORNING/AFTERNOON | - | ⚠️ Your feature |
| type | ATTRACTION/FOOD/etc | - | ⚠️ Your feature |
| - | ❌ | start_time_minutes | 🆕 ADD |
| - | ❌ | end_time_minutes | 🆕 ADD |
| - | ❌ | cost, currency | 🆕 ADD |
| - | ❌ | booking_url | 🆕 ADD |

**Impact**: Low-Medium - Models are similar, easy to merge

---

### 4. Booking Model

| Field | Existing | Backend | Match? |
|-------|----------|---------|--------|
| type | FLIGHT/HOTEL/etc | type (same enums!) | ✅ **EXCELLENT MATCH!** |
| confirmationNumber | ✅ | confirmation_number | ✅ Match |
| provider | ✅ | provider | ✅ Match |
| startDateTime | ISO 8601 | start_datetime (ISO) | ✅ Match |
| endDateTime | ISO 8601 | end_datetime (ISO) | ✅ Match |
| timezone | ✅ | timezone | ✅ Match |
| fromLocation | ✅ | from_location | ✅ Match |
| toLocation | ✅ | to_location | ✅ Match |
| price | ✅ | cost | ✅ Match (rename) |
| currency | ✅ | currency | ✅ Match |
| notes | ✅ | notes | ✅ Match |
| imageUri | ✅ | - | ⚠️ Your feature (local) |
| - | ❌ | trip_day_id | 🆕 ADD (FK) |

**Impact**: **VERY LOW** - Models are **almost identical**! 🎉

---

## Missing Backend Features in Your App

### Major Features (Not in KMP app yet):
1. **User Management** (Google OAuth authentication)
2. **Trip Collaboration** (trip members, invitations, roles)
3. **Activity Logs** (audit trail)
4. **Comments** (with @mentions)
5. **Accommodations** (dedicated entity)
6. **Transits** (dedicated entity)
7. **Expenses** (budget tracking, expense splitting)
8. **Trip Notes** (with colors, types, pinning)
9. **Packing Lists** (with items, categories)
10. **Checklists** (todo items with priorities)

---

## Architecture Comparison

### Your Current Architecture ✅
```
shared/
├── commonMain/
│   ├── domain/
│   │   ├── model/ ✅ Well-structured
│   │   ├── usecase/ ✅ Clean architecture
│   │   └── service/ ✅ Business logic
│   ├── data/
│   │   ├── local/ (SQLDelight) ✅ Local-first
│   │   ├── remote/ (TravlogueApiClient - placeholder) ⚠️ Not implemented
│   │   └── repository/ ✅ Repository pattern
│   └── feature/ ✅ Feature-based modules
```

### Recommended Enhanced Architecture 🚀
```
shared/
├── commonMain/
│   ├── domain/
│   │   ├── model/ ← Extend with backend fields
│   │   ├── usecase/ ← Keep as-is
│   │   └── service/ ← Keep + add sync service
│   ├── data/
│   │   ├── local/ (SQLDelight) ← Keep for offline cache
│   │   ├── remote/
│   │   │   ├── api/ ← NEW: Ktor client for Logbook API
│   │   │   ├── dto/ ← NEW: API response models
│   │   │   └── auth/ ← NEW: OAuth implementation
│   │   └── repository/
│   │       ├── TripRepository ← Enhance with API calls
│   │       └── sync/ ← NEW: Offline sync logic
│   └── feature/ ← Keep all ViewModels
```

---

## Integration Strategy

### Phase 1: Foundation (Week 1)
**Tasks:**
- ✅ Set up Ktor client pointing to `https://api.travlogue.in`
- ✅ Implement Google OAuth flow (already supported by backend)
- ✅ Create API DTOs matching backend schema
- ✅ Add auth token storage

**Files to Create:**
- `shared/src/commonMain/kotlin/core/data/remote/api/LogbookApi.kt`
- `shared/src/commonMain/kotlin/core/data/remote/dto/*.kt`
- `shared/src/commonMain/kotlin/core/data/remote/auth/AuthManager.kt`

**Keep:**
- All ViewModels (just update data layer calls)
- All UI screens (no changes needed)
- All domain models (just extend)

---

### Phase 2: Data Models (Week 2)
**Tasks:**
- Extend Trip model with backend fields
- Keep your current fields as "extras" (originCity, etc.)
- Add mappers between API DTOs ↔ Domain models ↔ SQLDelight entities
- Update repository to call API + cache locally

**Files to Modify:**
```kotlin
// Before
data class Trip(
    val id: String,
    val name: String,
    val originCity: String,
    val dateType: DateType,
    // ...
)

// After (Enhanced)
data class Trip(
    val id: Int, // Changed from String
    val name: String,
    val description: String? = null, // NEW
    val originCity: String? = null, // Keep as local-only field
    val dateType: DateType, // Derived from dates_confirmed
    val coverPhotoUrl: String? = null, // NEW
    val primaryDestinationCountry: String, // NEW
    val countriesVisited: List<String> = emptyList(), // NEW
    val tripType: TripType, // NEW enum
    val status: TripStatus, // NEW enum
    val visibility: TripVisibility, // NEW enum
    val budgetTotal: Double? = null, // NEW
    val currency: String, // NEW
    val tags: List<String> = emptyList(), // NEW
    val createdBy: Int, // NEW (user ID)
    // ... keep existing fields
)
```

---

### Phase 3: Repository Enhancement (Week 3)
**Tasks:**
- Update TripRepository to use API as source of truth
- Keep SQLDelight for offline cache
- Implement sync logic (pull from API, cache locally)

**Example:**
```kotlin
class TripRepository(
    private val logbookApi: LogbookApi, // NEW
    private val localDb: TravlogueDb, // EXISTING
    private val authManager: AuthManager // NEW
) {
    // Get trips from API, cache locally
    suspend fun getTrips(): List<Trip> {
        return try {
            val apiTrips = logbookApi.getTrips()
            localDb.tripQueries.insertAll(apiTrips) // Cache
            apiTrips
        } catch (e: Exception) {
            localDb.tripQueries.getAllTrips() // Fallback to cache
        }
    }
}
```

---

### Phase 4: Feature Parity (Weeks 4-6)
**Add backend features one by one:**
1. **Week 4**: Accommodations + Transits
2. **Week 5**: Expenses + Trip Notes
3. **Week 6**: Packing Lists + Checklists

**Strategy**:
- Backend models → DTO → Domain model → SQLDelight (cache) → UI
- Reuse your existing UI patterns for new features

---

## Rework Breakdown

### 🟢 **No Change (40%)**
- ✅ All UI screens (Compose Multiplatform)
- ✅ Navigation structure
- ✅ ViewModels structure (just update data sources)
- ✅ UseCase pattern
- ✅ Theme, design system
- ✅ Timeline visualization logic
- ✅ Gap detection service
- ✅ Booking sync service

### 🟡 **Enhance/Extend (40%)**
- ⚠️ Data models (add fields, keep existing)
- ⚠️ Repository (add API calls, keep SQLDelight)
- ⚠️ Add Ktor client
- ⚠️ Add auth layer
- ⚠️ Add DTOs for API

### 🔴 **New Implementation (20%)**
- 🆕 Google OAuth flow
- 🆕 Sync service (online/offline)
- 🆕 New features: Expenses, Notes, Packing, Checklists, Collaboration
- 🆕 API error handling
- 🆕 Token refresh logic

---

## Code Reusability Matrix

| Component | Reusable? | Modification Level |
|-----------|-----------|-------------------|
| **ViewModels** | ✅ 90% | Update data sources only |
| **UI Screens** | ✅ 100% | No changes (maybe add fields) |
| **Domain Models** | ✅ 70% | Extend with new fields |
| **Use Cases** | ✅ 85% | Minor updates |
| **Repository Interface** | ✅ 80% | Add API methods |
| **Services** | ✅ 90% | Keep gap detection, etc. |
| **SQLDelight Schema** | ✅ 60% | Extend tables |
| **Mappers** | ⚠️ 30% | Rewrite for API DTOs |
| **DI Setup** | ✅ 80% | Add API dependencies |
| **Remote Layer** | ❌ 0% | NEW (implement Ktor) |
| **Auth** | ❌ 0% | NEW (Google OAuth) |

---

## Technology Stack Compatibility

| Category | Your Plan | Backend Needs | Compatibility |
|----------|-----------|---------------|---------------|
| **Networking** | Ktor | REST API (FastAPI) | ✅ **Perfect match** |
| **Serialization** | kotlinx.serialization | JSON | ✅ **Perfect match** |
| **Database** | SQLDelight | - | ✅ **Keep for cache** |
| **DI** | Koin | - | ✅ **Perfect match** |
| **Auth** | Not yet | Google OAuth 2.0 | 🆕 **Add** |
| **Image Loading** | Kamel/Coil3 | URLs from API | ✅ **Compatible** |
| **Coroutines** | ✅ | ✅ | ✅ **Perfect match** |
| **Date/Time** | kotlinx-datetime | ISO 8601 timestamps | ✅ **Compatible** |

---

## Offline-First Strategy

Your app is **local-first** (SQLDelight), backend is **server-first**. Here's how to merge:

### Recommended Approach: **Online-First with Offline Fallback**

```kotlin
suspend fun getTrips(): Result<List<Trip>> {
    return try {
        // 1. Try API first (requires auth)
        val apiTrips = logbookApi.getTrips()

        // 2. Cache in SQLDelight
        localDb.tripQueries.clearAndInsertAll(apiTrips)

        // 3. Return API data
        Result.success(apiTrips)
    } catch (networkError: Exception) {
        // 4. Fallback to cache
        val cachedTrips = localDb.tripQueries.getAllTrips()
        if (cachedTrips.isNotEmpty()) {
            Result.success(cachedTrips)
        } else {
            Result.failure(networkError)
        }
    }
}
```

---

## Migration Recommendations

### ✅ **DO:**
1. **Keep your UI completely** - It's well-designed!
2. **Keep your architecture** - It's clean and KMP-ready
3. **Extend models gradually** - Don't break existing code
4. **Use backend as source of truth** - Cache locally
5. **Test offline scenarios** - Your offline-first approach is great

### ❌ **DON'T:**
1. **Rewrite ViewModels** - Just update data layer calls
2. **Change navigation** - Keep your existing flow
3. **Remove SQLDelight** - Keep for offline cache
4. **Break existing screens** - Enhance incrementally

---

## Timeline Estimate

| Phase | Duration | Effort | Outcome |
|-------|----------|--------|---------|
| **Setup API Client** | 1 week | Medium | Auth + Basic API calls working |
| **Model Migration** | 1 week | Medium | Models extended, mappers ready |
| **Repository Upgrade** | 1 week | Medium | API + cache working |
| **Feature Parity** | 3 weeks | High | All backend features in app |
| **Testing & Polish** | 2 weeks | Medium | Production-ready |
| **Total** | **8 weeks** | - | Full integration |

---

## Next Steps

### Immediate Actions:
1. **Generate API client** from FastAPI OpenAPI spec
2. **Set up Google OAuth** in Android/iOS
3. **Create DTO models** matching backend
4. **Update Trip model** with new fields
5. **Test API connection** with auth

### Decision Points:
- **Keep originCity?** (Your unique field) → Yes, as local-only
- **Use backend IDs?** → Yes, change String → Int
- **Offline editing?** → Yes, queue changes and sync
- **Photo uploads?** → Phase 2 (after basic features)

---

## Conclusion

### 🎯 **Final Assessment:**

**Rework Level**: **MEDIUM (30-40%)**

**Good News:**
- ✅ Your architecture is **excellent** for API integration
- ✅ Most UI code is **100% reusable**
- ✅ Your models are **surprisingly close** to backend (especially Booking!)
- ✅ Offline-first approach can be **preserved**

**Required Work:**
- Extend data models (~20-30 new fields across all models)
- Implement API layer with Ktor (~1000 LOC)
- Add Google OAuth (~500 LOC)
- Update repositories to use API + cache (~800 LOC)
- Add sync service (~400 LOC)

**Total New Code**: ~2,700 LOC (compared to your current ~14,800 LOC = **18% addition**)

### 🚀 **Recommendation:**

**PROCEED** with your KMP project! The integration is very feasible. Your architecture is solid, and the backend API aligns well with your vision.

**Start with**: Authentication → Trips → Activities/Bookings → New Features
