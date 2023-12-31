package com.example.stranger.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.stranger.Activities.models.User;
//import com.example.stranger.Manifest;
import com.example.stranger.R;
import com.example.stranger.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    FirebaseAuth auth;
    FirebaseDatabase database;
    long coins=0;
    String permissions[]=new String[]{"android.permission.CAMERA","android.permission.RECORD_AUDIO"};
    private int requestCode=1;
    User user;
Button findButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(binding.getRoot());

        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //setting profile picture with current user profile picture from his gmail---->using glide sdk
        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        FirebaseUser currentUser=auth.getCurrentUser();

        database.getReference().child("profiles")
                        .child(currentUser.getUid())
                                .addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                                        user=snapshot.getValue(User.class);
                                        coins=user.getCoins();
                                        binding.coins.setText("You Have:\n "+coins);
                                        Glide.with(MainActivity.this)
                                                .load(user.getProfile())
                                                .into(binding.profileImage);
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });

        binding.findButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isPermissionGranted()){
                    if(coins>5){
                        Intent intent=new Intent(MainActivity.this,ConnectingActivity.class);
                        intent.putExtra("profile",user.getProfile());
                        startActivity(intent);
//                       startActivity(new Intent(MainActivity.this,ConnectingActivity.class));
                    }else{
                        Toast.makeText(MainActivity.this, "Insufficient coins", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    askpermission();
                }
            }
        });
    }

    void askpermission(){
        ActivityCompat.requestPermissions(this,permissions,requestCode);
    }

    private boolean isPermissionGranted(){
        for(String permission:permissions){
            if(ActivityCompat.checkSelfPermission(this,permission)!= PackageManager.PERMISSION_GRANTED){
                return false;
            }
        }
        return true;
    }
}