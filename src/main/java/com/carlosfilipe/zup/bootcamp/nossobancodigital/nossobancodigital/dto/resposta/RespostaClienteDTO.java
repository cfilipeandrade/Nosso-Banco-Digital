package com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.dto.resposta;

import java.io.Serializable;
import java.time.LocalDate;

import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.model.Conta;
import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.model.FotoCpf;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RespostaClienteDTO implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long id;

    private String nome;

    private String sobrenome;

    private String cpf;

    private String email;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataDeNascimento;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private RespostaEnderecoDTO endereco;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private FotoCpf fotoCpf;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Conta conta;
    
}
