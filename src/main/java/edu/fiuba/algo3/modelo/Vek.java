package edu.fiuba.algo3.modelo;

public class Vek extends Unidad {
    public boolean recibirAtaque(Arma arma, Direccion direccion) {
        return arma.atacar(this.casilla, direccion);
    }
}
