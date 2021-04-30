package com.alex.watchshop.exception;

import static java.lang.String.format;

public class NotFoundException extends RuntimeException {

    public NotFoundException(Class<?> type, Long id) {
        super(format("%s [id:%d] entity is not found", type.getName(), id));
    }
}
