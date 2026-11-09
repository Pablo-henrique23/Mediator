package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Colaborador {
    private final String nome;
    private final Mediador mediador;
    private final List<String> mensagensRecebidas = new ArrayList<>();

    protected Colaborador(String nome, Mediador mediador) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome e obrigatorio");
        }
        this.nome = nome;
        this.mediador = Objects.requireNonNull(mediador, "O mediador e obrigatorio");
    }

    public String getNome() {
        return nome;
    }

    public void enviarMensagem(String destinatario, String texto) {
        mediador.enviarMensagem(this, destinatario, texto);
    }

    void receberMensagem(String remetente, String texto) {
        mensagensRecebidas.add(remetente + ": " + texto);
    }

    public List<String> getMensagensRecebidas() {
        return List.copyOf(mensagensRecebidas);
    }
}
