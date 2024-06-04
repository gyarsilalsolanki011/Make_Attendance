package com.gyarsilalsolanki011.makeattendance.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.gyarsilalsolanki011.makeattendance.activities.staff.StaffHomeActivity;
import com.gyarsilalsolanki011.makeattendance.activities.student.StudentHomeActivity;
import com.gyarsilalsolanki011.makeattendance.databinding.ActivityLoginBinding;
import com.gyarsilalsolanki011.makeattendance.repository.auth.FirebaseAuthRepository;
import com.gyarsilalsolanki011.makeattendance.repository.user.FirebaseUserRepository;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    private String whichUser;
    private ActivityLoginBinding binding;
    private final FirebaseAuthRepository auth = new FirebaseAuthRepository();
    private final FirebaseUserRepository userRepository = new FirebaseUserRepository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        whichUser = "Student";

        binding.createAccountTextButton.setOnClickListener(
                v -> Register()
        );

        binding.loginButton.setOnClickListener(
                v -> login()
        );
    }

    private  void Register(){
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        intent.putExtra("whichUser", whichUser);
        startActivity(intent);
        finish();
    }


    private void login(){
        String email = Objects.requireNonNull(binding.emailTextEdit.getText()).toString().trim();
        String password = Objects.requireNonNull(binding.passwordTextEdit.getText()).toString().trim();

        if(TextUtils.isEmpty(email)){
            Snackbar.make(binding.getRoot(), "Email is required", Snackbar.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(password)){
            Snackbar.make(binding.getRoot(), "Password is required", Snackbar.LENGTH_SHORT).show();
        }else{
            binding.progressIndicator.setVisibility(View.VISIBLE);
            binding.loginButton.setVisibility(View.GONE);

            Task<AuthResult> task = auth.login(email, password);
            task.addOnSuccessListener(
                    result -> checkUser()
            );

            task.addOnFailureListener(error -> {
                binding.progressIndicator.setVisibility(View.GONE);
                binding.loginButton.setVisibility(View.VISIBLE);
                Log.d("AUTH", error.toString());
                Snackbar.make(binding.getRoot(), Objects.requireNonNull(error.getMessage()), Snackbar.LENGTH_SHORT).show();
            });
        }
    }

    private void checkUser() {
        if (whichUser.equals("Student")) {
            userRepository.getStudentData().addOnSuccessListener(
                    doc -> {
                        SharedPreferences sharedPreferences = getSharedPreferences("user_data", MODE_PRIVATE);
                        sharedPreferences.edit().putString("userType", "Student").apply();
                        Intent iStudentView = new Intent(LoginActivity.this, StudentHomeActivity.class);
                        startActivity(iStudentView);
                        finish();
                    }
            );
        }

        else {
            userRepository.getFacultyData().addOnSuccessListener(
                    doc -> {
                        SharedPreferences sharedPreferences = getSharedPreferences("user_data", MODE_PRIVATE);
                        sharedPreferences.edit().putString("userType", "Faculty").apply();
                        Intent iFacultyView = new Intent(LoginActivity.this, StaffHomeActivity.class);
                        startActivity(iFacultyView);
                        finish();
                    }
            );
        }
    }

}