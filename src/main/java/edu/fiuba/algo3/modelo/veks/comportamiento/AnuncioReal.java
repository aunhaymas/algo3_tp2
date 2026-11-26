package edu.fiuba.algo3.modelo.veks.comportamiento;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.veks.Vek;

public class AnuncioReal implements AnuncioDeAtaque {
    private final Vek vek;
    private Coordenada objetivo; //no es final, dado que debe poder modificarse
    private Trayectoria trayectoria;
    public AnuncioReal(Vek vek, Coordenada objetivo) {
        this.vek=vek;
        this.objetivo=objetivo;
    }
    public void ejecutarAtaque(Tablero contexto){
        if (this.vek.estaDestruida()) return;
        this.vek.atacar(contexto,objetivo);
    }
    public void desplazarAtaque(Coordenada cambio){
        this.objetivo = objetivo.trasladar(cambio);
    }
}
