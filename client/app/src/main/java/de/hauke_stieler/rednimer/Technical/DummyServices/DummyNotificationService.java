package de.hauke_stieler.rednimer.Technical.DummyServices;


import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import de.hauke_stieler.rednimer.Common.Material.NotificationSpecification;
import de.hauke_stieler.rednimer.Common.Material.Reminder;
import de.hauke_stieler.rednimer.Common.ServiceInterface.INotificationService;
import de.hauke_stieler.rednimer.Common.Technical.DateTimeFormatter;
import de.hauke_stieler.rednimer.R;
import juard.contract.Contract;

/**
 * Created by hauke on 10.06.17.
 */

public class DummyNotificationService implements INotificationService {

    public static final int VIBRATION_DURATION = 150;
    private Map<Reminder, Timer> _reminderMap;

    private static int _notificationID = 0;

    public DummyNotificationService() {
        _reminderMap = new HashMap<>();

        Contract.NotNull(_reminderMap);
    }

    private static int getNotificationID() {
        return _notificationID++;
    }

    @Override
    public void addReminder(Reminder reminder, Context context) {
        Timer timer = createTimer(reminder, context);

        _reminderMap.put(reminder, timer);
    }

    private Timer createTimer(Reminder reminder, Context context) {
        NotificationSpecification specification = reminder.getNotificationSpecification();

        Log.i("start", "createTimer: " + DateTimeFormatter.formatTime(specification.getStartingPoint()));

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                raiseNotification(reminder.getTitle(), context);
                specification.setIsRaised();

                // Show notification at due date:
                if (specification.isFinished()) {
                    try {
                        Thread.sleep(reminder.getDueDate().getTime().getTime() - System.currentTimeMillis());

                        raiseNotification(reminder.getTitle() + " actual", context);

                        timer.cancel();
                        this.cancel();
                        timer.purge();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, specification.getStartingPoint().getTime(), specification.getFrequencyInMillis());

        return timer;
    }

    private void raiseNotification(String title, Context context) {
        showPopupNotification(title, context);
        vibrate(context);
    }

    private void showPopupNotification(String title, Context context) {
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.ic_add_alarm_black_32dp)
                        .setContentTitle("redNimer - Wake up!!")
                        .setContentText(title);

        Notification notification = notificationBuilder.build();
        int notificationID = getNotificationID();

        notificationManager.notify(notificationID, notification);
    }

    public void vibrate(Context context) {
        Vibrator vibrator = (Vibrator) context.getSystemService(context.VIBRATOR_SERVICE);
        if (vibrator.hasVibrator()) {
            vibrator.vibrate(new long[]{0, VIBRATION_DURATION / 2, VIBRATION_DURATION / 2, VIBRATION_DURATION / 2, VIBRATION_DURATION / 2, VIBRATION_DURATION, VIBRATION_DURATION}, -1);
        }
    }
}
