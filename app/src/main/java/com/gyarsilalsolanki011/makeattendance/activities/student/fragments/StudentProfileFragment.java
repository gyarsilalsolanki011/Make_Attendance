package com.gyarsilalsolanki011.makeattendance.activities.student.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.gyarsilalsolanki011.makeattendance.R;

import java.util.Objects;

public class StudentProfileFragment extends Fragment {

    TextView fullName, fatherName, motherName, branch, semester, rollNumber, email, studentName;
    private final FirebaseFirestore database = FirebaseFirestore.getInstance();
    private  final FirebaseAuth Auth = FirebaseAuth.getInstance();
    String userId;
    public StudentProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fullName = view.findViewById(R.id.studentFullNameSet);
        fatherName = view.findViewById(R.id.studentFatherNameSet);
        motherName = view.findViewById(R.id.studentMotherNameSet);
        branch = view.findViewById(R.id.studentBranchSet);
        semester = view.findViewById(R.id.studentSemesterSet);
        email = view.findViewById(R.id.studentEmailSet);
        studentName = view.findViewById(R.id.studentNameSet);
        rollNumber = view.findViewById(R.id.rollNumberSet);

        fullName.setText("Gyarsilal Soanki");
        fatherName.setText("Gulabsingh Soanki");
        motherName.setText("SakaliBai Salani");
        rollNumber.setText("0201CS211039");
        branch.setText("CSE");
        semester.setText("VI");
        email.setText("gyarsilalsolanki011@gmail.com");

        //setDataOnStudentProfile(view);
    }

    private void setDataOnStudentProfile(View view) {

        fullName = view.findViewById(R.id.studentFullNameSet);
        fatherName = view.findViewById(R.id.studentFatherNameSet);
        motherName = view.findViewById(R.id.studentMotherNameSet);
        branch = view.findViewById(R.id.studentBranchSet);
        semester = view.findViewById(R.id.studentSemesterSet);
        email = view.findViewById(R.id.studentEmailSet);
        studentName = view.findViewById(R.id.studentNameSet);
        rollNumber = view.findViewById(R.id.rollNumberSet);

        userId = Objects.requireNonNull(Auth.getCurrentUser()).getUid();
        DocumentReference documentReference = database.collection("Students").document(userId);
        documentReference.addSnapshotListener((value, error) -> {
            if (error != null){
                Log.e("DataBase error", Objects.requireNonNull(error.getMessage()));
            }

            assert value != null;
            fullName.setText(value.getString("fullName"));
            studentName.setText(value.getString("fullName"));
            fatherName.setText(value.getString("fatherName"));
            motherName.setText(value.getString("motherName"));
            rollNumber.setText(value.getString("rollNumber"));
            branch.setText(value.getString("branch"));
            semester.setText(value.getString("semester"));
            email.setText(value.getString("email"));

            Log.d("Database values received", "fullName"+fullName);

        });
    }
}