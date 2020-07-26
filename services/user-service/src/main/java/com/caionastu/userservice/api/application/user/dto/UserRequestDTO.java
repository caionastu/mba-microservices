package com.caionastu.userservice.api.application.user.dto;

import com.caionastu.core.request.AbstractRequestDTO;
import com.caionastu.userservice.api.application.coordinate.dto.LocationRequestDTO;
import com.mongodb.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserRequestDTO extends AbstractRequestDTO {

    private String name;
    private String userType;
    private LocationRequestDTO location;
}
