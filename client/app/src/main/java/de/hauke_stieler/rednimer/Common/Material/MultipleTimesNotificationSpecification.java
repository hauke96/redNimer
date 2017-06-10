package de.hauke_stieler.rednimer.Common.Material;

import java.util.Calendar;

/**
 * Created by hauke on 11.06.17.
 */

public class MultipleTimesNotificationSpecification implements INotificationSpecification {
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
}
