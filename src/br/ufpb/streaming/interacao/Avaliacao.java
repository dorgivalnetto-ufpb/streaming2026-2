package br.ufpb.streaming.interacao;

import br.ufpb.streaming.usuario.Perfil;

import java.time.LocalDate;

public class Avaliacao {

    private int idAvaliacao;
    //nota 1 a 5
    private int nota;
    private String comentario;
    private Perfil perfil;
    private LocalDate dataAvaliacao;

    public Avaliacao(int idAvaliacao, int nota, String comentario, Perfil perfil, LocalDate dataAvaliacao) {
        this.idAvaliacao = idAvaliacao;
        this.nota = nota;
        this.comentario = comentario;
        this.perfil = perfil;
        this.dataAvaliacao = dataAvaliacao;
    }

    realizarAvaliacao()

        alterarAvaliacao()
}
