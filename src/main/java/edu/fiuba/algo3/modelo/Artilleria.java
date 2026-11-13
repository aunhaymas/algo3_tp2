package edu.fiuba.algo3.modelo;

public class Artilleria extends Arma {

    public void atacar(Dañable objetivo) {
        objetivo.recibirDaño(1);
    }

    public void atacar(Trayectoria trayectoria) {
        // Deberia recorrer todas las casillas hasta que una NO este libre, De ahi hit!. 
        Casilla destino = trayectoria.getDestino();
        destino.recibirAtaque(new Ataque(1));
        // Empuja todos los adyacentes
        destino.empujarAdyacentes();
    }
}
