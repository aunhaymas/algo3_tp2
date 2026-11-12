package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.CasillaVaciaException;

public class Casilla {
    private boolean estaLibre;
    private Pieza pieza;
    private Tablero tablero;
    private Coordenada coordenada;
    private TipoTerreno terrenoCasilla;
    public Casilla(Coordenada coordenada) {
        this.coordenada=coordenada;
        this.estaLibre=true;
        this.terrenoCasilla=new Suelo();
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

    public TipoTerreno getTerreno() {
        return this.terrenoCasilla;
    }

    public Pieza obtenerPieza() {
        if (estaLibre()){
            throw new CasillaVaciaException("");
        }
        return this.pieza;
    }

    public Coordenada getCoordenada() {
        return this.coordenada;
    }

    public void moverContenidoA(Casilla destino) {
        destino.recibirPieza(this.removerPieza());
    }
}
