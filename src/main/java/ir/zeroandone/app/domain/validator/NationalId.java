package ir.zeroandone.app.domain.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NationalIdValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NationalId {


    String message() default "{NationalId}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}