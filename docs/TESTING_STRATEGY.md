# Travlogue - Testing Strategy & Implementation Plan

**Version:** 1.0
**Date:** January 17, 2025
**Status:** Phase 1 MVP Complete - Testing Phase Begins

---

## 1. Executive Summary

This document outlines a comprehensive testing strategy for Travlogue, covering unit tests, integration tests, UI tests, and end-to-end tests. The strategy is designed to ensure code quality, prevent regressions, and build confidence in the application's reliability.

### Testing Goals
1. ✅ **Reliability** - Ensure all features work as expected
2. ✅ **Regression Prevention** - Catch bugs before they reach production
3. ✅ **Code Quality** - Enforce best practices through testability
4. ✅ **Documentation** - Tests serve as living documentation
5. ✅ **Confidence** - Enable safe refactoring and feature additions

---

## 2. Testing Pyramid Strategy

We follow the standard testing pyramid approach:

```
          /\
         /  \        E2E Tests (5%)
        /____\       - Complete user flows
       /      \      - Critical paths only
      /  UI    \     UI Tests (15%)
     /  Tests   \    - Screen interactions
    /____________\   - Navigation flows
   /              \
  /  Integration   \ Integration Tests (30%)
 /     Tests        \- Repository + DAO
/____________________\- ViewModel + Repository

======================== Unit Tests (50%)
- ViewModels          - Most tests here
- Repositories        - Fast execution
- Utils & Extensions  - High coverage
- Domain Logic        - Isolated
========================
```

### Test Distribution Target
- **Unit Tests:** 50% of total tests (~100-150 tests)
- **Integration Tests:** 30% (~60-90 tests)
- **UI Tests:** 15% (~30-45 tests)
- **E2E Tests:** 5% (~10-15 tests)

---

## 3. Technology Stack

### Testing Libraries

```kotlin
// Unit Testing
testImplementation("junit:junit:4.13.2")
testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
testImplementation("app.cash.turbine:turbine:1.0.0") // Flow testing
testImplementation("io.mockk:mockk:1.13.8") // Mocking
testImplementation("com.google.truth:truth:1.1.5") // Assertions

// Android Testing
androidTestImplementation("androidx.test.ext:junit:1.1.5")
androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
androidTestImplementation("androidx.compose.ui:ui-test-junit4")

// Room Testing
androidTestImplementation("androidx.room:room-testing:2.8.2")

// Hilt Testing
androidTestImplementation("com.google.dagger:hilt-android-testing:2.51.1")
kaptAndroidTest("com.google.dagger:hilt-android-compiler:2.51.1")

// Navigation Testing
androidTestImplementation("androidx.navigation:navigation-testing:2.8.5")

// Debug Tools
debugImplementation("androidx.compose.ui:ui-test-manifest")
```

### Test Rules & Utilities
- **MainDispatcherRule** - For coroutine testing
- **HiltAndroidRule** - For dependency injection in tests
- **ComposeTestRule** - For Compose UI testing
- **TestInstantTaskExecutorRule** - For LiveData (if used)

---

## 4. Phase-by-Phase Implementation Plan

### Phase 1: Foundation & Setup (Week 1)
**Goal:** Set up testing infrastructure and utilities

#### Tasks
1. **Add Testing Dependencies**
   - Add all testing libraries to `build.gradle.kts`
   - Configure test source sets
   - Add JaCoCo for code coverage

2. **Create Test Utilities**
   - `TestDispatchers.kt` - Coroutine test dispatchers
   - `TestData.kt` - Mock data for tests
   - `FakeRepository.kt` - Fake implementations
   - `MainDispatcherRule.kt` - Coroutine test rule

3. **Setup Test Structure**
   ```
   app/src/
   ├── test/                           # Unit tests
   │   └── java/com/aurora/travlogue/
   │       ├── core/
   │       │   ├── data/repository/
   │       │   └── common/
   │       └── feature/
   │           ├── home/
   │           ├── createtrip/
   │           └── tripdetail/
   │
   └── androidTest/                    # Instrumented tests
       └── java/com/aurora/travlogue/
           ├── data/local/dao/         # DAO tests
           ├── di/                     # DI tests
           └── feature/                # UI tests
   ```

4. **Configure Coverage Reports**
   - JaCoCo configuration
   - Coverage thresholds (target: 70%+)
   - HTML reports generation

**Deliverables:**
- ✅ All dependencies added
- ✅ Test utility classes created
- ✅ Test folder structure established
- ✅ Coverage reporting configured

---

### Phase 2: Core Layer Tests (Week 2)
**Goal:** Test utility classes, repositories, and data layer

#### 2.1 Utility Tests (5-10 tests)

**DateTimeUtils Tests**
- `DateTimeUtilsTest.kt`
  - ✅ ISO string to LocalDate conversion
  - ✅ LocalDate to display string formatting
  - ✅ Date arithmetic (add days, days between)
  - ✅ ISO 8601 datetime parsing
  - ✅ Timezone handling
  - ✅ Invalid date handling
  - ✅ Edge cases (leap years, DST)

**Example:**
```kotlin
@Test
fun `formatDateForDisplay returns correct format`() {
    // Given
    val isoDate = "2025-11-15"

    // When
    val result = DateTimeUtils.formatDateForDisplay(isoDate)

    // Then
    assertThat(result).isEqualTo("Nov 15, 2025")
}
```

#### 2.2 Repository Tests (30-40 tests)

**TripRepository Tests**
- `TripRepositoryTest.kt`
  - ✅ Insert trip successfully
  - ✅ Get trip by ID returns correct trip
  - ✅ Get all trips returns all trips
  - ✅ Update trip modifies existing trip
  - ✅ Delete trip removes trip
  - ✅ Get locations for trip
  - ✅ Get activities for trip
  - ✅ Get bookings for trip
  - ✅ Flow emissions on data changes

**Example:**
```kotlin
@Test
fun `getTripById returns correct trip`() = runTest {
    // Given
    val trip = TripMockData.fixedDateTrip
    repository.insertTrip(trip)

    // When
    val result = repository.getTripById(trip.id).first()

    // Then
    assertThat(result).isEqualTo(trip)
}
```

**LocationRepository Tests**
**ActivityRepository Tests**
**BookingRepository Tests**

#### 2.3 DAO Tests (20-30 tests)

**Note:** These are instrumented tests (androidTest)

**TripDao Tests**
- `TripDaoTest.kt`
  - ✅ Insert and retrieve trip
  - ✅ Update trip fields
  - ✅ Delete trip cascades to locations
  - ✅ Get all trips ordered by date
  - ✅ Search trips by name
  - ✅ Flow emits on insert/update/delete

**ActivityDao Tests**
- `ActivityDaoTest.kt`
  - ✅ Insert activity
  - ✅ Get activities by trip ID (JOIN query)
  - ✅ Get activities by location
  - ✅ Update activity
  - ✅ Delete activity by ID
  - ✅ Foreign key constraints work

**Example:**
```kotlin
@Test
fun getActivitiesByTripId_returnsCorrectActivities() = runBlocking {
    // Given
    val trip = createTestTrip()
    val location = createTestLocation(trip.id)
    val activity = createTestActivity(location.id)

    tripDao.insertTrip(trip)
    locationDao.insertLocation(location)
    activityDao.insertActivity(activity)

    // When
    val activities = activityDao.getActivitiesByTripId(trip.id).first()

    // Then
    assertThat(activities).hasSize(1)
    assertThat(activities[0].id).isEqualTo(activity.id)
}
```

**Deliverables:**
- ✅ All utility classes tested
- ✅ All repositories have test coverage
- ✅ All DAOs tested with Room in-memory database
- ✅ Coverage: 80%+ for core layer

---

### Phase 3: ViewModel Tests (Week 3)
**Goal:** Test presentation layer logic and state management

#### 3.1 HomeViewModel Tests (15-20 tests)

**HomeViewModelTest.kt**
- ✅ Initial state is loading
- ✅ Load trips populates state with trips
- ✅ Load trips handles empty list
- ✅ Load trips handles error
- ✅ Create trip adds trip to repository
- ✅ Create trip emits success event
- ✅ Create trip with invalid data emits error
- ✅ Delete trip removes from repository
- ✅ Delete trip updates state
- ✅ Retry after error reloads trips
- ✅ Dialog state management

**Example:**
```kotlin
@Test
fun `loadTrips populates state with trips`() = runTest {
    // Given
    val mockTrips = listOf(
        TripMockData.fixedDateTrip,
        TripMockData.flexibleDateTrip
    )
    fakeRepository.setTrips(mockTrips)

    val viewModel = HomeViewModel(fakeRepository)

    // When
    advanceUntilIdle()

    // Then
    val state = viewModel.uiState.value
    assertThat(state.trips).hasSize(2)
    assertThat(state.isLoading).isFalse()
    assertThat(state.error).isNull()
}
```

#### 3.2 CreateTripViewModel Tests (10-15 tests)

**CreateTripViewModelTest.kt**
- ✅ Initial state has empty fields
- ✅ Update trip name updates state
- ✅ Select fixed date type updates state
- ✅ Select dates validates range
- ✅ Create trip with valid data succeeds
- ✅ Create trip with empty name fails
- ✅ Create trip with invalid dates fails
- ✅ Form validation works correctly
- ✅ Navigation event emitted on success
- ✅ Error event emitted on failure

#### 3.3 TripDetailViewModel Tests (30-40 tests)

**TripDetailViewModelTest.kt**

**Loading & Display:**
- ✅ Initial state is loading
- ✅ Load trip details populates state
- ✅ Load handles trip not found
- ✅ Day schedule generation works for fixed dates
- ✅ Day schedule generation works for flexible dates
- ✅ Activities grouped by time slot correctly
- ✅ Tab selection updates state
- ✅ Day expansion toggles correctly

**CRUD Operations:**
- ✅ Add activity inserts to repository
- ✅ Add location with auto-order works
- ✅ Add booking stores correctly
- ✅ Update activity modifies existing
- ✅ Update location modifies existing
- ✅ Update booking modifies existing
- ✅ Delete activity removes from repository
- ✅ Delete location triggers cascade
- ✅ Delete booking removes correctly

**Dialog Management:**
- ✅ Show add activity dialog updates state
- ✅ Hide add activity dialog clears state
- ✅ Show edit activity dialog sets editing entity
- ✅ Hide edit activity dialog clears editing entity
- ✅ Dialog pre-selection works correctly

**Events:**
- ✅ Add success emits snackbar event
- ✅ Add error emits error event
- ✅ Delete success emits snackbar event
- ✅ Update success emits snackbar event

**Example:**
```kotlin
@Test
fun `addActivity inserts activity and emits success`() = runTest {
    // Given
    val viewModel = TripDetailViewModel(fakeRepository, savedStateHandle)
    val events = mutableListOf<TripDetailUiEvent>()

    backgroundScope.launch {
        viewModel.uiEvents.toList(events)
    }

    // When
    viewModel.addActivity(
        locationId = "loc-1",
        title = "Visit Museum",
        description = "Art museum",
        date = "2025-11-15",
        timeSlot = TimeSlot.MORNING,
        type = ActivityType.ATTRACTION
    )
    advanceUntilIdle()

    // Then
    val activities = fakeRepository.getActivitiesForTrip("trip-1").first()
    assertThat(activities).hasSize(1)
    assertThat(events).contains(
        TripDetailUiEvent.ShowSnackbar("Activity added successfully")
    )
}
```

**Deliverables:**
- ✅ All ViewModels tested comprehensively
- ✅ State transitions verified
- ✅ Event emissions tested
- ✅ Error handling covered
- ✅ Coverage: 85%+ for ViewModels

---

### Phase 4: Integration Tests (Week 4)
**Goal:** Test component interactions

#### 4.1 Repository + DAO Integration Tests (15-20 tests)

**TripRepositoryIntegrationTest.kt**
- ✅ Insert trip, then retrieve
- ✅ Insert trip with locations
- ✅ Delete trip cascades to all entities
- ✅ Update trip and verify changes
- ✅ Flow updates on data changes
- ✅ Complex queries work correctly

**Example:**
```kotlin
@Test
fun deleteTripCascadesToAllEntities() = runBlocking {
    // Given
    val trip = insertTestTrip()
    val location = insertTestLocation(trip.id)
    val activity = insertTestActivity(location.id)
    val booking = insertTestBooking(trip.id)

    // When
    repository.deleteTrip(trip)

    // Then
    assertThat(repository.getTripById(trip.id).first()).isNull()
    assertThat(repository.getLocationsForTrip(trip.id).first()).isEmpty()
    assertThat(repository.getActivitiesForTrip(trip.id).first()).isEmpty()
    assertThat(repository.getBookingsForTrip(trip.id).first()).isEmpty()
}
```

#### 4.2 ViewModel + Repository Integration Tests (10-15 tests)

**TripDetailViewModelIntegrationTest.kt**
- ✅ Create activity updates UI state via Flow
- ✅ Delete location removes activities from UI
- ✅ Update booking reflects in state immediately
- ✅ Multiple rapid updates handled correctly
- ✅ Error in repository propagates to UI

#### 4.3 Navigation Tests (5-10 tests)

**NavigationTest.kt**
- ✅ Navigate from Home to TripDetail
- ✅ Navigate from TripDetail back to Home
- ✅ Navigate with parameters works
- ✅ Deep links work correctly
- ✅ Back stack management

**Deliverables:**
- ✅ Critical integration paths tested
- ✅ Data flow verified end-to-end
- ✅ Edge cases covered
- ✅ Coverage: 75%+ for integration scenarios

---

### Phase 5: UI Tests (Week 5)
**Goal:** Test user interface and interactions

#### 5.1 Compose UI Tests (20-30 tests)

**HomeScreenTest.kt**
```kotlin
@Test
fun homeScreen_displaysTrips() {
    composeTestRule.setContent {
        HomeScreen(
            uiState = HomeUiState(
                trips = listOf(TripMockData.fixedDateTrip)
            ),
            onTripClick = {},
            onCreateTrip = {},
            onDeleteTrip = {}
        )
    }

    composeTestRule
        .onNodeWithText("Spain Adventure")
        .assertIsDisplayed()
}

@Test
fun homeScreen_clickCreateTrip_opensDialog() {
    composeTestRule.setContent {
        val viewModel = HomeViewModel(fakeRepository)
        HomeScreen(viewModel = viewModel)
    }

    composeTestRule
        .onNodeWithContentDescription("Create Trip")
        .performClick()

    composeTestRule
        .onNodeWithText("Create New Trip")
        .assertIsDisplayed()
}
```

**TripDetailScreenTest.kt**
- ✅ Displays trip header correctly
- ✅ Tab navigation works
- ✅ Day card expansion works
- ✅ FAB changes based on tab
- ✅ Add dialog opens on FAB click
- ✅ Edit dialog opens on item click
- ✅ Delete confirmation shows
- ✅ Snackbar appears on success

**AddActivityDialogTest.kt**
- ✅ Form fields render correctly
- ✅ Validation errors show
- ✅ Save button disabled when invalid
- ✅ Save button enabled when valid
- ✅ Form submission works
- ✅ Cancel closes dialog

**EditActivityDialogTest.kt**
- ✅ Fields pre-populated correctly
- ✅ Delete button visible
- ✅ Delete confirmation shows
- ✅ Update saves changes
- ✅ Cancel discards changes

#### 5.2 Screenshot Tests (Optional)

**ScreenshotTest.kt**
- ✅ Home screen - empty state
- ✅ Home screen - with trips
- ✅ Trip detail - timeline
- ✅ Trip detail - locations
- ✅ Add activity dialog
- ✅ Edit booking dialog

**Deliverables:**
- ✅ All major screens tested
- ✅ User interactions verified
- ✅ Accessibility labels checked
- ✅ Coverage: 70%+ for UI components

---

### Phase 6: End-to-End Tests (Week 6)
**Goal:** Test complete user journeys

#### E2E Test Scenarios (10-15 tests)

**CompleteUserJourneyTest.kt**

**Journey 1: Create Trip and Add Activities**
```kotlin
@Test
fun createTripAndAddActivities_completesSuccessfully() {
    // 1. Launch app
    // 2. Tap "Create Trip"
    // 3. Fill trip details
    // 4. Save trip
    // 5. Navigate to trip detail
    // 6. Tap FAB
    // 7. Add location
    // 8. Add activity
    // 9. Verify activity appears in timeline
}
```

**Journey 2: Edit and Delete Flow**
```kotlin
@Test
fun editAndDeleteActivity_worksCorrectly() {
    // 1. Navigate to trip with activity
    // 2. Tap activity
    // 3. Edit dialog opens
    // 4. Change title
    // 5. Save
    // 6. Verify change reflected
    // 7. Tap activity again
    // 8. Tap delete
    // 9. Confirm deletion
    // 10. Verify activity removed
}
```

**Journey 3: Multi-Day Trip Planning**
**Journey 4: Booking Management**
**Journey 5: Error Recovery**

**Deliverables:**
- ✅ Critical user paths tested
- ✅ Happy paths verified
- ✅ Error scenarios covered
- ✅ Smoke tests for releases

---

## 5. Test Implementation Guidelines

### 5.1 Naming Conventions

**Unit Tests:**
```kotlin
// Pattern: methodName_condition_expectedResult
@Test
fun getTripById_whenTripExists_returnTrip()

// Pattern: should_expectedBehavior_when_condition
@Test
fun should_emitSuccessEvent_when_activityCreatedSuccessfully()

// Pattern: given_when_then (using backticks)
@Test
fun `given valid trip data when creating trip then inserts successfully`()
```

**UI Tests:**
```kotlin
// Pattern: screenName_action_expectedResult
@Test
fun homeScreen_clickFAB_opensCreateDialog()

// Pattern: feature_scenario
@Test
fun tripDetail_expandDayCard_showsActivities()
```

### 5.2 Test Structure (AAA Pattern)

```kotlin
@Test
fun testName() {
    // Arrange (Given)
    val input = setupTestData()
    val expected = expectedResult()

    // Act (When)
    val result = systemUnderTest.method(input)

    // Assert (Then)
    assertThat(result).isEqualTo(expected)
}
```

### 5.3 Coroutine Testing

```kotlin
class MyViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun testSuspendFunction() = runTest {
        // Test code with coroutines
        advanceUntilIdle() // Wait for all coroutines
    }
}
```

### 5.4 Flow Testing with Turbine

```kotlin
@Test
fun uiState_emitsCorrectValues() = runTest {
    viewModel.uiState.test {
        // Initial state
        assertThat(awaitItem().isLoading).isTrue()

        // After loading
        assertThat(awaitItem().trips).hasSize(3)

        cancelAndIgnoreRemainingEvents()
    }
}
```

### 5.5 Fake Implementations

```kotlin
class FakeTripRepository : TripRepository {
    private val trips = mutableListOf<Trip>()
    private val _tripsFlow = MutableStateFlow<List<Trip>>(emptyList())

    override suspend fun insertTrip(trip: Trip) {
        trips.add(trip)
        _tripsFlow.value = trips.toList()
    }

    override fun getAllTrips(): Flow<List<Trip>> = _tripsFlow

    // ... other methods
}
```

---

## 6. Code Coverage Targets

### Target Coverage by Layer

| Layer | Target | Priority |
|-------|--------|----------|
| **Utils** | 90%+ | High |
| **Repositories** | 85%+ | High |
| **ViewModels** | 85%+ | High |
| **DAOs** | 80%+ | High |
| **UI Components** | 70%+ | Medium |
| **Dialogs** | 65%+ | Medium |
| **Navigation** | 60%+ | Low |

### Overall Target
- **Phase 1 Goal:** 75% overall coverage
- **Phase 2 Goal:** 80% overall coverage
- **Production Goal:** 85% overall coverage

---

## 7. Continuous Integration

### CI Pipeline (GitHub Actions / Jenkins)

```yaml
name: Run Tests

on: [push, pull_request]

jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3

      - name: Set up JDK
        uses: actions/setup-java@v3
        with:
          java-version: '17'

      - name: Run Unit Tests
        run: ./gradlew test

      - name: Run Instrumented Tests
        run: ./gradlew connectedAndroidTest

      - name: Generate Coverage Report
        run: ./gradlew jacocoTestReport

      - name: Upload Coverage
        uses: codecov/codecov-action@v3
        with:
          file: ./build/reports/jacoco/test/jacocoTestReport.xml
```

### Quality Gates
- ✅ All tests must pass
- ✅ Coverage must not decrease
- ✅ No critical bugs from static analysis
- ✅ Build must succeed

---

## 8. Test Data Management

### 8.1 Mock Data Strategy

**Create Centralized Test Data:**

```kotlin
// test/java/com/aurora/travlogue/testutil/TestData.kt
object TestData {
    val fixedDateTrip = Trip(
        id = "test-trip-1",
        name = "Test Spain Trip",
        originCity = "New York",
        dateType = DateType.FIXED,
        startDate = "2025-11-15",
        endDate = "2025-11-25"
    )

    val testLocation = Location(
        id = "test-loc-1",
        tripId = "test-trip-1",
        name = "Barcelona",
        country = "Spain",
        date = "2025-11-15",
        order = 1
    )

    val testActivity = Activity(
        id = "test-act-1",
        locationId = "test-loc-1",
        title = "Visit Sagrada Familia",
        description = "Beautiful architecture",
        date = "2025-11-15",
        timeSlot = TimeSlot.MORNING,
        type = ActivityType.ATTRACTION
    )

    // Helper functions
    fun createTrip(
        id: String = UUID.randomUUID().toString(),
        name: String = "Test Trip",
        // ... other parameters with defaults
    ): Trip = Trip(id, name, ...)
}
```

### 8.2 Test Database

**For DAO tests, use in-memory database:**

```kotlin
@RunWith(AndroidJUnit4::class)
class TripDaoTest {
    private lateinit var database: TravlogueDatabase
    private lateinit var tripDao: TripDao

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            context,
            TravlogueDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()

        tripDao = database.tripDao()
    }

    @After
    fun tearDown() {
        database.close()
    }
}
```

---

## 9. Testing Best Practices

### DO ✅
- Write tests before fixing bugs (TDD for bug fixes)
- Test behavior, not implementation
- Use meaningful test names
- Keep tests independent and isolated
- Use fake/mock objects appropriately
- Test edge cases and error conditions
- Keep tests fast and focused
- Use Given-When-Then structure
- Clean up resources in @After/@AfterEach

### DON'T ❌
- Test private methods directly
- Depend on test execution order
- Use real network or database in unit tests
- Copy-paste test code
- Write tests that depend on timing
- Test framework code (e.g., Room, Hilt)
- Ignore flaky tests
- Over-mock (prefer fakes for complex dependencies)

---

## 10. Implementation Timeline

### 6-Week Testing Sprint

| Week | Phase | Focus | Deliverables |
|------|-------|-------|--------------|
| **Week 1** | Foundation | Setup & Infrastructure | Dependencies, utilities, structure |
| **Week 2** | Core Tests | Utils, Repos, DAOs | 50-80 tests, 80% core coverage |
| **Week 3** | ViewModel Tests | Presentation Logic | 55-75 tests, 85% ViewModel coverage |
| **Week 4** | Integration | Component Interaction | 30-45 tests, critical paths covered |
| **Week 5** | UI Tests | Compose & Dialogs | 20-30 tests, 70% UI coverage |
| **Week 6** | E2E & Polish | User Journeys | 10-15 tests, CI setup, documentation |

### Milestones
- ✅ **Week 2 End:** Core layer fully tested
- ✅ **Week 3 End:** Presentation layer fully tested
- ✅ **Week 4 End:** Integration scenarios covered
- ✅ **Week 6 End:** 75%+ overall coverage, CI running

---

## 11. Priority Test List (Start Here)

### High Priority (Week 1-2)
1. **DateTimeUtils** - Used everywhere
2. **TripRepository** - Core data access
3. **ActivityDao** - Complex JOIN queries
4. **HomeViewModel** - Entry point
5. **TripDetailViewModel** - Most complex logic

### Medium Priority (Week 3-4)
6. **CreateTripViewModel**
7. **LocationRepository**
8. **BookingRepository**
9. **Repository + DAO integration**
10. **ViewModel + Repository integration**

### Lower Priority (Week 5-6)
11. **UI component tests**
12. **Dialog tests**
13. **Navigation tests**
14. **E2E user journeys**
15. **Screenshot tests**

---

## 12. Example Test Files Structure

```
app/src/
├── test/java/com/aurora/travlogue/
│   ├── testutil/
│   │   ├── MainDispatcherRule.kt
│   │   ├── TestData.kt
│   │   └── FakeRepositories.kt
│   │
│   ├── core/
│   │   ├── common/
│   │   │   └── DateTimeUtilsTest.kt
│   │   └── data/repository/
│   │       ├── TripRepositoryTest.kt
│   │       ├── LocationRepositoryTest.kt
│   │       ├── ActivityRepositoryTest.kt
│   │       └── BookingRepositoryTest.kt
│   │
│   └── feature/
│       ├── home/
│       │   └── presentation/
│       │       └── HomeViewModelTest.kt
│       │
│       ├── createtrip/
│       │   └── presentation/
│       │       └── CreateTripViewModelTest.kt
│       │
│       └── tripdetail/
│           ├── presentation/
│           │   └── TripDetailViewModelTest.kt
│           └── domain/
│               └── DayScheduleGenerationTest.kt
│
└── androidTest/java/com/aurora/travlogue/
    ├── testutil/
    │   └── HiltTestRunner.kt
    │
    ├── data/local/dao/
    │   ├── TripDaoTest.kt
    │   ├── LocationDaoTest.kt
    │   ├── ActivityDaoTest.kt
    │   └── BookingDaoTest.kt
    │
    ├── integration/
    │   ├── TripRepositoryIntegrationTest.kt
    │   └── TripDetailFlowIntegrationTest.kt
    │
    └── ui/
        ├── HomeScreenTest.kt
        ├── TripDetailScreenTest.kt
        ├── AddActivityDialogTest.kt
        ├── EditActivityDialogTest.kt
        └── CompleteUserJourneyTest.kt
```

---

## 13. Next Steps

### Immediate Actions
1. ✅ **Review and approve this testing strategy**
2. ✅ **Add testing dependencies to build.gradle.kts**
3. ✅ **Create test utility classes**
4. ✅ **Set up test folder structure**
5. ✅ **Write first test (DateTimeUtilsTest)**

### Week 1 Goals
- Complete testing infrastructure setup
- Write 10-15 utility and repository tests
- Establish patterns and conventions
- Configure JaCoCo coverage reporting

---

## 14. Success Metrics

### Quantitative
- ✅ **75%+ overall code coverage**
- ✅ **85%+ ViewModel coverage**
- ✅ **80%+ Repository coverage**
- ✅ **All critical paths have E2E tests**
- ✅ **CI pipeline green**
- ✅ **Test execution < 5 minutes (unit tests)**

### Qualitative
- ✅ **Team confidence in refactoring**
- ✅ **Bugs caught before production**
- ✅ **Tests serve as documentation**
- ✅ **Easy to add new tests**
- ✅ **Fast feedback loop**

---

## Appendix

### A. Useful Resources
- [Android Testing Codelab](https://developer.android.com/codelabs/advanced-android-kotlin-training-testing-basics)
- [Testing Compose](https://developer.android.com/jetpack/compose/testing)
- [Turbine (Flow Testing)](https://github.com/cashapp/turbine)
- [Truth Assertions](https://truth.dev/)
- [MockK](https://mockk.io/)

### B. Common Testing Patterns
See examples throughout this document

### C. Troubleshooting Guide
- **Flaky tests:** Use `runTest` with proper dispatcher handling
- **Database tests fail:** Ensure in-memory DB and proper cleanup
- **UI tests timeout:** Increase idle timeout or use explicit waits
- **Coverage not accurate:** Check JaCoCo exclusions

---

**Document Owner:** Sid
**Last Updated:** January 17, 2025
**Status:** Ready for Implementation
**Next Review:** After Week 3 (adjust strategy if needed)
