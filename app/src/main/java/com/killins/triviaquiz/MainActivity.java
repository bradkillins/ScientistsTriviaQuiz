package com.killins.triviaquiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String playerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startBtn = findViewById(R.id.btnStartGame);
        startBtn.setOnClickListener(v -> showToast("Start game!!!!"));
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
                showToast("About Menu Item Clicked");
                break;
            case helpMenuId:
                showToast("Help Menu Item Clicked");
                break;
            default:
        }

        return super.onOptionsItemSelected(item);
    }



    private void showToast(String msg){
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        toast.show();
    }

}