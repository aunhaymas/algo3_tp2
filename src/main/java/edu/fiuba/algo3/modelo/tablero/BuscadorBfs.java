package edu.fiuba.algo3.modelo.tablero;

import edu.fiuba.algo3.modelo.BuscaTrayectorias;
import edu.fiuba.algo3.modelo.Casilla;
import edu.fiuba.algo3.modelo.Coordenada;
import edu.fiuba.algo3.modelo.Trayectoria;
import edu.fiuba.algo3.modelo.unidades.Unidad;

import java.util.*;

public class BuscadorBfs implements BuscaTrayectorias {
    @Override
    public List<Trayectoria> trayectoriasDesde(Coordenada origen, Unidad unidad, Tablero contexto) {
        Map<Casilla, Casilla> anteriores = new HashMap<>();
        Queue<Casilla> pendientes = new ArrayDeque<>();
        Casilla casillaOrigen = contexto.obtenerCasilla(origen);
        Map<Casilla, Integer> distancias = new HashMap<>();

        List<Trayectoria> trayectorias = new ArrayList<>();

        pendientes.add(casillaOrigen);
        distancias.put(casillaOrigen, 0);

        while (!pendientes.isEmpty()) {
            Casilla actual = pendientes.remove();
            int distanciaActual = distancias.get(actual);
            if (distanciaActual == unidad.rangoMovimiento())continue;

            for (Casilla adyacente : contexto.obtenerAdyacentes(actual)) {
                if (distancias.containsKey(adyacente)) continue;
                if (!unidad.puedeTransitar(adyacente)) continue; //esto puede servirme en el proximo test
                int nuevaDistancia = distanciaActual + 1;
                anteriores.put(adyacente, actual);
                distancias.put(adyacente, nuevaDistancia);
                pendientes.add(adyacente);
                //Aca esta el bug de luciernaga pudiendo terminar en acido
                if (unidad.puedeTerminarEn(adyacente)) {
                    trayectorias.add(reconstruirTrayectoria(casillaOrigen, adyacente, anteriores)
                    );
                }
            }
        }

        return trayectorias;
    }
    private Trayectoria reconstruirTrayectoria(Casilla origen, Casilla destino, Map<Casilla, Casilla> anteriores) {
        List<Casilla> casillas = new ArrayList<>();
        Casilla actual = destino;
        while (actual != origen) {
            casillas.add(actual);
            actual = anteriores.get(actual);
        }
        casillas.add(origen);
        Collections.reverse(casillas);
        return new Trayectoria(casillas);
    }
}
