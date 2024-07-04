package com.gyarsilalsolanki011.makeattendance.repository.user;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.gyarsilalsolanki011.makeattendance.repository.user.model.User;

public interface UserRepository {
    Task<DocumentSnapshot> getStudentData();
    void setStudentData(User user);
    Task<DocumentSnapshot> getFacultyData();
    void setFacultyData(User user);
    Task<DocumentSnapshot> getAttendanceData(String subject);
    void setAttendanceData(User user, String subject);
}
