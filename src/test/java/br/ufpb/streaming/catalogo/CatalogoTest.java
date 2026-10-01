package br.ufpb.streaming.catalogo;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CatalogoTest {

    @Test
    void deveCadastrarFilmeNoCatalogo() {
        //ARRANGE: preparar
        //Preparação: cria catálogo e filme.
        Catalogo catalogo = new Catalogo();

        Filme filme = criarFilme(
                1,
                "Viagem Interestelar"
        );

        //ACT: executar
        //Execução: adiciona o filme
        catalogo.adicionarConteudo(filme);

        //ASSERT
        //Verificação: confere o tamanho da lista.
        assertEquals(
                1,
                catalogo.listarConteudos().size()
        );

        //assertSame() para confirmar que é o mesmo objeto;
        assertSame(
                filme,
                catalogo.listarConteudos().get(0)
        );
    }

    @Test
    void naoDeveCadastrarConteudosComMesmoId() {
        Catalogo catalogo = new Catalogo();

        Filme primeiroFilme = criarFilme(
                1,
                "Viagem Interestelar"
        );

        Filme segundoFilme = criarFilme(
                1,
                "Outro filme"
        );

        catalogo.adicionarConteudo(
                primeiroFilme
        );

        try {
            //O teste tenta adicionar o segundo filme.
            catalogo.adicionarConteudo(segundoFilme);
            //Se nenhuma exceção ocorrer, chega ao fail() e o teste falha.
            fail("Deveria lançar uma exceção.");
            //Se IllegalArgumentException ocorrer, entra no catch.
        } catch (IllegalArgumentException excecao) {
            assertEquals(
                    //Dentro do catch, verifica a mensagem da exceção.
                    "Já existe um conteúdo com o ID 1.",
                    excecao.getMessage()
            );
        }

        //// Confirma que o segundo filme não foi adicionado.
        assertEquals(
                1,
                catalogo.listarConteudos().size()
        );
    }

    @Test
    void devePesquisarConteudoPorTitulo() {
        Catalogo catalogo = new Catalogo();

        Filme filme1 = criarFilme(
                1,
                "Viagem Interestelar"
        );

        Filme filme2 = criarFilme(
                2,
                "O Planeta Desconhecido"
        );

        Serie serie = new Serie(
                3,
                "Aventuras no Espaço",
                "Uma equipe explora diferentes planetas.",
                10,
                "Ana Silva",
                2026
        );

        catalogo.adicionarConteudo(filme1);
        catalogo.adicionarConteudo(filme2);
        catalogo.adicionarConteudo(serie);

        List<Conteudo> resultados =
                catalogo.pesquisarPorTitulo(
                        "planeta"
                );

        assertEquals(
                1,
                resultados.size()
        );

        assertSame(
                filme2,
                resultados.get(0)
        );

        assertEquals(
                "O Planeta Desconhecido",
                resultados.get(0).getTitulo()
        );
    }

    //Ele é um metodo auxiliar dos testes. Em vez de repetir oito argumentos sempre que for necessário
    //criar um filme, o teste informa apenas os dados que variam.
    //O metodo completa os demais dados com valores válidos.
    //Ele não recebe @Test, porque não é um caso de teste. É apenas um metodo utilizado para preparar
    //os objetos necessários aos testes
    private Filme criarFilme(
            int id,
            String titulo) {

        return new Filme(
                id,
                titulo,
                "Uma aventura pelo espaço.",
                12,
                169,
                2024,
                "Estúdio Universo",
                "Português"
        );
    }
}