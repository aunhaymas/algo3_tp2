package edu.fiuba.algo3.modelo;

public class Ataque {
    protected int danio;
    private Direccion direccionEmpuje;

    public Ataque(int danio) {
        this.danio = danio;
        this.direccionEmpuje = null;
    }

    public Ataque(int danio, Direccion direccionEmpuje) {
        this.danio = danio;
        this.direccionEmpuje = direccionEmpuje;
    }

    public int getDanio() {
        return this.danio;
    }

    public Direccion getDireccionEmpuje() {
        return this.direccionEmpuje;
    }

    public boolean tieneEmpuje() {
        return this.direccionEmpuje != null;
    }

    public void efectuarAtaque(Unidad unidad) {
        unidad.recibirDaño(danio);
    }
}
