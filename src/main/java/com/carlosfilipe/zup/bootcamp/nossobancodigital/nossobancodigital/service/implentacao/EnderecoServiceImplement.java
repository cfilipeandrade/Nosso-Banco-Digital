package com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.service.implentacao;

import java.util.List;

import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.dto.resposta.RespostaEnderecoDTO;
import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.dto.solicitacao.EnderecoDTO;
import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.exception.RecursoNaoEncontrado;
import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.mapper.EnderecoMapper;
import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.model.Cliente;
import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.model.Endereco;
import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.repository.EnderecoRepository;
import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.service.interfaces.ClienteService;
import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.service.interfaces.EnderecoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EnderecoServiceImplement implements EnderecoService {

    @Autowired
    EnderecoRepository enderecoRepository;

    @Autowired
    ClienteService clienteService;

    @Autowired
    EnderecoMapper enderecoMapper;

    @Override
    public Page<RespostaEnderecoDTO> buscarTodosClientes(Pageable pageable) {
        Page<Endereco> paginaEnderecos = enderecoRepository.findAll(pageable);
        return converterParaPageRespostaEnderecoDTO(paginaEnderecos, pageable);
    }

    @Override
    public Endereco salvarNovoEndereco(Long id, EnderecoDTO enderecoDTO) {

        Cliente cliente = clienteService.buscarClienteId(id);

        Endereco endereco = converterParaEndereco(enderecoDTO);
        cliente.setEndereco(endereco);
        System.out.println(endereco);
        Endereco enderecoSalvo = enderecoRepository.save(endereco);

        clienteService.salvarEnderecoCliente(cliente, enderecoSalvo);
        return (converterParaEndereco(enderecoDTO));
    }

    @Override
    public Endereco buscarEnderecoId(Long id) {
        return seExistirRetornarEnderecoComId(id);
    }

    @Override
    public Endereco buscarEnderecoCep(String cep) {
        return enderecoRepository.findByCep(cep)
                .orElseThrow(() -> new RecursoNaoEncontrado("Não encontrado endereco nesse cep: " + cep));
    }

    @Override
    public Endereco seExistirAtualizarEndereco(Long id, EnderecoDTO enderecoDTO) {
        Endereco enderecoEncontrado = seExistirRetornarEnderecoComId(id);
        Endereco endereco = converterParaEndereco(enderecoDTO);
        enderecoEncontrado.setBairro(endereco.getBairro());
        enderecoEncontrado.setCep(endereco.getCep());
        enderecoEncontrado.setCidade(endereco.getCidade());
        enderecoEncontrado.setEstado(endereco.getEstado());
        enderecoEncontrado.setComplemento(endereco.getComplemento());
        enderecoEncontrado.setRua(endereco.getRua());
        endereco.setId(enderecoEncontrado.getId());
        return enderecoRepository.save(enderecoEncontrado);
    }


    public void deletarEnderecoId(Long id) {
        Endereco enderecoEncontrado = seExistirRetornarEnderecoComId(id);
        enderecoRepository.delete(enderecoEncontrado);
    }

    @Override
    public Endereco seExistirRetornarEnderecoComId(Long id) {
        return enderecoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontrado("Não foi encontrado endereco com esse id: " + id));
    }

    private Endereco converterParaEndereco(EnderecoDTO enderecoDTO) {
        return enderecoMapper.converteParaEndereco(enderecoDTO);
    }

    private List<RespostaEnderecoDTO> converterParaListaRespostaEnderecoDTO(List<Endereco> listaEndereco) {
        return enderecoMapper.converterParaListaRespostaEnderecoDTO(listaEndereco);
    }

    private Page<RespostaEnderecoDTO> converterParaPageRespostaEnderecoDTO(Page<Endereco> paginaEnderecos, Pageable pageable) {
        List<RespostaEnderecoDTO> dtos = converterParaListaRespostaEnderecoDTO(paginaEnderecos.getContent());
        return new PageImpl<>(dtos, pageable, paginaEnderecos.getTotalElements());
    }

    
}
