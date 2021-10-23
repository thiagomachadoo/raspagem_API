package com.fazenda.raspagem.scrapper.entity;

public class all {
    //atributos
    private String autorizador;
    private String autorizacao;
    private String retornoAutorizacao;
    private String inutilizacao;

    //construtores


    public all(String autorizador, String autorizacao, String retornoAutorizacao, String inutilizacao) {
        this.autorizador = autorizador;
        this.autorizacao = autorizacao;
        this.retornoAutorizacao = retornoAutorizacao;
        this.inutilizacao = inutilizacao;

    }



    public String getAutorizador() {
        return autorizador;
    }

    public void setAutorizador(String autorizador) {
        this.autorizador = autorizador;
    }

    public String getAutorizacao() {
        return autorizacao;
    }

    public void setAutorizacao(String autorizacao) {
        this.autorizacao = autorizacao;
    }

    public String getRetornoAutorizacao() {
        return retornoAutorizacao;
    }

    public void setRetornoAutorizacao(String retornoAutorizacao) {
        this.retornoAutorizacao = retornoAutorizacao;
    }

    public String getInutilizacao() {
        return inutilizacao;
    }

    public void setInutilizacao(String inutilizacao) {
        this.inutilizacao = inutilizacao;
    }
}

