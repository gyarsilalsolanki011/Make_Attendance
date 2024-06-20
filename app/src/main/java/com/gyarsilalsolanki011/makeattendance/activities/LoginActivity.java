package com.gyarsilalsolanki011.makeattendance.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

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
    String userType;
    RadioButton radioButton;
    SharedPreferences sharedPreferences;
    private ActivityLoginBinding binding;
    private final FirebaseAuthRepository auth = new FirebaseAuthRepository();
    private final FirebaseUserRepository userRepository = new FirebaseUserRepository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        whichUser = getIntent().getStringExtra("whichUser");
        if (whichUser == null){
            binding.selectLinearLayout.setVisibility(View.VISIBLE);
            binding.loginLinearLayout.setVisibility(View.GONE);
            binding.moveForward.setOnClickListener(v -> {
                whichUser = selectYourField();
                Toast.makeText(this, "You have Selected "+whichUser, Toast.LENGTH_SHORT).show();
            });
        } else {
            binding.selectLinearLayout.setVisibility(View.GONE);
            binding.loginLinearLayout.setVisibility(View.VISIBLE);
        }

        binding.moveForward.setOnClickListener(v -> {
            whichUser = selectYourField();
            Toast.makeText(this, "You have Selected "+whichUser, Toast.LENGTH_SHORT).show();
        });

        binding.createAccountTextButton.setOnClickListener(
                v -> Register()
        );

        binding.loginButton.setOnClickListener(
                v -> login()
        );
    }

    private void Register(){
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
            binding.loginProgressIndicator.setVisibility(View.VISIBLE);
            binding.loginButton.setVisibility(View.GONE);

            Task<AuthResult> task = auth.login(email, password);
            task.addOnSuccessListener(
                    result -> checkUser()
            );

            task.addOnFailureListener(error -> {
                binding.loginProgressIndicator.setVisibility(View.GONE);
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
                        sharedPreferences = getSharedPreferences("user_type", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("whichUser", "Student");
                        editor.apply();
                        Intent iStudentView = new Intent(LoginActivity.this, StudentHomeActivity.class);
                        startActivity(iStudentView);
                        finish();
                    }

            );
        }

        else {
            userRepository.getFacultyData().addOnSuccessListener(
                    doc -> {
                        sharedPreferences = getSharedPreferences("user_type", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("whichUser", "Faculty");
                        editor.apply();
                        Intent iFacultyView = new Intent(LoginActivity.this, StaffHomeActivity.class);
                        startActivity(iFacultyView);
                        finish();
                    }
            );
        }
    }

    private String selectYourField() {

        int selectedId = binding.radioGroup.getCheckedRadioButtonId();
        binding.moveForward.setVisibility(View.GONE);
        binding.selectProgressIndicator.setVisibility(View.VISIBLE);
        radioButton = findViewById(selectedId);
        userType = radioButton.getText().toString();

        new Handler().postDelayed(() -> {
            binding.selectLinearLayout.setVisibility(View.GONE);
            binding.loginLinearLayout.setVisibility(View.VISIBLE);
        },1000);
        return userType;
    }

}