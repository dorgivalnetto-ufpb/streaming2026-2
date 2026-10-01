package br.ufpb.streaming.restricao;

import br.ufpb.streaming.catalogo.Filme;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DisponibilidadeRegionalTest {

    @Test
    void deveCriarDisponibilidadeRegionalAtiva() {
        Filme filme = criarFilme();
        Regiao regiao = criarRegiao();

        DisponibilidadeRegional disponibilidade =
                new DisponibilidadeRegional(
                        1,
                        filme,
                        regiao,
                        LocalDate.now().minusDays(1),
                        LocalDate.now().plusDays(30)
                );

        assertTrue(disponibilidade.verificarDisponibilidade());
        assertSame(filme, disponibilidade.getConteudo());
        assertSame(regiao, disponibilidade.getRegiao());
    }

    @Test
    void deveTornarTituloIndisponivel() {
        DisponibilidadeRegional disponibilidade =
                criarDisponibilidade();

        disponibilidade.tornarIndisponivel(
                "Licença expirada."
        );

        assertFalse(disponibilidade.verificarDisponibilidade());
        assertEquals(
                "Licença expirada.",
                disponibilidade.getMotivo()
        );
    }

    @Test
    void deveTornarTituloDisponivelNovamente() {
        DisponibilidadeRegional disponibilidade =
                criarDisponibilidade();

        disponibilidade.tornarIndisponivel("Licença expirada.");
        disponibilidade.tornarDisponivel(
                LocalDate.now().plusDays(60)
        );

        assertTrue(disponibilidade.verificarDisponibilidade());
        assertEquals("", disponibilidade.getMotivo());
    }

    @Test
    void naoDeveConsiderarDisponivelComLicencaExpirada() {
        DisponibilidadeRegional disponibilidade =
                new DisponibilidadeRegional(
                        1,
                        criarFilme(),
                        criarRegiao(),
                        LocalDate.now().minusDays(30),
                        LocalDate.now().minusDays(1)
                );

        assertFalse(disponibilidade.verificarDisponibilidade());
    }

    private DisponibilidadeRegional criarDisponibilidade() {
        return new DisponibilidadeRegional(
                1,
                criarFilme(),
                criarRegiao(),
                LocalDate.now().minusDays(1),
                LocalDate.now().plusDays(30)
        );
    }

    private Filme criarFilme() {
        return new Filme(
                1, "Viagem Interestelar", "Uma aventura pelo espaço.",
                12, 169, 2024, "Estúdio Universo", "Português"
        );
    }

    private Regiao criarRegiao() {
        return new Regiao(1, "Nordeste", "Brasil");
    }
}
