package br.ufpb.streaming.interacao;

import br.ufpb.streaming.catalogo.Conteudo;

import java.util.ArrayList;
import java.util.List;

public class MinhaLista {

    private int idLista;
    private List<Conteudo> conteudos;

    public MinhaLista(int idLista){
        this.idLista = idLista;
        this.conteudos = new ArrayList<Conteudo>();
    }

    public int getIdLista(){
        return idLista;
    }

    public void adicionarConteudo(Conteudo conteudo){
        if (conteudo == null){
            throw new IllegalArgumentException("O conteúdo é obrigatório.");
        }

       if(buscarConteudo(conteudo.getIdConteudo()) != null){
           throw new IllegalArgumentException("O conteúdo já existe na lista.");
       }

        conteudos.add(conteudo);
    }

    public Conteudo buscarConteudo(int idConteudo){
        for(Conteudo conteudo : conteudos){
            if(conteudo.getIdConteudo() == idConteudo){
                return conteudo;
            }
        }
        return null;
    }

    public void removerConteudo(int idConteudo){
        Conteudo conteudo = buscarConteudo(idConteudo);
        if(conteudo != null){
            conteudos.remove(conteudo);
        }
    }

    public List<Conteudo> listarConteudos(){
        return conteudos;
    }
}
