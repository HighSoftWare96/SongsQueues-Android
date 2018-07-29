package com.highsoftware96.songsqueues.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CalendarView;
import android.widget.TextView;

import com.highsoftware96.songsqueues.R;
import com.highsoftware96.songsqueues.fragments.songlineupsdetails.LineupPresentationDetailsFragment;
import com.highsoftware96.songsqueues.models.local.Event;

import java.util.Date;
import java.util.GregorianCalendar;

public abstract class NewEventDialog extends Dialog implements View.OnClickListener, CalendarView.OnDateChangeListener {

    private LineupPresentationDetailsFragment parentFragment;
    private Event resultEvent = null;
    private Date selectedDate = null;
    private TextView textViewTitle;
    private CalendarView calendarView;

    public Date getSelectedDate() {
        return selectedDate;
    }

    public TextView getTextViewTitle() {
        return textViewTitle;
    }

    /**
     * Costruttore da utilizzare quando si vuole creare un nuovo evento     *
     *
     * @param context
     * @param parentFragment
     */
    public NewEventDialog(@NonNull Context context, LineupPresentationDetailsFragment parentFragment) {
        super(context);
        this.parentFragment = parentFragment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onPrepareDialog();
    }

    protected void onPrepareDialog() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.lineup_event_edit_layout);
        findViewById(R.id.dialog_ok_btn).setOnClickListener(this);
        findViewById(R.id.dialog_cancel_btn).setOnClickListener(this);

        calendarView = findViewById(R.id.event_date_edit_picker);
        calendarView.setOnDateChangeListener(this);
        this.textViewTitle = findViewById(R.id.event_title_edit_picker);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = 1050;
        getWindow().setAttributes(params);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_ok_btn:
                if (textViewTitle.getText() != null && textViewTitle.getText().length() > 0 && selectedDate != null) {
                    this.resultEvent = new Event(textViewTitle.getText().toString(), selectedDate, false);
                    onEventReturn(this.resultEvent);
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

    @Override
    public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
        this.selectedDate = new GregorianCalendar(year, month, dayOfMonth).getTime();
    }

    void setSelectedDate(Date selectedDate) {
        this.selectedDate = selectedDate;
        this.calendarView.setDate(selectedDate.getTime());
    }

    public abstract void onEventReturn(Event resultEvent);
}
