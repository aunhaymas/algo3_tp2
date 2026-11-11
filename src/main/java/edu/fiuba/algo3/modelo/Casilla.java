package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.CasillaVaciaException;

public class Casilla {
    private boolean estaLibre;
    private Pieza pieza;
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
}
