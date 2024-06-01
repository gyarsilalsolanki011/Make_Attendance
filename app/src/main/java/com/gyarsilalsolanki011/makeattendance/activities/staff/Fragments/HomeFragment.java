package com.gyarsilalsolanki011.makeattendance.activities.staff.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.gyarsilalsolanki011.makeattendance.R;
import com.gyarsilalsolanki011.makeattendance.activities.staff.adapter.AttendanceRecyclerAdapter;
import com.gyarsilalsolanki011.makeattendance.activities.staff.model.AttendanceModel;
import com.gyarsilalsolanki011.makeattendance.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    ArrayList<AttendanceModel> arrAttends = new ArrayList<>();
    AttendanceRecyclerAdapter adapter;
    ProgressDialog progressDialog;
    FragmentHomeBinding binding;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentHomeBinding.inflate(getLayoutInflater());

        openProgressDialog();


    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home2, container, false);
    }

    private void openProgressDialog() {

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(true);
        progressDialog.setMessage("Fetching Data....");
        progressDialog.show();

    }
}