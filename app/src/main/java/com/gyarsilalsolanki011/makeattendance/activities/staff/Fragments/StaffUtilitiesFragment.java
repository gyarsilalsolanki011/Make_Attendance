package com.gyarsilalsolanki011.makeattendance.activities.staff.Fragments;

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

public class StaffUtilitiesFragment extends Fragment {

    private final FirebaseAuthRepository auth = new FirebaseAuthRepository();
    MaterialButton button;
    SharedPreferences sharedPreferences;

    public StaffUtilitiesFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_utilities2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Context context = getContext();
        button = view.findViewById(R.id.facultyLogoutButton);
        button.setOnClickListener(
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
        intent.putExtra("whichUser", "Faculty");
        startActivity(intent);
        getActivity().finish();
    }
}