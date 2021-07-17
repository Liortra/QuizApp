package com.example.quizapp.viewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.R;

public class ScoreListViewHolder extends RecyclerView.ViewHolder {

    public TextView txt_name,txt_score;

    public ScoreListViewHolder(@NonNull View itemView) {
        super(itemView);
        txt_name = itemView.findViewById(R.id.txt_name);
        txt_score = itemView.findViewById(R.id.txt_score);
    }
}
