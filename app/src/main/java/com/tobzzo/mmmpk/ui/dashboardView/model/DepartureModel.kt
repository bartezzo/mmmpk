package com.tobzzo.mmmpk.ui.dashboardView.model

data class DepartureModel(
    val line: String,
    val station: String,
    val direction: String,
    val day:DepartureDayEnum,
    val hour:Int,
    val minute:Int
)