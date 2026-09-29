package br.ufpb.streaming.interfaces;

import br.ufpb.streaming.interacao.Avaliacao;

import java.util.List;

public interface Avaliavel {

    void registrarAvaliacao(Avaliacao avaliacao);

    List<Avaliacao> listarAvaliacoes();
}
