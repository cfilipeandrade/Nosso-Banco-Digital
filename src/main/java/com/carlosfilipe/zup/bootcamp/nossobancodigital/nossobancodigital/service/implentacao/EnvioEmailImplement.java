package com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.service.implentacao;

import com.carlosfilipe.zup.bootcamp.nossobancodigital.nossobancodigital.model.Mensagem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EnvioEmailImplement {

    @Autowired
    private JavaMailSender javaMailSender;

    public void enviar(Mensagem mensagem) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setFrom(mensagem.getRemetente());
        simpleMailMessage.setTo(mensagem.getDestinatario()).toArray(new String[mensagem.getDestinatario().size()]));
        simpleMailMessage.setSubject(mensagem.getAssunto());
        simpleMailMessage.setText(mensagem.getTextoMensagem());

        javaMailSender.send(simpleMailMessage);
    }
    
}
