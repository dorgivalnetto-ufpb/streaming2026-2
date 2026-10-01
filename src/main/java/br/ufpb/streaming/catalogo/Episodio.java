package br.ufpb.streaming.catalogo;

import br.ufpb.streaming.interfaces.Reproduzivel;

import java.time.LocalDate;

public class Episodio implements Reproduzivel {

    private final int numero;
    private final String titulo;
    private final int duracao;
    private final String sinopse;
    private final LocalDate dataLancamento;

    public Episodio(
            int numero,
            String titulo,
            int duracao,
            String sinopse,
            LocalDate dataLancamento) {

        if (numero <= 0) {
            throw new IllegalArgumentException(
                    "O número do episódio deve ser positivo."
            );
        }

        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException(
                    "O título do episódio é obrigatório."
            );
        }

        if (duracao <= 0) {
            throw new IllegalArgumentException(
                    "A duração deve ser maior que zero."
            );
        }

        if (dataLancamento == null) {
            throw new IllegalArgumentException(
                    "A data de lançamento é obrigatória."
            );
        }

        this.numero = numero;
        this.titulo = titulo.trim();
        this.duracao = duracao;

        if (sinopse == null) {
            this.sinopse = "";
        } else {
            this.sinopse = sinopse.trim();
        }

        this.dataLancamento = dataLancamento;
    }

    @Override
    public void reproduzir() {
        System.out.println(
                "Reproduzindo episódio: " + titulo
        );
    }

    @Override
    public int obterDuracao() {
        return duracao;
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
        return "Episódio "
                + numero
                + " - "
                + titulo;
    }
}