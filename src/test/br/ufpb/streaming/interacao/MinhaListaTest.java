package test.br.ufpb.streaming.interacao;

import br.ufpb.streaming.catalogo.Filme;
import br.ufpb.streaming.interacao.MinhaLista;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class MinhaListaTest {

    /*
    nao permita dois conteudos com os mesmos IDS
    não permita conteudo nulo
    remover pelo ID
    retornar null quanto o conteudo não existir
     */

    @Test
    void deveAdicionarFilmeNaMinhaLista(){
        MinhaLista minhaLista = new MinhaLista(1);

        Filme filme1 = criarFilme(1, "Toy Story 5");

        minhaLista.adicionarConteudo(filme1);

        assertEquals(1,minhaLista.listarConteudos().size());

        assertSame(filme1, minhaLista.listarConteudos().get(0));
    }

    @Test
    void naoDeveAdicionarConteudoComMesmoId(){
        MinhaLista minhaLista = new MinhaLista(1);

        Filme filme1 = new Filme(1, "Titulo", "Descricao", 12, 120, 2026);

        Filme filme2 = criarFilme(1, "Devoradores de Estrelas");

        minhaLista.adicionarConteudo(filme1);

        try{
            minhaLista.adicionarConteudo(filme2);

            fail("Deveria lançar uma exceção.");

        } catch(IllegalArgumentException excecao){
            assertEquals("O conteúdo já existe na lista.", excecao.getMessage());
        }

        assertEquals(1, minhaLista.listarConteudos().size());

        assertSame(filme1, minhaLista.listarConteudos().get(0));
    }

    @Test
    void deveRemoverConteudoDaMinhaLista(){

    }

    @Test
    void deveRetornarNullQuandoConteudoNaoExistir(){

    }

    private Filme criarFilme(int id, String titulo){

        return new Filme(id, titulo, "Uma aventura no espaço", 12, 120,
                2024);

    }
}
