package br.ufpb.streaming.interacao;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DispositivoTest {

    @Test
    void deveCriarDispositivoComDadosValidos() {
        Dispositivo dispositivo = new Dispositivo(
                1,
                "Smart TV",
                "Televisão da sala"
        );

        assertEquals(1, dispositivo.getIdDispositivo());
        assertEquals("Smart TV", dispositivo.getTipo());
        assertEquals("Televisão da sala", dispositivo.getNome());
        assertEquals(
                "Televisão da sala (Smart TV)",
                dispositivo.toString()
        );
    }

    @Test
    void naoDeveCriarDispositivoComIdInvalido() {
        try {
            new Dispositivo(0, "Celular", "Celular pessoal");
            fail("Deveria lançar uma exceção.");
        } catch (IllegalArgumentException excecao) {
            assertEquals(
                    "O ID do dispositivo deve ser positivo.",
                    excecao.getMessage()
            );
        }
    }

    @Test
    void naoDeveCriarDispositivoSemTipo() {
        try {
            new Dispositivo(1, "", "Dispositivo pessoal");
            fail("Deveria lançar uma exceção.");
        } catch (IllegalArgumentException excecao) {
            assertEquals(
                    "O tipo do dispositivo é obrigatório.",
                    excecao.getMessage()
            );
        }
    }

    @Test
    void naoDeveCriarDispositivoSemNome() {
        try {
            new Dispositivo(
                    1,
                    "Smart TV",
                    " "
            );

            fail("Deveria lançar uma exceção.");
        } catch (IllegalArgumentException excecao) {
            assertEquals(
                    "O nome do dispositivo é obrigatório.",
                    excecao.getMessage()
            );
        }
    }
}
