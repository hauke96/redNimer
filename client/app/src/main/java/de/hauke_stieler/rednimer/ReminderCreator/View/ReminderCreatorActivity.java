package de.hauke_stieler.rednimer.ReminderCreator.View;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import de.hauke_stieler.rednimer.R;

public class ReminderCreatorActivity extends AppCompatActivity {
    private static DateFormat _dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.GERMANY);
    private static DateFormat _timeFormat = new SimpleDateFormat("HH:mm");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_creator);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Reminder editor");

        registerListener();

        Calendar calendar = GregorianCalendar.getInstance();
        calendar.add(Calendar.DATE, 1);

        setDateText((TextView) findViewById(R.id.creatorChooseDateTextView), calendar.getTime());
        setTimeText((TextView) findViewById(R.id.creatorChooseTimeTextView), calendar.getTime());

        toggleNotificationLayoutVisibility(false);
    }

    private void registerListener() {
        SwitchCompat switchControl = (SwitchCompat) findViewById(R.id.creatorNotificationSwitch);
        switchControl.setOnCheckedChangeListener((buttonView, isChecked) -> {
            toggleNotificationLayoutVisibility(isChecked);
        });

        findViewById(R.id.creatorChooseDateTextView).setOnClickListener(v -> creatorChooseDateTextView_OnClick((TextView) v));
        findViewById(R.id.creatorChooseTimeTextView).setOnClickListener(v -> creatorChooseTimeTextView_OnClick((TextView) v));
    }

    private void toggleNotificationLayoutVisibility(boolean beforeLayoutChosen) {
        if (beforeLayoutChosen) {
            findViewById(R.id.creatorFrequencyLayout).setVisibility(View.VISIBLE);
            findViewById(R.id.creatorBeforeLayout).setVisibility(View.GONE);
        } else {
            findViewById(R.id.creatorFrequencyLayout).setVisibility(View.GONE);
            findViewById(R.id.creatorBeforeLayout).setVisibility(View.VISIBLE);
        }
    }

    private void creatorChooseDateTextView_OnClick(TextView view) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(), (dialogView, year, monthOfYear, dayOfMonth) ->
        {
            GregorianCalendar calendar = new GregorianCalendar(year, monthOfYear, dayOfMonth);
            setDateText(view, calendar.getTime());
        },
                2017, 06, 05);
        datePickerDialog.show();
    }

    private void creatorChooseTimeTextView_OnClick(TextView view) {
        TimePickerDialog timePickerDialog = new TimePickerDialog(view.getContext(), (view1, hourOfDay, minute) -> {
            GregorianCalendar calendar = new GregorianCalendar();

            calendar.set(GregorianCalendar.HOUR_OF_DAY, hourOfDay);
            calendar.set(GregorianCalendar.MINUTE, minute);

            setTimeText(view, calendar.getTime());
        }, 12, 0, true);
        timePickerDialog.show();
    }

    private void setDateText(TextView view, Date date) {
        view.setText(_dateFormat.format(date.getTime()));
    }

    private void setTimeText(TextView view, Date date) {
        view.setText("at " + _timeFormat.format(date.getTime()));
    }
}
