package com.example.quizapp.activities.home;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.quizapp.R;
import com.example.quizapp.fragments.CategoryFragment;
import com.example.quizapp.fragments.ScoreListFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        init();
        listeners();
    }

    private void init(){
        bottomNavigationView = findViewById(R.id.navigation);
    }

    private void listeners() {
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            switch (item.getItemId()){
                case R.id.action_category:
                    selectedFragment = CategoryFragment.newInstance();
                    break;
                case R.id.action_score_list:
                    selectedFragment = ScoreListFragment.newInstance();
                    break;
            }
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_layout,selectedFragment);
            transaction.commit();
            return true;
        });
        setDefaultFragment();
    }

    private void setDefaultFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout,CategoryFragment.newInstance());
        transaction.commit();
    }
}