package com.koval.shop.annotation;

import com.koval.shop.util.LogoValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Target(PARAMETER)
@Retention(RUNTIME)
@Constraint(validatedBy = {LogoValidator.class})
public @interface ValidLogo {

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
