package edu.fiuba.algo3.modelo.veks.comportamiento;

import edu.fiuba.algo3.modelo.Casilla;
import edu.fiuba.algo3.modelo.Coordenada;
import edu.fiuba.algo3.modelo.Seleccionador;
import edu.fiuba.algo3.modelo.Trayectoria;
import edu.fiuba.algo3.modelo.mechs.Mech;
import edu.fiuba.algo3.modelo.unidades.PiezaEdificio;
import edu.fiuba.algo3.modelo.unidades.PiezaMontania;
import edu.fiuba.algo3.modelo.veks.Opcion;
import edu.fiuba.algo3.modelo.veks.Vek;

import java.util.List;

public class SeleccionadorObjetivosAtaqueVek implements Seleccionador {
    private final Coordenada objetivo;
    private final List<Opcion> opciones;
    private final Trayectoria trayectoria;
    public SeleccionadorObjetivosAtaqueVek(Trayectoria trayectoria, Coordenada objetivo, List<Opcion> opciones) {
        this.trayectoria=trayectoria;
        this.objetivo=objetivo;
        this.opciones=opciones;
    }

    // Se supone que vek verifico que puede atacar a estos objetivos, por lo que solo
    // se utiliza esta clase en ese instante
    @Override
    public void seleccionarEdificio(PiezaEdificio edificio) {
        OpcionAtaqueEdificio opcion = new OpcionAtaqueEdificio(this.trayectoria,this.objetivo);
        agregarOpcion(opcion);
    }

    @Override
    public void seleccionarMech(Mech mech) {
        OpcionAtaqueMech opcion = new OpcionAtaqueMech(this.trayectoria,this.objetivo);
        agregarOpcion(opcion);
    }

    @Override
    public void seleccionarVek(Vek vek) {

    }

    @Override
    public void seleccionarMontania(PiezaMontania montania) {

    }

    @Override
    public void seleccionarCasilla(Casilla casilla) {

    }
    private void agregarOpcion(Opcion opcion){
        opciones.add(opcion);
    }
}
