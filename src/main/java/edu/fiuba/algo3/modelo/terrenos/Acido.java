package edu.fiuba.algo3.modelo.terrenos;

import edu.fiuba.algo3.modelo.unidades.TipoMovimiento;
import edu.fiuba.algo3.modelo.unidades.Unidad;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Acido implements TipoTerreno{

    @Override
    public void aplicarEfecto(Unidad unidad) {
        unidad.contaminar();
    }

    @Override
    public boolean esTransitable() { 
        return true; 
    }

    @Override
    public Paint color() {
        return Color.YELLOWGREEN;
    }

    public boolean puedeTerminarAca(TipoMovimiento mov) {

        return mov.puedeTerminarEnAcido();
    }
}
