package com.caionastu.core.excpetions;


import com.caionastu.core.error.ErrorBlock;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RecordNotFoundException extends AbstractException {

    public RecordNotFoundException(ErrorBlock errorBlock) {
        super(errorBlock);
    }

    public RecordNotFoundException(String message) {
        super(message);
    }
}
