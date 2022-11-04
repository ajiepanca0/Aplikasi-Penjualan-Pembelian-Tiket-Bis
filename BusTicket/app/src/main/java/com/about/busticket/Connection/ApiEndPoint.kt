package com.about.busticket.Connection

import com.about.busticket.Model.Admin.*
import com.about.busticket.Model.Departure.ResponseDetailDeparture
import com.about.busticket.Model.ResponseDeparture
import com.about.busticket.Model.user.ResponseDataUser
import retrofit2.Call
import retrofit2.http.*

interface ApiEndPoint {

    // endpoint endpoint url untuk mengakses dan melakukan perintah di database


    @FormUrlEncoded
    @POST("create_user.php")
    fun create_user(
            @Field("phone") phone:String,
        @Field("name") name:String,
        @Field("email") email:String,
        @Field("password") password:String): Call<SubmitModel>

    @FormUrlEncoded
    @POST("login_user.php")
    fun login_user(
            @Field("phone") phone:String,
            @Field("password") password:String): Call<SubmitModel>


    @FormUrlEncoded
    @POST("login_admin.php")
    fun login_admin(
            @Field("phone") phone:String,
            @Field("password") password:String): Call<SubmitModel>

    @FormUrlEncoded
    @POST("create_admin.php")
    fun create_admin(

            @Field("phone") phone:String,
        @Field("name") name:String,
        @Field("email") email:String,
        @Field("password") password:String): Call<SubmitModel>



    @FormUrlEncoded
    @POST("create_departure.php")
    fun add_departure(
        @Field("id_departure") id_departure:String,
        @Field("destination") destination:String,
        @Field("name_bus") name_bus:String,
        @Field("date") date:String,
        @Field("time") time:String,
        @Field("price") price:String): Call<SubmitModel>


    @FormUrlEncoded
    @POST("create_booking.php")
    fun add_booking(
        @Field("name") name:String,
        @Field("phone") phone:String,
        @Field("destination") destination:String,
        @Field("date") date:String,
        @Field("time") time:String,
        @Field("bus") name_bus:String,
        @Field("price") price:String): Call<SubmitModel>




    @FormUrlEncoded
    @POST("update_departure.php")
    fun update_departure(
        @Field("id_departure") id_departure:String,
        @Field("destination") destination:String,
        @Field("name_bus") name_bus:String,
        @Field("date") date:String,
        @Field("time") time:String,
        @Field("price") price:String): Call<SubmitModel>

    @FormUrlEncoded
    @POST("update_user.php")
    fun update_user(
        @Field("phone") phone:String,
        @Field("name") name:String,
        @Field("email") email:String,
        @Field("password") password:String): Call<SubmitModel>

    @FormUrlEncoded
    @POST("update_admin.php")
    fun update_admin(
        @Field("phone") phone:String,
        @Field("name") name:String,
        @Field("email") email:String,
        @Field("password") password:String): Call<SubmitModel>


    @GET("Data_Departure.php")
    fun data():Call<ResponseDeparture>

    @GET("Data_BookingOrder.php")
    fun DataBooking():Call<ResponseBookingOrder>

    @GET("data_admin.php")
    fun getDataAdmin( @Query("phone") phone: String): Call<ResponseAdmin>

    @GET("data_user.php")
    fun getDataUser( @Query("phone") phone: String): Call<ResponseDataUser>


    @GET("detail_booking.php")
    fun getDataDetailBooking( @Query("id_booking") id_booking: String): Call<ResponseDetailBooking>

    @GET("BoMe.php")
    fun getDataBoMe( @Query("phone") phone: String): Call<ResponseBoMe>

    @GET("detail_departure.php")
    fun get_detail_departure( @Query("id_departure") id_departure: String): Call<ResponseDetailDeparture>

    @FormUrlEncoded
    @POST("delete_departure.php")
    fun delete_departure(
        @Field("id_departure") id_departure:String): Call<SubmitModel>


}