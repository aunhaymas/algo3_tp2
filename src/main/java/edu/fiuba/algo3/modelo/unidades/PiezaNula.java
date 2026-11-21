package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.modelo.Casilla;
import edu.fiuba.algo3.modelo.Coordenada;
import edu.fiuba.algo3.modelo.ataques.Ataque;
import edu.fiuba.algo3.modelo.empujes.Empuje;
import edu.fiuba.algo3.modelo.empujes.EmpujeNulo;

public class PiezaNula implements Pieza{
    @Override
    public void entrarEn(Casilla casilla) {

    }

    @Override
    public Empuje recibirAtaqueConEmpuje(Ataque ataque) {
        return new EmpujeNulo();
    }

    @Override
    public void recibirAtaque(Ataque ataque) {
        this.recibirAtaqueConEmpuje(ataque);
    }

    @Override 
    public int getVida(){
        throw new RuntimeException("No se puede obtener vida de Casilla vacia.");
    }

    @Override
    public void notificarEmpuje(Coordenada direccion) {

    }


    @Override
    public boolean estaDestruida() {
        return false; // No tiene nada, entonces no hay nada que destruir.
    }
}
