package com.gyarsilalsolanki011.makeattendance.activities.staff;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.gyarsilalsolanki011.makeattendance.activities.student.StudentHomeActivity;
import com.gyarsilalsolanki011.makeattendance.activities.student.StudentRegistrationActivity;
import com.gyarsilalsolanki011.makeattendance.databinding.ActivityStaffRegistrationBinding;
import com.gyarsilalsolanki011.makeattendance.repository.auth.FirebaseAuthRepository;
import com.gyarsilalsolanki011.makeattendance.repository.user.FirebaseUserRepository;
import com.gyarsilalsolanki011.makeattendance.repository.user.model.User;

import java.util.Objects;

public class StaffRegistrationActivity extends AppCompatActivity {

    private String fullName, email, password;
    private ActivityStaffRegistrationBinding binding;
    private final FirebaseAuthRepository auth = new FirebaseAuthRepository();
    private final FirebaseUserRepository user = new FirebaseUserRepository();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityStaffRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fullName = getIntent().getStringExtra("fullName");
        email = getIntent().getStringExtra("email");
        password = getIntent().getStringExtra("password");

        binding.buttonSubmit.setOnClickListener(
                v ->  createUser()
        );
    }

    private void createUser() {
        String facultyName = Objects.requireNonNull(binding.editTextFacultyName.getText()).toString().trim();
        String branch = Objects.requireNonNull(binding.editTextBranch.getText()).toString().trim();
        String semester = Objects.requireNonNull(binding.editTextSemester.getText()).toString().trim();
        String subject = Objects.requireNonNull(binding.editTextSubject.getText()).toString().trim();

        if (TextUtils.isEmpty(facultyName)) {
            Snackbar.make(binding.getRoot(), "Faculty Name is required", Snackbar.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(semester)) {
            Snackbar.make(binding.getRoot(), "Semester is required", Snackbar.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(subject)) {
            Snackbar.make(binding.getRoot(), "Subject is required", Snackbar.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(branch)){
            Snackbar.make(binding.getRoot(), "Branch is required", Snackbar.LENGTH_SHORT).show();
        } else {
            if (fullName.equals(facultyName)) {
                binding.progressIndicator.setVisibility(View.VISIBLE);
                binding.buttonSubmit.setVisibility(View.GONE);

                Task<AuthResult> task = auth.register(email, password);
                task.addOnSuccessListener(
                        authResult -> {
                            user.setFacultyData(User.faculty(email, facultyName, branch, semester, subject));
                            Intent iFacultyHome = new Intent(StaffRegistrationActivity.this, StaffHomeActivity.class);
                            startActivity(iFacultyHome);
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