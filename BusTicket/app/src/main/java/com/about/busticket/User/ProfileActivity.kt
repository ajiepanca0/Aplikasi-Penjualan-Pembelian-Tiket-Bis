package com.about.busticket.User

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.about.busticket.Admin.AdminProfileActivity
import com.about.busticket.Connection.ApiRetrofit
import com.about.busticket.Connection.SubmitModel
import com.about.busticket.Model.Admin.ResponseAdmin
import com.about.busticket.Model.user.ResponseDataUser
import com.about.busticket.R
import com.about.busticket.databinding.ActivityAdminProfileBinding
import com.about.busticket.databinding.ActivityProfileBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileActivity : AppCompatActivity() {

    companion object {

        const val QueryUser = "user"
        private val TAG = ProfileActivity::class.java.simpleName

    }

    private val api by lazy { ApiRetrofit().endPoint }
    private lateinit var binding : ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getdata()

        // jika button di klik akan melakukan pengecekan
        binding.ActionEditProfile.setOnClickListener {
            if (binding.etPhonenumber.text.toString().length<0){
                binding.etPhonenumber.setError("Masukan Nomor Handphone")
            }else if (binding.etName.text.toString().length<0){
                binding.etName.setError("Masukan Nama")
            }else if (binding.etEmail.text.toString().length<0){
                binding.etEmail.setError("Masukan Email")
            }else if (binding.etPassword.text.toString().length<0){
                binding.etPassword.setError("Masukan Password")
            }else{
                UpdateData() // menjalankan fungsi update data
            }
        }

         }


//melakukan get data dan menangkap hasil dari responbody
    private fun getdata() {
            api.getDataUser(intent.getStringExtra(QueryUser).toString())
                .enqueue(object : Callback<ResponseDataUser> {
                    override fun onResponse(call: Call<ResponseDataUser>, response: Response<ResponseDataUser>) {
                        if (response.isSuccessful){
                            val responseBody =response.body()
                            if (responseBody != null){
                                setdata(responseBody) // passing value ke fungsi setdata
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

    // melakukan penempelan data pada widget widget yang telah di tentukan
        private fun setdata(responseBody: ResponseDataUser) {
            binding.etPhonenumber.setText(responseBody.phone)
            binding.etName.setText(responseBody.name)
            binding.etEmail.setText(responseBody.email)
            binding.etPassword.setText(responseBody.password)
        }

    // melakukan fungsi pass data dan melakukan update pada database
    private fun UpdateData() {
        api.update_user(
            binding.etPhonenumber.text.toString(),
            binding.etName.text.toString(),
            binding.etEmail.text.toString(),
            binding.etPassword.text.toString()
            )
            .enqueue(object : Callback<SubmitModel>{
                override fun onResponse(call: Call<SubmitModel>, response: Response<SubmitModel>) {
                    if (response.isSuccessful){

                        Toast.makeText(applicationContext, "Berhasil Terupdate", Toast.LENGTH_SHORT).show()
                        val i = Intent(applicationContext,HomeUserActivity::class.java)
                        i.putExtra(HomeUserActivity.phoneKey,binding.etPhonenumber.text.toString())
                        startActivity(i)
                    }
                }

                override fun onFailure(call: Call<SubmitModel>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
                }

            })
    }


    }
