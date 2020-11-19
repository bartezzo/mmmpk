package com.tobzzo.mmmpk.network

import com.tobzzo.mmmpk.ui.dashboardView.model.DepartureDayEnum
import com.tobzzo.mmmpk.ui.dashboardView.model.DepartureModel
import io.reactivex.Observable

interface ApiServiceInterface {
    fun getAllDepartures(day: DepartureDayEnum, hour: Int, minute: Int): Observable<List<DepartureModel>>
}