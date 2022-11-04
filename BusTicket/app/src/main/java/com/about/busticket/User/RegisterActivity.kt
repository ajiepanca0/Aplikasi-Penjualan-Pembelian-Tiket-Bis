package com.about.busticket.User

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.about.busticket.Connection.ApiRetrofit
import com.about.busticket.Connection.SubmitModel
import com.about.busticket.R
import com.about.busticket.databinding.ActivityRegisterBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    private val api by lazy { ApiRetrofit().endPoint }


    private lateinit var binding : ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ActionRegist.setOnClickListener { chedata() }

    }


// melakukan pengechekan data
    private fun chedata() {

        if (binding.etName.text.toString().length <3){
            binding.etName.setError("Minimal 3 Karakter")
        }else if(binding.etEmail.text.toString().length <3){
            binding.etEmail.setError("Minimal 3 Karakter")
        }else if(binding.etPhonenumber.text.toString().length <10){
            binding.etPhonenumber.setError("Minimal 10 Karakter")
        }else if(binding.etPassword.text.toString().length <8){
            binding.etPassword.setError("Minimal 8 Karakter")
        }else{
            insertdata() // menjalankan fungsi insert data
        }

    }
        // melakukan passing data dan insert ke database
    private fun insertdata() {
        api.create_user(binding.etPhonenumber.text.toString(),
                binding.etName.text.toString(),
        binding.etEmail.text.toString(),
        binding.etPassword.text.toString())
            .enqueue(object : Callback<SubmitModel> {
                override fun onResponse(call: Call<SubmitModel>, response: Response<SubmitModel>) {

                    if (response.isSuccessful) {
                        val chek = response.body()
                        Toast.makeText(applicationContext, chek!!.status, Toast.LENGTH_SHORT).show()
                        val gotologin = Intent(applicationContext,LoginActivity::class.java)
                        startActivity(gotologin)
                    } else {
                        Toast.makeText(applicationContext, "Gagal Merespon", Toast.LENGTH_SHORT).show()
                    }
                }


                override fun onFailure(call: Call<SubmitModel>, t: Throwable) {
                    Toast.makeText(applicationContext, "error", Toast.LENGTH_SHORT).show()
                }

            })
    }
}