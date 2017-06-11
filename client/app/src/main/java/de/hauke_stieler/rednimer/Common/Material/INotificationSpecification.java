package de.hauke_stieler.rednimer.Common.Material;

import java.util.Calendar;

/**
 * Created by hauke on 11.06.17.
 */

public interface INotificationSpecification {
    Calendar getStartingPoint();

    int getFrequencyInMillis();

    boolean isOneTimeNotification();

    void setIsRaised();

    boolean hasBeenRaised();
}
