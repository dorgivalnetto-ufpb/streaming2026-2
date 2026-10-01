package br.ufpb.streaming.pessoa;

public class Ator extends Pessoa {

    public Ator(int idPessoa, String nome) {
        super(idPessoa, nome);
    }

    @Override
    public String toString() {
        return "Ator: " + getNome();
    }
}
