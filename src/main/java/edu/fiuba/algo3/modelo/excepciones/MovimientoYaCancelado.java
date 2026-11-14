package edu.fiuba.algo3.modelo.excepciones;

public class MovimientoYaCancelado extends RuntimeException {
    public MovimientoYaCancelado(String message) {
        super(message);
    }
}
