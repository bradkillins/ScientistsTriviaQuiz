package com.killins.triviaquiz;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class GameOverDialog extends DialogFragment {
    String playerName;
    int score;
    private GameOverDialogListener listener;
    public GameOverDialog(String playerName, int score){
        this.playerName = playerName;
        this.score = score;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.thanks)
                .setMessage(getString(R.string.congrats, playerName, score))
                .setPositiveButton(R.string.okay, (dialog, which) -> listener.onGameOverDialogPositiveClick());
        return builder.create();
    }

    public interface GameOverDialogListener {
        void onGameOverDialogPositiveClick();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (GameOverDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    " must implement GameOverDialogListener.");
        }
    }
}
