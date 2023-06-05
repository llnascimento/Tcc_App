package com.example.sbrotina.notification

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.sbrotina.MainActivity
import com.example.sbrotina.R
import com.example.sbrotina.notification.TimerNotification.NotificationID.TIMER_ID
import com.example.sbrotina.notification.TimerNotification.NotificationID.TIMER_NOTIFICATION

class TimerNotification(private val context: Context) : BaseNotification {

    object NotificationID {
        const val TIMER_NOTIFICATION = "timerNotification"
        const val TIMER_ID = 1
    }

    private fun createTapFunction(): PendingIntent {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            Log.d("Timer" +
                    "notification", "${flags}")

        }
        /*val pendingIntent =
            PendingIntent.getActivity(context, Companion.requestCode, intent, PendingIntent.FLAG_MUTABLE)

        return pendingIntent*/

        return PendingIntent.getActivity(
            context, 0,
            intent, PendingIntent.FLAG_IMMUTABLE
        )
    }

    private fun createNotification(): NotificationCompat.Builder {

        return NotificationCompat.Builder(context, TIMER_NOTIFICATION)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(context.resources.getString(R.string.app_name))
            .setOngoing(true)
            .setContentIntent(createTapFunction())
            .setOnlyAlertOnce(true)
    }

    override fun show(text: String) {
        val notificationManager = NotificationManagerCompat.from(context)

        notificationManager.notify(
            TIMER_ID, this.createNotification()
                .setContentText(text)
                .build()
        )
    }

    companion object {
        const val requestCode = NotificationID.TIMER_ID
    }
}





