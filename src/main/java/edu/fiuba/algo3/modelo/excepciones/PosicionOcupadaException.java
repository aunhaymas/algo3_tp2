package edu.fiuba.algo3.modelo.excepciones;

public class PosicionOcupadaException extends RuntimeException {
    public PosicionOcupadaException(int x, int y) {
        super(String.format("No se puede colocar la pieza, la posición (%d, %d) ya está ocupada.", x, y));
    }
}
