package com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.service.implentacao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.model.Cliente;
import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.model.FotoCpf;
import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.repository.ClienteRepository;
import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.repository.FotoCpfRepository;
import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.service.interfaces.ClienteService;
import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.service.interfaces.CpfService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CpfServiceImplement implements CpfService {

    @Autowired
    ClienteService clienteService;

    @Autowired
	public
    ClienteRepository clienteRepository;

    @Autowired
	public
    FotoCpfRepository fotoCpfRepository;

    @Override
    public void salvarArquivoCpfCliente(String diretorio, Long id, MultipartFile cpfFrente) {

        Cliente cliente = clienteService.seExistirRetornarEnderecoClienteId(id);

        Path diretorioPath = Paths.get(diretorio, "cliente_" + id);

        gerarDiretorioDestinoCpf(diretorioPath, cpfFrente);
        String caminhoArquivoFrente = buscarLocalizacaoCpf(diretorioPath, cpfFrente.getOriginalFilename());

        salvarCpfCliente(cliente,  caminhoArquivoFrente);
    }


    public FotoCpf gerarNovoCpf(String cpfFrente) {
        FotoCpf cpf = new FotoCpf();
        cpf.setFrenteCpf(cpfFrente);
        return cpf;
    }

    public String buscarLocalizacaoCpf(Path diretorioPath, String nomeDoArquivo) {
        return diretorioPath.toString() + "\\" + nomeDoArquivo;
    }

    private Path gerarDiretorioDestinoCpf(Path diretorioPath, MultipartFile arquivo) {

        String nomeArquivoFrente = retornarDiretorioDestinoCpf(diretorioPath.toString(), arquivo);
        Path arquivoPathFrente = diretorioPath.resolve(nomeArquivoFrente);
        try {
            Files.createDirectories(diretorioPath);
            arquivo.transferTo(arquivoPathFrente.toFile());
        } catch (IOException e) {
            throw new RuntimeException("Problemas na tentativa de salvar arquivo: ", e);
        }
        return diretorioPath;
    }
    private String retornarDiretorioDestinoCpf(String diretorioPath, MultipartFile arquivo) {
        return diretorioPath + "\\" + arquivo.getOriginalFilename();
    }

    @Override
    public void salvarCpfCliente(Cliente cliente, String cpfFrente) {
        FotoCpf cpf = gerarNovoCpf(cpfFrente);
        cliente.setFotoCpf(fotoCpfRepository.save(cpf));
        clienteRepository.save(cliente);

    }

}
