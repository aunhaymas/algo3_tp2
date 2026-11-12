package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.SimboloInvalidoException;

public class TerrenosFactory {
    
    public TipoTerreno crearTerreno(String simbolo) {
        switch (simbolo) {
            case "S":
                return new Suelo();
            case "E":
                return new Edificio();
            case "A":
                return new Agua();
            case "M":
                return new Montaña();
            case "Á":
                return new Acido();
                
            default:
                throw new SimboloInvalidoException("Tipo de terreno desconocido: " + simbolo);
        }
    }
}
