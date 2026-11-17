package edu.fiuba.algo3.modelo.empujes;

import edu.fiuba.algo3.modelo.Casilla;
import edu.fiuba.algo3.modelo.Coordenada;
import edu.fiuba.algo3.modelo.Tablero;

public interface Empuje {
    //TODO cambiar tipo de parámetro contexto de tablero a interfaz
    void empujar(Casilla casilla);

    Empuje prepararDesde(Coordenada origen);
}
