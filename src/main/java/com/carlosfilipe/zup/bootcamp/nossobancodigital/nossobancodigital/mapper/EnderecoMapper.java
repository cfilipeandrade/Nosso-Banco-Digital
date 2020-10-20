package com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.mapper;

import java.util.List;

import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.dto.resposta.RespostaEnderecoDTO;
import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.dto.solicitacao.EnderecoDTO;
import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.model.Endereco;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public class EnderecoMapper {

    public Endereco converterParaEndereco(EnderecoDTO enderecoDTO);

    public RespostaEnderecoDTO converterParaRespostaEnderecoDTO(Endereco endereco);

    public List<RespostaEnderecoDTO> converterParaListaRespostaEnderecoDTO(List<Endereco> listaEnderecos);

    public List<Endereco> converterParaListaEndereco(List<EnderecoDTO> listaEnderecosDTO);

	public Endereco converteParaEndereco(EnderecoDTO enderecoDTO) {
		return converteParaEndereco(enderecoDTO);
	}
    
}
