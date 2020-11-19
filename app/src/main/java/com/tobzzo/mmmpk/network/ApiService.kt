package com.tobzzo.mmmpk.network

import com.fasterxml.jackson.databind.ObjectMapper
import com.tobzzo.mmmpk.MmmpkApp
import com.tobzzo.mmmpk.R
import com.tobzzo.mmmpk.helpers.fromStringDayToEnumDay
import com.tobzzo.mmmpk.helpers.fromStringNumberToIntNumber
import com.tobzzo.mmmpk.ui.dashboardView.model.DepartureDayEnum
import com.tobzzo.mmmpk.ui.dashboardView.model.DepartureModel
import com.tobzzo.mmmpk.ui.dashboardView.model.json.Departures
import io.reactivex.Observable
import timber.log.Timber
import java.io.IOException

class ApiService : ApiServiceInterface {
    override fun getAllDepartures(
        day: DepartureDayEnum,
        hour: Int,
        minute: Int
    ): Observable<List<DepartureModel>> {
        return try {
            val xmlList = deserializeFromXML()
            val filtered = xmlList.filter { departure ->
                departure.day == day && departure.hour >= hour
            }

            return Observable.just(filtered)
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
            val lines = departures.line
            lines.forEach { line ->
                val lineName = line.name
                val stations = line.station
                stations.forEach { station ->
                    val stationName = station.name
                    val directions = station.direction
                    directions.forEach { direction ->
                        val directionName = direction.name
                        val departureDays = direction.day
                        departureDays.forEach { departureDay ->
                            val departureDayName = departureDay.name
                            val departureDayTimes = departureDay.time
                            departureDayTimes.forEach { time ->
                                val departureTimeHour = time.hour
                                val departureTimeMinute = time.minute

                                xmlList.add(
                                    DepartureModel(
                                        lineName,
                                        stationName,
                                        directionName,
                                        departureDayName.fromStringDayToEnumDay(),
                                        departureTimeHour.fromStringNumberToIntNumber(),
                                        departureTimeMinute.fromStringNumberToIntNumber()
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }

        return xmlList
    }
}