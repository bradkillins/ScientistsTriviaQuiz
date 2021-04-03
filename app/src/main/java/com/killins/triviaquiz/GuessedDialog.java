package com.killins.triviaquiz;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class GuessedDialog extends DialogFragment {
    boolean right;
    private GuessDialogListener listener;
    public GuessedDialog(boolean right){
        this.right = right;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(right ? R.string.right_guessTitle:R.string.wrong_guessTitle)
                .setMessage(right ? R.string.right_guessMsg : R.string.wrong_guessMsg)
                .setPositiveButton(R.string.next, (dialog, which) -> listener.onGuessDialogPositiveClick());
        return builder.create();
    }

    public interface GuessDialogListener {
        void onGuessDialogPositiveClick();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (GuessedDialog.GuessDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    " must implement GuessDialogListener.");
        }
    }
}
