package com.gyarsilalsolanki011.makeattendance.activities.staff.Fragments;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.gyarsilalsolanki011.makeattendance.R;
import com.gyarsilalsolanki011.makeattendance.activities.staff.adapter.AttendanceRecyclerAdapter;
import com.gyarsilalsolanki011.makeattendance.activities.staff.model.AttendanceModel;

import java.util.ArrayList;
import java.util.Objects;

public class StaffHomeFragment extends Fragment {
    ArrayList<AttendanceModel> arrAttends = new ArrayList<>();
    private final FirebaseFirestore database = FirebaseFirestore.getInstance();
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
        //dataInitialize();

        arrAttends.add(new AttendanceModel(3, "Gyarsilal Solanki", 1, 75));
        arrAttends.add(new AttendanceModel(2, "Mohan Pyare", 2, 50));
        arrAttends.add(new AttendanceModel(4, "Bhaiyalal Bare", 0, 100));
        arrAttends.add(new AttendanceModel(3, "Ramcharan Chouhan", 1, 75));
        arrAttends.add(new AttendanceModel(4, "Nandkishor More", 0, 100));
        arrAttends.add(new AttendanceModel(4, "Chandan Nagvanshi", 0, 100));
        arrAttends.add(new AttendanceModel(3, "Nirmla Goad", 1, 75));
        arrAttends.add(new AttendanceModel(2, "Naman Mandloi", 2, 50));
        arrAttends.add(new AttendanceModel(4, "Anil Yadav", 0, 100));
        arrAttends.add(new AttendanceModel(3, "Vivek Tripathi", 1, 75));
        arrAttends.add(new AttendanceModel(3, "Ramshankar Negi", 1, 75));
        arrAttends.add(new AttendanceModel(3, "Abhimanyu Thakur", 1, 75));
        arrAttends.add(new AttendanceModel(4, "Jaishanker Yadav", 0, 100));
        arrAttends.add(new AttendanceModel(4, "Nitesh fakir", 0, 100));
        arrAttends.add(new AttendanceModel(2, "Kumar Pandit", 2, 50));
        arrAttends.add(new AttendanceModel(3, "Deelip Kripalu", 1, 75));

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new AttendanceRecyclerAdapter(getContext(), arrAttends);
        recyclerView.setAdapter(adapter);
        if (progressDialog.isShowing()) progressDialog.dismiss();
    }

    private void dataInitialize() {
        database.collection("Machine Learning").orderBy("fullName", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if (error != null){
                            if (progressDialog.isShowing()) progressDialog.dismiss();
                            Log.e("FireStore Database error", Objects.requireNonNull(error.getMessage()));
                            return;
                        }
                        assert value != null;
                        for (DocumentChange dc : value.getDocumentChanges()){

                            if (dc.getType() == DocumentChange.Type.ADDED){
                                arrAttends.add(dc.getDocument().toObject(AttendanceModel.class));
                            }
                            adapter.notifyDataSetChanged();
                            if (progressDialog.isShowing()) progressDialog.dismiss();
                        }
                    }
                });
    }

    private void openProgressDialog() {

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(true);
        progressDialog.setMessage("Fetching Data....");
        progressDialog.show();

    }
}