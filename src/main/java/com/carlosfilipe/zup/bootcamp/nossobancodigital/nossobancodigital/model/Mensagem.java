package  com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mensagem {
    
    private String assunto;

    private String remetente;

    private List<String> destinatario;

    private String textoMensagem;

}