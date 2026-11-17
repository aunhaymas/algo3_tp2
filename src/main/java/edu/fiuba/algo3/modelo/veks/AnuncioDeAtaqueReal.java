package edu.fiuba.algo3.modelo.veks;

import edu.fiuba.algo3.modelo.Casilla;
import edu.fiuba.algo3.modelo.Coordenada;
import edu.fiuba.algo3.modelo.Trayectoria;
import edu.fiuba.algo3.modelo.veks.*;
import edu.fiuba.algo3.modelo.unidades.*;

public class AnuncioDeAtaqueReal implements AnuncioDeAtaque {
    private final Vek vek;
    private Atacable objetivo; //no es final, dado que debe poder modificarse
    private Trayectoria trayectoria;

    public AnuncioDeAtaqueReal(Vek vek, Atacable objetivo) {
        this.vek=vek;
        this.objetivo=objetivo;
    }
    
    @Override
    public void ejecutarAtaque(){
        vek.atacar(objetivo);
    }
    // TDD: en otro caso de uso, se requiere modificar casilla objetivo
    // public void modificarObjetivo(Atacable nuevoObjetivo)
    public void desplazarAtaque(Coordenada cambio){
        //trayectoria.desplazar(cambio);
    }
}
