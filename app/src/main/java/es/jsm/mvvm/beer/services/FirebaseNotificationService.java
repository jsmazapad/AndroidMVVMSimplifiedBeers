package es.jsm.mvvm.beer.services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import es.jsm.mvvm.beer.R;
import es.jsm.mvvm.beer.ui.splash.SplashActivity;


/**
 * Servicio para la recepción de mensajes PUSH
 * Sobreescribe la recepción de mensajes para crear una notificación también cuando la app está abierta
 */
public class FirebaseNotificationService extends FirebaseMessagingService {
    private static final int NOTIF_ALERT_ID = 240720;
    private static final String CHANNEL_ID = "BEERS";
    public static final String TOPIC_MESSAGE_KEY = "from";
    public static final String NODE_MESSAGE_KEY = "node";
    private  NotificationManager notificationManager;

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
    }

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
            //Al recibir un nuevo token nos suscribimos al topic de nuevo
            try {
                FirebaseMessaging.getInstance().subscribeToTopic("global");
            } catch (IllegalStateException e) {
                //Solicitado que la Aplicación siga como si nada
            }

    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        //Este método sólo es llamado si la app está activa,
        // en caso contrario va directamente a la bandeja de notificaciones del sistema
        Log.d("Firebase", "From: " + remoteMessage.getFrom());

        String messageTitle = "";
        String messageBody = "";

        if (remoteMessage.getNotification() != null) {

            messageTitle = remoteMessage.getNotification().getTitle();
            messageBody = remoteMessage.getNotification().getBody();

            notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            //En SDK > 26 se requiere un canal de notificaciones
            if (Build.VERSION.SDK_INT >= 26) {

                NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                        CHANNEL_ID,
                        NotificationManager.IMPORTANCE_DEFAULT);
                channel.setDescription("Notificaciones de APP");

                notificationManager.createNotificationChannel(channel);

            }

            NotificationCompat.Builder notificationMessage = new NotificationCompat.Builder(this, CHANNEL_ID);
            notificationMessage.setContentTitle(messageTitle);
            notificationMessage.setContentText(messageBody);
            notificationMessage.setSmallIcon(R.drawable.ic_catalog);
            try {
                notificationMessage.setLargeIcon((((BitmapDrawable) ContextCompat.getDrawable(this, R.mipmap.ic_launcher)).getBitmap()));
            }catch (NullPointerException exception){
                exception.printStackTrace();
            }
            notificationMessage.setChannelId(CHANNEL_ID);
            notificationMessage.setAutoCancel(true);
            // Definimos a que activity iremos si el usuario pulsa sobre la notificación
            Intent notIntent = new Intent(this, SplashActivity.class);

            // Check if message contains a data payload.
            Bundle bundle = new Bundle();
            if(remoteMessage.getFrom() != null) {
                bundle.putString(TOPIC_MESSAGE_KEY, remoteMessage.getFrom());
                notIntent.putExtra(TOPIC_MESSAGE_KEY, remoteMessage.getFrom() );
            }
            if (remoteMessage.getData().size() > 0 && remoteMessage.getData().get(NODE_MESSAGE_KEY) != null) {
                Log.d("Firebase", "Message data payload: " + remoteMessage.getData());
                bundle.putString(NODE_MESSAGE_KEY, remoteMessage.getData().get(NODE_MESSAGE_KEY));
                notIntent.putExtra(NODE_MESSAGE_KEY, remoteMessage.getData().get(NODE_MESSAGE_KEY) );
            }
            notificationMessage.setExtras(bundle);
            PendingIntent contIntent = PendingIntent.getActivity(this, 0, notIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_ONE_SHOT);
            notificationMessage.setContentIntent(contIntent);
            Notification notification = notificationMessage.build();

            // Sonido por defecto de notificación
            // Uso en API 10 o menor, lo dejamos por motivos de retrocompatibilidad del servicio
            notification.sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            notificationManager.notify(NOTIF_ALERT_ID, notification);

        }
    }



}
