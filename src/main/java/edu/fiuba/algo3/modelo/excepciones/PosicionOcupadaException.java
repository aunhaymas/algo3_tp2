package edu.fiuba.algo3.modelo.excepciones;

public class PosicionOcupadaException extends RuntimeException {
    public PosicionOcupadaException(String message, Integer x, Integer y) {
        super(message);
    }
}
