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

import java.util.Calendar;

import de.hauke_stieler.rednimer.Common.Dao.IReminderDao;
import de.hauke_stieler.rednimer.Common.Material.Reminder;
import de.hauke_stieler.rednimer.Common.ServiceInterface.INotificationService;
import de.hauke_stieler.rednimer.Common.ServiceInterface.IReminderService;
import de.hauke_stieler.rednimer.Common.Technical.DateTimeFormatter;
import de.hauke_stieler.rednimer.DayOverview.Adapter.DayOverviewPagerAdapter;
import de.hauke_stieler.rednimer.R;
import de.hauke_stieler.rednimer.ReminderCreator.View.ReminderCreatorActivity;
import de.hauke_stieler.rednimer.Technical.DummyService.DummyDataFactory;
import juard.contract.Contract;
import juard.injection.Locator;

public class DayOverview extends Fragment {

    private final INotificationService notificationService;
    private boolean _isResumed;
    private IReminderDao _reminderDao;

    public DayOverview() {
        IReminderService reminderService = Locator.get(IReminderService.class);
        notificationService = Locator.get(INotificationService.class);

        reminderService.ReminderAdded.add(objects -> {
            Contract.NotNull(objects);
            Contract.Satisfy(objects.length > 0);
            Contract.Satisfy(objects[0] instanceof Reminder);

            notificationService.addReminder((Reminder) objects[0], getContext());
        });
    }

    public static DayOverview newInstance(IReminderDao reminderDao) {
        DayOverview fragment = new DayOverview();
        fragment.init(reminderDao);
        return fragment;
    }

    private void init(IReminderDao reminderDao) {
        Contract.NotNull(reminderDao);

        _reminderDao = reminderDao;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        _reminderDao.init(getContext());

//        new DummyDataFactory(Locator.get(IReminderService.class));
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
                Calendar date = getSelectedDate(position, pagerAdapter);
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

    private Calendar getSelectedDate(int position, DayOverviewPagerAdapter pagerAdapter) {
        ReminderLister item = pagerAdapter.getItem(position);
        return item.getDate();
    }

    private void setTitleToDate(Calendar date) {
        String dateString = DateTimeFormatter.formatDate(date);

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
