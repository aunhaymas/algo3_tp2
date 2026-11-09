package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.excepciones.MapaInvalidoException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTableroTest {
    
    @Test
    public void tablerosConCantidadDeFilasDiferenteDe8NoSonValidos() {
        ParserTablero parser = new ParserTablero(new TerrenosFactory());
        
        assertThrows(MapaInvalidoException.class, () -> {
            parser.parsear(List.of(
                    "########",
                    "#......#",
                    "#......#",
                    "#......#",
                    "#......#",
                    "#......#",
                    "#......#"
            ));
        });
    }

    @Test
    public void tablerosConCantidadDeColumnasDiferenteDe8NoSonValidos() {
        ParserTablero parser = new ParserTablero(new TerrenosFactory());
        
        assertThrows(MapaInvalidoException.class, () -> {
            parser.parsear(List.of(
                    "########",
                    "#......#",
                    "#......#",
                    "#......#",
                    "#......#",
                    "#......#",
                    "#......#",
                    "#######"
            ));
        });
    }

    @Test
    public void tablerosVaciosNoSonValidos() {
        ParserTablero parser = new ParserTablero(new TerrenosFactory());
        
        assertThrows(MapaInvalidoException.class, () -> {
            parser.parsear(List.of());
        });
    }
    
    @Test
    public void parserCreaTableroConTerrenosCorrectamenteUbicados() {
        ParserTablero parser = new ParserTablero(new TerrenosFactory());
        
        Tablero tablero = parser.parsear(List.of(
                "ESSSEESE",
                "EESEEESE",
                "EESESESE",
                "EESEEESE",
                "SESESESE",
                "EESEEESE",
                "EESEEESE",
                "EESESESE"
        ));

        assertTrue(tablero.obtenerCasilla(new Coordenada(2, 0)).getTerreno() instanceof Suelo);
    }
}
