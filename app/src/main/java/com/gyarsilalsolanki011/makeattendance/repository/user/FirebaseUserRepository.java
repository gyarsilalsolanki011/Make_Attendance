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
    private final CollectionReference userCollection = database.collection("users");
    private final static String TAG = "FIREBASE USER REPOSITORY";

    @Override
    public Task<DocumentSnapshot> getUserData() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        assert user != null;
        return userCollection.document(user.getUid()).get();
    }

    @Override
    public void setUserData(User userData) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            Task<Void> task = userCollection.document(user.getUid()).set(userData.toMap());
            task.addOnFailureListener(e-> Log.e(TAG, e.toString()));
            task.addOnSuccessListener(e-> Log.d(TAG, "Data set success"));
        }
    }
}
