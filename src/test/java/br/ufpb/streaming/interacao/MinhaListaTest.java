package br.ufpb.streaming.interacao;

import br.ufpb.streaming.catalogo.Filme;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MinhaListaTest {

    /*
    não permitir conteúdo nulo;
    não permitir dois conteúdos com o mesmo ID;
    remover pelo ID;
    retornar null quando o conteúdo não existir.
    */

    //implementar
    @Test
    void deveAdicionarFilmeNaMinhaLista(){
        MinhaLista minhaLista =
                new MinhaLista(1);

        Filme filme = criarFilme(
                1,
                "Viagem Interestelar"
        );

        minhaLista.adicionarConteudo(filme);

        assertEquals(
                1,
                minhaLista.listarConteudos().size()
        );

        assertSame(
                filme,
                minhaLista.listarConteudos().get(0)
        );
    }

    //implementar
    @Test
    void naoDeveAdicionarConteudosComMesmoId(){
        MinhaLista minhaLista =
                new MinhaLista(1);

        Filme primeiroFilme = criarFilme(
                1,
                "Viagem Interestelar"
        );

        Filme segundoFilme = criarFilme(
                1,
                "Outro filme"
        );

        minhaLista.adicionarConteudo(
                primeiroFilme
        );

        try {
            minhaLista.adicionarConteudo(
                    segundoFilme
            );

            fail("Deveria lançar uma exceção.");

        } catch (IllegalArgumentException excecao) {
            assertEquals(
                    "O conteúdo já está na lista.",
                    excecao.getMessage()
            );
        }

        assertEquals(
                1,
                minhaLista.listarConteudos().size()
        );

        assertSame(
                primeiroFilme,
                minhaLista.listarConteudos().get(0)
        );
    }

    @Test
    void deveRemoverConteudoDaMinhaLista(){

    }

    @Test
    void deveRetornarNullQuandoConteudoNaoExistir(){

    }

    private Filme criarFilme(
            int id,
            String titulo) {

        return new Filme(
                id,
                titulo,
                "Uma aventura pelo espaço.",
                12,
                169,
                2024,
                "Estúdio Universo",
                "Português"
        );
    }
}
