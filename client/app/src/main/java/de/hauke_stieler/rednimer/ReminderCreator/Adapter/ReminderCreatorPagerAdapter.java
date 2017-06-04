package de.hauke_stieler.rednimer.ReminderCreator.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by hauke on 04.06.17.
 */
public class ReminderCreatorPagerAdapter extends FragmentPagerAdapter {

    public ReminderCreatorPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
