package br.ufpb.streaming.interacao;

public class Dispositivo {

    private final int idDispositivo;
    private final String tipo;
    private final String nome;

    public Dispositivo(
            int idDispositivo,
            String tipo,
            String nome) {

        if (idDispositivo <= 0) {
            throw new IllegalArgumentException(
                    "O ID do dispositivo deve ser positivo."
            );
        }

        if (tipo == null || tipo.isBlank()) {
            throw new IllegalArgumentException(
                    "O tipo do dispositivo é obrigatório."
            );
        }

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException(
                    "O nome do dispositivo é obrigatório."
            );
        }

        this.idDispositivo =
                idDispositivo;

        this.tipo = tipo.trim();
        this.nome = nome.trim();
    }

    public int getIdDispositivo() {
        return idDispositivo;
    }

    public String getTipo() {
        return tipo;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return nome + " (" + tipo + ")";
    }
}