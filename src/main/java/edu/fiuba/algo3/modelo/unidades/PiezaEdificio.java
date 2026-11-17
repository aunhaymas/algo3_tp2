package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.modelo.Casilla;
import edu.fiuba.algo3.modelo.RedElectrica;
import edu.fiuba.algo3.modelo.ataques.Ataque;
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
    public Empuje recibirAtaqueConEmpuje(Ataque ataque) {
        this.red.edificioDestruido();
        this.vida--; //
        return new EmpujeNulo();
    }

    @Override
    public void recibirAtaque(Ataque ataque) {
        this.recibirAtaqueConEmpuje(ataque);
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
