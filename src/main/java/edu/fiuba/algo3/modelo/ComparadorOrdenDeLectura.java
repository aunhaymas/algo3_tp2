package edu.fiuba.algo3.modelo;

import java.util.Comparator;

public class ComparadorOrdenDeLectura implements Comparator<Casilla> {
	@Override 
	public int compare(Casilla casillaA, Casilla casillaB) {
		if (casillaA.equals(casillaB)) {
			return 0;
		}
		return casillaA.precedeEnOrdenLectura(casillaB) ? -1 : 1;
	}
}
