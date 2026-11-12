package edu.fiuba.algo3.modelo;

public interface Pieza {
    // Una vez dentro, se delega en pieza como interactuar con casilla
    public void entrarEn(Casilla casilla);
    public boolean recibirAtaque(Ataque ataque);
    public boolean estaDestruida();
    // public boolean mover(int fila, int columna);
}
