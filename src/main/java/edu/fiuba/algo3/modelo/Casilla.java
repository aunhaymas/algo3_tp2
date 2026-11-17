package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.tablero.*;
import edu.fiuba.algo3.modelo.estadosUnidad.*;
import edu.fiuba.algo3.modelo.mechs.*;
import edu.fiuba.algo3.modelo.unidades.*;
import edu.fiuba.algo3.modelo.empujes.*;
import edu.fiuba.algo3.modelo.terrenos.*;
import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.ataques.*;
import edu.fiuba.algo3.modelo.excepciones.*;
import edu.fiuba.algo3.modelo.veks.*;
import edu.fiuba.algo3.modelo.estadoCasilla.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Casilla implements Atacable{
    private boolean estaLibre;
    private Pieza pieza;
    private Tablero tablero;
    private Coordenada coordenada;
    private TipoTerreno terrenoCasilla;
    private boolean tieneEmergenciaActiva;
    private EstadoEmergencia estadoEmergencia;
    private EstadoCasilla estado;

    public Casilla(Coordenada coordenada) {
        this.coordenada=coordenada;
        this.estado = new EstadoVacia();
        this.estaLibre=true;
        this.terrenoCasilla=new Suelo();
        this.estadoEmergencia = null;
        limpiarCasilla();
    }
    public Casilla(Coordenada coordenada, TipoTerreno terreno) {
        this.coordenada=coordenada;
        this.estaLibre=true;
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

    public int calcularDistanciaCon(Casilla casilla) {
        return coordenada.distanciaA(casilla.getCoordenada());
    }

    public boolean precedeEnOrdenLectura(Casilla otra) {
        return this.coordenada.precedeEnOrdenLectura(otra.getCoordenada());
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

    public void activarEmergencia() {
        this.estadoEmergencia = new EmergenciaActiva();
    }

    public void resolverEmergencia(Vek nuevoVek) {
        this.estadoEmergencia = this.estadoEmergencia.resolver(this, nuevoVek);
    }

    public boolean tieneEmergenciaActiva() {
        return this.estadoEmergencia.estaActiva();
    }

    void notificarBloqueoDeEmergencia() {
        this.pieza.bloquearEmergencia();
    }

    public boolean estaLibre() {
        return this.estado.estaLibre();
    }

    public void recibirPieza(Pieza pieza) {
        pieza.entrarEn(this);
        this.pieza=pieza;
        this.estado = new EstadoOcupada(pieza);
        // pieza.entrarEn(this) para aplicar efecto
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

    private void limpiarCasilla(){
        this.estado = new EstadoVacia();
        this.pieza=new PiezaNula();
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
        if (!this.equals(destino)) {
            destino.recibirPieza(this.removerPieza());
            this.limpiarCasilla();
        }
    }

    @Override 
    public boolean equals(Object otro) {
        if (this == otro) {
            return true;
        }
        if (!(otro instanceof Casilla)) {
            return false;
        }
        Casilla casillaOtro = (Casilla)otro;
        return this.coordenada.equals(casillaOtro.getCoordenada());
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
        // TODO Refactorizar para eliminar if
        if (destino.estaLibre()) moverContenidoA(destino);
        else{
            DanioColision colision = new DanioColision();
            destino.recibirAtaque(colision);
            recibirAtaque(colision);
        }
    }
    
    public void recibirEmpuje(Casilla origen, Pieza pieza) {
        this.estado = this.estado.recibirEmpuje(origen, this, pieza);
    }

    public void reportarA(LocalizadorEntidad localizador) {
        this.pieza.reportarA(localizador, this);
    }
}
