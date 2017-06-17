package de.hauke_stieler.rednimer.Common.Material;

import java.util.Calendar;

import de.hauke_stieler.rednimer.Common.DomainValue.TimeUnit;
import juard.contract.Contract;

/**
 * Created by hauke on 11.06.17.
 */

public class OneTimeNotificationSpecification extends NotificationSpecification {
    public OneTimeNotificationSpecification(Calendar startDate, int repetitionTimeInMillis, int amountOfNotifications) {
        super(startDate, repetitionTimeInMillis, amountOfNotifications);
    }

    public static OneTimeNotificationSpecification getInstance(Calendar dueDate, int timeBeforeDueDate, TimeUnit timeUnit) {
        Contract.NotNull(dueDate);
        Contract.Satisfy(timeBeforeDueDate >= 0);
        Contract.NotNull(timeUnit);

        timeBeforeDueDate = timeBeforeDueDate * timeUnit.Milliseconds;
        int amountOfNotifications = 2; // One notification before due date and one at due date

        Calendar startingDate = (Calendar) dueDate.clone();
        startingDate.add(Calendar.MILLISECOND, -timeBeforeDueDate);
        // start at beginning of minute:
        startingDate.set(Calendar.SECOND, 0);
        startingDate.set(Calendar.MILLISECOND, 0);

        return new OneTimeNotificationSpecification(startingDate, timeBeforeDueDate, amountOfNotifications);
    }
}
