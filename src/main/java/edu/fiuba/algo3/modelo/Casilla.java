package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.CasillaVaciaException;
import edu.fiuba.algo3.modelo.excepciones.TrayectoriaInvalidaException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public int obtenerDistanciaX(Casilla destino) {
        return - destino.obtenerDistanciaX(this.coordenada);
    }

    public int obtenerDistanciaY(Coordenada coordenada) {
        return coordenada.getY() - this.coordenada.getY();
    }

    public int obtenerDistanciaY(Casilla destino) {
        return - destino.obtenerDistanciaY(this.coordenada);
    }

    public boolean tiene(Pieza pieza) {
        return this.pieza == pieza;
    }

    private Trayectoria obtenerTrayectoriaPorDistanciaYDireccion(Direccion direccion, int distancia) {
        List<Casilla> casillas = new ArrayList<Casilla>(Arrays.asList(this));
        Casilla previa = this;
        for (int i = 1; i <= Math.abs(distancia); i++) {
            Casilla siguiente = previa.casillaAdyacente(direccion);
            casillas.add(siguiente);
            previa = siguiente;
        }
        return new Trayectoria(casillas, direccion);
    }

    public Trayectoria obtenerTrayectoria(Casilla destino) {
        int distanciaX = this.obtenerDistanciaX(destino);
        int distanciaY = this.obtenerDistanciaY(destino);
        if (distanciaX != 0) {

            if (distanciaY != 0) {
                throw new TrayectoriaInvalidaException();
            }
            Direccion direccion = distanciaX < 0 ? new Oeste() : new Este();
            return this.obtenerTrayectoriaPorDistanciaYDireccion(direccion, distanciaX);

        } else {

            if (distanciaY != 0) {
                Direccion direccion = distanciaY < 0 ? new Norte() : new Sur();
                return this.obtenerTrayectoriaPorDistanciaYDireccion(direccion, distanciaY);
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

    public void recibirAtaque(Ataque ataque) {
        this.pieza.recibirAtaque(ataque);
        if (this.pieza.estaDestruida()) {
            limpiarCasilla();
        }
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
        if (this.estaLibre()) return;
        Casilla adyacente = this.casillaAdyacente(direccion);
        this.moverContenidoA(adyacente);
    }

    public void empujarAdyacentes() {
        Norte norte = new Norte();
        this.casillaAdyacente(norte).empujar(norte);
        Este este = new Este();
        this.casillaAdyacente(este).empujar(este);
        Sur sur = new Sur();
        this.casillaAdyacente(sur).empujar(sur);
        Oeste oeste = new Oeste();
        this.casillaAdyacente(oeste).empujar(oeste);
    }
}
