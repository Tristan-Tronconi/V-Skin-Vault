package edu.filsrouge.VskinVault;

import android.content.Context;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class NotificationWorker extends Worker {

    public NotificationWorker(Context context, WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @Override
    public Result doWork() {
        Notification notification = new Notification(getApplicationContext());
        notification.sendNotification("V-Skin Vault", "Nouvel arrivage d'été !");
        return Result.success();
    }
}