package br.com.apkdoandroid.Calendar

import android.content.Context
import java.util.Calendar

class CalendaCustom {
//(private val context: Context)
    fun calendar() : Calendar{
        return Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(2024,1,27)
            add(Calendar.SECOND,5)
        }
    }
}