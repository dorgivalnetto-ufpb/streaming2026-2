package br.ufpb.streaming.aplicacao;

import br.ufpb.streaming.catalogo.Conteudo;
import br.ufpb.streaming.catalogo.Filme;

public class Main{
    static void main() {

        Conteudo filme1 = new Filme(
                144,
                2026,
                1,
                "Homem-Aranha: Um novo dia",
                "Quatro anos se passaram e Peter Paker atua como um herói em tempo integral em Nova Iorque",
                12);

        System.out.println(filme1.getTitulo());

    }
}