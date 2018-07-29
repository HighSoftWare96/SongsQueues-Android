package com.highsoftware96.songsqueues.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.highsoftware96.songsqueues.R;
import com.highsoftware96.songsqueues.fragments.songlineupsdetails.LineupPresentationDetailsFragment;
import com.highsoftware96.songsqueues.models.local.Event;
import com.highsoftware96.songsqueues.models.local.EventNotificationMode;
import com.highsoftware96.songsqueues.models.local.EventNotificationModesConstants;

public class NotificationSelectEventDialog extends Dialog implements View.OnClickListener {

    private Event tempLineupEvent;
    private LineupPresentationDetailsFragment parentFragment;
    private TextView notificationModeTextViewBtn;
    private int positionInList;

    public NotificationSelectEventDialog(@NonNull Context context, LineupPresentationDetailsFragment parentFragment, Event referredLineupEvent, int eventPositionInList) {
        super(context);
        this.parentFragment = parentFragment;
        // evento di scorta che tengo prima che l'utente confermi.
        this.tempLineupEvent = referredLineupEvent;
        this.positionInList = eventPositionInList;
        prepareDialog();
    }

    private void prepareDialog() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.lineup_event_notification_edit_layout);

        TextView dateText = findViewById(R.id.event_edit_date_text);
        dateText.setText(this.tempLineupEvent.getEventDate().toString());

        Button okBtn = findViewById(R.id.dialog_ok_btn);
        okBtn.setOnClickListener(this);

        Button cancelBtn = findViewById(R.id.dialog_cancel_btn);
        cancelBtn.setOnClickListener(this);

        TextView titlePlaceholder = findViewById(R.id.event_title_notification_edit_picker);
        titlePlaceholder.setText(this.tempLineupEvent.getEventName());

        Switch notificationActive = findViewById(R.id.event_edit_active_notification_btn);
        // abilito lo switch della notifica solo se è attiva
        notificationActive.setChecked(this.tempLineupEvent.isNotificationEnabled());
        notificationActive.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Abilito o disabilito la notifica
                if (isChecked) {
                    tempLineupEvent.setNotificationMode(null);
                } else {
                    tempLineupEvent.unsetNotificationMode();
                }
                notificationModeTextViewBtn.setEnabled(isChecked);
            }
        });

        this.notificationModeTextViewBtn = findViewById(R.id.event_edit_select_notification_mode);
        this.notificationModeTextViewBtn.setOnClickListener(this);
        this.notificationModeTextViewBtn.setEnabled(this.tempLineupEvent.isNotificationEnabled());

        if (tempLineupEvent.getNotificationMode() != null)
            this.notificationModeTextViewBtn.setText(EventNotificationModesConstants.getStringRapr(this.tempLineupEvent.getNotificationMode()));

        getWindow().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = 1100;
        params.height = 900;
        getWindow().setAttributes(params);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_ok_btn:
                parentFragment.saveEventData(tempLineupEvent, positionInList);
                cancel();
                break;
            case R.id.dialog_cancel_btn:
                cancel();
                break;
            case R.id.event_edit_select_notification_mode:
                new AlertDialog.Builder(getContext())
                        // setto le costanti da visualizzare nelle opzioni del menu e quella di default (quella impostata)
                        .setSingleChoiceItems(EventNotificationModesConstants.getConstants(), EventNotificationModesConstants.getModeIndex(tempLineupEvent.getNotificationMode()), null)
                        .setPositiveButton(R.string.ok_string, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.dismiss();
                                // al click positivo recupero l'elemento selezionato
                                int selectedPosition = ((AlertDialog) dialog).getListView().getCheckedItemPosition();
                                // imposto la modalità selezionata.
                                NotificationSelectEventDialog.this.setNotificationMode(selectedPosition, EventNotificationModesConstants.getConstants()[selectedPosition]);
                            }
                        })
                        .show();
                break;
            default:
                break;
        }
    }

    /**
     * Recupera la modalità di notifica e la imposta nell'evento
     *
     * @param position           posizione della modalità clickata
     * @param modeSelectionIndex stringa di descrizione della modalità selezionata.
     */
    private void setNotificationMode(int position, String modeSelectionIndex) {
        EventNotificationMode modeDef = EventNotificationModesConstants.getRelatedDefinition(modeSelectionIndex);
        this.tempLineupEvent.setNotificationMode(modeDef);
        this.notificationModeTextViewBtn.setText(EventNotificationModesConstants.getConstants()[position]);
    }
}
