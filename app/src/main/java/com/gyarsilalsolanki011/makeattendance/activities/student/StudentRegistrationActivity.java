package com.gyarsilalsolanki011.makeattendance.activities.student;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.gyarsilalsolanki011.makeattendance.databinding.ActivityStudentRegistrationBinding;
import com.gyarsilalsolanki011.makeattendance.repository.auth.FirebaseAuthRepository;
import com.gyarsilalsolanki011.makeattendance.repository.user.FirebaseUserRepository;
import com.gyarsilalsolanki011.makeattendance.repository.user.model.User;

import java.util.Objects;

public class StudentRegistrationActivity extends AppCompatActivity {

    private String fullName, email, password;
    private ActivityStudentRegistrationBinding binding;
    private final FirebaseAuthRepository auth = new FirebaseAuthRepository();
    private final FirebaseUserRepository user = new FirebaseUserRepository();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityStudentRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fullName = getIntent().getStringExtra("fullName");
        email = getIntent().getStringExtra("email");
        password = getIntent().getStringExtra("password");

        binding.buttonSubmit.setOnClickListener(
                v -> createUser()
        );
    }

    private void createUser() {
        String studentName = Objects.requireNonNull(binding.editTextStudentName.getText()).toString().trim();
        String branch = Objects.requireNonNull(binding.editTextBranch.getText()).toString().trim();
        String semester = Objects.requireNonNull(binding.editTextSemester.getText()).toString().trim();
        String rollNumber = Objects.requireNonNull(binding.editTextRollNumber.getText()).toString().trim();
        String fatherName = Objects.requireNonNull(binding.editTextFatherName.getText()).toString().trim();
        String motherName = Objects.requireNonNull(binding.editTextMotherName.getText()).toString().trim();

        if (TextUtils.isEmpty(studentName)) {
            Snackbar.make(binding.getRoot(), "Student Name is required", Snackbar.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(semester)) {
            Snackbar.make(binding.getRoot(), "Semester is required", Snackbar.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(rollNumber)) {
            Snackbar.make(binding.getRoot(), "Roll Number is required", Snackbar.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(branch)){
            Snackbar.make(binding.getRoot(), "Branch is required", Snackbar.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(fatherName)) {
            Snackbar.make(binding.getRoot(), "Father Name is required", Snackbar.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(motherName)) {
            Snackbar.make(binding.getRoot(), "Mother Name is required", Snackbar.LENGTH_SHORT).show();
        } else {
            if (fullName.equals(studentName)) {
                binding.progressIndicator.setVisibility(View.VISIBLE);
                binding.buttonSubmit.setVisibility(View.GONE);

                Task<AuthResult> task = auth.register(email, password);
                task.addOnSuccessListener(
                        authResult -> {
                            user.setStudentData(User.student(email, fullName, rollNumber, branch, semester, fatherName, motherName));
                            Intent iStudentHome = new Intent(StudentRegistrationActivity.this, StudentHomeActivity.class);
                            startActivity(iStudentHome);
                            finish();
                        });

                task.addOnFailureListener(
                        error ->{
                            binding.progressIndicator.setVisibility(View.GONE);
                            binding.buttonSubmit.setVisibility(View.VISIBLE);
                            Snackbar.make(binding.getRoot(), Objects.requireNonNull(error.getMessage()), Snackbar.LENGTH_SHORT).show();
                        });
            } else {
                Snackbar.make(binding.getRoot(), "Please enter the same Name", Snackbar.LENGTH_SHORT).show();
            }
        }
    }
}