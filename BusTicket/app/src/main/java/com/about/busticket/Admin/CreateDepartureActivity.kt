package com.about.busticket.Admin

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.about.busticket.Connection.ApiRetrofit
import com.about.busticket.Connection.SubmitModel
import com.about.busticket.DepartureActivity
import com.about.busticket.databinding.ActivityCreateDepartureBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class CreateDepartureActivity : AppCompatActivity() {

    private val api by lazy { ApiRetrofit().endPoint }

    private lateinit var binding : ActivityCreateDepartureBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateDepartureBinding.inflate(layoutInflater)
        setContentView(binding.root)

        DateAction()
        binding.CreateDepartureBtn.setOnClickListener { checkdata() }
        // jika button create di klik akan melakukan pengecheckan

    }

    // melakukan pengecheckan
    private fun checkdata() {
        if (binding.IdscheduleCreate.text.toString().length<1){
            binding.IdscheduleCreate.setError("Id Tidak Boleh Kosong")
        }else if (binding.DestinationCreate.text.toString().length<1){
            binding.DestinationCreate.setError("Destination Tidak Boleh Kosong")
        }else if (binding.BusNameCreate.text.toString().length<1){
            binding.BusNameCreate.setError("Bus Tidak Boleh Kosong")
        }else if (binding.DateCreate.text.toString().length<1){
            binding.DateCreate.setError("Date Tidak Boleh Kosong")
        }else if (binding.timeCreate.text.toString().length<1){
            binding.timeCreate.setError("Time Tidak Boleh Kosong")
        }else if (binding.priceCreate.text.toString().length<1){
            binding.priceCreate.setError("Price Tidak Boleh Kosong")
        }else{
            insertdata() // jika data tidak ada yg bersamalah akan melakukan insertdata
        }
    }

    private fun insertdata() {

        // melakukan pass data yang akan di insert pada database
        api.add_departure(binding.IdscheduleCreate.text.toString(),binding.DestinationCreate.text.toString(),binding.BusNameCreate.text.toString(),
            binding.DateCreate.text.toString(),binding.timeCreate.text.toString(),binding.priceCreate.text.toString())
            .enqueue(object : Callback<SubmitModel> {
                override fun onResponse(call: Call<SubmitModel>, response: Response<SubmitModel>) {
                    if (response.isSuccessful) {
                        val chek = response.body()
                        Toast.makeText(applicationContext, chek!!.status, Toast.LENGTH_SHORT).show()
                        val move = Intent(applicationContext, DepartureActivity::class.java)
                        startActivity(move)
                    } else {
                        Toast.makeText(applicationContext, "Gagal Merespon", Toast.LENGTH_SHORT)
                            .show()
                    }

                }

                override fun onFailure(call: Call<SubmitModel>, t: Throwable) {

                    Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()

                }

            })
    }


    private fun updatelabel(mycalendar: Calendar) {

        val myformat = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(myformat, Locale.US)
        binding.DateCreate.setText(sdf.format(mycalendar.time))
    }
    private fun DateAction() {
        val mycalendar = Calendar.getInstance()

        val datePicker = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            mycalendar.set(Calendar.YEAR, year)
            mycalendar.set(Calendar.MONTH, month)
            mycalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updatelabel(mycalendar)
        }

        binding.DateCreate.setOnClickListener {
            DatePickerDialog(this, datePicker, mycalendar.get(Calendar.YEAR), mycalendar.get(
                Calendar.MONTH), mycalendar.get(Calendar.DAY_OF_MONTH)).show()
        }
    }




}