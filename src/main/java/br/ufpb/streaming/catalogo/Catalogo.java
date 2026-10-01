package br.ufpb.streaming.catalogo;

import br.ufpb.streaming.enums.TipoCategoria;
import br.ufpb.streaming.enums.TipoPesquisa;
import br.ufpb.streaming.pessoa.Ator;

import java.util.ArrayList;
import java.util.List;

public class Catalogo {

    private final List<Conteudo> conteudos;
    private final List<CategoriaEtiqueta> categorias;

    public Catalogo() {
        this.conteudos = new ArrayList<>();
        this.categorias = new ArrayList<>();
    }

    /*
     * Conteúdos
     */

    public void adicionarConteudo(
            Conteudo conteudo) {

        if (conteudo == null) {
            throw new IllegalArgumentException(
                    "O conteúdo não pode ser nulo."
            );
        }

        if (buscarPorId(
                conteudo.getIdConteudo()) != null) {

            throw new IllegalArgumentException(
                    "Já existe um conteúdo com o ID "
                            + conteudo.getIdConteudo()
                            + "."
            );
        }

        conteudos.add(conteudo);
    }

    public Conteudo buscarPorId(
            int idConteudo) {

        for (Conteudo conteudo : conteudos) {
            if (conteudo.getIdConteudo()
                    == idConteudo) {

                return conteudo;
            }
        }

        return null;
    }

    public void editarConteudo(
            Conteudo conteudoAtualizado) {

        if (conteudoAtualizado == null) {
            throw new IllegalArgumentException(
                    "O conteúdo é obrigatório."
            );
        }

        for (int i = 0; i < conteudos.size(); i++) {
            Conteudo conteudo =
                    conteudos.get(i);

            if (conteudo.getIdConteudo()
                    == conteudoAtualizado
                    .getIdConteudo()) {

                conteudos.set(
                        i,
                        conteudoAtualizado
                );

                return;
            }
        }

        throw new IllegalArgumentException(
                "Conteúdo não encontrado."
        );
    }

    public boolean removerConteudo(
            Conteudo conteudo) {

        if (conteudo == null) {
            throw new IllegalArgumentException(
                    "O conteúdo é obrigatório."
            );
        }

        Conteudo conteudoEncontrado =
                buscarPorId(
                        conteudo.getIdConteudo()
                );

        if (conteudoEncontrado == null) {
            return false;
        }

        conteudos.remove(conteudoEncontrado);
        return true;
    }

    public List<Conteudo> listarConteudos() {
        return new ArrayList<>(conteudos);
    }

    public int quantidadeConteudos() {
        return conteudos.size();
    }

    /*
     * Pesquisa
     */

    public List<Conteudo> pesquisar(
            String termo,
            TipoPesquisa tipoPesquisa) {

        if (termo == null || termo.isBlank()) {
            throw new IllegalArgumentException(
                    "O termo de pesquisa é obrigatório."
            );
        }

        if (tipoPesquisa == null) {
            throw new IllegalArgumentException(
                    "O tipo de pesquisa é obrigatório."
            );
        }

        String termoPesquisado =
                termo.trim().toLowerCase();

        List<Conteudo> resultados =
                new ArrayList<>();

        for (Conteudo conteudo : conteudos) {
            boolean encontrou = false;

            if (tipoPesquisa
                    == TipoPesquisa.TITULO) {

                encontrou = pesquisarNoTitulo(
                        conteudo,
                        termoPesquisado
                );

            } else if (tipoPesquisa
                    == TipoPesquisa.GENERO) {

                encontrou = pesquisarNoGenero(
                        conteudo,
                        termoPesquisado
                );

            } else if (tipoPesquisa
                    == TipoPesquisa.ELENCO) {

                encontrou = pesquisarNoElenco(
                        conteudo,
                        termoPesquisado
                );
            }

            if (encontrou) {
                resultados.add(conteudo);
            }
        }

        return resultados;
    }

    private boolean pesquisarNoTitulo(
            Conteudo conteudo,
            String termo) {

        String titulo =
                conteudo
                        .getTitulo()
                        .toLowerCase();

        return titulo.contains(termo);
    }

    private boolean pesquisarNoGenero(
            Conteudo conteudo,
            String termo) {

        for (CategoriaEtiqueta categoria
                : conteudo.getCategorias()) {

            boolean categoriaDeGenero =
                    categoria.getTipo()
                            == TipoCategoria.GENERO;

            String nomeCategoria =
                    categoria
                            .getNome()
                            .toLowerCase();

            if (categoriaDeGenero
                    && nomeCategoria.contains(termo)) {

                return true;
            }
        }

        return false;
    }

    private boolean pesquisarNoElenco(
            Conteudo conteudo,
            String termo) {

        for (Ator ator : conteudo.getAtores()) {
            String nomeAtor =
                    ator
                            .getNome()
                            .toLowerCase();

            if (nomeAtor.contains(termo)) {
                return true;
            }
        }

        return false;
    }

    /*
     * Métodos específicos de pesquisa
     */

    public List<Conteudo> pesquisarPorTitulo(
            String titulo) {

        return pesquisar(
                titulo,
                TipoPesquisa.TITULO
        );
    }

    public List<Conteudo> pesquisarPorGenero(
            String genero) {

        return pesquisar(
                genero,
                TipoPesquisa.GENERO
        );
    }

    public List<Conteudo> pesquisarPorElenco(
            String nomeAtor) {

        return pesquisar(
                nomeAtor,
                TipoPesquisa.ELENCO
        );
    }

    /*
     * Categorias
     */

    public void adicionarCategoria(
            CategoriaEtiqueta categoria) {

        if (categoria == null) {
            throw new IllegalArgumentException(
                    "A categoria é obrigatória."
            );
        }

        for (CategoriaEtiqueta item : categorias) {
            if (item.getIdCategoria()
                    == categoria.getIdCategoria()) {

                return;
            }
        }

        categorias.add(categoria);
    }

    public boolean removerCategoria(
            CategoriaEtiqueta categoria) {

        if (categoria == null) {
            throw new IllegalArgumentException(
                    "A categoria é obrigatória."
            );
        }

        for (int i = 0; i < categorias.size(); i++) {
            CategoriaEtiqueta item =
                    categorias.get(i);

            if (item.getIdCategoria()
                    == categoria.getIdCategoria()) {

                categorias.remove(i);
                return true;
            }
        }

        return false;
    }

    public List<CategoriaEtiqueta>
    listarCategorias() {

        return new ArrayList<>(categorias);
    }
}