package edu.fiuba.algo3.modelo.empujes;

import edu.fiuba.algo3.modelo.Casilla;
import edu.fiuba.algo3.modelo.Coordenada;
import edu.fiuba.algo3.modelo.Direccion;

public class EmpujeAdyacente implements Empuje{

    @Override
    public void empujar(Casilla casilla) {
        casilla.empujarAdyacentes();
    }

    @Override
    public Empuje prepararDesde(Coordenada origen) {
        return new EmpujeAdyacente();
    }
}
