package edu.fiuba.algo3.modelo;

public class Casilla {
    boolean estaLibre;
    public Casilla(Efecto terreno) {
        this.estaLibre=true;
    }

    public boolean estaLibre() {
        return estaLibre;
    }

    public void recibirPieza(Pieza pieza) {
        this.estaLibre=false;
        // pieza.entrarEn(this) para aplicar efecto
    }

    public void removerPieza() {
        this.estaLibre=true;
        // this.pieza= piezaNula
    }
}
