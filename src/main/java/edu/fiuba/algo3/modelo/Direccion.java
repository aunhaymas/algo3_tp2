package edu.fiuba.algo3.modelo;

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
}
