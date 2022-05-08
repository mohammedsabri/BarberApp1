package com.example.barberapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate.now
import java.time.LocalDateTime
import java.util.*


@Parcelize
data class BookModel(
    var id: Long = 0,
    val service: String = "haircut",
    val appDate: String,
    val time: String,
    val email: String = "moe@sabri.com",
    val barbername: String ="moe",
    val cost: Int = 15) : Parcelable