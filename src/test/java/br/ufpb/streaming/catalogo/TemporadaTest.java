package br.ufpb.streaming.catalogo;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TemporadaTest {

    @Test
    void deveAdicionarEpisodioNaTemporada() {
        Temporada temporada =
                criarTemporada();

        Episodio episodio =
                criarEpisodio(
                        1,
                        "A partida"
                );

        temporada.adicionarEpisodio(
                episodio
        );

        assertEquals(
                1,
                temporada.obterEpisodios().size()
        );

        assertSame(
                episodio,
                temporada.buscarEpisodio(1)
        );
    }

    @Test
    void naoDeveAdicionarEpisodiosComMesmoNumero() {
        Temporada temporada =
                criarTemporada();

        Episodio primeiro =
                criarEpisodio(
                        1,
                        "A partida"
                );

        Episodio duplicado =
                criarEpisodio(
                        1,
                        "Outro episódio"
                );

        temporada.adicionarEpisodio(
                primeiro
        );

        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> temporada
                        .adicionarEpisodio(
                                duplicado
                        )
        );

        assertEquals(
                "Já existe um episódio com o número 1.",
                excecao.getMessage()
        );

        assertEquals(
                1,
                temporada.obterEpisodios().size()
        );

        assertSame(
                primeiro,
                temporada.obterEpisodios().get(0)
        );
    }

    @Test
    void deveRetornarNullQuandoEpisodioNaoExistir() {
        Temporada temporada =
                criarTemporada();

        Episodio resultado =
                temporada.buscarEpisodio(10);

        assertNull(resultado);
    }

    private Temporada criarTemporada() {
        return new Temporada(
                1,
                "Primeira temporada",
                2026
        );
    }

    private Episodio criarEpisodio(
            int numero,
            String titulo) {

        return new Episodio(
                numero,
                titulo,
                45,
                "Sinopse do episódio.",
                LocalDate.of(
                        2026,
                        3,
                        10
                )
        );
    }
}