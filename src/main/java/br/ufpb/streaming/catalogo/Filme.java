package br.ufpb.streaming.catalogo;

import br.ufpb.streaming.interfaces.Reproduzivel;

public class Filme
        extends Conteudo
        implements Reproduzivel {

    private final int duracaoTotal;
    private final int anoLancamento;
    private final String estudio;
    private final String idiomaOriginal;

    public Filme(
            int idConteudo,
            String titulo,
            String descricao,
            int classificacaoIndicativa,
            int duracaoTotal,
            int anoLancamento,
            String estudio,
            String idiomaOriginal) {

        super(
                idConteudo,
                titulo,
                descricao,
                classificacaoIndicativa
        );

        if (duracaoTotal <= 0) {
            throw new IllegalArgumentException(
                    "A duração deve ser maior que zero."
            );
        }

        if (anoLancamento <= 0) {
            throw new IllegalArgumentException(
                    "O ano de lançamento deve ser positivo."
            );
        }

        if (estudio == null || estudio.isBlank()) {
            throw new IllegalArgumentException(
                    "O estúdio é obrigatório."
            );
        }

        if (idiomaOriginal == null
                || idiomaOriginal.isBlank()) {

            throw new IllegalArgumentException(
                    "O idioma original é obrigatório."
            );
        }

        this.duracaoTotal = duracaoTotal;
        this.anoLancamento = anoLancamento;
        this.estudio = estudio.trim();
        this.idiomaOriginal =
                idiomaOriginal.trim();
    }

    @Override
    public void reproduzir() {
        System.out.println(
                "Reproduzindo o filme: "
                        + getTitulo()
        );
    }

    @Override
    public int obterDuracao() {
        return duracaoTotal;
    }

    public int getDuracaoTotal() {
        return duracaoTotal;
    }

    public int getAnoLancamento() {
        return anoLancamento;
    }

    public String getEstudio() {
        return estudio;
    }

    public String getIdiomaOriginal() {
        return idiomaOriginal;
    }

    @Override
    public String toString() {
        return getTitulo()
                + " ("
                + anoLancamento
                + ") - "
                + duracaoTotal
                + " minutos";
    }
}