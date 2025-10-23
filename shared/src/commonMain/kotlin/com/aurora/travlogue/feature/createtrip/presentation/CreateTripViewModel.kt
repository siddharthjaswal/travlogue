package com.aurora.travlogue.feature.createtrip.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aurora.travlogue.core.common.DateTimeUtils.toIsoString
import com.aurora.travlogue.core.data.repository.TripRepository
import com.aurora.travlogue.core.domain.model.DateType
import com.aurora.travlogue.core.domain.model.Trip
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate

/**
 * CreateTripViewModel - KMP version
 * Manages the create trip screen state
 */
class CreateTripViewModel(
    private val tripRepository: TripRepository
) : ViewModel() {

    // UI State
    private val _uiState = MutableStateFlow(CreateTripUiState())
    val uiState: StateFlow<CreateTripUiState> = _uiState.asStateFlow()

    // UI Events
    private val _uiEvents = MutableSharedFlow<CreateTripUiEvent>()
    val uiEvents = _uiEvents.asSharedFlow()

    // ==================== Public API ====================

    fun onTripNameChanged(name: String) {
        _uiState.update { it.copy(
            tripName = name,
            tripNameError = if (name.isBlank()) "Trip name is required" else null
        )}
    }

    fun onOriginCityChanged(city: String) {
        _uiState.update { it.copy(
            originCity = city,
            originCityError = if (city.isBlank()) "Origin city is required" else null
        )}
    }

    fun onDateTypeSelected(dateType: DateType) {
        _uiState.update { it.copy(
            selectedDateType = dateType,
            dateError = null
        )}
    }

    fun onStartDateSelected(date: LocalDate) {
        _uiState.update {
            val endDate = it.endDate
            it.copy(
                startDate = date,
                dateError = if (endDate != null && date > endDate) {
                    "Start date must be before end date"
                } else null
            )
        }
    }

    fun onEndDateSelected(date: LocalDate) {
        _uiState.update {
            val startDate = it.startDate
            it.copy(
                endDate = date,
                dateError = if (startDate != null && date < startDate) {
                    "End date must be after start date"
                } else null
            )
        }
    }

    fun onFlexibleMonthChanged(month: String) {
        _uiState.update { it.copy(
            flexibleMonth = month,
            dateError = if (month.isBlank()) "Month is required for flexible dates" else null
        )}
    }

    fun onFlexibleDurationChanged(duration: String) {
        _uiState.update { it.copy(
            flexibleDuration = duration.filter { char -> char.isDigit() }
        )}
    }

    fun onCreateTripClicked() {
        // Validate
        if (!validateForm()) return

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            try {
                val state = _uiState.value
                val trip = Trip(
                    name = state.tripName.trim(),
                    originCity = state.originCity.trim(),
                    dateType = state.selectedDateType,
                    startDate = state.startDate?.toIsoString(),
                    endDate = state.endDate?.toIsoString(),
                    flexibleMonth = if (state.selectedDateType == DateType.FLEXIBLE && state.flexibleMonth.isNotBlank())
                        state.flexibleMonth.trim() else null,
                    flexibleDuration = if (state.selectedDateType == DateType.FLEXIBLE && state.flexibleDuration.isNotBlank())
                        state.flexibleDuration.toIntOrNull() else null
                )

                tripRepository.insertTrip(trip)

                _uiState.update { it.copy(isLoading = false, isSuccess = true) }
                _uiEvents.emit(CreateTripUiEvent.TripCreatedSuccess)

            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = e.message ?: "Failed to create trip"
                    )
                }
                _uiEvents.emit(CreateTripUiEvent.ShowError(e.message ?: "Failed to create trip"))
            }
        }
    }

    fun onBackPressed() {
        viewModelScope.launch {
            _uiEvents.emit(CreateTripUiEvent.NavigateBack)
        }
    }

    // ==================== Private Methods ====================

    private fun validateForm(): Boolean {
        val state = _uiState.value
        var hasError = false

        // Validate trip name
        if (state.tripName.isBlank()) {
            _uiState.update { it.copy(tripNameError = "Trip name is required") }
            hasError = true
        }

        // Validate origin city
        if (state.originCity.isBlank()) {
            _uiState.update { it.copy(originCityError = "Origin city is required") }
            hasError = true
        }

        // Validate dates based on type
        when (state.selectedDateType) {
            DateType.FIXED -> {
                if (state.startDate == null || state.endDate == null) {
                    _uiState.update { it.copy(dateError = "Please select start and end dates") }
                    hasError = true
                } else if (state.endDate < state.startDate) {
                    _uiState.update { it.copy(dateError = "End date must be after start date") }
                    hasError = true
                }
            }
            DateType.FLEXIBLE -> {
                if (state.flexibleMonth.isBlank()) {
                    _uiState.update { it.copy(dateError = "Please enter a month") }
                    hasError = true
                }
            }
        }

        return !hasError
    }
}

/**
 * UI State for Create Trip screen
 */
data class CreateTripUiState(
    val tripName: String = "",
    val originCity: String = "",
    val selectedDateType: DateType = DateType.FIXED,
    val startDate: LocalDate? = null,
    val endDate: LocalDate? = null,
    val flexibleMonth: String = "",
    val flexibleDuration: String = "",
    val tripNameError: String? = null,
    val originCityError: String? = null,
    val dateError: String? = null,
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null
)

/**
 * UI Events for Create Trip screen
 */
sealed class CreateTripUiEvent {
    data object TripCreatedSuccess : CreateTripUiEvent()
    data object NavigateBack : CreateTripUiEvent()
    data class ShowError(val message: String) : CreateTripUiEvent()
}
