package com.game.validation;

import com.game.exception.InitializationException;
import com.game.model.UpdatePlayerRequest;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

import static com.game.validation.Assertions.isTrue;
import static com.game.validation.Assertions.notEmpty;
import static com.game.validation.Common.validationException;

@Component
class UpdatePlayerRequestValidator implements Validator<UpdatePlayerRequest> {
    private final Long minDate;
    private final Long maxDate;

    public UpdatePlayerRequestValidator() {
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
    public void validate(UpdatePlayerRequest request) {
        if (request.getName() != null) {
            notEmpty(request.getName(), validationException("'name' must be not empty"));
            isTrue(request.getName().length() <= 12, validationException("'name' length must be lower or equals 12, actual: " + request.getName().length()));
        }
        if (request.getTitle() != null) {
            isTrue(request.getTitle().length() <= 30, validationException("'title' length must be lower of equals 30, actual: " + request.getTitle().length()));
        }
        if (request.getBirthday() != null) {
            isTrue(request.getBirthday() >= 0, validationException("'birthday' must be zero of positive, actual: " + request.getBirthday()));
            isTrue(request.getBirthday() >= minDate && request.getBirthday() <= maxDate,
                    validationException("'birthday' must be greater or equals " + minDate + " and lower or equals " + maxDate + ", actual: " + request.getBirthday()));
        }
        if (request.getExperience() != null) {
            isTrue(request.getExperience() >= 0 && request.getExperience() <= 10_000_000,
                    validationException("'experience' must be greater of equals zero and lower or equals 10_000_000, actual: " + request.getExperience()));
        }
    }

    @Override
    public Class<UpdatePlayerRequest> getValidatedClass() {
        return UpdatePlayerRequest.class;
    }
}
