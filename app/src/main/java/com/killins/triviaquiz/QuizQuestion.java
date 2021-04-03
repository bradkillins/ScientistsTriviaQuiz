package com.killins.triviaquiz;

public class QuizQuestion {
    int imageId, correctQ;
    String q1, q2, q3, q4, hint;

    public QuizQuestion(int imageId, int correctQ, String q1, String q2, String q3, String q4, String hint){
        this.imageId = imageId;
        this.correctQ = correctQ;
        this.q1 = q1;
        this.q2 = q2;
        this.q3 = q3;
        this.q4 = q4;
        this.hint = hint;
    }
}
