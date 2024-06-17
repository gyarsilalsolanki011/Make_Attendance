package com.gyarsilalsolanki011.makeattendance.activities.staff.Fragments;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.gyarsilalsolanki011.makeattendance.R;
import com.gyarsilalsolanki011.makeattendance.activities.staff.adapter.AttendanceRecyclerAdapter;
import com.gyarsilalsolanki011.makeattendance.activities.staff.model.AttendanceModel;
import java.util.ArrayList;

public class StaffHomeFragment extends Fragment {
    ArrayList<AttendanceModel> arrAttends = new ArrayList<>();
    AttendanceRecyclerAdapter adapter;
    ProgressDialog progressDialog;

    public StaffHomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home2, container, false);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        openProgressDialog();
        dataInitialize();

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new AttendanceRecyclerAdapter(getContext(), arrAttends);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        if (progressDialog.isShowing()) progressDialog.dismiss();
    }

    private void dataInitialize() {

        arrAttends.add(new AttendanceModel("Gyarsilal Solanki",2,2,2));
        arrAttends.add(new AttendanceModel("Motilal Solanki",2,2,2));
        arrAttends.add(new AttendanceModel("Shantilal Solanki",2,2,2));
        arrAttends.add(new AttendanceModel("Jogilal Solanki",2,2,2));
        arrAttends.add(new AttendanceModel("Bhaiyu Solanki",2,2,2));
        arrAttends.add(new AttendanceModel("Jogilal Solanki",2,2,2));
        arrAttends.add(new AttendanceModel("Motilal Solanki",2,2,2));
        arrAttends.add(new AttendanceModel("Shantilal Solanki",2,2,2));
        arrAttends.add(new AttendanceModel("Jogilal Solanki",2,2,2));
        arrAttends.add(new AttendanceModel("Bhaiyu Solanki",2,2,2));
        arrAttends.add(new AttendanceModel("Jogilal Solanki",2,2,2));

    }

    private void openProgressDialog() {

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(true);
        progressDialog.setMessage("Fetching Data....");
        progressDialog.show();

    }
}