package br.ufpb.streaming.pessoa;

public class Diretor extends Pessoa {

    public Diretor(int idPessoa, String nome) {
        super(idPessoa, nome);
    }

    @Override
    public String toString() {
        return "Diretor: " + getNome();
    }
}
