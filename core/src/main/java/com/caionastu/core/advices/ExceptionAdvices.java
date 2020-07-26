package com.caionastu.core.advices;

import com.caionastu.core.error.ErrorBlock;
import com.caionastu.core.excpetions.BusinessException;
import com.caionastu.core.excpetions.RecordNotFoundException;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@RestControllerAdvice
@NoArgsConstructor
public class ExceptionAdvices {

    protected AdviceHandler adviceHandler;

    public ExceptionAdvices(AdviceHandler adviceHandler) {
        this.adviceHandler = adviceHandler;
    }

    @ExceptionHandler(BusinessException.class)
    public Mono<ResponseEntity<ErrorBlock>> handleBusinessException(BusinessException exception) {
        return adviceHandler.handleAbstractException(exception);
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public Mono<ResponseEntity<ErrorBlock>> handleRecordNotFoundException(RecordNotFoundException exception) {
        return adviceHandler.handleAbstractException(exception);
    }
}
