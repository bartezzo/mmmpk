package com.tobzzo.mmmpk.ui.dashboard.view

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tobzzo.mmmpk.R
import com.tobzzo.mmmpk.helpers.inflate
import com.tobzzo.mmmpk.ui.dashboard.model.DepartureModel

class DeparturesRecyclerAdapter(private val departures: List<DepartureModel>) :
    RecyclerView.Adapter<DeparturesHolder>() {
    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeparturesHolder {
        val inflatedView = parent.inflate(R.layout.departurees_recyclerview_item_row, false)
        return DeparturesHolder(inflatedView)
    }

    override fun getItemCount(): Int = departures.size

    override fun onBindViewHolder(holder: DeparturesHolder, position: Int) {
        val item = departures[position]
        holder.bindDeparture(item)
    }
}