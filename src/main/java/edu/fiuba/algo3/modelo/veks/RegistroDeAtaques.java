package edu.fiuba.algo3.modelo.veks;

import edu.fiuba.algo3.modelo.tablero.Tablero;

import java.util.LinkedList;
import java.util.Queue;

public class RegistroDeAtaques {
    private final Queue<AnuncioDeAtaque> ataques;

    public RegistroDeAtaques() {
        this.ataques = new LinkedList<>();
    }

    public void registrarAtaque(AnuncioDeAtaque anuncioReal) {
        this.ataques.add(anuncioReal);
    }

    public void ejecutarSiguienteAtaque(Tablero contexto) {
        AnuncioDeAtaque ataque = this.ataques.poll();
        ataque.ejecutarAtaque(contexto);
    }
}
