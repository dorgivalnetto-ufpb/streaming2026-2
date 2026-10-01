package br.ufpb.streaming.usuario;

import br.ufpb.streaming.enums.TipoPerfil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ContaTest {

    //implementar
    @Test
    void deveAdicionarPerfilNaConta(){
        Conta conta = new Conta(
                1,
                "usuario@email.com",
                "123456"
        );

        Perfil perfil = new Perfil(
                1,
                "Perfil principal",
                TipoPerfil.ADULTO
        );

        conta.adicionarPerfil(perfil);

        assertEquals(
                1,
                conta.listarPerfis().size()
        );

        assertSame(
                perfil,
                conta.listarPerfis().get(0)
        );
    }

    //implementar
    @Test
    void naoDeveAdicionarPerfisComMesmoId(){
        Conta conta = new Conta(
                1,
                "usuario@email.com",
                "123456"
        );

        Perfil primeiroPerfil =
                new Perfil(
                        1,
                        "Perfil principal",
                        TipoPerfil.ADULTO
                );

        Perfil segundoPerfil =
                new Perfil(
                        1,
                        "Perfil convidado",
                        TipoPerfil.CONVIDADO
                );

        conta.adicionarPerfil(
                primeiroPerfil
        );

        try {
            conta.adicionarPerfil(
                    segundoPerfil
            );

            fail("Deveria lançar uma exceção.");

        } catch (IllegalArgumentException excecao) {
            assertEquals(
                    "Já existe um perfil com o ID 1.",
                    excecao.getMessage()
            );
        }

        assertEquals(
                1,
                conta.listarPerfis().size()
        );

        assertSame(
                primeiroPerfil,
                conta.listarPerfis().get(0)
        );
    }


    @Test
    void deveBuscarPerfilPeloId(){

    }

    @Test
    void deveRemoverPerfilDaConta(){

    }

    @Test
    void deveRetornarNullQuandoPerfilNaoExistir(){

    }
}
