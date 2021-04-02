package com.killins.triviaquiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startBtn = findViewById(R.id.btnStartGame);
        startBtn.setOnClickListener(v -> {
            EditText playerName = findViewById(R.id.playerName_editText);
            if (playerName.getText().length() < 1) {

                Snackbar snackbar = Snackbar.make(startBtn, R.string.noPlayerName, Snackbar.LENGTH_LONG);
                snackbar.setAction(R.string.add, (x -> findViewById(R.id.playerName_editText).requestFocus()));
                snackbar.setActionTextColor(getResources().getColor(R.color.brightBlue));
                snackbar.show();
            }
            else{
                startGameActivity(playerName.getText().toString());
            }
        });

    }

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
                startAboutActivity();
                break;
            case helpMenuId:
                startHelpActivity();
                break;
            default:
        }

        return super.onOptionsItemSelected(item);
    }

    private void startAboutActivity() {
        Intent aboutIntent = new Intent(this, AboutActivity.class);
        startActivity(aboutIntent);
    }

    private void startHelpActivity() {
        Intent helpIntent = new Intent(this, HelpActivity.class);
        startActivity(helpIntent);
    }

    private void startGameActivity(String playerName) {
        Intent gameIntent = new Intent(this, GameActivity.class);
        gameIntent.putExtra("com.killins.triviaquiz.PLAYERNAME", playerName);
        startActivity(gameIntent);
    }

}