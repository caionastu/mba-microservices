package com.caionastu.core.excpetions;


import com.caionastu.core.error.ErrorBlock;

public class RecordNotFoundException extends AbstractException {

    public RecordNotFoundException(ErrorBlock errorBlock) {
        super(errorBlock);
    }

    public RecordNotFoundException(String message) {
        super(message);
    }
}
