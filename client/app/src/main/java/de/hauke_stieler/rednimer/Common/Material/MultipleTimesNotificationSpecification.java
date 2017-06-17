package de.hauke_stieler.rednimer.Common.Material;

import java.util.Calendar;

import de.hauke_stieler.rednimer.Common.DomainValue.TimeUnit;
import juard.contract.Contract;

/**
 * Created by hauke on 11.06.17.
 */

public class MultipleTimesNotificationSpecification extends NotificationSpecification {
    public MultipleTimesNotificationSpecification(Calendar startDate, int repetitionTimeInMillis, int amountOfNotifications) {
        super(startDate, repetitionTimeInMillis, amountOfNotifications);
    }

    public static MultipleTimesNotificationSpecification getInstance(Calendar dueDate, int timeBeforeDueDate, TimeUnit timeUnit, int repetitionTime, TimeUnit repetitionTimeUnit) {
        Contract.NotNull(dueDate);
        Contract.Satisfy(timeBeforeDueDate >= 0);
        Contract.NotNull(timeUnit);
        Contract.Satisfy(repetitionTime > 0);
        Contract.NotNull(repetitionTimeUnit);

        int amountOfNotifications = timeBeforeDueDate / repetitionTime + 1; // +1 for one additional notification at startDate
        int repetitionTimeInMillis = repetitionTime * repetitionTimeUnit.Milliseconds;

        Calendar startingDate = (Calendar) dueDate.clone();
        startingDate.add(Calendar.MILLISECOND, -timeBeforeDueDate * timeUnit.Milliseconds);
        // start at beginning of minute:
        startingDate.set(Calendar.SECOND, 0);
        startingDate.set(Calendar.MILLISECOND, 0);

        return new MultipleTimesNotificationSpecification(startingDate, repetitionTimeInMillis, amountOfNotifications);
    }
}
