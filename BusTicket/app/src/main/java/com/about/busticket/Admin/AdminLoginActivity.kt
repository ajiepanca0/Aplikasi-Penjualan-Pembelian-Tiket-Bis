package com.about.busticket.Admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.about.busticket.Connection.ApiRetrofit
import com.about.busticket.Connection.SubmitModel
import com.about.busticket.R
import com.about.busticket.User.HomeUserActivity
import com.about.busticket.databinding.ActivityAdminLoginBinding
import com.about.busticket.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdminLoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAdminLoginBinding
    private val api by lazy { ApiRetrofit().endPoint }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //melakukan action pada tombol login. melakukan pengecekan data dan melakukan fungsi login()
        binding.LoginBtn.setOnClickListener {
            if (binding.phoneLogin.text.toString().length==0){
                binding.phoneLogin.setError("Tidak Boleh Kosong")
            }else if (binding.passwordLogin.text.toString().length==0){
                binding.passwordLogin.setError("Tidak Boleh Kosong")
            }else{
                login()
            }
        }
    }

    private fun login() {
        //melakukan pengiriman data untuk kedatabase
        api.login_admin(binding.phoneLogin.text.toString(),binding.passwordLogin.text.toString())
                .enqueue(object : Callback<SubmitModel> {//mendapatkan respon dari database
                    override fun onResponse(call: Call<SubmitModel>, response: Response<SubmitModel>) {
                        if (response.isSuccessful) { // jika respon berhasil akan menuju halaman utama
                            val chek = response.body()
                            var status = chek!!.status
                            if (status.equals("Sukses")) {
                                Toast.makeText(applicationContext, chek!!.status, Toast.LENGTH_SHORT).show()
                                val home = Intent(applicationContext, HomeAdminActivity::class.java)
                                home.putExtra(HomeAdminActivity.QueryAdmin,binding.phoneLogin.text.toString())
                                startActivity(home)
                            } else {
                                Toast.makeText(applicationContext, "PASSWORD ATAU EMAIL SALAH", Toast.LENGTH_SHORT).show()
                            }


                        }
                    }
                    //jika respon salah akan menampilkan pesan error
                    override fun onFailure(call: Call<SubmitModel>, t: Throwable) {
                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
                    }

                })
    }
}