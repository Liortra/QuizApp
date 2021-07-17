package com.example.quizapp.service.rest;

import com.example.quizapp.model.Question;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface QuestionInterface {
    // GET
    @GET("/questions")
    Call<List<Question>> getAllQuestions();

    @GET("/questions/category/{categoryId}")
    Call<List<Question>> getAllQuestionsByCategoryId(@Path("categoryId") String categoryId);

    @GET("/questions/category/{questionId}")
    Call<Question> getQuestionsByQuestionId(@Path("questionId") String questionId);
}
