package com.game.validation;

import com.game.exception.ValidationException;

interface Validator<T> {
    void validate(T t) throws ValidationException;

    Class<T> getValidatedClass();
}
