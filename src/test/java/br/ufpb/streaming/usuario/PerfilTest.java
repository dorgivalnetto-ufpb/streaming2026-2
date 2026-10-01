package br.ufpb.streaming.usuario;

import br.ufpb.streaming.catalogo.Filme;
import br.ufpb.streaming.enums.TipoPerfil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PerfilTest {

    @Test
    void deveAdicionarConteudoNaMinhaListaDoPerfil() {
        // Preparação
        Perfil perfil = criarPerfil();

        Filme filme = criarFilme();

        // Execução
        perfil.adicionarNaMinhaLista(
                filme
        );

        // Verificação
        assertEquals(
                1,
                perfil.listarMinhaLista().size()
        );

        assertSame(
                filme,
                perfil.listarMinhaLista().get(0)
        );
    }

    @Test
    void deveRemoverConteudoDaMinhaListaDoPerfil() {
        // Preparação
        Perfil perfil = criarPerfil();

        Filme filme = criarFilme();

        perfil.adicionarNaMinhaLista(
                filme
        );

        // Execução
        perfil.removerDaMinhaLista(
                filme.getIdConteudo()
        );

        // Verificação
        assertEquals(
                0,
                perfil.listarMinhaLista().size()
        );

        assertFalse(
                perfil.getMinhaLista().contem(
                        filme.getIdConteudo()
                )
        );
    }

    @Test
    void deveCriarPerfilComListaVazia() {
        // Execução
        Perfil perfil = criarPerfil();

        // Verificação
        assertNotNull(
                perfil.getMinhaLista()
        );

        assertEquals(
                0,
                perfil.listarMinhaLista().size()
        );

        assertTrue(
                perfil.listarMinhaLista().isEmpty()
        );
    }

    private Perfil criarPerfil() {
        return new Perfil(
                1,
                "Perfil principal",
                TipoPerfil.ADULTO
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