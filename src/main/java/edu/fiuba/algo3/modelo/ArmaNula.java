package edu.fiuba.algo3.modelo;

public class ArmaNula extends Arma {
    @Override
    public boolean atacar(Casilla casilla, Direccion direccion) {
        return false;
    }

    @Override
    public void atacar(Dañable objetivo) {

    }
}
