package com.game.validation;

import com.game.exception.InitializationException;
import com.game.model.CreatePlayerRequest;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

import static com.game.validation.Assertions.isTrue;
import static com.game.validation.Assertions.notEmpty;
import static com.game.validation.Assertions.notNull;
import static com.game.validation.Common.validationException;

@Component
class CreatePlayerRequestValidator implements Validator<CreatePlayerRequest> {
    private final Long minDate;
    private final Long maxDate;

    public CreatePlayerRequestValidator() {
        try {
            String dateFormat = "dd/MM/yyyy";
            SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
            minDate = sdf.parse("01/01/2000").getTime();
            maxDate = sdf.parse("31/12/3000").getTime();
        } catch (Exception e) {
            throw new InitializationException(e);
        }
    }

    @Override
    public void validate(CreatePlayerRequest request) {
        notNull(request, validationException("request is null"));
        notEmpty(request.getName(), validationException("'name' must be not empty"));
        isTrue(request.getName().length() <= 12, validationException("'name' length must be lower or equals 12, actual: " + request.getName().length()));
        notNull(request.getTitle(), validationException("'title' must be not null"));
        isTrue(request.getTitle().length() <= 30, validationException("'title' length must be lower of equals 30, actual: " + request.getTitle().length()));
        notNull(request.getRace(), validationException("'race' must be not null"));
        notNull(request.getProfession(), validationException("'profession' must be not null"));
        notNull(request.getBirthday(), validationException("'birthday' must be not null"));
        isTrue(request.getBirthday() >= 0, validationException("'birthday' must be zero of positive, actual: " + request.getBirthday()));
        isTrue(request.getBirthday() >= minDate && request.getBirthday() <= maxDate,
                validationException("'birthday' must be greater or equals " + minDate + " and lower or equals " + maxDate + ", actual: " + request.getBirthday()));
        notNull(request.getExperience(), validationException("'experience' must be not null"));
        isTrue(request.getExperience() >= 0 && request.getExperience() <= 10_000_000,
                validationException("'experience' must be greater of equals zero and lower or equals 10_000_000, actual: " + request.getExperience()));
    }

    @Override
    public Class<CreatePlayerRequest> getValidatedClass() {
        return CreatePlayerRequest.class;
    }
}
