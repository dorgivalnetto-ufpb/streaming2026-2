package br.ufpb.streaming.restricao;

import br.ufpb.streaming.catalogo.Filme;
import br.ufpb.streaming.enums.TipoPerfil;
import br.ufpb.streaming.usuario.Perfil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControleParentalTest {

    @Test
    void devePermitirConteudoComClassificacaoAdequada() {
        ControleParental controle = new ControleParental(1, 10);
        Perfil perfil = criarPerfil(1, TipoPerfil.KIDS);
        Filme filme = criarFilme(1, 10);

        boolean permitido = controle.verificarClassificacao(
                perfil, filme
        );

        assertTrue(permitido);
    }

    @Test
    void naoDevePermitirConteudoAcimaDaClassificacao() {
        ControleParental controle = new ControleParental(1, 10);
        Perfil perfil = criarPerfil(1, TipoPerfil.KIDS);
        Filme filme = criarFilme(1, 16);

        boolean permitido = controle.verificarClassificacao(
                perfil, filme
        );

        assertFalse(permitido);
    }

    @Test
    void naoDevePermitirTituloBloqueado() {
        ControleParental controle = new ControleParental(1, 18);
        Perfil perfil = criarPerfil(1, TipoPerfil.KIDS);
        Filme filme = criarFilme(1, 10);

        controle.bloquearTitulo(perfil, filme);

        assertFalse(
                controle.verificarClassificacao(perfil, filme)
        );
    }

    @Test
    void devePermitirTituloDepoisDeRemoverBloqueio() {
        ControleParental controle = new ControleParental(1, 18);
        Perfil perfil = criarPerfil(1, TipoPerfil.KIDS);
        Filme filme = criarFilme(1, 10);

        controle.bloquearTitulo(perfil, filme);
        controle.permitirTitulo(perfil, filme);

        assertTrue(
                controle.verificarClassificacao(perfil, filme)
        );
    }

    @Test
    void bloqueioDeUmPerfilNaoDeveBloquearOutroPerfil() {
        ControleParental controle =
                new ControleParental(1, 12);

        Perfil primeiroPerfil = new Perfil(
                1,
                "Criança 1",
                TipoPerfil.KIDS
        );

        Perfil segundoPerfil = new Perfil(
                2,
                "Criança 2",
                TipoPerfil.KIDS
        );

        Filme filme = criarFilme(1,  10);

        controle.bloquearTitulo(
                primeiroPerfil,
                filme
        );

        assertFalse(
                controle.verificarClassificacao(
                        primeiroPerfil,
                        filme
                )
        );

        assertTrue(
                controle.verificarClassificacao(
                        segundoPerfil,
                        filme
                )
        );
    }

    @Test
    void devePermitirConteudoParaPerfilAdulto() {
        ControleParental controle =
                new ControleParental(1, 10);

        Perfil adulto = new Perfil(
                1,
                "Adulto",
                TipoPerfil.ADULTO
        );

        Filme filme = criarFilme(
                1,
                18
        );

        assertTrue(
                controle.verificarClassificacao(
                        adulto,
                        filme
                )
        );
    }

    @Test
    void naoDeveCriarBloqueiosDuplicados() {
        ControleParental controle =
                new ControleParental(1, 12);

        Perfil perfil = new Perfil(
                1,
                "Kids",
                TipoPerfil.KIDS
        );

        Filme filme = criarFilme(
                1,

                10
        );

        controle.bloquearTitulo(
                perfil,
                filme
        );

        try {
            controle.bloquearTitulo(
                    perfil,
                    filme
            );

            fail("Deveria lançar uma exceção.");
        } catch (IllegalArgumentException excecao) {
            assertEquals(
                    "O título já está bloqueado para este perfil.",
                    excecao.getMessage()
            );
        }

        assertEquals(
                1,
                controle.listarBloqueios().size()
        );
    }

    private Perfil criarPerfil(int id, TipoPerfil tipo) {
        return new Perfil(id, "Perfil", tipo);
    }

    private Filme criarFilme(int id, int classificacao) {
        return new Filme(
                id, "Filme", "Descrição", classificacao,
                100, 2026, "Estúdio", "Português"
        );
    }
}
