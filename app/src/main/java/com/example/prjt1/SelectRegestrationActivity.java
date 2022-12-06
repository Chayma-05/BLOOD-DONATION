package com.example.prjt1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SelectRegestrationActivity extends AppCompatActivity {

    private Button donorButton,recipientButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_regestration);


        donorButton = findViewById(R.id.donorButton);
        recipientButton = findViewById(R.id.recipientButton);




        donorButton.setOnClickListener(view -> {
            Intent intent = new Intent(SelectRegestrationActivity.this,DonorRegestrationActivity.class);
            startActivity(intent);
        });


        recipientButton.setOnClickListener(view -> {
           Intent intent = new Intent(SelectRegestrationActivity.this,RecipienRegistrationActivity.class);
           startActivity(intent);
        });

    }
}