package com.example.appreceptora
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class NotificationReceiver : BroadcastReceiver() {

    private val CHANNEL_ID = "channelId"
    private val NOTIFICATION_ID = 1

    override fun onReceive(context: Context?, intent: Intent?) {
        // Manejar la recepción del mensaje

        val mensaje = intent?.getStringExtra("mensaje")
        Log.d("AppReceptora", mensaje.toString())
        showNotification(context, mensaje ?: "Mensaje vacío")
    }

    @SuppressLint("MissingPermission","Solicitado en MainActivity")
    private fun showNotification(context: Context?, message: String) {
        // Crear un canal de notificación (necesario para Android 8.0 y versiones superiores)
        createNotificationChannel(context)

        // Crear la notificación
        val notification = NotificationCompat.Builder(context!!, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notificacion)
            .setContentTitle("Nuevo Mensaje")
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .build()

        // Mostrar la notificación
        val notificationManager = NotificationManagerCompat.from(context!!)
        notificationManager.notify(NOTIFICATION_ID, notification)
    }

    private fun createNotificationChannel(context: Context?) {
        // Crear el canal de notificación
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val name = "Canal de Notificación"
            val descriptionText = "Canal para mostrar notificaciones"
            val importance = NotificationManagerCompat.IMPORTANCE_MAX
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }

            // Registrar el canal en el NotificationManager
            val notificationManager =
                context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}

