package com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.dto.resposta;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RespostaEnderecoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String cep;

    private String rua;

    private String bairro;

    private String complemento;

    private String cidade;

    private String estado;

    
}
