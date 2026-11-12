package edu.fiuba.algo3.modelo;

public class PiezaNula implements Pieza{
    @Override
    public void entrarEn(Casilla casilla) {

    }

    @Override 
    public int getVida(){
        throw new RuntimeException("No se puede obtener vida de Casilla vacia.");
    }

    @Override
    public void recibirAtaque(Ataque ataque) {
        return;
    }

    @Override
    public boolean estaDestruida() {
        return false; // No tiene nada, entonces no hay nada que destruir.
    }
}
