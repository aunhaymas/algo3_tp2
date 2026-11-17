package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.empujes.Empuje;

public interface Pieza {
    // Una vez dentro, se delega en pieza como interactuar con casilla
    public void entrarEn(Casilla casilla);
    Empuje recibirAtaqueConEmpuje(Ataque ataque);
    public void recibirAtaque(Ataque ataque);
    public boolean estaDestruida();
    public int getVida();
    // public boolean mover(int fila, int columna);
}
