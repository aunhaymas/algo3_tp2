package edu.fiuba.algo3.modelo.estadoCasilla;

import edu.fiuba.algo3.modelo.Casilla;
import edu.fiuba.algo3.modelo.Coordenada;
import edu.fiuba.algo3.modelo.ataques.Ataque;
import edu.fiuba.algo3.modelo.empujes.Empuje;
import edu.fiuba.algo3.modelo.unidades.Pieza;
import edu.fiuba.algo3.modelo.unidades.Unidad;
import edu.fiuba.algo3.modelo.veks.Vek;

public interface EstadoCasilla {
    public EstadoCasilla recibirEmpuje(Casilla origen, Casilla destino, Pieza pieza);

    public  boolean estaLibre();
    public Empuje recibirAtaque(Ataque ataque);

    Pieza obtenerPieza();

    boolean tienePieza(Pieza pieza);

    void empujarA(Casilla origen, Casilla destino);

    EstadoCasilla emergencia(Vek vek);

    EstadoCasilla resolverEmergenciaIniciarTurno();
}
