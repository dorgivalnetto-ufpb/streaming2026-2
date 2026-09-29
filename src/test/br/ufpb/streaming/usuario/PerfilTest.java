package test.br.ufpb.streaming.usuario;

import br.ufpb.streaming.catalogo.Filme;
import br.ufpb.streaming.enums.TipoPerfil;
import br.ufpb.streaming.usuario.Perfil;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class PerfilTest {

    @Test
    void deveAdicionarConteudoNaMinhaListaDoPerfil(){
        //preparação
        Perfil perfil1 = new Perfil(1, "Dorgival", TipoPerfil.ADULTO);

        Filme filme1 = new Filme(1, "Titulo", "Descricao", 12, 120, 2026);

        //execução
        perfil1.adicionarNaMinhaLista(filme1);

        //
        assertEquals(1, perfil1.listarMinhaLista().size());
        assertSame(filme1, perfil1.listarMinhaLista().get(0));
    }

    @Test
    void deveRemoverConteudoDaMinhaListaDoPerfil(){

    }

    @Test
    void deveCriarPerfilComListaVazia(){

    }
}
