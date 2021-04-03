package com.killins.triviaquiz;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class BackWarningDialog extends DialogFragment {
    private BackWarningDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.backWarning_title)
                .setMessage(R.string.backWarning_msg)
                .setPositiveButton(R.string.okay, (dialog, which) -> listener.onBackWarningDialogPositiveClick())
                .setNegativeButton(R.string.cancel, (dialog, which) -> {
                    //Do nothing to return to calling activity
                });
        return builder.create();
    }

    public interface BackWarningDialogListener {
        void onBackWarningDialogPositiveClick();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (BackWarningDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    " must implement BackWarningDialogListener.");
        }
    }
}
