package com.tobzzo.mmmpk.ui.dashboardView.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.tobzzo.mmmpk.R
import com.tobzzo.mmmpk.helpers.ErrorMessage
import com.tobzzo.mmmpk.helpers.show
import com.tobzzo.mmmpk.helpers.subscribe
import com.tobzzo.mmmpk.ui.dashboardView.model.DepartureModel
import com.tobzzo.mmmpk.ui.dashboardView.model.json.Departures
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
        bindUIData()
        bindUIGestures()
        setupRecyclerView()

        viewModel.getDeparturesData()
    }



//    private fun deserializeFromXML() {
//        try {
//            val xmlMapper = XmlMapper()
//
//            // read file and put contents into the string
//            val readContent: String = String(Files.readAllBytes(Paths.get("to_deserialize.xml")))
//
//            // deserialize from the XML into a Phone object
//            val deserializedData: PhoneDetails =
//                xmlMapper.readValue(readContent, PhoneDetails::class.java)
//
//            // Print object details
//            println("Deserialized data: ")
//            System.out.println("\tName: " + deserializedData.getName())
//            System.out.println("\tMemory: " + deserializedData.getMemory())
//            System.out.println("\tDisplay Size: " + deserializedData.getDisplaySize())
//        } catch (e: IOException) {
//            // handle the exception
//        }
//    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = linearLayoutManager
    }

    private fun bindUIGestures() {
//        disposable = downloadButton.clicks()
//            .observeOnMainThread()
//            .subscribe{
//                viewModel.getDeparturesData()
//            }
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
        progressBar.show(isDownloading)
    }

    private fun showErrorMessage(error: ErrorMessage) {
        Snackbar.make(
            rootLayout,
            error.getMessage(),
            Snackbar.LENGTH_SHORT
        ).show()
    }
}