package com.gyarsilalsolanki011.makeattendance.activities.staff.model;

public class AttendanceModel {
    public String fullName;
    public Integer present, absent, percentage;

    public AttendanceModel(Integer absent, String fullName,  Integer percentage, Integer present) {
        this.fullName = fullName;
        this.present = present;
        this.absent = absent;
        this.percentage = percentage;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getPresent() {
        return present;
    }

    public void setPresent(Integer present) {
        this.present = present;
    }

    public Integer getAbsent() {
        return absent;
    }

    public void setAbsent(Integer absent) {
        this.absent = absent;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }
}
