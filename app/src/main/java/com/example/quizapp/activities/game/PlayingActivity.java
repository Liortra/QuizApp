package com.example.quizapp.activities.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.quizapp.R;
import com.example.quizapp.service.firebase.FirebaseDB;
import com.example.quizapp.utils.Common;
import com.squareup.picasso.Picasso;

public class PlayingActivity extends AppCompatActivity implements View.OnClickListener {

    final static long INTERVAL = 1000; // 1 sec
    final static long TIMEOUT = 7000; // 7 sec
    int progressValue = 0, index = 0, score = 0, thisQuestion = 0, totalQuestion, correctAnswer;

    CountDownTimer mCountDown;
    ProgressBar progressBar;
    ImageView question_image;
    Button btnA, btnB, btnC, btnD;
    TextView txtScore, txtQuestionNum, question_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing);

        init();
        listeners();
    }

    private void init(){
        txtScore = findViewById(R.id.txtScore);
        txtQuestionNum = findViewById(R.id.txtTotalQuestion);
        question_text = findViewById(R.id.question_text);
        question_image = findViewById(R.id.question_image);

        progressBar = findViewById(R.id.progress_bar);

        btnA = findViewById(R.id.btnAnswerA);
        btnB = findViewById(R.id.btnAnswerB);
        btnC = findViewById(R.id.btnAnswerC);
        btnD = findViewById(R.id.btnAnswerD);

    }

    private void listeners(){
        btnA.setOnClickListener(this);
        btnB.setOnClickListener(this);
        btnC.setOnClickListener(this);
        btnD.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        mCountDown.cancel();
        if(index < totalQuestion){
            Button clickedButton = (Button) v;
            if (clickedButton.getText().equals(Common.questionList.get(index).getCorrectAnswer())){
                score+=10;
                correctAnswer++;
                showQuestion(++index);
            }
            else{
                Intent intent = new Intent(this,DoneActivity.class);
                Bundle dataSend = new Bundle();
                dataSend.putInt("SCORE", score);
                dataSend.putInt("TOTAL", totalQuestion);
                dataSend.putInt("CORRECT", correctAnswer);
                intent.putExtras(dataSend);
                startActivity(intent);
                finish();
            }
            txtScore.setText(String.format("%d",score));

        }

    }

    private void showQuestion(int i) {
        if(index < totalQuestion){
            thisQuestion++;
            txtQuestionNum.setText(String.format("%d / %d",thisQuestion, totalQuestion));
            progressBar.setProgress(0);
            progressValue = 0;

            if (Common.questionList.get(index).getIsImageQuestion().equals("true")){
                Picasso.get().load(Common.questionList.get(i).getQuestion())
                        .into(question_image);
                question_image.setVisibility(View.VISIBLE);
                question_text.setVisibility(View.INVISIBLE);
            } else{
                question_text.setText(Common.questionList.get(i).getQuestion());
                question_image.setVisibility(View.INVISIBLE);
                question_text.setVisibility(View.VISIBLE);
            }
            btnA.setText(Common.questionList.get(i).getAnswerA());
            btnB.setText(Common.questionList.get(i).getAnswerB());
            btnC.setText(Common.questionList.get(i).getAnswerC());
            btnD.setText(Common.questionList.get(i).getAnswerD());

            mCountDown.start();
        }else{
            // if it's final question
            Intent intent = new Intent(this,DoneActivity.class);
            Bundle dataSend = new Bundle();
            dataSend.putInt("SCORE", score);
            dataSend.putInt("TOTAL", totalQuestion);
            dataSend.putInt("CORRECT", correctAnswer);
            intent.putExtras(dataSend);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        totalQuestion = Common.questionList.size();
        mCountDown = new CountDownTimer(TIMEOUT,INTERVAL) {
            @Override
            public void onTick(long millisUntilFinished) {
                progressBar.setProgress(progressValue);
                progressValue++;
            }

            @Override
            public void onFinish() {
                mCountDown.cancel();
                showQuestion(++index);
            }
        };
        showQuestion(index);
    }
}