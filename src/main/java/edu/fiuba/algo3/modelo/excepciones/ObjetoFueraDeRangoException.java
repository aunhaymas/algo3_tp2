package edu.fiuba.algo3.modelo.excepciones;

public class ObjetoFueraDeRangoException extends RuntimeException {
    public ObjetoFueraDeRangoException() {
        super("Objeto fuera de rango!");
    }
}
