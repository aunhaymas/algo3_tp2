package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.Unidad;

import java.util.List;

public interface BuscaTrayectorias {
    List<Trayectoria> trayectoriasDesde(Coordenada origen, Unidad unidad, Tablero contexto);
}
