package edu.fiuba.algo3.modelo;

import java.util.List;
import edu.fiuba.algo3.modelo.excepciones.MapaInvalidoException;

public class ParserTablero {
    private final TerrenosFactory fabricaTerrenos;
    private static final int LARGO_PREDEFINIDO = 8;
    private static final int ANCHO_PREDEFINIDO = 8;

    public ParserTablero(TerrenosFactory fabricaTerrenos) {
        this.fabricaTerrenos = fabricaTerrenos;
    }

    public Tablero parsear(List<String> lineas) {
        validarVacio(lineas);
        validarMapa8x8(lineas);
        
        int filas = LARGO_PREDEFINIDO;
        int columnas = ANCHO_PREDEFINIDO;
        Tablero tablero = new Tablero();

        for (int i = 0; i < filas; i++) {
            String linea = lineas.get(i);
            for (int j = 0; j < columnas; j++) {
                char simbolo = linea.charAt(j);
                Efecto terreno = fabricaTerrenos.crearTerreno(String.valueOf(simbolo));
                Coordenada coordenada = new Coordenada(j, i);
                Casilla casilla = new Casilla(coordenada,terreno);
                tablero.agregarCasilla(coordenada, casilla);
            }
        }

        return tablero;
    }

    private void validarVacio(List<String> lineas) {
    if (lineas.isEmpty()) {
        throw new MapaInvalidoException("El archivo de mapa está vacío");
        }
    }

    private void validarMapa8x8(List<String> lineas) {
        if (lineas.size() != LARGO_PREDEFINIDO) {
            throw new MapaInvalidoException("El mapa debe tener " + LARGO_PREDEFINIDO + " filas");
        }
        for (String linea : lineas) {
            if (linea.length() != ANCHO_PREDEFINIDO) {
                throw new MapaInvalidoException("Cada fila del mapa debe tener " + ANCHO_PREDEFINIDO + " columnas");
            }
        }
    }
}
