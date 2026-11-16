package edu.fiuba.algo3.modelo.empujes;

import edu.fiuba.algo3.modelo.Casilla;
import edu.fiuba.algo3.modelo.Coordenada;
import edu.fiuba.algo3.modelo.Tablero;

public class EmpujeEfectivo implements Empuje{
    private final Coordenada destino;
    public EmpujeEfectivo(Coordenada destino){
        this.destino = destino;
    }

    @Override
    public void empujar(Casilla casilla, Tablero contexto) {
        casilla.empujarA(contexto.obtenerCasilla(destino));
    }

    @Override
    public Empuje prepararDesde(Coordenada origen) {
        return new EmpujeEfectivo(origen.trasladar(destino));
    }
}
