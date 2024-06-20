package com.gyarsilalsolanki011.makeattendance.activities.student.fragments;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.gyarsilalsolanki011.makeattendance.R;
import com.gyarsilalsolanki011.makeattendance.activities.student.adapter.AttendanceViewRecyclerAdapter;
import com.gyarsilalsolanki011.makeattendance.activities.student.model.AttendanceViewModel;

import java.util.ArrayList;
import java.util.Objects;


public class StudentHomeFragment extends Fragment {
    ArrayList<AttendanceViewModel> arrayListAttendance;
    private String userId;
    private int present, absent, percentage;
    private ProgressDialog progressDialog;
    AttendanceViewRecyclerAdapter adapter;
    private final FirebaseFirestore database = FirebaseFirestore.getInstance();
    private  final FirebaseAuth Auth = FirebaseAuth.getInstance();

    public StudentHomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        openProgressDialog();
        dataInitialize();

        RecyclerView recyclerView = view.findViewById(R.id.viewRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new AttendanceViewRecyclerAdapter(getContext(), arrayListAttendance);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        if (progressDialog.isShowing()) progressDialog.dismiss();
    }

    private void dataInitialize() {

        arrayListAttendance = new ArrayList<>();

        String[] subjectList = new String[]{
                getString(R.string.mc),
                getString(R.string.ml),
                getString(R.string.cn),
                getString(R.string.ip),
                getString(R.string.se),
        };

        for (String s : subjectList) {
            userId = Objects.requireNonNull(Auth.getCurrentUser()).getUid();
            DocumentReference documentReference = database.collection(s).document(userId);
            documentReference.addSnapshotListener((value, error) -> {
                if (error != null) {
                    Log.e("DataBase Attendance error", Objects.requireNonNull(error.getMessage()));
                }

                assert value != null;
                present = Math.toIntExact(value.getLong("present"));
                absent = Math.toIntExact(value.getLong("absent"));
                percentage = Math.toIntExact(value.getLong("percentage"));
            });
            present = 2;
            absent = 2;
            percentage = 76;
            AttendanceViewModel model = new AttendanceViewModel(s, getNotification(percentage), present, present+absent);
            arrayListAttendance.add(model);
        }
    }

    private String getNotification(int percentage) {
        if (percentage <= 75){
            return getString(R.string.notification1);
        } else {
            return getString(R.string.notification2);
        }
    }

    private void openProgressDialog() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(true);
        progressDialog.setMessage("Fetching Data....");
        progressDialog.show();
    }
}