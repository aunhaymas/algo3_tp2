package edu.fiuba.algo3.modelo;

public interface Pieza {
    // Una vez dentro, se delega en pieza como interactuar con casilla
    public void entrarEn(Casilla casilla);
    // public void recibirAtaque(Ataque ataque);
    public boolean recibirAtaque(int danio);
    public boolean estaDestruida();
    public boolean mover(int fila, int columna);
}
