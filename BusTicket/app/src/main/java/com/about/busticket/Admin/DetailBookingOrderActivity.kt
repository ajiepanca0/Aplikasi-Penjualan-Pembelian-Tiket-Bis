package com.about.busticket.Admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.about.busticket.Connection.ApiRetrofit
import com.about.busticket.Model.Admin.ResponseDetailBooking
import com.about.busticket.databinding.ActivityDetailBookingOrderBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailBookingOrderActivity : AppCompatActivity() {

    companion object{

        const val dataidbooking = "data"
    }

    private lateinit var binding : ActivityDetailBookingOrderBinding
    private val api by lazy { ApiRetrofit().endPoint }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBookingOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getdata()

        binding.tvIsiIdbooking.text = intent.getStringExtra(dataidbooking)

    }
    // melakukan get data data detailbooking
    private fun getdata() {
        api.getDataDetailBooking(intent.getStringExtra(dataidbooking).toString())
            .enqueue(object : Callback<ResponseDetailBooking>{
                override fun onResponse(
                    call: Call<ResponseDetailBooking>,
                    response: Response<ResponseDetailBooking>
                ) {

                    if (response.isSuccessful){
                        val data = response.body()

                        // jika respon sukses akan menempelkan data pada widget" yang telah di tentukan

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