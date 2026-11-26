package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.PosicionOcupadaException;
import edu.fiuba.algo3.modelo.excepciones.TrayectoriaInvalidaException;

import java.util.ArrayList;
import java.util.List;
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
    public Coordenada trasladar(Coordenada cambio) {
        return new Coordenada(this.x + cambio.x,this.y +cambio.y);
    }

    public void throwPosicionOcupada() {
        throw new PosicionOcupadaException(x, y);
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

    public Coordenada multiplicar(int i) {
        return new Coordenada(this.x*(i),this.y*(i));
    }
    public List<Coordenada> trayectoriaA(Coordenada destino){
        Coordenada direccion = this.direccionA(destino);
        List<Coordenada> trayectoria = new ArrayList<Coordenada>();
        Coordenada actual = this;
        while (!actual.equals(destino)) {
            trayectoria.add(actual);
            actual = actual.trasladar(direccion);
        }
        trayectoria.add(actual);
        return trayectoria;
    }

    public Coordenada direccionA(Coordenada destino) {
        if (!estaAlineadaCon(destino)) { throw new TrayectoriaInvalidaException(); }
        Coordenada desplazo = destino.trasladar(this.multiplicar(-1));
        return direccion(desplazo);
    }
    public boolean estaAlineadaCon(Coordenada otra) {
        return this.x == otra.x || this.y == otra.y;
    }
    private Coordenada direccion(Coordenada desplazo){
        int x = Integer.compare(desplazo.x, 0);
        int y = Integer.compare(desplazo.y, 0);
        return new Coordenada(x,y);
    }
}
