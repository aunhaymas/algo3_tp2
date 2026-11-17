package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.empujes.Empuje;
import edu.fiuba.algo3.modelo.empujes.EmpujeNulo;

public class PiezaMontania implements Pieza{
    @Override
    public void entrarEn(Casilla casilla) {
        casilla.recibirPieza(this);
    }

    @Override
    public Empuje recibirAtaque(Ataque ataque, Coordenada origen) {
        return new EmpujeNulo();
    }

    @Override
    public void recibirAtaque(Ataque ataque) {

    }

    @Override
    public boolean estaDestruida() {
        return false;
    }

    @Override
    public int getVida() {
        return 1;
    }

    @Override
    public void notificarEmpuje(Coordenada direccion) {

    }
}
