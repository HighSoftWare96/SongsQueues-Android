package com.highsoftware96.songsqueues.dialogs;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.highsoftware96.songsqueues.R;
import com.highsoftware96.songsqueues.fragments.songlineupsdetails.LineupPresentationDetailsFragment;
import com.highsoftware96.songsqueues.models.local.Event;

public abstract class EditEventDialog extends NewEventDialog {
    private Event referredEvent;

    /**
     * Costruttore da utilizzare quando si vuole creare un nuovo evento     *
     *
     * @param context
     * @param parentFragment
     */
    public EditEventDialog(@NonNull Context context, LineupPresentationDetailsFragment parentFragment, Event referredEvent) {
        super(context, parentFragment);
        this.referredEvent = referredEvent;
    }

    @Override
    protected void onPrepareDialog() {
        super.onPrepareDialog();
        // imposto i parametri per il dialogo che si apre con in riferimento un altro evento
        super.getTextViewTitle().setText(this.referredEvent.getEventName());
        super.setSelectedDate(this.referredEvent.getEventDate());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_ok_btn:
                TextView titleTV = getTextViewTitle();
                if (titleTV.getText() != null && titleTV.getText().length() > 0 && getSelectedDate() != null) {
                    this.referredEvent.setEventDate(getSelectedDate());
                    this.referredEvent.setEventName(titleTV.getText().toString());
                    onEventReturn(this.referredEvent);
                    dismiss();
                }
                break;
            case R.id.dialog_cancel_btn:
                dismiss();
                break;
            default:
                break;
        }
    }
}
