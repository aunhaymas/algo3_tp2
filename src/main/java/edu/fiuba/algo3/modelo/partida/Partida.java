package edu.fiuba.algo3.modelo.partida;

import edu.fiuba.algo3.modelo.RedElectrica;
import edu.fiuba.algo3.modelo.mechs.Mech;
import edu.fiuba.algo3.modelo.partida.fase.FaseTurno;
import edu.fiuba.algo3.modelo.partida.fase.FaseVek;
import edu.fiuba.algo3.modelo.tablero.Tablero;

import java.util.List;

public class Partida {
    private Tablero tablero;
    private List<Mech> mechsVivos;
    private RedElectrica red;
    private final JugadorVek jugadorVek= new JugadorVek();
    private int turnoActual = 1;
    private FaseTurno faseActual = new FaseVek();


    public Partida(Tablero tablero) {
        this.tablero = tablero;
    }

    public Partida(Tablero tablero, RedElectrica red, List<Mech> mechs) {
        this(tablero);
        this.mechsVivos = mechs;
        this.red=red;
    }

    public void avanzarFase() {
        this.faseActual= this.faseActual.avanzarFase(this);
    }
    public void ejecutarFaseVek(){
        this.jugadorVek.anunciarAtaques(this.tablero);
    }
    public void ejecutarFaseResolucion(){
        this.jugadorVek.ejecutarAtaques(this.tablero);
    }
    public void siguienteTurno(){
        this.turnoActual+=1;
    }

    public int turnoActual() {
        return this.turnoActual;
    }

    public void ejecutarFaseEmergencia() {

    }
}
