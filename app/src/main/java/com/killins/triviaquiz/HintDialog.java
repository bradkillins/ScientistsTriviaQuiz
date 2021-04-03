package com.killins.triviaquiz;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class HintDialog extends DialogFragment {
    String hint;
    public HintDialog(String hint){
        this.hint = hint;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.hint_title)
                .setMessage(hint)
                .setPositiveButton(R.string.okay, (dialog, which) -> {
            //Do nothing to return to the activity
        });
        return builder.create();
    }
}
