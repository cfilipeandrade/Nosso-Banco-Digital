package com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.service.implentacao.CpfServiceImplement;

import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Cliente {

    public void salvarArquivoCpfCliente(CpfServiceImplement cpfServiceImplement, Long id, String CpfFrente) {
	    FotoCpf cpf = cpfServiceImplement.gerarNovoCpf(CpfFrente);
	    setFotoCpf(cpfServiceImplement.fotoCpfRepository.save(cpf));
	    cpfServiceImplement.clienteRepository.save(this);
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(length = 30, nullable = false)
    private String primeiroNome;

    @NotEmpty
    @Column(length = 100, nullable = false)
    private String sobrenome;

    @CPF
    @Column(nullable = false)
    private String cpf;

    @Email
    @Column(nullable = false)
    private String email;


    @Past
    @NotNull
    private LocalDate dataNascimento;

    @OneToOne
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;

    @OneToOne
    @JoinColumn(name = "id_conta")
    private Conta conta;

    @OneToOne
    @JoinColumn(name = "id_foto_cpf")
    private FotoCpf fotoCpf;
   
}
