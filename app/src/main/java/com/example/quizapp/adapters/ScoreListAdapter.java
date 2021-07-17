package com.example.quizapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.R;
import com.example.quizapp.model.QuestionScore;
import com.example.quizapp.viewHolder.ScoreListViewHolder;

import java.util.ArrayList;

public class ScoreListAdapter extends RecyclerView.Adapter<ScoreListViewHolder> {
    private ArrayList<QuestionScore> questionScores;

    public ScoreListAdapter(ArrayList<QuestionScore> questionScores) {
        this.questionScores = questionScores;
    }

    public void setList(ArrayList<QuestionScore> list){
        this.questionScores = list;
        notifyDataSetChanged();
    }

    @Override
    public ScoreListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_score_list,parent,false);
        return new ScoreListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ScoreListViewHolder holder, int position) {
        holder.txt_name.setText(questionScores.get(position).getCategoryName());
        holder.txt_score.setText(questionScores.get(position).getScore());
    }

    @Override
    public int getItemCount() {
        return questionScores.size();
    }

}
