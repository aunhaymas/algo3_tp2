package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

public class MechTest {
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
    public void mechDeCombateSoloPuedeAtacarCasillaAdyacente() {
        // Arrange
        Mech mech = new MechDeCombate();
        Coordenada coordenadaMech = new Coordenada(5, 5);
        Vek enemigoAdyacente = new Vek();
        Coordenada coordenadaEnemigoAdyacente = new Coordenada(5, 4);
        int vidaInicialEnemigoAdyacente = enemigoAdyacente.getVida();
        // Falta la logica para ubicar al mech y al enemigo en las casillas correspondientes
        tablero.colocarPieza(mech, coordenadaMech);
        tablero.colocarPieza(enemigoAdyacente, coordenadaEnemigoAdyacente);

        // Act
        // Atacar deberia emplear direcciones relativas, no casillas directamente
        mech.atacar(tablero, Direccion.ARRIBA); 

        // Assert
        // el enemigo en la casilla adyacente debe haber recibido 2 de daño,
        assertEquals(vidaInicialEnemigoAdyacente - 2, enemigoAdyacente.getVida());
        // el enemigo debe ser empujado una casilla atras

    }

    @Test
    public void mechDeCanonAtacaEnLineaRecta() {
        // Arrange
        Mech mech = new MechDeCanon();
        Coordenada coordenadaMech = new Coordenada(5, 5);
        Vek enemigoLineaRecta = new Vek();
        Coordenada coordenadaEnemigoLineaRecta = new Coordenada(5, 2);
        int vidaInicialEnemigoLineaRecta = enemigoLineaRecta.getVida();
    
        // Act
        // mech.atacar(casillaLineaRecta);

        // Assert
        // el enemigo en la casilla linea recta debe haber recibido 1 de daño,
        assertEquals(vidaInicialEnemigoLineaRecta - 1, enemigoLineaRecta.getVida());
        // el enemigo debe ser empujado una casilla atras
    }

    @Test
    public void mechDeArtilleriaAtacaEnLineaRectaYSaltaUnaCasilla() {
        // Arrange
        Mech mech = new MechDeArtilleria();
        // Aca tiene que ir la logica para ubicar al mech en una casilla
        // El ataque ha de saltarse la casilla adyacente.
        Casilla casillaAdyacente = new Casilla();
        // La casilla linea recta debe ubicarse en la misma fila o columna que el mech y debe incluir un enemigo al que el mech pueda atacar
        Casilla casillaLineaRecta = new Casilla();
    
        // Act
        mech.atacar(casillaLineaRecta);
        mech.atacar(casillaAdyacente);

        // Assert
        // el enemigo en la casilla adyacente no debe haber recibido daño
        // el enemigo en la casilla linea recta debe haber recibido 1 de daño
        // todo lo adyacente a la casilla linea recta debe haberse desplazado una casilla a cada lado.
    }
}
