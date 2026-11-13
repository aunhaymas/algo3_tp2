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
    public void mechDeCombateAtacaCasillaAdyacente() {
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
        mech.atacar(tablero, coordenadaEnemigoAdyacente); 

        // Assert
        // el enemigo en la casilla adyacente debe haber recibido 2 de daño,
        assertEquals(vidaInicialEnemigoAdyacente - 2, enemigoAdyacente.getVida());
        // el enemigo debe ser empujado una casilla atras
        Casilla casillaEnemigo = tablero.obtenerCasilla(enemigoAdyacente);
        assertEquals(0, casillaEnemigo.obtenerDistanciaX(coordenadaEnemigoAdyacente));
        assertEquals(1, casillaEnemigo.obtenerDistanciaY(coordenadaEnemigoAdyacente));
    }

    @Test
    public void mechDeCombateNoPuedeAtacarCasillaNoAdyacente() {
        // Arrange
        Mech mech = new MechDeCombate();
        Coordenada coordenadaMech = new Coordenada(5, 5);
        Vek enemigoNoAdyacente = new Vek();
        Coordenada coordenadaEnemigoNoAdyacente = new Coordenada(4, 4);
        // Falta la logica para ubicar al mech y al enemigo en las casillas correspondientes
        tablero.colocarPieza(mech, coordenadaMech);
        tablero.colocarPieza(enemigoNoAdyacente, coordenadaEnemigoNoAdyacente);

        // Act y Assert
        assertThrows(TrayectoriaInvalidaException.class, () -> {
            mech.atacar(tablero, coordenadaEnemigoNoAdyacente);
        });

    }

    @Test
    public void mechDeCombateNoAlcanzaATocarCasillasLejanas() {
        // Arrange
        Mech mech = new MechDeCombate();
        Coordenada coordenadaMech = new Coordenada(5, 5);
        Vek enemigoLejano = new Vek();
        Coordenada coordenadaEnemigoLejano = new Coordenada(3, 5);
        // Falta la logica para ubicar al mech y al enemigo en las casillas correspondientes
        tablero.colocarPieza(mech, coordenadaMech);
        tablero.colocarPieza(enemigoLejano, coordenadaEnemigoLejano);

        // Act y Assert
        // Atacar un enemigo lejano debe devolver excepcion "Objeto fuera de rango"
        assertThrows(ObjetoFueraDeRangoException.class, () -> {
            mech.atacar(tablero, coordenadaEnemigoLejano);
        });

    }

    @Test
    public void trayectoriaDebeDevolverContinua() {
        // Arrange
        Coordenada coordenadaSur = new Coordenada(5, 5);
        Coordenada coordenadaNorte = new Coordenada(5, 2);
        Casilla casillaSur = tablero.obtenerCasilla(coordenadaSur);
        Casilla casillaNorte = tablero.obtenerCasilla(coordenadaNorte);

        // Act
        Trayectoria trayectoria = casillaSur.obtenerTrayectoria(casillaNorte);
        
        // Assert
        // Trayectoria debe tener 4 (5-4-3-2) de distancia
        assertEquals(3, trayectoria.logitud());
        assert(trayectoria.sonCasillasAdyacentes(new Norte()));

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
        assertEquals(0, casillaEnemigo.obtenerDistanciaX(coordenadaEnemigoLineaRecta));
        assertEquals(1, casillaEnemigo.obtenerDistanciaY(coordenadaEnemigoLineaRecta));
    }

    @Test
    public void mechDeCanonGolpeaUnObstaculo() {
        // Arrange
        Mech mech = new MechDeCanon();
        Coordenada coordenadaMech = new Coordenada(5, 5);
        Vek enemigoLineaRecta = new Vek();
        Coordenada coordenadaEnemigoLineaRecta = new Coordenada(5, 1);
        int vidaInicialEnemigoLineaRecta = enemigoLineaRecta.getVida();
        Vek enemigoObstaculo = new Vek();
        Coordenada coordenadaObstaculo = new Coordenada(5, 4);
        int vidaInicialEnemigoObstaculo = enemigoLineaRecta.getVida();
        // Falta la logica para ubicar al mech y al enemigo en las casillas correspondientes
        tablero.colocarPieza(mech, coordenadaMech);
        tablero.colocarPieza(enemigoLineaRecta, coordenadaEnemigoLineaRecta);
        tablero.colocarPieza(enemigoObstaculo, coordenadaObstaculo);
    
        // Act
        mech.atacar(tablero, coordenadaEnemigoLineaRecta);

        // Assert
        // el enemigo en la casilla linea recta debe estar normal,
        assertEquals(vidaInicialEnemigoLineaRecta, enemigoLineaRecta.getVida());
        // el enemigo en la casilla obstaculo dee recibir el impacto,
        assertEquals(vidaInicialEnemigoObstaculo - 1, enemigoObstaculo.getVida());
        // el enemigo debe ser empujado una casilla atras
    }

    @Test
    public void mechDeCombateNoPuedeAtacarEnDiagonal() {
        // Arrange
        Mech mech = new MechDeCombate();
        Coordenada coordenadaMech = new Coordenada(5, 5);
        Vek enemigoDiagonal = new Vek();
        Coordenada coordenadaEnemigoDiagonal = new Coordenada(3, 2);
        // Falta la logica para ubicar al mech y al enemigo en las casillas correspondientes
        tablero.colocarPieza(mech, coordenadaMech);
        tablero.colocarPieza(enemigoDiagonal, coordenadaEnemigoDiagonal);

        // Act y Assert
        assertThrows(TrayectoriaInvalidaException.class, () -> {
            mech.atacar(tablero, coordenadaEnemigoDiagonal);
        });

    }

    @Test
    public void mechDeArtilleriaAtacaEnLineaRectaYSaltaUnaCasilla() {
        // Arrange
        Mech mech = new MechDeArtilleria();
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
