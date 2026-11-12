package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.CasillaVaciaException;
import edu.fiuba.algo3.modelo.excepciones.TrayectoriaInvalidaException;

import java.util.List;
import java.util.ArrayList;

public class Casilla {
    private boolean estaLibre;
    private Pieza pieza;
    private Tablero tablero;
    private Coordenada coordenada;
    private Efecto terrenoCasilla;
    public Casilla(Coordenada coordenada) {
        this.estaLibre=true;
        this.terrenoCasilla=new Suelo();
        limpiarCasilla();
    }
    public Casilla(Coordenada coordenada, Efecto terreno) {
        this.estaLibre=true;
        this.terrenoCasilla=terreno;
        limpiarCasilla();
    }

    public void setPosicion(Tablero tablero, Coordenada coordenada) {
        this.tablero=tablero;
        this.coordenada=coordenada;
    }

    public Casilla casillaAdyacente(Direccion direccion) {
        return this.tablero.obtenerCasilla(direccion.obtenerCoordenadaAdyacente(this.coordenada));
    }

    public int obtenerDistanciaX(Coordenada coordenada) {
        return coordenada.getX() - this.coordenada.getX();
    }

    public int obtenerDistanciaY(Coordenada coordenada) {
        return coordenada.getY() - this.coordenada.getY();
    }

    public boolean tiene(Pieza pieza) {
        return this.pieza == pieza;
    }

    public Trayectoria obtenerTrayectoria(Casilla destino) {
        int distanciaX = destino.obtenerDistanciaX(this.coordenada);
        int distanciaY = destino.obtenerDistanciaY(this.coordenada);
        if (distanciaX != 0) {

            if (distanciaY != 0) {
                throw new TrayectoriaInvalidaException();
            }
            Direccion direccion = distanciaX < 0 ? new Oeste() : new Este();
            List<Casilla> casillas = List.of(this);
            for (int i = 1; i < Math.abs(distanciaX); i++) {
                casillas.add(this.casillaAdyacente(direccion));
            }
            return new Trayectoria(casillas, direccion);

        } else {

            if (distanciaY != 0) {
                Direccion direccion = distanciaY < 0 ? new Norte() : new Sur();
                List<Casilla> casillas = List.of(this);
                for (int i = 1; i < Math.abs(distanciaY); i++) {
                    casillas.add(this.casillaAdyacente(direccion));
                }
                return new Trayectoria(casillas, direccion);
            } else {
                throw new TrayectoriaInvalidaException();
            }

        }
    }

    public boolean estaLibre() {
        return estaLibre;
    }

    public void recibirPieza(Pieza pieza) {
        this.estaLibre=false;
        this.pieza=pieza;
        pieza.entrarEn(this);
        // pieza.entrarEn(this) para aplicar efecto
    }

    public Pieza removerPieza() {

        Pieza piezaGuardada =this.obtenerPieza();
        limpiarCasilla();
        return piezaGuardada;
    }

    public boolean recibirAtaque(Ataque ataque) {
        boolean hit = this.pieza.recibirAtaque(ataque);
        if (this.pieza.estaDestruida()) {
            limpiarCasilla();
        }
        return hit;
    }


    private void limpiarCasilla(){
        this.estaLibre = true;
        this.pieza=new PiezaNula();
    }

    public void aplicarEfecto(Unidad unidad) {
        this.terrenoCasilla.aplicarEfecto(unidad);
    }

    public Efecto getTerreno() {
        return this.terrenoCasilla;
    }

    public Pieza obtenerPieza() {
        if (estaLibre()){
            throw new CasillaVaciaException("");
        }
        return this.pieza;
    }


    public void moverContenidoA(Casilla destino) {
        destino.recibirPieza(this.removerPieza());
    }

    public void empujar(Direccion direccion) {
        Casilla adyacente = this.casillaAdyacente(direccion);
        this.moverContenidoA(adyacente);
    }
}
