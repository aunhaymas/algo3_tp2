package edu.fiuba.algo3.modelo.terrenos;

import edu.fiuba.algo3.modelo.RedElectrica;
import edu.fiuba.algo3.modelo.unidades.TipoMovimiento;
import edu.fiuba.algo3.modelo.unidades.Unidad;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Edificio implements TipoTerreno {
    public Edificio(){}
    public Edificio(RedElectrica redElectrica) {
    }

    @Override
    public void aplicarEfecto(Unidad unidad) {
        // Implementación del efecto del edificio en la unidad
    }

    @Override
    public boolean esTransitable() { 
        return false; 
    }

    @Override
    public Paint color() {
        return Color.GRAY;
    }
    
    public boolean puedeTerminarAca(TipoMovimiento mov) {
        return false;
    }

}
