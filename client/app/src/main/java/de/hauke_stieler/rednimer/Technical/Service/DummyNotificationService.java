package de.hauke_stieler.rednimer.Technical.Service;


import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import de.hauke_stieler.rednimer.Common.Material.Reminder;
import de.hauke_stieler.rednimer.Common.ServiceInterface.INotificationService;
import de.hauke_stieler.rednimer.R;
import juard.contract.Contract;

/**
 * Created by hauke on 10.06.17.
 */

public class DummyNotificationService implements INotificationService {

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
        Timer result = new Timer();

        result.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                raiseNotifiation(reminder.getTitle(), context);
            }
        }, reminder.getDueDate().getTime(), 10000 /* TODO add real frequency */);

        return result;
    }

    private void raiseNotifiation(String title, Context context) {
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
}
