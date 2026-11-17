package edu.fiuba.algo3.modelo.ataques;

import edu.fiuba.algo3.modelo.Direccion;
import edu.fiuba.algo3.modelo.empujes.EmpujeEfectivo;

public class AtaquePunio extends Ataque {
    public AtaquePunio() {
        super(2);
    }
    public AtaquePunio(Direccion direccion) {
        super(2, new EmpujeEfectivo(direccion.obtenerDireccion()));
    }
}
