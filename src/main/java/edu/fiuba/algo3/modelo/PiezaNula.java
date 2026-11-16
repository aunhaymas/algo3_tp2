package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.empujes.Empuje;
import edu.fiuba.algo3.modelo.empujes.EmpujeNulo;

public class PiezaNula implements Pieza{
    @Override
    public void entrarEn(Casilla casilla) {

    }

    @Override
    public Empuje recibirAtaque(Ataque ataque, Coordenada origen) {
        return new EmpujeNulo();
    }

    @Override
    public void recibirAtaque(Ataque ataque) {
        this.recibirAtaque(ataque,new Coordenada(0,0));
    }

    @Override 
    public int getVida(){
        throw new RuntimeException("No se puede obtener vida de Casilla vacia.");
    }


    @Override
    public boolean estaDestruida() {
        return false; // No tiene nada, entonces no hay nada que destruir.
    }
}
