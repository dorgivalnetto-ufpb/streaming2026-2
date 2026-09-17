package br.ufpb.streaming.catalogo;

import java.util.ArrayList;
import java.util.List;

public class Serie extends Conteudo{

    private String criador;
    private int anoEstreia;
    private List<Temporada> temporadas;

    public Serie(int idConteudo, String titulo, String descricao, int classificacaoIndicativa, String criador, int anoEstreia) {
        super(idConteudo, titulo, descricao, classificacaoIndicativa);

        //validações

        this.criador = criador;
        this.anoEstreia = anoEstreia;
        this.temporadas = new ArrayList<Temporada>();
    }

    public void adicionarTemporada(Temporada temporada){
        temporadas.add(temporada);
    }

    public Temporada buscarTemporada(int numero){
        if(numero <=0){
            throw new IllegalArgumentException("O número da temporada deve ser maior que zero");
        }

        for(Temporada temporada : temporadas){
            if(temporada.getNumero() == numero){
                return temporada;
            }
        }
        return null;
    }

    public String getCriador() {
        return criador;
    }

    public int getAnoEstreia() {
        return anoEstreia;
    }

    @Override
    public String toString() {
        return "Serie{" +
                "criador='" + criador + '\'' +
                ", anoEstreia=" + anoEstreia +
                '}';
    }
}
