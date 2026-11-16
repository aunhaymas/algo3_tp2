package edu.fiuba.algo3.modelo.empujes;

import edu.fiuba.algo3.modelo.Casilla;
import edu.fiuba.algo3.modelo.Coordenada;
import edu.fiuba.algo3.modelo.Tablero;

public interface Empuje {
    void empujar(Casilla casilla, Tablero contexto);

    Empuje prepararDesde(Coordenada origen);
}
