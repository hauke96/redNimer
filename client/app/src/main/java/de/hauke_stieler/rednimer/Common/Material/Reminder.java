package de.hauke_stieler.rednimer.Common.Material;

import java.util.Calendar;

import de.hauke_stieler.rednimer.Common.Technical.DateTimeFormatter;

/**
 * Created by hauke on 30.05.17.
 */
public class Reminder {
    private final String _title;
    private final String _description;
    private boolean _oneNotification;
    private int _notificationMillisBeforeDueDate;
    private final Calendar _dueDate;

    public Reminder(String title, String description, Calendar dueDate){
        this(title, description, dueDate, true, 1, "Minute");
    }

    public Reminder(String title, String description, Calendar dueDate, boolean oneNotification, int oneNotificationNumber, String oneNotificationUnit) {
        //TODO contracts

        _title = title;
        _dueDate = (Calendar) dueDate.clone();
        _description = description;
        _oneNotification = oneNotification;
        _notificationMillisBeforeDueDate = oneNotificationNumber;
    }

    public String getTitle() {
        return _title;
    }

    public Calendar getDueDate() {
        return (Calendar) _dueDate.clone();
    }

    public String getDueDateDescription() {
        return DateTimeFormatter.formatDate(_dueDate) + " at " + DateTimeFormatter.formatTime(_dueDate);
    }
}
