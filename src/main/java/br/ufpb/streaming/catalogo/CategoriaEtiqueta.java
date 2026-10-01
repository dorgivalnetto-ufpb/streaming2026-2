package br.ufpb.streaming.catalogo;

import br.ufpb.streaming.enums.TipoCategoria;

public class CategoriaEtiqueta {

    private final int idCategoria;
    private String nome;
    private TipoCategoria tipo;

    public CategoriaEtiqueta(
            int idCategoria,
            String nome,
            TipoCategoria tipo) {

        if (idCategoria <= 0) {
            throw new IllegalArgumentException(
                    "O ID da categoria deve ser positivo."
            );
        }

        this.idCategoria = idCategoria;

        setNome(nome);
        setTipo(tipo);
    }

    public void classificar(
            Conteudo conteudo) {

        if (conteudo == null) {
            throw new IllegalArgumentException(
                    "O conteúdo é obrigatório."
            );
        }

        conteudo.adicionarCategoria(this);
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException(
                    "O nome da categoria é obrigatório."
            );
        }

        this.nome = nome.trim();
    }

    public TipoCategoria getTipo() {
        return tipo;
    }

    public void setTipo(TipoCategoria tipo) {
        if (tipo == null) {
            throw new IllegalArgumentException(
                    "O tipo da categoria é obrigatório."
            );
        }

        this.tipo = tipo;
    }

    @Override
    public boolean equals(Object objeto) {
        if (this == objeto) {
            return true;
        }

        if (!(objeto instanceof CategoriaEtiqueta)) {
            return false;
        }

        CategoriaEtiqueta outraCategoria =
                (CategoriaEtiqueta) objeto;

        return idCategoria
                == outraCategoria.idCategoria;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(idCategoria);
    }

    @Override
    public String toString() {
        return nome + " (" + tipo + ")";
    }
}