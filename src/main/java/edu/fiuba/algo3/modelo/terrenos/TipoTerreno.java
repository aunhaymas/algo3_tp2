package edu.fiuba.algo3.modelo.terrenos;

import edu.fiuba.algo3.modelo.unidades.Unidad;
import javafx.scene.paint.Paint;

public interface TipoTerreno {
    void aplicarEfecto(Unidad unidad);
    boolean esTransitable();
    Paint color();
}
