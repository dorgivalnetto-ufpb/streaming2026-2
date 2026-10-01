package br.ufpb.streaming.interacao;

import br.ufpb.streaming.catalogo.Conteudo;
import br.ufpb.streaming.usuario.Perfil;

import java.time.LocalDate;

public class Avaliacao {

    private final int idAvaliacao;
    private final Perfil perfil;
    private final Conteudo conteudo;

    private int nota;
    private String comentario;
    private LocalDate dataAvaliacao;

    public Avaliacao(
            int idAvaliacao,
            Perfil perfil,
            Conteudo conteudo,
            int nota,
            String comentario) {

        if (idAvaliacao <= 0) {
            throw new IllegalArgumentException(
                    "O ID da avaliação deve ser positivo."
            );
        }

        if (perfil == null) {
            throw new IllegalArgumentException(
                    "O perfil é obrigatório."
            );
        }

        if (conteudo == null) {
            throw new IllegalArgumentException(
                    "O conteúdo é obrigatório."
            );
        }

        this.idAvaliacao = idAvaliacao;
        this.perfil = perfil;
        this.conteudo = conteudo;

        alterarAvaliacao(
                nota,
                comentario
        );
    }

    public void registrarAvaliacao() {
        conteudo.registrarAvaliacao(this);
    }

    public void alterarAvaliacao(
            int nota,
            String comentario) {

        if (nota < 1 || nota > 5) {
            throw new IllegalArgumentException(
                    "A nota deve estar entre 1 e 5."
            );
        }

        this.nota = nota;

        if (comentario == null) {
            this.comentario = "";
        } else {
            this.comentario =
                    comentario.trim();
        }

        this.dataAvaliacao =
                LocalDate.now();
    }

    public int getIdAvaliacao() {
        return idAvaliacao;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public Conteudo getConteudo() {
        return conteudo;
    }

    public int getNota() {
        return nota;
    }

    public String getComentario() {
        return comentario;
    }

    public LocalDate getDataAvaliacao() {
        return dataAvaliacao;
    }

    @Override
    public String toString() {
        return conteudo.getTitulo()
                + " - "
                + nota
                + " estrela(s): "
                + comentario;
    }
}