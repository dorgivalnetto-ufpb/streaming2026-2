package br.ufpb.streaming.restricao;

import br.ufpb.streaming.catalogo.Filme;
import br.ufpb.streaming.enums.TipoPerfil;
import br.ufpb.streaming.usuario.Perfil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BloqueioTituloTest {

    @Test
    void deveCriarBloqueioAtivo() {
        Perfil perfil = criarPerfilKids();
        Filme filme = criarFilme(1, 12);

        BloqueioTitulo bloqueio = new BloqueioTitulo(
                1, perfil, filme
        );

        assertTrue(bloqueio.isAtivo());
        assertNotNull(bloqueio.getDataBloqueio());
        assertSame(perfil, bloqueio.getPerfil());
        assertSame(filme, bloqueio.getConteudo());
    }

    @Test
    void deveDesativarBloqueio() {
        BloqueioTitulo bloqueio = new BloqueioTitulo(
                1, criarPerfilKids(), criarFilme(1, 12)
        );

        bloqueio.desativar();

        assertFalse(bloqueio.isAtivo());
    }

    @Test
    void deveAtivarBloqueioNovamente() {
        BloqueioTitulo bloqueio = new BloqueioTitulo(
                1, criarPerfilKids(), criarFilme(1, 12)
        );

        bloqueio.desativar();
        bloqueio.ativar();

        assertTrue(bloqueio.isAtivo());
    }

    private Perfil criarPerfilKids() {
        return new Perfil(1, "Perfil Kids", TipoPerfil.KIDS);
    }

    private Filme criarFilme(int id, int classificacao) {
        return new Filme(
                id, "Filme", "Descrição", classificacao,
                100, 2026, "Estúdio", "Português"
        );
    }
}
