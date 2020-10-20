package com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.repository;

import java.util.Optional;

import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.model.FotoCpf;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FotoCpfRepository extends JpaRepository<FotoCpf, Long> {

    Optional<FotoCpf> findById(Long id);
    Optional<FotoCpf> findByFrenteCpf(String frenteCpf);

    
}
