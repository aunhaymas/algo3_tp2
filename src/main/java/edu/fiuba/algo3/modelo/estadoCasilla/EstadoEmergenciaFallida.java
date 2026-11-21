package edu.fiuba.algo3.modelo.estadoCasilla;

import edu.fiuba.algo3.modelo.Casilla;
import edu.fiuba.algo3.modelo.ataques.Ataque;
import edu.fiuba.algo3.modelo.ataques.DanioColision;
import edu.fiuba.algo3.modelo.empujes.Empuje;
import edu.fiuba.algo3.modelo.empujes.EmpujeNulo;
import edu.fiuba.algo3.modelo.unidades.Pieza;
import edu.fiuba.algo3.modelo.veks.Vek;

public class EstadoEmergenciaFallida implements EstadoCasilla {
    private final Pieza pieza;

    public EstadoEmergenciaFallida(Pieza pieza) {
        this.pieza = pieza;
    }

    @Override
    public EstadoCasilla recibirEmpuje(Casilla origen, Casilla destino, Pieza pieza) {
        //no hace nada aunque estaría bueno ver si hay un caso donde empujen otra pieza a esta casilla
        //donde ya hay otra pieza.
        return this;
    }

    @Override
    public boolean estaLibre() {
        return false;
    }

    @Override
    public Empuje recibirAtaque(Ataque ataque) {
        return this.pieza.recibirAtaqueConEmpuje(ataque);
    }

    @Override
    public Pieza obtenerPieza() {
        return this.pieza;
    }

    @Override
    public boolean tienePieza(Pieza pieza) {
        return false;
    }

    @Override
    public void empujarA(Casilla origen, Casilla destino) {
        destino.recibirEmpuje(origen,this.pieza);
    }

    @Override
    public EstadoCasilla emergencia(Vek vek) {
        return this;
    }

    @Override
    public EstadoCasilla resolverEmergenciaIniciarTurno() {
        this.pieza.recibirAtaque(new DanioColision());
        return new EstadoOcupada(this.pieza);
    }
}
