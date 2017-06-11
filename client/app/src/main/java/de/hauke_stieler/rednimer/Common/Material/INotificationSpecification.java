package de.hauke_stieler.rednimer.Common.Material;

import java.util.Calendar;

import de.hauke_stieler.rednimer.R;

/**
 * Created by hauke on 11.06.17.
 */

public interface INotificationSpecification {
    int SMALL_ICON = R.drawable.ic_add_alarm_black_32dp;

    Calendar getStartingPoint();

    int getFrequencyInMillis();

    boolean isOneTimeNotification();

    void setIsRaised();

    boolean hasBeenRaised();

    boolean isFinished();
}
