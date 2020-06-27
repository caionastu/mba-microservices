package com.caionastu.core.excpetions;


import com.caionastu.core.error.ErrorBlock;

public class BusinessException extends AbstractException {

    public BusinessException(ErrorBlock errorBlock) {
        super(errorBlock);
    }

    public BusinessException(String message) {
        super(message);
    }
}
