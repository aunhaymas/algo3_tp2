package edu.fiuba.algo3.modelo.excepciones;

public class ArchivoIlegibleException extends RuntimeException {
    public ArchivoIlegibleException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
    
}
