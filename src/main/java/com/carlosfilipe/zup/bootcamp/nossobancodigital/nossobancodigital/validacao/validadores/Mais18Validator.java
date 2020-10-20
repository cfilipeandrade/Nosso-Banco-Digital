package com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.validacao.validadores;

import java.time.LocalDate;
import java.time.Period;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.validacao.anotacoes.Mais18;

public class Mais18Validator implements ConstraintValidator<Mais18, LocalDate> {

    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        LocalDate hoje = LocalDate.now();
        return Period.between(localDate, hoje).toTotalMonths() >= 216;
    }

    
}
