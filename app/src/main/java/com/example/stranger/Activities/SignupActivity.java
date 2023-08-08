package com.example.stranger.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.stranger.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {

    FirebaseAuth auth;
    EditText emailBox,passwordBox,nameBox;
    Button signupBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        auth=FirebaseAuth.getInstance();

        nameBox=findViewById(R.id.nameBox);
        emailBox=findViewById(R.id.emailBox);
        passwordBox=findViewById(R.id.passwordBox);

        signupBtn=findViewById(R.id.signupBtn);

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email,password,name;
                email=emailBox.getText().toString();
                password=passwordBox.getText().toString();
                name=nameBox.getText().toString();

                auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(SignupActivity.this, "Account is Created", Toast.LENGTH_SHORT).show();
                       }else {
                            Toast.makeText(SignupActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                      }
                    }
                });
            }
        });
    }
}