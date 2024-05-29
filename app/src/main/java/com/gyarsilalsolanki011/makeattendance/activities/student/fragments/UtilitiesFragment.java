package com.gyarsilalsolanki011.makeattendance.activities.student.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gyarsilalsolanki011.makeattendance.R;
import com.gyarsilalsolanki011.makeattendance.activities.LoginActivity;
import com.gyarsilalsolanki011.makeattendance.activities.MainActivity;
import com.gyarsilalsolanki011.makeattendance.databinding.FragmentUtilitiesBinding;
import com.gyarsilalsolanki011.makeattendance.repository.auth.FirebaseAuthRepository;

public class UtilitiesFragment extends Fragment {
    FragmentUtilitiesBinding binding;
    private final FirebaseAuthRepository auth = new FirebaseAuthRepository();
    public UtilitiesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUtilitiesBinding.inflate(getLayoutInflater());

        binding.logoutButton.setOnClickListener(v -> {
            logout();
        });
        return inflater.inflate(R.layout.fragment_utilities, container, false);
    }

    private void logout(){
        auth.logout();
        Intent intent = new Intent(getContext(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}