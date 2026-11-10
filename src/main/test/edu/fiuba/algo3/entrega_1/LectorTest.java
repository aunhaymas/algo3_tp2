package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.excepciones.ArchivoIlegibleException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.Test;

public class LectorTest {
    
    @Test
    public void LectorLeeCorrectamenteElArchivo() {
        LectorArchivo lector = new LectorArchivo();
        List<String> lineas;
        Path rutaTablero = Paths.get("src/main/java/edu/fiuba/algo3/tablero/tableroTest.txt");
        lineas = lector.leerArchivo(rutaTablero);
        assertEquals("ESSSEESE", lineas.get(0));
    }

    @Test
    public void LeerArchivoInexistenteLanzaExcepcion() {
        LectorArchivo lector = new LectorArchivo();
        Path rutaInexistente = Paths.get("ruta/inventada/no_existe.txt");
        assertThrows(ArchivoIlegibleException.class, () -> {
            lector.leerArchivo(rutaInexistente);
        });
    }
}
