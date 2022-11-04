package com.about.busticket.Model.Admin

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataBooking ( // sebagai penampung data
    var idbooking : String?,
    var ordername : String?,
    var phone : String?,
    var destination : String ?,
    var date : String ?,
    var time : String ?,
    var bus : String ?,
    var price : String ? ): Parcelable
