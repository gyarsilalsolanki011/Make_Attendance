package com.gyarsilalsolanki011.makeattendance.activities.staff.model;

public class AttendanceModel {
    public String fullName;
    public Integer present, absent, percentage;

    public AttendanceModel(String fullName, Integer present, Integer absent, Integer percentage) {
        this.fullName = fullName;
        this.present = present;
        this.absent = absent;
        this.percentage = percentage;
    }
}
