package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.veks.AnuncioDeAtaqueReal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Vek extends Unidad {

	public Vek(int vida, int movimientos) {
        super(vida, movimientos);
    }

	@Override
	public void entrarEn(Casilla casilla) {
		casilla.aplicarEfecto(this);
	}

	@Override
    public void sumergir() {
        this.recibirDaño(this.vida);
    }

    public AnuncioDeAtaqueReal anunciarAtaque(Casilla casillaMech) {
		// TDD: Proximo caso de uso me obligaria a guardar este anuncio?
		return new AnuncioDeAtaqueReal(this,casillaMech);
	}

    @Override
	public void reportarA(LocalizadorEntidad localizador, Casilla casilla) {
		localizador.registrar(this, casilla);
	}

	public List<Casilla> hallarDestinosValidosDesde(Casilla origen, Tablero tablero) {
        List<Casilla> destinosValidos = new ArrayList<>();
        List<Direccion> direcciones = Arrays.asList(new Norte(), new Sur(), new Oeste(), new Este());

        for (Direccion direccion : direcciones) {
            Casilla actual = origen;

            for (int i = 0; i < this.movimientos; i++) {
                Coordenada siguientePosicion = direccion.obtenerCoordenadaAdyacente(actual.getCoordenada());
                Casilla siguienteCasilla = tablero.obtenerCasilla(siguientePosicion);
                
                if (tablero.esPosicionValida(siguientePosicion) && siguienteCasilla.estaLibre()) {
                    destinosValidos.add(siguienteCasilla);
                    actual = siguienteCasilla;
                }
            }
        }
        tablero.ordenarCasillasEnOrdenLectura(destinosValidos);
        return destinosValidos;
    }
    
    public boolean puedoAtacarDesde(Casilla origen, Casilla destino, Tablero tablero) {
        List<Casilla> destinosPosibles = hallarDestinosValidosDesde(origen, tablero);
        return destinosPosibles.contains(origen);
    }
}
