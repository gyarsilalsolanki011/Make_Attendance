package com.gyarsilalsolanki011.makeattendance.activities;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.gyarsilalsolanki011.makeattendance.R;
import com.gyarsilalsolanki011.makeattendance.activities.staff.StaffHomeActivity;
import com.gyarsilalsolanki011.makeattendance.activities.student.StudentHomeActivity;
import com.gyarsilalsolanki011.makeattendance.repository.auth.FirebaseAuthRepository;
import com.gyarsilalsolanki011.makeattendance.repository.user.UserType;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {
    RadioGroup group;
    String userType;
    MaterialButton submitBtn;
    RadioButton radioButton;
    private final FirebaseAuthRepository auth = new FirebaseAuthRepository();

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
        SharedPreferences sharedPreferences = getSharedPreferences("user_data", MODE_PRIVATE);
        UserType userType = UserType.valueOf(sharedPreferences.getString("userType", "Student"));
        if (auth.getCurrentUser() != null) {
            Intent intent;
            switch (userType){
                case Faculty:
                    intent = new Intent(SplashActivity.this, StaffHomeActivity.class);
                    break;
                case Student:
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

    private String selectYourField() {
        group = findViewById(R.id.radioGroup);
        submitBtn = findViewById(R.id.submitBtn);

        Dialog dialog = new Dialog(getApplicationContext());
        dialog.setContentView(R.layout.login_option);
        dialog.setCancelable(false);
        dialog.show();

        submitBtn.setOnClickListener(v -> {
            int ID = group.getCheckedRadioButtonId();
            radioButton = findViewById(ID);
            userType = radioButton.getText().toString();
            dialog.dismiss();
        });
        return userType;
    }
}