package com.caionastu.userservice.api.domain.user.vo;

import lombok.Getter;

public enum UserType {
    CLIENT("CLIENT"),
    PERSONAL_ORGANIZER("PERSONAL ORGANIZER"),
    ;

    @Getter
    private final String type;

    UserType(String type) {
        this.type = type;
    }

    public static UserType of(String type) {
        for (UserType value : values()) {
            if (value.getType().equals(type)) {
                return value;
            }
        }

        return CLIENT;
    }
}
