package com.about.busticket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.about.busticket.Connection.ApiRetrofit
import com.about.busticket.databinding.ActivityDepartureBinding
import retrofit2.Call
import retrofit2.Callback
import com.about.busticket.Adapter.DepartureAdapter
import com.about.busticket.Admin.HomeAdminActivity
import com.about.busticket.Admin.UpdateDepartureActivity
import com.about.busticket.Model.Departure.DataDeparture
import com.about.busticket.Model.DerpatureBusItem
import com.about.busticket.Model.ResponseDeparture
import retrofit2.Response
import java.util.ArrayList

class DepartureActivity : AppCompatActivity() {

    private val api by lazy { ApiRetrofit().endPoint }
    private lateinit var binding : ActivityDepartureBinding




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDepartureBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this) //  menyusun item dalam daftar satu dimensi. pada recyclerview
        binding.rvSchedule.setLayoutManager(layoutManager)
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvSchedule.addItemDecoration(itemDecoration)

        Getdata()


    }

    // melakukan get data dan menangkap respon dari ResponseDeparture
    private fun Getdata() {
        val client = api.data()
        client.enqueue(object : Callback<ResponseDeparture> {
            override fun onResponse(
                call: Call<ResponseDeparture>,
                response: Response<ResponseDeparture>
            ) {
                if (response.isSuccessful) {
                    setreviewdata(response.body()!!.derpatureBus)
                }
            }

            override fun onFailure(call: Call<ResponseDeparture>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun setreviewdata(derpatureBus: List<DerpatureBusItem>) {

        val listreview = ArrayList<DataDeparture>()
// melakukan pass data yang didapatkan dari responbody ke data class DataDeparture
        for (review in derpatureBus){
            val departure= DataDeparture(review.idDeparture,review.destination,review.nameBus,review.date,review.time,review.price)
            listreview.add(departure)
        }

        val adapter = DepartureAdapter(listreview)
        binding.rvSchedule.adapter = adapter


        // aksi jika item di click akan melakukan perpindahan dan membawa data ke DetailBookingOrder
        adapter.setOnItemClickCallback(object : DepartureAdapter.OnItemClickCallback {
            override fun onItemClicked(data: DataDeparture) {
                Toast.makeText(applicationContext, data.idDeparture, Toast.LENGTH_SHORT).show()
                val pindah = Intent(applicationContext, UpdateDepartureActivity::class.java)
                pindah.putExtra(UpdateDepartureActivity.QueryAdmin,data.idDeparture)
                startActivity(pindah)
            }

        })

    }

}