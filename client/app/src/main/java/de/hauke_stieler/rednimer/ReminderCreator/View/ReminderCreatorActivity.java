package de.hauke_stieler.rednimer.ReminderCreator.View;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import de.hauke_stieler.rednimer.R;

public class ReminderCreatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_creator);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("New reminder");
    }
}
