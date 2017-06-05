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
import java.util.Locale;

import de.hauke_stieler.rednimer.DayOverview.Adapter.DayOverviewPagerAdapter;
import de.hauke_stieler.rednimer.R;
import de.hauke_stieler.rednimer.ReminderCreator.View.ReminderCreatorActivity;

public class DayOverview extends Fragment {

    private DateFormat _dateFormat;
    private boolean _isResumed;

    public DayOverview() {
        _dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.GERMANY);
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
    public void onStart() {
        super.onStart();

        // directly after onStart, onResume is called. So when the activity starts, the resume-routine is actually not a resume-routine
        _isResumed = false;
    }

    @Override
    public void onResume() {
        super.onResume();

        // directly after onStart, onResume is called. So when the activity starts, the resume-routine is actually not a resume-routine
        if (_isResumed) {
            ViewPager viewPager = (ViewPager) getView().findViewById(R.id.dayOverviewViewPager);
            DayOverviewPagerAdapter adapter = (DayOverviewPagerAdapter) viewPager.getAdapter();
            adapter.getItem(viewPager.getCurrentItem()).reloadItems();
        }

        _isResumed = true;
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
