package edu.fiuba.algo3.entrega_2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.terrenos.TerrenosFactory;
import edu.fiuba.algo3.modelo.mechs.MechDeCanon;
import edu.fiuba.algo3.modelo.unidades.*;
import edu.fiuba.algo3.modelo.Casilla;
import edu.fiuba.algo3.modelo.RedElectrica;
import edu.fiuba.algo3.modelo.excepciones.PosicionOcupadaException;
import edu.fiuba.algo3.modelo.Coordenada;
import edu.fiuba.algo3.modelo.mechs.MechDeCombate;
import edu.fiuba.algo3.modelo.tablero.ParserTablero;

public class UbicadorUnidadesInicialesTest {
    private UbicadorUnidadesIniciales ubicador = new UbicadorUnidadesIniciales();
    private ParserTablero parserTablero = new ParserTablero(new TerrenosFactory());
    private Tablero tablero = parserTablero.parsear(List.of(
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS",
            "SSSSSSSS"
    ));

    @Test
    public void UbicadorColocaPiezaCorrectamenteEnSuPosicion() {
        Coordenada coordenada = new Coordenada(3, 4);
        Pieza mechDeCombate = new MechDeCombate();

        Map<Coordenada, Pieza> piezasAColocar = new HashMap<>();
        piezasAColocar.put(coordenada, mechDeCombate);

        ubicador.ubicar(piezasAColocar, tablero);

        Casilla casillaObjetivo = tablero.obtenerCasilla(coordenada);

        assertFalse(casillaObjetivo.estaLibre());
        assertEquals(mechDeCombate, casillaObjetivo.obtenerPieza());
    }

    @Test
    public void LanzaExcepcionAlIntentarColocarEnCasillaOcupada() {
        
        Coordenada coordenadaOcupada = new Coordenada(1, 1);

        RedElectrica redElectrica = new RedElectrica();
        Pieza edificio = new PiezaEdificio(redElectrica); 
        tablero.colocarPieza(edificio, coordenadaOcupada);

        Pieza Mech = new MechDeCanon();
        Map<Coordenada, Pieza> piezasAColocar = new HashMap<>();
        piezasAColocar.put(coordenadaOcupada, Mech);

        assertThrows(PosicionOcupadaException.class, () -> {
            ubicador.ubicar(piezasAColocar, tablero);
        });
    }
}
