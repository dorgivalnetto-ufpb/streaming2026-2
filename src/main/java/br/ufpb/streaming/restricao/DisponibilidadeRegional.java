package br.ufpb.streaming.restricao;

import br.ufpb.streaming.catalogo.Conteudo;

import java.time.LocalDate;

public class DisponibilidadeRegional {

    private final int idDisponibilidade;
    private final Conteudo conteudo;
    private final Regiao regiao;

    private boolean disponivel;
    private LocalDate dataInicioDisponibilidade;
    private LocalDate dataFimDisponibilidade;
    private String motivo;

    public DisponibilidadeRegional(
            int idDisponibilidade,
            Conteudo conteudo,
            Regiao regiao,
            LocalDate dataInicioDisponibilidade,
            LocalDate dataFimDisponibilidade) {

        if (idDisponibilidade <= 0) {
            throw new IllegalArgumentException(
                    "O ID da disponibilidade deve ser positivo."
            );
        }

        if (conteudo == null) {
            throw new IllegalArgumentException(
                    "O conteúdo é obrigatório."
            );
        }

        if (regiao == null) {
            throw new IllegalArgumentException(
                    "A região é obrigatória."
            );
        }

        validarPeriodo(
                dataInicioDisponibilidade,
                dataFimDisponibilidade
        );

        this.idDisponibilidade =
                idDisponibilidade;

        this.conteudo = conteudo;
        this.regiao = regiao;

        this.dataInicioDisponibilidade =
                dataInicioDisponibilidade;

        this.dataFimDisponibilidade =
                dataFimDisponibilidade;

        this.disponivel = true;
        this.motivo = "";
    }

    public boolean verificarDisponibilidade() {
        LocalDate dataAtual =
                LocalDate.now();

        if (!disponivel) {
            return false;
        }

        if (dataAtual.isBefore(
                dataInicioDisponibilidade)) {

            return false;
        }

        if (dataFimDisponibilidade != null
                && dataAtual.isAfter(
                dataFimDisponibilidade)) {

            return false;
        }

        return true;
    }

    public void tornarIndisponivel(
            String motivo) {

        if (motivo == null
                || motivo.isBlank()) {

            throw new IllegalArgumentException(
                    "O motivo da indisponibilidade é obrigatório."
            );
        }

        this.disponivel = false;
        this.motivo = motivo.trim();
    }

    public void tornarDisponivel(
            LocalDate dataFimDisponibilidade) {

        LocalDate dataInicio =
                LocalDate.now();

        validarPeriodo(
                dataInicio,
                dataFimDisponibilidade
        );

        this.dataInicioDisponibilidade =
                dataInicio;

        this.dataFimDisponibilidade =
                dataFimDisponibilidade;

        this.disponivel = true;
        this.motivo = "";
    }

    private void validarPeriodo(
            LocalDate dataInicio,
            LocalDate dataFim) {

        if (dataInicio == null) {
            throw new IllegalArgumentException(
                    "A data inicial é obrigatória."
            );
        }

        if (dataFim != null
                && dataFim.isBefore(dataInicio)) {

            throw new IllegalArgumentException(
                    "A data final não pode ser anterior à data inicial."
            );
        }
    }

    public int getIdDisponibilidade() {
        return idDisponibilidade;
    }

    public Conteudo getConteudo() {
        return conteudo;
    }

    public Regiao getRegiao() {
        return regiao;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public LocalDate getDataInicioDisponibilidade() {
        return dataInicioDisponibilidade;
    }

    public LocalDate getDataFimDisponibilidade() {
        return dataFimDisponibilidade;
    }

    public String getMotivo() {
        return motivo;
    }

    @Override
    public String toString() {
        String status;

        if (verificarDisponibilidade()) {
            status = "Disponível";
        } else {
            status = "Indisponível";
        }

        return conteudo.getTitulo()
                + " - "
                + regiao
                + " - "
                + status;
    }
}