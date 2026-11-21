package edu.fiuba.algo3.modelo.veks.comportamiento;

import edu.fiuba.algo3.modelo.Coordenada;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public interface AnuncioDeAtaque {
    public void ejecutarAtaque(Tablero contexto);
    public void desplazarAtaque(Coordenada cambio);
}
