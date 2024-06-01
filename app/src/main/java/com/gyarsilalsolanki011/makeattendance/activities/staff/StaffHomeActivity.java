package com.gyarsilalsolanki011.makeattendance.activities.staff;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.gyarsilalsolanki011.makeattendance.databinding.ActivityStaffHomeBinding;

public class StaffHomeActivity extends AppCompatActivity {
    ActivityStaffHomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityStaffHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



    }
}