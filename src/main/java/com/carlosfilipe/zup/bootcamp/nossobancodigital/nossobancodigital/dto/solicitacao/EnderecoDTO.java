package com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.dto.solicitacao;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDTO implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "{campo.cep.obrigatorio}")
    private String cep;

    @NotEmpty(message = "{campo.rua.obrigatorio}")
    private String rua;

    @NotEmpty(message = "{campo.bairro.obrigatorio}")
    private String bairro;

    @NotEmpty(message = "{campo.complemento.obrigatorio}")
    private String complemento;

    @NotEmpty(message = "{campo.cidade.obrigatorio}")
    private String cidade;

    @NotEmpty(message = "{campo.estado.obrigatorio}")
    private String estado;

}
