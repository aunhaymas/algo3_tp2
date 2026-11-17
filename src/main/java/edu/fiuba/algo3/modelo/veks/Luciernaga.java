package edu.fiuba.algo3.modelo.veks;


import edu.fiuba.algo3.modelo.tablero.*;
import edu.fiuba.algo3.modelo.estadosUnidad.*;
import edu.fiuba.algo3.modelo.mechs.*;
import edu.fiuba.algo3.modelo.unidades.*;
import edu.fiuba.algo3.modelo.empujes.*;
import edu.fiuba.algo3.modelo.terrenos.*;
import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.excepciones.*;
import edu.fiuba.algo3.modelo.veks.*;
import edu.fiuba.algo3.modelo.armas.*;

public class Luciernaga extends Vek {
    public Luciernaga(){
        super(3,2);
        this.arma = new ArmaAcido();
    }

    // Falta refactorizar mucho ._.
    @Override 
    public boolean puedoAtacarDesde(Casilla origen, Casilla destino, Tablero tablero) {
        int distX = origen.obtenerDistanciaX(destino);
        int distY = origen.obtenerDistanciaY(destino);

        // Debe estar en la misma fila o misma columna (y no ser la misma casilla)
        boolean enMismaFila = (distY == 0 && distX != 0);
        boolean enMismaColumna = (distX == 0 && distY != 0);

        if (!enMismaFila && !enMismaColumna) {
            return false;
        }

        Direccion direccion;
        if (enMismaFila) {
            direccion = distX < 0 ? new Oeste() : new Este();
        } else {
            direccion = distY < 0 ? new Norte() : new Sur();
        }

        Coordenada coordActual = direccion.obtenerCoordenadaAdyacente(origen.getCoordenada());

        while (tablero.esPosicionValida(coordActual)) {
            Casilla casillaActual = tablero.obtenerCasilla(coordActual);

            if (casillaActual.equals(destino)) {
                return true; // Es el primer obstaculo que encuentra el ataque
            }
            if (!casillaActual.estaLibre()) {
                return false; // Un obstaculo en el medio bloquea el ataque antes de llegar al objetivo
            }

            coordActual = direccion.obtenerCoordenadaAdyacente(coordActual);
        }

        return false;
    }
}
