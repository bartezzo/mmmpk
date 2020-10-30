package com.tobzzo.mmmpk.network

import com.tobzzo.mmmpk.ui.dashboardView.model.DepartureModel
import io.reactivex.Observable

interface ApiServiceInterface {
    fun getAllDepartures(): Observable<List<DepartureModel>>
}