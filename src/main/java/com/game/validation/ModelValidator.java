package com.game.validation;

import com.game.exception.InitializationException;
import com.game.model.CreatePlayerRequest;
import com.game.model.UpdatePlayerRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ModelValidator {
    private final Map<Class<?>, Validator<?>> validatorMap;

    public ModelValidator(List<Validator<?>> validators) {
        if (validators == null || validators.isEmpty()) {
            throw new InitializationException("Validators must be not empty");
        }
        validatorMap = validators.stream()
                .collect(Collectors.toMap(Validator::getValidatedClass, v -> v));
    }

    public void validateCreatePlayerRequest(CreatePlayerRequest request) {
        getValidator(CreatePlayerRequest.class).validate(request);
    }

    public void validateUpdatePlayerRequest(UpdatePlayerRequest request) {
        getValidator(UpdatePlayerRequest.class).validate(request);
    }

    public void validatePlayerId(Long playerId) {
        getValidator(Long.class).validate(playerId);
    }

    @SuppressWarnings("unchecked")
    private <T> Validator<T> getValidator(Class<T> clazz) {
        return (Validator<T>) Optional.ofNullable(validatorMap.get(clazz))
                .orElseThrow(() -> new IllegalArgumentException("Valiadtor for class " + clazz.getName() + " not found"));
    }
}
