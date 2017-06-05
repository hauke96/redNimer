package de.hauke_stieler.rednimer.Common.Material;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by hauke on 30.05.17.
 */
public class Reminder {
    private String _title;
    private String _description;
    private Calendar _dueDate;

    public Reminder(String title, String description, Date dueDate) {
        _dueDate = new GregorianCalendar();
        _dueDate.setTime(dueDate);

        _title = title;
        _description = description;
    }

    public String getTitle() {
        return _title;
    }

    public Date getDueDate() {
        return (Date) _dueDate.getTime().clone();
    }

    public String getDueDateDescription() {
        return _dueDate.toString();
    }
}
