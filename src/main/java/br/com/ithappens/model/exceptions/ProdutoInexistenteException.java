package br.com.ithappens.model.exceptions;

public class ProdutoInexistenteException extends RuntimeException {
    public ProdutoInexistenteException(String msg){ super(msg); }
}
