package de.hauke_stieler.rednimer.ReminderCreator.View;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;
import java.util.GregorianCalendar;

import de.hauke_stieler.rednimer.Common.DomainValue.TimeUnit;
import de.hauke_stieler.rednimer.Common.Material.MultipleTimesNotificationSpecification;
import de.hauke_stieler.rednimer.Common.Material.NotificationSpecification;
import de.hauke_stieler.rednimer.Common.Material.OneTimeNotificationSpecification;
import de.hauke_stieler.rednimer.Common.Material.Reminder;
import de.hauke_stieler.rednimer.Common.ServiceInterface.AbstractReminderService;
import de.hauke_stieler.rednimer.Common.Technical.DateTimeFormatter;
import de.hauke_stieler.rednimer.R;
import juard.contract.Contract;
import juard.injection.Locator;

public class ReminderCreatorActivity extends AppCompatActivity {
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

        setDueDateLayoutVisibility(false);
        setNotificationLayoutVisibility(false);
        setEndDateChooserVisibility(false);

        Contract.NotNull(_selectedDate);
        Contract.NotNull(_reminderService);
    }

    private void registerListener() {
        SwitchCompat dueDateSwitchControl = (SwitchCompat) findViewById(R.id.creatorDueDateSwitch);
        dueDateSwitchControl.setOnCheckedChangeListener((buttonView, isChecked) -> {
            setDueDateLayoutVisibility(isChecked);
        });

        SwitchCompat notificationSwitchControl = (SwitchCompat) findViewById(R.id.creatorNotificationSwitch);
        notificationSwitchControl.setOnCheckedChangeListener((buttonView, isChecked) -> {
            setNotificationLayoutVisibility(isChecked);
        });

        Spinner dueDateEndSpinner = (Spinner) findViewById(R.id.creatorMultipleDueDatesEndSpinner);
        dueDateEndSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String neverString = dueDateEndSpinner.getContext().getResources().getStringArray(R.array.multipleDueDatesChoices)[0];
                String selectedItem = (String) dueDateEndSpinner.getItemAtPosition(position);

                if (selectedItem.equals(neverString)) {
                    setEndDateChooserVisibility(false);
                } else {
                    setEndDateChooserVisibility(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        findViewById(R.id.creatorChooseDateTextView).setOnClickListener(v -> creatorChooseDateTextView_OnClick((TextView) v));
        findViewById(R.id.creatorChooseTimeTextView).setOnClickListener(v -> creatorChooseTimeTextView_OnClick((TextView) v));

        findViewById(R.id.creatorSaveButton).setOnClickListener(v -> saveReminder());
    }

    private void setDueDateLayoutVisibility(boolean visible) {
        setVisible(R.id.creatorMultipleDueDatesLayout, visible);
    }

    private void setNotificationLayoutVisibility(boolean visible) {
        setVisible(R.id.creatorMultipleNotificationsLayout, visible);
    }

    private void setEndDateChooserVisibility(boolean visible) {
        setVisible(R.id.creatorChooseEndDateTextView, visible);
    }

    private void setVisible(int viewId, boolean visible) {
        if (visible) {
            findViewById(viewId).setVisibility(View.VISIBLE);
        } else {
            findViewById(viewId).setVisibility(View.GONE);
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
        view.setText(DateTimeFormatter.formatDate(date));
    }

    private void setTimeText(TextView view, Calendar date) {
        view.setText(DateTimeFormatter.formatTime(date));
    }

    private void saveReminder() {
        String title = ((TextView) findViewById(R.id.creatorTitleEditText)).getText().toString();
        String description = ((TextView) findViewById(R.id.creatorDescriptionEditText)).getText().toString();
        NotificationSpecification notificationSpecification;

        int timeBeforeDueDate = Integer.parseInt(((EditText) findViewById(R.id.creatorOneNotificationNumberEditText)).getText().toString());
        String timeUnit = ((Spinner) findViewById(R.id.creatorOneNotificationUnitSpinner)).getSelectedItem().toString();

        boolean oneTimeNotificationChosen = !((SwitchCompat) findViewById(R.id.creatorNotificationSwitch)).isChecked();

        if (oneTimeNotificationChosen) {
            notificationSpecification = OneTimeNotificationSpecification.getInstance(_selectedDate, timeBeforeDueDate, TimeUnit.get(timeUnit));
        } else {
            int repetitionTime = Integer.parseInt(((EditText) findViewById(R.id.creatorMultipleNotificationsNumberEditText)).getText().toString());
            String repetitionTimeUnit = ((Spinner) findViewById(R.id.creatorMultipleNotificationsUnitSpinner)).getSelectedItem().toString();

            notificationSpecification = MultipleTimesNotificationSpecification.getInstance(_selectedDate, timeBeforeDueDate, TimeUnit.get(timeUnit), repetitionTime, TimeUnit.get(repetitionTimeUnit));
        }

        Reminder reminder = new Reminder(title, description, _selectedDate, notificationSpecification);

        _reminderService.add(reminder);

        onBackPressed();
    }
}
