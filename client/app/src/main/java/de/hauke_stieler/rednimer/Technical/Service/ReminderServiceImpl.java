package de.hauke_stieler.rednimer.Technical.Service;

import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import de.hauke_stieler.rednimer.Common.Dao.IReminderDao;
import de.hauke_stieler.rednimer.Common.Material.Reminder;
import de.hauke_stieler.rednimer.Common.ServiceInterface.IReminderService;
import juard.contract.Contract;

/**
 * Created by hauke on 01.08.17.
 */
public class ReminderServiceImpl implements IReminderService {

    private IReminderDao _reminderDao;

    public ReminderServiceImpl(IReminderDao reminderDao){
        Contract.NotNull(reminderDao);

        _reminderDao = reminderDao;
    }

    @Override
    public void add(Reminder reminder) {
        Contract.NotNull(reminder);

        if(!_reminderDao.hasReminder(reminder.getId())) {
            _reminderDao.add(reminder);
        }
        else{
            Log.e(this.getClass().getSimpleName(), "add: Reminder already exists!");
        }
    }

    @Override
    public List<Reminder> getAll(Calendar date) {
        Contract.NotNull(date);

        List<Reminder> result = new ArrayList<>();

        List<Reminder> reminders = _reminderDao.getAllReminder();
        for (Reminder reminder : reminders) {

            if(isOnSameDay(date, reminder)){
                result.add(reminder);
            }
        }

        Contract.NotNull(result);
        return result;
    }

    private boolean isOnSameDay(Calendar date, Reminder reminder) {
        Calendar dueDate = reminder.getDueDate();

        return dueDate.get(Calendar.YEAR) == date.get(Calendar.YEAR) &&
                dueDate.get(Calendar.DAY_OF_YEAR) == date.get(Calendar.DAY_OF_YEAR);
    }
}
