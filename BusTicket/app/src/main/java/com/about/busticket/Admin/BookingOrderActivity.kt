package com.about.busticket.Admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.about.busticket.Adapter.BookingOrderAdapter
import com.about.busticket.Connection.ApiRetrofit
import com.about.busticket.Model.Admin.BookingOrderItem
import com.about.busticket.Model.Admin.DataBooking
import com.about.busticket.Model.Admin.ResponseBookingOrder
import com.about.busticket.databinding.ActivityBookingOrderBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class BookingOrderActivity : AppCompatActivity() {

    private val api by lazy { ApiRetrofit().endPoint }
    private lateinit var binding : ActivityBookingOrderBinding




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookingOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val layoutManager = LinearLayoutManager(this) //
        binding.rvbookingOrder.setLayoutManager(layoutManager) //  menyusun item dalam daftar satu dimensi. pada recyclerview
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvbookingOrder.addItemDecoration(itemDecoration)

        Getdata()
    }
        //melakukan get data dari database
    private fun Getdata() {
        val dataBooking = api.DataBooking()
        dataBooking.enqueue(object : Callback<ResponseBookingOrder>{
            override fun onResponse(
                call: Call<ResponseBookingOrder>,
                response: Response<ResponseBookingOrder>
            ) {
               if(response.isSuccessful){
                   setreviewdata(response.body()!!.bookingOrder) // jika respon sukses akan menjalankan fungsi setviewdata dengan value responbody yang di dapat
               }
            }

            override fun onFailure(call: Call<ResponseBookingOrder>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show() // menampilkan error jika respon gagal
            }

        })
    }

    private fun setreviewdata(bookingOrder: List<BookingOrderItem>) {

        val listreview = ArrayList<DataBooking>()

        for (review in bookingOrder){ // melakukan pass data yang didapatkan dari responbody ke data class DataBooking
            val Booking= DataBooking(review.idBooking,
                review.name,
                review.phone,
                review.destination,
                review.date,
                review.time,
                review.bus,
                review.price)
            listreview.add(Booking)
        }
        val adapter = BookingOrderAdapter(listreview) // value pada adapter BookingAdapter
        binding.rvbookingOrder.adapter = adapter

        // aksi jika item di click akan melakukan perpindahan dan membawa data ke DetailBookingOrder
        adapter.setOnItemClickCallback(object : BookingOrderAdapter.OnItemClickCallback {
            override fun onItemClicked(data: DataBooking) {
                Toast.makeText(applicationContext, data.idbooking, Toast.LENGTH_SHORT).show()
                val pindah = Intent(applicationContext, DetailBookingOrderActivity::class.java)
                pindah.putExtra(DetailBookingOrderActivity.dataidbooking,data.idbooking)
                startActivity(pindah)
            }


        })

    }
}