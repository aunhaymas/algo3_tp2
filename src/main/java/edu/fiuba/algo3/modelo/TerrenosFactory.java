package edu.fiuba.algo3.modelo;

public class TerrenosFactory {
    
    public Efecto crearTerreno(String simbolo) {
        switch (simbolo) {
            case "S":
                return new Suelo();
            
            //Hay que agregar los demas casos de terreno, por ahora solo se implementa el caso de Suelo

            default:
                throw new IllegalArgumentException("Tipo de terreno desconocido: " + simbolo);
        }
    }
}
