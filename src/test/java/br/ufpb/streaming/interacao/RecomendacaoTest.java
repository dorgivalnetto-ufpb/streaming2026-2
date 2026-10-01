package br.ufpb.streaming.interacao;

import br.ufpb.streaming.catalogo.Catalogo;
import br.ufpb.streaming.catalogo.Filme;
import br.ufpb.streaming.enums.TipoPerfil;
import br.ufpb.streaming.usuario.Perfil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecomendacaoTest {

    @Test
    void deveAdicionarConteudoRecomendado() {
        Recomendacao recomendacao = new Recomendacao(
                1, criarPerfil()
        );
        Filme filme = criarFilme(1, "Viagem Interestelar");

        recomendacao.adicionarConteudo(filme);

        assertEquals(
                1,
                recomendacao.listarConteudos().size()
        );
        assertTrue(recomendacao.contem(filme));
    }

    @Test
    void naoDeveAdicionarConteudoDuplicado() {
        Recomendacao recomendacao = new Recomendacao(
                1, criarPerfil()
        );
        Filme primeiro = criarFilme(1, "Viagem Interestelar");
        Filme duplicado = criarFilme(1, "Outro filme");

        recomendacao.adicionarConteudo(primeiro);

        try {
            recomendacao.adicionarConteudo(duplicado);
            fail("Deveria lançar uma exceção.");
        } catch (IllegalArgumentException excecao) {
            assertEquals(
                    "O conteúdo já foi recomendado.",
                    excecao.getMessage()
            );
        }

        assertEquals(1, recomendacao.listarConteudos().size());
    }

    @Test
    void naoDeveRecomendarConteudoQueEstaNaMinhaLista() {
        Perfil perfil = criarPerfil();
        Filme filme = criarFilme(1, "Viagem Interestelar");
        perfil.adicionarNaMinhaLista(filme);
        Recomendacao recomendacao = new Recomendacao(1, perfil);

        try {
            recomendacao.adicionarConteudo(filme);
            fail("Deveria lançar uma exceção.");
        } catch (IllegalArgumentException excecao) {
            assertEquals(
                    "O conteúdo já está na Minha Lista do perfil.",
                    excecao.getMessage()
            );
        }
    }

    @Test
    void deveRemoverConteudoRecomendado() {
        Recomendacao recomendacao = new Recomendacao(
                1, criarPerfil()
        );
        Filme filme = criarFilme(1, "Viagem Interestelar");
        recomendacao.adicionarConteudo(filme);

        recomendacao.removerConteudo(
                filme.getIdConteudo()
        );

        assertTrue(recomendacao.listarConteudos().isEmpty());
    }

    @Test
    void deveGerarSugestoesSemConteudosDaMinhaLista() {
        Perfil perfil = new Perfil(
                1,
                "Dorgival",
                TipoPerfil.ADULTO
        );

        Filme filmeNaLista =
                criarFilme(1, "Filme já escolhido");

        Filme filmeRecomendado =
                criarFilme(2, "Filme recomendado");

        perfil.adicionarNaMinhaLista(
                filmeNaLista
        );

        Catalogo catalogo = new Catalogo();

        catalogo.adicionarConteudo(
                filmeNaLista
        );

        catalogo.adicionarConteudo(
                filmeRecomendado
        );

        Recomendacao recomendacao =
                new Recomendacao(1, perfil);

        recomendacao.gerarSugestoes(
                catalogo
        );

        assertFalse(
                recomendacao.contemConteudo(
                        filmeNaLista
                )
        );

        assertTrue(
                recomendacao.contemConteudo(
                        filmeRecomendado
                )
        );

        assertNotNull(
                recomendacao.getDataGeracao()
        );
    }

    @Test
    void alteracaoNaListaRetornadaNaoDeveAlterarRecomendacao() {
        Perfil perfil = new Perfil(
                1,
                "Dorgival",
                TipoPerfil.ADULTO
        );

        Filme filme = criarFilme(
                1,
                "Viagem Interestelar"
        );

        Recomendacao recomendacao =
                new Recomendacao(1, perfil);

        recomendacao.adicionarConteudo(
                filme
        );

        List<Conteudo> copia =
                recomendacao.listarConteudos();

        copia.clear();

        assertEquals(
                1,
                recomendacao.listarConteudos().size()
        );
    }

    private Perfil criarPerfil() {
        return new Perfil(1, "Perfil principal", TipoPerfil.ADULTO);
    }

    private Filme criarFilme(int id, String titulo) {
        return new Filme(
                id, titulo, "Uma aventura pelo espaço.",
                12, 169, 2024, "Estúdio Universo", "Português"
        );
    }
}
