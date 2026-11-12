package edu.fiuba.algo3.modelo;

public class Norte extends Direccion {
    public Coordenada obtenerCoordenadaAdyacente(Coordenada coordenada) {
        return new Coordenada(coordenada.getX(), coordenada.getY() - 1);
    }
}
