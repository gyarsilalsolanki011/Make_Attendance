package com.gyarsilalsolanki011.makeattendance.activities.student.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gyarsilalsolanki011.makeattendance.activities.student.model.AttendanceViewModel;

import java.util.ArrayList;

public class AttendanceViewRecyclerAdapter extends RecyclerView.Adapter<AttendanceViewRecyclerAdapter.ViewHolder> {
    Context context;
    ArrayList<AttendanceViewModel> arrayListAttendance;

    public AttendanceViewRecyclerAdapter(Context context, ArrayList<AttendanceViewModel> arrayList) {
        this.context = context;
        this.arrayListAttendance = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AttendanceViewRecyclerAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
