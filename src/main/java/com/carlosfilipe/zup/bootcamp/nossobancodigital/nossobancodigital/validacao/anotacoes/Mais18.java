package com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.validacao.anotacoes;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.validacao.validadores.Mais18Validator;

@Documented
@Constraint(validatedBy = Mais18Validator.class)
@Target({ ElementType.FIELD })
@Retention (RetentionPolicy.RUNTIME)
public @interface Mais18 {
    String message() default "{com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.validacao.anotacoes.Mais18}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    
}
