package com.about.busticket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.about.busticket.User.LoginActivity
import com.about.busticket.User.RegisterActivity
import com.about.busticket.databinding.ActivityDepartureBinding
import com.about.busticket.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding :ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // jika button di klik akan pindah ke LoginActivity
        binding.movetologin.setOnClickListener {
            val login =Intent(applicationContext, LoginActivity::class.java)
            startActivity(login)
        }
        // jika button di klik akan pindah ke RegisterActivity
        binding.movetoregister.setOnClickListener {
            val regis =Intent(applicationContext, RegisterActivity::class.java)
            startActivity(regis)
        }
    }
}