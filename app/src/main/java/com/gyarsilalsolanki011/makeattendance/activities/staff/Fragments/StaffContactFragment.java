package com.gyarsilalsolanki011.makeattendance.activities.staff.Fragments;

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
import android.widget.ImageView;
import android.widget.TextView;

import com.gyarsilalsolanki011.makeattendance.R;

public class StaffContactFragment extends Fragment {
    TextView whatsapp,feedback,send_your_message;
    ImageView linkedin, instagram, github, share;
    public StaffContactFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        whatsapp = v.findViewById(R.id.whatsapp);
        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uriText = "https://api.whatsapp.com/send/"+"?phone="+"7909519946"+"&text="+
                        "Please+help+me+regarding+the+issue+:+"+"&type=phone_number&app_absent=0";
                gotoUrl(uriText);
            }
            private void gotoUrl(String s) {
                Uri uri = Uri.parse(s);
                startActivity(new Intent(Intent.ACTION_VIEW,uri));
            }
        });


        feedback = v.findViewById(R.id.feedback);
        feedback.setOnClickListener(view -> {

            Intent intent = new Intent(Intent.ACTION_SENDTO);
            String uriText = "mailto:" + Uri.encode("gyarsilalsolanki011@gmail.com") + "?subject=" +
                    Uri.encode("Enter Your email") + "&body=" + Uri.encode("");

            Uri uri = Uri.parse(uriText);
            intent.setData(uri);
            startActivity(Intent.createChooser(intent, "Send Email"));

        });



        send_your_message = v.findViewById(R.id.send_your_message);
        send_your_message.setOnClickListener(v1 -> {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            String uriText = "mailto:" + Uri.encode("gyarsials105@gmail.com") + "?subject=" +
                    Uri.encode("Ask yor query") + "&body=" + Uri.encode("please make sure to write name and phone number too");

            Uri uri = Uri.parse(uriText);
            intent.setData(uri);
            startActivity(Intent.createChooser(intent, "Send Email"));
        });


        linkedin = v.findViewById(R.id.linkedin);
        linkedin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String uriText = "https://www.linkedin.com/in/gyarsilalsolanki";
                gotoUrl(uriText);
            }
            private void gotoUrl(String s) {
                Uri uri = Uri.parse(s);
                startActivity(new Intent(Intent.ACTION_VIEW,uri));
            }
        });

        github = v.findViewById(R.id.github);
        github.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String uriText = "https://github.com/gyarsilalsolanki011";
                gotoUrl(uriText);
            }
            private void gotoUrl(String s) {
                Uri uri = Uri.parse(s);
                startActivity(new Intent(Intent.ACTION_VIEW,uri));
            }
        });


        instagram = v.findViewById(R.id.instagram);
        instagram.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String uriText = "https://www.instagram.com/itz_gsl_tiger?igsh=czZoZmo4emZuYjIz";
                gotoUrl(uriText);
            }
            private void gotoUrl(String s) {
                Uri uri = Uri.parse(s);
                startActivity(new Intent(Intent.ACTION_VIEW,uri));
            }
        });



        share = v.findViewById(R.id.share);
        share.setOnClickListener(view -> {

            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            String shareBody = "look all Programmings";
            String subject = "https://github.com/gyarsilalsolanki011?tab=repositories";
            i.putExtra(Intent.EXTRA_SUBJECT,shareBody);
            i.putExtra(Intent.EXTRA_TEXT,subject);
            startActivity(Intent.createChooser(i,"Share via"));

        });
    }
}