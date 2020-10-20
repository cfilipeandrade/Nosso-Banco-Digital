package com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.mapper;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.mapstruct.Mapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.controller.CadastroClienteController;
import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.dto.resposta.RespostaClienteDTO;
import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.dto.resposta.RespostaEnderecoDTO;
import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.dto.solicitacao.ClienteDTO;
import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.dto.solicitacao.EnderecoDTO;
import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.model.Cliente;
import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.model.Endereco;

@Mapper(componentModel = "spring")
public class ClienteMapper{

    public Cliente converterParaCliente(ClienteDTO clienteDTO);

    public RespostaClienteDTO converterParaRespostaClienteDTO(Cliente cliente);

    public List<RespostaClienteDTO> converterParaListaRespostaClienteDTO(List<Cliente> listaCliente);

    public List<Cliente> converterParaListaCliente(List<ClienteDTO> listaClienteDTO);

}

