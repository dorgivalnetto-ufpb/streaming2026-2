package br.ufpb.streaming.catalogo;

import java.util.ArrayList;
import java.util.List;

public class Catalogo {

    private List<Conteudo> conteudos;

    public Catalogo(){
        this.conteudos = new ArrayList<Conteudo>();
    }

    public void adicionarConteudo(Conteudo conteudo){
        if(conteudo == null){
            throw new IllegalArgumentException("O conteúdo não pode ser nulo");
        }

        if (buscarPorId(conteudo.getIdConteudo()) != null){
            throw new IllegalArgumentException("Já existe um conteúdo com o ID " + conteudo.getIdConteudo());
        }

        conteudos.add(conteudo);
    }

    public Conteudo buscarPorId(int idConteudo){
        for( Conteudo conteudo: conteudos){
            if(conteudo.getIdConteudo() == idConteudo){
                return conteudo;
            }
        }
        return null;
    }

    public List<Conteudo> pesquisarPorTitulo(String titulo){
        List<Conteudo> resultados = new ArrayList<>();
        for (Conteudo conteudo :conteudos){
            if(conteudo.getTitulo().toLowerCase().contains(titulo.toLowerCase())){
                resultados.add(conteudo);
                //"Viagem Interestelar".contains("Viagem"); //true
                //"Viagem Interestelar".contains("viagem"); //false
            }
        }
        return resultados;
    }

    public List<Conteudo> listarConteudos(){
        return conteudos;
    }

    public int getIdConteudo(){
        return conteudos.size();
    }

}
