package br.ufpb.streaming.restricao;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegiaoTest {

    @Test
    void deveCriarRegiaoComDadosValidos() {
        Regiao regiao = new Regiao(
                1,
                "Nordeste",
                "Brasil"
        );

        assertEquals(1, regiao.getIdRegiao());
        assertEquals("Nordeste", regiao.getNome());
        assertEquals("Brasil", regiao.getPais());
        assertEquals("Nordeste - Brasil", regiao.toString());
    }

    @Test
    void naoDeveCriarRegiaoSemNome() {
        try {
            new Regiao(1, "", "Brasil");
            fail("Deveria lançar uma exceção.");
        } catch (IllegalArgumentException excecao) {
            assertEquals(
                    "O nome da região é obrigatório.",
                    excecao.getMessage()
            );
        }
    }
}
