package com.gyarsilalsolanki011.makeattendance.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.gyarsilalsolanki011.makeattendance.activities.staff.StaffHome;
import com.gyarsilalsolanki011.makeattendance.activities.student.StudentHome;
import com.gyarsilalsolanki011.makeattendance.databinding.ActivitySplashBinding;
import com.gyarsilalsolanki011.makeattendance.repository.auth.FirebaseAuthRepository;
import com.gyarsilalsolanki011.makeattendance.repository.user.FirebaseUserRepository;
import com.gyarsilalsolanki011.makeattendance.repository.user.UserType;

import java.util.Map;
import java.util.Objects;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    private final FirebaseAuthRepository auth = new FirebaseAuthRepository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        ActivitySplashBinding binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if(checkInternetConnection()){
            checkUser();
        }else {
            Snackbar.make(binding.getRoot(), "No internet", Snackbar.LENGTH_SHORT).show();
        }
    }
    private void checkUser(){
        if(auth.getCurrentUser() != null){
            new FirebaseUserRepository().getUserData().addOnSuccessListener(
                    doc->{
                        Map<String, Object> user = doc.getData();
                        assert user != null;
                        Intent intent;
                        if(UserType.valueOf(Objects.requireNonNull(user.get("type")).toString())==UserType.Student){
                            intent = new Intent(SplashActivity.this, StudentHome.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }else if(UserType.valueOf(Objects.requireNonNull(user.get("type")).toString())==UserType.Faculty){
                            intent = new Intent(SplashActivity.this, StaffHome.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }

                    }
            );
        }
    }

    private boolean checkInternetConnection() {
        ConnectivityManager conMgr = (ConnectivityManager) getSystemService (Context.CONNECTIVITY_SERVICE);
        return (conMgr.getActiveNetworkInfo() != null
                && conMgr.getActiveNetworkInfo().isAvailable()
                && conMgr.getActiveNetworkInfo().isConnected());
    }
}