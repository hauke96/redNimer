package de.hauke_stieler.rednimer.Technical.DummyService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hauke_stieler.rednimer.Common.Material.Reminder;
import de.hauke_stieler.rednimer.Common.ServiceInterface.IReminderService;
import de.hauke_stieler.rednimer.Common.ServiceInterface.INotificationService;
import juard.contract.Contract;

/**
 * Created by hauke on 30.05.17.
 */
public class DummyReminderService implements IReminderService {

    private Map<String, List<Reminder>> _reminderMap;
    private DateFormat _dateFormatter;
    private INotificationService _notificationService;

    public DummyReminderService(INotificationService notificationService) {
        Contract.NotNull(notificationService);

        _notificationService = notificationService;

        _reminderMap = new HashMap<>();
        _dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
    }

    @Override
    public void add(Reminder reminder) {
        String date = _dateFormatter.format(reminder.getDueDate().getTime());
        boolean keyAlreadyExists = _reminderMap.containsKey(date);

        if (!keyAlreadyExists) {
            _reminderMap.put(date, new ArrayList<>());
        }

        _reminderMap.get(date).add(reminder);
        ReminderAdded.fireEvent(new Reminder[]{reminder});
    }

    @Override
    public List<Reminder> getAll(Calendar date) {
        String dateString = _dateFormatter.format(date.getTime());

        return _reminderMap.get(dateString);
    }
}
