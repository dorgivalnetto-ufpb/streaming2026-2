package br.ufpb.streaming.catalogo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SerieTest {

    @Test
    void deveAdicionarTemporadaNaSerie() {
        Serie serie = criarSerie();

        Temporada temporada =
                new Temporada(
                        1,
                        "Primeira temporada",
                        2026
                );

        serie.adicionarTemporada(
                temporada
        );

        assertEquals(
                1,
                serie.obterTemporadas().size()
        );

        assertSame(
                temporada,
                serie.buscarTemporada(1)
        );
    }

    @Test
    void naoDeveAdicionarTemporadasComMesmoNumero() {
        Serie serie = criarSerie();

        Temporada primeira =
                new Temporada(
                        1,
                        "Primeira temporada",
                        2026
                );

        Temporada duplicada =
                new Temporada(
                        1,
                        "Outra temporada",
                        2027
                );

        serie.adicionarTemporada(
                primeira
        );

        /*
        try {
        // Tenta adicionar uma temporada com número repetido.
        serie.adicionarTemporada(duplicada);

        // Se nenhuma exceção ocorrer, o teste falha.
        fail("Deveria lançar uma exceção.");

        } catch (IllegalArgumentException excecao) {
        // Verifica se a exceção possui a mensagem esperada.
        assertEquals(
                "Já existe uma temporada com o número 1.",
                excecao.getMessage()
        );
    }

         */

        IllegalArgumentException excecao =
                /* o assertThrows() recebe duas informações:
                assertThrows(
                    tipoDaExcecaoEsperada,
                    operaçãoQueSeráExecutada);
                 */
                assertThrows(
                        //indica que o teste espera uma IllegalArgumentException.
                        IllegalArgumentException.class,
                        /* é uma expressão lambda. Ela representa a operação que o JUnit deve executar.
                        Execute serie.adicionarTemporada(duplicada) e verifique se essa operação lança uma IllegalArgumentException.
                        A operação não é executada antes do assertThrows().
                        Ela é entregue ao JUnit para que o próprio JUnit a execute e observe o resultado.
                         */
                        () -> serie.adicionarTemporada(duplicada)
                        //Se a exceção ocorrer, o assertThrows() retorna o objeto da exceção:
                );

        assertEquals(
                "Já existe uma temporada com o número 1.",
                //Por isso é possível verificar sua mensagem:
                excecao.getMessage()
                /*
                O teste falhará se:
                    nenhuma exceção for lançada;
                    for lançada uma exceção de outro tipo;
                    a mensagem for diferente da esperada.
                 */
        );

        assertEquals(
                1,
                serie.obterTemporadas().size()
        );

        assertSame(
                primeira,
                serie.obterTemporadas().get(0)
        );
    }


    @Test
    void deveRetornarNullQuandoTemporadaNaoExistir() {
        Serie serie = criarSerie();

        Temporada resultado =
                serie.buscarTemporada(10);

        assertNull(resultado);
    }

    private Serie criarSerie() {
        return new Serie(
                1,
                "Aventuras no Espaço",
                "Uma equipe explora planetas.",
                10,
                "Ana Silva",
                2026
        );
    }
}