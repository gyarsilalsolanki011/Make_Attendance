package com.gyarsilalsolanki011.makeattendance.activities.staff;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.gyarsilalsolanki011.makeattendance.R;
import com.gyarsilalsolanki011.makeattendance.activities.student.fragments.ContactFragment;
import com.gyarsilalsolanki011.makeattendance.activities.student.fragments.HomeFragment;
import com.gyarsilalsolanki011.makeattendance.activities.student.fragments.ProfileFragment;
import com.gyarsilalsolanki011.makeattendance.activities.student.fragments.UtilitiesFragment;
import com.gyarsilalsolanki011.makeattendance.databinding.ActivityStaffHomeBinding;

public class StaffHomeActivity extends AppCompatActivity {
    ActivityStaffHomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityStaffHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.bnView.setOnNavigationItemSelectedListener(menuItem -> {

            int id = menuItem.getItemId();

            if (id==R.id.nav_Utilities1){
                loadFrag(new UtilitiesFragment(),1);
            }else if (id==R.id.nav_Contact1){
                loadFrag(new ContactFragment(), 1);
            }else if (id==R.id.nav_profile1){
                loadFrag(new ProfileFragment(), 1);
            }else {
                loadFrag(new HomeFragment(), 0);
            }
            return true;
        });
        binding.bnView.setSelectedItemId(R.id.nav_home1);
    }
    public  void loadFrag(Fragment fragment, int flag){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (flag == 0)
            ft.add(R.id.container_faculty, fragment);
        else
            ft.replace(R.id.container_faculty, fragment);
        ft.commit();
    }
}