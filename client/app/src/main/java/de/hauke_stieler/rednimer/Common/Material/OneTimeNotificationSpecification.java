package de.hauke_stieler.rednimer.Common.Material;

import java.util.Calendar;

import de.hauke_stieler.rednimer.Common.DomainValue.TimeUnit;
import juard.contract.Contract;

/**
 * Created by hauke on 11.06.17.
 */

public class OneTimeNotificationSpecification implements INotificationSpecification {

    private final Calendar _dueDate;
    private final Calendar _startingDate;
    private final int _timeBeforeDueDate;
    private int _amountOfRaisings; // amount of notifications which will be raised until due date

    /**
     * @param timeBeforeDueDate Amount of time before due date (e.g. in Weeks)
     * @param timeUnit          Unit if the {@code timeBeforeDueDate} param (e.g. Week, Day, ...)
     */
    public OneTimeNotificationSpecification(Calendar dueDate, int timeBeforeDueDate, TimeUnit timeUnit) {
        Contract.NotNull(dueDate);
        Contract.Satisfy(timeBeforeDueDate >= 0);
        Contract.NotNull(timeUnit);

        _dueDate = dueDate;

        _timeBeforeDueDate = timeBeforeDueDate * timeUnit.Milliseconds;
        _amountOfRaisings = 2; // One notification before due date and one at due date

        _startingDate = (Calendar) dueDate.clone();
        _startingDate.add(Calendar.MILLISECOND, -_timeBeforeDueDate);
        // start at beginning of minute:
        _startingDate.set(Calendar.SECOND, 0);
        _startingDate.set(Calendar.MILLISECOND, 0);
    }

    @Override
    public Calendar getStartingPoint() {
        return (Calendar) _startingDate.clone();
    }

    @Override
    public int getFrequencyInMillis() {
        return _timeBeforeDueDate;
    }

    @Override
    public boolean isOneTimeNotification() {
        return true;
    }

    @Override
    public void setIsRaised() {
        if (_amountOfRaisings > 0) _amountOfRaisings--;
    }

    @Override
    public boolean hasBeenRaised() {
        return _amountOfRaisings <= 1;
    }

    @Override
    public boolean isFinished() {
        return _amountOfRaisings <= 0;
    }
}
