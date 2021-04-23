package com.example.gangelkotlin.firebase

import android.R
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.gangelkotlin.MotionDetector
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class MyFirebaseInstanceIdService: FirebaseMessagingService()
{
    companion object{
        const val TAB = "MyFirebaseMsgService"
    }


    override fun onNewToken(refreshedToken: String) {
        super.onNewToken(refreshedToken)
        // Get updated InstanceID token.
        val token = FirebaseInstanceId.getInstance().token
        Log.d(TAG, "Refreshed token: $token")

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
       //sendRegistrationToServer(refreshedToken)
    }




   override  fun onMessageReceived(remoteMessage: RemoteMessage) {

        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
       Log.d(TAG, "From:  ${remoteMessage.from}")

        // Check if message contains a data payload.
        if (remoteMessage.data.size > 0) {
            Log.d(TAG, "Message data payload: ${remoteMessage.data}")
            //notification channel and manager code

            val notifyID = 1
            val CHANNEL_ID = "my_channel_02"
            val name: CharSequence = "distance"
            val importance = NotificationManager.IMPORTANCE_HIGH


            if (remoteMessage.data.isNotEmpty()) {

                Log.d(TAG, "Message data payload: ${remoteMessage.data}")

                val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager?
                if (Build.VERSION.SDK_INT >= 26) {
                    if (getApplicationContext() != null) {
                        val channel = NotificationChannel(CHANNEL_ID, name, importance)
                        manager!!.createNotificationChannel(channel)

                            val notification: Notification = Notification.Builder(getApplicationContext(), CHANNEL_ID)
                                    .setSmallIcon(com.example.gangelkotlin.R.drawable.shield2)
                                    .setContentTitle(remoteMessage.getData().get("title"))
                                    .setContentText(remoteMessage.getData().get("text") )
                                    .setAutoCancel(true)
                                    .build()
                            manager.notify(notifyID, notification)



                    }
                }


            }
            if ( /* Check if data needs to be processed by long running job */true) {
                // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.
                scheduleJob()
            } else {
                // Handle message within 10 seconds
                handleNow()
            }
        }

        // Check if message contains a notification payload.
        remoteMessage.notification?.let {

        }
           // Log.d(TAG, "Message Notification Body: " + remoteMessage.notification!!.body)
       if (remoteMessage.getNotification() != null) {
           Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification()!!.getBody());
       }

    }

    private fun scheduleJob() {
        // [START dispatch_job]
        val work: OneTimeWorkRequest = OneTimeWorkRequest.Builder(MyWorker::class.java)
                .build()
        WorkManager.getInstance().beginWith(work).enqueue()
        // [END dispatch_job]
    }

    /**
     * Handle time allotted to BroadcastReceivers.
     */
    private fun handleNow() {
        Log.d(TAG, "Short lived task is done.")
    }

//    private fun sendNotification(messageBody: String) {
//        val intent = Intent(this, MotionDetector::class.java)
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//        val pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
//                PendingIntent.FLAG_ONE_SHOT)
//
//        val channelId = "my_channel_02"
//        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
//        val notificationBuilder = NotificationCompat.Builder(this, channelId)
//                .setSmallIcon(R.drawable.ic_lock_idle_alarm)
//                .setContentTitle("title")
//                .setContentText(messageBody)
//                .setAutoCancel(true)
//                .setSound(defaultSoundUri)
//                .setContentIntent(pendingIntent)
//
//        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//
//        // Since android Oreo notification channel is needed.
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val channel = NotificationChannel(channelId,
//                    "Channel human readable title",
//                    NotificationManager.IMPORTANCE_DEFAULT)
//            notificationManager.createNotificationChannel(channel)
//        }
//
//        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build())
//
//    }

}
