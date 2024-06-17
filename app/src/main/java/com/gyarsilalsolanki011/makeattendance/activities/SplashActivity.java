package com.gyarsilalsolanki011.makeattendance.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.gyarsilalsolanki011.makeattendance.R;
import com.gyarsilalsolanki011.makeattendance.activities.staff.StaffHomeActivity;
import com.gyarsilalsolanki011.makeattendance.activities.student.StudentHomeActivity;
import com.gyarsilalsolanki011.makeattendance.repository.auth.FirebaseAuthRepository;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {
    private final FirebaseAuthRepository auth = new FirebaseAuthRepository();
    SharedPreferences sharedPreferences;
    String whichUser;

    @Override
    protected void onStart() {
        super.onStart();
        checkLoginStatus();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);

        checkLoginStatus();
    }

    private void checkLoginStatus() {
        sharedPreferences = getSharedPreferences("user_type", MODE_PRIVATE);
        whichUser = sharedPreferences.getString("whichUser", "Faculty");
        if (auth.getCurrentUser() != null) {
            Intent intent;
            switch (whichUser){
                case "Faculty":
                    intent = new Intent(SplashActivity.this, StaffHomeActivity.class);
                    break;
                case "Student":
                    intent = new Intent(SplashActivity.this, StudentHomeActivity.class);
                    break;
                default:
                    intent = new Intent(SplashActivity.this, MainActivity.class);
            }
            startActivity(intent);
            finish();
        } else {
            Intent i = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(i);
            finish();
        }
    }

}