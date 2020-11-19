package com.tobzzo.mmmpk.ui.dashboardView.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.jakewharton.rxbinding2.view.clicks
import com.tobzzo.mmmpk.R
import com.tobzzo.mmmpk.helpers.*
import com.tobzzo.mmmpk.ui.dashboardView.model.*
import com.tobzzo.mmmpk.ui.dashboardView.model.DepartureEnums.*
import com.tobzzo.mmmpk.ui.dashboardView.viewModel.DashboardViewModel
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_dashboard.*


class DashboardFragment : Fragment() {

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(DashboardViewModel::class.java)
    }
    private var disposable: Disposable? = null
    private var linearLayoutManager = LinearLayoutManager(context)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createView()
    }

    private fun createView() {
        setupViews()
        bindUIData()
        bindUIGestures()
        setupRecyclerView()

//        viewModel.getDeparturesData()
    }

    private fun setupViews() {
        setupFilterDepartureLinePicker()
        setupFilterDepartureStationPicker()
        setupFilterDepartureDirectionPicker()
        setupFilterDepartureDayPicker()
        setupFilterDepartureHourPicker()
        setupFilterDepartureMinutePicker()
    }

    private fun setupFilterDepartureLinePicker() {
        val enums = getAllEnums(DEPARTURE_LINE)
        filter_departure_line_picker.minValue = 0
        filter_departure_line_picker.maxValue = enums?.let { it.size - 1 } ?: 0
        filter_departure_line_picker.displayedValues = enums
        filter_departure_line_picker.value = (enums?.size ?: 0) / 2
    }

    private fun setupFilterDepartureStationPicker() {
        val enums = getAllEnums(DEPARTURE_STATION)
        filter_departure_station_picker.minValue = 0
        filter_departure_station_picker.maxValue = enums?.let { it.size - 1 } ?: 0
        filter_departure_station_picker.displayedValues = enums
        filter_departure_station_picker.value = (enums?.size ?: 0) / 2
    }

    private fun setupFilterDepartureDirectionPicker() {
        val enums = getAllEnums(DEPARTURE_DIRECTION)
        filter_departure_direction_picker.minValue = 0
        filter_departure_direction_picker.maxValue = enums?.let { it.size - 1 } ?: 0
        filter_departure_direction_picker.displayedValues = enums
        filter_departure_direction_picker.value = (enums?.size ?: 0) / 2
    }

    private fun setupFilterDepartureDayPicker() {
        val enums = getAllEnums(DEPARTURE_DAY)
        filter_departure_day.minValue = 0
        filter_departure_day.maxValue = enums?.let { it.size - 1 } ?: 0
        filter_departure_day.displayedValues = enums
        filter_departure_day.value = (enums?.size ?: 0) / 2
    }

    private fun getAllEnums(enum: DepartureEnums): Array<String>? = when (enum) {
        DEPARTURE_LINE -> DepartureLineEnum.values().map { it -> it.toString() }
        DEPARTURE_STATION -> DepartureStationEnum.values().map { it -> it.toString() }
        DEPARTURE_DIRECTION -> DepartureDirectionEnum.values().map { it -> it.toString() }
        DEPARTURE_DAY -> DepartureDayEnum.values().map { it -> it.toString() }
        else -> emptyList()
    }.filter { it != "NONE" }.toTypedArray()

    private fun setupFilterDepartureHourPicker() {
        filter_departure_hour.minValue = 0
        filter_departure_hour.maxValue = 23
        filter_departure_hour.value = 8
    }

    private fun setupFilterDepartureMinutePicker() {
        filter_departure_minute.minValue = 0
        filter_departure_minute.maxValue = 59
        filter_departure_minute.value = 30
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.setHasFixedSize(true)
    }

    private fun bindUIGestures() {
//        disposable = downloadButton.clicks()
//            .observeOnMainThread()
//            .subscribe{
//                viewModel.getDeparturesData()
//            }
        filter_image.clicks()
            .observeOnMainThread()
            .subscribe {
                viewModel.getDeparturesData(
                    filter_departure_day.value.fromIntDayToEnumDay(),
                    filter_departure_hour.value,
                    filter_departure_hour.value
                )
            }

        filter_departure_line.clicks()
            .observeOnMainThread()
            .subscribe {
                filter_departure_line_box.changeViewVisibility()
            }

        filter_departure_station.clicks()
            .observeOnMainThread()
            .subscribe {
                filter_departure_station_box.changeViewVisibility()
            }


        filter_departure_direction.clicks()
            .observeOnMainThread()
            .subscribe {
                filter_departure_direction_box.changeViewVisibility()
            }

        filter_departure_line_picker.setOnValueChangedListener { picker, oldVal, newVal ->
            filter_departure_line.text = filter_departure_line_picker.displayedValues[newVal]
        }

        filter_departure_station_picker.setOnValueChangedListener { picker, oldVal, newVal ->
            filter_departure_station.text = filter_departure_station_picker.displayedValues[newVal]
        }

        filter_departure_direction_picker.setOnValueChangedListener { picker, oldVal, newVal ->
            filter_departure_direction.text = filter_departure_direction_picker.displayedValues[newVal]
        }

    }

    private fun bindUIData() {
        viewModel.departures.subscribe(this, ::showAllDepartures)
        viewModel.progress.subscribe(this, ::updateProgress)
        viewModel.errors.subscribe(this, ::showErrorMessage)
    }

    private fun showAllDepartures(departures: List<DepartureModel>) {
        recyclerView.adapter = DeparturesRecyclerAdapter(departures)
    }

    private fun updateProgress(isDownloading: Boolean) {
//        progressBar.show(isDownloading)
    }

    private fun showErrorMessage(error: ErrorMessage) {
        Snackbar.make(
            rootLayout,
            error.getMessage(),
            Snackbar.LENGTH_SHORT
        ).show()
    }
}