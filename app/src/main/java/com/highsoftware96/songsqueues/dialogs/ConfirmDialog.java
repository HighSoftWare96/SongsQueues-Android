package com.highsoftware96.songsqueues.dialogs;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.highsoftware96.songsqueues.R;

public abstract class ConfirmDialog extends PopupWindow {

    private String query = null;
    private Activity parent;
    private String okPrompt = null;
    private String cancelPrompt = null;
    private boolean yesOrNo = false;
    private View opaquePanelBackground = null;

    public ConfirmDialog(@Nullable String query, Activity parentActivity, @Nullable View opaquePanelBackground) {
        this.query = query;
        this.parent = parentActivity;
        this.opaquePanelBackground = opaquePanelBackground;
        configureView();
    }

    public ConfirmDialog(@Nullable String query, Activity parentActivity, String okPrompt, String cancelPrompt) {
        this.query = query;
        this.parent = parentActivity;
        this.okPrompt = okPrompt;
        this.cancelPrompt = cancelPrompt;
        this.opaquePanelBackground = opaquePanelBackground;

        configureView();
    }

    private void configureView() {
        LayoutInflater inflater = parent.getLayoutInflater();
        // inserisco il layout con i tasti opzioni per l'elemento
        View itemMenuView = inflater.inflate(R.layout.confirm_dialog_layout, null);
        if (cancelPrompt != null && okPrompt != null) {
            ((TextView) itemMenuView.findViewById(R.id.confirmdialog_ok)).setText(okPrompt);
            ((TextView) itemMenuView.findViewById(R.id.confirmdialog_cancel)).setText(cancelPrompt);
        }
        if (query != null)
            ((TextView) itemMenuView.findViewById(R.id.confirmdialog_text)).setText(query);
        setFocusable(true);
        setBackgroundDrawable(new ColorDrawable(Color.DKGRAY));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setElevation(100);
        }
        // imposto i vincoli di grandezza del menu
        setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        setContentView(itemMenuView);
        setAnimationStyle(R.style.WindowMenu_animation);
        itemMenuView.findViewById(R.id.confirmdialog_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yesOrNo = true;
                dismiss();
            }
        });
        itemMenuView.findViewById(R.id.confirmdialog_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yesOrNo = false;
                dismiss();
            }
        });
    }

    public void show() {
        if (opaquePanelBackground != null) {
            opaquePanelBackground.animate().setDuration(200).alpha(0.8f);
        }
        this.showAsDropDown(parent.findViewById(R.id.popupMenuAnchorDivider));
    }

    protected boolean getConfirm() {
        return this.yesOrNo;
    }

    @Override
    public void dismiss() {
        super.dismiss();
        if (opaquePanelBackground != null)
            // Tolgo il pannello dell'opacità sotto il menu
            opaquePanelBackground.animate().setDuration(200).alpha(0f);
        // chiamo il metodo che deve implementare l'utente che utilizza la classe in cui puo recuperare tutte
        // le robe che gli servono per verificare cosa l'utente ha confermato (si o no)
        onAfterConfirmDialogDismiss(getConfirm());
    }

    /**
     * TEMPLATE PATTERN:
     * Ho fatto in modo che questa classe sia astratta per far si che
     * chi la usa deve per forza implementare (magari con una classe interna)
     * il metodo sottostante che viene chiamato alla fine quando la finestra viene chiusa
     * in questo modo da qui l'utente puo chiamare getConfirm() sapendo che l'utente non puo piu modificare
     * la sua scelta.
     * Un esempio:
     *
     * @see com.highsoftware96.songsqueues.fragments.songlineups.SongLineupsFragment.SongLineupsFragmentConfirmDialog
     * @see ConfirmDialog
     */
    public abstract void onAfterConfirmDialogDismiss(boolean confirmation);
}
