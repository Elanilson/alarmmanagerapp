package br.com.apkdoandroid.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import br.com.apkdoandroid.alarmmanagerapp.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MeuService : Service() {

    private val coroutine = CoroutineScope(Dispatchers.IO)

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val idCanal = "lembrete"
        val notificationManager = applicationContext.getSystemService(NotificationManager::class.java)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val canal = NotificationChannel(idCanal,"Lembretes", NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(canal)
        }

        val notificacaoBuilder = NotificationCompat.Builder(applicationContext,idCanal).apply {
            setSmallIcon(R.drawable.ic_launcher_foreground)
            setShowWhen(true)
            setContentTitle("Lembrete")
            setContentText("Lembre-se de fazer algo")
        }

        startForeground(1,notificacaoBuilder.build())
        executarAcao()
        return super.onStartCommand(intent, flags, startId)
    }

    private fun executarAcao() {
        coroutine.launch {
            repeat(12){contador ->
                delay(2000L)
                Log.i("agedamento_android","Ação executada: ${contador}")
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutine.cancel()
    }
}