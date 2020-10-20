package com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.validacao.anotacoes;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.validacao.validadores.UnicoEmailValidator;

@Documented
@Constraint(validatedBy = UnicoEmailValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UnicoEmail {
    String message() default "{com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.validacao.anotacoes.UnicoEmail}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
    
}
