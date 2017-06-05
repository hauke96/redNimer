package de.hauke_stieler.rednimer.DayOverview.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hauke_stieler.rednimer.Common.Material.Reminder;
import de.hauke_stieler.rednimer.Common.ServiceInterface.AbstractReminderService;

/**
 * Created by hauke on 30.05.17.
 */
public class DummyReminderService extends AbstractReminderService {

    private Map<String, List<Reminder>> _reminderMap;
    private DateFormat _dateFormater;

    public DummyReminderService() {
        _reminderMap = new HashMap<>();
        _dateFormater = new SimpleDateFormat("yyyy-MM-dd");


        GregorianCalendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DAY_OF_MONTH, -5);
        Date date;

        for (int i = 0; i < 10; i++) {
            date = calendar.getTime();

            // generate some random amount of reminder
            int r = (int) (Math.random() * 6);
            for (int j = 0; j < r; j++) {
                add(new Reminder(date));
            }

            // go to next day
            calendar.add(Calendar.DATE, 1);
        }
    }

    @Override
    public void add(Reminder reminder) {
        String date = _dateFormater.format(reminder.getDueDate());
        boolean keyAlreadyExists = _reminderMap.containsKey(date);

        if (!keyAlreadyExists) {
            _reminderMap.put(date, new ArrayList<>());
        }

        _reminderMap.get(date).add(reminder);

        ReminderAdded.fireEvent(new Reminder[]{reminder});
    }

    @Override
    public List<Reminder> getAll(Date date) {
        String dateString = _dateFormater.format(date);

        return _reminderMap.get(dateString);
    }
}
