package com.caionastu.core.advices;

import com.caionastu.core.error.ErrorBlock;
import com.caionastu.core.excpetions.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@RestControllerAdvice
@Slf4j
public class BusinessExceptionAdvice {

    @ExceptionHandler(BusinessException.class)
    public Mono<ResponseEntity<ErrorBlock>> handleBusinessException(BusinessException exception) {
        HttpStatus code = exception.getErrorBlock().getCode();
        return Mono.just(ResponseEntity.status(code)
                .body(exception.getErrorBlock()));
    }
}
