package br.ufpb.streaming.restricao;

import br.ufpb.streaming.catalogo.Conteudo;
import br.ufpb.streaming.enums.TipoPerfil;
import br.ufpb.streaming.usuario.Perfil;

import java.util.ArrayList;
import java.util.List;

public class ControleParental {

    private final int idControle;
    private int classificacaoMaxima;
    private final List<BloqueioTitulo> bloqueios;
    private int proximoIdBloqueio;

    public ControleParental(
            int idControle,
            int classificacaoMaxima) {

        if (idControle <= 0) {
            throw new IllegalArgumentException(
                    "O ID do controle parental deve ser positivo."
            );
        }

        if (classificacaoMaxima < 0) {
            throw new IllegalArgumentException(
                    "A classificação máxima não pode ser negativa."
            );
        }

        this.idControle = idControle;
        this.classificacaoMaxima =
                classificacaoMaxima;

        this.bloqueios = new ArrayList<>();
        this.proximoIdBloqueio = 1;
    }

    public boolean verificarClassificacao(
            Perfil perfil,
            Conteudo conteudo) {

        validarPerfilEConteudo(
                perfil,
                conteudo
        );

        /*
         * Nesta versão, apenas perfis Kids
         * possuem restrições.
         */
        if (perfil.getTipo() != TipoPerfil.KIDS) {
            return true;
        }

        if (estaBloqueado(
                perfil,
                conteudo)) {

            return false;
        }

        return conteudo
                .getClassificacaoIndicativa()
                <= classificacaoMaxima;

        //Portanto, um perfil Kids só pode assistir quando:
        //o título não está bloqueado
        //E
        //a classificação do conteúdo é adequada
    }

    public void bloquearTitulo(
            Perfil perfil,
            Conteudo conteudo) {

        validarPerfilEConteudo(
                perfil,
                conteudo
        );

        if (estaBloqueado(
                perfil,
                conteudo)) {

            throw new IllegalArgumentException(
                    "O título já está bloqueado para este perfil."
            );
        }

        BloqueioTitulo bloqueio =
                new BloqueioTitulo(
                        proximoIdBloqueio,
                        perfil,
                        conteudo
                );

        proximoIdBloqueio++;

        bloqueios.add(bloqueio);
    }

    public void permitirTitulo(
            Perfil perfil,
            Conteudo conteudo) {

        validarPerfilEConteudo(
                perfil,
                conteudo
        );

        for (int i = 0;
             i < bloqueios.size();
             i++) {

            BloqueioTitulo bloqueio =
                    bloqueios.get(i);

            if (pertenceAoPerfilEConteudo(
                    bloqueio,
                    perfil,
                    conteudo)) {

                bloqueios.remove(i);
                return;
            }
        }
    }

    public boolean estaBloqueado(
            Perfil perfil,
            Conteudo conteudo) {

        validarPerfilEConteudo(
                perfil,
                conteudo
        );

        for (BloqueioTitulo bloqueio
                : bloqueios) {

            if (pertenceAoPerfilEConteudo(
                    bloqueio,
                    perfil,
                    conteudo)
                    && bloqueio.isAtivo()) {

                return true;
            }
        }

        return false;
    }



    private boolean pertenceAoPerfilEConteudo(
            BloqueioTitulo bloqueio,
            Perfil perfil,
            Conteudo conteudo) {

        boolean mesmoPerfil =
                bloqueio.getPerfil()
                        .getIdPerfil()
                        == perfil.getIdPerfil();

        boolean mesmoConteudo =
                bloqueio.getConteudo()
                        .getIdConteudo()
                        == conteudo.getIdConteudo();

        return mesmoPerfil && mesmoConteudo;
    }

    private void validarPerfilEConteudo(
            Perfil perfil,
            Conteudo conteudo) {

        if (perfil == null) {
            throw new IllegalArgumentException(
                    "O perfil é obrigatório."
            );
        }

        if (conteudo == null) {
            throw new IllegalArgumentException(
                    "O conteúdo é obrigatório."
            );
        }
    }

    public List<BloqueioTitulo> listarBloqueios() {
        return new ArrayList<>(bloqueios);
    }

    public int getIdControle() {
        return idControle;
    }

    public int getClassificacaoMaxima() {
        return classificacaoMaxima;
    }
}