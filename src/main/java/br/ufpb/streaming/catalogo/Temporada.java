package br.ufpb.streaming.catalogo;

import java.util.ArrayList;
import java.util.List;

public class Temporada {

    private final int numero;
    private final String titulo;
    private final int anoLancamento;
    private final List<Episodio> episodios;

    public Temporada(
            int numero,
            String titulo,
            int anoLancamento) {

        if (numero <= 0) {
            throw new IllegalArgumentException(
                    "O número da temporada deve ser positivo."
            );
        }

        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException(
                    "O título da temporada é obrigatório."
            );
        }

        if (anoLancamento <= 0) {
            throw new IllegalArgumentException(
                    "O ano de lançamento deve ser positivo."
            );
        }

        this.numero = numero;
        this.titulo = titulo.trim();
        this.anoLancamento = anoLancamento;
        this.episodios = new ArrayList<>();
    }

    public void adicionarEpisodio(
            Episodio episodio) {

        if (episodio == null) {
            throw new IllegalArgumentException(
                    "O episódio é obrigatório."
            );
        }

        if (buscarEpisodio(
                episodio.getNumero()) != null) {

            throw new IllegalArgumentException(
                    "Já existe um episódio com o número "
                            + episodio.getNumero()
                            + "."
            );
        }

        episodios.add(episodio);
    }

    public Episodio buscarEpisodio(
            int numero) {

        if (numero <= 0) {
            throw new IllegalArgumentException(
                    "O número do episódio deve ser positivo."
            );
        }

        for (Episodio episodio : episodios) {
            if (episodio.getNumero() == numero) {
                return episodio;
            }
        }

        return null;
    }

    public boolean removerEpisodio(
            Episodio episodio) {

        if (episodio == null) {
            throw new IllegalArgumentException(
                    "O episódio é obrigatório."
            );
        }

        Episodio episodioEncontrado =
                buscarEpisodio(
                        episodio.getNumero()
                );

        if (episodioEncontrado == null) {
            return false;
        }

        episodios.remove(
                episodioEncontrado
        );

        return true;
    }

    public List<Episodio> obterEpisodios() {
        return new ArrayList<>(episodios);
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

    @Override
    public String toString() {
        return "Temporada "
                + numero
                + " - "
                + titulo;
    }
}