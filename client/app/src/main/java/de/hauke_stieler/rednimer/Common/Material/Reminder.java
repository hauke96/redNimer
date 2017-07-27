package de.hauke_stieler.rednimer.Common.Material;

import java.util.Calendar;

import de.hauke_stieler.rednimer.Common.DomainValue.ID;
import de.hauke_stieler.rednimer.Common.DomainValue.TimeUnit;
import de.hauke_stieler.rednimer.Common.Technical.DateTimeFormatter;
import juard.contract.Contract;

/**
 * Created by hauke on 30.05.17.
 */
public class Reminder {
    private final String _title;
    private final String _description;
    private final Calendar _dueDate;
    private final NotificationSpecification _notificationSpecification;

    @Deprecated
    public Reminder(String title, String description, Calendar dueDate) {
        this(ID.create(Reminder.class), title, description, dueDate);
    }

    @Deprecated
    public Reminder(ID<Reminder> id, String title, String description, Calendar dueDate) {
        this(id, title, description, dueDate, OneTimeNotificationSpecification.getInstance(dueDate, 10, TimeUnit.MINUTE));
    }

    public Reminder(String title, String description, Calendar dueDate, NotificationSpecification notificationSpecification) {
        this(ID.create(Reminder.class), title, description, dueDate, notificationSpecification);
    }

    public Reminder(ID<Reminder> id, String title, String description, Calendar dueDate, NotificationSpecification notificationSpecification) {
        Contract.NotNull(id);
        Contract.NotNullOrEmpty(title);
        Contract.NotNullOrEmpty(description);
        Contract.NotNull(dueDate);
        Contract.NotNull(notificationSpecification);

        _title = title;
        _dueDate = (Calendar) dueDate.clone();
        _description = description;
        _notificationSpecification = notificationSpecification;
    }

    public String getTitle() {
        return _title;
    }

    public Calendar getDueDate() {
        return (Calendar) _dueDate.clone();
    }

    //TODO next to this: add getter for real description
    public String getDueDateDescription() {
        return DateTimeFormatter.formatDate(_dueDate) + " at " + DateTimeFormatter.formatTime(_dueDate);
    }

    public NotificationSpecification getNotificationSpecification() {
        return _notificationSpecification;
    }
}
