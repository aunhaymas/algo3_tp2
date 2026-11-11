package edu.fiuba.algo3.modelo;

import java.util.List;

public class Trayectoria {

    private final List<Casilla> casillas;
    private final Casilla destino;
    private final Casilla origen;

    public Trayectoria(List<Casilla> casillas) {
        this.casillas = casillas;
        this.origen=casillas.getFirst();
        this.destino=casillas.getLast();
    }

    public void ejecutarTraslado() {
        origen.moverContenidoA(destino);
    }

    public int logitud() {
        return this.casillas.size()-1;
    }
}
