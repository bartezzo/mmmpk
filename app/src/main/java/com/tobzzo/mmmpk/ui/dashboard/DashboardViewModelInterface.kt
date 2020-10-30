package com.tobzzo.mmmpk.ui.dashboard

import androidx.lifecycle.LiveData
import com.tobzzo.mmmpk.helpers.ErrorMessage
import com.tobzzo.mmmpk.ui.dashboard.model.DepartureModel

interface DashboardViewModelInterface {
    val departures: LiveData<List<DepartureModel>>
    val progress: LiveData<Boolean>
    val errors: LiveData<ErrorMessage>
    val departuresCount: Int

    fun getDeparturesData()
}