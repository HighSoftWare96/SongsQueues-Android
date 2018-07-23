package com.highsoftware96.songsqueues.adapter;

import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.highsoftware96.songsqueues.R;
import com.highsoftware96.songsqueues.fragments.songlineupsdetails.LineupPresentationDetailsFragment;
import com.highsoftware96.songsqueues.models.local.Event;

import java.util.ArrayList;

public class LineupEventsListAdapter extends BaseAdapter {

    private ArrayList<Event> dataToDisplay;
    private LineupPresentationDetailsFragment contextFragment;

    public LineupEventsListAdapter(ArrayList<Event> dataToDisplay, LineupPresentationDetailsFragment contextFragment) {
        this.dataToDisplay = dataToDisplay;
        this.contextFragment = contextFragment;
    }

    @Override
    public int getCount() {
        return this.dataToDisplay.size();
    }

    @Override
    public Object getItem(int position) {
        return this.dataToDisplay.get(position);
    }

    @Override
    public long getItemId(int position) {
        return this.dataToDisplay.get(position).getID();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = this.contextFragment.getLayoutInflater().inflate(R.layout.lineup_event_item_list_layout, null);
        TextView eventName = convertView.findViewById(R.id.event_name_placeholder);
        eventName.setText(this.dataToDisplay.get(position).getEventName());
        TextView eventDate = convertView.findViewById(R.id.event_date_placeholder);
        eventDate.setText(this.dataToDisplay.get(position).getEventDate().toString());
        /// imposto i vari bottoni per ogni item.
        ImageButton deleteItemBtn = convertView.findViewById(R.id.delete_event_action);
        deleteItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contextFragment.deleteEventItemAction(position);
            }
        });
        ImageButton notificationItemBtn = convertView.findViewById(R.id.notification_event_action);
        if (this.dataToDisplay.get(position).isNotificationEnabled()) {
            // se le notiiche sono attive mostro la campanella blu
            notificationItemBtn.setImageDrawable(ContextCompat.getDrawable(contextFragment.getContext(), R.drawable.notification_icon_active));
        }
        notificationItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contextFragment.notificationEventItemAction(position);
            }
        });
        ImageButton editItemBtn = convertView.findViewById(R.id.edit_event_action);
        editItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contextFragment.editEventItemAction(position);
            }
        });
        return convertView;
    }
}
