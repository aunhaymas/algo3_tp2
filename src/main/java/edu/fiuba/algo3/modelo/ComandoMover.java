package edu.fiuba.algo3.modelo;

public class ComandoMover {
    private final Mech mech;
    private final Trayectoria trayectoria;
    private Memento memento;

    public ComandoMover(Mech mech, Trayectoria trayectoria) {
        this.mech = mech;
        this.trayectoria=trayectoria;
    }

    public void ejecutar() {
        memento = mech.guardarEstado();
        mech.moverseA(trayectoria);
    }

    public void deshacer() {
        mech.restaurarMovimiento(trayectoria.invertir());
        mech.restaurarEstado(this.memento);
    }
}
