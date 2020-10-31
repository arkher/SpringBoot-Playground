package br.com.ithappens.model.enums;

public enum TipoPedido {
    ENTRADA("ENTRADA"),
    SAIDA("SAIDA");

    private String operacao;
    TipoPedido(String operacao){
        this.operacao = operacao;
    }
}