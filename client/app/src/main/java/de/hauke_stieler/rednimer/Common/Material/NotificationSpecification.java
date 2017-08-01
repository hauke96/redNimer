package de.hauke_stieler.rednimer.Common.Material;

import java.util.Calendar;

import de.hauke_stieler.rednimer.Common.DomainValue.ID;
import de.hauke_stieler.rednimer.R;
import juard.contract.Contract;

/**
 * Created by hauke on 11.06.17.
 */

public class NotificationSpecification {
    private static final int SMALL_ICON = R.drawable.ic_add_alarm_black_32dp;

    private ID<NotificationSpecification> _id;
    private final Calendar _startDate;
    private final int _repetitionTimeInMillis;
    private int _amountOfNotifications;

    @Deprecated
    public NotificationSpecification(Calendar startDate, int repetitionTimeInMillis, int amountOfNotifications) {
        this(ID.create(NotificationSpecification.class), startDate, repetitionTimeInMillis, amountOfNotifications);
    }

    public NotificationSpecification(ID<NotificationSpecification> id, Calendar startDate, int repetitionTimeInMillis, int amountOfNotifications) {
        Contract.NotNull(id);
        Contract.NotNull(startDate);
        Contract.Satisfy(repetitionTimeInMillis > 0);
        Contract.Satisfy(amountOfNotifications >= 1); // This one will be the notification at the due date

        _id = id;
        _startDate = startDate;
        _repetitionTimeInMillis = repetitionTimeInMillis;
        _amountOfNotifications = amountOfNotifications;
    }

    public ID<NotificationSpecification> getId() {
        return _id;
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
