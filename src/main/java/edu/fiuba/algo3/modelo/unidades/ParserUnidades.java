package edu.fiuba.algo3.modelo.unidades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.fiuba.algo3.modelo.Casilla;
import edu.fiuba.algo3.modelo.Coordenada;
import edu.fiuba.algo3.modelo.excepciones.MapaInvalidoException;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.terrenos.TerrenosFactory;
import edu.fiuba.algo3.modelo.terrenos.TipoTerreno;
import edu.fiuba.algo3.modelo.excepciones.FormatoArchivoUnidadesInvalidoException;

public class ParserUnidades {
    
    private final UnidadesFactory fabricaUnidades;
    private static final int CANTIDAD_UNIDADES_FIJA = 6;

    public ParserUnidades(UnidadesFactory fabricaUnidades) {
        this.fabricaUnidades = fabricaUnidades;
    }

    public Map<Coordenada, Pieza> parsear(List<String> lineas) {
        validarVacio(lineas);

        Map<Coordenada, Pieza> unidades = new HashMap<>();

        for (String linea : lineas) {

            int inicioParentesis = linea.lastIndexOf('(');
            int finParentesis = linea.lastIndexOf(')');

            String nombreUnidad = linea.substring(0, inicioParentesis).trim();

            String coordenadasString = linea.substring(inicioParentesis + 1, finParentesis);
            String[] partesCoordenada = coordenadasString.split(",");

            if (partesCoordenada.length != 2) {
                throw new FormatoArchivoUnidadesInvalidoException("Coordenadas inválidas en línea: " + linea);
            }

            int x = Integer.parseInt(partesCoordenada[0].trim());
            int y = Integer.parseInt(partesCoordenada[1].trim());

            Pieza unidad = fabricaUnidades.crearUnidad(nombreUnidad);
            Coordenada coordenada = new Coordenada(x, y);

            unidades.put(coordenada, unidad);
        }
        
        return unidades;
    }

    private void validarVacio(List<String> lineas) {
    if (lineas.isEmpty()) {
        throw new MapaInvalidoException("El archivo de mapa está vacío");
        }
    }
}
