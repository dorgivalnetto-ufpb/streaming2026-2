package br.ufpb.streaming.catalogo;

public abstract class Conteudo {

    private final int idConteudo;
    private String titulo;
    private String descricao;
    private int classificacaoIndicativa;

    public Conteudo(int idConteudo, String titulo, String descricao, int classificacaoIndicativa){
        if(idConteudo <=0){
            throw new IllegalArgumentException("O ID do conteúdo deve ser maio que zero!");
        }

        if(titulo == null || titulo.isBlank()){
            throw new IllegalArgumentException("O título é obrigatório");
        }

        if (classificacaoIndicativa <0 || classificacaoIndicativa > 18){
            throw new IllegalArgumentException("A classificação deve estar entre 0 e 18.");
        }

        this.idConteudo = idConteudo;
        this.titulo = titulo;
        this.descricao = descricao;
        this.classificacaoIndicativa = classificacaoIndicativa;
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

    @Override
    public String toString() {
        return idConteudo + " - " + titulo + " - " + descricao + "Classificação indicativa " + classificacaoIndicativa;
    }
}
