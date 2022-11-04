package com.about.busticket.Admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.about.busticket.Connection.ApiRetrofit
import com.about.busticket.Connection.SubmitModel
import com.about.busticket.Model.Admin.ResponseAdmin
import com.about.busticket.R
import com.about.busticket.User.HomeUserActivity
import com.about.busticket.databinding.ActivityAdminProfileBinding
import com.about.busticket.databinding.ActivityHomeAdminBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdminProfileActivity : AppCompatActivity() {

    companion object {
        //mendapatkan passing data dari login
        const val QueryAdmin = "Admin"
        private val TAG = AdminProfileActivity::class.java.simpleName

    }

    private lateinit var binding : ActivityAdminProfileBinding
    private val api by lazy { ApiRetrofit().endPoint }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getdata()

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
                UpdateData()
            }
        }
    }

    //mendapatkan data dari database dengan kunci yang di passing oleh activity login
    private fun getdata() {
        api.getDataAdmin(intent.getStringExtra(QueryAdmin).toString())
                .enqueue(object : Callback<ResponseAdmin> {
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
        //melakukan penempelan data dari database ke widget edittext pada adminprofile
    private fun setdata(responseBody: ResponseAdmin) {
       binding.etPhonenumber.setText(responseBody.phone)
        binding.etName.setText(responseBody.name)
        binding.etEmail.setText(responseBody.email)
        binding.etPassword.setText(responseBody.password)
    }


        //fungsi update data
    private fun UpdateData() {
            // mengirim data kedatabase untuk di proses update
            api.update_admin(
            binding.etPhonenumber.text.toString(),
            binding.etName.text.toString(),
            binding.etEmail.text.toString(),
            binding.etPassword.text.toString()
        )
            .enqueue(object : Callback<SubmitModel>{ // menangkap respon
                override fun onResponse(call: Call<SubmitModel>, response: Response<SubmitModel>) {
                    if (response.isSuccessful){ // jika respon berhasil akan menampilkan toast berhasil update

                        Toast.makeText(applicationContext, "Berhasil Terupdate", Toast.LENGTH_SHORT).show()
                        val i = Intent(applicationContext, HomeAdminActivity::class.java)
                        i.putExtra(HomeAdminActivity.QueryAdmin,binding.etPhonenumber.text.toString())
                        startActivity(i)
                    }
                }
                // jika gagal akan menampilan pesan error
                override fun onFailure(call: Call<SubmitModel>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
                }

            })
    }

}