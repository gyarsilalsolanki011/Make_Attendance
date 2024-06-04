package com.gyarsilalsolanki011.makeattendance.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.gyarsilalsolanki011.makeattendance.activities.staff.StaffRegistrationActivity;
import com.gyarsilalsolanki011.makeattendance.activities.student.StudentRegistrationActivity;
import com.gyarsilalsolanki011.makeattendance.databinding.ActivityRegisterBinding;
import com.gyarsilalsolanki011.makeattendance.repository.user.UserType;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    private String whichUser;
    private ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        whichUser = getIntent().getStringExtra("whichUser");

        binding.alreadyAccountTextButton.setOnClickListener(v-> finish());

        binding.createAccountButton.setOnClickListener(
                v -> createUser()
        );
    }

    private void createUser(){
        String email = Objects.requireNonNull(binding.emailTextEdit.getText()).toString().trim();
        String fullName = Objects.requireNonNull(binding.fullNameTextEdit.getText()).toString().trim();
        String password = Objects.requireNonNull(binding.passwordTextEdit.getText()).toString().trim();
        String passwordConfirm = Objects.requireNonNull(binding.passwordConfirmTextEdit.getText()).toString().trim();

        if (TextUtils.isEmpty(email)) {
            Snackbar.make(binding.getRoot(), "Email is required", Snackbar.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(fullName)) {
            Snackbar.make(binding.getRoot(), "Name is required", Snackbar.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            Snackbar.make(binding.getRoot(), "Password is required", Snackbar.LENGTH_SHORT).show();
        } else {
            if (password.equals(passwordConfirm)) {
                binding.progressIndicator.setVisibility(View.VISIBLE);
                binding.createAccountButton.setVisibility(View.GONE);

                if (whichUser.equals(UserType.Student.toString())){

                    Intent iStudent = new Intent(RegisterActivity.this, StudentRegistrationActivity.class);
                    iStudent.putExtra("email", email);
                    iStudent.putExtra("fullName", fullName);
                    startActivity(iStudent);
                    finish();

                } else if (whichUser.equals(UserType.Faculty.toString())) {

                    Intent iStudent = new Intent(RegisterActivity.this, StaffRegistrationActivity.class);
                    iStudent.putExtra("email", email);
                    iStudent.putExtra("fullName", fullName);
                    startActivity(iStudent);
                    finish();

                } else {

                    Snackbar.make(binding.getRoot(), "Admin Introduced soon", Snackbar.LENGTH_SHORT).show();

                }

            } else {
                Snackbar.make(binding.getRoot(), "Confirm password did not match", Snackbar.LENGTH_SHORT).show();
            }
        }
    }
}