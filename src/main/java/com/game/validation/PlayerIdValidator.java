package com.game.validation;

import com.game.exception.ValidationException;
import org.springframework.stereotype.Component;

import static com.game.validation.Assertions.isTrue;
import static com.game.validation.Assertions.notNull;
import static com.game.validation.Common.validationException;

@Component
class PlayerIdValidator implements Validator<Long> {
    @Override
    public void validate(Long playerId) throws ValidationException {
        notNull(playerId, validationException("player id must be not null"));
        isTrue(playerId > 0, validationException("player id must be positive"));
    }

    @Override
    public Class<Long> getValidatedClass() {
        return Long.class;
    }
}
