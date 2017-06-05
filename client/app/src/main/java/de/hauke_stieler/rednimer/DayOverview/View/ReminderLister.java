package de.hauke_stieler.rednimer.DayOverview.View;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import de.hauke_stieler.rednimer.Common.Material.Reminder;
import de.hauke_stieler.rednimer.Common.ServiceInterface.IReminderService;
import de.hauke_stieler.rednimer.R;
import de.hauke_stieler.rednimer.DayOverview.Adapter.ReminderListAdapter;
import juard.contract.Contract;

public class ReminderLister extends Fragment {

    private IReminderService _reminderService;
    private Date _date;
    private ArrayAdapter<Reminder> _listItemAdapter;

    public ReminderLister() {

    }

    public static ReminderLister newInstance(IReminderService reminderService, Date date) {
        Contract.RequireNotNull(reminderService);
        Contract.RequireNotNull(date);

        ReminderLister reminderLister = new ReminderLister();

        reminderLister._reminderService = reminderService;
        reminderLister._date = date;

        return reminderLister;
    }

    public Date getDate() {
        return (Date) _date.clone();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<Reminder> reminders = _reminderService.getAll(_date);
        if(reminders == null){
            reminders = new ArrayList<>();
        }

        _listItemAdapter = ReminderListAdapter.getInstance(getContext(), R.layout.fragment_reminder_list_item, reminders);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reminder_lister, container, false);

        ListView itemListView = (ListView) view.findViewById(R.id.reminderItemListView);

        Log.i("adapter state", "date: " + _date.toString() + "; adapter: " + _listItemAdapter);
        if (_listItemAdapter != null) {
            itemListView.setAdapter(_listItemAdapter);
        }

        return view;
    }
}
