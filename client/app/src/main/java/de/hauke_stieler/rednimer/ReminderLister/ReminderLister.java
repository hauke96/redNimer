package de.hauke_stieler.rednimer.ReminderLister;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import de.hauke_stieler.rednimer.Common.Reminder;
import de.hauke_stieler.rednimer.R;

/**
 */
public class ReminderLister extends Fragment {
//    private OnFragmentInteractionListener mListener;

    private ArrayAdapter<Reminder> _listItemAdapter;

    public ReminderLister() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ReminderLister.
     */
    // TODO: Rename and change types and number of parameters
    public static ReminderLister newInstance() {
        ReminderLister fragment = new ReminderLister();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }

        ArrayList<Reminder> reminderListItems = new ArrayList<>();
        reminderListItems.add(new Reminder());
        reminderListItems.add(new Reminder());
        reminderListItems.add(new Reminder());

        _listItemAdapter = new ReminderListAdapter(getContext(), R.layout.fragment_reminder_list_item, reminderListItems);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reminder_lister, container, false);

        ListView itemListView = (ListView)view.findViewById(R.id.reminderItemListView);
        itemListView.setAdapter(_listItemAdapter);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
//        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
