package de.hauke_stieler.rednimer.ReminderCreator.View;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import de.hauke_stieler.rednimer.Common.DomainValue.TimeUnit;
import de.hauke_stieler.rednimer.Common.Material.INotificationSpecification;
import de.hauke_stieler.rednimer.Common.Material.OneTimeNotificationSpecification;
import de.hauke_stieler.rednimer.Common.Material.Reminder;
import de.hauke_stieler.rednimer.Common.ServiceInterface.AbstractReminderService;
import de.hauke_stieler.rednimer.R;
import juard.contract.Contract;
import juard.injection.Locator;

public class ReminderCreatorActivity extends AppCompatActivity {
    private static DateFormat _dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    private static DateFormat _timeFormat = new SimpleDateFormat("HH:mm");

    private Calendar _selectedDate;

    private AbstractReminderService _reminderService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_creator);

        _reminderService = Locator.get(AbstractReminderService.class);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Reminder editor");

        registerListener();

        _selectedDate = GregorianCalendar.getInstance();

        setDateText((TextView) findViewById(R.id.creatorChooseDateTextView), _selectedDate);
        setTimeText((TextView) findViewById(R.id.creatorChooseTimeTextView), _selectedDate);

        toggleDueDateLayoutVisibility(false);
        toggleNotificationLayoutVisibility(false);

        Contract.NotNull(_dateFormat);
        Contract.NotNull(_timeFormat);
        Contract.NotNull(_selectedDate);
        Contract.NotNull(_reminderService);
    }

    private void registerListener() {
        SwitchCompat dueDateSwitchControl = (SwitchCompat) findViewById(R.id.creatorDueDateSwitch);
        dueDateSwitchControl.setOnCheckedChangeListener((buttonView, isChecked) -> {
            toggleDueDateLayoutVisibility(isChecked);
        });

        SwitchCompat notificationSwitchControl = (SwitchCompat) findViewById(R.id.creatorNotificationSwitch);
        notificationSwitchControl.setOnCheckedChangeListener((buttonView, isChecked) -> {
            toggleNotificationLayoutVisibility(isChecked);
        });

        findViewById(R.id.creatorChooseDateTextView).setOnClickListener(v -> creatorChooseDateTextView_OnClick((TextView) v));
        findViewById(R.id.creatorChooseTimeTextView).setOnClickListener(v -> creatorChooseTimeTextView_OnClick((TextView) v));

        findViewById(R.id.creatorSaveButton).setOnClickListener(v -> saveReminder());
    }

    private void toggleDueDateLayoutVisibility(boolean multipleDueDatesLayoutChosen) {
        if (multipleDueDatesLayoutChosen) {
            findViewById(R.id.creatorMultipleDueDatesLayout).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.creatorMultipleDueDatesLayout).setVisibility(View.GONE);
        }
    }

    private void toggleNotificationLayoutVisibility(boolean multipleNotificationsLayoutChosen) {
        if (multipleNotificationsLayoutChosen) {
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
            _selectedDate.set(Calendar.YEAR, year);
            _selectedDate.set(Calendar.MONTH, monthOfYear);
            _selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            setDateText(view, _selectedDate);
        }, _selectedDate.get(Calendar.YEAR), _selectedDate.get(Calendar.MONTH), _selectedDate.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }

    private void creatorChooseTimeTextView_OnClick(TextView view) {
        TimePickerDialog timePickerDialog = new TimePickerDialog(view.getContext(), (view1, hourOfDay, minute) ->
        {
            _selectedDate.set(Calendar.HOUR_OF_DAY, hourOfDay);
            _selectedDate.set(Calendar.MINUTE, minute);

            setTimeText(view, _selectedDate);
        }, _selectedDate.get(Calendar.HOUR_OF_DAY), _selectedDate.get(Calendar.MINUTE), true);

        timePickerDialog.show();
    }

    private void setDateText(TextView view, Calendar date) {
        view.setText(_dateFormat.format(date.getTime()));
    }

    private void setTimeText(TextView view, Calendar date) {
        view.setText(_timeFormat.format(date.getTime()));
    }

    private void saveReminder() {
        TextView titleTextView = (TextView) findViewById(R.id.creatorTitleEditText);
        TextView descriptionTextView = (TextView) findViewById(R.id.creatorDescriptionEditText);

        INotificationSpecification notificationSpecification = null;

        // When checked, multiple notifications have been chosen
        boolean oneTimeNotification = !((SwitchCompat) findViewById(R.id.creatorNotificationSwitch)).isChecked();

        if (oneTimeNotification) {
            int timeBeforeDueDate = Integer.parseInt(((EditText) findViewById(R.id.creatorOneNotificationNumberEditText)).getText().toString());
            String timeUnit = ((Spinner) findViewById(R.id.creatorOneNotificationUnitSpinner)).getSelectedItem().toString();

            notificationSpecification = new OneTimeNotificationSpecification(_selectedDate, timeBeforeDueDate, TimeUnit.get(timeUnit));
        } else {
            Log.e("Create Reminder", "Multiple notifications not implemented yet!");
        }

        Reminder reminder = new Reminder(titleTextView.getText().toString(), descriptionTextView.getText().toString(), _selectedDate, notificationSpecification);

        _reminderService.add(reminder);

        onBackPressed();
    }
}
