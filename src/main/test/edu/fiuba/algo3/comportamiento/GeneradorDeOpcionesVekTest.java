package edu.fiuba.algo3.comportamiento;

import edu.fiuba.algo3.modelo.Casilla;
import edu.fiuba.algo3.modelo.Coordenada;
import edu.fiuba.algo3.modelo.RedElectrica;
import edu.fiuba.algo3.modelo.mechs.Mech;
import edu.fiuba.algo3.modelo.mechs.MechDeArtilleria;
import edu.fiuba.algo3.modelo.tablero.BuscadorBfs;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.terrenos.Acido;
import edu.fiuba.algo3.modelo.unidades.Pieza;
import edu.fiuba.algo3.modelo.unidades.PiezaEdificio;
import edu.fiuba.algo3.modelo.unidades.PiezaMontania;
import edu.fiuba.algo3.modelo.veks.*;
import edu.fiuba.algo3.modelo.veks.comportamiento.AnuncioDeAtaque;
import edu.fiuba.algo3.modelo.veks.comportamiento.GeneradorDeOpcionesVek;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GeneradorDeOpcionesVekTest {

    @Test
    void generadorDeOpcionesASaltadorGeneraLaOpcionAtaqueAEdificio() {
        Casilla origen = Casilla.crearEn(0, 0);
        Casilla posicionAtaque = Casilla.crearEn(1, 0);
        Casilla casillaEdificio = Casilla.crearEn(2, 0);
        RedElectrica red = new RedElectrica();
        PiezaEdificio edificio = new PiezaEdificio(red);
        casillaEdificio.recibirPieza(edificio);

        Vek vek = new Saltador(); //hace 2 de danio
        origen.recibirPieza(vek);

        Tablero tablero = new Tablero(List.of(origen, posicionAtaque, casillaEdificio));
        GeneradorDeOpcionesVek generador = new GeneradorDeOpcionesVek(new BuscadorBfs());

        List<Opcion> opciones = generador.generarOpcionesPara(vek, tablero);
        AnuncioDeAtaque anuncioDeAtaque = opciones.get(0).ejecutar(vek);

        anuncioDeAtaque.ejecutarAtaque(tablero);

        assertTrue(posicionAtaque.tiene(vek));
        assertTrue(edificio.estaDestruida());
    }

    @Test
    void generadorDeOpcionesAAvisponGeneraLaOpcionAtaqueAMechDeArtilleria() {
        Casilla origen = Casilla.crearEn(0, 0);
        Casilla posicionAtaque = Casilla.crearEn(1, 0);
        Casilla casillaMech = Casilla.crearEn(2, 0);
        Mech mech = new MechDeArtilleria(); // tiene 2 de vida
        casillaMech.recibirPieza(mech);

        Vek vek = new Avispon(); // hace 1 de danio
        origen.recibirPieza(vek);

        Tablero tablero = new Tablero(List.of(origen, posicionAtaque, casillaMech));
        GeneradorDeOpcionesVek generador = new GeneradorDeOpcionesVek(new BuscadorBfs());

        List<Opcion> opciones = generador.generarOpcionesPara(vek, tablero);

        AnuncioDeAtaque anuncioDeAtaque = opciones.get(0).ejecutar(vek);
        anuncioDeAtaque.ejecutarAtaque(tablero);

        //Avispon le hace 1 de danio a mech que tiene 2 de vida
        assertTrue(posicionAtaque.tiene(vek));
        assertEquals(1, mech.getVida());
    }

    @Test
    void seGeneraOpcionAtaqueAMechDeArtilleriaSinNecesidadDeMoverse(){
        Casilla origen = Casilla.crearEn(0, 0);
        Casilla casillaMech = Casilla.crearEn(1, 0);
        Mech mech = new MechDeArtilleria(); // tiene 2 de vida
        casillaMech.recibirPieza(mech);

        Vek vek = new Avispon(); // hace 1 de danio
        origen.recibirPieza(vek);

        Tablero tablero = new Tablero(List.of(origen, casillaMech));
        GeneradorDeOpcionesVek generador = new GeneradorDeOpcionesVek(new BuscadorBfs());

        List<Opcion> opciones = generador.generarOpcionesPara(vek, tablero);

        AnuncioDeAtaque anuncioDeAtaque = opciones.get(0).ejecutar(vek);
        anuncioDeAtaque.ejecutarAtaque(tablero);

        //Avispon le hace 1 de danio a mech que tiene 2 de vida
        assertTrue(origen.tiene(vek));
        assertEquals(1, mech.getVida());
    }
    @Test
    void luciernagaOpcionAtaqueEdificioLejano(){
        Casilla origen = Casilla.crearEn(0, 0);
        Casilla casilla2 = Casilla.crearEn(1,0);
        Casilla casilla3 = Casilla.crearEn(2,0);
        Casilla casilla4 = Casilla.crearEn(3,0);
        Casilla casilla5 = Casilla.crearEn(4,0);
        Casilla casilla6 = Casilla.crearEn(5,0);
        Casilla casillaEdificio = Casilla.crearEn(6, 0);
        RedElectrica red = new RedElectrica();
        Pieza edificio = new PiezaEdificio(red); // tiene 2 de vida
        casillaEdificio.recibirPieza(edificio);

        Vek vek = new Luciernaga(); // hace 1 de danio, movimiento 2
        origen.recibirPieza(vek);

        Tablero tablero = new Tablero(List.of(origen, casillaEdificio,casilla2,casilla3,casilla4,casilla5,casilla6));
        GeneradorDeOpcionesVek generador = new GeneradorDeOpcionesVek(new BuscadorBfs());

        List<Opcion> opciones = generador.generarOpcionesPara(vek, tablero);

        AnuncioDeAtaque anuncioDeAtaque = opciones.get(0).ejecutar(vek);
        anuncioDeAtaque.ejecutarAtaque(tablero);
        assertEquals(3,opciones.size());// puede atacar desde 3 lugares distintos
        //Avispon le hace 1 de danio a edificio que tiene 2 de vida
        assertEquals(4, red.integridadActual());
    }

    @Test
    void luciernagaTieneUnObstaculoEnMedioDelEdificioYnoGeneraOpcion(){
        Casilla origen = Casilla.crearEn(0, 0);
        Casilla casilla2 = Casilla.crearEn(1,0);
        Casilla casilla3 = Casilla.crearEn(2,0);
        Casilla casilla4 = Casilla.crearEn(3,0);
        Casilla casilla5 = Casilla.crearEn(4,0);
        Casilla casilla6 = Casilla.crearEn(5,0);
        Casilla casillaEdificio = Casilla.crearEn(6, 0);
        RedElectrica red = new RedElectrica();
        casilla2.recibirPieza(new PiezaMontania());
        Pieza edificio = new PiezaEdificio(red); // tiene 2 de vida
        casillaEdificio.recibirPieza(edificio);

        Vek vek = new Luciernaga(); // hace 1 de danio, movimiento 2
        origen.recibirPieza(vek);

        Tablero tablero = new Tablero(List.of(origen, casillaEdificio,casilla2,casilla3,casilla4,casilla5,casilla6));
        GeneradorDeOpcionesVek generador = new GeneradorDeOpcionesVek(new BuscadorBfs());

        List<Opcion> opciones = generador.generarOpcionesPara(vek, tablero);

        assertEquals(0,opciones.size());
    }
    @Test
    void luciernagaTieneUnaCasillaConAcidoDesdeDondeAtacarYnoGeneraOpcion(){
        Casilla origen = Casilla.crearEn(1, 1);
        Casilla casilla2 = new Casilla(new Coordenada(1,0),new Acido());
        Casilla casilla3 = new Casilla(new Coordenada(2,0),new Acido());
        Casilla casilla4 = Casilla.crearEn(3,0);
        Casilla casilla5 = Casilla.crearEn(4,0);
        Casilla casilla6 = Casilla.crearEn(5,0);
        Casilla casillaEdificio = Casilla.crearEn(6, 0);
        RedElectrica red = new RedElectrica();
        Pieza edificio = new PiezaEdificio(red); // tiene 2 de vida
        casillaEdificio.recibirPieza(edificio);

        Vek vek = new Luciernaga(); // hace 1 de danio, movimiento 2
        origen.recibirPieza(vek);

        Tablero tablero = new Tablero(List.of(origen, casillaEdificio,casilla2,casilla3,casilla4,casilla5,casilla6));
        GeneradorDeOpcionesVek generador = new GeneradorDeOpcionesVek(new BuscadorBfs());

        List<Opcion> opciones = generador.generarOpcionesPara(vek, tablero);

        assertEquals(0,opciones.size());
    }
    @Test
    void AvisponTieneUnaCasillaConAcidoDesdeDondeAtacarYSiGeneraOpcion(){
        Casilla origen = Casilla.crearEn(1, 1);
        Casilla casillaAcido = new Casilla(new Coordenada(1,0),new Acido());
        Casilla casillaEdificio = Casilla.crearEn(2, 0);
        RedElectrica red = new RedElectrica();
        Pieza edificio = new PiezaEdificio(red); // tiene 2 de vida
        casillaEdificio.recibirPieza(edificio);

        Vek vek = new Avispon(); // hace 1 de danio, movimiento 4
        origen.recibirPieza(vek);

        Tablero tablero = new Tablero(List.of(origen, casillaEdificio,casillaAcido));
        GeneradorDeOpcionesVek generador = new GeneradorDeOpcionesVek(new BuscadorBfs());

        List<Opcion> opciones = generador.generarOpcionesPara(vek, tablero);
        Opcion opcion =opciones.get(0);
        AnuncioDeAtaque anuncioDeAtaque = opcion.ejecutar(vek);
        anuncioDeAtaque.ejecutarAtaque(tablero);
        assertEquals(1,opciones.size());
        assertTrue(edificio.estaDestruida());
    }
    @Test
    public void generadorDeOpcionesParaAvisponGeneraOpcionAcercarseAEdificioSiNoHayNadaParaAtacar(){
        Casilla origen = Casilla.crearEn(1, 1);
        Casilla casillaAcido = new Casilla(new Coordenada(1,0),new Acido());
        Casilla casilla2 = new Casilla(new Coordenada(2,0),new Acido());
        Casilla casilla3 = Casilla.crearEn(3,0);
        Casilla casilla4 = Casilla.crearEn(4,0);
        Casilla casilla5 = Casilla.crearEn(5,0);
        Casilla casillaEdificio = Casilla.crearEn(6, 0);
        Pieza edificio = new PiezaEdificio(new RedElectrica());
        casillaEdificio.recibirPieza(edificio);

        Vek vek = new Avispon(); // hace 1 de danio, movimiento 4
        origen.recibirPieza(vek);

        Tablero tablero = new Tablero(List.of(origen, casillaEdificio,casillaAcido,casilla3,casilla4,casilla5,casilla2));
        GeneradorDeOpcionesVek generador = new GeneradorDeOpcionesVek(new BuscadorBfs());

        List<Opcion> opciones = generador.generarOpcionesPara(vek, tablero);
        Opcion opcion =opciones.get(0);
        AnuncioDeAtaque anuncioDeAtaque = opcion.ejecutar(vek);
        anuncioDeAtaque.ejecutarAtaque(tablero);
        assertTrue(casilla4.tiene(vek)); // El vek se acerca a 4 posiciones
        // assertEquals(1,opciones.size()); no hace falta esto, testeo por comportamiento
        assertFalse(edificio.estaDestruida());
    }
}
