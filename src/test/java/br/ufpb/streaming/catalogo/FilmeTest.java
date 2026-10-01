package br.ufpb.streaming.catalogo;
import br.ufpb.streaming.interacao.Avaliacao;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FilmeTest {

    @Test
    void deveRegistrarAvaliacaoNoFilme() {
        Filme filme = criarFilme();

        Avaliacao avaliacao =
                new Avaliacao(
                        5,
                        "Excelente filme!"
                );

        filme.registrarAvaliacao(
                avaliacao
        );

        assertEquals(
                1,
                filme.listarAvaliacoes().size()
        );

        assertSame(
                avaliacao,
                filme.listarAvaliacoes().get(0)
        );
    }

    @Test
    void naoDeveRegistrarAvaliacaoNula() {
        Filme filme = criarFilme();

        try {
            filme.registrarAvaliacao(null);

            fail("Deveria lançar uma exceção.");

        } catch (IllegalArgumentException excecao) {
            assertEquals(
                    "A avaliação é obrigatória.",
                    excecao.getMessage()
            );
        }

        assertEquals(
                0,
                filme.listarAvaliacoes().size()
        );
    }

    private Filme criarFilme() {
        return new Filme(
                1,
                "Viagem Interestelar",
                "Uma aventura pelo espaço.",
                12,
                169,
                2024,
                "Estúdio Universo",
                "Português"
        );
    }
}