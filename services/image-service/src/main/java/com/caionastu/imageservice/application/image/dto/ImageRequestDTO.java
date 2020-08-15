package com.caionastu.imageservice.application.image.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ImageRequestDTO {

    private String id;

    @NotBlank(message = "User Id must not be blank.")
    private String userId;

    private ImageType imageType;
}
