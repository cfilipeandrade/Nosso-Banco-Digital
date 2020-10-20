package com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.service.interfaces;

import java.nio.file.Path;

import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.model.Cliente;

import org.springframework.web.multipart.MultipartFile;

public interface CpfService {

    void salvarArquivoCpfCliente(String diretorio, Long id, MultipartFile arquivoFrente);

    void salvarCpfCliente(Cliente cliente, String docFrente);

    String buscarLocalizacaoCpf(Path diretorioPath, String nomeDoArquivo);
 
}
