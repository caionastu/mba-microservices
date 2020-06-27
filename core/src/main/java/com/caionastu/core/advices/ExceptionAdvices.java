package com.caionastu.core.advices;

import com.caionastu.core.error.CommonErrorKeys;
import com.caionastu.core.error.ErrorBlock;
import com.caionastu.core.excpetions.BusinessException;
import com.caionastu.core.excpetions.RecordNotFoundException;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;

@RestControllerAdvice
@NoArgsConstructor
public class ExceptionAdvices {

    @ExceptionHandler(BusinessException.class)
    public Mono<ResponseEntity<ErrorBlock>> handleBusinessException(BusinessException exception) {
        HttpStatus code = exception.getErrorBlock().getCode();
        return Mono.just(ResponseEntity.status(code)
                .body(exception.getErrorBlock()));
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public Mono<ResponseEntity<ErrorBlock>> handleRecordNotFoundException(RecordNotFoundException exception) {
        HttpStatus code = exception.getErrorBlock().getCode();
        return Mono.just(ResponseEntity.status(code)
                .body(exception.getErrorBlock()));
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public Mono<ResponseEntity<ErrorBlock>> webExchangeBindExceptionHandler(WebExchangeBindException exception) {
        ErrorBlock errorBlock = new ErrorBlock();

        errorBlock.setCode(HttpStatus.BAD_REQUEST);
        errorBlock.setHeader(CommonErrorKeys.ENTITY_FAIL_REQUIREMENT);

        exception.getBindingResult()
                .getAllErrors()
                .forEach(error -> errorBlock.addErrorMessage(error.getDefaultMessage()));

        return Mono.just(ResponseEntity.badRequest().body(errorBlock));
    }
}
