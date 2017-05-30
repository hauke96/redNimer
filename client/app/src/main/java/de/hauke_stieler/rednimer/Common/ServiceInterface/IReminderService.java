package de.hauke_stieler.rednimer.Common.ServiceInterface;

import java.util.Date;

import de.hauke_stieler.rednimer.Common.Material.Reminder;

/**
 * Created by hauke on 30.05.17.
 */
public interface IReminderService {
    void add(Reminder reminder);

    void getAll(Date date);
}
