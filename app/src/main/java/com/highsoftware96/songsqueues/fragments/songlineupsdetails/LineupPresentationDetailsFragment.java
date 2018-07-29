package com.highsoftware96.songsqueues.fragments.songlineupsdetails;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.highsoftware96.songsqueues.R;
import com.highsoftware96.songsqueues.activities.LineupPresentationActivity;
import com.highsoftware96.songsqueues.adapter.LineupEventsListAdapter;
import com.highsoftware96.songsqueues.dialogs.ConfirmDialog;
import com.highsoftware96.songsqueues.dialogs.EditEventDialog;
import com.highsoftware96.songsqueues.dialogs.NewEventDialog;
import com.highsoftware96.songsqueues.dialogs.NotificationSelectEventDialog;
import com.highsoftware96.songsqueues.models.local.Event;


public class LineupPresentationDetailsFragment extends Fragment implements View.OnClickListener {

    private ListView eventsListView;
    private TextView descriptionTextView;
    private TextView creationDateTextView;
    private TextView lastModifiedTextView;
    private TextView categoryTextView;
    private LineupEventsListAdapter eventsListAdapter;
    private TextView addNewEventBtn;

    public LineupPresentationDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_lineup_presentation_details, container, false);
        // COSTRUZIONE DELLA VIEW
        this.descriptionTextView = v.findViewById(R.id.description_placeholder);
        this.descriptionTextView.setText(((LineupPresentationActivity) getActivity()).getSelectedLineupToShow().getDescription());
        this.categoryTextView = v.findViewById(R.id.data_category_placeholder);
        this.categoryTextView.setText(((LineupPresentationActivity) getActivity()).getSelectedLineupToShow().getCategory().toString());
        this.creationDateTextView = v.findViewById(R.id.data_creation_placeholder);
        this.creationDateTextView.setText(((LineupPresentationActivity) getActivity()).getSelectedLineupToShow().getDateCreation().toString());
        this.lastModifiedTextView = v.findViewById(R.id.data_lastmodified_placeholder);
        this.lastModifiedTextView.setText(((LineupPresentationActivity) getActivity()).getSelectedLineupToShow().getDateLastModified().toString());

        this.addNewEventBtn = v.findViewById(R.id.add_new_event);
        this.addNewEventBtn.setOnClickListener(this);

        // settings della listview
        this.eventsListView = v.findViewById(R.id.lineup_events_list_view);
        this.eventsListAdapter = new LineupEventsListAdapter(((LineupPresentationActivity) getActivity()).getSelectedLineupToShow().getEvents(), this);
        this.eventsListView.setAdapter(this.eventsListAdapter);
        this.eventsListView.setDivider(null);
        setListViewHeightBasedOnChildren(this.eventsListView);
        // Inflate the layout for this fragment
        return v;
    }

    private void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 20;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ViewGroup.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    public void editEventItemAction(final int eventPositionInList) {
        // TODO: l'utente ha clickato il bottone di modifica di un evento
        Snackbar.make(getActivity().findViewById(android.R.id.content), "Modifica evento #" + eventPositionInList, Snackbar.LENGTH_SHORT).show();
        EditEventDialog editEventDialog = new EditEventDialog(getContext(), this, ((LineupPresentationActivity) getActivity()).getSelectedLineupToShow().getEvents().get(eventPositionInList)) {
            @Override
            public void onEventReturn(Event resultEvent) {
                editEvent(resultEvent, eventPositionInList);
            }
        };
        editEventDialog.show();
    }

    public void notificationEventItemAction(int eventPositionInList) {
        // TODO: l'utente ha clickato il bottone di modifica della notifica un evento
        Snackbar.make(getActivity().findViewById(android.R.id.content), "Notifiche evento #" + eventPositionInList, Snackbar.LENGTH_SHORT).show();
        NotificationSelectEventDialog notificationSelectEventDialog = new NotificationSelectEventDialog(getContext(), this, ((LineupPresentationActivity) getActivity()).getSelectedLineupToShow().getEvents().get(eventPositionInList).clone(), eventPositionInList);
        notificationSelectEventDialog.show();
    }

    public void deleteEventItemAction(final int eventPositionInList) {
        // TODO: l'utente ha clickato il bottone di cancellazione di un evento
        Snackbar.make(getActivity().findViewById(android.R.id.content), "Cancellazione evento #" + eventPositionInList, Snackbar.LENGTH_SHORT).show();
        ConfirmDialog confirmDialog = new ConfirmDialog(null, getActivity(), null) {
            @Override
            public void onAfterConfirmDialogDismiss(boolean confirmation) {
                if (confirmation) {
                    deleteEvent(eventPositionInList);
                }
            }
        };
        confirmDialog.show();
    }

    private void editEvent(Event resultEvent, int eventPositionInList) {
        ((LineupPresentationActivity) getActivity()).editEvent(eventPositionInList, resultEvent);
        this.eventsListAdapter.notifyDataSetChanged();
        this.setListViewHeightBasedOnChildren(this.eventsListView);
    }

    public void deleteEvent(int positionInList) {
        ((LineupPresentationActivity) getActivity()).deleteEvent(positionInList);
        this.eventsListAdapter.notifyDataSetChanged();
        this.setListViewHeightBasedOnChildren(this.eventsListView);
    }

    public void saveEventData(Event referredLineupEvent, int positionInList) {
        ((LineupPresentationActivity) getActivity()).saveEventData(referredLineupEvent, positionInList);
        this.eventsListAdapter.notifyDataSetChanged();
    }

    public void addNewEvent(Event newEvent) {
        ((LineupPresentationActivity) getActivity()).addNewEvent(newEvent);
        this.eventsListAdapter.notifyDataSetChanged();
        this.setListViewHeightBasedOnChildren(this.eventsListView);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_new_event:
                NewEventDialog newEventDialog = new NewEventDialog(getContext(), this) {
                    @Override
                    public void onEventReturn(Event resultEvent) {
                        addNewEvent(resultEvent);
                    }
                };
                newEventDialog.show();
                break;
            default:
                break;
        }
    }
}
