package edu.fiuba.algo3.modelo.partida;
import java.util.List;
import java.util.Map;

import edu.fiuba.algo3.modelo.tablero.LectorArchivo;
import edu.fiuba.algo3.modelo.tablero.ParserTablero;
import edu.fiuba.algo3.modelo.unidades.ParserUnidades;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.unidades.Pieza;
import edu.fiuba.algo3.modelo.unidades.UbicadorUnidadesIniciales;
import edu.fiuba.algo3.modelo.Coordenada;
import java.nio.file.Path;

public class ArmadorDePartida {

    private final LectorArchivo lector;
    private final ParserTablero parserMapa;
    private final ParserUnidades parserUnidades;
    private final UbicadorUnidadesIniciales ubicadorUnidades;
    
    public ArmadorDePartida(LectorArchivo lector, ParserTablero parserMapa, ParserUnidades parserUnidades) {
        
        this.lector = lector;
        this.parserMapa = parserMapa;
        this.parserUnidades = parserUnidades;
        this.ubicadorUnidades = new UbicadorUnidadesIniciales();

    }

    public Partida armarPartida(Path archivoMapa, Path archivoUnidades) {
        
        Tablero tablero = parserMapa.parsear(lector.leerArchivo(archivoMapa));
        Map<Coordenada, Pieza> unidades = parserUnidades.parsear(lector.leerArchivo(archivoUnidades));

        ubicadorUnidades.ubicar(unidades, tablero);

        return new Partida(tablero);
    }

}
