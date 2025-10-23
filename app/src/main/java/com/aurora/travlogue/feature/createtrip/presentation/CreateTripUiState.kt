package com.aurora.travlogue.feature.createtrip.presentation

import com.aurora.travlogue.core.domain.model.DateType
import kotlinx.datetime.LocalDate

/**
 * UI State for Create Trip Screen
 */
data class CreateTripUiState(
    // Trip basic info
    val tripName: String = "",
    val originCity: String = "",

    // Date type selection
    val selectedDateType: DateType = DateType.FIXED,

    // Fixed date fields
    val startDate: LocalDate? = null,
    val endDate: LocalDate? = null,

    // Flexible date fields
    val flexibleMonth: String = "",
    val flexibleDuration: String = "",

    // Validation
    val tripNameError: String? = null,
    val originCityError: String? = null,
    val dateError: String? = null,

    // Loading/Success states
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null
) {
    /**
     * Check if the form is valid and ready to submit
     */
    val isValid: Boolean
        get() = tripName.isNotBlank() &&
                originCity.isNotBlank() &&
                when (selectedDateType) {
                    DateType.FIXED -> startDate != null && endDate != null &&
                                     (endDate == null || endDate >= startDate!!)
                    DateType.FLEXIBLE -> flexibleMonth.isNotBlank()
                }
}

/**
 * UI Events for Create Trip Screen
 * One-time events that trigger actions
 */
sealed interface CreateTripUiEvent {
    data object NavigateBack : CreateTripUiEvent
    data object TripCreatedSuccess : CreateTripUiEvent
    data class ShowError(val message: String) : CreateTripUiEvent
}
