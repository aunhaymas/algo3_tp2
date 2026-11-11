package edu.fiuba.algo3.modelo;

public class Sur extends Direccion {
    public Coordenada obtenerCoordenadaAdyacente(Coordenada coordenada) {
        return new Coordenada(coordenada.getX(), coordenada.getY() + 1);
    }
}
