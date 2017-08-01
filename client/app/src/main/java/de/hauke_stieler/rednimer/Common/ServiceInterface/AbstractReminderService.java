package de.hauke_stieler.rednimer.Common.ServiceInterface;

import java.util.Calendar;
import java.util.List;

import de.hauke_stieler.rednimer.Common.Material.Reminder;
import juard.event.EventArgs;

/**
 * Created by hauke on 30.05.17.
 */
//TODO why is this an abstract class? if not necessary, turn into interface
public abstract class AbstractReminderService {
    public final EventArgs ReminderAdded = new EventArgs();

    public abstract void add(Reminder reminder);

    public abstract List<Reminder> getAll(Calendar date);
}
