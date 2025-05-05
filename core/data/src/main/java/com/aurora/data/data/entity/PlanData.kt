package com.aurora.data.data.entity

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