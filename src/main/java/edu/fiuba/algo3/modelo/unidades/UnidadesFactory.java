package edu.fiuba.algo3.modelo.unidades;

import edu.fiuba.algo3.modelo.veks.*;
import edu.fiuba.algo3.modelo.mechs.*;

public class UnidadesFactory {
    
    public Pieza crearUnidad(String nombre) {
        switch (nombre) {
            case "Mech de Combate":
                return new MechDeCombate();
            case "Mech Cañon":
                return new MechDeCanon();
            case "Mech de Artillería":
                return new MechDeArtilleria();
            case "Saltador":
                return new Saltador();
            case "Luciérnaga":
                return new Luciernaga();
            case "Avispón":
                return new Avispon();
                
            default:
                throw new IllegalArgumentException("Tipo de unidad desconocido: " + nombre);
        }
    }
}
