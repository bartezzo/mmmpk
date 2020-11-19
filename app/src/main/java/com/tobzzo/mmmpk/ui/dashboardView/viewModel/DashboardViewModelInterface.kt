package com.tobzzo.mmmpk.ui.dashboardView.viewModel

import androidx.lifecycle.LiveData
import com.tobzzo.mmmpk.helpers.ErrorMessage
import com.tobzzo.mmmpk.ui.dashboardView.model.DepartureDayEnum
import com.tobzzo.mmmpk.ui.dashboardView.model.DepartureModel

interface DashboardViewModelInterface {
    val departures: LiveData<List<DepartureModel>>
    val progress: LiveData<Boolean>
    val errors: LiveData<ErrorMessage>
    val departuresCount: Int

    fun getDeparturesData(day: DepartureDayEnum, hour: Int, minute: Int)
}