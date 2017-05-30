package de.hauke_stieler.rednimer.DayOverview.View;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

import de.hauke_stieler.rednimer.Common.Material.Reminder;
import de.hauke_stieler.rednimer.R;
import de.hauke_stieler.rednimer.DayOverview.Adapter.ReminderListAdapter;

/**
 */
public class ReminderLister extends Fragment {

    private ArrayAdapter<Reminder> _listItemAdapter;
    private Date _date;

    public ReminderLister() {
    }

    public static ReminderLister newInstance(Date date) {
        ReminderLister reminderLister = new ReminderLister();

        reminderLister._date = date;

        return reminderLister;
    }

    public Date getDate(){
        return (Date) _date.clone();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayList<Reminder> reminderListItems = new ArrayList<>();
        reminderListItems.add(new Reminder(_date));
        reminderListItems.add(new Reminder(_date));
        reminderListItems.add(new Reminder(_date));

        _listItemAdapter = new ReminderListAdapter(getContext(), R.layout.fragment_reminder_list_item, reminderListItems);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reminder_lister, container, false);

        ListView itemListView = (ListView) view.findViewById(R.id.reminderItemListView);
        itemListView.setAdapter(_listItemAdapter);

        return view;
    }
}
