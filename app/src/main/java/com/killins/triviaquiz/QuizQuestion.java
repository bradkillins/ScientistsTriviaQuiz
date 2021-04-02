package com.killins.triviaquiz;

import android.graphics.drawable.Drawable;

public class QuizQuestion {
    Drawable image;
    int correctQ;
    String q1, q2, q3, q4, hint;

    public QuizQuestion(Drawable image, int correctQ, String q1, String q2, String q3, String q4, String hint){
        this.image = image;
        this.correctQ = correctQ;
        this.q1 = q1;
        this.q2 = q2;
        this.q3 = q3;
        this.q4 = q4;
        this.hint = hint;
    }
}
