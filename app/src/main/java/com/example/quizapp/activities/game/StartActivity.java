package com.example.quizapp.activities.game;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quizapp.R;
import com.example.quizapp.model.Question;
import com.example.quizapp.service.rest.QuestionInterface;
import com.example.quizapp.service.rest.RestClient;
import com.example.quizapp.utils.Common;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StartActivity extends AppCompatActivity {

    Button btnPlay;
    QuestionInterface restInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        init();
        listeners();
    }

    private void init(){
        restInterface = RestClient.createRetrofit().create(QuestionInterface.class);
        btnPlay = findViewById(R.id.btnPlay);
        loadQuestion(Common.categoryId);
    }

    private void loadQuestion(String categoryId) {
        if(!Common.questionList.isEmpty())
            Common.questionList.clear();
        Call<List<Question>> call = restInterface.getAllQuestionsByCategoryId(categoryId);
        call.enqueue(new Callback<List<Question>>() {
            @Override
            public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {
                if(response.isSuccessful() && response.body() != null){
                    Common.questionList.addAll(response.body());
                }else
                    Toast.makeText(getBaseContext(),"We don't have questions..",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<List<Question>> call, Throwable t) {
                Toast.makeText(getBaseContext(),"Start: Something wrong...",Toast.LENGTH_LONG).show();
            }
        });
        Collections.shuffle(Common.questionList);
    }

    private void listeners(){
        btnPlay.setOnClickListener(v -> {
            Intent intent = new Intent(StartActivity.this, PlayingActivity.class);
            startActivity(intent);
            finish();
        });
    }
}