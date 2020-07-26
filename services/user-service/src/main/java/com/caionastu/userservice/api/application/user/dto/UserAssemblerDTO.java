package com.caionastu.userservice.api.application.user.dto;

import com.caionastu.core.interfaces.IAssemblerDTO;
import com.caionastu.userservice.api.application.address.dto.AddressAssemblerDTO;
import com.caionastu.userservice.api.domain.user.vo.User;
import com.caionastu.userservice.api.domain.user.vo.UserType;
import org.springframework.stereotype.Component;

@Component
public class UserAssemblerDTO implements IAssemblerDTO<UserDTO, User> {

    private final AddressAssemblerDTO addressAssemblerDTO;

    public UserAssemblerDTO(AddressAssemblerDTO addressAssemblerDTO) {
        this.addressAssemblerDTO = addressAssemblerDTO;
    }

    @Override
    public UserDTO toDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .lastName(user.getLastName())
                .age(user.getAge())
                .email(user.getEmail())
                .username(user.getUsername())
                .userType(user.getUserType().getType())
                .address(addressAssemblerDTO.toDTO(user.getAddress()))
                .build();
    }

    @Override
    public User toEntity(UserDTO userDTO) {
        return User.builder()
                .id(userDTO.getId())
                .name(userDTO.getName())
                .lastName(userDTO.getLastName())
                .age(userDTO.getAge())
                .email(userDTO.getEmail())
                .username(userDTO.getUsername())
                .userType(UserType.of(userDTO.getUserType()))
                .password(userDTO.getPassword())
                .address(addressAssemblerDTO.toEntity(userDTO.getAddress()))
                .build();
    }
}
