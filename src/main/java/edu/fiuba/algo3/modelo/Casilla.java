package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.ataques.Ataque;
import edu.fiuba.algo3.modelo.empujes.*;
import edu.fiuba.algo3.modelo.estadoCasilla.EstadoCasilla;
import edu.fiuba.algo3.modelo.estadoCasilla.EstadoOcupada;
import edu.fiuba.algo3.modelo.estadoCasilla.EstadoVacia;
import edu.fiuba.algo3.modelo.excepciones.TrayectoriaInvalidaException;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.terrenos.Suelo;
import edu.fiuba.algo3.modelo.terrenos.TipoTerreno;
import edu.fiuba.algo3.modelo.unidades.Atacable;
import edu.fiuba.algo3.modelo.unidades.Pieza;
import edu.fiuba.algo3.modelo.unidades.Unidad;
import edu.fiuba.algo3.modelo.veks.Vek;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Casilla implements Atacable{
    private Tablero tablero;
    private Coordenada coordenada;
    private TipoTerreno terrenoCasilla;
    private EstadoCasilla estado;
    public static Casilla crearEn(int x, int y){
        return new Casilla(new Coordenada(x,y));
    }
    public Casilla(Coordenada coordenada) {
        this.coordenada=coordenada;
        this.estado = new EstadoVacia();
        this.terrenoCasilla=new Suelo();
        limpiarCasilla();
    }
    public Casilla(Coordenada coordenada, TipoTerreno terreno) {
        this.coordenada=coordenada;
        this.terrenoCasilla=terreno;
        limpiarCasilla();
    }

    public void setTablero(Tablero tablero) {
        this.tablero=tablero;
    }

    public Casilla casillaAdyacente(Direccion direccion) {
        return this.tablero.obtenerCasilla(direccion.obtenerCoordenadaAdyacente(this.coordenada));
    }

    public boolean esAdyacente(Casilla otraCasilla, Direccion direccion) {
        return otraCasilla == this.casillaAdyacente(direccion);
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
        return estado.tienePieza(pieza);
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
        return this.estado.estaLibre();
    }

    public void recibirPieza(Pieza pieza) {
        if (!this.estado.estaLibre()) {
            coordenada.throwPosicionOcupada();
        }
        pieza.entrarEn(this);
        this.estado = new EstadoOcupada(pieza);
    }

    public Pieza removerPieza() {

        Pieza piezaGuardada =this.obtenerPieza();
        limpiarCasilla();
        return piezaGuardada;
    }

    @Override
    public void recibirAtaque(Ataque ataque) {

        Empuje empuje = this.estado.recibirAtaque(ataque);
        empuje.empujar(this);
    }

    public Coordenada direccionA(Casilla destino){
        return destino.calcularDireccionDesde(this.coordenada);
    }
    private Coordenada calcularDireccionDesde(Coordenada origen){
        return origen.direccionA(this.coordenada);
    }
    private void limpiarCasilla(){
        this.estado = new EstadoVacia();
    }

    public void aplicarEfecto(Unidad unidad) {
        this.terrenoCasilla.aplicarEfecto(unidad);
    }

    public TipoTerreno getTerreno() {
        return this.terrenoCasilla;
    }

    public Pieza obtenerPieza() {
        return this.estado.obtenerPieza();
    }

    public Coordenada getCoordenada() {
        return this.coordenada;
    }

    public void moverContenidoA(Casilla destino) {
        if (!destino.estaLibre()) {
            coordenada.throwPosicionOcupada();
        }
        destino.recibirPieza(this.removerPieza());
        this.limpiarCasilla();
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

    // Casilla conoce su espacio fisico
    // La precondicion es que, quien llame a este metodo,
    // le envie una casilla valida
    public void empujarA(Casilla destino) {
        this.estado.empujarA(this,destino);
    }

    public void recibirEmpuje(Casilla origen, Pieza pieza) {
        this.estado=this.estado.recibirEmpuje(origen,this,pieza);
    }

    public void emergencia(Vek vek){
        this.estado = this.estado.emergencia(vek);
    }

    public void resolverEmergencia(){
        this.estado = this.estado.resolverEmergenciaIniciarTurno();
    }
    public Coordenada obtenerAdyacente(Direccion direccion){
        return  direccion.obtenerCoordenadaAdyacente(this.coordenada);
    }
}
