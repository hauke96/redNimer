package de.hauke_stieler.rednimer.Common.Material;

import java.util.Calendar;

import de.hauke_stieler.rednimer.Common.DomainValue.TimeUnit;
import juard.contract.Contract;

/**
 * Created by hauke on 11.06.17.
 */

public class MultipleTimesNotificationSpecification implements INotificationSpecification {
    private OneTimeNotificationSpecification _oneTimeNotificationSpecification;
    private int _repetitionTimeInMillis;
    private TimeUnit _repetitionTimeUnit;
    private int _amountOfRaisings; // amount of notifications which will be raised until due date

    /**
     * Create a specification for multiple repeating notifications.
     *
     * @param oneTimeNotificationSpecification The start time of the notification
     * @param repetitionTime                   The time between notifications
     * @param repetitionTimeUnit               The unit in which the repetition is (e.g. minutes)
     */
    public MultipleTimesNotificationSpecification(OneTimeNotificationSpecification oneTimeNotificationSpecification, int repetitionTime, TimeUnit repetitionTimeUnit) {
        Contract.NotNull(oneTimeNotificationSpecification);
        Contract.Satisfy(repetitionTime > 0);
        Contract.NotNull(repetitionTimeUnit);

        _oneTimeNotificationSpecification = oneTimeNotificationSpecification;
        _repetitionTimeInMillis = repetitionTime * repetitionTimeUnit.Milliseconds;
        _repetitionTimeUnit = repetitionTimeUnit;

        _amountOfRaisings = oneTimeNotificationSpecification.getFrequencyInMillis() / _repetitionTimeInMillis + 1; // +1 for the actual notification at the due date
    }

    @Override
    public Calendar getStartingPoint() {
        return _oneTimeNotificationSpecification.getStartingPoint();
    }

    @Override
    public int getFrequencyInMillis() {
        return _repetitionTimeInMillis;
    }

    @Override
    public boolean isOneTimeNotification() {
        return false;
    }

    @Override
    public void setIsRaised() {
        if (_amountOfRaisings > 0) _amountOfRaisings--;
    }

    @Override
    public boolean hasBeenRaised() {
        return _amountOfRaisings <= 1; // when one notification is left, all other has been raised
    }

    @Override
    public boolean isFinished() {
        return _amountOfRaisings <= 0;
    }
}
