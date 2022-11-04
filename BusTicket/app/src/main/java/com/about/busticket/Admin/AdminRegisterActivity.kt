package com.about.busticket.Admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.about.busticket.Connection.ApiRetrofit
import com.about.busticket.Connection.SubmitModel
import com.about.busticket.R
import com.about.busticket.User.LoginActivity
import com.about.busticket.databinding.ActivityAdminRegisterBinding
import com.about.busticket.databinding.ActivityRegisterBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdminRegisterActivity : AppCompatActivity() {

    private val api by lazy { ApiRetrofit().endPoint }
    private lateinit var binding : ActivityAdminRegisterBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //jikaa tombol regis di tekan akan menjalankan fungsi chek data
        binding.RegistAdmin.setOnClickListener { chekdata() }

    }

    // melakukan pengecekan pada form" yang ada
    private fun chekdata() {
        if (binding.etName.text.toString().length <3){
            binding.etName.setError("Minimal 3 Karakter")
        }else if(binding.etEmail.text.toString().length <3){
            binding.etEmail.setError("Minimal 3 Karakter")
        }else if(binding.etPhonenumber.text.toString().length <10){
            binding.etEmail.setError("Minimal 10 Karakter")
        }else if(binding.etPassword.text.toString().length <8){
            binding.etPassword.setError("Minimal 8 Karakter")
        }else{
            insertdata() // jika tidak ada yg error akan melakukan fungsi insert data ke database
        }
    }

    private fun insertdata() {
        api.create_admin(binding.etName.text.toString(),//melakukan passing data yang akan dikirimkan ke database
            binding.etEmail.text.toString(),
            binding.etPhonenumber.text.toString(),
            binding.etPassword.text.toString())
            .enqueue(object : Callback<SubmitModel> {
                override fun onResponse(call: Call<SubmitModel>, response: Response<SubmitModel>) {
                    if (response.isSuccessful) {
                        val chek = response.body()
                        Toast.makeText(applicationContext, chek!!.status, Toast.LENGTH_SHORT).show()
                        val gotologin = Intent(applicationContext, AdminLoginActivity::class.java)
                        startActivity(gotologin)
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
}