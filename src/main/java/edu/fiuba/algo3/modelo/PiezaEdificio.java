package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.empujes.Empuje;
import edu.fiuba.algo3.modelo.empujes.EmpujeNulo;

public class PiezaEdificio implements Pieza {
    private final RedElectrica red;
    private int vida = 1;
    public PiezaEdificio(RedElectrica redElectrica) {
        this.red=redElectrica;
    }

    @Override
    public void entrarEn(Casilla casilla) {
        casilla.recibirPieza(this);
    }

    @Override
    public Empuje recibirAtaque(Ataque ataque, Coordenada origen) {
        this.red.edificioDestruido();
        this.vida--; //
        return new EmpujeNulo();
    }

    @Override
    public void recibirAtaque(Ataque ataque) {
        this.recibirAtaque(ataque,new Coordenada(0,0));
    }

    @Override
    public boolean estaDestruida() {
        return vida<1;
    }

    @Override
    public int getVida() {
        return this.vida;
    }
}
