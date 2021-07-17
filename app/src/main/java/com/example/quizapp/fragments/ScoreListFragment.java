package com.example.quizapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.R;
import com.example.quizapp.adapters.ScoreListAdapter;
import com.example.quizapp.model.QuestionScore;
import com.example.quizapp.service.rest.QuestionScoreInterface;
import com.example.quizapp.service.rest.RestClient;
import com.example.quizapp.utils.Common;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScoreListFragment extends Fragment {

    View myFragment;
    RecyclerView rankingList;
    LinearLayoutManager layoutManager;
    ScoreListAdapter scoreListAdapter;
    QuestionScoreInterface restInterface;

    public static ScoreListFragment newInstance(){
        ScoreListFragment scoreListFragment = new ScoreListFragment();
        return scoreListFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init(){
        restInterface = RestClient.createRetrofit().create(QuestionScoreInterface.class);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myFragment = inflater.inflate(R.layout.fragment_score_list,container,false);

        rankingList = myFragment.findViewById(R.id.rankingList);
        layoutManager = new LinearLayoutManager(getActivity());
        rankingList.setHasFixedSize(true);

        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        rankingList.setLayoutManager(layoutManager);

        scoreListAdapter = new ScoreListAdapter(Common.questionScoreList);
        getScores();
        rankingList.setAdapter(scoreListAdapter);
        return myFragment;
    }

    private void getScores() {
        Call<List<QuestionScore>> call = restInterface.getAllQuestionsScores(Common.currentPlayer.getPlayerName());
        call.enqueue(new Callback<List<QuestionScore>>() {
            @Override
            public void onResponse(Call<List<QuestionScore>> call, Response<List<QuestionScore>> response) {
                if(response.isSuccessful() && response.body() != null){
                    Common.questionScoreList.addAll(response.body());
                    scoreListAdapter.setList(Common.questionScoreList);
                }else
                    Toast.makeText(getContext(),"You don't have scores..",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<List<QuestionScore>> call, Throwable t) {
                Toast.makeText(getContext(),"ScoreList: Something wrong...",Toast.LENGTH_LONG).show();
            }
        });
    }
}