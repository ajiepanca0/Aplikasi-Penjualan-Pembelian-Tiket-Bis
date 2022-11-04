package com.about.busticket.Admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.about.busticket.Connection.ApiRetrofit
import com.about.busticket.Model.Admin.ResponseDetailBooking
import com.about.busticket.R
import com.about.busticket.databinding.ActivityDetailBookingOrderBinding
import com.about.busticket.databinding.ActivityFindBoAdminBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FindBoAdminActivity : AppCompatActivity() {

    private lateinit var binding : ActivityFindBoAdminBinding
    private val api by lazy { ApiRetrofit().endPoint }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFindBoAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnfind.setOnClickListener {
            if (binding.findIDBO.text.toString().length<10){
                binding.findIDBO.setError("Masukan ID Yang Valid")
            }else{
                getdata()
            }
        }


    }
        // melakukan get data berdasarkan id booking yang dikirimkan
    private fun getdata() {
        api.getDataDetailBooking(binding.findIDBO.text.toString())
            .enqueue(object : Callback<ResponseDetailBooking> {
                override fun onResponse(
                    call: Call<ResponseDetailBooking>,
                    response: Response<ResponseDetailBooking>
                ) {

                    if (response.isSuccessful){
                        val data = response.body()

                        binding.tvIsiIdbooking.text= binding.findIDBO.text.toString()
                        binding.tvIsiName.text = data?.name
                        binding.tvIsiPhone.text = data?.phone
                        binding.tvIsiDestination.text = data?.destination

                        binding.tvIsiDate.text = data?.date
                        binding.tvIsiTime.text = data?.time
                        binding.tvIsiBus.text = data?.bus
                        binding.tvIsiPrice.text = data?.price
                    }

                }

                override fun onFailure(call: Call<ResponseDetailBooking>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()                }

            })
    }

}