package de.hauke_stieler.rednimer.Common.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.Date;
import java.util.GregorianCalendar;

import de.hauke_stieler.rednimer.Common.Material.NotificationSpecification;
import de.hauke_stieler.rednimer.Common.Material.Reminder;
import de.hauke_stieler.rednimer.Technical.DatabaseUtils.DatabaseHelper;
import de.hauke_stieler.rednimer.Technical.DatabaseUtils.DatabaseScheme;
import juard.contract.Contract;

/**
 * Created by hauke on 21.07.17.
 */

public class ReminderDao implements IReminderDao{

    DatabaseHelper dbHelper;

    public ReminderDao(Context context) {
        Contract.NotNull(context);

        dbHelper = new DatabaseHelper(context);
    }

    @Override
    public Reminder getAllReminder() {
        String reminderTable = DatabaseScheme.REMINDER_TABLE_NAME;
        String specificationTable = DatabaseScheme.SPECIFICATION_TABLE_NAME;

        String query = "SELECT * FROM "+ reminderTable +
                " INNER JOIN "+ specificationTable +
                " ON "+ reminderTable +"."+DatabaseScheme.REMINDER_COLUMN_ID + "="+ specificationTable +"."+DatabaseScheme.SPECIFICATION_COLUMN_ID;

        Cursor cursor = dbHelper.runQuery(query);

        String specificationId = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseScheme.SPECIFICATION_COLUMN_ID));
        int specificationRepititionTime = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseScheme.SPECIFICATION_COLUMN_REPETITION_TIME));
        int specificationAmountOfNotifications = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseScheme.SPECIFICATION_COLUMN_AMOUNT_OF_NOTIFICATIONS));
        long specificationStartDate = cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseScheme.SPECIFICATION_COLUMN_START_DATE));

        String reminderId = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseScheme.REMINDER_COLUMN_ID));
        String reminderTitle = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseScheme.REMINDER_COLUMN_TITLE));
        String reminderDescription = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseScheme.REMINDER_COLUMN_DESCRIPTION));
        long reminderDueDate = cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseScheme.REMINDER_COLUMN_DUE_DATE));

        Date date = new Date(specificationStartDate);
        //TODO create specification and reminder here
        //new NotificationSpecification(date, specificationRepititionTime, specificationAmountOfNotifications);
        return null;
    }

    @Override
    public boolean hasReminder(String id) {
        return false;
    }

    @Override
    public void add(Reminder reminder) {
        ContentValues values = new ContentValues();

        values.put(DatabaseScheme.REMINDER_COLUMN_TITLE, reminder.getTitle());
        values.put(DatabaseScheme.REMINDER_COLUMN_DESCRIPTION, reminder.getDueDateDescription());
        values.put(DatabaseScheme.REMINDER_COLUMN_DUE_DATE, reminder.getDueDate().getTimeInMillis());

        dbHelper.insert(values, DatabaseScheme.REMINDER_TABLE_NAME);
    }
}
