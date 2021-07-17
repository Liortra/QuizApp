package com.example.quizapp.service.rest;

import com.example.quizapp.model.QuestionScore;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface QuestionScoreInterface {
    // GET
    @GET("/score/{playerEmail}")
    Call<List<QuestionScore>> getAllQuestionsScores(@Path("playerEmail") String playerEmail);

    @GET("/score/category/{categoryId}/{playerEmail}")
    Call<QuestionScore> getQuestionScoreByCategoryId(@Path("categoryId") String categoryId, @Path("playerEmail") String playerEmail);

    //PUT
    @PUT("/score/update")
    Call<Void> updateScore(@Body QuestionScore newScore);

}
