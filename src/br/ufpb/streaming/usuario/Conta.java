package br.ufpb.streaming.usuario;

import java.util.ArrayList;
import java.util.List;

public class Conta {

    private int idConta;
    private String email;
    private String senha;
    private List<Perfil> perfis;

    public Conta(int idConta, String email, String senha) {
        this.idConta = idConta;
        this.email = email;
        this.senha = senha;
        this.perfis = new ArrayList<Perfil>();
    }

    public int getIdConta() {
        return idConta;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public void adicionarPerfil(Perfil perfil){
        if(perfil == null){
            throw new IllegalArgumentException("O perfil é obrigatório");
        }
        if(buscarPerfil(perfil.getIdPerfil()) != null){
            throw new IllegalArgumentException("Já existe um perfil com o ID " + perfil.getIdPerfil());
        }
        perfis.add(perfil);
    }

    public Perfil buscarPerfil(int idPerfil){
        for(Perfil perfil: perfis){
            if(perfil.getIdPerfil() == idPerfil){
                return perfil;
            }
        }
        return null;
    }

    public void removerPerfil(int idPerfil){
        Perfil perfil = buscarPerfil(idPerfil);

        if(perfil !=null) {
            perfis.remove(idPerfil);
        }
    }

    public List<Perfil> listarPerfis(){
        return perfis;
    }
}
