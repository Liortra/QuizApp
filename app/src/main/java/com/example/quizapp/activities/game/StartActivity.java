package com.example.quizapp.activities.game;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.quizapp.R;
import com.example.quizapp.model.Question;
import com.example.quizapp.service.firebase.FirebaseDB;
import com.example.quizapp.utils.Common;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.Collections;

public class StartActivity extends AppCompatActivity {

    Button btnPlay;
    FirebaseDB firebaseDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        init();
        listeners();
    }

    private void init(){
        firebaseDB = FirebaseDB.getInstance();
        btnPlay = findViewById(R.id.btnPlay);
        loadQuestion(Common.categoryId);
    }

    private void loadQuestion(String categoryId) {
        if(!Common.questionList.isEmpty())
            Common.questionList.clear();

        firebaseDB.getQuestions().orderByChild("CategoryId").equalTo(categoryId)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot postSnapshot : snapshot.getChildren()){
                            Question ques = postSnapshot.getValue(Question.class);
                            Common.questionList.add(ques);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        Collections.shuffle(Common.questionList);
    }

    private void listeners(){
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, PlayingActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}