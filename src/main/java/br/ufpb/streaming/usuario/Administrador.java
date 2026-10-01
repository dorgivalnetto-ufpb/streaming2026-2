package br.ufpb.streaming.usuario;

import br.ufpb.streaming.catalogo.Catalogo;
import br.ufpb.streaming.catalogo.CategoriaEtiqueta;
import br.ufpb.streaming.catalogo.Conteudo;
import br.ufpb.streaming.restricao.DisponibilidadeRegional;

import java.time.LocalDate;

public class Administrador {

    private final int idAdministrador;
    private String nome;
    private String email;
    private final Catalogo catalogo;

    public Administrador(
            int idAdministrador,
            String nome,
            String email,
            Catalogo catalogo) {

        if (idAdministrador <= 0) {
            throw new IllegalArgumentException(
                    "O ID do administrador deve ser positivo."
            );
        }

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException(
                    "O nome do administrador é obrigatório."
            );
        }

        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException(
                    "O e-mail do administrador é obrigatório."
            );
        }

        if (catalogo == null) {
            throw new IllegalArgumentException(
                    "O catálogo é obrigatório."
            );
        }

        this.idAdministrador = idAdministrador;
        this.nome = nome.trim();
        this.email = email.trim();
        this.catalogo = catalogo;
    }

    public void cadastrarTitulo(
            Conteudo conteudo) {

        catalogo.adicionarConteudo(
                conteudo
        );
    }

    public void editarTitulo(
            Conteudo conteudo) {

        catalogo.editarConteudo(
                conteudo
        );
    }

    public boolean removerTitulo(
            Conteudo conteudo) {

        return catalogo.removerConteudo(
                conteudo
        );
    }

    public void cadastrarCategoria(
            CategoriaEtiqueta categoria) {

        catalogo.adicionarCategoria(
                categoria
        );
    }

    public boolean removerCategoria(
            CategoriaEtiqueta categoria) {

        return catalogo.removerCategoria(
                categoria
        );
    }

    public void classificarConteudo(
            Conteudo conteudo,
            CategoriaEtiqueta categoria) {

        if (conteudo == null) {
            throw new IllegalArgumentException(
                    "O conteúdo é obrigatório."
            );
        }

        if (categoria == null) {
            throw new IllegalArgumentException(
                    "A categoria é obrigatória."
            );
        }

        categoria.classificar(conteudo);
    }

    public void tornarTituloIndisponivel(
            DisponibilidadeRegional disponibilidade,
            String motivo) {

        if (disponibilidade == null) {
            throw new IllegalArgumentException(
                    "A disponibilidade regional é obrigatória."
            );
        }

        disponibilidade.tornarIndisponivel(
                motivo
        );
    }

    public void tornarTituloDisponivel(
            DisponibilidadeRegional disponibilidade,
            LocalDate dataFimDisponibilidade) {

        if (disponibilidade == null) {
            throw new IllegalArgumentException(
                    "A disponibilidade regional é obrigatória."
            );
        }

        disponibilidade.tornarDisponivel(
                dataFimDisponibilidade
        );
    }

    public int getIdAdministrador() {
        return idAdministrador;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public Catalogo getCatalogo() {
        return catalogo;
    }
}