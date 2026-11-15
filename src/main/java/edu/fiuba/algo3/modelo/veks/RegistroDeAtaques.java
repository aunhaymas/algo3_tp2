package edu.fiuba.algo3.modelo.veks;

import java.util.LinkedList;
import java.util.Queue;

public class RegistroDeAtaques {
    private final Queue<AnuncioDeAtaque> ataques;

    public RegistroDeAtaques() {
        this.ataques = new LinkedList<>();
    }

    public void registrarAtaque(AnuncioDeAtaque anuncioDeAtaque) {
        this.ataques.add(anuncioDeAtaque);
    }

    public void ejecutarSiguienteAtaque() {
        AnuncioDeAtaque ataque = this.ataques.poll();
        ataque.ejecutarAtaque();
    }
}
