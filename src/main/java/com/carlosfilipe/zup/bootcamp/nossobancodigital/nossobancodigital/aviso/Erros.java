package com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.aviso;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

public class Erros {
    
    @Getter
    List<String> erros;

    public Erros(String e){
        this.erros = Arrays.asList(e);
    }

    public Erros(List<String> listErrors) {
        this.erros = listErrors;
    }

    
}
