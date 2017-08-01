package de.hauke_stieler.rednimer.Common.Dao;

import android.content.Context;

import java.util.List;

import de.hauke_stieler.rednimer.Common.DomainValue.ID;
import de.hauke_stieler.rednimer.Common.Material.Reminder;

/**
 * Created by hauke on 21.07.17.
 */

public interface IReminderDao {
    void init(Context context);

    List<Reminder> getAllReminder();

    boolean hasReminder(ID<Reminder> id);

    void add(Reminder reminder);
}
