package br.ufpb.streaming.pessoa;

public abstract class Pessoa {

    private final int idPessoa;
    private String nome;

    public Pessoa(int idPessoa, String nome) {
        if (idPessoa <= 0) {
            throw new IllegalArgumentException(
                    "O ID da pessoa deve ser positivo."
            );
        }

        setNome(nome);
        this.idPessoa = idPessoa;
    }

    public int getIdPessoa() {
        return idPessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException(
                    "O nome da pessoa é obrigatório."
            );
        }

        this.nome = nome.trim();
    }

    @Override
    public String toString() {
        return nome;
    }
}
