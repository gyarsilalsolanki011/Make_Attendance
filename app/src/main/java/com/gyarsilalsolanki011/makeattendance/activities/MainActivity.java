package com.gyarsilalsolanki011.makeattendance.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.gyarsilalsolanki011.makeattendance.databinding.ActivityMainBinding;
import com.gyarsilalsolanki011.makeattendance.repository.auth.FirebaseAuthRepository;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private final FirebaseAuthRepository auth = new FirebaseAuthRepository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.email.setText(auth.getCurrentUser().getEmail());
        binding.logoutButton.setOnClickListener(
                v-> logout()
        );
    }

    private void logout(){
        auth.logout();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}