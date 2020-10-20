package com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.validacao.validadores;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.repository.ClienteRepository;
import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.validacao.anotacoes.UnicoCpf;

import org.springframework.beans.factory.annotation.Autowired;

public class UnicoCpfValidator implements ConstraintValidator<UnicoCpf, String> {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public boolean isValid(String cpf, ConstraintValidatorContext constraintValidatorContext) {
        return !clienteRepository.findByCpf(cpf).isPresent();
    }

    public ClienteRepository getClienteRepository() {
        return clienteRepository;
    }

    public void setClienteRepository(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public UnicoCpfValidator(ClienteRepository clienteRepository) {
        this.setClienteRepository(clienteRepository);
    }



    
}
