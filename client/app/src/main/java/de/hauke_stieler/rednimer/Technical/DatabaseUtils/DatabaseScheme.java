package de.hauke_stieler.rednimer.Technical.DatabaseUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hauke on 19.07.17.
 */

public final class DatabaseScheme {
    public static String DATABASE_NAME = "rednimer.db";

    public static String REMINDER_TABLE_NAME = "reminder";
    public static String REMINDER_COLUMN_ID = "id";
    public static String REMINDER_COLUMN_TITLE = "title";
    public static String REMINDER_COLUMN_DESCRIPTION = "description";
    public static String REMINDER_COLUMN_DUE_DATE = "due_date";
    public static String REMINDER_COLUMN_NOTIFICATION_SPECIFICATION = "notification_specification";

    public static String SPECIFICATION_TABLE_NAME = "notification_specification";
    public static String SPECIFICATION_COLUMN_ID = "id";
    public static String SPECIFICATION_COLUMN_START_DATE = "start_date";
    public static String SPECIFICATION_COLUMN_REPETITION_TIME = "repetition_time_in_millis";
    public static String SPECIFICATION_COLUMN_AMOUNT_OF_NOTIFICATIONS = "amount_of_notifications";

    /*
    CREATE TABLE rednimer
    (
        id VARCHAR(40) NOT NULL,
        title TEXT NOT NULL,
        description TEXT,
        due_date LONG NOT NULL,
        notification_specification VARCHAR(40) NOT NULL,
        PRIMARY KEY (id),
        FOREIGN KEY (notification_specification) REFERENCES notification_specification(id)
    );

    CREATE TABLE notification_specification
    (
        id VARCHAR(40) NOT NULL,
        start_date LONG NOT NULL,
        repetition_time_in_millis INT NOT NULL,
        amount_of_notifications INT NOT NULL,
        PRIMARY KEY (id)
    );
    */
    public static String CREATE_SCRIPT = "CREATE TABLE IF NOT EXISTS " + REMINDER_TABLE_NAME + "(" +
            REMINDER_COLUMN_ID + " VARCHAR(40) NOT NULL," +
            REMINDER_COLUMN_TITLE + " TEXT NOT NULL," +
            REMINDER_COLUMN_DESCRIPTION + " TEXT," +
            REMINDER_COLUMN_DUE_DATE + " LONG NOT NULL," +
            REMINDER_COLUMN_NOTIFICATION_SPECIFICATION + " VARCHAR(40) NOT NULL," +
            "PRIMARY KEY (id)," +
            "FOREIGN KEY (notification_specification) REFERENCES notification_specification(id));" +

            "CREATE TABLE IF NOT EXISTS " + SPECIFICATION_TABLE_NAME + "(" +
            SPECIFICATION_COLUMN_ID + "VARCHAR(40) NOT NULL," +
            SPECIFICATION_COLUMN_START_DATE + "LONG NOT NULL," +
            SPECIFICATION_COLUMN_REPETITION_TIME + "INT NOT NULL," +
            SPECIFICATION_COLUMN_AMOUNT_OF_NOTIFICATIONS + "INT NOT NULL, PRIMARY KEY (id));";

    public static String DROP_DATABASE_SCRIPT = "DROP DATABASE IF EXISTS " + DATABASE_NAME;
}
