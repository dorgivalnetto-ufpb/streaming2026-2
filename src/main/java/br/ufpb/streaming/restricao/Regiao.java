package br.ufpb.streaming.restricao;

public class Regiao {

    private final int idRegiao;
    private final String nome;
    private final String pais;

    public Regiao(
            int idRegiao,
            String nome,
            String pais) {

        if (idRegiao <= 0) {
            throw new IllegalArgumentException(
                    "O ID da região deve ser positivo."
            );
        }

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException(
                    "O nome da região é obrigatório."
            );
        }

        if (pais == null || pais.isBlank()) {
            throw new IllegalArgumentException(
                    "O país da região é obrigatório."
            );
        }

        this.idRegiao = idRegiao;
        this.nome = nome.trim();
        this.pais = pais.trim();
    }

    public int getIdRegiao() {
        return idRegiao;
    }

    public String getNome() {
        return nome;
    }

    public String getPais() {
        return pais;
    }

    @Override
    public String toString() {
        return nome + " - " + pais;
    }
}