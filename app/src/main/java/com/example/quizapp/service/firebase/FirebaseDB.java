package com.example.quizapp.service.firebase;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseDB {
    private static FirebaseDB firebaseDB;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase database;
    private DatabaseReference users;
    private DatabaseReference categories;
    private DatabaseReference questions;
    private DatabaseReference question_score;

    private FirebaseDB(){
        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        users = database.getReference("Users");
        categories = database.getReference("Category");
        questions = database.getReference("Questions");
        question_score = database.getReference("Question_Score");

    }

    public static FirebaseDB getInstance(){
        if(firebaseDB == null)
            firebaseDB = new FirebaseDB();
        return firebaseDB;
    }

    public FirebaseAuth getFirebaseAuth() {
        return firebaseAuth;
    }

    public FirebaseDatabase getDatabase() {
        return database;
    }

    public DatabaseReference getUsers() {
        return users;
    }

    public DatabaseReference getCategories() {
        return categories;
    }

    public DatabaseReference getQuestions() {
        return questions;
    }

    public DatabaseReference getQuestion_score() {
        return question_score;
    }

}
