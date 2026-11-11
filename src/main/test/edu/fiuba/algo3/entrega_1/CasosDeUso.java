package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Casilla;
import edu.fiuba.algo3.modelo.Coordenada;
import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.Unidad;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class CasosDeUso {

    //Verificar que un Mech solo pueda moverse a casillas dentro de su rango de movimiento,
    // sin atravesar obstáculos ni otras unidades.
    @Test
    public void MechSeMueveSegunSuRangoDeMovimientoYNoAtraviesaObstaculosNiUnidades() {
        Casilla origen = new Casilla(new Coordenada(0,0));
    }
}
