package com.gyarsilalsolanki011.makeattendance.activities.student.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gyarsilalsolanki011.makeattendance.R;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.attendance_view, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull AttendanceViewRecyclerAdapter.ViewHolder holder, int position) {

        AttendanceViewModel model = (AttendanceViewModel) arrayListAttendance.get(position);
        holder.subject.setText(model.getSubject());
        holder.notification.setText(model.getNotification());
        holder.classAttended.setText(model.getClassesAttended().toString());
        holder.classConducted.setText(model.getClassesConducted().toString());
    }

    @Override
    public int getItemCount() {
        return arrayListAttendance.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView subject, notification, classAttended, classConducted;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            subject = itemView.findViewById(R.id.subjectTextView);
            notification = itemView.findViewById(R.id.shortListNotification);
            classAttended = itemView.findViewById(R.id.classes_attended);
            classConducted = itemView.findViewById(R.id.classes_conducted);
        }
    }
}
