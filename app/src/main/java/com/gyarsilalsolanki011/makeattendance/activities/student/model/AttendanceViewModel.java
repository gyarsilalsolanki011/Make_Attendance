package com.gyarsilalsolanki011.makeattendance.activities.student.model;

public class AttendanceViewModel {
    public String subject, notification;
    public Integer classesAttended, classesConducted;
    public AttendanceViewModel(String subject, String notification, Integer classesAttended, Integer classesConducted) {
        this.subject = subject;
        this.notification = notification;
        this.classesAttended = classesAttended;
        this.classesConducted = classesConducted;
    }
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public Integer getClassesAttended() {
        return classesAttended;
    }

    public void setClassesAttended(Integer classesAttended) {
        this.classesAttended = classesAttended;
    }

    public Integer getClassesConducted() {
        return classesConducted;
    }

    public void setClassesConducted(Integer classesConducted) {
        this.classesConducted = classesConducted;
    }
}
