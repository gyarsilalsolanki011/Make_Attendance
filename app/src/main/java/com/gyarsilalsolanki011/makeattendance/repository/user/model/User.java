package com.gyarsilalsolanki011.makeattendance.repository.user.model;

import com.gyarsilalsolanki011.makeattendance.repository.user.UserType;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class User {
    String email;
    String fullName;
    UserType type;

    public User(@NotNull String email, @NotNull String fullName, @NotNull UserType type) {
        this.email = email;
        this.fullName = fullName;
        this.type = type;
    }

    public static User student(@NotNull String email, @NotNull String fullName) {
        return new User(email, fullName, UserType.Student);
    }

    public static User faculty(@NotNull String email, @NotNull String fullName) {
        return new User(email, fullName, UserType.Faculty);
    }

    public Map<String, Object> toMap() {
        Map<String, Object> data = new HashMap<>();
        data.put("fullName", this.fullName);
        data.put("email", this.email);
        data.put("type", this.type.name());
        return data;
    }

    public User fromMap(Map<String, Object> data) {
        return new User(
                (String) Objects.requireNonNull(data.get("email")),
                (String) Objects.requireNonNull(data.get("fullName")),
                UserType.valueOf((String) data.get("type"))
        );
    }
}
