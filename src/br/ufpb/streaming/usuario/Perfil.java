package br.ufpb.streaming.usuario;

import br.ufpb.streaming.catalogo.Conteudo;
import br.ufpb.streaming.enums.TipoPerfil;
import br.ufpb.streaming.interacao.MinhaLista;

import java.util.List;

public class Perfil {

    private int idPerfil;
    private String nome;
    private TipoPerfil tipo;
    private MinhaLista minhaLista;

    public Perfil(int idPerfil, String nome, TipoPerfil tipo){
        this.idPerfil = idPerfil;
        this.nome = nome;
        this.tipo = tipo;
        this.minhaLista = new MinhaLista(idPerfil);
    }

    public int getIdPerfil() {
        return idPerfil;
    }

    public TipoPerfil getTipo(){
        return tipo;
    }

    public MinhaLista getMinhaLista(){
        return minhaLista;
    }

    public String getNome(){
        return nome;
    }

    public void adicionarNaMinhaLista(Conteudo conteudo){
        minhaLista.adicionarConteudo(conteudo);
    }

    public void removerDaMinhaLista(int idConteudo){
        minhaLista.removerConteudo(idConteudo);
    }

    public List<Conteudo> listarMinhaLista(){
        return minhaLista.listarConteudos();
    }

    @Override
    public String toString() {
        return "Perfil{" +
                "nome='" + nome + '\'' +
                ", tipo=" + tipo +
                ", idPerfil=" + idPerfil +
                '}';
    }
}