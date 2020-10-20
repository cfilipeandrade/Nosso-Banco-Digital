package com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.service.interfaces;

import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.dto.resposta.RespostaEnderecoDTO;
import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.dto.solicitacao.EnderecoDTO;
import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.model.Endereco;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EnderecoService {

    Endereco salvarNovoEndereco(Long id, EnderecoDTO enderecoDTO);

    Page<RespostaEnderecoDTO> buscarTodosClientes(org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable pageable);

    Endereco buscarEnderecoId(Long id);

    Endereco buscarEnderecoCep(String cep);

    Endereco seExistirAtualizarEndereco(Long id, EnderecoDTO enderecoDTO);

    Endereco seExistirRetornarEnderecoComId(Long id);
    
}
