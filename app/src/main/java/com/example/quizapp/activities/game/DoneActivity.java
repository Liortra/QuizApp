package com.example.quizapp.activities.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizapp.R;
import com.example.quizapp.activities.home.HomeActivity;
import com.example.quizapp.model.QuestionScore;
import com.example.quizapp.service.rest.QuestionScoreInterface;
import com.example.quizapp.service.rest.RestClient;
import com.example.quizapp.utils.Common;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoneActivity extends AppCompatActivity {

    Button btnTryAgain;
    TextView txtResultScore, getTxtResultQuestion;
    ProgressBar progressBar;
    QuestionScoreInterface restInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);

        init();
        listeners();
    }
    private void init(){
        restInterface = RestClient.createRetrofit().create(QuestionScoreInterface.class);

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
            QuestionScore questionScore =  new QuestionScore(String.format("%s_%s", Common.currentPlayer.getPlayerName(), Common.categoryId),
                    Common.currentPlayer.getPlayerName(), String.valueOf(score), Common.categoryId, Common.categoryName);
            Call<Void> call = restInterface.updateScore(questionScore);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if(!response.isSuccessful())
                        Toast.makeText(getBaseContext(),"Did you play the game?",Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(getBaseContext(),"Thank you for playing",Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(getBaseContext(),"Done: Something wrong...",Toast.LENGTH_LONG).show();
                }
            });
        }
    }
    private void listeners(){
        btnTryAgain.setOnClickListener(v -> {
            Intent intent = new Intent(DoneActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        });
    }
}