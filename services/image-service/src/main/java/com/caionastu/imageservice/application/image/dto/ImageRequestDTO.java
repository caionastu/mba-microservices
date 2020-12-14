package com.caionastu.imageservice.application.image.dto;

import com.caionastu.core.request.AbstractRequestDTO;
import lombok.*;

@Getter
@ToString
@AllArgsConstructor
public class ImageRequestDTO extends AbstractRequestDTO {

    private String userId;
    private ImageType imageType;
}
