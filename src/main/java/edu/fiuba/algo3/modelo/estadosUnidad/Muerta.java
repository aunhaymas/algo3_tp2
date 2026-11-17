package edu.fiuba.algo3.modelo.estadosUnidad;

import edu.fiuba.algo3.modelo.Coordenada;
import edu.fiuba.algo3.modelo.ataques.Ataque;
import edu.fiuba.algo3.modelo.empujes.Empuje;
import edu.fiuba.algo3.modelo.empujes.EmpujeNulo;
import edu.fiuba.algo3.modelo.unidades.Unidad;

public class Muerta extends EstadoUnidad {
    @Override
    public int calcularDañoRecibido(int dañoBase) {
        return 0;
    }

    @Override
    public int calcularDañoAtaque(int dañoBase) {
        return 0;
    }

    @Override
    public boolean puedeAtacar() {
        return false;
    }

    @Override
    public void aplicarA(Unidad unidad) {

    }

    @Override
    public boolean estaContaminado() {
        return false;
    }

    @Override
    public Empuje calcularEmpuje(Ataque ataque, Coordenada origen) {
        return new EmpujeNulo();
    }
}
