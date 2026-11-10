package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MechTest {
    private Tablero tablero = new Tablero();

    @Test
    public void mechDeCombateSoloPuedeAtacarCasillaAdyacente() {
        // Arrange
        Mech mech = new MechDeCombate();
        Vek enemigoAdyacente = new Vek();
        // Aca tiene que ir la logica para ubicar al mech y el enemigo en una casilla
        // Casilla casillaAdyacente = new Casilla();
        tablero.agregarCasilla(new Coordenada(0, 0), new Casilla(new Suelo()));
        tablero.agregarCasilla(new Coordenada(0, 1), new Casilla(new Suelo()));

        // Falta la logica para ubicar al mech y al enemigo en las casillas correspondientes
        // tablero.posicionarUnidad(mech, new Coordenada(0, 0));
        // tablero.posicionarUnidad(enemigoAdyacente, new Coordenada(0, 1));
    
        // Act
        // Atacar deberia emplear direcciones relativas, no casillas directamente
        // mech.atacar(casillaAdyacente); 

        // Assert
        // el enemigo en la casilla adyacente debe haber recibido 2 de daño, y ser empujado una casilla atras
        // el enemigo en la casilla lejana no debe haber recibido daño

    }

    @Test
    public void mechDeCanonAtacaEnLineaRecta() {
        // Arrange
        Mech mech = new MechDeCanon();
        // Aca tiene que ir la logica para ubicar al mech en una casilla
        Casilla casillaLineaRecta = new Casilla();
        // La casilla linea recta debe ubicarse en la misma fila o columna que el mech y debe incluir un enemigo al que el mech pueda atacar
        Casilla casillaDiagonal = new Casilla();
        // Casilla diagonal debe incluir un enemigo al que el mech no pueda atacar
    
        // Act
        mech.atacar(casillaLineaRecta);
        mech.atacar(casillaDiagonal);

        // Assert
        // el enemigo en la casilla linea recta debe haber recibido 1 de daño, y ser empujado una casilla atras
        // el enemigo en la casilla diagonal no debe haber recibido daño
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
