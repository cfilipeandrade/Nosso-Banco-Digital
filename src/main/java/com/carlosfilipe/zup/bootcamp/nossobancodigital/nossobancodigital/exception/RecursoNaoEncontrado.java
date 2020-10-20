package com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.exception;

import java.util.List;

import lombok.Getter;

public class RecursoNaoEncontrado extends RuntimeException {
    private static final long serialVersionUID = 1L;

    @Getter
    List<String> erros;

    public RecursoNaoEncontrado(List<String> listErros){
        this.erros = listErros;
    }

    public RecursoNaoEncontrado(String mensagem){
        super(mensagem);
    }
}
