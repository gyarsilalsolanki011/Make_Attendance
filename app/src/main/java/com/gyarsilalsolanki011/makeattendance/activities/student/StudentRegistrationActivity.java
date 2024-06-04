package com.gyarsilalsolanki011.makeattendance.activities.student;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.gyarsilalsolanki011.makeattendance.R;
import com.gyarsilalsolanki011.makeattendance.databinding.ActivityStudentRegistrationBinding;
import com.gyarsilalsolanki011.makeattendance.repository.auth.FirebaseAuthRepository;
import com.gyarsilalsolanki011.makeattendance.repository.user.FirebaseUserRepository;

public class StudentRegistrationActivity extends AppCompatActivity {

    private String fullName, email;
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

    }
}