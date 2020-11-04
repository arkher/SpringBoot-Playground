package br.com.ithappens.model.exceptions;

public class FilialNaoEncontradaException extends RuntimeException {
    public FilialNaoEncontradaException(String msg){
        super(msg);
    }
}
