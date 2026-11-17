package edu.fiuba.algo3.modelo.ataques;

import edu.fiuba.algo3.modelo.Coordenada;
import edu.fiuba.algo3.modelo.Direccion;
import edu.fiuba.algo3.modelo.empujes.Empuje;
import edu.fiuba.algo3.modelo.empujes.EmpujeEfectivo;

public class AtaqueCanon extends Ataque {
    public AtaqueCanon() {
        super(1);
    }
    public AtaqueCanon(Direccion direccion) {
        super(1, new EmpujeEfectivo(direccion.obtenerDireccion()));
    }
}
