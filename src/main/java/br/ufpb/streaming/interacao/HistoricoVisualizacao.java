package br.ufpb.streaming.interacao;

import br.ufpb.streaming.catalogo.Conteudo;
import br.ufpb.streaming.usuario.Perfil;

import java.time.LocalDateTime;

public class HistoricoVisualizacao {

    private final int idHistorico;
    private final Perfil perfil;
    private final Conteudo conteudo;
    private final Dispositivo dispositivo;

    private LocalDateTime dataHora;
    private double progresso;
    private int tempoAssistido;

    public HistoricoVisualizacao(
            int idHistorico,
            Perfil perfil,
            Conteudo conteudo,
            Dispositivo dispositivo) {

        if (idHistorico <= 0) {
            throw new IllegalArgumentException(
                    "O ID do histórico deve ser positivo."
            );
        }

        if (perfil == null) {
            throw new IllegalArgumentException(
                    "O perfil é obrigatório."
            );
        }

        if (conteudo == null) {
            throw new IllegalArgumentException(
                    "O conteúdo é obrigatório."
            );
        }

        if (dispositivo == null) {
            throw new IllegalArgumentException(
                    "O dispositivo é obrigatório."
            );
        }

        this.idHistorico = idHistorico;
        this.perfil = perfil;
        this.conteudo = conteudo;
        this.dispositivo = dispositivo;

        this.progresso = 0.0;
        this.tempoAssistido = 0;
    }

    public void registrarVisualizacao() {
        this.dataHora =
                LocalDateTime.now();
    }

    public void atualizarProgresso(
            double progresso,
            int tempoAssistido) {

        if (progresso < 0.0
                || progresso > 100.0) {

            throw new IllegalArgumentException(
                    "O progresso deve estar entre 0 e 100."
            );
        }

        if (tempoAssistido < 0) {
            throw new IllegalArgumentException(
                    "O tempo assistido não pode ser negativo."
            );
        }

        this.progresso = progresso;
        this.tempoAssistido =
                tempoAssistido;

        this.dataHora =
                LocalDateTime.now();
    }

    public int getIdHistorico() {
        return idHistorico;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public Conteudo getConteudo() {
        return conteudo;
    }

    public Dispositivo getDispositivo() {
        return dispositivo;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public double getProgresso() {
        return progresso;
    }

    public int getTempoAssistido() {
        return tempoAssistido;
    }

    @Override
    public String toString() {
        return "Perfil: "
                + perfil.getNome()
                + "\nConteúdo: "
                + conteudo.getTitulo()
                + "\nProgresso: "
                + progresso
                + "%"
                + "\nTempo assistido: "
                + tempoAssistido
                + " minutos"
                + "\nDispositivo: "
                + dispositivo.getTipo();
    }
}