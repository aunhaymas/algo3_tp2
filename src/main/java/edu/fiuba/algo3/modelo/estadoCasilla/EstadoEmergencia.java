package edu.fiuba.algo3.modelo.estadoCasilla;

import edu.fiuba.algo3.modelo.Casilla;
import edu.fiuba.algo3.modelo.ataques.Ataque;
import edu.fiuba.algo3.modelo.empujes.Empuje;
import edu.fiuba.algo3.modelo.empujes.EmpujeNulo;
import edu.fiuba.algo3.modelo.excepciones.CasillaVaciaException;
import edu.fiuba.algo3.modelo.unidades.Pieza;
import edu.fiuba.algo3.modelo.veks.Vek;

public class EstadoEmergencia implements EstadoCasilla{
    private final Pieza pieza;

    public EstadoEmergencia(Pieza pieza) {
        this.pieza = pieza;
    }

    @Override
    public EstadoCasilla recibirEmpuje(Casilla origen, Casilla destino, Pieza pieza) {
        origen.moverContenidoA(destino);
        pieza.notificarEmpuje(origen.direccionA(destino));
        return new EstadoEmergenciaFallida(pieza);
    }

    @Override
    public boolean estaLibre() {
        return false;
    }

    @Override
    public Empuje recibirAtaque(Ataque ataque) {
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
        //no hay pieza
    }

    @Override
    public EstadoCasilla emergencia(Vek vek) {
        return this;
    }

    @Override
    public EstadoCasilla resolverEmergenciaIniciarTurno() {
        return new EstadoOcupada(this.pieza);
    }
}
