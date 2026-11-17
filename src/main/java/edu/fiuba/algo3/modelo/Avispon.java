package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Avispon extends Vek {
    public Avispon() {
        super(2, 4);
        this.arma = new ArmaAguijon();
    }

    @Override
    public void sumergir() {
        // Se sobrescribe vacío para que no reciba daño ni cambie de estado
    }
}