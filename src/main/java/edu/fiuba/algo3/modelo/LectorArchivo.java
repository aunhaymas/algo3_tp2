package edu.fiuba.algo3.modelo;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import edu.fiuba.algo3.modelo.excepciones.ArchivoIlegibleException;
import java.nio.file.Path;

public class LectorArchivo {
    public List<String> leerArchivo(Path ruta){
        try {
            return Files.readAllLines(ruta);
        } catch (IOException e) {
            throw new ArchivoIlegibleException("No se pudo leer el archivo", e);
        }
    }
}
