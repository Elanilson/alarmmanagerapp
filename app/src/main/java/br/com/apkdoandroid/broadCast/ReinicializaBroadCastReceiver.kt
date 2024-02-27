package br.com.apkdoandroid.broadCast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import br.com.apkdoandroid.alarmes.Agendamento

class ReinicializaBroadCastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
       if(intent.action == Intent.ACTION_BOOT_COMPLETED){
           Log.i("agedamento_android","Executou ACTION_BOOT_COMPLETED")
           val agendamento = Agendamento(context)
           agendamento.agendar3()
       }
    }
}