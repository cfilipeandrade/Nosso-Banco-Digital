package com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.controller;

import javax.validation.Valid;

import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.dto.resposta.RespostaEnderecoDTO;
import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.dto.solicitacao.EnderecoDTO;
import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.mapper.EnderecoMapper;
import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.model.Endereco;
import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.service.interfaces.EnderecoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "controle de endereço dos clientes", description = "Controle interno de endereço dos clientes", tags = {"Controle de Endereços"})
@RestController
@RequestMapping("admin/endereco")
public class EnderecoController {

    @Autowired
    EnderecoService enderecoService;

    @Autowired
    EnderecoMapper enderecoMapper;

    @GetMapping
    @ApiOperation(value = "Busca todos os Endereços cadastrados na base de dados" )
    @ApiResponses(value = {@ApiResponse(code = 404, message = "Endereços não localizados"),
            @ApiResponse(code = 403, message = "Você não possui permissão para visualizar este recurso"),
            @ApiResponse(code = 401, message = "Você não possui credenciais de autenticação válidas"),
            @ApiResponse(code = 200, message = "Endereços localizados")})
    public Page<RespostaEnderecoDTO> buscarTodosEnderecos(@PageableDefault(size = 5) Pageable pageable) {
        return enderecoService.buscarTodosClientes(pageable);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Busca o Endereço especificado pelo Id informado" )
    @ApiResponses(value = {@ApiResponse(code = 404, message = "Endereço não localizado"),
            @ApiResponse(code = 403, message = "Você não possui permissão para visualizar este recurso"),
            @ApiResponse(code = 401, message = "Você não possui credenciais de autenticação válidas"),
            @ApiResponse(code = 200, message = "Endereço localizado")})
    public ResponseEntity<RespostaEnderecoDTO> busqueEnderecoId(@PathVariable Long id) {
        Endereco enderecoEncontradoPeloId = enderecoService.buscarEnderecoId(id);
        return ResponseEntity.ok(converterParaRespostaEnderecoDTO(enderecoEncontradoPeloId));
    }

    @GetMapping("/cep/{cep}")
    @ApiOperation(value = "Busca o Endereço especificado pelo CEP informado" )
    @ApiResponses(value = {@ApiResponse(code = 404, message = "Endereço não localizado"),
            @ApiResponse(code = 403, message = "Você não possui permissão para visualizar este recurso"),
            @ApiResponse(code = 401, message = "Você não possui credenciais de autenticação válidas"),
            @ApiResponse(code = 200, message = "Endereço localizado")})
    public ResponseEntity<RespostaEnderecoDTO> buscarEnderecoCep(@PathVariable String cep) {
        Endereco enderecoEncontradoPeloCep = enderecoService.buscarEnderecoCep(cep);
        return ResponseEntity.ok(converterParaRespostaEnderecoDTO(enderecoEncontradoPeloCep));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Atualiza o Endereço, se existir, especificado pelo Id informado" )
    @ApiResponses(value = {@ApiResponse(code = 404, message = "Endereço não localizado"),
            @ApiResponse(code = 403, message = "Você não possui permissão para visualizar este recurso"),
            @ApiResponse(code = 401, message = "Você não possui credenciais de autenticação válidas"),
            @ApiResponse(code = 200, message = "Endereço atualizado com sucesso")})
    public ResponseEntity<RespostaEnderecoDTO> atualizarEnderecoPeloId(@PathVariable Long id, @RequestBody @Valid EnderecoDTO endereco) {
        Endereco enderecoAtualizado = enderecoService.seExistirAtualizarEndereco(id, endereco);
        return ResponseEntity.ok(converterParaRespostaEnderecoDTO(enderecoAtualizado));
    }

    private RespostaEnderecoDTO converterParaRespostaEnderecoDTO(Endereco endereco) {
        return enderecoMapper.converterParaRespostaEnderecoDTO(endereco);
    }
    
}
