package com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.aviso;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.exception.RecursoNaoEncontrado;
import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.exception.ViolacaoRegraNegocio;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartException;

public class AvisoController {

    @ExceptionHandler(RecursoNaoEncontrado.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Erros recursoNaoEncontradoException(RecursoNaoEncontrado e) {
        return new Erros(e.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Erros handleConstraintViolationException(ConstraintViolationException e) {
        return new Erros(e.getMessage());
    }

    @ExceptionHandler(MultipartException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Erros tamanhoMaximoArquivoException(MultipartException e) {
        return new Erros(e.getMessage());

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Erros handleMethodNotValidExceptErros(MethodArgumentNotValidException e) {
        List<String> listErrors = e.getBindingResult()
                .getAllErrors()
                .stream()
                .map(objectError -> objectError.getDefaultMessage())
                .collect(Collectors.toList());
        return new Erros(listErrors);
    }


    @ExceptionHandler(ViolacaoRegraNegocio.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Erros violacaoRegraNegocio(ViolacaoRegraNegocio e) {
        return new Erros(e.getMessage());
    }
    
}
