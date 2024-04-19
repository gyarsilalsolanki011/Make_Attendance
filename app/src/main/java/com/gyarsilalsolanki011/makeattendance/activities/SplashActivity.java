package com.gyarsilalsolanki011.makeattendance.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.gyarsilalsolanki011.makeattendance.R;
import com.gyarsilalsolanki011.makeattendance.repository.auth.FirebaseAuthRepository;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    private final FirebaseAuthRepository auth = new FirebaseAuthRepository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);
        checkLoginStatus();
    }

    private void checkLoginStatus() {
        if (auth.getCurrentUser() != null) {
            Intent i = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        } else {
            Intent i = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(i);
            finish();
        }
    }
}