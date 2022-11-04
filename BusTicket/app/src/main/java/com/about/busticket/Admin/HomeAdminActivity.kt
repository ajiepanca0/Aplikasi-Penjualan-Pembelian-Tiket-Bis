package com.about.busticket.Admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import com.about.busticket.Connection.ApiRetrofit
import com.about.busticket.DepartureActivity
import com.about.busticket.Model.Admin.ResponseAdmin
import com.about.busticket.R
import com.about.busticket.User.HomeUserActivity
import com.about.busticket.User.LoginActivity
import com.about.busticket.databinding.ActivityAdminLoginBinding
import com.about.busticket.databinding.ActivityHomeAdminBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeAdminActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeAdminBinding
    private val api by lazy { ApiRetrofit().endPoint }

    private lateinit var query : String

    companion object {

        const val QueryAdmin = "Admin"
        private val TAG = HomeAdminActivity::class.java.simpleName

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getdata()

        //pindah ke activity Departure
        binding.ChekSchedule.setOnClickListener {
            val departureActivity = Intent(applicationContext, DepartureActivity::class.java)
            startActivity(departureActivity)
        }

        //pindah ke CreateDepartureActivity
        binding.CreateSchedule.setOnClickListener {
            val createDepartureActivity = Intent(applicationContext, CreateDepartureActivity::class.java)
            createDepartureActivity.putExtra(HomeAdminActivity.QueryAdmin,query)
            startActivity(createDepartureActivity)
        }
        //pindah ke AdminProfileActivity
        binding.edituser.setOnClickListener {
            val adminProfileActivity = Intent(applicationContext, AdminProfileActivity::class.java)
            adminProfileActivity.putExtra(HomeAdminActivity.QueryAdmin,query)
            startActivity(adminProfileActivity)
        }
        //pindah ke BookingOrderActivity
        binding.movetoBookingOrder.setOnClickListener {
            val toBookingOrder = Intent(applicationContext, BookingOrderActivity::class.java)
            startActivity(toBookingOrder)
        }
    // melakukan logout
        binding.logoutBtn.setOnClickListener {
            Toast.makeText(applicationContext, "Berhasil Keluar", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        //pindah ke FindBoAdminActivity
        binding.findChekBO.setOnClickListener {
            val toFindBo = Intent(applicationContext, FindBoAdminActivity::class.java)
            startActivity(toFindBo)
        }
    }

    // Melakukan Keluar jika tombol back di tekan 2 kali

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

    // melakukan get data
    private fun getdata() {
            api.getDataAdmin(intent.getStringExtra(QueryAdmin).toString())
                    .enqueue(object :Callback<ResponseAdmin>{
                        override fun onResponse(call: Call<ResponseAdmin>, response: Response<ResponseAdmin>) {
                            if (response.isSuccessful){
                                val responseBody =response.body()
                                if (responseBody != null){
                                        setdata(responseBody)

                                }
                            } else {
                                Log.e(TAG, "onFailure: ${response.message()}")
                            }
                        }

                        override fun onFailure(call: Call<ResponseAdmin>, t: Throwable) {
                            TODO("Not yet implemented")
                        }

                    })
    }

    private fun setdata(responseBody: ResponseAdmin) {
            binding.name.text = responseBody.name
            query = responseBody.phone
    }
}