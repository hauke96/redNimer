package de.hauke_stieler.rednimer.Common.Material;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by hauke on 30.05.17.
 */
public class Reminder {
    private String _title;
    private String _remindingDescription;
    private Date _dueDate;

    public Reminder(Date dueDate) {
        _dueDate = dueDate;

        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(dueDate);

        _title = "A great reminder " + calendar.get(Calendar.DAY_OF_WEEK);
        _remindingDescription = "18:00 - 23:00 every 3h " + (int)(Math.random()*100.0);
    }

    public String getTitle() {
        return _title;
    }

    public Date getDueDate() {
        return (Date) _dueDate.clone();
    }

    public String getRemindingDescription() {
        return _remindingDescription;
    }
}
