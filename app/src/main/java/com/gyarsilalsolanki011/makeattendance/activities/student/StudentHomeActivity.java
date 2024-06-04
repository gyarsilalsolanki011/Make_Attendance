package com.gyarsilalsolanki011.makeattendance.activities.student;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.gyarsilalsolanki011.makeattendance.R;
import com.gyarsilalsolanki011.makeattendance.activities.student.fragments.StudentContactFragment;
import com.gyarsilalsolanki011.makeattendance.activities.student.fragments.StudentHomeFragment;
import com.gyarsilalsolanki011.makeattendance.activities.student.fragments.StudentProfileFragment;
import com.gyarsilalsolanki011.makeattendance.activities.student.fragments.StudentUtilitiesFragment;
import com.gyarsilalsolanki011.makeattendance.databinding.ActivityStudentHomeBinding;

public class StudentHomeActivity extends AppCompatActivity {

    ActivityStudentHomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityStudentHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.bnView.setOnNavigationItemSelectedListener(menuItem -> {

            int id = menuItem.getItemId();

            if (id==R.id.nav_Utilities){
                loadFrag(new StudentUtilitiesFragment(),1);
            }else if (id==R.id.nav_Contact){
                loadFrag(new StudentContactFragment(), 1);
            }else if (id==R.id.nav_profile){
                loadFrag(new StudentProfileFragment(), 1);
            }else {
                loadFrag(new StudentHomeFragment(), 0);
            }
            return true;
        });
        binding.bnView.setSelectedItemId(R.id.nav_home);
    }

    public  void loadFrag(Fragment fragment, int flag){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (flag == 0)
            ft.add(R.id.container_Student, fragment);
        else
            ft.replace(R.id.container_Student, fragment);
        ft.commit();
    }
}