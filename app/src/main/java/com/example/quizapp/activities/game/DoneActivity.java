package com.example.quizapp.activities.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.quizapp.R;
import com.example.quizapp.activities.home.HomeActivity;
import com.example.quizapp.model.QuestionScore;
import com.example.quizapp.service.firebase.FirebaseDB;
import com.example.quizapp.utils.Common;

public class DoneActivity extends AppCompatActivity {

    Button btnTryAgain;
    TextView txtResultScore, getTxtResultQuestion;
    ProgressBar progressBar;

    FirebaseDB firebaseDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);

        init();
        listeners();
    }
    private void init(){
        firebaseDB = FirebaseDB.getInstance();

        txtResultScore = findViewById(R.id.txtTotalScore);
        getTxtResultQuestion = findViewById(R.id.txtTotalQuestion);
        progressBar = findViewById(R.id.doneProgressBar);
        btnTryAgain = findViewById(R.id.btnTryAgain);

        Bundle extra = getIntent().getExtras();
        if(extra!=null){
            int score = extra.getInt("SCORE");
            int totalQuestion  = extra.getInt("TOTAL");
            int correctAnswer = extra.getInt("CORRECT");
            txtResultScore.setText(String.format("SCORE : %d", score));
            getTxtResultQuestion.setText(String.format("PASSED %d/%d", correctAnswer,totalQuestion));
            progressBar.setMax(totalQuestion);
            progressBar.setProgress(correctAnswer);
            firebaseDB.getQuestion_score().child(String.format("%s_%s", Common.currentPlayer.getPlayerName(), Common.categoryId))
                    .setValue(new QuestionScore(String.format("%s_%s", Common.currentPlayer.getPlayerName(), Common.categoryId ),
                            Common.currentPlayer.getPlayerName(),
                            String.valueOf(score),
                            Common.categoryId,
                            Common.categoryName));
        }
    }
    private void listeners(){
        btnTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoneActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //

    }
}