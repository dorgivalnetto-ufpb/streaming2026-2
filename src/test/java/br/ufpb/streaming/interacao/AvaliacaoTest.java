package br.ufpb.streaming.interacao;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AvaliacaoTest {

    @Test
    void deveCriarAvaliacaoComNotaEComentario() {
        Avaliacao avaliacao =
                new Avaliacao(
                        5,
                        "Excelente filme!"
                );

        assertEquals(
                5,
                avaliacao.getNota()
        );

        assertEquals(
                "Excelente filme!",
                avaliacao.getComentario()
        );
    }

    @Test
    void naoDeveAceitarNotaMenorQueUm() {
        try {
            new Avaliacao(
                    0,
                    "Não gostei."
            );

            fail("Deveria lançar uma exceção.");

        } catch (IllegalArgumentException excecao) {
            assertEquals(
                    "A nota deve estar entre 1 e 5.",
                    excecao.getMessage()
            );
        }
    }

    @Test
    void naoDeveAceitarNotaMaiorQueCinco() {
        try {
            new Avaliacao(
                    6,
                    "Muito bom."
            );

            fail("Deveria lançar uma exceção.");

        } catch (IllegalArgumentException excecao) {
            assertEquals(
                    "A nota deve estar entre 1 e 5.",
                    excecao.getMessage()
            );
        }
    }
}