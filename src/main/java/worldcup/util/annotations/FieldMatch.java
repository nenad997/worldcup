package worldcup.util.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import worldcup.util.validator.FieldMatchValidator;

import java.lang.annotation.Documented;

@Documented
@Constraint(validatedBy = FieldMatchValidator.class)
public @interface FieldMatch {
    String message() default "Fields do not match";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String first();
    String second();
}
