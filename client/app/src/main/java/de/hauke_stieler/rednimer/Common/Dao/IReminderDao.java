package de.hauke_stieler.rednimer.Common.Dao;

import java.util.List;

import de.hauke_stieler.rednimer.Common.Material.Reminder;

/**
 * Created by hauke on 21.07.17.
 */

public interface IReminderDao {
    List<Reminder> getAllReminder();

    boolean hasReminder(String id);

    void add(Reminder reminder);
}
