package edu.fiuba.algo3.modelo;

public class ComandoMover extends Comando{
    private final Unidad unidad;
    private final Tablero tablero;
    private final Coordenada origen;
    private final Coordenada destino;

    public ComandoMover(Unidad unidad, Tablero tablero, Coordenada origen, Coordenada destino) {
        super();
        this.unidad = unidad;
        this.tablero = tablero;
        this.origen = origen;
        this.destino = destino;
    }

    @Override
    public void ejecutar() {
        tablero.moverPieza(origen,destino);
        unidad.mover();
    }
}
