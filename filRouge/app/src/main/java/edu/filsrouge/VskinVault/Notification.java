package edu.filsrouge.VskinVault;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import androidx.core.app.NotificationCompat;

import java.util.Objects;

public class Notification {
    private static final String CHANNEL_ID = "channel1";
    private final Context context;

    public Notification(Context context) {
        this.context = context;
        createNotificationChannel("V-Skin Vault", "Nouvel arrivage d'été !", NotificationManager.IMPORTANCE_DEFAULT);
    }

    private void createNotificationChannel (String name, String description, int importance){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            Objects.requireNonNull(notificationManager).createNotificationChannel(channel);
        }
    }

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