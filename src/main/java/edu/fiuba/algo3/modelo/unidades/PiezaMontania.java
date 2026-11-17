package edu.fiuba.algo3.modelo.unidades;
import edu.fiuba.algo3.modelo.LocalizadorEntidad;
import edu.fiuba.algo3.modelo.Casilla;
import edu.fiuba.algo3.modelo.ataques.Ataque;
import edu.fiuba.algo3.modelo.empujes.Empuje;
import edu.fiuba.algo3.modelo.empujes.EmpujeNulo;

public class PiezaMontania implements Pieza{
    @Override
    public void entrarEn(Casilla casilla) {
        casilla.recibirPieza(this);
    }

    @Override
    public Empuje recibirAtaqueConEmpuje(Ataque ataque) {
        return new EmpujeNulo();
    }
    @Override 
    public void bloquearEmergencia() {
        // Nada
    }

    @Override
    public void recibirAtaque(Ataque ataque) {

    }

    @Override
    public boolean estaDestruida() {
        return false;
    }

    @Override
    public int getVida() {
        return 1;
    }

    @Override
	public void reportarA(LocalizadorEntidad localizador, Casilla casilla) {
		localizador.registrar(this, casilla);
	}
}
