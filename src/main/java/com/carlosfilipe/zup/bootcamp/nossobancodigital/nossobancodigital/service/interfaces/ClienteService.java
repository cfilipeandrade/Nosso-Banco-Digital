package com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.service.interfaces;

import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.dto.resposta.RespostaClienteDTO;
import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.dto.solicitacao.ClienteDTO;
import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.model.Cliente;
import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.model.Endereco;

import org.springframework.data.domain.Page;


public interface ClienteService {
    Page<RespostaClienteDTO> buscarTodosClientes(org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable pageable);

    Cliente atualizarClientePeloId(Long id, ClienteDTO cliente);

    Cliente buscarClienteId(Long id);

    Cliente buscarClienteCPF(String cpf);

    Cliente buscarClienteEmail(String email);

    Page<RespostaClienteDTO> buscarClienteNome(String name, org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable pageable);

    Cliente salvarNovoCliente(ClienteDTO cliente);

    Cliente seExistirRetornarEnderecoClienteId(Long id);

    void deletarClienteId(Long id);

    void salvarEnderecoCliente(Cliente cliente, Endereco endereco);


}

