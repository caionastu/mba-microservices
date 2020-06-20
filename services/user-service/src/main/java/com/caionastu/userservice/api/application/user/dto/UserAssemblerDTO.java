package com.caionastu.userservice.api.application.user.dto;

import com.caionastu.core.interfaces.IAssemblerDTO;
import com.caionastu.userservice.api.domain.user.vo.User;
import com.caionastu.userservice.api.domain.user.vo.UserType;

public class UserAssemblerDTO implements IAssemblerDTO<UserDTO, User> {

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
                .build();
    }
}
