package com.gyarsilalsolanki011.makeattendance.repository.user.model;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class User {
    String email, fullName, rollNumber, branch, semester, fatherName, motherName;
    String subject;
    Integer present, absent, percentage;

    public User(@NotNull String email, @NotNull String fullName, @NotNull String rollNumber, @NotNull String branch, @NotNull String semester, @NotNull String fatherName, @NotNull String motherName) {
        this.email = email;
        this.fullName = fullName;
        this.rollNumber = rollNumber;
        this.branch = branch;
        this.semester = semester;
        this.fatherName = fatherName;
        this.motherName = motherName;
    }

    public User(@NotNull String email, @NotNull String fullName, @NotNull String subject, @NotNull String branch, @NotNull String semester) {
        this.email = email;
        this.fullName = fullName;
        this.branch = branch;
        this.semester = semester;
        this.subject = subject;
    }

    public User(@NotNull String fullName, @NotNull Integer present, @NotNull Integer absent, @NotNull Integer percentage) {
        this.fullName = fullName;
        this.present = present;
        this.absent = absent;
        this.percentage = percentage;
    }

    public  static User Attendance(@NotNull String fullName, @NotNull Integer present, @NotNull Integer absent, @NotNull Integer percentage) {
        return  new User(fullName, present, absent, percentage);
    }

    public static User student(@NotNull String email, @NotNull String fullName, @NotNull String rollNumber, @NotNull String branch, @NotNull String semester, @NotNull String fatherName, @NotNull String motherName) {
        return new User(email, fullName, rollNumber, branch, semester, fatherName, motherName);
    }

    public static User faculty(@NotNull String email, @NotNull String fullName, @NotNull String subject, @NotNull String branch, @NotNull String semester) {
        return new User(email, fullName, subject, branch, semester);
    }

    public Map<String, Object> toMapStudent() {
        Map<String, Object> data = new HashMap<>();
        data.put("branch", this.branch);
        data.put("fullName", this.fullName);
        data.put("email", this.email);
        data.put("fatherName", this.fatherName);
        data.put("motherName", this.motherName);
        data.put("rollNumber", this.rollNumber);
        data.put("semester", this.semester);

        return data;
    }

    public User fromMapStudent(Map<String, Object> data) {
        return new User(
                (String) Objects.requireNonNull(data.get("email")),
                (String) Objects.requireNonNull(data.get("fullName")),
                (String) Objects.requireNonNull(data.get("rollNumber")),
                (String) Objects.requireNonNull(data.get("branch")),
                (String) Objects.requireNonNull(data.get("semester")),
                (String) Objects.requireNonNull(data.get("fatherName")),
                (String) Objects.requireNonNull(data.get("motherName"))

        );
    }
    public Map<String, Object> toMapFaculty() {
        Map<String, Object> data = new HashMap<>();
        data.put("fullName", this.fullName);
        data.put("email", this.email);
        data.put("branch", this.branch);
        data.put("semester", this.semester);
        data.put("subject", this.subject);

        return data;
    }

    public User fromMapFaculty(Map<String, Object> data) {
        return new User(
                (String) Objects.requireNonNull(data.get("email")),
                (String) Objects.requireNonNull(data.get("fullName")),
                (String) Objects.requireNonNull(data.get("branch")),
                (String) Objects.requireNonNull(data.get("semester")),
                (String) Objects.requireNonNull(data.get("subject"))
        );
    }
    public Map<String, Object> toMapAttendance() {
        Map<String, Object> data = new HashMap<>();
        data.put("fullName", this.fullName);
        data.put("present", this.present);
        data.put("absent", this.absent);
        data.put("percentage", this.percentage);
        return data;
    }

    public void fromMapAttendance(Map<String, Object> data) {
        new User(
                (String) Objects.requireNonNull(data.get("fullName")),
                (Integer) Objects.requireNonNull(data.get("present")),
                (Integer) Objects.requireNonNull(data.get("absent")),
                (Integer) Objects.requireNonNull(data.get("percentage"))
        );
    }

    public Integer getPresent() {
        return present;
    }

    public Integer getAbsent() {
        return absent;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public String getBranch() {
        return branch;
    }

    public String getSemester() {
        return semester;
    }

    public String getFatherName() {
        return fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public String getSubject() {
        return subject;
    }
}
