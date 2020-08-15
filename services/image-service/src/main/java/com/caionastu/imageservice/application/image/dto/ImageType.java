package com.caionastu.imageservice.application.image.dto;

import lombok.Getter;

public enum ImageType {
    PROFILE("PROFILE"),
    PROJECT("PROJECT"),
    PORTFOLIO("PORTFOLIO")
    ;

    @Getter
    private String type;

    ImageType(String type) {
        this.type = type;
    }
}
