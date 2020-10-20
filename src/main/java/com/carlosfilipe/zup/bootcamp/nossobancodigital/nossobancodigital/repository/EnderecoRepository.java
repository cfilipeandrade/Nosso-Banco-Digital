package com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.repository;

import java.util.Optional;

import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.model.Endereco;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    Optional<Endereco> findByCep(String cep);
    
}
