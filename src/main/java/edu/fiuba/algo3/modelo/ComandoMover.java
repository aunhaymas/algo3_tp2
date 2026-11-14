package edu.fiuba.algo3.modelo;

public class ComandoMover {
    private final Mech mech;
    private final Trayectoria trayectoria;
    public ComandoMover(Mech mech, Trayectoria trayectoria) {
        this.mech = mech;
        this.trayectoria=trayectoria;
    }

    public void ejecutar() {
        mech.moverseA(trayectoria);
    }

    public void deshacer() {
        mech.restaurarMovimiento();
        mech.moverseA(trayectoria.invertir());
        mech.restaurarMovimiento();
    }
}
