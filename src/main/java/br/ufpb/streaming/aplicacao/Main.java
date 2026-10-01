package br.ufpb.streaming.aplicacao;

import br.ufpb.streaming.catalogo.Catalogo;
import br.ufpb.streaming.catalogo.CategoriaEtiqueta;
import br.ufpb.streaming.catalogo.Conteudo;
import br.ufpb.streaming.catalogo.Episodio;
import br.ufpb.streaming.catalogo.Filme;
import br.ufpb.streaming.catalogo.Serie;
import br.ufpb.streaming.catalogo.Temporada;
import br.ufpb.streaming.enums.StatusSerie;
import br.ufpb.streaming.enums.TipoCategoria;
import br.ufpb.streaming.enums.TipoPerfil;
import br.ufpb.streaming.enums.TipoPesquisa;
import br.ufpb.streaming.interacao.Avaliacao;
import br.ufpb.streaming.interacao.Dispositivo;
import br.ufpb.streaming.interacao.HistoricoVisualizacao;
import br.ufpb.streaming.interacao.Recomendacao;
import br.ufpb.streaming.pessoa.Ator;
import br.ufpb.streaming.pessoa.Diretor;
import br.ufpb.streaming.restricao.ControleParental;
import br.ufpb.streaming.restricao.DisponibilidadeRegional;
import br.ufpb.streaming.restricao.Regiao;
import br.ufpb.streaming.usuario.Administrador;
import br.ufpb.streaming.usuario.Conta;
import br.ufpb.streaming.usuario.Perfil;

import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        System.out.println(
                "=== PLATAFORMA DE STREAMING ==="
        );

        /*
         * 1. Catálogo e administrador
         */

        Catalogo catalogo = new Catalogo();

        Administrador administrador =
                new Administrador(
                        1,
                        "Administrador",
                        "admin@streaming.com",
                        catalogo
                );

        /*
         * 2. Conta e perfis
         */

        Conta conta = new Conta(
                1,
                "usuario@email.com",
                "123456"
        );

        Perfil perfilAdulto = new Perfil(
                1,
                "Dorgival",
                TipoPerfil.ADULTO
        );

        Perfil perfilKids = new Perfil(
                2,
                "Ravi",
                TipoPerfil.KIDS
        );

        Perfil perfilConvidado = new Perfil(
                3,
                "Convidado",
                TipoPerfil.CONVIDADO
        );

        conta.adicionarPerfil(perfilAdulto);
        conta.adicionarPerfil(perfilKids);
        conta.adicionarPerfil(perfilConvidado);

        System.out.println("\n=== PERFIS DA CONTA ===");

        for (Perfil perfil : conta.listarPerfis()) {
            System.out.println(perfil);
        }

        /*
         * 3. Categorias
         */

        CategoriaEtiqueta ficcao =
                new CategoriaEtiqueta(
                        1,
                        "Ficção científica",
                        TipoCategoria.GENERO
                );

        CategoriaEtiqueta aventura =
                new CategoriaEtiqueta(
                        2,
                        "Aventura",
                        TipoCategoria.GENERO
                );

        CategoriaEtiqueta viagemEspacial =
                new CategoriaEtiqueta(
                        3,
                        "Viagem espacial",
                        TipoCategoria.TAG
                );

        administrador.cadastrarCategoria(ficcao);
        administrador.cadastrarCategoria(aventura);
        administrador.cadastrarCategoria(
                viagemEspacial
        );

        /*
         * 4. Atores e diretor
         */

        Ator anaSilva = new Ator(
                1,
                "Ana Silva"
        );

        Ator carlosSantos = new Ator(
                2,
                "Carlos Santos"
        );

        Diretor diretor = new Diretor(
                3,
                "Marcos Oliveira"
        );

        /*
         * 5. Filme
         */

        Filme filme = new Filme(
                1,
                "Viagem Interestelar",
                "Uma equipe viaja pelo espaço.",
                12,
                169,
                2024,
                "Estúdio Universo",
                "Português"
        );

        filme.adicionarAtor(anaSilva);
        filme.adicionarDiretor(diretor);

        administrador.classificarConteudo(
                filme,
                ficcao
        );

        administrador.classificarConteudo(
                filme,
                viagemEspacial
        );

        /*
         * 6. Série
         */

        Serie serie = new Serie(
                2,
                "Aventuras no Espaço",
                "Uma equipe explora diferentes planetas.",
                10,
                "Maria Souza",
                2026
        );

        serie.adicionarAtor(carlosSantos);
        serie.adicionarDiretor(diretor);

        administrador.classificarConteudo(
                serie,
                ficcao
        );

        administrador.classificarConteudo(
                serie,
                aventura
        );

        serie.setStatus(
                StatusSerie.EM_ANDAMENTO
        );

        /*
         * 7. Temporada e episódios
         */

        Temporada primeiraTemporada =
                new Temporada(
                        1,
                        "Primeira temporada",
                        2026
                );

        Episodio primeiroEpisodio =
                new Episodio(
                        1,
                        "A partida",
                        45,
                        "A equipe inicia sua missão.",
                        LocalDate.of(
                                2026,
                                3,
                                10
                        )
                );

        Episodio segundoEpisodio =
                new Episodio(
                        2,
                        "O planeta desconhecido",
                        48,
                        "A equipe encontra um planeta misterioso.",
                        LocalDate.of(
                                2026,
                                3,
                                17
                        )
                );

        primeiraTemporada.adicionarEpisodio(
                primeiroEpisodio
        );

        primeiraTemporada.adicionarEpisodio(
                segundoEpisodio
        );

        serie.adicionarTemporada(
                primeiraTemporada
        );

        /*
         * 8. Cadastro dos títulos
         */

        administrador.cadastrarTitulo(filme);
        administrador.cadastrarTitulo(serie);

        System.out.println(
                "\n=== CATÁLOGO ==="
        );

        exibirConteudos(
                catalogo.listarConteudos()
        );

        System.out.println(
                "Quantidade de conteúdos: "
                        + catalogo.quantidadeConteudos()
        );

        /*
         * 9. Busca por ID
         */

        System.out.println(
                "\n=== BUSCA PELO ID 1 ==="
        );

        Conteudo encontrado =
                catalogo.buscarPorId(1);

        if (encontrado != null) {
            System.out.println(encontrado);
        } else {
            System.out.println(
                    "Conteúdo não encontrado."
            );
        }

        /*
         * 10. Pesquisas com TipoPesquisa
         */

        System.out.println(
                "\n=== PESQUISA POR TÍTULO ==="
        );

        exibirConteudos(
                catalogo.pesquisar(
                        "espaço",
                        TipoPesquisa.TITULO
                )
        );

        System.out.println(
                "\n=== PESQUISA POR GÊNERO ==="
        );

        exibirConteudos(
                catalogo.pesquisar(
                        "ficção",
                        TipoPesquisa.GENERO
                )
        );

        System.out.println(
                "\n=== PESQUISA POR ELENCO ==="
        );

        exibirConteudos(
                catalogo.pesquisar(
                        "Ana Silva",
                        TipoPesquisa.ELENCO
                )
        );

        /*
         * 11. Métodos específicos de pesquisa
         */

        System.out.println(
                "\n=== PESQUISA ESPECÍFICA POR TÍTULO ==="
        );

        exibirConteudos(
                catalogo.pesquisarPorTitulo(
                        "viagem"
                )
        );

        System.out.println(
                "\n=== PESQUISA ESPECÍFICA POR GÊNERO ==="
        );

        exibirConteudos(
                catalogo.pesquisarPorGenero(
                        "aventura"
                )
        );

        System.out.println(
                "\n=== PESQUISA ESPECÍFICA POR ELENCO ==="
        );

        exibirConteudos(
                catalogo.pesquisarPorElenco(
                        "Carlos"
                )
        );

        /*
         * 12. Minha Lista
         */

        perfilAdulto.adicionarNaMinhaLista(
                filme
        );

        System.out.println(
                "\n=== MINHA LISTA DE "
                        + perfilAdulto.getNome()
                        + " ==="
        );

        exibirConteudos(
                perfilAdulto.listarMinhaLista()
        );

        System.out.println(
                "Filme está na lista: "
                        + perfilAdulto
                        .getMinhaLista()
                        .contem(
                                filme.getIdConteudo()
                        )
        );

        /*
         * 13. Avaliação
         */

        Avaliacao avaliacao =
                new Avaliacao(
                        1,
                        perfilAdulto,
                        filme,
                        5,
                        "Excelente filme!"
                );

        avaliacao.registrarAvaliacao();

        System.out.println(
                "\n=== AVALIAÇÃO ==="
        );

        System.out.println(avaliacao);

        System.out.println(
                "Média do filme: "
                        + filme.calcularMediaAvaliacoes()
        );

        /*
         * 14. Reprodução
         */

        System.out.println(
                "\n=== REPRODUÇÃO ==="
        );

        filme.reproduzir();
        primeiroEpisodio.reproduzir();

        System.out.println(
                "Duração do filme: "
                        + filme.obterDuracao()
                        + " minutos"
        );

        System.out.println(
                "Duração do episódio: "
                        + primeiroEpisodio
                        .obterDuracao()
                        + " minutos"
        );

        /*
         * 15. Histórico de visualização
         */

        Dispositivo smartTv =
                new Dispositivo(
                        1,
                        "Smart TV",
                        "Televisão da sala"
                );

        HistoricoVisualizacao historico =
                new HistoricoVisualizacao(
                        1,
                        perfilAdulto,
                        filme,
                        smartTv
                );

        historico.registrarVisualizacao();

        historico.atualizarProgresso(
                50.0,
                84
        );

        System.out.println(
                "\n=== HISTÓRICO ==="
        );

        System.out.println(historico);

        System.out.println(
                "Progresso: "
                        + historico.getProgresso()
                        + "%"
        );

        System.out.println(
                "Tempo assistido: "
                        + historico.getTempoAssistido()
                        + " minutos"
        );

        /*
         * 16. Controle parental
         */

        ControleParental controleParental =
                new ControleParental(
                        1,
                        10
                );

        System.out.println(
                "\n=== CONTROLE PARENTAL ==="
        );

        boolean seriePermitida =
                controleParental
                        .verificarClassificacao(
                                perfilKids,
                                serie
                        );

        boolean filmePermitido =
                controleParental
                        .verificarClassificacao(
                                perfilKids,
                                filme
                        );

        System.out.println(
                "Kids pode assistir à série: "
                        + seriePermitida
        );

        System.out.println(
                "Kids pode assistir ao filme: "
                        + filmePermitido
        );

        controleParental.bloquearTitulo(
                perfilKids,
                serie
        );

        System.out.println(
                "Série permitida após bloqueio: "
                        + controleParental
                        .verificarClassificacao(
                                perfilKids,
                                serie
                        )
        );

        controleParental.permitirTitulo(
                perfilKids,
                serie
        );

        System.out.println(
                "Série permitida após liberação: "
                        + controleParental
                        .verificarClassificacao(
                                perfilKids,
                                serie
                        )
        );

        /*
         * 17. Disponibilidade regional
         */

        Regiao nordeste = new Regiao(
                1,
                "Nordeste",
                "Brasil"
        );

        Regiao europa = new Regiao(
                2,
                "Europa",
                "Portugal"
        );

        DisponibilidadeRegional disponibilidadeNordeste =
                new DisponibilidadeRegional(
                        1,
                        filme,
                        nordeste,
                        LocalDate.now().minusDays(1),
                        LocalDate.now().plusDays(365)
                );

        DisponibilidadeRegional disponibilidadeEuropa =
                new DisponibilidadeRegional(
                        2,
                        filme,
                        europa,
                        LocalDate.now().minusDays(1),
                        LocalDate.now().plusDays(365)
                );

        filme.adicionarDisponibilidade(
                disponibilidadeNordeste
        );

        filme.adicionarDisponibilidade(
                disponibilidadeEuropa
        );

        administrador.tornarTituloIndisponivel(
                disponibilidadeEuropa,
                "Restrição de licença."
        );

        System.out.println(
                "\n=== DISPONIBILIDADE REGIONAL ==="
        );

        System.out.println(
                "Disponível no Nordeste: "
                        + filme.verificarDisponibilidade(
                        nordeste
                )
        );

        System.out.println(
                "Disponível na Europa: "
                        + filme.verificarDisponibilidade(
                        europa
                )
        );

        administrador.tornarTituloDisponivel(
                disponibilidadeEuropa,
                LocalDate.now().plusDays(180)
        );

        System.out.println(
                "Disponível na Europa após renovação: "
                        + filme.verificarDisponibilidade(
                        europa
                )
        );

        /*
         * 18. Recomendações
         */

        Recomendacao recomendacao =
                new Recomendacao(
                        1,
                        perfilAdulto
                );

        recomendacao.gerarSugestoes(
                catalogo
        );

        System.out.println(
                "\n=== RECOMENDAÇÕES ==="
        );

        exibirConteudos(
                recomendacao.listarConteudos()
        );

        /*
         * 19. Navegação pela série
         */

        System.out.println(
                "\n=== TEMPORADAS E EPISÓDIOS ==="
        );

        for (Temporada temporada
                : serie.obterTemporadas()) {

            System.out.println(temporada);

            for (Episodio episodio
                    : temporada.obterEpisodios()) {

                System.out.println(
                        "  " + episodio
                );
            }
        }

        /*
         * 20. Edição de conteúdo
         */

        Filme filmeTemporario =
                new Filme(
                        3,
                        "Filme temporário",
                        "Conteúdo para demonstrar edição.",
                        10,
                        100,
                        2026,
                        "Estúdio Exemplo",
                        "Português"
                );

        administrador.cadastrarTitulo(
                filmeTemporario
        );

        Filme filmeAtualizado =
                new Filme(
                        3,
                        "Filme temporário atualizado",
                        "Descrição atualizada.",
                        12,
                        105,
                        2026,
                        "Estúdio Exemplo",
                        "Português"
                );

        administrador.editarTitulo(
                filmeAtualizado
        );

        System.out.println(
                "\n=== CONTEÚDO EDITADO ==="
        );

        System.out.println(
                catalogo.buscarPorId(3)
        );

        /*
         * 21. Remoção de conteúdo
         */

        administrador.removerTitulo(
                filmeAtualizado
        );

        System.out.println(
                "\n=== CATÁLOGO APÓS REMOÇÃO ==="
        );

        exibirConteudos(
                catalogo.listarConteudos()
        );

        /*
         * 22. Remoção de Minha Lista
         */

        perfilAdulto.removerDaMinhaLista(
                filme.getIdConteudo()
        );

        System.out.println(
                "\n=== MINHA LISTA APÓS REMOÇÃO ==="
        );

        exibirConteudos(
                perfilAdulto.listarMinhaLista()
        );

        System.out.println(
                "\n=== FIM DA EXECUÇÃO ==="
        );
    }

    private static void exibirConteudos(
            List<Conteudo> conteudos) {

        if (conteudos.isEmpty()) {
            System.out.println(
                    "Nenhum conteúdo encontrado."
            );

            return;
        }

        for (Conteudo conteudo : conteudos) {
            System.out.println(
                    "- " + conteudo
            );
        }
    }
}