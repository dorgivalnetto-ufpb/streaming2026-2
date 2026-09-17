package br.ufpb.streaming.catalogo;

import java.util.ArrayList;
import java.util.List;

public class Temporada{

    private int numero;
    private String titulo;
    private int anoLancamento;
    private int duracao;

    private List<Episodio> episodios;


    public Temporada(int numero, String titulo, int anoLancamento, int duracao){
        //validações

        this.numero = numero;
        this.titulo = titulo;
        this.anoLancamento = anoLancamento;
        this.duracao = duracao;
        this.episodios = new ArrayList<Episodio>();
    }

    public void adicionarEpisodios(Episodio episodio){
        episodios.add(episodio);
    }

    public Episodio buscarEpisodios(int numero){
        if(numero <0){
            throw new IllegalArgumentException("O n~umero do episodio deve ser maior que zero");
        }

        for(Episodio episodio : episodios){
            if (episodio.getNumero() == numero){
                return episodio;
            }
        }
        return null;
    }


    public int getNumero() {
        return numero;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getAnoLancamento() {
        return anoLancamento;
    }

    public int getDuracao() {
        return duracao;
    }
}
