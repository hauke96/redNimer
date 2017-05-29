package de.hauke_stieler.rednimer.ReminderLister;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import de.hauke_stieler.rednimer.Common.Reminder;
import de.hauke_stieler.rednimer.R;

/**
 * Created by hauke on 30.05.17.
 */
public class ReminderListAdapter extends ArrayAdapter<Reminder> {
    List<Reminder> _items;

    public ReminderListAdapter(Context context, int resource, List<Reminder> objects) {
        super(context, resource, objects);

        _items = objects;
    }

    @Override
    public View getView(int position, android.view.View convertView, android.view.ViewGroup parent){
        LayoutInflater mInflater = (LayoutInflater) getContext()
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        View view = mInflater.inflate(R.layout.fragment_reminder_list_item, null);

        TextView titleTextView = (TextView) view.findViewById(R.id.reminderTitleTextView);
        titleTextView.setText(_items.get(position).getTitle());

        TextView dueDateTextView = (TextView) view.findViewById(R.id.reminderDueDateTextView);
        dueDateTextView.setText(_items.get(position).getDueDate());

        return view;
    }
}
