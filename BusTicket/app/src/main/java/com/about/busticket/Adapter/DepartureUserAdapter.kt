package com.about.busticket.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.about.busticket.Model.Departure.DataDeparture
import com.about.busticket.R


class DepartureUserAdapter(private val listReview: ArrayList<DataDeparture>) : RecyclerView.Adapter<DepartureUserAdapter.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.depature_row, viewGroup, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val (idDeparture,destination,name_bus,date,time,price)=listReview[position]

        viewHolder.destination.text = destination
        viewHolder.namebus.text = name_bus
        viewHolder.date.text = date
        viewHolder.time.text = time
        viewHolder.price.text = price






    }
    override fun getItemCount(): Int {
        return listReview.size
    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val destination : TextView = view.findViewById(R.id.tv_item_destination)
        val namebus : TextView = view.findViewById(R.id.tv_item_name_bus)
        val date : TextView = view.findViewById(R.id.tv_item_date)
        val time : TextView = view.findViewById(R.id.tv_item_time)
        val price : TextView = view.findViewById(R.id.tv_item_price)


    }

}