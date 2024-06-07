package com.gyarsilalsolanki011.makeattendance.activities.staff.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.gyarsilalsolanki011.makeattendance.R;

import java.util.Objects;

public class StaffProfileFragment extends Fragment {

    TextView fullName, branch, semester, subject, email, facultyName;
    private final FirebaseFirestore database = FirebaseFirestore.getInstance();
    private  final FirebaseAuth Auth = FirebaseAuth.getInstance();
    String userId;
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
        subject = view.findViewById(R.id.rollNumberSet);

        fullName.setText("Gyarsilal Soanki");
        subject.setText("Machine Learning");
        branch.setText("CSE");
        semester.setText("VI");
        email.setText("gyarsilalsolanki@gmail.com");
        //setDataOnFacultyProfile(view);
    }

    private void setDataOnFacultyProfile(View view) {

        fullName = view.findViewById(R.id.facultyFullNameSet);
        branch = view.findViewById(R.id.facultyBranchSet);
        semester = view.findViewById(R.id.facultySemesterSet);
        email = view.findViewById(R.id.facultyEmailSet);
        facultyName = view.findViewById(R.id.facultyNameSet);
        subject = view.findViewById(R.id.rollNumberSet);

        userId = Objects.requireNonNull(Auth.getCurrentUser()).getUid();
        DocumentReference documentReference = database.collection("Students").document(userId);
        documentReference.addSnapshotListener((value, error) -> {
            if (error != null){
                Log.e("DataBase error", Objects.requireNonNull(error.getMessage()));
            }

            assert value != null;
            fullName.setText(value.getString("fullName"));
            facultyName.setText(value.getString("fullName"));
            subject.setText(value.getString("subject"));
            branch.setText(value.getString("branch"));
            semester.setText(value.getString("semester"));
            email.setText(value.getString("email"));
        });
    }
}