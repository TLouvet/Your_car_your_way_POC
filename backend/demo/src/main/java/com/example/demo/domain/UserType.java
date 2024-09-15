package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum UserType {
    CUSTOMER("Customer"),
    EMPLOYEE("Employee");

    private final String displayName;

    UserType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @JsonCreator
    public static UserType fromString(String value) {
        for (UserType userType : UserType.values()) {
            if (userType.displayName.equalsIgnoreCase(value)) {
                return userType;
            }
        }
        throw new IllegalArgumentException("Unknown UserType: " + value);
    }
}