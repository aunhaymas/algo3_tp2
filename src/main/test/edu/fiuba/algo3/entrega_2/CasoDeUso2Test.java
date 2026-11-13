package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//Verificar que el daño a un edificio reduzca la Red Eléctrica en 1 punto.
public class CasoDeUso2Test {
   @Test
    public void danioAEdificioReduceSiempreRedElectrica1Punto(){
       RedElectrica redElectrica = new RedElectrica();
       Pieza edificio = new PiezaEdificio(redElectrica);
       DanioColision danio = new DanioColision(); //1 de danio
       edificio.recibirAtaque(danio);
       assertEquals(4,redElectrica.integridadActual());

   }

}
