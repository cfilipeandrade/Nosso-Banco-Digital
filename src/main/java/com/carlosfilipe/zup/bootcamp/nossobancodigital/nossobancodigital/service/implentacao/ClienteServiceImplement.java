package com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.service.implentacao;

import java.util.List;
import java.util.Optional;

import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.dto.resposta.RespostaClienteDTO;
import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.dto.solicitacao.ClienteDTO;
import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.exception.RecursoNaoEncontrado;
import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.mapper.ClienteMapper;
import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.model.Cliente;
import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.model.Endereco;
import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.repository.ClienteRepository;
import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.service.interfaces.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImplement implements ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ClienteMapper clienteMapper;

    private Cliente converterParaCliente(ClienteDTO cliente) {
        return clienteMapper.converterParaCliente(cliente);
    }

    private List<RespostaClienteDTO> converterParaListaRespostaClienteDTO(List<Cliente> listaClientes) {
        return clienteMapper.converterParaListaRespostaClienteDTO(listaClientes);
    }

    private Page<RespostaClienteDTO> converterParaPageRespostaClienteDTO(Page<Cliente> paginaClientes,
            Pageable pageable) {
        List<RespostaClienteDTO> dtos = converterParaListaRespostaClienteDTO(paginaClientes.getContent());
        return new PageImpl<>(dtos, pageable, paginaClientes.getTotalElements());
    }

    @Override
    public Page<RespostaClienteDTO> buscarTodosClientes(Pageable pageable) {
        Page<Cliente> paginaClientes = clienteRepository.findAll(pageable);
        return converterParaPageRespostaClienteDTO(paginaClientes, pageable);
    }

    @Override
    public Page<RespostaClienteDTO> buscarClienteNome(String nome, Pageable pageable) {
        Page<Cliente> paginaClientes = clienteRepository.findAllByPrimeiroNome(nome, pageable);
        return converterParaPageRespostaClienteDTO(paginaClientes, pageable);
    }

   
    @Override
    public Cliente buscarClienteId(Long id) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        return clienteOptional.orElseThrow(() -> new RecursoNaoEncontrado("Não existe cliente com o ID: " + id));
    }

    @Override
    public Cliente buscarClienteCPF(String cpf) {
        Optional<Cliente> clienteOptional = clienteRepository.findByCpf(cpf);
        return clienteOptional.orElseThrow(() -> new RecursoNaoEncontrado("Não existe cliente cadastrado com o CPF: " + cpf));
    }


    @Override
    public Cliente buscarClienteEmail(String email) {
        Optional<Cliente> clienteOptional = clienteRepository.findByEmail(email);
        return clienteOptional.orElseThrow(() -> new RecursoNaoEncontrado("Não existe cliente cadastrado com o E-mail: " + email));
    }

    @Override
    public Cliente atualizarClientePeloId(Long id, ClienteDTO cliente) {
        Cliente clienteEncontrado = buscarClienteId(id);
        clienteEncontrado.setCpf(cliente.getCpf());
        clienteEncontrado.setDataNascimento(cliente.getDataDeNascimento());
        clienteEncontrado.setEmail(cliente.getEmail());
        clienteEncontrado.setPrimeiroNome(cliente.getNome());
        clienteEncontrado.setSobrenome(cliente.getSobrenome());
        return clienteRepository.save(clienteEncontrado);
    }

    @Override
    public Cliente salvarNovoCliente(ClienteDTO clienteDTO) {
        Cliente cliente = converterParaCliente(clienteDTO);
        return clienteRepository.save(cliente);
    }

    @Override
    public void deletarClienteId(Long id) {
        Cliente clienteEncontrado = buscarClienteId(id);
        clienteRepository.delete(clienteEncontrado);
    }

    @Override
    public void salvarEnderecoCliente(Cliente cliente, Endereco endereco) {
        cliente.setEndereco(endereco);
        clienteRepository.save(cliente);
    }

    @Override
    public Cliente seExistirRetornarEnderecoClienteId(Long id) {
        Cliente clienteEncontrado = buscarClienteId(id);
        Optional<Cliente> enderecoOptional = clienteRepository.findByEndereco(clienteEncontrado.getEndereco());
        return enderecoOptional.orElseThrow(() -> new RecursoNaoEncontrado("O cliente do ID " + id + " não possui endereço cadastrado"));
    }

    @Override
    public Page<RespostaClienteDTO> buscarTodosClientes(
            org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Page<RespostaClienteDTO> buscarClienteNome(String name,
            org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    
    

    
}
