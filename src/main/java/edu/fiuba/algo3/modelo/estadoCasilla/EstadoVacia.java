package edu.fiuba.algo3.modelo.estadoCasilla;

import edu.fiuba.algo3.modelo.Ataque;
import edu.fiuba.algo3.modelo.Casilla;
import edu.fiuba.algo3.modelo.Coordenada;
import edu.fiuba.algo3.modelo.Pieza;
import edu.fiuba.algo3.modelo.empujes.Empuje;
import edu.fiuba.algo3.modelo.empujes.EmpujeNulo;
import edu.fiuba.algo3.modelo.excepciones.CasillaVaciaException;

public class EstadoVacia implements EstadoCasilla{
    @Override
    public EstadoCasilla recibirEmpuje(Casilla origen, Casilla destino, Pieza pieza) {
        origen.moverContenidoA(destino);
        return new EstadoOcupada(pieza);
    }

    @Override
    public boolean estaLibre() {
        return true;
    }

    @Override
    public Empuje recibirAtaque(Ataque ataque, Coordenada origen) {
        return new EmpujeNulo();
    }

    @Override
    public Pieza obtenerPieza() {
        throw new CasillaVaciaException("La casilla esta Vacia");
    }

    @Override
    public boolean tienePieza(Pieza pieza) {
        return false;
    }

    @Override
    public void empujarA(Casilla origen, Casilla destino) {

    }
}
