package com.tobzzo.mmmpk.ui.dashboard.network

import com.tobzzo.mmmpk.ui.dashboard.model.DepartureModel
import io.reactivex.Observable

class ApiService() : ApiServiceInterface {
    override fun getAllDepartures(): Observable<List<DepartureModel>> {

        return Observable.just(
            listOf(
                DepartureModel("name1", "time1"),
                DepartureModel("name2", "time2"),
                DepartureModel("name3", "time3")
            )
        )
    }
}