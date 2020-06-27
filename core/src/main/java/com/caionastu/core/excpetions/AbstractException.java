package com.caionastu.core.excpetions;


import com.caionastu.core.error.ErrorBlock;
import lombok.Getter;

public abstract class AbstractException extends RuntimeException {

    @Getter
    private final ErrorBlock errorBlock;

    public AbstractException(ErrorBlock errorBlock) {
        super(errorBlock.getHeader());
        this.errorBlock = errorBlock;
    }

    public AbstractException(String message) {
        super(message);
        this.errorBlock = ErrorBlock.builder()
                .header(message)
                .build();
    }
}
