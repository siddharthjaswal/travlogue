package com.aurora.data.plan

data class PlanData(
    val date: String?,
    val city: String?,
    val stay: String?,
    val transits: String?,
    val details: String?,
    val status: String?,
    val expensesTravel: Double?,
    val expensesStay: Double?,
    val otherExpenses: String?
)