package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.CasillaVaciaException;

public class Casilla {
    private boolean estaLibre;
    private Pieza pieza;
    private Efecto terrenoCasilla;
    public Casilla(Efecto terreno) {
        this.estaLibre=true;
        this.terrenoCasilla=terreno;
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

    public Pieza removerPieza() {

        Pieza piezaGuardada =this.obtenerPieza();
        limpiarCasilla();
        return piezaGuardada;
    }

    public void recibirAtaque(Ataque ataque) {
        this.pieza.recibirAtaque(ataque);
    }

    public boolean recibirAtaque(Arma arma, Direccion direccion) {
        boolean hit = arma.atacar(this.pieza, direccion);
        // Si la pieza es destruida, la casilla queda libre
        if (this.pieza.estaDestruida()) {
            limpiarCasilla();
        }
        return hit;
    }


    private void limpiarCasilla(){
        this.estaLibre = true;
        this.pieza=new PiezaNula();
    }

    public void aplicarEfecto(Unidad unidad) {
        this.terrenoCasilla.aplicarEfecto(unidad);
    }

    public Efecto getTerreno() {
        return this.terrenoCasilla;
    }

    public Pieza obtenerPieza() {
        if (estaLibre()){
            throw new CasillaVaciaException("");
        }
        return this.pieza;
    }
}
