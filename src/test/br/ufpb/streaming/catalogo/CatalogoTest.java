package test.br.ufpb.streaming.catalogo;

import br.ufpb.streaming.catalogo.Catalogo;
import br.ufpb.streaming.catalogo.Conteudo;
import br.ufpb.streaming.catalogo.Filme;
import br.ufpb.streaming.catalogo.Serie;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CatalogoTest {

    @Test
    void deveCadastrarFilmeNoCatalogo(){

        //AAA

        //Arrange: preparação
        //criar catalogo e adicionar um filme
        Catalogo catalogo = new Catalogo();

        //Filme filme = new Filme();
        Filme filme = criarFilme(1, "Viagem Interestalar");

        //Act: ação executar
        //adicionar filme
        catalogo.adicionarConteudo(filme);

        //Assert
        //verificação: o filme foi adicionado a lista?
        //esperado, verificar
        assertEquals(1, catalogo.listarConteudos().size());

        assertSame(filme, catalogo.listarConteudos().get(0));
    }

    @Test
    void naoDeveCadastrarConteudosComMesmoId(){
        Catalogo catalogo = new Catalogo();

        Filme primeiroFilme = criarFilme(1, "Toy Story 5");

        Filme segundoFilme = criarFilme(1, "Homem de ferro");

        catalogo.adicionarConteudo(primeiroFilme);

        try{
            catalogo.adicionarConteudo(segundoFilme);

            fail("Deveria lançar uma exceção");
        } catch (IllegalArgumentException excecao){
            assertEquals(
            "Já existe um conteúdo com o ID 1",
            excecao.getMessage());
        }

        //confirma que o segundo filme não foi adicionado
        assertEquals(1, catalogo.listarConteudos().size());
    }

    @Test
    void devePesquisarConteudoPorTitulo(){

        //Catalogo, Filme1, Filme2, Serie
        Catalogo catalogo = new Catalogo();

        Filme primeiroFilme = criarFilme(1, "Toy Story 5");

        Filme segundoFilme = criarFilme(2, "Homem de ferro");

        Serie serie = new Serie(3, "Lost", "Descrição", 12, "Vince", 2000);

        //adicionarConteudo(), pesquisarPorTitulo()
        catalogo.adicionarConteudo(primeiroFilme);
        catalogo.adicionarConteudo(segundoFilme);
        catalogo.adicionarConteudo(serie);

        List<Conteudo> resultados = catalogo.pesquisarPorTitulo("story");

        //assert
        assertEquals(1, resultados.size());

        assertSame(primeiroFilme, resultados.get(0));

        assertEquals("Toy Story", resultados.get(0).getTitulo());
    }

    private Filme criarFilme(int id, String titulo){

        return new Filme(id, titulo, "Uma aventura no espaço", 12, 120,
                2024);

    }
}
