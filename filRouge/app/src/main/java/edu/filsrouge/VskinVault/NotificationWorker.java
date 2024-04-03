package edu.filsrouge.VskinVault;

import android.content.Context;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

/**
 * Classe NotificationWorker qui étend Worker.
 * Cette classe est responsable de la gestion des tâches en arrière-plan pour les notifications.
 */
public class NotificationWorker extends Worker {

    /**
     * Constructeur de la classe NotificationWorker.
     * @param context Le contexte dans lequel le NotificationWorker est créé.
     * @param workerParams Les paramètres du travailleur.
     */
    public NotificationWorker(Context context, WorkerParameters workerParams) {
        super(context, workerParams);
    }

    /**
     * Cette méthode est appelée lorsque le travailleur commence à travailler.
     * @return Le résultat du travail, soit succès, soit échec.
     */
    @Override
    public Result doWork() {
        // Création d'une nouvelle notification
        Notification notification = new Notification(getApplicationContext());
        // Envoi de la notification avec le titre et le contenu spécifiés
        notification.sendNotification("V-Skin Vault", "Nouvel arrivage d'été !");
        // Retourne le succès du travail
        return Result.success();
    }
}