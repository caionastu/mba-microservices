package com.caionastu.core.advices;

import com.caionastu.core.error.ErrorBlock;
import com.caionastu.core.excpetions.AbstractException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;

@Component
public class AdviceHandler {

    public Mono<ResponseEntity<ErrorBlock>> handleAbstractException(AbstractException exception) {
        HttpStatus code = exception.getErrorBlock().getCode();
        return Mono.just(ResponseEntity.status(code)
                .body(exception.getErrorBlock()));
    }

    public Mono<ResponseEntity<ErrorBlock>> handleWebExchangeBindException(WebExchangeBindException exception, String entityFailRequirementMessage) {
        ErrorBlock errorBlock = new ErrorBlock();

        errorBlock.setCode(HttpStatus.BAD_REQUEST);
        errorBlock.setHeader(entityFailRequirementMessage);

        exception.getBindingResult()
                .getAllErrors()
                .forEach(error -> errorBlock.addErrorMessage(error.getDefaultMessage()));

        return Mono.just(ResponseEntity.badRequest().body(errorBlock));
    }
}
