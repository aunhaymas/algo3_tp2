package edu.fiuba.algo3.modelo;

import java.util.Objects;

public class Coordenada {
    private final int x;
    private final int y;

    public Coordenada(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int distanciaA(Coordenada otra) {
        return Math.max(Math.abs(this.x - otra.getX()), Math.abs(this.y - otra.getY()));
    }


    public Coordenada trasladar(int movX, int movY) {
        return new Coordenada(this.x + movX, this.y + movY);
    }

    /**
     * Determina si la coordenada precede la otra en un orden de lectura,
     * es decir, de arriba a abajo y de izquierda a derecha.
     */
    public boolean precedeEnOrdenLectura(Coordenada otra) {
        if (this.y != otra.getY()) {
            return this.y < otra.getY();
        }
       return this.x < otra.getX();
    }

    //obligatorio para usar el hashmap
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordenada that = (Coordenada) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y); 
    }

}
