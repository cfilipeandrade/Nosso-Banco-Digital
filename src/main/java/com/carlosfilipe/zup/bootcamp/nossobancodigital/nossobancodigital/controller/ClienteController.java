package com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.controller;

import javax.validation.Valid;

import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.dto.resposta.RespostaClienteDTO;
import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.dto.solicitacao.ClienteDTO;
import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.mapper.ClienteMapper;
import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.model.Cliente;
import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.service.interfaces.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "controle de clientes", description = "Controle interno dos dados do cliente", tags = {"Controle de Clientes"})
@RestController
@RequestMapping("admin/cliente")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @Autowired
    ClienteMapper clienteMapper;

    @GetMapping
    @ApiOperation(value = "Busca todos os clientes cadastrados na base de dados" )
    @ApiResponses(value = {@ApiResponse(code = 404, message = "Clientes não localizados"),
            @ApiResponse(code = 403, message = "Você não possui permissão para visualizar este recurso"),
            @ApiResponse(code = 401, message = "Você não possui credenciais de autenticação válidas"),
            @ApiResponse(code = 200, message = "Clientes localizados")})
    public Page<RespostaClienteDTO> busqueTodosClientes(@PageableDefault(size = 5) Pageable pageable) {

        return clienteService.buscarTodosClientes(pageable);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Busca o Cliente especificado pelo Id informado" )
    @ApiResponses(value = {@ApiResponse(code = 404, message = "Cliente não localizado"),
            @ApiResponse(code = 403, message = "Você não possui permissão para visualizar este recurso"),
            @ApiResponse(code = 401, message = "Você não possui credenciais de autenticação válidas"),
            @ApiResponse(code = 200, message = "Cliente localizado")})
    public ResponseEntity<RespostaClienteDTO> buscarClienteId(@PathVariable Long id) {
        Cliente clienteEncontradoPeloId = clienteService.buscarClienteId(id);
        return ResponseEntity.ok(converterParaRespostaClienteDTO(clienteEncontradoPeloId));
    }

    @GetMapping("/nome/{nome}")
    @ApiOperation(value = "Busca o Cliente especificado pelo Nome informado" )
    @ApiResponses(value = {@ApiResponse(code = 404, message = "Clientes não localizados"),
            @ApiResponse(code = 403, message = "Você não possui permissão para visualizar este recurso"),
            @ApiResponse(code = 401, message = "Você não possui credenciais de autenticação válidas"),
            @ApiResponse(code = 200, message = "Clientes localizados")})
    public ResponseEntity<Page<RespostaClienteDTO>> buscarClienteNome(@PathVariable String nome, @PageableDefault(size = 5) Pageable pageable) {
        return new ResponseEntity<>(clienteService.buscarClienteNome(nome, pageable), HttpStatus.OK);
    }


    @GetMapping("/cpf/{cpf}")
    @ApiOperation(value = "Busca o Cliente especificado pelo CPF informado" )
    @ApiResponses(value = {@ApiResponse(code = 404, message = "Cliente não localizado"),
            @ApiResponse(code = 403, message = "Você não possui permissão para visualizar este recurso"),
            @ApiResponse(code = 401, message = "Você não possui credenciais de autenticação válidas"),
            @ApiResponse(code = 200, message = "Cliente localizado")})
    public ResponseEntity buscarClienteCPF(@PathVariable String cpf) {
        return new ResponseEntity<>(clienteService.buscarClienteCPF(cpf), HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    @ApiOperation(value = "Busca o Cliente especificado pelo Email informado" )
    @ApiResponses(value = {@ApiResponse(code = 404, message = "Cliente não localizado"),
            @ApiResponse(code = 403, message = "Você não possui permissão para visualizar este recurso"),
            @ApiResponse(code = 401, message = "Você não possui credenciais de autenticação válidas"),
            @ApiResponse(code = 200, message = "Cliente localizado")})
    public ResponseEntity buscarClienteEmail(@PathVariable String email) {
        return new ResponseEntity<>(clienteService.buscarClienteEmail(email), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Atualiza o Cliente, se existir, especificado pelo Id informado" )
    @ApiResponses(value = {@ApiResponse(code = 404, message = "Cliente não localizado"),
            @ApiResponse(code = 403, message = "Você não possui permissão para visualizar este recurso"),
            @ApiResponse(code = 401, message = "Você não possui credenciais de autenticação válidas"),
            @ApiResponse(code = 200, message = "Cliente atualizado com sucesso")})
    public RespostaClienteDTO atualizarClientePeloId (@PathVariable Long id, @RequestBody @Valid ClienteDTO cliente, Cliente cliente2) {
        Cliente clienteEncontrado = clienteService.atualizarClientePeloId(id, cliente);
        return converterParaRespostaClienteDTO(clienteEncontrado);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deleta o Cliente, se existir, especificado pelo Id informado" )
    @ApiResponses(value = {@ApiResponse(code = 404, message = "Usuário não localizado"),
            @ApiResponse(code = 403, message = "Você não possui permissão para visualizar este recurso"),
            @ApiResponse(code = 401, message = "Você não possui credenciais de autenticação válidas")})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleterClienteId(@PathVariable Long id) {
        clienteService.deletarClienteId(id);
    }

    private RespostaClienteDTO converterParaRespostaClienteDTO(Cliente cliente) {
        return clienteMapper.converterParaRespostaClienteDTO(cliente);
    }
    
}
