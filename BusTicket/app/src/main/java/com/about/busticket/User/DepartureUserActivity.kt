package com.about.busticket.User

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.about.busticket.Adapter.DepartureAdapter
import com.about.busticket.Adapter.DepartureUserAdapter
import com.about.busticket.Admin.UpdateDepartureActivity
import com.about.busticket.Connection.ApiRetrofit
import com.about.busticket.Model.Departure.DataDeparture
import com.about.busticket.Model.DerpatureBusItem
import com.about.busticket.Model.ResponseDeparture
import com.about.busticket.R
import com.about.busticket.databinding.ActivityDepartureBinding
import com.about.busticket.databinding.ActivityDepartureUserBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class DepartureUserActivity : AppCompatActivity() {

    private val api by lazy { ApiRetrofit().endPoint }
    private lateinit var binding : ActivityDepartureUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDepartureUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)//  menyusun item dalam daftar satu dimensi. pada recyclerview
        binding.rvSchedule.setLayoutManager(layoutManager)
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvSchedule.addItemDecoration(itemDecoration)

        Getdata()
    }
    // melakukan get data dan menangkap hasil dari respon departure
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

        val adapter = DepartureUserAdapter(listreview)
        binding.rvSchedule.adapter = adapter


    }

}