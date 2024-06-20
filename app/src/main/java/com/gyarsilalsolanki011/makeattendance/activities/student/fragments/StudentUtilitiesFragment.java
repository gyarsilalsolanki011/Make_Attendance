package com.gyarsilalsolanki011.makeattendance.activities.student.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
    SharedPreferences sharedPreferences;
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
        Context context = getContext();
        studentLogoutBtn = view.findViewById(R.id.studentLogoutButton);
        studentLogoutBtn.setOnClickListener(
                v -> {
                    assert context != null;
                    logout(context);
                }
        );
    }

    @SuppressLint("CommitPrefEdits")
    private void logout(@NonNull Context context) {
        auth.logout();
        assert this.getActivity() != null;
        sharedPreferences = this.getActivity().getSharedPreferences("user_type", Context.MODE_PRIVATE);
        sharedPreferences.edit().clear().apply();
        Intent intent = new Intent(context, LoginActivity.class);
        intent.putExtra("whichUser", "Student");
        startActivity(intent);
        getActivity().finish();
    }

}