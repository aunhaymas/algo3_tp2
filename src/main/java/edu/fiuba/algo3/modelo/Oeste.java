package edu.fiuba.algo3.modelo;

public class Oeste extends Direccion {
    public Coordenada obtenerCoordenadaAdyacente(Coordenada coordenada) {
        return new Coordenada(coordenada.getX() - 1, coordenada.getY());
    }
}
