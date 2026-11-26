package edu.fiuba.algo3.modelo.partida;

import edu.fiuba.algo3.modelo.Coordenada;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.veks.Saltador;
import edu.fiuba.algo3.modelo.veks.Vek;

public class EmergenciaTurno implements SolicitudEmergencia{
    private final Coordenada objetivo;
    private final Vek vek;
    public EmergenciaTurno(Coordenada coordenadaObjetivo, Vek vek) {
        this.objetivo=coordenadaObjetivo;
        this.vek=vek;
    }

    public void solicitarEmergenciaEn(Tablero tablero) {
        tablero.solicitarEmergenciaEn(this.objetivo,this.vek);
    }
}
