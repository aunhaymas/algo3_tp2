package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Acido;
import edu.fiuba.algo3.modelo.Unidad;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class AcidoTest {

    @Test
    void aplicarEfectoAcidoContaminaALaUnidad(){
        Unidad unidadMock = Mockito.mock(Unidad.class);
        Acido acidoMock = Mockito.mock(Acido.class);

        acidoMock.aplicarEfecto(unidadMock);

        Mockito.verify(acidoMock).aplicarEfecto(unidadMock);
    }
}
