package de.hauke_stieler.rednimer.DayOverview.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
    private DateFormat _dateFormatter;

    public DummyReminderService() {
        _reminderMap = new HashMap<>();
        _dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

        GregorianCalendar calendar = new GregorianCalendar();

        calendar.set(Calendar.HOUR_OF_DAY, 18);
        calendar.set(Calendar.MINUTE, 00);
        add(new Reminder("Einkaufen", "* Nudeln\n*Wasser\n*Bier", calendar));

        calendar.set(Calendar.HOUR_OF_DAY, 19);
        calendar.set(Calendar.MINUTE, 30);
        add(new Reminder("Lernen", "Für Mathe lernen", calendar));

        // go to next day
        calendar.add(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 12);
        calendar.set(Calendar.MINUTE, 00);
        add(new Reminder("Arzt", "Um 13:15 Zahnarzt", calendar));

        // go to next day
        calendar.add(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 20);
        calendar.set(Calendar.MINUTE, 30);
        add(new Reminder("Freunde", "Freunde in der Kneipe treffen", calendar));
    }

    @Override
    public void add(Reminder reminder) {
        String date = _dateFormatter.format(reminder.getDueDate());
        boolean keyAlreadyExists = _reminderMap.containsKey(date);

        if (!keyAlreadyExists) {
            _reminderMap.put(date, new ArrayList<>());
        }

        _reminderMap.get(date).add(reminder);
        ReminderAdded.fireEvent(new Reminder[]{reminder});
    }

    @Override
    public List<Reminder> getAll(Calendar date) {
        String dateString = _dateFormatter.format(date);

        return _reminderMap.get(dateString);
    }
}
