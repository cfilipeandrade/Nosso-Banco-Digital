package com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.exception;

public class ViolacaoRegraNegocio extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ViolacaoRegraNegocio(String mensagem) {
        super(mensagem);
        
    }

}
