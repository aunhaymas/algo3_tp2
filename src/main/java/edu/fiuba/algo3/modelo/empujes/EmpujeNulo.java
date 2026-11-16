package edu.fiuba.algo3.modelo.empujes;

import edu.fiuba.algo3.modelo.Casilla;
import edu.fiuba.algo3.modelo.Coordenada;
import edu.fiuba.algo3.modelo.Tablero;

public class EmpujeNulo implements Empuje {
    @Override
    public void empujar(Casilla casilla, Tablero contexto) {
        // no empuja!
    }

    @Override
    public Empuje prepararDesde(Coordenada origen) {
        return new EmpujeNulo();
    }
}
