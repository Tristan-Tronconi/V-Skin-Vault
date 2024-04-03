package edu.filsrouge.VskinVault;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import androidx.core.app.NotificationCompat;

import java.util.Objects;

/**
 * Classe Notification pour la gestion des notifications.
 * Cette classe est responsable de la création et de l'envoi des notifications.
 */
public class Notification {
    private static final String CHANNEL_ID = "channel1";
    private final Context context;

    /**
     * Constructeur de la classe Notification.
     * @param context Le contexte dans lequel la Notification est créée.
     */
    public Notification(Context context) {
        this.context = context;
        createNotificationChannel("V-Skin Vault", "Nouvel arrivage d'été !", NotificationManager.IMPORTANCE_DEFAULT);
    }

    /**
     * Cette méthode est utilisée pour créer un canal de notification.
     * @param name Le nom du canal de notification.
     * @param description La description du canal de notification.
     * @param importance Le niveau d'importance du canal de notification.
     */
    private void createNotificationChannel (String name, String description, int importance){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            Objects.requireNonNull(notificationManager).createNotificationChannel(channel);
        }
    }

    /**
     * Cette méthode est utilisée pour envoyer une notification.
     * @param title Le titre de la notification.
     * @param content Le contenu de la notification.
     */
    public void sendNotification(String title, String content) {
        NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.panier)
                .setContentTitle(title)
                .setContentText(content)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Objects.requireNonNull(notificationManager).notify(1, builder.build());
    }
}