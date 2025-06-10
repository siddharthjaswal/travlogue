package com.aurora.data.data.entity.trip

const val INIT_TRIP_NAME = "Initialize Trip ✨"

const val PROMPT_AWAITING_DESTINATION = "Focus on suggesting or deciding a travel destination."
const val PROMPT_AWAITING_START_DATE = "The destination is known. Focus on travel dates, specifically the start date."
const val PROMPT_AWAITING_END_DATE = "Destination and start date are set. Focus on the end date or trip duration."
const val PROMPT_PLANNING_COMPLETE = "The main trip details are set. Focus on activities, itinerary, or local tips."

fun createNewTripEntity(name: String): TripEntity {
    return TripEntity(
        name = name,
        lastUpdated = System.currentTimeMillis()
    )
}

fun getTripPlanningStage(tripEntity: TripEntity?): TripPlanningStage {
    if(tripEntity == null) return TripPlanningStage.STAGE_AWAITING_DESTINATION
    return when {
        tripEntity.name.isBlank() || tripEntity.name == INIT_TRIP_NAME -> TripPlanningStage.STAGE_AWAITING_DESTINATION
        tripEntity.startDate == null -> TripPlanningStage.STAGE_AWAITING_START_DATE
        tripEntity.endDate == null -> TripPlanningStage.STAGE_AWAITING_END_DATE
        else -> TripPlanningStage.STAGE_PLANNING_COMPLETE
    }
}

enum class TripPlanningStage {
    STAGE_AWAITING_DESTINATION,
    STAGE_AWAITING_START_DATE,
    STAGE_AWAITING_END_DATE,
    STAGE_PLANNING_COMPLETE
}
