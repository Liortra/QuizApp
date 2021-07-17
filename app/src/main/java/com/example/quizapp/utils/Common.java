package com.example.quizapp.utils;

import com.example.quizapp.model.Player;
import com.example.quizapp.model.Question;
import com.example.quizapp.model.QuestionScore;

import java.util.ArrayList;
import java.util.List;

public class Common {
    public static String categoryId, categoryName;
    public static Player currentPlayer;
    public static List<Question> questionList = new ArrayList<>();
    public static ArrayList<QuestionScore> questionScoreList = new ArrayList<>();
}
