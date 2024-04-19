package com.gyarsilalsolanki011.makeattendance.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.gyarsilalsolanki011.makeattendance.databinding.ActivityRegisterBinding;
import com.gyarsilalsolanki011.makeattendance.repository.auth.FirebaseAuthRepository;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    private final FirebaseAuthRepository auth = new FirebaseAuthRepository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.alreadyAccountTextButton.setOnClickListener(v->{
            finish();
        });

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
                Task<AuthResult> task = auth.register(email, password);
                task.addOnSuccessListener(
                        result -> {
                            FirebaseUser user = result.getUser();
                            assert user != null;
                            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }
                );
                task.addOnFailureListener(
                        error -> {
                            binding.progressIndicator.setVisibility(View.GONE);
                            binding.createAccountButton.setVisibility(View.VISIBLE);
                            FirebaseException authError = (FirebaseException) error;
                            Snackbar.make(binding.getRoot(), Objects.requireNonNull(authError.getMessage()), Snackbar.LENGTH_SHORT).show();
                        }
                );
            } else {
                Snackbar.make(binding.getRoot(), "Confirm password did not match", Snackbar.LENGTH_SHORT).show();
            }
        }
    }
}