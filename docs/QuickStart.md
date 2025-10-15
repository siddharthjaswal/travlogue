# Travlogue - Developer Quick Start Guide

## 🚀 Getting Started in 5 Steps

### Step 1: Project Setup (Day 1)

**Create Android Project:**
```
- Name: Travlogue
- Package: com.aurora.travlogue
- Language: Kotlin
- Minimum SDK: API 26 (Android 8.0)
- Build System: Gradle (Kotlin DSL)
```

**Initial Dependencies** (add to `build.gradle.kts`):
```kotlin
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp") version "1.9.20-1.0.14" // for Room
}

dependencies {
    // Core Android
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    
    // Room Database
    val roomVersion = "2.6.0"
    implementation("androidx.room:room-runtime:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")
    ksp("androidx.room:room-compiler:$roomVersion")
    
    // Retrofit for API calls
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
    
    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    
    // ViewModel & LiveData
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    
    // Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.5")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.5")
    
    // Optional: Jetpack Compose (if you prefer)
    // implementation("androidx.compose.ui:ui:1.5.4")
    // implementation("androidx.compose.material3:material3:1.1.2")
}
```

---

### Step 2: Create Data Layer (Days 2-3)

**File: `data/local/entities/Trip.kt`**
```kotlin
package com.aurora.travlogue.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.util.UUID

@Entity(tableName = "trips")
data class Trip(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val name: String,
    val originCity: String,
    val dateType: DateType,
    val startDate: String?, // Store as ISO string
    val endDate: String?,
    val flexibleMonth: String?,
    val flexibleDuration: Int?,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)

enum class DateType {
    FIXED,
    FLEXIBLE
}
```

**File: `data/local/dao/TripDao.kt`**
```kotlin
package com.aurora.travlogue.data.local.dao

import androidx.room.*
import com.aurora.travlogue.data.local.entities.Trip
import kotlinx.coroutines.flow.Flow

@Dao
interface TripDao {
    @Query("SELECT * FROM trips ORDER BY createdAt DESC")
    fun getAllTrips(): Flow<List<Trip>>
    
    @Query("SELECT * FROM trips WHERE id = :tripId")
    suspend fun getTripById(tripId: String): Trip?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrip(trip: Trip)
    
    @Update
    suspend fun updateTrip(trip: Trip)
    
    @Delete
    suspend fun deleteTrip(trip: Trip)
}
```

**File: `data/local/TravlogueDatabase.kt`**
```kotlin
package com.aurora.travlogue.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aurora.travlogue.data.local.dao.TripDao
import com.aurora.travlogue.data.local.entities.Trip

@Database(
    entities = [Trip::class], 
    version = 1,
    exportSchema = false
)
abstract class TravlogueDatabase : RoomDatabase() {
    abstract fun tripDao(): TripDao
    
    companion object {
        @Volatile
        private var INSTANCE: TravlogueDatabase? = null
        
        fun getDatabase(context: Context): TravlogueDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TravlogueDatabase::class.java,
                    "travlogue_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
```

**File: `data/repository/TripRepository.kt`**
```kotlin
package com.aurora.travlogue.data.repository

import com.aurora.travlogue.data.local.dao.TripDao
import com.aurora.travlogue.data.local.entities.Trip
import kotlinx.coroutines.flow.Flow

class TripRepository(private val tripDao: TripDao) {
    
    val allTrips: Flow<List<Trip>> = tripDao.getAllTrips()
    
    suspend fun getTripById(id: String): Trip? {
        return tripDao.getTripById(id)
    }
    
    suspend fun insertTrip(trip: Trip) {
        tripDao.insertTrip(trip)
    }
    
    suspend fun updateTrip(trip: Trip) {
        tripDao.updateTrip(trip)
    }
    
    suspend fun deleteTrip(trip: Trip) {
        tripDao.deleteTrip(trip)
    }
}
```

---

### Step 3: Create ViewModel (Day 4)

**File: `ui/viewmodel/TripViewModel.kt`**
```kotlin
package com.aurora.travlogue.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.aurora.travlogue.data.local.entities.Trip
import com.aurora.travlogue.data.repository.TripRepository
import kotlinx.coroutines.launch

class TripViewModel(private val repository: TripRepository) : ViewModel() {
    
    val allTrips = repository.allTrips.asLiveData()
    
    fun insertTrip(trip: Trip) = viewModelScope.launch {
        repository.insertTrip(trip)
    }
    
    fun updateTrip(trip: Trip) = viewModelScope.launch {
        repository.updateTrip(trip)
    }
    
    fun deleteTrip(trip: Trip) = viewModelScope.launch {
        repository.deleteTrip(trip)
    }
}

class TripViewModelFactory(private val repository: TripRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TripViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TripViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
```

---

### Step 4: Create Basic UI (Days 5-6)

**File: `ui/MainActivity.kt`** (starter)
```kotlin
package com.aurora.travlogue.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.aurora.travlogue.data.local.TravlogueDatabase
import com.aurora.travlogue.data.repository.TripRepository
import com.aurora.travlogue.databinding.ActivityMainBinding
import com.aurora.travlogue.ui.viewmodel.TripViewModel
import com.aurora.travlogue.ui.viewmodel.TripViewModelFactory

class MainActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityMainBinding
    private lateinit var tripViewModel: TripViewModel
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        // Initialize ViewModel
        val database = TravlogueDatabase.getDatabase(applicationContext)
        val repository = TripRepository(database.tripDao())
        val viewModelFactory = TripViewModelFactory(repository)
        tripViewModel = ViewModelProvider(this, viewModelFactory)[TripViewModel::class.java]
        
        // Observe trips
        tripViewModel.allTrips.observe(this) { trips ->
            // Update UI with trip list
        }
    }
}
```

---

### Step 5: Implement Gap Detection Logic (Week 2)

**File: `domain/usecase/DetectGapsUseCase.kt`**
```kotlin
package com.aurora.travlogue.domain.usecase

import com.aurora.travlogue.data.local.entities.Location
import com.aurora.travlogue.data.local.entities.Gap
import com.aurora.travlogue.data.local.entities.GapType
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class DetectGapsUseCase {
    
    /**
     * Detects gaps in the itinerary
     * Returns list of gaps found
     */
    fun execute(locations: List<Location>): List<Gap> {
        val gaps = mutableListOf<Gap>()
        
        // Sort locations by date
        val sortedLocations = locations
            .filter { it.date != null }
            .sortedBy { it.date }
        
        // Check for location jumps without transit
        for (i in 0 until sortedLocations.size - 1) {
            val current = sortedLocations[i]
            val next = sortedLocations[i + 1]
            
            if (current.name != next.name && 
                current.date != null && 
                next.date != null) {
                
                val daysBetween = ChronoUnit.DAYS.between(
                    LocalDate.parse(current.date), 
                    LocalDate.parse(next.date)
                )
                
                // If consecutive days or same day, missing transit
                if (daysBetween <= 1) {
                    gaps.add(Gap(
                        tripId = current.tripId,
                        gapType = GapType.MISSING_TRANSIT,
                        fromLocationId = current.id,
                        toLocationId = next.id,
                        fromDate = LocalDate.parse(current.date),
                        toDate = LocalDate.parse(next.date)
                    ))
                }
                
                // If multiple days between, unplanned days
                if (daysBetween > 1) {
                    gaps.add(Gap(
                        tripId = current.tripId,
                        gapType = GapType.UNPLANNED_DAY,
                        fromLocationId = current.id,
                        toLocationId = next.id,
                        fromDate = LocalDate.parse(current.date),
                        toDate = LocalDate.parse(next.date)
                    ))
                }
            }
        }
        
        return gaps
    }
}
```

---

## 📋 Development Checklist

### Week 1: Foundation
- [ ] Create Android project with Kotlin
- [ ] Add all dependencies
- [ ] Create Trip entity
- [ ] Create TripDao
- [ ] Create TravlogueDatabase
- [ ] Create TripRepository
- [ ] Create TripViewModel
- [ ] Test: Can create and save a trip

### Week 2: Basic UI
- [ ] Create MainActivity layout
- [ ] Create TripListFragment
- [ ] Create CreateTripFragment
- [ ] Implement trip list RecyclerView
- [ ] Implement trip creation form
- [ ] Test: Can view and create trips in UI

### Week 3: Expand Data Models
- [ ] Create Location entity and DAO
- [ ] Create Activity entity and DAO
- [ ] Create Booking entity and DAO
- [ ] Create Gap entity and DAO
- [ ] Update database version
- [ ] Test: All entities working

### Week 4: Gap Detection
- [ ] Implement DetectGapsUseCase
- [ ] Create GapListFragment
- [ ] Display detected gaps in UI
- [ ] Test: Gaps detected correctly

### Week 5: First API Integration
- [ ] Get OpenWeatherMap API key
- [ ] Create Retrofit service for weather
- [ ] Create WeatherRepository
- [ ] Add weather display to location
- [ ] Test: Weather data fetched

---

## 🔑 API Keys Setup

Create `local.properties` file (not in version control):
```properties
WEATHER_API_KEY=your_openweathermap_key
PLACES_API_KEY=your_google_places_key
AMADEUS_API_KEY=your_amadeus_key
AMADEUS_API_SECRET=your_amadeus_secret
```

Access in `build.gradle.kts`:
```kotlin
val localProperties = Properties()
val localPropertiesFile = rootProject.file("local.properties")
if (localPropertiesFile.exists()) {
    localPropertiesFile.inputStream().use { localProperties.load(it) }
}

android {
    defaultConfig {
        buildConfigField("String", "WEATHER_API_KEY", 
            "\"${localProperties.getProperty("WEATHER_API_KEY", "")}\"")
    }
}
```

---

## 🐛 Common Issues & Solutions

### Issue: Room @Database error
**Solution:** Make sure you have KSP plugin and annotationProcessor configured correctly

### Issue: Date/Time parsing errors
**Solution:** Use ISO 8601 format (yyyy-MM-dd) for date strings

### Issue: API calls blocking UI
**Solution:** Always use coroutines and call from ViewModel scope

### Issue: Database not persisting
**Solution:** Check you're using applicationContext, not activity context

---

## 📚 Useful Resources

- [Room Documentation](https://developer.android.com/training/data-storage/room)
- [Kotlin Coroutines Guide](https://kotlinlang.org/docs/coroutines-guide.html)
- [MVVM Architecture](https://developer.android.com/topic/architecture)
- [Retrofit Documentation](https://square.github.io/retrofit/)

---

## 🎯 Next Conversation Starters

When you're ready to continue development, share:
1. This PRD markdown
2. The Quick Start Guide
3. What you've built so far
4. What you're stuck on or want to build next

Claude will remember the context and help you continue!