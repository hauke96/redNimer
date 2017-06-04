package de.hauke_stieler.rednimer.ReminderCreator.View;

import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Date;

import de.hauke_stieler.rednimer.R;
import de.hauke_stieler.rednimer.ReminderCreator.Adapter.ReminderCreatorPagerAdapter;

public class ReminderCreatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_creator);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("New reminder");

        ViewPager viewPager = (ViewPager) findViewById(R.id.reminderCreatorViewPager);
        viewPager.setAdapter(new ReminderCreatorPagerAdapter(getSupportFragmentManager()));
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
            }
        });
    }
}
