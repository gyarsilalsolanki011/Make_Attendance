package com.gyarsilalsolanki011.makeattendance.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.gyarsilalsolanki011.makeattendance.R;
import com.gyarsilalsolanki011.makeattendance.databinding.ActivityLoginBinding;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.createAccountTextButton.setOnClickListener(
                v -> {}
        );

        binding.loginButton.setOnClickListener(
                v -> {
                    String email = Objects.requireNonNull(binding.emailTextEdit.getText()).toString().trim();
                    String password = Objects.requireNonNull(binding.passwordTextEdit.getText()).toString().trim();

                    if(TextUtils.isEmpty(email)){
                        Snackbar.make(binding.getRoot(), "Email is required", Snackbar.LENGTH_SHORT).show();
                    }else if(TextUtils.isEmpty(password)){
                        Snackbar.make(binding.getRoot(), "Password is required", Snackbar.LENGTH_SHORT).show();
                    }else{
                        Snackbar.make(binding.getRoot(), "Done", Snackbar.LENGTH_SHORT).show();
                    }
                }
        );
    }
}