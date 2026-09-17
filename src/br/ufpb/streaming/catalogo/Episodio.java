package br.ufpb.streaming.catalogo;

import br.ufpb.streaming.interfaces.Reproduzivel;

import java.time.LocalDate;

public class Episodio implements Reproduzivel {

    private int numero;
    private String titulo;
    private int duracao;
    private String sinopse;
    private LocalDate dataLancamento;

    public Episodio(int numero, String titulo, int duracao, String sinopse, LocalDate dataLancamento) {
        //validações

        this.numero = numero;
        this.titulo = titulo;
        this.duracao = duracao;
        this.sinopse = sinopse;
        this.dataLancamento = dataLancamento;
    }

    public int getNumero() {
        return numero;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getDuracao() {
        return duracao;
    }

    public String getSinopse() {
        return sinopse;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    @Override
    public String toString() {
        return "Episodio{" +
                "numero=" + numero +
                ", titulo='" + titulo + '\'' +
                ", duracao=" + duracao +
                ", sinopse='" + sinopse + '\'' +
                ", dataLancamento=" + dataLancamento +
                '}';
    }

    @Override
    public void reproduzir() {
        System.out.println("Reproduzindo o episódio: " + titulo);
    }

    @Override
    public int obterDuracao() {
        return duracao;
    }
}
