package com.caionastu.userservice.api.application.user.dto;

import com.caionastu.core.request.AbstractRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO extends AbstractRequestDTO {

    private String name;
    private String userType;
}
