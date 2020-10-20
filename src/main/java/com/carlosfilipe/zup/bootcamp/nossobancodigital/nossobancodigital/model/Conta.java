package com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class Conta {
    
    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 4)
    private String agencia;

    @Column(length = 8)
    private String conta;

    @OneToOne
    private Cliente cliente;
    
}
