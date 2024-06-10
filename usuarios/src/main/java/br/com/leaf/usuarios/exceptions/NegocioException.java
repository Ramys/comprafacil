package br.com.leaf.usuarios.exceptions;

public class NegocioException extends RuntimeException {

    public NegocioException(String message) {
        super(message);
    }
}
