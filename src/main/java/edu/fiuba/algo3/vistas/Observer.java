package edu.fiuba.algo3.vistas;

import java.util.Observable;

public interface Observer {
    public void update();
    public void deseleccionar(Observer o);
}
