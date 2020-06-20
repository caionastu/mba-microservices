package com.caionastu.userservice.api.application.user.controller;

import com.caionastu.userservice.api.application.user.appService.UserAppService;
import com.caionastu.userservice.api.application.user.dto.UserDTO;
import com.caionastu.userservice.api.domain.user.vo.User;
import com.netflix.loadbalancer.Server;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.server.EntityResponse;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "users")
public class UserController {

    private final UserAppService appService;

    public UserController(UserAppService appService) {
        this.appService = appService;
    }

    @GetMapping
    public Flux<UserDTO> findAll() {
        return Flux.just(new UserDTO());
    }

    @GetMapping(path = "/{id}")
    public void findById(@PathVariable String id) {

    }

    @GetMapping(path = "/role/{role}")
    public void findByRole(String role) {

    }

    @PostMapping
    public Mono<ResponseEntity<UserDTO>> create(@RequestBody UserDTO userDTO) {
        return appService.create(userDTO)
                .map(ResponseEntity::ok);
    }

    @PutMapping
    public void update() {

    }

    @DeleteMapping
    public void delete() {

    }
}
