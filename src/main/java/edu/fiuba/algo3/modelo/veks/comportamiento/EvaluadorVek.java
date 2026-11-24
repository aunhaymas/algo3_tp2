package edu.fiuba.algo3.modelo.veks.comportamiento;

import edu.fiuba.algo3.modelo.unidades.Unidad;
import edu.fiuba.algo3.modelo.veks.Decision;
import edu.fiuba.algo3.modelo.veks.Opcion;
import edu.fiuba.algo3.modelo.veks.Vek;

import java.util.List;

public class EvaluadorVek implements Evaluador{
    private Decision decisionTomada;
    private OpcionAtaqueEdificio ataqueEdificio;
    private OpcionAtaqueMech ataqueMech;
    private OpcionAcercarseAEdificio acercarseAEdificio;

    @Override
    public Decision tomarDecision(List<Opcion> opciones, Vek vek) {
        for (Opcion opcion:opciones) opcion.evaluarse(this);
        return seleccionarMejorOpcion(vek);
    }

    private Decision seleccionarMejorOpcion(Vek vek) {
        if (ataqueEdificio != null) return new DecisionAtaqueEdificio(ataqueEdificio,vek);
        if (ataqueMech!=null) return new DecisionAtaqueMech(ataqueMech,vek);
        return new DecisionAcercarseAEdificio(acercarseAEdificio,vek);
    }

    @Override
    public void evaluarOpcionAtaqueMech(OpcionAtaqueMech opcion) {
        ataqueMech = opcion;
    }

    @Override
    public void evaluarOpcionAtaqueEdificio(OpcionAtaqueEdificio opcion) {
        ataqueEdificio=opcion;
    }

    @Override
    public void evaluarOpcionAcercarseEdificio(OpcionAcercarseAEdificio opcion) {
        acercarseAEdificio = opcion;
    }


}
