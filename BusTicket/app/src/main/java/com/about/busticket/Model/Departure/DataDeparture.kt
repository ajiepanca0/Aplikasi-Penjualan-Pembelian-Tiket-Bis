package com.about.busticket.Model.Departure

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataDeparture(

    var idDeparture : String ?,
    var destination : String ?,
    var name_bus : String ?,
    var date : String ?,
    var time : String ?,
    var price : String ? ):Parcelable