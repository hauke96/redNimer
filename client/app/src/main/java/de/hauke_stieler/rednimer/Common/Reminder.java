package de.hauke_stieler.rednimer.Common;

import java.util.Date;

/**
 * Created by hauke on 30.05.17.
 */
public class Reminder {
    private String _title;
    private String _remindingDescription;
    private Date _dueDate;

    public Reminder(Date dueDate) {
        _dueDate = dueDate;

        _title = "A great reminder";
        _remindingDescription = "18:00 - 23:00 every 3h";
    }

    public String getTitle() {
        return _title;
    }

    public Date getDueDate() {
        return _dueDate;
    }

    public String getRemindingDescription() {
        return _remindingDescription;
    }
}
