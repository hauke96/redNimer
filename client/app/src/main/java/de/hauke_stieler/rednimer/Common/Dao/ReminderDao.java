package de.hauke_stieler.rednimer.Common.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.util.Log;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import de.hauke_stieler.rednimer.Common.DomainValue.ID;
import de.hauke_stieler.rednimer.Common.Material.NotificationSpecification;
import de.hauke_stieler.rednimer.Common.Material.Reminder;
import de.hauke_stieler.rednimer.Technical.DatabaseUtils.DatabaseHelper;
import de.hauke_stieler.rednimer.Technical.DatabaseUtils.DatabaseScheme;
import juard.contract.Contract;

/**
 * Created by hauke on 21.07.17.
 */

public class ReminderDao implements IReminderDao {

    DatabaseHelper dbHelper;

    public ReminderDao() {
    }

    public void init(Context context) {
        Contract.NotNull(context);

        dbHelper = new DatabaseHelper(context);
    }

    @Override
    public List<Reminder> getAllReminder() {
        Log.i(getClass().getSimpleName(), "getAllReminder");

        String reminderTable = DatabaseScheme.REMINDER_TABLE_NAME;
        String specificationTable = DatabaseScheme.SPECIFICATION_TABLE_NAME;

//        String query = "SELECT * FROM " + reminderTable +
//                " INNER JOIN " + specificationTable +
//                " ON " + reminderTable + "." + DatabaseScheme.REMINDER_COLUMN_ID + "=" + specificationTable + "." + DatabaseScheme.SPECIFICATION_COLUMN_ID;
        String query = "SELECT * FROM " + reminderTable;

        Cursor cursor = dbHelper.runQuery(query);

        List<Reminder> result = new ArrayList<>();

        if (cursor.getCount() <= 0) {
            return result;
        }

        if(cursor.isBeforeFirst()) {
            cursor.moveToNext();
        }

        do {
//            NotificationSpecification specification = getNotificationSpecification(cursor);

//            Reminder reminder = getReminder(cursor, specification);

            Reminder reminder = getReminder(cursor, new NotificationSpecification(new GregorianCalendar(), 1, 1));

            result.add(reminder);

        } while (cursor.moveToNext());

        return result;
    }

    @NonNull
    private NotificationSpecification getNotificationSpecification(Cursor cursor) {
        String specificationIdString = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseScheme.SPECIFICATION_COLUMN_ID));
        int specificationRepetitionTime = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseScheme.SPECIFICATION_COLUMN_REPETITION_TIME));
        int specificationAmountOfNotifications = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseScheme.SPECIFICATION_COLUMN_AMOUNT_OF_NOTIFICATIONS));
        long specificationStartDate = cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseScheme.SPECIFICATION_COLUMN_START_DATE));

        Calendar specificationCalendar = createCalendar(specificationStartDate);

        ID<NotificationSpecification> specificationId = ID.create(NotificationSpecification.class, specificationIdString);

        return new NotificationSpecification(specificationId, specificationCalendar, specificationRepetitionTime, specificationAmountOfNotifications);
    }

    @NonNull
    private Reminder getReminder(Cursor cursor, NotificationSpecification specification) {
        String reminderIdString = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseScheme.REMINDER_COLUMN_ID));
        String reminderTitle = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseScheme.REMINDER_COLUMN_TITLE));
        String reminderDescription = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseScheme.REMINDER_COLUMN_DESCRIPTION));
        long reminderDueDate = cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseScheme.REMINDER_COLUMN_DUE_DATE));

        Calendar reminderCalendar = createCalendar(reminderDueDate);

        ID<Reminder> reminderId = ID.create(Reminder.class, reminderIdString);

        return new Reminder(reminderId, reminderTitle, reminderDescription, reminderCalendar, specification);
    }

    @NonNull
    private Calendar createCalendar(long specificationStartDate) {
        Calendar specificationCalendar = GregorianCalendar.getInstance();
        specificationCalendar.setTimeInMillis(specificationStartDate);
        return specificationCalendar;
    }

    @Override
    public boolean hasReminder(ID<Reminder> id) {
        Log.i(getClass().getSimpleName(), MessageFormat.format("hasReminder: ID<{0}> = {1}", id.getType().getSimpleName(), id.getValue()));

        String query = MessageFormat.format("SELECT * FROM {0} WHERE {1}=\"{2}\" LIMIT 1", DatabaseScheme.REMINDER_TABLE_NAME, DatabaseScheme.REMINDER_COLUMN_ID, id.getValue());

        Cursor cursor = dbHelper.runQuery(query);

        return cursor != null && cursor.getCount() > 0;
    }

    @Override
    public void add(Reminder reminder) {
        Log.i(getClass().getSimpleName(), MessageFormat.format("add: Title: {0}, ID<{1}> = {2}", reminder.getTitle(), reminder.getId().getType().getSimpleName(), reminder.getId().getValue()));

        Contract.NotNull(reminder);
        Contract.Satisfy(!hasReminder(reminder.getId()));

        ContentValues values = new ContentValues();

        values.put(DatabaseScheme.REMINDER_COLUMN_ID, reminder.getId().getValue());
        values.put(DatabaseScheme.REMINDER_COLUMN_TITLE, reminder.getTitle());
        values.put(DatabaseScheme.REMINDER_COLUMN_DESCRIPTION, reminder.getDueDateDescription());
        values.put(DatabaseScheme.REMINDER_COLUMN_DUE_DATE, reminder.getDueDate().getTimeInMillis());

        dbHelper.insert(values, DatabaseScheme.REMINDER_TABLE_NAME);
    }
}
