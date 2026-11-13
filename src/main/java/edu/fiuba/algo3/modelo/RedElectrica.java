package edu.fiuba.algo3.modelo;

public class RedElectrica {
    private int vida = 5;
    public void edificioDestruido(){
        this.vida--;
    }
    public boolean estaDestruida(){
        return this.vida<1;
    }
    public int integridadActual(){
        return this.vida;
    }
}
