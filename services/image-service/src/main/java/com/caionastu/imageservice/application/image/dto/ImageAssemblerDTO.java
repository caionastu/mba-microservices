package com.caionastu.imageservice.application.image.dto;

import com.caionastu.core.interfaces.IAssemblerDTO;
import com.caionastu.imageservice.domain.vo.Image;
import org.springframework.stereotype.Component;

@Component
public class ImageAssemblerDTO implements IAssemblerDTO<ImageDTO, Image> {

    @Override
    public ImageDTO toDTO(Image image) {
        return ImageDTO.builder()
                .id(image.getId())
                .userId(image.getUserId())
                .image(image.getImage())
                .imageType(image.getImageType())
                .build();
    }

    @Override
    public Image toEntity(ImageDTO imageDTO) {
        return Image.builder()
                .id(imageDTO.getId())
                .userId(imageDTO.getUserId())
                .image(imageDTO.getImage())
                .imageType(imageDTO.getImageType())
                .build();
    }
}
