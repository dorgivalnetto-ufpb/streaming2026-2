package br.ufpb.streaming.catalogo;

import br.ufpb.streaming.enums.StatusSerie;

import java.util.ArrayList;
import java.util.List;

public class Serie extends Conteudo {

    private final String criador;
    private final int anoEstreia;
    private StatusSerie status;
    private Integer anoEncerramento;

    private final List<Temporada> temporadas;

    public Serie(
            int idConteudo,
            String titulo,
            String descricao,
            int classificacaoIndicativa,
            String criador,
            int anoEstreia) {

        super(
                idConteudo,
                titulo,
                descricao,
                classificacaoIndicativa
        );

        if (criador == null || criador.isBlank()) {
            throw new IllegalArgumentException(
                    "O criador da série é obrigatório."
            );
        }

        if (anoEstreia <= 0) {
            throw new IllegalArgumentException(
                    "O ano de estreia deve ser positivo."
            );
        }

        this.criador = criador.trim();
        this.anoEstreia = anoEstreia;
        this.status = StatusSerie.EM_ANDAMENTO;
        this.anoEncerramento = null;
        this.temporadas = new ArrayList<>();
    }

    public void adicionarTemporada(
            Temporada temporada) {

        if (temporada == null) {
            throw new IllegalArgumentException(
                    "A temporada é obrigatória."
            );
        }

        if (buscarTemporada(
                temporada.getNumero()) != null) {

            throw new IllegalArgumentException(
                    "Já existe uma temporada com o número "
                            + temporada.getNumero()
                            + "."
            );
        }

        temporadas.add(temporada);
    }

    public boolean removerTemporada(
            Temporada temporada) {

        if (temporada == null) {
            throw new IllegalArgumentException(
                    "A temporada é obrigatória."
            );
        }

        Temporada temporadaEncontrada =
                buscarTemporada(
                        temporada.getNumero()
                );

        if (temporadaEncontrada == null) {
            return false;
        }

        temporadas.remove(
                temporadaEncontrada
        );

        return true;
    }

    public Temporada buscarTemporada(
            int numero) {

        if (numero <= 0) {
            throw new IllegalArgumentException(
                    "O número da temporada deve ser positivo."
            );
        }

        for (Temporada temporada : temporadas) {
            if (temporada.getNumero() == numero) {
                return temporada;
            }
        }

        return null;
    }

    public List<Temporada> obterTemporadas() {
        return new ArrayList<>(temporadas);
    }

    public void setStatus(StatusSerie status) {
        if (status == null) {
            throw new IllegalArgumentException(
                    "O status da série é obrigatório."
            );
        }

        this.status = status;
    }

    public void finalizar(int anoEncerramento) {
        if (anoEncerramento < anoEstreia) {
            throw new IllegalArgumentException(
                    "O encerramento não pode ser "
                            + "anterior à estreia."
            );
        }

        this.anoEncerramento =
                anoEncerramento;

        this.status =
                StatusSerie.FINALIZADA;
    }

    public String getCriador() {
        return criador;
    }

    public int getAnoEstreia() {
        return anoEstreia;
    }

    public StatusSerie getStatus() {
        return status;
    }

    public Integer getAnoEncerramento() {
        return anoEncerramento;
    }

    @Override
    public String toString() {
        return getTitulo()
                + " ("
                + anoEstreia
                + ") - "
                + status;
    }
}