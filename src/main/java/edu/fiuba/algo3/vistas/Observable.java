package edu.fiuba.algo3.vistas;

import java.util.ArrayList;
import java.util.List;

public class Observable {
    // Add an Observer
    // Notify all Observers
    private List<Observer> vistas = new ArrayList<>();

    public void agregarObserver(Observer o) {
        vistas.add(o);
    }

    public void deseleccionarTodos(Observer obs) {
        for (Observer o: vistas) {
            o.deseleccionar(obs);
        }
    }

    public void notificarTodos() {
        for (Observer vista : vistas) {
            vista.update();
        }
    }
}
