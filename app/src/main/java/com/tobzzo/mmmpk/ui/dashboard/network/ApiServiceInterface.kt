package com.tobzzo.mmmpk.ui.dashboard.network

import com.tobzzo.mmmpk.ui.dashboard.model.DepartureModel
import io.reactivex.Observable

interface ApiServiceInterface {
    fun getAllDepartures(): Observable<List<DepartureModel>>
}