package com.killins.triviaquiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MenuItem;
import android.widget.ImageView;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    ArrayList<QuizQuestion> questions;
    int score;
    int currentQuestion;
    int qRemaining;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        if(savedInstanceState != null) {
            score = savedInstanceState.getInt("score");
            currentQuestion = savedInstanceState.getInt("currentQuestion");
            qRemaining = savedInstanceState.getInt("qRemaining");
        }
        questions = new ArrayList<>();
        addQuestions();

        ImageView questionImage = findViewById(R.id.question_imageView);
        questionImage.setImageDrawable(questions.get(0).image);

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("score", score);
        outState.putInt("currentQuestion", currentQuestion);
        outState.putInt("qRemaining", qRemaining);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void addQuestions(){
        questions.add(new QuizQuestion(ContextCompat.getDrawable(this,R.drawable.einstein_albert),
                2,
                getString(R.string.wrong_dalton), getString(R.string.albert),
                getString(R.string.wrong_edwin), getString(R.string.wrong_luis),
                getString(R.string.qHint_einstein)));

//        questions.add(new QuizQuestion(R.drawable.sagan_carl, 3,
//                getString(R.string.wrong_brian), getString(R.string.wrong_matt_harris),
//                getString(R.string.carl), getString(R.string.wrong_frank),
//                getString(R.string.qHint_sagan)));
//
//        questions.add(new QuizQuestion(R.drawable.curie_marie, 1,
//                getString(R.string.marie), getString(R.string.wrong_irene),
//                getString(R.string.wrong_barbara), getString(R.string.wrong_florance),
//                getString(R.string.qHint_curie)));
//
//        questions.add(new QuizQuestion(R.drawable.wu_chien_shiung, 3,
//                getString(R.string.wrong_xue), getString(R.string.wrong_suk_fong),
//                getString(R.string.chien), getString(R.string.wrong_zhang),
//                getString(R.string.qHint_shiung)));
//
//        questions.add(new QuizQuestion(R.drawable.chandrasekhar_subrahmanyan, 4,
//                getString(R.string.wrong_srinivasa), getString(R.string.wrong_nath),
//                getString(R.string.wrong_chandra), getString(R.string.subrahamanyan),
//                getString(R.string.qHint_chandrasekhar)));
//
//        questions.add(new QuizQuestion(R.drawable.braun_wernher_von, 1,
//                getString(R.string.wernher), getString(R.string.wrong_wegener),
//                getString(R.string.wrong_rudolf), getString(R.string.wrong_karl),
//                getString(R.string.qHint_braun)));
//
//        questions.add(new QuizQuestion((R.drawable.franklin_rosalind), 2,
//                getString(R.string.wrong_rachel), getString(R.string.rosalind),
//                getString(R.string.wrong_jane), getString(R.string.wrong_sophie),
//                getString(R.string.qHint_franklin)));
//
//        questions.add(new QuizQuestion(R.drawable.millikan_faraday, 3,
//                getString(R.string.wrong_james), getString(R.string.wrong_maxwell),
//                getString(R.string.millikan), getString(R.string.wrong_alex),
//                getString(R.string.qHint_faraday)));
//
//        questions.add(new QuizQuestion(R.drawable.newton_issac, 4,
//                getString(R.string.wrong_aristotle), getString(R.string.wrong_carolus),
//                getString(R.string.wrong_copernicus), getString(R.string.issac),
//                getString(R.string.qHint_newton)));
//
//        questions.add(new QuizQuestion(R.drawable.darwin_charles, 2,
//                getString(R.string.wrong_dmitri), getString(R.string.charles),
//                getString(R.string.wrong_alfred), getString(R.string.wrong_max),
//                getString(R.string.qHint_darwin)));
    }
}