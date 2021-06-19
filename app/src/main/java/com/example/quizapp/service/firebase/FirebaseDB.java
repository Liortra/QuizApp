package com.example.quizapp.service.firebase;

import com.google.firebase.auth.FirebaseAuth;

public class FirebaseDB {
    private static FirebaseDB firebaseDB;
    private FirebaseAuth firebaseAuth;
    //add the player details

    private FirebaseDB(){
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public static FirebaseDB getInstance(){
        if(firebaseDB == null)
            firebaseDB = new FirebaseDB();
        return firebaseDB;
    }
}
