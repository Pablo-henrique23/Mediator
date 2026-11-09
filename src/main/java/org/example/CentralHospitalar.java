package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CentralHospitalar implements Mediador {
    private final Map<String, Colaborador> colaboradores = new HashMap<>();

    @Override
    public void cadastrar(Colaborador colaborador) {
        Objects.requireNonNull(colaborador, "O colaborador e obrigatorio");
        if (colaboradores.containsKey(colaborador.getNome())) {
            throw new IllegalArgumentException("Nome ja cadastrado: " + colaborador.getNome());
        }
        colaboradores.put(colaborador.getNome(), colaborador);
    }

    @Override
    public void enviarMensagem(Colaborador remetente, String destinatario, String texto) {
        Objects.requireNonNull(remetente, "O remetente e obrigatorio");
        if (colaboradores.get(remetente.getNome()) != remetente) {
            throw new IllegalStateException("Remetente nao cadastrado");
        }
        if (destinatario == null || destinatario.isBlank()) {
            throw new IllegalArgumentException("Destinatario invalido");
        }
        if (texto == null || texto.isBlank()) {
            throw new IllegalArgumentException("Mensagem vazia");
        }

        Colaborador receptor = colaboradores.get(destinatario);
        if (receptor == null) {
            throw new IllegalArgumentException("Destinatario nao encontrado: " + destinatario);
        }
        receptor.receberMensagem(remetente.getNome(), texto);
    }
}
