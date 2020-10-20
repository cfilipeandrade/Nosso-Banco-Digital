package com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.controller;

import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.service.interfaces.CpfService;

import java.net.URI;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.dto.resposta.RespostaClienteDTO;
import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.dto.resposta.RespostaEnderecoDTO;
import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.dto.solicitacao.ClienteDTO;
import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.dto.solicitacao.EnderecoDTO;
import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.mapper.ClienteMapper;
import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.mapper.EnderecoMapper;
import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.model.Cliente;
import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.model.Endereco;
import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.service.interfaces.ClienteService;
import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.service.interfaces.EnderecoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Cadastro de cliente", description = "Cliente -> Endereco ->Cpf - Aceite", tags = {"Cadastro de Clientes"})
@RestController
@RequestMapping("cadastro")
public class CadastroClienteController {
    
    @Value("${caminho-arquivos-pasta}")
    String diretorioArquivos;

    @Autowired
	public
    ClienteService clienteService;

    @Autowired
	public
    EnderecoService enderecoService;

    @Autowired
    CpfService documentoService;

    @Autowired
    EnderecoMapper enderecoMapper;

    @Autowired
    ClienteMapper clienteMapper;
    
    @PostMapping("/cliente")
    @ApiOperation(value = "Salva dados do Cliente")
    @ApiResponses(value = {@ApiResponse(code = 404, message = "Não foi possível criar o Cliente"),
            @ApiResponse(code = 201, message = "Cliente criado com sucesso")})
    public ResponseEntity salvarCliente(@RequestBody @Valid ClienteDTO client) {
        Cliente clienteSalvo = clienteService.salvarNovoCliente(client);
        URI location = geradorLocation(clienteSalvo.getId(), "/{id}/endereco");
        return ResponseEntity.status(HttpStatus.CREATED).header(HttpHeaders.LOCATION, String.valueOf(location)).body(clienteMapper.converterParaRespostaClienteDTO(clienteSalvo));
    }

    @ApiOperation(value = "Salva dados de endereço do Cliente")
    @PostMapping("cliente/{id}/endereco")
    @ApiResponses(value = {@ApiResponse(code = 404, message = "Não foi possível criar o Endereço"),
            @ApiResponse(code = 422, message = "Necessario Cadastrar o Cliente"),
            @ApiResponse(code = 201, message = "Endereço criado com sucesso")})
    public ResponseEntity salvarEndereco(@RequestBody @Valid EnderecoDTO endereco, @PathVariable("id") Long id) {
        Endereco enderecoSalvo = enderecoService.salvarNovoEndereco(id, endereco);

        URI location = geradorLocation(id, "/arquivo");
        RespostaEnderecoDTO respostaEnderecoDTO = enderecoMapper.converterParaRespostaEnderecoDTO(enderecoSalvo);
        return ResponseEntity.status(HttpStatus.CREATED).header(HttpHeaders.LOCATION, String.valueOf(location)).body(respostaEnderecoDTO);
    }

    @PostMapping("cliente/{id}/endereco/arquivo")
    @ApiOperation(value = "Salva documentos de identificação do cliente (frente)")
    @ApiResponses(value = {@ApiResponse(code = 404, message = "Não foi possível criar o Documento"),
            @ApiResponse(code = 422, message = "Necessario Cadastrar o Cliente e Endereço"),
            @ApiResponse(code = 201, message = "Documento salvo com sucesso")})
    public ResponseEntity salvarArquivoCpfCliente(@RequestParam("frente") MultipartFile fotoCpfFrente,
                                         @PathVariable("id") Long id) {
        documentoService.salvarArquivoCpfCliente(diretorioArquivos, id, fotoCpfFrente);
        URI location = geradorLocation(id, "/aceite");
        RespostaClienteDTO respostaClienteDTO = clienteMapper.converterParaRespostaClienteDTO(clienteService.buscarClienteId(id));
        return ResponseEntity.status(HttpStatus.CREATED).header(HttpHeaders.LOCATION, String.valueOf(location)).body(respostaClienteDTO);

    }

    @PostMapping("cliente/{id}/endereco/arquivo/aceite")
    @ApiOperation(value = "Recebe aceite do cliente a proposta de abertura de conta")
    @ApiResponses(value = {@ApiResponse(code = 404, message = "Não foi possível criar o Documento"),
            @ApiResponse(code = 422, message = "Necessario Cadastrar o Cliente, Endereço e Documento de identificação"),
            @ApiResponse(code = 200, message = "Aceite recebido com sucesso")})
    public String aceiteContrato(@PathVariable("id") Long id, @PathParam("aceite") Boolean aceite) {
        Cliente cliente = clienteService.seExistirRetornarEnderecoClienteId(id);
        enderecoService.seExistirRetornarEnderecoComId(id);
        return (aceite ? "Que ótima notícia " + cliente.getPrimeiroNome() + "!!! Iremos criar a sua conta" : "É uma pena " + cliente.getPrimeiroNome() + " =/ Vamos dar um tempo para você pensar melhor");
    }


    public URI geradorLocation(Long id, String proximoPassoCadastro) {
        Cliente clienteAtualizado = clienteService.buscarClienteId(id);
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path(proximoPassoCadastro)
                .buildAndExpand(clienteAtualizado.getId())
                .toUri();
    }
}
