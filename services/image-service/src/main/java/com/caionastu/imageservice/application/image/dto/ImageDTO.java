package com.caionastu.imageservice.application.image.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ImageDTO {

    private String id;

    @NotBlank(message = "User Id must not be blank.")
    private String userId;

    @NotBlank(message = "Image must not be blank.")
    private String image;

    @NotNull(message = "Image Type must not be null.")
    private ImageType imageType;

}
