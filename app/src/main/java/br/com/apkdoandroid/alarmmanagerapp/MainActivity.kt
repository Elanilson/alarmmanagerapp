package br.com.apkdoandroid.alarmmanagerapp

import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import br.com.apkdoandroid.alarmes.Agendamento
import br.com.apkdoandroid.alarmmanagerapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val agendamento by lazy { Agendamento(this) }
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        solicitarPermissao()
        binding.buttonAgendar.setOnClickListener { agendamento.agendar3() }
        binding.buttonCancelar.setOnClickListener { agendamento.cancelar() }

    }

    fun solicitarPermissao(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            val permissaoNotificacao = ActivityCompat.checkSelfPermission(
                this,android.Manifest.permission.POST_NOTIFICATIONS
            )
            if(permissaoNotificacao == PackageManager.PERMISSION_DENIED){
                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),100)

            }

        }
    }
}