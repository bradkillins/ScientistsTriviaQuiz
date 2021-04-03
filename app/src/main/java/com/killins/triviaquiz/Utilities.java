package com.killins.triviaquiz;

import android.content.Context;
import android.content.Intent;

import static androidx.core.content.ContextCompat.startActivity;

public class Utilities {

    public static void startAboutActivity(Context context) {
        Intent aboutIntent = new Intent(context, AboutActivity.class);
        startActivity(context, aboutIntent, null);
    }

    public static void startHelpActivity(Context context) {
        Intent helpIntent = new Intent(context, HelpActivity.class);
        startActivity(context, helpIntent, null);
    }
}
