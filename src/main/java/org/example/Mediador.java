package org.example;

public interface Mediador {
    void cadastrar(Colaborador colaborador);
    void enviarMensagem(Colaborador remetente, String destinatario, String texto);
}
