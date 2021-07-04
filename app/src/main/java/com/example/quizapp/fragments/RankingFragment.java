package com.example.quizapp.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quizapp.Interface.ItemClickListener;
import com.example.quizapp.Interface.RankingCallBack;
import com.example.quizapp.R;
import com.example.quizapp.activities.game.ScoreDetailActivity;
import com.example.quizapp.model.QuestionScore;
import com.example.quizapp.model.Ranking;
import com.example.quizapp.service.firebase.FirebaseDB;
import com.example.quizapp.utils.Common;
import com.example.quizapp.viewHolder.RankingViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class RankingFragment extends Fragment {

    View myFragment;
    RecyclerView rankingList;
    LinearLayoutManager layoutManager;
    FirebaseRecyclerAdapter<Ranking, RankingViewHolder> adapter;
    FirebaseDB firebaseDB;
    int sum = 0;

    public static RankingFragment newInstance(){
        RankingFragment rankingFragment = new RankingFragment();
        return rankingFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
    }

    private void init(){ firebaseDB = FirebaseDB.getInstance(); }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myFragment = inflater.inflate(R.layout.fragment_ranking,container,false);

        rankingList = myFragment.findViewById(R.id.rankingList);
        layoutManager = new LinearLayoutManager(getActivity());
        rankingList.setHasFixedSize(true);

        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        rankingList.setLayoutManager(layoutManager);
        
        updateScore(Common.currentPlayer.getPlayerName(), ranking -> {
            firebaseDB.getRankingTbl().child(ranking.getPlayerName())
                    .setValue(ranking);
//                showRanking();
        });

        adapter = new FirebaseRecyclerAdapter<Ranking, RankingViewHolder>(
                Ranking.class,R.layout.layout_ranking,RankingViewHolder.class,firebaseDB.getRankingTbl().orderByChild("score")
        ) {
            @Override
            protected void populateViewHolder(RankingViewHolder rankingViewHolder, Ranking ranking, int i) {
                rankingViewHolder.txt_name.setText(ranking.getPlayerName());
                rankingViewHolder.txt_score.setText(String.valueOf(ranking.getScore()));

                rankingViewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Intent scoreDetail = new Intent(getActivity(), ScoreDetailActivity.class);
                        scoreDetail.putExtra("viewPlayer", ranking.getPlayerName());
                        startActivity(scoreDetail);
                    }
                });
            }
        };

        adapter.notifyDataSetChanged();
        rankingList.setAdapter(adapter);
        return myFragment;
    }

    private void showRanking() {
        firebaseDB.getRankingTbl().orderByChild("score")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot data: snapshot.getChildren()) {
                            Ranking local = data.getValue(Ranking.class);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    private void updateScore(String playerName, RankingCallBack<Ranking> callBack) {
        firebaseDB.getQuestion_score().orderByChild("player").equalTo(playerName)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot data: snapshot.getChildren()) {
                            QuestionScore ques = data.getValue(QuestionScore.class);
                            sum += Integer.parseInt(ques.getScore());
                        }
                        Ranking ranking = new Ranking(playerName,sum);
                        callBack.callback(ranking);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
}