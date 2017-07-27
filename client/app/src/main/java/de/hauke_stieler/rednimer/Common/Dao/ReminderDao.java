package de.hauke_stieler.rednimer.Common.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

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
    public List<Reminder> getAllReminder() {
        String reminderTable = DatabaseScheme.REMINDER_TABLE_NAME;
        String specificationTable = DatabaseScheme.SPECIFICATION_TABLE_NAME;

        String query = "SELECT * FROM "+ reminderTable +
                " INNER JOIN "+ specificationTable +
                " ON "+ reminderTable +"."+DatabaseScheme.REMINDER_COLUMN_ID + "="+ specificationTable +"."+DatabaseScheme.SPECIFICATION_COLUMN_ID;

        Cursor cursor = dbHelper.runQuery(query);

        List<Reminder> result = new ArrayList<>();

        do {
            String specificationId = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseScheme.SPECIFICATION_COLUMN_ID));
            int specificationRepetitionTime = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseScheme.SPECIFICATION_COLUMN_REPETITION_TIME));
            int specificationAmountOfNotifications = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseScheme.SPECIFICATION_COLUMN_AMOUNT_OF_NOTIFICATIONS));
            long specificationStartDate = cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseScheme.SPECIFICATION_COLUMN_START_DATE));

            Calendar specificationCalendar = GregorianCalendar.getInstance();
            specificationCalendar.setTimeInMillis(specificationStartDate);

            NotificationSpecification specification = new NotificationSpecification(specificationCalendar, specificationRepetitionTime, specificationAmountOfNotifications);

            String reminderId = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseScheme.REMINDER_COLUMN_ID));
            String reminderTitle = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseScheme.REMINDER_COLUMN_TITLE));
            String reminderDescription = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseScheme.REMINDER_COLUMN_DESCRIPTION));
            long reminderDueDate = cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseScheme.REMINDER_COLUMN_DUE_DATE));

            Calendar reminderCalendar = GregorianCalendar.getInstance();
            reminderCalendar.setTimeInMillis(reminderDueDate);

            Reminder reminder = new Reminder(reminderTitle, reminderDescription, reminderCalendar, specification);

            result.add(reminder);

        }while(cursor.moveToNext());

        return result;
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