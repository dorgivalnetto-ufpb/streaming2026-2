package br.ufpb.streaming.usuario;

import br.ufpb.streaming.catalogo.Conteudo;
import br.ufpb.streaming.enums.TipoPerfil;
import br.ufpb.streaming.interacao.MinhaLista;

import java.util.List;

public class Perfil {

    private final int idPerfil;
    private String nome;
    private final TipoPerfil tipo;
    private final MinhaLista minhaLista;

    public Perfil(
            int idPerfil,
            String nome,
            TipoPerfil tipo) {

        if (idPerfil <= 0) {
            throw new IllegalArgumentException(
                    "O ID do perfil deve ser positivo."
            );
        }

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException(
                    "O nome do perfil é obrigatório."
            );
        }

        if (tipo == null) {
            throw new IllegalArgumentException(
                    "O tipo do perfil é obrigatório."
            );
        }

        this.idPerfil = idPerfil;
        this.nome = nome.trim();
        this.tipo = tipo;
        this.minhaLista = new MinhaLista(idPerfil);
    }

    public int getIdPerfil() {
        return idPerfil;
    }

    public String getNome() {
        return nome;
    }

    public TipoPerfil getTipo() {
        return tipo;
    }

    public MinhaLista getMinhaLista() {
        return minhaLista;
    }

    public void adicionarNaMinhaLista(
            Conteudo conteudo) {

        minhaLista.adicionarConteudo(conteudo);
    }

    public void removerDaMinhaLista(
            int idConteudo) {

        minhaLista.removerConteudo(idConteudo);
    }

    public List<Conteudo> listarMinhaLista() {
        return minhaLista.listarConteudos();
    }

    @Override
    public String toString() {
        return "Perfil: " + nome
                + " | Tipo: " + tipo;
    }
}