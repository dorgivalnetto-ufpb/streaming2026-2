package br.ufpb.streaming.restricao;

import br.ufpb.streaming.catalogo.Conteudo;
import br.ufpb.streaming.usuario.Perfil;

import java.time.LocalDateTime;

public class BloqueioTitulo {

    private final int idBloqueio;
    private final Perfil perfil;
    private final Conteudo conteudo;
    private final LocalDateTime dataBloqueio;
    private boolean ativo;

    public BloqueioTitulo(
            int idBloqueio,
            Perfil perfil,
            Conteudo conteudo) {

        if (idBloqueio <= 0) {
            throw new IllegalArgumentException(
                    "O ID do bloqueio deve ser positivo."
            );
        }

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

        this.idBloqueio = idBloqueio;
        this.perfil = perfil;
        this.conteudo = conteudo;
        this.dataBloqueio =
                LocalDateTime.now();

        this.ativo = true;
    }

    public int getIdBloqueio() {
        return idBloqueio;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public Conteudo getConteudo() {
        return conteudo;
    }

    public LocalDateTime getDataBloqueio() {
        return dataBloqueio;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void ativar() {
        this.ativo = true;
    }

    public void desativar() {
        this.ativo = false;
    }

    @Override
    public String toString() {
        String situacao;

        if (ativo) {
            situacao = "ativo";
        } else {
            situacao = "inativo";
        }

        return "Bloqueio de "
                + conteudo.getTitulo()
                + " para o perfil "
                + perfil.getNome()
                + " em "
                + dataBloqueio
                + " - "
                + situacao;
    }
}