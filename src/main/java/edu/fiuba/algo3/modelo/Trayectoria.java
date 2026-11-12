package edu.fiuba.algo3.modelo;

import java.util.List;

public class Trayectoria {

    private final List<Casilla> casillas;
    private final Casilla destino;
    private final Casilla origen;
    private Direccion direccion;

    public Trayectoria(List<Casilla> casillas) {
        this.casillas = casillas;
        this.origen=casillas.get(0);
        this.destino=casillas.get(casillas.size() - 1);
    }

    public Trayectoria(List<Casilla> casillas, Direccion direccion) {
        this.casillas = casillas;
        this.origen=casillas.get(0);
        this.destino=casillas.get(casillas.size() - 1);
        this.direccion=direccion;
    }

    public void ejecutarTraslado() {
        origen.moverContenidoA(destino);
    }

    public Direccion getDireccion(){
        return this.direccion;
    }

    public int logitud() {
        return this.casillas.size()-1;
    }

    public boolean tieneObstaculos() {
        if (casillas.size() <= 2) return false;
        List<Casilla> casillasIntermedias = casillas.subList(1, casillas.size() - 1);
        return casillasIntermedias.stream().anyMatch(casilla -> !casilla.estaLibre());
    }

    public Casilla getDestino(){
        return destino;
    }

    public Casilla devolverPrimeraCasillaNoLibre(){
        for (Casilla casilla : casillas) {
            if (!casilla.estaLibre()) {
                return casilla;
            }
        }
        return destino;
    }

    public boolean esTrayectoriaCorta() {
        return casillas.size() <= 2;
    }
}
