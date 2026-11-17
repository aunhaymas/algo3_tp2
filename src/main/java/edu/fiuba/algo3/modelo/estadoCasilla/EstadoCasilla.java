package edu.fiuba.algo3.modelo.estadoCasilla;

import edu.fiuba.algo3.modelo.Ataque;
import edu.fiuba.algo3.modelo.Casilla;
import edu.fiuba.algo3.modelo.Coordenada;
import edu.fiuba.algo3.modelo.Pieza;
import edu.fiuba.algo3.modelo.empujes.Empuje;

public interface EstadoCasilla {
    public EstadoCasilla recibirEmpuje(Casilla origen, Casilla destino, Pieza pieza);

    public  boolean estaLibre();
    public Empuje recibirAtaque(Ataque ataque);

    Pieza obtenerPieza();

    boolean tienePieza(Pieza pieza);

    void empujarA(Casilla origen, Casilla destino);
}
