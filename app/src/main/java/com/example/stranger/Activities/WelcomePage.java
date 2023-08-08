package com.example.stranger.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.stranger.R;
import com.google.firebase.auth.FirebaseAuth;

public class WelcomePage extends AppCompatActivity {
Button getstarted;
FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        auth=FirebaseAuth.getInstance();
        if(auth.getCurrentUser()!=null){
            goToNextActivity();
        }

        getstarted=findViewById(R.id.getStartedBtn);
        getstarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToNextActivity();
            }
        });
    }
    void goToNextActivity(){
        startActivity(new Intent(WelcomePage.this, LoginActivity.class));
        finish();
    }
}