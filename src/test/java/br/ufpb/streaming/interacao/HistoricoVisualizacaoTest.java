package br.ufpb.streaming.interacao;

import br.ufpb.streaming.catalogo.Filme;
import br.ufpb.streaming.enums.TipoPerfil;
import br.ufpb.streaming.usuario.Perfil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HistoricoVisualizacaoTest {

    @Test
    void deveRegistrarVisualizacao() {
        Perfil perfil = criarPerfil();
        Filme filme = criarFilme();
        Dispositivo dispositivo = criarDispositivo();

        HistoricoVisualizacao historico =
                new HistoricoVisualizacao(
                        1, perfil, filme, dispositivo
                );

        historico.registrarVisualizacao();

        assertNotNull(historico.getDataHora());
        assertSame(perfil, historico.getPerfil());
        assertSame(filme, historico.getConteudo());
        assertSame(dispositivo, historico.getDispositivo());
        assertEquals(0.0, historico.getProgresso(), 0.01);
        assertEquals(0, historico.getTempoAssistido());
    }

    @Test
    void deveAtualizarProgressoDaVisualizacao() {
        HistoricoVisualizacao historico =
                new HistoricoVisualizacao(
                        1,
                        criarPerfil(),
                        criarFilme(),
                        criarDispositivo()
                );

        historico.registrarVisualizacao();
        historico.atualizarProgresso(50.0, 84);

        assertEquals(50.0, historico.getProgresso(), 0.01);
        assertEquals(84, historico.getTempoAssistido());
    }

    @Test
    void naoDeveAceitarProgressoMaiorQueCem() {
        HistoricoVisualizacao historico =
                new HistoricoVisualizacao(
                        1,
                        criarPerfil(),
                        criarFilme(),
                        criarDispositivo()
                );

        try {
            historico.atualizarProgresso(110.0, 100);
            fail("Deveria lançar uma exceção.");
        } catch (IllegalArgumentException excecao) {
            assertEquals(
                    "O progresso deve estar entre 0 e 100.",
                    excecao.getMessage()
            );
        }
    }

    @Test
    void naoDeveAceitarTempoAssistidoNegativo() {
        HistoricoVisualizacao historico =
                criarHistorico();

        try {
            historico.atualizarProgresso(
                    50.0,
                    -10
            );

            fail("Deveria lançar uma exceção.");
        } catch (IllegalArgumentException excecao) {
            assertEquals(
                    "O tempo assistido não pode ser negativo.",
                    excecao.getMessage()
            );
        }
    }

    private Perfil criarPerfil() {
        return new Perfil(1, "Perfil principal", TipoPerfil.ADULTO);
    }

    private Filme criarFilme() {
        return new Filme(
                1, "Viagem Interestelar", "Uma aventura pelo espaço.",
                12, 169, 2024, "Estúdio Universo", "Português"
        );
    }

    private Dispositivo criarDispositivo() {
        return new Dispositivo(1, "Smart TV", "Televisão da sala");
    }
}
