package com.gyarsilalsolanki011.makeattendance.repository.user;

import android.util.Log;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.gyarsilalsolanki011.makeattendance.repository.user.model.User;

public class FirebaseUserRepository implements UserRepository{

    private final FirebaseFirestore database = FirebaseFirestore.getInstance();
    private final CollectionReference studentCollection = database.collection("Students");
    private final CollectionReference facultyCollection = database.collection("Faculties");
    private final static String TAG = "FIREBASE USER REPOSITORY";
    private CollectionReference attendanceCollection;

    @Override
    public Task<DocumentSnapshot> getStudentData() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        assert user != null;
        return studentCollection.document(user.getUid()).get();
    }

    @Override
    public void setStudentData(User userData) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            Task<Void> task = studentCollection.document(user.getUid()).set(userData.toMapStudent());
            task.addOnFailureListener(e-> Log.e(TAG, e.toString()));
            task.addOnSuccessListener(e-> Log.d(TAG, "Data set success"));
        }
    }
    @Override
    public Task<DocumentSnapshot> getFacultyData() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        assert user != null;
        return facultyCollection.document(user.getUid()).get();
    }

    @Override
    public void setFacultyData(User userData) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            Task<Void> task = facultyCollection.document(user.getUid()).set(userData.toMapFaculty());
            task.addOnFailureListener(e -> Log.e(TAG, e.toString()));
            task.addOnSuccessListener(e -> Log.d(TAG, "Data set success"));
        }
    }

    @Override
    public Task<DocumentSnapshot> getAttendanceData(String subject) {

        attendanceCollection = database.collection(subject);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        assert user != null;
        return attendanceCollection.document(user.getUid()).get();
    }

    @Override
    public void setAttendanceData(User userData, String subject) {

        attendanceCollection = database.collection(subject);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            Task<Void> task = attendanceCollection.document(user.getUid()).set(userData.toMapAttendance());
            task.addOnFailureListener(e -> Log.e(TAG, e.toString()));
            task.addOnSuccessListener(e -> Log.d(TAG, "Data set success"));
        }
    }
}
