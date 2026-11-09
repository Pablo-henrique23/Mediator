package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CentralHospitalarTest {
    private CentralHospitalar central;
    private Medico medico;
    private Enfermeiro enfermeiro;
    private Recepcionista recepcionista;

    @BeforeEach
    void preparar() {
        central = new CentralHospitalar();
        medico = new Medico("Dr. Paulo", central);
        enfermeiro = new Enfermeiro("Ana", central);
        recepcionista = new Recepcionista("Carla", central);

        central.cadastrar(medico);
        central.cadastrar(enfermeiro);
        central.cadastrar(recepcionista);
    }

    @Test
    void medicoEnviaMensagemAoEnfermeiro() {
        medico.enviarMensagem("Ana", "Verifique o paciente do quarto 12");

        assertEquals(1, enfermeiro.getMensagensRecebidas().size());
        assertEquals("Dr. Paulo: Verifique o paciente do quarto 12",
                enfermeiro.getMensagensRecebidas().get(0));
    }

    @Test
    void apenasODestinatarioRecebeAMensagem() {
        medico.enviarMensagem("Ana", "Preciso de ajuda");

        assertTrue(medico.getMensagensRecebidas().isEmpty());
        assertTrue(recepcionista.getMensagensRecebidas().isEmpty());
    }

    @Test
    void recepcionistaConsegueEnviarMensagemAoMedico() {
        recepcionista.enviarMensagem("Dr. Paulo", "Seu paciente chegou");

        assertEquals("Carla: Seu paciente chegou", medico.getMensagensRecebidas().get(0));
    }

    @Test
    void rejeitaDestinatarioNaoCadastrado() {
        assertThrows(IllegalArgumentException.class,
                () -> medico.enviarMensagem("Joao", "Ola"));
    }

    @Test
    void rejeitaRemetenteNaoCadastrado() {
        Medico visitante = new Medico("Dr. Visitante", central);

        assertThrows(IllegalStateException.class,
                () -> visitante.enviarMensagem("Ana", "Ola"));
    }

    @Test
    void rejeitaNomeDuplicado() {
        assertThrows(IllegalArgumentException.class,
                () -> central.cadastrar(new Medico("Ana", central)));
    }

    @Test
    void rejeitaMensagemVazia() {
        assertThrows(IllegalArgumentException.class,
                () -> medico.enviarMensagem("Ana", "  "));
    }
}
