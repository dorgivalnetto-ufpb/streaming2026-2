package br.ufpb.streaming.catalogo;

import br.ufpb.streaming.enums.StatusDisponibilidade;
import br.ufpb.streaming.interacao.Avaliacao;
import br.ufpb.streaming.interfaces.Avaliavel;
import br.ufpb.streaming.pessoa.Ator;
import br.ufpb.streaming.pessoa.Diretor;
import br.ufpb.streaming.restricao.DisponibilidadeRegional;
import br.ufpb.streaming.restricao.Regiao;

import java.util.ArrayList;
import java.util.List;

public abstract class Conteudo implements Avaliavel {

    private final int idConteudo;
    private String titulo;
    private String descricao;
    private int classificacaoIndicativa;

    private final List<Avaliacao> avaliacoes;
    private final List<CategoriaEtiqueta> categorias;
    private final List<Ator> atores;
    private final List<Diretor> diretores;
    private final List<DisponibilidadeRegional> disponibilidades;

    private StatusDisponibilidade statusDisponibilidade;

    protected Conteudo(
            int idConteudo,
            String titulo,
            String descricao,
            int classificacaoIndicativa) {

        if (idConteudo <= 0) {
            throw new IllegalArgumentException(
                    "O ID do conteúdo deve ser positivo."
            );
        }

        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException(
                    "O título é obrigatório."
            );
        }

        if (classificacaoIndicativa < 0
                || classificacaoIndicativa > 18) {

            throw new IllegalArgumentException(
                    "A classificação deve estar entre 0 e 18."
            );
        }

        this.idConteudo = idConteudo;
        this.titulo = titulo.trim();

        if (descricao == null) {
            this.descricao = "";
        } else {
            this.descricao = descricao.trim();
        }

        this.classificacaoIndicativa =
                classificacaoIndicativa;

        this.avaliacoes = new ArrayList<>();
        this.categorias = new ArrayList<>();
        this.atores = new ArrayList<>();
        this.diretores = new ArrayList<>();
        this.disponibilidades = new ArrayList<>();

        this.statusDisponibilidade =
                StatusDisponibilidade.DISPONIVEL;
    }

    public int getIdConteudo() {
        return idConteudo;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getClassificacaoIndicativa() {
        return classificacaoIndicativa;
    }

    /*
     * Avaliações
     */

    @Override
    public void registrarAvaliacao(
            Avaliacao avaliacao) {

        if (avaliacao == null) {
            throw new IllegalArgumentException(
                    "A avaliação é obrigatória."
            );
        }

        if (avaliacao.getConteudo().getIdConteudo()
                != idConteudo) {

            throw new IllegalArgumentException(
                    "A avaliação pertence a outro conteúdo."
            );
        }

        if (buscarAvaliacao(
                avaliacao.getIdAvaliacao()) != null) {

            throw new IllegalArgumentException(
                    "Já existe uma avaliação com o ID "
                            + avaliacao.getIdAvaliacao()
                            + "."
            );
        }

        avaliacoes.add(avaliacao);
    }

    public Avaliacao buscarAvaliacao(
            int idAvaliacao) {

        for (Avaliacao avaliacao : avaliacoes) {
            if (avaliacao.getIdAvaliacao()
                    == idAvaliacao) {

                return avaliacao;
            }
        }

        return null;
    }

    @Override
    public List<Avaliacao> listarAvaliacoes() {
        return new ArrayList<>(avaliacoes);
    }

    public double calcularMediaAvaliacoes() {
        if (avaliacoes.isEmpty()) {
            return 0.0;
        }

        double soma = 0.0;

        for (Avaliacao avaliacao : avaliacoes) {
            soma += avaliacao.getNota();
        }

        return soma / avaliacoes.size();
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

    public void removerCategoria(
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
                return;
            }
        }
    }

    public List<CategoriaEtiqueta> getCategorias() {
        return new ArrayList<>(categorias);
    }

    /*
     * Atores
     */

    public void adicionarAtor(Ator ator) {
        if (ator == null) {
            throw new IllegalArgumentException(
                    "O ator é obrigatório."
            );
        }

        for (Ator item : atores) {
            if (item.getIdPessoa()
                    == ator.getIdPessoa()) {

                return;
            }
        }

        atores.add(ator);
    }

    public boolean removerAtor(Ator ator) {
        if (ator == null) {
            throw new IllegalArgumentException(
                    "O ator é obrigatório."
            );
        }

        for (int i = 0; i < atores.size(); i++) {
            Ator item = atores.get(i);

            if (item.getIdPessoa()
                    == ator.getIdPessoa()) {

                atores.remove(i);
                return true;
            }
        }

        return false;
    }

    public List<Ator> getAtores() {
        return new ArrayList<>(atores);
    }

    /*
     * Diretores
     */

    public void adicionarDiretor(
            Diretor diretor) {

        if (diretor == null) {
            throw new IllegalArgumentException(
                    "O diretor é obrigatório."
            );
        }

        for (Diretor item : diretores) {
            if (item.getIdPessoa()
                    == diretor.getIdPessoa()) {

                return;
            }
        }

        diretores.add(diretor);
    }

    public boolean removerDiretor(
            Diretor diretor) {

        if (diretor == null) {
            throw new IllegalArgumentException(
                    "O diretor é obrigatório."
            );
        }

        for (int i = 0; i < diretores.size(); i++) {
            Diretor item = diretores.get(i);

            if (item.getIdPessoa()
                    == diretor.getIdPessoa()) {

                diretores.remove(i);
                return true;
            }
        }

        return false;
    }

    public List<Diretor> getDiretores() {
        return new ArrayList<>(diretores);
    }

    /*
     * Disponibilidade regional
     */

    public void adicionarDisponibilidade(
            DisponibilidadeRegional disponibilidade) {

        if (disponibilidade == null) {
            throw new IllegalArgumentException(
                    "A disponibilidade regional é obrigatória."
            );
        }

        if (disponibilidade
                .getConteudo()
                .getIdConteudo() != idConteudo) {

            throw new IllegalArgumentException(
                    "A disponibilidade pertence a outro conteúdo."
            );
        }

        int idRegiao = disponibilidade
                .getRegiao()
                .getIdRegiao();

        for (DisponibilidadeRegional item
                : disponibilidades) {

            if (item.getRegiao()
                    .getIdRegiao() == idRegiao) {

                throw new IllegalArgumentException(
                        "Já existe uma disponibilidade "
                                + "cadastrada para essa região."
                );
            }
        }

        disponibilidades.add(disponibilidade);
    }

    public boolean removerDisponibilidade(
            DisponibilidadeRegional disponibilidade) {

        if (disponibilidade == null) {
            throw new IllegalArgumentException(
                    "A disponibilidade regional é obrigatória."
            );
        }

        for (int i = 0;
             i < disponibilidades.size();
             i++) {

            DisponibilidadeRegional item =
                    disponibilidades.get(i);

            if (item.getIdDisponibilidade()
                    == disponibilidade
                    .getIdDisponibilidade()) {

                disponibilidades.remove(i);
                return true;
            }
        }

        return false;
    }

    public List<DisponibilidadeRegional>
    getDisponibilidades() {

        return new ArrayList<>(disponibilidades);
    }

    public boolean verificarDisponibilidade(
            Regiao regiao) {

        if (regiao == null) {
            throw new IllegalArgumentException(
                    "A região é obrigatória."
            );
        }

        if (statusDisponibilidade
                != StatusDisponibilidade.DISPONIVEL) {

            return false;
        }

        for (DisponibilidadeRegional disponibilidade
                : disponibilidades) {

            boolean mesmaRegiao =
                    disponibilidade
                            .getRegiao()
                            .getIdRegiao()
                            == regiao.getIdRegiao();

            if (mesmaRegiao
                    && disponibilidade
                    .verificarDisponibilidade()) {

                return true;
            }
        }

        return false;
    }

    /*
     * Status geral
     */

    public StatusDisponibilidade
    getStatusDisponibilidade() {

        return statusDisponibilidade;
    }

    public void setStatusDisponibilidade(
            StatusDisponibilidade statusDisponibilidade) {

        if (statusDisponibilidade == null) {
            throw new IllegalArgumentException(
                    "O status de disponibilidade "
                            + "é obrigatório."
            );
        }

        this.statusDisponibilidade =
                statusDisponibilidade;
    }

    @Override
    public String toString() {
        return idConteudo
                + " - "
                + titulo
                + " - Classificação: "
                + classificacaoIndicativa
                + " anos";
    }
}