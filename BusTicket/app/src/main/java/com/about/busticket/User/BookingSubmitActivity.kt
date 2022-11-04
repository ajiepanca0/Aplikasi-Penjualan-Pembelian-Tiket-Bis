package com.about.busticket.User

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import com.about.busticket.Connection.ApiRetrofit
import com.about.busticket.Connection.SubmitModel
import com.about.busticket.R
import com.about.busticket.databinding.ActivityBookingSubmitBinding
import com.about.busticket.databinding.ActivityCreateDepartureBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookingSubmitActivity : AppCompatActivity() {

    companion object{

        // menangkap data yang dikirimkan oleh activity
        const val DataNameBus = "bus"
        const val DataJadwal = "date"
        const val DataJam = "time"
        const val DataPrice = "price"
        const val DataDestination = "to"

        const val hpuser = "user"


    }

    private val api by lazy { ApiRetrofit().endPoint }
    private lateinit var binding : ActivityBookingSubmitBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookingSubmitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.CreateBooking.setOnClickListener { chekdata() }
        binding.tvIsitotalPriceTicket.text = "0"

// menempelkan data yang dikirimkan pada widget" yang telah di tentukan
        binding.tvIsiJamkeberangkatan.text = intent.getStringExtra(DataJam)
        binding.tvIsiTanggalkeberangkatan.text = intent.getStringExtra(DataJadwal)
        binding.tvIsiNamebus.text  = intent.getStringExtra(DataNameBus)
        binding.tvIsipriceticket.text = intent.getStringExtra(DataPrice)
        binding.tvIsiTujuan.text = intent.getStringExtra(DataDestination)
        binding.etPhonenumber.setText(intent.getStringExtra(hpuser).toString())



        // melakukan fungsi addtextchanged
        binding.etQuantity.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {



            }

            override fun afterTextChanged(s: Editable?) {

                if(binding.etQuantity.length()==0){
                    binding.tvIsitotalPriceTicket.text = "0"
                }else {

                    binding.tvIsitotalPriceTicket.text =
                        (s.toString().toInt() * intent.getStringExtra(
                            DataPrice
                        ).toString().toInt()).toString()
                }


            }

        })



    }
        // melakukan pengecekan data
    private fun chekdata() {
     if (binding.etName.text.toString().length==0) {
         binding.etName.setError("Masukan Nama")
     }else if(binding.etPhonenumber.toString().length<10){
         binding.etPhonenumber.setError("Nomor Handphone Kurang")
     }else if ( binding.tvIsitotalPriceTicket.text.toString().length==0){
         binding.etQuantity.setError("Masukan Quantity")
     }else{
            DoBooking()
     }
    }

    // melakukan passing dan  insert ke database
    private fun DoBooking() {
        api.add_booking(binding.etName.text.toString(),
            binding.etPhonenumber.text.toString(),
            intent.getStringExtra(DataDestination).toString(),
            intent.getStringExtra(DataJadwal).toString(),
            intent.getStringExtra(DataJam).toString(),
            intent.getStringExtra(DataNameBus).toString(),
            binding.tvIsitotalPriceTicket.text.toString())
            .enqueue(object : Callback<SubmitModel>{
                override fun onResponse(call: Call<SubmitModel>, response: Response<SubmitModel>) {
                    if (response.isSuccessful){
                        Toast.makeText(applicationContext,"Berhasil Booking", Toast.LENGTH_SHORT).show()
                        val i = Intent(applicationContext,HomeUserActivity::class.java)
                        i.putExtra(HomeUserActivity.phoneKey,binding.etPhonenumber.text.toString())
                        startActivity(i)
                    }
                }

                override fun onFailure(call: Call<SubmitModel>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()                }

            })
    }
}