package com.killins.triviaquiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity
        implements BackWarningDialog.BackWarningDialogListener,
        GuessedDialog.GuessDialogListener,
        GameOverDialog.GameOverDialogListener {

    QuizQuestion[] questions;
    int score;
    int currentQuestion;
    ArrayList<Integer> completedQuestions;
    Random r = new Random();
    int numberOfRounds = 5;
    boolean backButtonWarn = true;
    String playerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent intent = getIntent();
        if (intent != null)
            playerName = intent.getStringExtra("com.killins.triviaquiz.PLAYERNAME");

        if (savedInstanceState != null) {
            score = savedInstanceState.getInt("score");
            currentQuestion = savedInstanceState.getInt("currentQuestion");
            completedQuestions = savedInstanceState.getIntegerArrayList("completedQuestions");
        }
        //savedInstance is null so first time launching GameActivity
        else {
            score = 0;
            currentQuestion = -1;
            completedQuestions = new ArrayList<>();
        }

        initQuestions();
        pickQuestionToLoad();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("score", score);
        outState.putInt("currentQuestion", currentQuestion);
        outState.putIntegerArrayList("completedQuestions", completedQuestions);
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
            //override up button to same behaviour as back button with warning
            case android.R.id.home:
                onBackPressed();
                break;
            case aboutMenuId:
                Utilities.startAboutActivity(this);
                break;
            case helpMenuId:
                Utilities.startHelpActivity(this);
                break;
            default:
        }
        return true;
    }

    /**
     * Warn the user that navigating back will loose all game progress
     */
    @Override
    public void onBackPressed() {

        if (backButtonWarn) {
            BackWarningDialog dialog = new BackWarningDialog();
            dialog.show(getSupportFragmentManager(), "back_buttonWarning");
        } else
            super.onBackPressed();
    }

    /**
     * Determines the question to load based on currentQuestion or
     * ends the game if completedQuestions equals the numberOfRounds
     */
    private void pickQuestionToLoad() {

        if (currentQuestion < 0) {
            currentQuestion = r.nextInt(questions.length);
        } else {
            if (completedQuestions.size() >= numberOfRounds) {
                openGameOverDialog();
                return;
            }
        }
        if (!completedQuestions.contains(currentQuestion))
            completedQuestions.add(currentQuestion);
        loadQuestionToLayout(currentQuestion);
    }

    /**
     * Gets a QuizQuestion object from the questions array and loads
     * all the appropriate views in the layout with that question's
     * information.
     *
     * @param index - the index of the QuizQuestion to load in the array
     */
    private void loadQuestionToLayout(int index) {
        //Get views
        ImageView questionImage = findViewById(R.id.question_imageView);
        TextView scoreText = findViewById(R.id.score_textView);
        TextView remainingText = findViewById(R.id.remaining_textView);
        Button hintButton = findViewById(R.id.hint_btn);
        Button[] qButtons = new Button[]{
                findViewById(R.id.question1_btn),
                findViewById(R.id.question2_btn),
                findViewById(R.id.question3_btn),
                findViewById(R.id.question4_btn)
        };
        //Set texts and image
        scoreText.setText(getString(R.string.display_score, score));
        remainingText.setText(getString(R.string.display_remain, numberOfRounds - completedQuestions.size() + 1));

        questionImage.setImageDrawable(ContextCompat.getDrawable(this, questions[index].imageId));
        questionImage.setContentDescription(questions[index].hint);
        qButtons[0].setText(questions[index].q1);
        qButtons[1].setText(questions[index].q2);
        qButtons[2].setText(questions[index].q3);
        qButtons[3].setText(questions[index].q4);

        //Set onClickListeners
        for (Button button : qButtons) {
            button.setOnClickListener(v -> openGuessedDialog(false));
        }
        int correctIndex = questions[index].correctQ - 1;
        qButtons[correctIndex].setOnClickListener(v -> {
            score++;
            openGuessedDialog(true);
        });
        hintButton.setOnClickListener(v -> openHintDialog(questions[index].hint));
    }

    private void openGuessedDialog(boolean right) {
        GuessedDialog dialog = new GuessedDialog(right);
        dialog.show(getSupportFragmentManager(), "guess_dialog");
    }

    private void openHintDialog(String hint) {
        HintDialog dialog = new HintDialog(hint);
        dialog.show(getSupportFragmentManager(), "hint_dialog");
    }

    private void openGameOverDialog() {
        GameOverDialog dialog = new GameOverDialog(playerName, score);
        dialog.show(getSupportFragmentManager(), "gameOver_dialog");
    }

    @Override
    public void onBackWarningDialogPositiveClick() {
        backButtonWarn = false;
        onBackPressed();
    }

    @Override
    public void onGuessDialogPositiveClick() {
        int nextQuestion;
        do {
            nextQuestion = r.nextInt(questions.length);
        }
        while (completedQuestions.contains(nextQuestion));
        currentQuestion = nextQuestion;
        pickQuestionToLoad();
    }

    @Override
    public void onGameOverDialogPositiveClick() {
        backButtonWarn = false;
        onBackPressed();
    }

    /**
     * Create an array of 10 Quiz Questions with hardcoded data.
     */
    private void initQuestions() {
        questions = new QuizQuestion[]{
                new QuizQuestion(
                        R.drawable.einstein_albert,
                        2,
                        getString(R.string.wrong_dalton),
                        getString(R.string.albert),
                        getString(R.string.wrong_edwin),
                        getString(R.string.wrong_luis),
                        getString(R.string.qHint_einstein)
                ),
                new QuizQuestion(
                        R.drawable.sagan_carl,
                        3,
                        getString(R.string.wrong_brian),
                        getString(R.string.wrong_matt_harris),
                        getString(R.string.carl),
                        getString(R.string.wrong_frank),
                        getString(R.string.qHint_sagan)
                ),
                new QuizQuestion(
                        R.drawable.curie_marie,
                        1,
                        getString(R.string.marie),
                        getString(R.string.wrong_irene),
                        getString(R.string.wrong_barbara),
                        getString(R.string.wrong_florance),
                        getString(R.string.qHint_curie)
                ),
                new QuizQuestion(
                        R.drawable.wu_chien_shiung,
                        3,
                        getString(R.string.wrong_xue),
                        getString(R.string.wrong_suk_fong),
                        getString(R.string.chien),
                        getString(R.string.wrong_zhang),
                        getString(R.string.qHint_shiung)
                ),
                new QuizQuestion(
                        R.drawable.chandrasekhar_subrahmanyan,
                        4,
                        getString(R.string.wrong_srinivasa),
                        getString(R.string.wrong_nath),
                        getString(R.string.wrong_chandra),
                        getString(R.string.subrahamanyan),
                        getString(R.string.qHint_chandrasekhar)
                ),
                new QuizQuestion(
                        R.drawable.braun_wernher_von,
                        1,
                        getString(R.string.wernher),
                        getString(R.string.wrong_wegener),
                        getString(R.string.wrong_rudolf),
                        getString(R.string.wrong_karl),
                        getString(R.string.qHint_braun)
                ),
                new QuizQuestion(
                        R.drawable.franklin_rosalind,
                        2,
                        getString(R.string.wrong_rachel),
                        getString(R.string.rosalind),
                        getString(R.string.wrong_jane),
                        getString(R.string.wrong_sophie),
                        getString(R.string.qHint_franklin)
                ),
                new QuizQuestion(
                        R.drawable.millikan_faraday,
                        3,
                        getString(R.string.wrong_james),
                        getString(R.string.wrong_maxwell),
                        getString(R.string.millikan),
                        getString(R.string.wrong_alex),
                        getString(R.string.qHint_faraday)
                ),
                new QuizQuestion(
                        R.drawable.newton_issac,
                        4,
                        getString(R.string.wrong_aristotle),
                        getString(R.string.wrong_carolus),
                        getString(R.string.wrong_copernicus),
                        getString(R.string.issac),
                        getString(R.string.qHint_newton)
                ),
                new QuizQuestion(
                        R.drawable.darwin_charles,
                        2,
                        getString(R.string.wrong_dmitri),
                        getString(R.string.charles),
                        getString(R.string.wrong_alfred),
                        getString(R.string.wrong_max),
                        getString(R.string.qHint_darwin)
                )
        };
    }
}