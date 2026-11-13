package edu.fiuba.algo3.modelo;

public class Agua implements TipoTerreno{

    @Override
    public void aplicarEfecto(Unidad unidad){

        unidad.sumergir();

    }
}
