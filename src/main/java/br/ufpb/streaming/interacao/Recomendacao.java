package br.ufpb.streaming.interacao;

import br.ufpb.streaming.catalogo.Catalogo;
import br.ufpb.streaming.catalogo.Conteudo;
import br.ufpb.streaming.usuario.Perfil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Recomendacao {

    private final int idRecomendacao;
    private final Perfil perfil;
    private LocalDateTime dataGeracao;

    private final List<Conteudo>
            conteudosSugeridos;

    public Recomendacao(
            int idRecomendacao,
            Perfil perfil) {

        if (idRecomendacao <= 0) {
            throw new IllegalArgumentException(
                    "O ID da recomendação deve ser positivo."
            );
        }

        if (perfil == null) {
            throw new IllegalArgumentException(
                    "O perfil é obrigatório."
            );
        }

        this.idRecomendacao =
                idRecomendacao;

        this.perfil = perfil;

        this.conteudosSugeridos =
                new ArrayList<>();

        this.dataGeracao =
                LocalDateTime.now();
    }

    public void adicionarConteudo(
            Conteudo conteudo) {

        if (conteudo == null) {
            throw new IllegalArgumentException(
                    "O conteúdo é obrigatório."
            );
        }

        if (perfil.getMinhaLista().contem(
                conteudo.getIdConteudo())) {

            throw new IllegalArgumentException(
                    "O conteúdo já está na Minha Lista do perfil."
            );
        }

        if (contem(conteudo)) {
            throw new IllegalArgumentException(
                    "O conteúdo já foi recomendado."
            );
        }

        conteudosSugeridos.add(conteudo);
    }

    public boolean contem(
            Conteudo conteudo) {

        if (conteudo == null) {
            return false;
        }

        for (Conteudo sugerido
                : conteudosSugeridos) {

            if (sugerido.getIdConteudo()
                    == conteudo.getIdConteudo()) {

                return true;
            }
        }

        return false;
    }

    public void removerConteudo(
            int idConteudo) {

        for (int i = 0;
             i < conteudosSugeridos.size();
             i++) {

            Conteudo conteudo =
                    conteudosSugeridos.get(i);

            if (conteudo.getIdConteudo()
                    == idConteudo) {

                conteudosSugeridos.remove(i);
                return;
            }
        }
    }

    public void gerarSugestoes(
            Catalogo catalogo) {

        if (catalogo == null) {
            throw new IllegalArgumentException(
                    "O catálogo é obrigatório."
            );
        }

        conteudosSugeridos.clear();

        for (Conteudo conteudo
                : catalogo.listarConteudos()) {

            boolean estaNaMinhaLista =
                    perfil.getMinhaLista().contem(
                            conteudo.getIdConteudo()
                    );

            if (!estaNaMinhaLista) {
                adicionarConteudo(conteudo);
            }
        }

        dataGeracao =
                LocalDateTime.now();
    }

    public List<Conteudo> listarConteudos() {
        return new ArrayList<>(
                conteudosSugeridos
        );
    }

    public int getIdRecomendacao() {
        return idRecomendacao;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public LocalDateTime getDataGeracao() {
        return dataGeracao;
    }
}