package com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.dto.solicitacao;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.model.Conta;
import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.validacao.anotacoes.Mais18;
import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.validacao.anotacoes.UnicoCpf;
import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.validacao.anotacoes.UnicoEmail;
import com.fasterxml.jackson.annotation.JsonFormat;

import org.hibernate.validator.constraints.br.CPF;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO implements Serializable {

	private static final long serialVersionUID = 1L;

    @NotEmpty(message = " {campo.nome.obrigatorio}")
    private String nome;

    @NotEmpty(message = "{campo.sobrenome.obrigatorio}")
    private String sobrenome;

    @CPF(message = "{campo.cpf.invalido}")
    @UnicoCpf(message = "{campo.cpf.repetido}")
    private String cpf;

    @Email(message = "{campo.email.invalido}")
    @UnicoEmail(message = "{campo.email.repetido}")
    private String email;

    @NotNull(message = "{campo.data-nascimento.obrigatorio}")
    @Mais18(message = "{campo.data-nascimento.maioridade}")
    @Past(message = "{campo.data-nascimento.invalida}")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataDeNascimento;

    private Conta conta;
    
}
