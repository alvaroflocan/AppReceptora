package com.example.appreceptora

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class NotificacionReceiver : BroadcastReceiver() {
    // Se establecen los valores del ID del canl y de la notifivación

    private val ID_CANAL = "app_receptora_01"
    private val idNotificacion = 102
    private lateinit var notificationManager: NotificationManager

    /**
     * Método que sobreescribe la función onRecive
     */
    override fun onReceive(context: Context?, intent: Intent?) {
        val message = intent?.getStringExtra("message")
        notificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        showNotification(message ?: "Mensaje vacío", context)
    }

    private fun showNotification(message: String, context: Context?) {

        val notification = NotificationCompat.Builder(context!!, ID_CANAL)
            .setSmallIcon(R.drawable.ic_notificacion)
            .setContentTitle("Nuevo Mensaje")
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        // Mostrar la notificación
        notificationManager.notify(idNotificacion, notification)
    }
}
