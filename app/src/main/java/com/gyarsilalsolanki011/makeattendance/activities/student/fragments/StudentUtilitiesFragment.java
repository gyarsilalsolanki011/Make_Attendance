package com.gyarsilalsolanki011.makeattendance.activities.student.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;
import com.gyarsilalsolanki011.makeattendance.R;
import com.gyarsilalsolanki011.makeattendance.activities.LoginActivity;
import com.gyarsilalsolanki011.makeattendance.repository.auth.FirebaseAuthRepository;

public class StudentUtilitiesFragment extends Fragment {
    MaterialButton studentLogoutBtn;
    private final FirebaseAuthRepository auth = new FirebaseAuthRepository();
    public StudentUtilitiesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_utilities, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        studentLogoutBtn = view.findViewById(R.id.studentlogoutButton);
        studentLogoutBtn.setOnClickListener(v -> {
            auth.logout();
            Intent intent = new Intent(getContext(), LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
    }
}