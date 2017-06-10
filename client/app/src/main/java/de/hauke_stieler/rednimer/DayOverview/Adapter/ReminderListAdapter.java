package de.hauke_stieler.rednimer.DayOverview.Adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.hauke_stieler.rednimer.Common.Material.Reminder;
import de.hauke_stieler.rednimer.R;
import juard.contract.Contract;

/**
 * Created by hauke on 30.05.17.
 */
public class ReminderListAdapter extends ArrayAdapter<Reminder> {

    private ReminderListAdapter(Context context, int resource, List<Reminder> objects) {
        super(context, resource, objects);
    }

    public static ReminderListAdapter getInstance(Context context, int resource) {
        Contract.NotNull(context);

        return new ReminderListAdapter(context, resource, new ArrayList<>());
    }

    @Override
    public View getView(int position, android.view.View convertView, android.view.ViewGroup parent) {
        LayoutInflater mInflater = (LayoutInflater) getContext()
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (getItem(position) == null) {
            Log.e("Invalid position", "No Reminder-Item at position " + position);

            return null;
        }

        View view = mInflater.inflate(R.layout.fragment_reminder_list_item, null);

        TextView titleTextView = (TextView) view.findViewById(R.id.reminderTitleTextView);
        titleTextView.setText(getItem(position).getTitle());

        TextView dueDateTextView = (TextView) view.findViewById(R.id.reminderDueDateTextView);
        dueDateTextView.setText(getItem(position).getDueDateDescription());

        return view;
    }
}
