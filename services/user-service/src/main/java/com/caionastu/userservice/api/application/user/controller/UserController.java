package com.caionastu.userservice.api.application.user.controller;

import com.caionastu.userservice.api.application.user.appService.UserAppService;
import com.caionastu.userservice.api.application.user.dto.UserDTO;
import com.caionastu.userservice.api.application.user.dto.UserRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "users")
@Slf4j
public class UserController {

    private final UserAppService appService;

    public UserController(UserAppService appService) {
        this.appService = appService;
    }

    @GetMapping
    public Flux<UserDTO> findByFilter(@RequestBody UserRequestDTO requestDTO) {
        log.info("Find Users by Filter. RequestDTO: {}", requestDTO);
        return appService.findByFilter(requestDTO);
    }

    @GetMapping(path = "/{id}")
    public Mono<UserDTO> findById(@PathVariable String id) {
        log.info("Find Users by ID: {} ", id);
        return appService.findById(id);
    }

    @PostMapping
    public Mono<ResponseEntity<UserDTO>> create(@RequestBody @Valid UserDTO userDTO) {
        log.info("Create User. UserDto: {}", userDTO);
        return appService.create(userDTO)
                .flatMap(userCreated -> Mono.just(ResponseEntity.status(HttpStatus.CREATED).body(userCreated)));
    }

    @PutMapping(path = "/{id}")
    public Mono<ResponseEntity<UserDTO>> update(@PathVariable String id, @RequestBody @Valid UserDTO userDTO) {
        log.info("Update User. UserDto: {}", userDTO);
        return appService.update(id, userDTO)
                .flatMap(userUpdated -> Mono.just(ResponseEntity.ok().body(userUpdated)));
    }

    @DeleteMapping(path = "/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {
        log.info("Delete User. Id: {}", id);
        return appService.delete(id)
                .flatMap(aVoid -> Mono.just(ResponseEntity.ok().body(aVoid)));
    }
}
