package edu.fiuba.algo3.modelo.terrenos;

import edu.fiuba.algo3.modelo.unidades.TipoMovimiento;
import edu.fiuba.algo3.modelo.unidades.Unidad;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Suelo implements TipoTerreno{
    @Override
    public void aplicarEfecto(Unidad unidad) {

    }
    
    @Override
    public boolean esTransitable() { 
        return true; 
    }

    @Override
    public Paint color() {
        return Color.WHEAT;
    }

    public boolean puedeTerminarAca(TipoMovimiento mov) {
        return true;
    }
}
