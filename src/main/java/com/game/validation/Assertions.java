package com.game.validation;

import java.util.function.Supplier;

public final class Assertions {
    private Assertions() {
        throw new UnsupportedOperationException("Constructor invoke not allowed");
    }

    public static void notNull(Object o, Supplier<RuntimeException> throwableSupplier) {
        if (o == null) {
            throwException(throwableSupplier, "Object is null");
        }
    }

    public static void notEmpty(CharSequence sequence, Supplier<RuntimeException> throwableSupplier) {
        if (sequence == null || sequence.length() == 0) {
            throwException(throwableSupplier, "CharSequence is empty");
        }
    }

    public static void isTrue(boolean expression, Supplier<RuntimeException> throwableSupplier) {
        if (!expression) {
            throwException(throwableSupplier, "Expression is false");
        }
    }

    private static void throwException(Supplier<RuntimeException> throwableSupplier, String defaultMessage) {
        if (throwableSupplier == null) {
            throw new IllegalArgumentException(defaultMessage);
        } else {
            throw throwableSupplier.get();
        }
    }
}
