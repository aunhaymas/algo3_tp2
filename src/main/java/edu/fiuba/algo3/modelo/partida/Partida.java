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
    private List<SolicitudEmergencia> emergenciaTurnos;
    private boolean esGameOver;
    private boolean sePerdio;


    public Partida(Tablero tablero) {
        this.tablero = tablero;
    }

    public Partida(Tablero tablero, RedElectrica red, List<Mech> mechs) {
        this(tablero);
        this.mechsVivos = mechs;
        this.red=red;
        this.emergenciaTurnos = List.of(new SinEmergencia());
    }
    public Partida(Tablero tablero, RedElectrica red, List<Mech> mechs, List<SolicitudEmergencia> emergenciaTurnos) {
        this(tablero,red,mechs);
        this.emergenciaTurnos = emergenciaTurnos;
    }

    public void avanzarFase() {
        this.faseActual= this.faseActual.avanzarFase(this);
    }
    public void ejecutarFaseVek(){
        this.emergenciaTurnos.get(0).solicitarEmergenciaEn(tablero);
        this.jugadorVek.anunciarAtaques(this.tablero);
    }
    public void ejecutarFaseResolucion(){
        this.jugadorVek.ejecutarAtaques(this.tablero);
        // aca tiene sentido que se verifique
        verificarGameOver();
    }
    public void siguienteTurno(){
        this.turnoActual+=1;
    }

    public int turnoActual() {
        return this.turnoActual;
    }

    public void ejecutarFaseEmergencia() {
        tablero.resolverEmergencia();
    }

    private void verificarGameOver() {
        boolean apagon = this.red.estaDestruida();
        boolean flotaDerrotada = true;
        for (Mech mech:this.mechsVivos){
            if (!mech.estaDestruida()) flotaDerrotada =false;
        }
        boolean victoria = this.turnoActual == 5;
        this.esGameOver = apagon || flotaDerrotada || victoria;
        this.sePerdio = apagon || flotaDerrotada;

    }

    public boolean esGameOver() {
        return this.esGameOver;
    }

    public boolean sePerdio() {
        return this.sePerdio;
    }
}
