package com.gyarsilalsolanki011.makeattendance.activities.staff.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gyarsilalsolanki011.makeattendance.R;

public class StaffProfileFragment extends Fragment {

    TextView fullName, branch, semester, subject, email, facultyName;
    public StaffProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fullName = view.findViewById(R.id.facultyFullNameSet);
        branch = view.findViewById(R.id.facultyBranchSet);
        semester = view.findViewById(R.id.facultySemesterSet);
        email = view.findViewById(R.id.facultyEmailSet);
        facultyName = view.findViewById(R.id.facultyNameSet);
        subject = view.findViewById(R.id.subjectSet);

        fullName.setText("Gyarsilal Soanki");
        subject.setText("Machine Learning");
        facultyName.setText("Gyarsilal Solanki");
        branch.setText("CSE");
        semester.setText("VI");
        email.setText("gyarsilalsolanki011@gmail.com");

    }
}