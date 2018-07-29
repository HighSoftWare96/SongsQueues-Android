package com.highsoftware96.songsqueues.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

public class ModifyEventDialog extends Dialog implements View.OnClickListener {
    public ModifyEventDialog(@NonNull Context context) {
        super(context);
    }

    public ModifyEventDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected ModifyEventDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    public void onClick(View v) {

    }
}
