package com.example.appreceptora

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.wifi.WifiManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
private val ID_CANAL = "app_receptora_01"
private val idNotificacion = 102
private val conexionReceiver = object : BroadcastReceiver() {
    /**
     * Necesita el contexto y el intent para pasar la información
     */
    override fun onReceive(context: Context, intent: Intent) {

        val mensajeapp1 = intent?.extras?.getString("mensaje") ?: "Sin mensaje"
        // Intent para reiniciar la aplicación cuando se haga clic en la notificación

        val administradorNotificaciones = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Creo una notificación con el método Builder, pasando el contexto y aseguro que este no
        // será nulo, le paso además el ID del canl
        // Le paso los parámetros para crearla.
        val notificacion = NotificationCompat.Builder(context!!, ID_CANAL)
            .setContentTitle("Alarma Activada")
            .setContentText("Tu alarma programada se ha activado.")
            .setSmallIcon(R.drawable.ic_notificacion)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)  // La notificación se cierra automáticamente al hacer clic en ella
            .build()

        //Muestra la notificación
        administradorNotificaciones.notify(idNotificacion, notificacion)
    }

}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*val intentFilter = IntentFilter()
        intentFilter.addAction(Intent.ACTION_SEND)
        registerReceiver(conexionReceiver, intentFilter)*/
        val mensajeapp1 = intent?.extras?.getString("mensaje") ?: ""
        if(mensajeapp1 != "") {
            // Intent para reiniciar la aplicación cuando se haga clic en la notificación

            val administradorNotificaciones =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            // Creo una notificación con el método Builder, pasando el contexto y aseguro que este no
            // será nulo, le paso además el ID del canl
            // Le paso los parámetros para crearla.
            val notificacion = NotificationCompat.Builder(this, ID_CANAL)
                .setContentTitle("Alarma Activada")
                .setContentText("Tu alarma programada se ha activado.")
                .setSmallIcon(R.drawable.ic_notificacion)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)  // La notificación se cierra automáticamente al hacer clic en ella
                .build()

            //Muestra la notificación
            administradorNotificaciones.notify(idNotificacion, notificacion)
        }
    }
}