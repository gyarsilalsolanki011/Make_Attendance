package com.gyarsilalsolanki011.makeattendance.activities.staff.Fragments;

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

public class UtilitiesFragment extends Fragment {

    private final FirebaseAuthRepository auth = new FirebaseAuthRepository();
    MaterialButton button;

    public UtilitiesFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_utilities2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        button = view.findViewById(R.id.facultyLogoutButton);
        button.setOnClickListener(v ->{
            auth.logout();
            Intent intent = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);
        });
    }
}