package com.caionastu.userservice.api.application.user.dto;

import com.caionastu.core.request.AbstractRequestDTO;
import com.caionastu.userservice.api.application.coordinate.dto.LocationRequestDTO;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class UserRequestDTO extends AbstractRequestDTO {

    private String name;
    private String userType;
    private LocationRequestDTO location;
}
