package com.gyarsilalsolanki011.makeattendance.repository.user;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.gyarsilalsolanki011.makeattendance.repository.user.model.User;

public interface UserRepository {
    Task<DocumentSnapshot> getUserData();
    void setUserData(User user);
}
