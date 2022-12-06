package com.example.prjt1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class loginActivity extends AppCompatActivity {


private TextView backButton;
private Button loginbutton;
private TextInputEditText loginEmail,loginPassword;

private TextView forgotPassword;

private ProgressDialog loader;

private FirebaseAuth mAuth;

private FirebaseAuth.AuthStateListener authStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
              FirebaseUser user = mAuth.getCurrentUser();
              if (user != null){
                  Intent intent1 = new Intent(loginActivity.this,MainActivity.class);
                  startActivity(intent1);
                  finish();

              }
            }
        };


        backButton = findViewById(R.id.backButton);
        loginbutton = findViewById(R.id.loginButton);
        loginEmail= findViewById(R.id.loginEmail);
        loginPassword=findViewById(R.id.loginPassword);
        forgotPassword=findViewById(R.id.forgotPassword);

        loader = new ProgressDialog(this);


        backButton.setOnClickListener(view -> {
            Intent intent = new Intent(loginActivity.this,SelectRegestrationActivity.class);
            startActivity(intent);
            System.out.println("sign in  page");

        });

        loginbutton.setOnClickListener(view -> {
            System.out.println("Login page");

            final String email = loginEmail.getText().toString().trim();
            final String password = loginPassword.getText().toString().trim();


            if (TextUtils.isEmpty(email)){
                loginEmail.setError("Email is required");
            }
            if (TextUtils.isEmpty(password)){
                loginPassword.setError("Password is required");
            }
            else {
                loader.setMessage("log in in progress");
                loader.setCanceledOnTouchOutside(false);
                loader.show();

            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
               if (task.isSuccessful()){
                   Toast.makeText(loginActivity.this, "log in successful", Toast.LENGTH_SHORT).show();
                   Intent intent1 = new Intent(loginActivity.this,MainActivity.class);
                   startActivity(intent1);
                   finish();
               }else{
                   Toast.makeText(this, task.getException().toString(), Toast.LENGTH_SHORT).show();
               }
               loader.dismiss();
            });
            }
        });


    }

    @Override
    public void overridePendingTransition(int enterAnim, int exitAnim) {
        super.onStart();
        mAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(authStateListener);
    }
}