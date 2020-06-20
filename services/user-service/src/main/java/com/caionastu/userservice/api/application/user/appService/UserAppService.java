package com.caionastu.userservice.api.application.user.appService;

import com.caionastu.userservice.api.application.user.dto.UserAssemblerDTO;
import com.caionastu.userservice.api.application.user.dto.UserDTO;
import com.caionastu.userservice.api.domain.user.service.UserService;
import com.caionastu.userservice.api.domain.user.vo.User;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class UserAppService {

    private final UserAssemblerDTO assembler;
    private final UserService service;

    public UserAppService(UserService service) {
        this.assembler = new UserAssemblerDTO();
        this.service = service;
    }

    public Mono<UserDTO> create(UserDTO userDTO) {
        User user = assembler.toEntity(userDTO);
        return service.create(user)
                .map(assembler::toDTO);

    }
}
