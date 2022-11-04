package com.about.busticket.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.about.busticket.Model.Admin.DataBooking
import com.about.busticket.R


class BookingOrderAdapter(private val listReview: ArrayList<DataBooking>) : RecyclerView.Adapter<BookingOrderAdapter.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.bookingorder_row, viewGroup, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val (idbooking,ordername,phone,destination,date,time,name_bus,price)=listReview[position]

        viewHolder.idbooking.text = idbooking
        viewHolder.namebooking.text = ordername
        viewHolder.phoneuser.text = phone
        viewHolder.destination.text = destination
        viewHolder.date.text = date
        viewHolder.time.text = time
        viewHolder.namebus.text = name_bus
        viewHolder.price.text = price





        viewHolder.itemView.setOnClickListener {onItemClickCallback.onItemClicked(listReview[viewHolder.adapterPosition])}


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
        val idbooking : TextView = view.findViewById(R.id.idbooking)
        val namebooking : TextView = view.findViewById(R.id.ordername)
        val phoneuser : TextView = view.findViewById(R.id.phoneuser)



    }

    private lateinit var onItemClickCallback: BookingOrderAdapter.OnItemClickCallback
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
    interface OnItemClickCallback {
        fun onItemClicked(data: DataBooking)
    }
}