package com.caionastu.core.excpetions;


import com.caionastu.core.error.ErrorBlock;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BusinessException extends RuntimeException {

    @Getter
    private ErrorBlock errorBlock;

    public BusinessException(ErrorBlock errorBlock) {
        super(errorBlock.getHeader());
        this.errorBlock = errorBlock;
    }

    public BusinessException(String message) {
        super(message);
        this.errorBlock = ErrorBlock.builder()
                .header(message)
                .build();
    }
}
