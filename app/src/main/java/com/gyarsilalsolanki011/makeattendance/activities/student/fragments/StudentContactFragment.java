package com.gyarsilalsolanki011.makeattendance.activities.student.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gyarsilalsolanki011.makeattendance.R;

public class StudentContactFragment extends Fragment {
    Button btnEmail, btnMsg, btnShare;
    public StudentContactFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnEmail = view.findViewById(R.id.btnEmail);
        btnMsg = view.findViewById(R.id.btnMsg);
        btnShare = view.findViewById(R.id.btnShare);

        btnMsg.setOnClickListener(v -> {
            Intent iMsg = new Intent(Intent.ACTION_SENDTO);
            iMsg.setData(Uri.parse("smsto:"+Uri.encode("+917620824421")));
            iMsg.putExtra("sms_body", "Please solve this asap.");
            startActivity(iMsg);
        });

        btnEmail.setOnClickListener(v -> {
            Intent iEmail = new Intent(Intent.ACTION_SEND);
            iEmail.setType("message/rfc922");
            iEmail.putExtra(Intent.EXTRA_EMAIL, new String[]{"gyarsilals105@gmail.com"});
            iEmail.putExtra(Intent.EXTRA_SUBJECT, "Regarding _ Issue");
            iEmail.putExtra(Intent.EXTRA_TEXT,"Please resolve this issue asap.");
            startActivity(Intent.createChooser(iEmail, "Email via"));
        });

        btnShare.setOnClickListener(v -> {
            Intent iShare =  new Intent(Intent.ACTION_SEND);
            iShare.setType("text/plain");
            iShare.putExtra(Intent.EXTRA_TEXT, "This app is available on Github repo, please visit and check it out, https://github.com/gyarsilalsolanki011/Make_Attendance.git");
            startActivity(Intent.createChooser(iShare,"Share via"));
        });
    }
}