package de.hauke_stieler.rednimer.DayOverview.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.SimpleOnPageChangeListener;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import de.hauke_stieler.rednimer.DayOverview.Adapter.DayOverviewPagerAdapter;
import de.hauke_stieler.rednimer.R;
import de.hauke_stieler.rednimer.ReminderCreator.View.ReminderCreatorActivity;

public class DayOverview extends Fragment {

    private DateFormat _dateFormat;

    public DayOverview() {
        _dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    }

    public static DayOverview newInstance() {
        DayOverview fragment = new DayOverview();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View dayOverview = inflater.inflate(R.layout.fragment_day_overview, container, false);
        final ViewPager viewPager = (ViewPager) dayOverview.findViewById(R.id.dayOverviewViewPager);

        final DayOverviewPagerAdapter pagerAdapter = new DayOverviewPagerAdapter(getFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                Date date = getSelectedDate(position, pagerAdapter);
                setTitleToDate(date);
            }
        });
        viewPager.setCurrentItem(pagerAdapter.INITIAL_POSITION);

        registerListener(dayOverview);

        return dayOverview;
    }

    @Override
    public void onResume() {
        super.onResume();

        //TODO reload reminder
    }

    private Date getSelectedDate(int position, DayOverviewPagerAdapter pagerAdapter) {
        ReminderLister item = pagerAdapter.getItem(position);
        return item.getDate();
    }

    private void setTitleToDate(Date date) {
        String dateString = _dateFormat.format(date);

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        ActionBar actionBar = activity.getSupportActionBar();
        actionBar.setTitle(dateString);
    }

    private void registerListener(View dayOverview) {
        dayOverview.findViewById(R.id.addReminderButton).setOnClickListener(v -> addReminderButton_OnClick());
    }

    public void addReminderButton_OnClick() {
        Intent intent = new Intent(getContext(), ReminderCreatorActivity.class);
        startActivity(intent);
    }
}
