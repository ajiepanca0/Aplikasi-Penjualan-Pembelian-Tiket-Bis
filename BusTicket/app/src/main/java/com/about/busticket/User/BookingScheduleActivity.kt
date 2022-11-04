package com.about.busticket.User

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.about.busticket.Adapter.DepartureAdapter
import com.about.busticket.Connection.ApiRetrofit
import com.about.busticket.Model.Departure.DataDeparture
import com.about.busticket.Model.DerpatureBusItem
import com.about.busticket.Model.ResponseDeparture
import com.about.busticket.User.HomeUserActivity.Companion.phoneKey
import com.about.busticket.databinding.ActivityBookingScheduleBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class BookingScheduleActivity : AppCompatActivity() {

    private lateinit var binding : ActivityBookingScheduleBinding
    private val api by lazy { ApiRetrofit().endPoint }

    companion object{
        const val QueryUser = "user"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookingScheduleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this) //  menyusun item dalam daftar satu dimensi. pada recyclerview
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

        val adapter = DepartureAdapter(listreview)
        binding.rvSchedule.adapter = adapter

        // melakukan aksi jika item di click akan melakukan perpindahan ke activity selanjutnya dan membawa data
        adapter.setOnItemClickCallback(object : DepartureAdapter.OnItemClickCallback {
            override fun onItemClicked(data: DataDeparture) {
                Toast.makeText(applicationContext, data.idDeparture, Toast.LENGTH_SHORT).show()
                val movesub = Intent(applicationContext, BookingSubmitActivity::class.java)
                movesub.putExtra(BookingSubmitActivity.DataNameBus,data.name_bus)
                movesub.putExtra(BookingSubmitActivity.DataJadwal,data.date)
                movesub.putExtra(BookingSubmitActivity.DataJam,data.time)
                movesub.putExtra(BookingSubmitActivity.DataPrice,data.price)
                movesub.putExtra(BookingSubmitActivity.DataDestination,data.destination)
                movesub.putExtra(BookingSubmitActivity.hpuser,intent.getStringExtra(QueryUser).toString())

                startActivity(movesub)
            }

        })

    }




}
