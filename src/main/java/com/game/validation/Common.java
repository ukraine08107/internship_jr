package com.game.validation;

import com.game.exception.ValidationException;

import java.util.function.Supplier;

public final class Common {
    private Common() {
        throw new UnsupportedOperationException("Constructor invoke not allowed");
    }

    public static Supplier<RuntimeException> validationException(String message) {
        return () -> new ValidationException(message);
    }
}
