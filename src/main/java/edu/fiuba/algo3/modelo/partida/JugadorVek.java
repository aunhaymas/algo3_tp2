package edu.fiuba.algo3.modelo.partida;

import edu.fiuba.algo3.modelo.Seleccionador;
import edu.fiuba.algo3.modelo.ataques.Ataque;
import edu.fiuba.algo3.modelo.tablero.BuscadorBfs;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.veks.Decision;
import edu.fiuba.algo3.modelo.veks.Opcion;
import edu.fiuba.algo3.modelo.veks.Vek;
import edu.fiuba.algo3.modelo.veks.comportamiento.AnuncioDeAtaque;
import edu.fiuba.algo3.modelo.veks.comportamiento.AnuncioNulo;
import edu.fiuba.algo3.modelo.veks.comportamiento.GeneradorDeOpcionesVek;

import java.util.ArrayList;
import java.util.List;

public class JugadorVek {
    private final GeneradorDeOpcionesVek generador = new GeneradorDeOpcionesVek(new BuscadorBfs());
    private final List<AnuncioDeAtaque> ataquesAnunciados = new ArrayList<AnuncioDeAtaque>();
    private final List<Vek> veksActuales = new ArrayList<Vek>();

    public void anunciarAtaques(Tablero tablero) {
        this.ataquesAnunciados.clear();
        this.veksActuales.clear();
        Seleccionador seleccionador = new SeleccionadorDeVeks(this.veksActuales);
        tablero.serSeleccionadorPor(seleccionador); //selecciono veks
        for (Vek vek : veksActuales) {
            List<Opcion> opciones = this.generador.generarOpcionesPara(vek, tablero);
            Decision decision = vek.tomarDecision(opciones);
            AnuncioDeAtaque anuncio = decision.ejecutar(); //se ejecuta la opcion, se mueve y anuncia ataque
            this.ataquesAnunciados.add(anuncio);
        }

    }

    public void ejecutarAtaques(Tablero tablero) {
        for (AnuncioDeAtaque anuncioDeAtaque : this.ataquesAnunciados) {
            anuncioDeAtaque.ejecutarAtaque(tablero);
        }
    }

}
