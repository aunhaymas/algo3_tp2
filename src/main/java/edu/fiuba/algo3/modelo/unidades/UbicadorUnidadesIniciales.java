package edu.fiuba.algo3.modelo.unidades;

import java.util.Map;

import edu.fiuba.algo3.modelo.Coordenada;
import edu.fiuba.algo3.modelo.tablero.Tablero;

public class UbicadorUnidadesIniciales {
    
    public void ubicar(Map<Coordenada, Pieza> piezasIniciales, Tablero tablero) {
        
        for (Map.Entry<Coordenada, Pieza> entrada : piezasIniciales.entrySet()) {
            Coordenada posicion = entrada.getKey();
            Pieza pieza = entrada.getValue();
            
            tablero.colocarPieza(pieza, posicion);
        }
    }
}
