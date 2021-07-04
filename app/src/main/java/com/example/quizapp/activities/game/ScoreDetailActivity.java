package com.example.quizapp.activities.game;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.quizapp.R;
import com.example.quizapp.model.QuestionScore;
import com.example.quizapp.service.firebase.FirebaseDB;
import com.example.quizapp.viewHolder.ScoreDetailViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;

public class ScoreDetailActivity extends AppCompatActivity {

    FirebaseDB firebaseDB;
    RecyclerView scoreList;
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<QuestionScore, ScoreDetailViewHolder> adapter;
    String viewPlayer = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_detail);

        init();

        if(getIntent() != null)
            viewPlayer = getIntent().getStringExtra("viewPlayer");
        if(!viewPlayer.isEmpty())
            loadScoreDetail(viewPlayer);
    }

    private void init(){
        firebaseDB = FirebaseDB.getInstance();

        scoreList = findViewById(R.id.scoreList);
        scoreList.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        scoreList.setLayoutManager(layoutManager);
    }

    private void loadScoreDetail(String viewPlayer) {
        adapter = new FirebaseRecyclerAdapter<QuestionScore, ScoreDetailViewHolder>(
                QuestionScore.class, R.layout.score_detail_layout, ScoreDetailViewHolder.class,
                firebaseDB.getQuestion_score().orderByChild("player").equalTo(viewPlayer)
        ) {
            @Override
            protected void populateViewHolder(ScoreDetailViewHolder scoreDetailViewHolder, QuestionScore questionScore, int i) {
                scoreDetailViewHolder.txt_name.setText(questionScore.getCategoryName());
                scoreDetailViewHolder.txt_score.setText(questionScore.getScore());
            }
        };
        adapter.notifyDataSetChanged();
        scoreList.setAdapter(adapter);
    }
}