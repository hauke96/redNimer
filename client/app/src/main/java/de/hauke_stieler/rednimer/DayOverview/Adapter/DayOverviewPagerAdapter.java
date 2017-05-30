package de.hauke_stieler.rednimer.DayOverview.Adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import de.hauke_stieler.rednimer.Common.ServiceInterface.IReminderService;
import de.hauke_stieler.rednimer.DayOverview.View.ReminderListItem;
import de.hauke_stieler.rednimer.DayOverview.View.ReminderLister;
import juard.injection.Locator;

/**
 * Created by hauke on 30.05.17.
 */
public class DayOverviewPagerAdapter extends FragmentPagerAdapter {
    private static final int ITEM_COUNT = 1000;

    // Set this at your view pager as initial item position. This will enable <code>ITEM_COUNT / 2</code> many swipes in each direction.
    public static final int INITIAL_POSITION = ITEM_COUNT / 2;

    private List<ReminderListItem> _items;

    public DayOverviewPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);

        _items = new ArrayList<>();
    }

    @Override
    public ReminderLister getItem(int position) {
        int offset = position - INITIAL_POSITION;

        // Get the date of the lister depending on the selected page.
        // Page 0 is the current date (=today).
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DATE, offset);
        Date date = calendar.getTime();

        ReminderLister reminderLister = ReminderLister.newInstance(Locator.get(IReminderService.class), date);

        return reminderLister;
    }

    @Override
    public int getCount() {
        return ITEM_COUNT;
    }
}
