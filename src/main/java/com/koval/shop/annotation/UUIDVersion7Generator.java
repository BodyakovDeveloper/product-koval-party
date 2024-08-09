package com.koval.shop.annotation;

import com.koval.shop.service.core.hibernate.UUIDVersion7GeneratorService;
import org.hibernate.annotations.IdGeneratorType;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@IdGeneratorType(UUIDVersion7GeneratorService.class)
@Retention(RUNTIME)
@Target({METHOD, FIELD})
public @interface UUIDVersion7Generator {

    String name();

}
