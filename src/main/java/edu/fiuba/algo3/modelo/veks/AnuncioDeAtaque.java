package edu.fiuba.algo3.modelo.veks;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.unidades.Atacable;

public class AnuncioDeAtaque {
    private final Vek vek;
    private Coordenada origen; //no es final, dado que debe poder modificarse
    private Coordenada objetivo; //no es final, dado que debe poder modificarse
    private Trayectoria trayectoria;
    private AnuncioDeAtaque anuncioActual;
    public AnuncioDeAtaque(Vek vek, Coordenada origen,Coordenada objetivo) {
        this.vek=vek;
        this.objetivo=objetivo;
        this.origen = origen;
    }
    public void ejecutarAtaque(Tablero contexto){
        this.vek.atacar(contexto,objetivo);
    }
    // TDD: en otro caso de uso, se requiere modificar casilla objetivo
    // public void modificarObjetivo(Atacable nuevoObjetivo)
    public void desplazarAtaque(Coordenada cambio){
        this.objetivo = objetivo.trasladar(cambio);
    }
}
