package com.killins.triviaquiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startBtn = findViewById(R.id.btnStartGame);

        //Show a snackbar if no input in PlayerName EditText
        startBtn.setOnClickListener(v -> {
            EditText playerName = findViewById(R.id.playerName_editText);
            if (playerName.getText().length() < 1) {
                Snackbar snackbar = Snackbar.make(startBtn, R.string.noPlayerName, Snackbar.LENGTH_LONG);
                snackbar.setAction(R.string.add, (x -> findViewById(R.id.playerName_editText).requestFocus()));
                snackbar.setActionTextColor(getResources().getColor(R.color.brightBlue));
                snackbar.show();
            } else {
                startGameActivity(playerName.getText().toString());
            }
        });

    }

    //App action toolbar menu:

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_bar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        final int aboutMenuId = R.id.menu_about;
        final int helpMenuId = R.id.menu_help;

        switch (item.getItemId()) {
            case aboutMenuId:
                Utilities.startAboutActivity(this);
                break;
            case helpMenuId:
                Utilities.startHelpActivity(this);
                break;
            default:
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Starts the game activity with putting extras in the intent
     *
     * @param playerName - the playerName is put in the extras of the started intent.
     */
    private void startGameActivity(String playerName) {
        Intent gameIntent = new Intent(this, GameActivity.class);
        gameIntent.putExtra("com.killins.triviaquiz.PLAYERNAME", playerName);
        startActivity(gameIntent);
    }

}