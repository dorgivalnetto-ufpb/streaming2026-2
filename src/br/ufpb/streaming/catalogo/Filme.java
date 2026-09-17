package br.ufpb.streaming.catalogo;

import br.ufpb.streaming.interfaces.Reproduzivel;

public class Filme extends Conteudo implements Reproduzivel {

    private int duracao;
    private int anoLancamento;

    public Filme(int duracao, int anoLancamento, int idConteudo, String titulo, String descricao, int classficacaoIndicativa){
        super(idConteudo, titulo, descricao, classficacaoIndicativa);

        //validações

        this.duracao = duracao;
        this.anoLancamento = anoLancamento;
    }

    public void reproduzir(){
        System.out.println("Reproduzindo o filme: " + getTitulo());
    }

    public int obterDuracao() {
        return duracao;
    }

    public int getAnoLancamento() {
        return anoLancamento;
    }

    @Override
    public String toString() {
        return "Filme:" +
                "duracao=" + duracao +
                ", anoLancamento=" + anoLancamento;
    }
}
