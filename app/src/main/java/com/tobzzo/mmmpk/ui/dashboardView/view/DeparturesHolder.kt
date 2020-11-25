package com.tobzzo.mmmpk.ui.dashboardView.view

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.tobzzo.mmmpk.ui.dashboardView.model.DepartureModel
import kotlinx.android.synthetic.main.departurees_recyclerview_item_row.view.*

class DeparturesHolder(v: View): RecyclerView.ViewHolder(v), View.OnClickListener {
    private var view: View = v
    private var departure: DepartureModel? = null

    init {
        v.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val context = itemView.context
    }

    fun bindDeparture(departure: DepartureModel) {
        this.departure = departure

        view.departureStation.text = departure.station
        view.departureDirection.text = departure.direction
        view.departureTime.text = "${departure.hour} ${departure.minute}"

        Log.d("AAA", departure.toString())
    }
}