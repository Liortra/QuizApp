package com.example.quizapp.activities.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quizapp.R;
import com.example.quizapp.activities.home.HomeActivity;
import com.example.quizapp.model.Player;
import com.example.quizapp.service.firebase.FirebaseDB;
import com.example.quizapp.utils.Common;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;
import com.rengwuxian.materialedittext.MaterialEditText;

public class LoginActivity extends AppCompatActivity {
    MaterialEditText edtNewUser, edtNewPassword, edtNewEmail;
    MaterialEditText edtUser, edtPassword;
    Button btnSignUp, btnSignIn;
    FirebaseDB firebaseDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
        listeners();
    }

    private void init() {
        firebaseDB = FirebaseDB.getInstance();

        edtUser = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassword);

        btnSignIn = findViewById(R.id.btn_sign_in);
        btnSignUp = findViewById(R.id.btn_sign_up);

    }

    private void listeners() {
        btnSignUp.setOnClickListener(v -> showSignUpDialog());

        btnSignIn.setOnClickListener(v -> signIn(edtUser.getText().toString(),edtPassword.getText().toString()));
    }

    private void signIn(String player, String password) {
        firebaseDB.getUsers().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                if(snapshot.child(player).exists()){
                    if(!player.isEmpty()){
                        Player login = snapshot.child(player).getValue(Player.class);
                        if(login.getPassword().equals(password)){
                            Toast.makeText(LoginActivity.this,"Success Login",Toast.LENGTH_SHORT).show();
                            Intent homeActivity = new Intent(LoginActivity.this,HomeActivity.class);
                            Common.currentPlayer = login;
                            startActivity(homeActivity);
                            finish();
                        }
                        else
                            Toast.makeText(LoginActivity.this,"Wrong password",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(LoginActivity.this,"Please enter your user name",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(LoginActivity.this,"Player doesn't exist",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }

    private void showSignUpDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(LoginActivity.this);
        alertDialog.setTitle("Sign Up");
        alertDialog.setMessage("Please fill the information");

        LayoutInflater inflater = this.getLayoutInflater();
        View sign_up_layout = inflater.inflate(R.layout.sign_up_layout,null);

        edtNewUser = sign_up_layout.findViewById(R.id.edtNewUserName);
        edtNewPassword = sign_up_layout.findViewById(R.id.edtNewPassword);
        edtNewEmail = sign_up_layout.findViewById(R.id.edtNewEmail);

        alertDialog.setView(sign_up_layout);
        alertDialog.setIcon(R.drawable.ic_baseline_account_circle_24);

        alertDialog.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
        alertDialog.setPositiveButton("OK", (dialog, which) -> {
            Player player = new Player(
                    edtNewUser.getText().toString(),
                    edtNewPassword.getText().toString(),
                    edtNewEmail.getText().toString());
            firebaseDB.getUsers().addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                    if(snapshot.child(player.getPlayerName()).exists())
                        Toast.makeText(LoginActivity.this,"Player already exist",Toast.LENGTH_SHORT).show();
                    else{
                        firebaseDB.getUsers().child(player.getPlayerName()).setValue(player);
                        Toast.makeText(LoginActivity.this,"Player successfully registered",Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull @NotNull DatabaseError error) {

                }
            });
            dialog.dismiss();
        });
        alertDialog.show();
    }
}