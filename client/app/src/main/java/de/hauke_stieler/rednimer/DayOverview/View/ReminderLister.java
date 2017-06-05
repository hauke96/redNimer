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
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import de.hauke_stieler.rednimer.Common.Material.Reminder;
import de.hauke_stieler.rednimer.Common.ServiceInterface.AbstractReminderService;
import de.hauke_stieler.rednimer.Common.Technical.DateTimeFormatter;
import de.hauke_stieler.rednimer.DayOverview.Adapter.ReminderListAdapter;
import de.hauke_stieler.rednimer.R;
import juard.contract.Contract;

public class ReminderLister extends Fragment {

    private AbstractReminderService _reminderService;
    private Calendar _date;
    private ArrayAdapter<Reminder> _listItemAdapter;

    public ReminderLister() {

    }

    public static ReminderLister newInstance(AbstractReminderService reminderService, Calendar date) {
        Contract.RequireNotNull(reminderService);
        Contract.RequireNotNull(date);

        ReminderLister reminderLister = new ReminderLister();

        reminderLister._reminderService = reminderService;
        reminderLister._date = date;

        return reminderLister;
    }

    public Calendar getDate() {
        return (Calendar) _date.clone();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        _listItemAdapter = ReminderListAdapter.getInstance(getContext(), R.layout.fragment_reminder_list_item);

        _reminderService.ReminderAdded.add(objects -> reloadItems(objects));

        reloadItems();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reminder_lister, container, false);

        ListView itemListView = (ListView) view.findViewById(R.id.reminderItemListView);

        if (_listItemAdapter != null) {
            itemListView.setAdapter(_listItemAdapter);
        }

        return view;
    }

    private void reloadItems(Object... items) {
        boolean containsRelevantItem = false;

        for (Object item : items) {
            Calendar itemDate = ((Reminder) item).getDueDate();

            containsRelevantItem |= _date.get(Calendar.DAY_OF_YEAR) == itemDate.get(Calendar.DAY_OF_YEAR) && _date.get(Calendar.YEAR) == itemDate.get(Calendar.YEAR);
        }

        /*
        There won't be many items to go through (probably less then 1000), so it's not necessary yet to go through all items passed here and add them if they're not in the adapter.
         */
        if (containsRelevantItem) {
            reloadItems();
            Log.i("Add items", "Reloaded items for date " + DateTimeFormatter.formatDate(_date));
        }
    }

    public void reloadItems() {
        List<Reminder> reminders = _reminderService.getAll(_date);
        if (reminders == null) {
            reminders = new ArrayList<>();
        }

        _listItemAdapter.clear();
        _listItemAdapter.addAll(reminders);
    }
}
