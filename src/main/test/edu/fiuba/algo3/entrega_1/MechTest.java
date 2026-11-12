package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.excepciones.TrayectoriaInvalidaException;
import edu.fiuba.algo3.modelo.excepciones.ObjetoFueraDeRangoException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    public void mechDeCombateAtacaAVek() {
        // Arrange
        Mech mech = new MechDeCombate();
        Vek enemigo = new Vek();
        int vidaInicialEnemigo = enemigo.getVida();

        // Act
        mech.atacar(enemigo);
        // Assert
        assertEquals(vidaInicialEnemigo - 2, enemigo.getVida());
    }

    @Test
    public void mechDeCanonAtacaAVek() {
        // Arrange
        Mech mech = new MechDeCanon();
        Vek enemigo = new Vek();
        int vidaInicialEnemigo = enemigo.getVida();

        // Act
        mech.atacar(enemigo);
        // Assert
        assertEquals(vidaInicialEnemigo - 1, enemigo.getVida());
    }

    @Test
    public void mechDeArtilleriaAtacaAVek() {
        // Arrange
        Mech mech = new MechDeArtilleria();
        Vek enemigo = new Vek();
        int vidaInicialEnemigo = enemigo.getVida();

        // Act
        mech.atacar(enemigo);
        // Assert
        assertEquals(vidaInicialEnemigo - 1, enemigo.getVida());
    }


    // ---------------------------------------------------------

    @Test
    public void mechDeCombateSoloPuedeAtacarCasillaAdyacente() {
        // Arrange
        Mech mech = new MechDeCombate();
        Coordenada coordenadaMech = new Coordenada(5, 5);
        Vek enemigoAdyacente = new Vek();
        Coordenada coordenadaEnemigoAdyacente = new Coordenada(5, 4);
        int vidaInicialEnemigoAdyacente = enemigoAdyacente.getVida();
        Vek enemigoNoAdyacente = new Vek();
        Coordenada coordenadaEnemigoNoAdyacente = new Coordenada(4, 4);
        Vek enemigoLejano = new Vek();
        Coordenada coordenadaEnemigoLejano = new Coordenada(3, 5);
        // Falta la logica para ubicar al mech y al enemigo en las casillas correspondientes
        tablero.colocarPieza(mech, coordenadaMech);
        tablero.colocarPieza(enemigoAdyacente, coordenadaEnemigoAdyacente);
        tablero.colocarPieza(enemigoNoAdyacente, coordenadaEnemigoNoAdyacente);
        tablero.colocarPieza(enemigoLejano, coordenadaEnemigoLejano);

        // Act
        // Atacar deberia emplear direcciones relativas, no casillas directamente
        mech.atacar(tablero, coordenadaEnemigoAdyacente); 

        // Assert
        // el enemigo en la casilla adyacente debe haber recibido 2 de daño,
        assertEquals(vidaInicialEnemigoAdyacente - 2, enemigoAdyacente.getVida());
        // el enemigo debe ser empujado una casilla atras
        Casilla casillaEnemigo = tablero.obtenerCasilla(enemigoAdyacente);
        assertEquals(casillaEnemigo.obtenerDistanciaX(coordenadaEnemigoAdyacente), 0);
        assertEquals(casillaEnemigo.obtenerDistanciaY(coordenadaEnemigoAdyacente), -1);

        // Atacar un enemigo no adyacente debe devolver excepcion "Trayectoria invalida"
        assertThrows(TrayectoriaInvalidaException.class, () -> {
            mech.atacar(tablero, coordenadaEnemigoNoAdyacente);
        });
        // Atacar un enemigo lejano debe devolver excepcion "Objeto fuera de rango"
        assertThrows(ObjetoFueraDeRangoException.class, () -> {
            mech.atacar(tablero, coordenadaEnemigoLejano);
        });

    }

    @Test
    public void mechDeCanonAtacaEnLineaRecta() {
        // Arrange
        Mech mech = new MechDeCanon();
        Coordenada coordenadaMech = new Coordenada(5, 5);
        Vek enemigoLineaRecta = new Vek();
        Coordenada coordenadaEnemigoLineaRecta = new Coordenada(5, 2);
        int vidaInicialEnemigoLineaRecta = enemigoLineaRecta.getVida();
        // Falta la logica para ubicar al mech y al enemigo en las casillas correspondientes
        tablero.colocarPieza(mech, coordenadaMech);
        tablero.colocarPieza(enemigoLineaRecta, coordenadaEnemigoLineaRecta);
    
        // Act
        mech.atacar(tablero, coordenadaEnemigoLineaRecta);

        // Assert
        // el enemigo en la casilla linea recta debe haber recibido 1 de daño,
        assertEquals(vidaInicialEnemigoLineaRecta - 1, enemigoLineaRecta.getVida());
        // el enemigo debe ser empujado una casilla atras
        Casilla casillaEnemigo = tablero.obtenerCasilla(enemigoLineaRecta);
        assertEquals(casillaEnemigo.obtenerDistanciaX(coordenadaEnemigoLineaRecta), 0);
        assertEquals(casillaEnemigo.obtenerDistanciaY(coordenadaEnemigoLineaRecta), -1);
    }

    @Test
    public void mechDeArtilleriaAtacaEnLineaRectaYSaltaUnaCasilla() {
        // Arrange
        Mech mech = new MechDeCanon();
        Coordenada coordenadaMech = new Coordenada(5, 5);
        Vek enemigoLineaRecta = new Vek();
        Coordenada coordenadaEnemigoLineaRecta = new Coordenada(5, 2);
        int vidaInicialEnemigoLineaRecta = enemigoLineaRecta.getVida();
        // Falta la logica para ubicar al mech y al enemigo en las casillas correspondientes
        tablero.colocarPieza(mech, coordenadaMech);
        tablero.colocarPieza(enemigoLineaRecta, coordenadaEnemigoLineaRecta);
    
        // Act
        mech.atacar(tablero, coordenadaEnemigoLineaRecta);

        // Assert
        // el enemigo en la casilla linea recta debe haber recibido 1 de daño,
        assertEquals(vidaInicialEnemigoLineaRecta - 1, enemigoLineaRecta.getVida());

        // Assert
        // el enemigo en la casilla adyacente no debe haber recibido daño
        // el enemigo en la casilla linea recta debe haber recibido 1 de daño
        // todo lo adyacente a la casilla linea recta debe haberse desplazado una casilla a cada lado.
    }
}
