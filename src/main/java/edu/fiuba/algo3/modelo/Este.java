package edu.fiuba.algo3.modelo;

public class Este extends Direccion {
    public Coordenada obtenerCoordenadaAdyacente(Coordenada coordenada) {
        return new Coordenada(coordenada.getX() + 1, coordenada.getY());
    }
}
