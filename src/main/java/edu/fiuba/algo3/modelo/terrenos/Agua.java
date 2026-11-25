package edu.fiuba.algo3.modelo.terrenos;

import edu.fiuba.algo3.modelo.unidades.Unidad;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Agua implements TipoTerreno{

    @Override
    public void aplicarEfecto(Unidad unidad){
        unidad.sumergir();
    }

    @Override
    public boolean esTransitable() { 
        return true; 
    }

    @Override
    public Paint color() {
        return Color.LIGHTBLUE;
    }
}
