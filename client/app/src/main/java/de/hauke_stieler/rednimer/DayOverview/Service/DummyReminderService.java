package de.hauke_stieler.rednimer.DayOverview.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hauke_stieler.rednimer.Common.Material.Reminder;
import de.hauke_stieler.rednimer.Common.ServiceInterface.IReminderService;

/**
 * Created by hauke on 30.05.17.
 */
public class DummyReminderService implements IReminderService {

    private Map<Date, List<Reminder>> _reminderMap;

    public DummyReminderService() {
        _reminderMap = new HashMap<>();
    }

    @Override
    public void add(Reminder reminder) {
        Date date = reminder.getDueDate();
        boolean keyAlreadyExists = _reminderMap.containsKey(date);

        if (!keyAlreadyExists) {
            _reminderMap.put(date, new ArrayList<Reminder>());
        }

        _reminderMap.get(date).add(reminder);
    }

    @Override
    public Collection<Reminder> getAll(Date date) {
        return _reminderMap.get(date);
    }
}
