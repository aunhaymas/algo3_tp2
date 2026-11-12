package edu.fiuba.algo3.modelo;

public class Canon extends Arma {

    public void atacar(Dañable objetivo) {
        objetivo.recibirDaño(1);
    }

    public void atacar(Trayectoria trayectoria) {
        // Deberia recorrer todas las casillas hasta que una NO este libre, De ahi hit!. 
        Casilla hit = trayectoria.devolverPrimeraCasillaNoLibre();
        hit.recibirAtaque(new Ataque(1));
        hit.empujar(trayectoria.getDireccion());
    }
}
