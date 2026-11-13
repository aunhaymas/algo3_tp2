package edu.fiuba.algo3.modelo;

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
    public void recibirAtaque(Ataque ataque) {
        this.red.edificioDestruido();
        this.vida--; //
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
