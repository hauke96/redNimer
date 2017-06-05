package de.hauke_stieler.rednimer.ReminderCreator.View;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.view.View;

import de.hauke_stieler.rednimer.R;

public class ReminderCreatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_creator);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("New reminder");

        SwitchCompat switchControl = (SwitchCompat) findViewById(R.id.creatorNotificationSwitch);
        switchControl.setOnCheckedChangeListener((buttonView, isChecked) -> {
            toggleNotificationLayoutVisibility(isChecked);
        });

        toggleNotificationLayoutVisibility(false);
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
}
