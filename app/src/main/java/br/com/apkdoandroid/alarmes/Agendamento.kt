package br.com.apkdoandroid.alarmes

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import br.com.apkdoandroid.Calendar.CalendaCustom
import br.com.apkdoandroid.broadCast.AgedamentoBroadReceiver
import br.com.apkdoandroid.services.MeuService

class Agendamento(private val context: Context) {
    private lateinit var alarmManager: AlarmManager
    private lateinit var pendingIntent: PendingIntent
    private val calendar = CalendaCustom()

    fun agendar(){
        val intent = Intent(context,AgedamentoBroadReceiver::class.java)
        alarmManager = context.getSystemService(AlarmManager::class.java)
        alarmManager.set(
            AlarmManager.RTC,
            System.currentTimeMillis() + 5000,
            PendingIntent.getBroadcast(
                context,
                1,
                intent,
                PendingIntent.FLAG_IMMUTABLE
            )
        )
    }

    fun agendar2(){
        val intent = Intent(context,AgedamentoBroadReceiver::class.java)
        alarmManager = context.getSystemService(AlarmManager::class.java)
        alarmManager.set(
            AlarmManager.RTC_WAKEUP, // disperta dispositivo
            calendar.calendar().timeInMillis,
            PendingIntent.getBroadcast(
                context,
                1,
                intent,
                PendingIntent.FLAG_IMMUTABLE
            )
        )
    }
    fun agendar3(){
        val intent = Intent(context,MeuService::class.java)
        pendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
           PendingIntent.getForegroundService(
                context,
                1,
                intent,
                PendingIntent.FLAG_IMMUTABLE
            )
       } else {
           PendingIntent.getService(
               context,
               1,
               intent,
               PendingIntent.FLAG_IMMUTABLE
           )
       }
        alarmManager = context.getSystemService(AlarmManager::class.java)
        alarmManager.set(
            AlarmManager.RTC_WAKEUP, // disperta dispositivo
            calendar.calendar().timeInMillis,
            pendingIntent
        )
    }

    fun cancelar(){
        alarmManager.cancel(pendingIntent)
    }
}