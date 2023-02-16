package com.ji.mysolelife.utils

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.google.firebase.auth.FirebaseAuth
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class FBAuth {

    companion object {
        private lateinit var auth: FirebaseAuth

        fun getUid(): String {
            auth = FirebaseAuth.getInstance()

            return auth.currentUser?.uid.toString()
        }
        fun getTime(): String {
            val currentDateTime = Calendar.getInstance(TimeZone.getTimeZone("Asia/Seoul")).time
            val dateFormat = SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(currentDateTime)

            Log.e("dateFormat", dateFormat)
            return dateFormat
        }
    }
}