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
        if (this.esTrayectoriaCorta()) return false;
        List<Casilla> casillasIntermedias = casillas.subList(1, casillas.size() - 1);
        return casillasIntermedias.stream().anyMatch(casilla -> !casilla.estaLibre());
    }

    public Casilla getDestino(){
        return destino;
    }

    public Casilla devolverPrimeraCasillaNoLibre(){
        boolean primero = true;
        for (Casilla casilla : casillas) {
            if (primero) {
                // Saltar el primero
                primero = false;
                continue;
            }
            if (!casilla.estaLibre()) {
                return casilla;
            }
        }
        return destino;
    }

    public boolean sonCasillasAdyacentes(Direccion direccion){
        Casilla previo = null;
        for (Casilla casilla: casillas) {
            if ((previo != null) && (!previo.esAdyacente(casilla, direccion))) {
                return false;
            }
            previo = casilla;
        }
        return true;
    }

    public boolean esTrayectoriaCorta() {
        return casillas.size() <= 2;
    }
}
