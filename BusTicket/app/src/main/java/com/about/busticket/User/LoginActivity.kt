package com.about.busticket.User

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.about.busticket.Admin.AdminLoginActivity

import com.about.busticket.Connection.ApiRetrofit
import com.about.busticket.Connection.SubmitModel
import com.about.busticket.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {


    private lateinit var binding : ActivityLoginBinding
    private val api by lazy { ApiRetrofit().endPoint }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // jika button di klik akan melakukan pindah ke AdminLoginActivity
        binding.MovetoLoginAdmin.setOnClickListener {
            val loginadmin = Intent(applicationContext, AdminLoginActivity::class.java)
            startActivity(loginadmin)
        }

        // jika button di klik akan melakukan pengcekan
        binding.LoginBtn.setOnClickListener {

                if (binding.phoneLogin.text.toString().length==0){
                    binding.phoneLogin.setError("Tidak Boleh Kosong")
                }else if (binding.passwordLogin.text.toString().length==0){
                    binding.passwordLogin.setError("Tidak Boleh Kosong")
                }else{
                    login() // menjalankan fungsi login
                }
            }

    }

    // melakukan passing data dan insert ke database
    private fun login() {
        api.login_user(binding.phoneLogin.text.toString(),binding.passwordLogin.text.toString())
                .enqueue(object : Callback<SubmitModel> {
                    override fun onResponse(call: Call<SubmitModel>, response: Response<SubmitModel>) {
                        if (response.isSuccessful) {
                            val chek = response.body()
                            var status = chek!!.status.toString()
                            if (status.equals("Sukses")) {
                                Toast.makeText(applicationContext, chek!!.status, Toast.LENGTH_SHORT).show()
                                val home = Intent(applicationContext, HomeUserActivity::class.java)
                                home.putExtra(HomeUserActivity.phoneKey,binding.phoneLogin.text.toString())
                                startActivity(home)
                            } else {
                                Toast.makeText(applicationContext, "PASSWORD ATAU PHONE SALAH", Toast.LENGTH_SHORT).show()
                            }

                        }
                    }

                    override fun onFailure(call: Call<SubmitModel>, t: Throwable) {
                        Toast.makeText(applicationContext, "Sedang Merespon", Toast.LENGTH_SHORT).show()

                    }

                })
    }
}