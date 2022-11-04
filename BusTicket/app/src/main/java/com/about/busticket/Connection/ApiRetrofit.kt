package com.about.busticket.Connection

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiRetrofit {
    val endPoint: ApiEndPoint
    get() {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor).build()


        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.43.161/App_bus/") // sebagai base url untuk mengakses database
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
                    .build()

        return retrofit.create  (ApiEndPoint::class.java)
    }
}