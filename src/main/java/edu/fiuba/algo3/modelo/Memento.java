package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.mechs.Mech;

public interface Memento {
    public void restaurarEstadoEn(Mech mech);
}
