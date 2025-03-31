package com.aurora.data.plan

data class PlanData(
    var date: String?,
    var city: String?,
    var stay: StayData?,
    var transits: String?,
    var details: String?,
    var status: String?,
    var expensesTravel: Double?,
    var expensesStay: Double?,
    var otherExpenses: String?
)

data class StayData(
    var name: String?,
    var latitude: Double?,
    var longitude: Double?,
    var checkIn: Double?,
    var checkOut: Double?,
    var notes: String?,
    var price: Int?
)