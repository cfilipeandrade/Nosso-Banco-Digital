package com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.repository;

import java.util.Optional;

import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.model.Cliente;
import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.model.Endereco;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

    Page<Cliente> findAllByPrimeiroNome(String name, Pageable pageable);

    Optional<Cliente> findByEnderecoId(Long id);

    Optional<Cliente> findByCpf(String cpf);

    Optional<Cliente> findByEmail(String email);

    Optional<Cliente> findByEndereco(Endereco endereco);
    
}
