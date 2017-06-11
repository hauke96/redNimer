package de.hauke_stieler.rednimer.Common.Material;

import java.util.Calendar;

/**
 * Created by hauke on 11.06.17.
 */

public class MultipleTimesNotificationSpecification implements INotificationSpecification {
    private boolean _hasBeenRaised;

    @Override
    public Calendar getStartingPoint() {
        return null;
    }

    @Override
    public int getFrequencyInMillis() {
        return 0;
    }

    @Override
    public boolean isOneTimeNotification() {
        return false;
    }

    @Override
    public void setIsRaised() {
        _hasBeenRaised = true;
    }

    @Override
    public boolean hasBeenRaised() {
        return _hasBeenRaised;
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
