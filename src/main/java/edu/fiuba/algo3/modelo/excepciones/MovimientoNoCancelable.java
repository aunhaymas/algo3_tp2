package edu.fiuba.algo3.modelo.excepciones;

public class MovimientoNoCancelable extends RuntimeException {
    public MovimientoNoCancelable(String message) {
        super(message);
    }
}
