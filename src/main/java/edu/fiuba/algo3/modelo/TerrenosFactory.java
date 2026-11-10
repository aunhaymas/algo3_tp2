package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.SimboloInvalidoException;

public class TerrenosFactory {
    
    public Efecto crearTerreno(String simbolo) {
        switch (simbolo) {
            case "S":
                return new Suelo();
            case "E":
                return new Edificio();
            //Hay que agregar los demas casos de terreno, por ahora solo se implementa el caso de Suelo

            default:
                throw new SimboloInvalidoException("Tipo de terreno desconocido: " + simbolo);
        }
    }
}
