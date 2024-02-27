package br.com.apkdoandroid.broadCast

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import br.com.apkdoandroid.alarmmanagerapp.R

class AgedamentoBroadReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Log.i("agedamento_android","Executou agedamento")
        exibirNotificacao(context)
    }

    fun exibirNotificacao(context: Context){
        val idCanal = "lembrete"
        val notificationManager = context.getSystemService(NotificationManager::class.java)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val canal = NotificationChannel(idCanal,"Lembretes",NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(canal)
        }

        val notificacaoBuilder = NotificationCompat.Builder(context,idCanal).apply {
            setSmallIcon(R.drawable.ic_launcher_foreground)
            setShowWhen(true)
            setContentTitle("Lembrete")
            setContentText("Lembre-se de fazer algo")
        }

        notificationManager.notify(1,notificacaoBuilder.build())
    }
}