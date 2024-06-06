package com.gyarsilalsolanki011.makeattendance.activities.staff.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.gyarsilalsolanki011.makeattendance.R;
import com.gyarsilalsolanki011.makeattendance.activities.staff.model.AttendanceModel;

import java.util.ArrayList;

public class AttendanceRecyclerAdapter extends RecyclerView.Adapter<AttendanceRecyclerAdapter.ViewHolder> {
    Context context;
    ArrayList<AttendanceModel> arrAttends;

    public AttendanceRecyclerAdapter(Context context, ArrayList<AttendanceModel> arrAttends) {
        this.context = context;
        this.arrAttends = arrAttends;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.attendance_row, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull AttendanceRecyclerAdapter.ViewHolder holder, int position) {

        AttendanceModel model = (AttendanceModel) arrAttends.get(position);

        holder.fullName.setText(model.fullName);
        holder.present.setText(model.present.toString());
        holder.absent.setText(model.absent.toString());
        holder.percentage.setText(model.percentage.toString());

        holder.presentBtn.setOnClickListener(
                v -> {
                    updatePresentData(model);
                    holder.present.setText(model.present.toString());
                    holder.absent.setText(model.absent.toString());
                    updatePercentageData(model);
                    holder.percentage.setText(model.percentage.toString());
                }
        );

        holder.absentBtn.setOnClickListener(
                v -> {
                    updateAbsentData(model);
                    holder.present.setText(model.present.toString());
                    holder.absent.setText(model.absent.toString());
                    updatePercentageData(model);
                    holder.percentage.setText(model.percentage.toString());
                }
        );
    }

    @Override
    public int getItemCount() {
        return arrAttends.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView fullName, percentage, present, absent;
        FloatingActionButton presentBtn, absentBtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fullName = itemView.findViewById(R.id.student_name);
            percentage = itemView.findViewById(R.id.percentage);
            present = itemView.findViewById(R.id.present);
            absent = itemView.findViewById(R.id.absent);

            presentBtn = itemView.findViewById(R.id.button_mark);
            absentBtn = itemView.findViewById(R.id.button_cancel);
        }
    }

    private void updateAbsentData(AttendanceModel model) {
        model.absent += 1;
    }

    private void updatePresentData(AttendanceModel model) {
        model.present += 1;
    }

    private void updatePercentageData(AttendanceModel model) {
        model.percentage = (model.present/(model.present + model.absent)) * 100;
    }
}
