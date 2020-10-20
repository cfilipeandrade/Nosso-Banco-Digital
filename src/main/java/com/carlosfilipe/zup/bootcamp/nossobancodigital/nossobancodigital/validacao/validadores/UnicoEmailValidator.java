package com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.validacao.validadores;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.repository.ClienteRepository;
import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.validacao.anotacoes.UnicoEmail;

import org.springframework.beans.factory.annotation.Autowired;

public class UnicoEmailValidator implements ConstraintValidator<UnicoEmail, String> {

    @Autowired
    private ClienteRepository clienteRepository;
    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return !clienteRepository.findByEmail(email).isPresent();
    }
    
}
