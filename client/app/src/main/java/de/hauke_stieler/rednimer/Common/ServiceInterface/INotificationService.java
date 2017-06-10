package de.hauke_stieler.rednimer.Common.ServiceInterface;

import android.content.Context;

import de.hauke_stieler.rednimer.Common.Material.Reminder;

/**
 * Created by hauke on 10.06.17.
 */

public interface INotificationService {
    void addReminder(Reminder reminder, Context context);
}
