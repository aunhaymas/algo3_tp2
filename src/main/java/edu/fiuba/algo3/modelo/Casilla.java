package edu.fiuba.algo3.modelo;

public class Casilla {
    boolean estaLibre;
    Pieza pieza;
    public Casilla(Efecto terreno) {
        this.estaLibre=true;
        limpiarCasilla();
    }

    public boolean estaLibre() {
        return estaLibre;
    }

    public void recibirPieza(Pieza pieza) {
        this.estaLibre=false;
        this.pieza=pieza;
        pieza.entrarEn(this);
        // pieza.entrarEn(this) para aplicar efecto
    }

    public void removerPieza() {
        this.estaLibre=true;
        limpiarCasilla();
        // this.pieza= piezaNula
    }

    public void recibirAtaque(Ataque ataque) {
        this.pieza.recibirAtaque(ataque);
    }

    private void limpiarCasilla(){
        this.pieza=new PiezaNula();
    }
}
