package com.caionastu.userservice.api.domain.user.vo;

import lombok.Getter;

public enum UserType {
    CLIENT("Client"),
    PERSONAL_ORGANIZER("Personal Organizer"),
    ;

    @Getter
    private final String type;

    UserType(String type) {
        this.type = type;
    }
}
