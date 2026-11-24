package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.empujes.Este;
import edu.fiuba.algo3.modelo.empujes.Norte;
import edu.fiuba.algo3.modelo.empujes.Oeste;
import edu.fiuba.algo3.modelo.empujes.Sur;

import java.util.List;

public class Direccion {
    private Coordenada direccion;
    public Direccion(Coordenada direccion){
        this.direccion=direccion;
    }
    public Coordenada obtenerCoordenadaAdyacente(Coordenada coordenada) {
        return coordenada.trasladar(this.obtenerDireccion());
    }

    public Coordenada obtenerDireccion(){
        return  this.direccion;
    };

    public Direccion invertir(){
        return new Direccion(this.direccion.multiplicar(-1));
    }
    public static List<Direccion> cardinales() {
        return List.of(
                new Norte(),
                new Sur(),
                new Este(),
                new Oeste()
        );
    }
}
