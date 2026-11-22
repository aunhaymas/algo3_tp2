package edu.fiuba.algo3.modelo.veks;

import edu.fiuba.algo3.modelo.armas.ArmaAguijon;
import edu.fiuba.algo3.modelo.unidades.Volador;

public class Avispon extends Vek {
    public Avispon() {
        super(2, 4);
        this.arma = new ArmaAguijon();
        this.tipoMovimiento = new Volador();
    }

    @Override
    public void sumergir() {
        // Se sobrescribe vacío para que no reciba daño ni cambie de estado
    }

    @Override
    public void contaminar(){
        //al ser volador el acido no lo contamina
    }
    
}
