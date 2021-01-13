package RusuTudor.demo.models.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = GameTypeValidator.class)
@Target( {ElementType.METHOD,  ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface GameTypeConstraint {
    String message() default "Tip de joc invalid. Tipul de joc este o abreviere";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
