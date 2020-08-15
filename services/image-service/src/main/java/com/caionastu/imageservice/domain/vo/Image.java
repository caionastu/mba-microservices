package com.caionastu.imageservice.domain.vo;

import com.caionastu.imageservice.application.image.dto.ImageType;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Builder
@Document(collection = "images")
public class Image {

    private String id;
    private String userId;
    private String image;
    private ImageType imageType;

}
