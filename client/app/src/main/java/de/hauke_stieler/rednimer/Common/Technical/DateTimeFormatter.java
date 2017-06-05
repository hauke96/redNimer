package de.hauke_stieler.rednimer.Common.Technical;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by hauke on 05.06.17.
 */
public class DateTimeFormatter {

    private static DateFormat _dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    private static DateFormat _timeFormat = new SimpleDateFormat("HH:mm");

    public static String formatDate(Calendar calendar) {
        return _dateFormat.format(calendar.getTime());
    }

    public static String formatTime(Calendar calendar) {
        return _timeFormat.format(calendar.getTime());
    }
}
