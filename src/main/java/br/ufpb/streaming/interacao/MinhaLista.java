package br.ufpb.streaming.interacao;

import br.ufpb.streaming.catalogo.Conteudo;

import java.util.ArrayList;
import java.util.List;

public class MinhaLista {

    private final int idLista;
    private final List<Conteudo> conteudos;

    public MinhaLista(int idLista) {
        if (idLista <= 0) {
            throw new IllegalArgumentException(
                    "O ID da lista deve ser positivo."
            );
        }

        this.idLista = idLista;
        this.conteudos = new ArrayList<>();
    }

    public int getIdLista() {
        return idLista;
    }

    public void adicionarConteudo(Conteudo conteudo) {
        if (conteudo == null) {
            throw new IllegalArgumentException(
                    "O conteúdo é obrigatório."
            );
        }

        if (buscarConteudo(conteudo.getIdConteudo()) != null) {
            throw new IllegalArgumentException(
                    "O conteúdo já existe na lista."
            );
        }

        conteudos.add(conteudo);
    }

    public Conteudo buscarConteudo(int idConteudo) {
        for (Conteudo conteudo : conteudos) {
            if (conteudo.getIdConteudo() == idConteudo) {
                return conteudo;
            }
        }

        return null;
    }

    public void removerConteudo(int idConteudo) {
        Conteudo conteudo = buscarConteudo(idConteudo);

        if (conteudo != null) {
            conteudos.remove(conteudo);
        }
    }

    public boolean contem(int idConteudo) {
        return buscarConteudo(idConteudo) != null;
    }

    public List<Conteudo> listarConteudos() {
        return new ArrayList<>(conteudos);
    }
}