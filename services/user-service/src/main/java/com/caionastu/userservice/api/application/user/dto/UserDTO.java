package com.caionastu.userservice.api.application.user.dto;

import com.caionastu.userservice.api.domain.user.vo.UserType;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class UserDTO {

    private String id;
    private String name;
    private String lastName;
    private String age;
    private String email;
    private String password;
    private String address;
    private UserType userType;

}
