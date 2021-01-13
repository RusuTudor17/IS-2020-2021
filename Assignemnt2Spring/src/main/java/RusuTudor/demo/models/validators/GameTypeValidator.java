package RusuTudor.demo.models.validators;

import RusuTudor.demo.models.GameType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GameTypeValidator implements ConstraintValidator<GameTypeConstraint, String > {

    @Override
    public void initialize(GameTypeConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.equals(value.toUpperCase());
    }
}
