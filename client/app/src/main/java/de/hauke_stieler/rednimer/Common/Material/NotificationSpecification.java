package de.hauke_stieler.rednimer.Common.Material;

import java.util.Calendar;

import de.hauke_stieler.rednimer.R;
import juard.contract.Contract;

/**
 * Created by hauke on 11.06.17.
 */

public class NotificationSpecification {
    private static final int SMALL_ICON = R.drawable.ic_add_alarm_black_32dp;

    private final Calendar _startDate;
    private final int _repetitionTimeInMillis;
    private int _amountOfNotifications;

    public NotificationSpecification(Calendar startDate, int repetitionTimeInMillis, int amountOfNotifications) {
        Contract.NotNull(startDate);
        Contract.Satisfy(repetitionTimeInMillis > 0);
        Contract.Satisfy(amountOfNotifications >= 1); // This one will be the notification at the due date

        _startDate = startDate;
        _repetitionTimeInMillis = repetitionTimeInMillis;
        _amountOfNotifications = amountOfNotifications;
    }

    public Calendar getStartingPoint() {
        return _startDate;
    }

    public int getFrequencyInMillis() {
        return _repetitionTimeInMillis;
    }

    public void setIsRaised() {
        if (_amountOfNotifications > 0) _amountOfNotifications--;
    }

    public boolean isFinished() {
        return _amountOfNotifications <= 0;
    }
}
