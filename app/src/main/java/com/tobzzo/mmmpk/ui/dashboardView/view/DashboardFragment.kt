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
        setupRecyclerView()
        bindUIData()
        bindUIGestures()
    }

    private fun setupViews() {
        setupFilterDepartureLine()
        setupFilterDepartureStation()
        setupFilterDepartureDirection()
        setupFilterDepartureDay()
        setupFilterView()
    }


    private fun snack(text: String) {
        Snackbar.make(
            rootLayout,
            text,
            Snackbar.LENGTH_SHORT
        ).show()
    }

    private fun setupFilterView() {
    }

    private fun setupFilterDepartureLine() {
        val enums = getAllEnums(DEPARTURE_LINE)
    }

    private fun setupFilterDepartureStation() {
        val enums = getAllEnums(DEPARTURE_STATION)
    }

    private fun setupFilterDepartureDirection() {
        val enums = getAllEnums(DEPARTURE_DIRECTION)
    }

    private fun setupFilterDepartureDay() {
        val enums = getAllEnums(DEPARTURE_DAY)
    }

    private fun getAllEnums(enum: DepartureEnums): Array<String>? = when (enum) {
        DEPARTURE_LINE -> DepartureLineEnum.values().map { it -> it.toString() }
        DEPARTURE_STATION -> DepartureStationEnum.values().map { it -> it.toString() }
        DEPARTURE_DIRECTION -> DepartureDirectionEnum.values().map { it -> it.toString() }
        DEPARTURE_DAY -> DepartureDayEnum.values().map { it -> it.toString() }
        else -> emptyList()
    }.filter { it != "NONE" }.toTypedArray()


    private fun setupRecyclerView() {
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.setHasFixedSize(true)
    }

    private fun bindUIGestures() {

        filter_departure_line.clicks()
            .observeOnMainThread()
            .subscribe {
                viewModel.getDeparturesData(
                    DepartureDayEnum.PN_PT, 10, 5
                )
            }

        filter_departure_station.clicks()
            .observeOnMainThread()
            .subscribe {
                viewModel.getDeparturesData(
                    DepartureDayEnum.PN_PT, 10, 5
                )
            }


        filter_departure_direction.clicks()
            .observeOnMainThread()
            .subscribe {
                viewModel.getDeparturesData(
                    DepartureDayEnum.PN_PT, 10, 5
                )
            }

        filter_search.clicks()
            .observeOnMainThread()
            .subscribe {
                viewModel.getDeparturesData(
                    DepartureDayEnum.PN_PT, 10, 5
                )
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
    }

    private fun showErrorMessage(error: ErrorMessage) {
        snack(error.getMessage())
    }
}