package com.tobzzo.mmmpk.network

import com.tobzzo.mmmpk.ui.dashboardView.model.DepartureModel
import io.reactivex.Observable

class ApiService() : ApiServiceInterface {
    override fun getAllDepartures(): Observable<List<DepartureModel>> {

        return Observable.just(
            listOf(
                DepartureModel("name01", "time01"),
                DepartureModel("name02", "time02"),
                DepartureModel("name03", "time03"),
                DepartureModel("name04", "time04"),
                DepartureModel("name05", "time05"),
                DepartureModel("name06", "time06"),
                DepartureModel("name07", "time07"),
                DepartureModel("name08", "time08"),
                DepartureModel("name09", "time09"),
                DepartureModel("name10", "time10"),
                DepartureModel("name11", "time11"),
                DepartureModel("name12", "time12"),
                DepartureModel("name13", "time13"),
                DepartureModel("name14", "time14"),
                DepartureModel("name15", "time15"),
                DepartureModel("name16", "time16"),
                DepartureModel("name17", "time17"),
                DepartureModel("name18", "time18"),
                DepartureModel("name19", "time19"),
                DepartureModel("name20", "time20")
            )
        )
    }
}