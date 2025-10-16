package com.aurora.travlogue.feature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aurora.travlogue.core.data.local.entities.DateType
import com.aurora.travlogue.core.data.local.entities.Trip
import com.aurora.travlogue.core.data.repository.TripRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val tripRepository: TripRepository
) : ViewModel() {

    // All trips as StateFlow for Compose
    val allTrips: StateFlow<List<Trip>> = tripRepository.allTrips
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    // Dialog state
    private val _showCreateDialog = MutableStateFlow(false)
    val showCreateDialog: StateFlow<Boolean> = _showCreateDialog.asStateFlow()

    // Loading state
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    fun showCreateDialog() {
        _showCreateDialog.value = true
    }

    fun hideCreateDialog() {
        _showCreateDialog.value = false
    }

    fun createTrip(
        name: String,
        originCity: String,
        dateType: DateType,
        startDate: String?,
        endDate: String?,
        flexibleMonth: String?,
        flexibleDuration: Int?
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val trip = Trip(
                    name = name,
                    originCity = originCity,
                    dateType = dateType,
                    startDate = startDate,
                    endDate = endDate,
                    flexibleMonth = flexibleMonth,
                    flexibleDuration = flexibleDuration
                )
                tripRepository.insertTrip(trip)
                hideCreateDialog()
            } catch (e: Exception) {
                // Handle error (could add error state here)
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun deleteTrip(trip: Trip) {
        viewModelScope.launch {
            tripRepository.deleteTrip(trip)
        }
    }
}