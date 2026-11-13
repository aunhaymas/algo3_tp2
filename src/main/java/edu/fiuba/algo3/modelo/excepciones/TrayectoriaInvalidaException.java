package edu.fiuba.algo3.modelo.excepciones;

public class TrayectoriaInvalidaException extends RuntimeException {
    public TrayectoriaInvalidaException() {
        super("Esta trayectoria no es ortogonal y no se puede recorrer");
    }
}
