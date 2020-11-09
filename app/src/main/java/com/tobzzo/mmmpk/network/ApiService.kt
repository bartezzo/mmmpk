package com.tobzzo.mmmpk.network

import com.fasterxml.jackson.databind.ObjectMapper
import com.tobzzo.mmmpk.MmmpkApp
import com.tobzzo.mmmpk.R
import com.tobzzo.mmmpk.ui.dashboardView.model.DepartureModel
import com.tobzzo.mmmpk.ui.dashboardView.model.json.Departures
import com.tobzzo.mmmpk.ui.dashboardView.model.json.DeparturesDay
import io.reactivex.Observable
import timber.log.Timber
import java.io.IOException

class ApiService() : ApiServiceInterface {
    override fun getAllDepartures(): Observable<List<DepartureModel>> {
        return try {
            val xmlList = deserializeFromXML()
            return Observable.just(xmlList)
        } catch (e: IOException) {
            Timber.e(e, "deserializeFromXML error")
            Observable.error(Throwable("deserializeFromXML error:[$e]"))
        }
    }

    private fun deserializeFromXML(): List<DepartureModel> {
        val json = MmmpkApp.context?.resources?.openRawResource(R.raw.timetable3)
            ?.bufferedReader().use { it?.readText() }

        val xmlRawArray: Array<Departures> = ObjectMapper().readValue(
            json,
            Array<Departures>::class.java
        )

        val xmlList = mutableListOf<DepartureModel>()
        xmlRawArray.forEach { departures ->
            val line = departures.line
            val station = departures.station
            val direction = departures.direction
            val departureDays = departures.day
            departureDays.forEach { departureDay ->
                val departureDayName = departureDay.name
                val departureDayTimes = departureDay.time
                departureDayTimes.forEach { time ->
                    val departureTimeHour = time.hour
                    val departureTimeMinute = time.minute

                    xmlList.add(
                        DepartureModel(
                            line,
                            station,
                            direction,
                            departureDayName,
                            departureTimeHour,
                            departureTimeMinute
                        )
                    )
                }
            }
        }

        return xmlList
    }
}