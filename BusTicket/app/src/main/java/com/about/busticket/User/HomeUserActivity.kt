package com.about.busticket.User

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.about.busticket.Adapter.BookingOrderAdapter
import com.about.busticket.Admin.DetailBookingOrderActivity
import com.about.busticket.Connection.ApiRetrofit
import com.about.busticket.Model.Admin.BoMeItem
import com.about.busticket.Model.Admin.DataBooking
import com.about.busticket.Model.Admin.ResponseBoMe
import com.about.busticket.Model.user.ResponseDataUser
import com.about.busticket.databinding.ActivityHomeUserBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import java.util.ArrayList

class HomeUserActivity : AppCompatActivity() {

    companion object{
        const val phoneKey = "nomor"
    }
    private lateinit var binding : ActivityHomeUserBinding
    private val TAG = HomeUserActivity::class.java.simpleName

    private val api by lazy { ApiRetrofit().endPoint }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //pindah ke activity DepartureUserActivity

        binding.ChekSchedule.setOnClickListener {
            val i = Intent(applicationContext,DepartureUserActivity::class.java)
            startActivity(i)
        }

        //pindah ke activity BookingScheduleActivity

        binding.Booking.setOnClickListener {
            val i = Intent(applicationContext,BookingScheduleActivity::class.java)
            i.putExtra(BookingScheduleActivity.QueryUser,intent.getStringExtra(phoneKey).toString())
            startActivity(i)
        }

        //pindah ke activity ProfileActivity
        binding.edituser.setOnClickListener {
            val i = Intent(applicationContext,ProfileActivity::class.java)
            i.putExtra(ProfileActivity.QueryUser,intent.getStringExtra(phoneKey).toString())
            startActivity(i)
        }

        val layoutManager = LinearLayoutManager(this) //  menyusun item dalam daftar satu dimensi. pada recyclerview
        binding.rvBoMe.setLayoutManager(layoutManager)
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvBoMe.addItemDecoration(itemDecoration)

        getData()

        getdatauser()

        // menjalankan button logout
        binding.logoutBtn.setOnClickListener {
            Toast.makeText(applicationContext, "Berhasil Keluar", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }



    }
        // Jika tombol kembali di klik 2 kali akan keluar
    var doubleBackToExitPressedOnce = false

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            finish()
            onDestroy()
            return
        }
        doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Tekan Kembali Untuk Keluar", Toast.LENGTH_SHORT).show()
        Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }
    // melakukan get data dan menangkap hasil dari respon datauser
    private fun getdatauser() {
        api.getDataUser(intent.getStringExtra(phoneKey).toString())
            .enqueue(object : Callback<ResponseDataUser> {
                override fun onResponse(call: Call<ResponseDataUser>, response: Response<ResponseDataUser>) {
                    if (response.isSuccessful){
                        val responseBody =response.body()
                        if (responseBody != null){
                            binding.name.setText(responseBody.name)
                            val i = intent
                        }

                    } else {
                        Log.e(TAG, "onFailure: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<ResponseDataUser>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }

    // melakukan get data dan menangkap hasil dari responBoMe
    private fun getData() {
        api.getDataBoMe(intent.getStringExtra(phoneKey).toString())
            .enqueue(object : Callback<ResponseBoMe> {
                override fun onResponse(
                    call: Call<ResponseBoMe>,
                    response: Response<ResponseBoMe>
                ) {
                    if(response.isSuccessful){
                        setreviewdata(response.body()!!.boMe)
                    }

                }


                override fun onFailure(call: Call<ResponseBoMe>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }

    private fun setreviewdata(boMe: List<BoMeItem>) {
        val listreview = ArrayList<DataBooking>()

// melakukan pass data yang didapatkan dari responbody ke data class DataBooking
        for (review in boMe){
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

        val adapter = BookingOrderAdapter(listreview)
        binding.rvBoMe.adapter = adapter

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