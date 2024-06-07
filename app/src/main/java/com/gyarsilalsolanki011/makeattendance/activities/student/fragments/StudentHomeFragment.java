package com.gyarsilalsolanki011.makeattendance.activities.student.fragments;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gyarsilalsolanki011.makeattendance.R;
import com.gyarsilalsolanki011.makeattendance.activities.student.adapter.AttendanceViewRecyclerAdapter;
import com.gyarsilalsolanki011.makeattendance.activities.student.model.AttendanceViewModel;

import java.util.ArrayList;


public class StudentHomeFragment extends Fragment {
    ArrayList<AttendanceViewModel> arrayListAttendance;
    private ProgressDialog progressDialog;
    AttendanceViewRecyclerAdapter adapter;

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

        String[] notificationList = new String[]{
                getString(R.string.notification1),
                getString(R.string.notification2),
                getString(R.string.notification1),
                getString(R.string.notification2),
                getString(R.string.notification1)
        };

        for (int i=0; i<subjectList.length; i++){
            AttendanceViewModel model = new AttendanceViewModel(subjectList[i], notificationList[i], 2, 2);
            arrayListAttendance.add(model);
        }

    }

    private void openProgressDialog() {

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(true);
        progressDialog.setMessage("Fetching Data....");
        progressDialog.show();

    }
}