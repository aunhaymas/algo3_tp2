package edu.fiuba.algo3.vistas;

import java.util.List;

import edu.fiuba.algo3.modelo.Coordenada;
import edu.fiuba.algo3.modelo.mechs.Mech;
import edu.fiuba.algo3.modelo.mechs.MechDeArtilleria;
import edu.fiuba.algo3.modelo.mechs.MechDeCanon;
import edu.fiuba.algo3.modelo.mechs.MechDeCombate;
import edu.fiuba.algo3.modelo.tablero.ParserTablero;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.terrenos.TerrenosFactory;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class ContenedorTablero extends VBox {

    private Tablero tablero;
    private Observable observable;
    private VistaTablero vistaTablero;
    Stage stage;

    public ContenedorTablero(Stage stage) {
        super();
        // this.tablero = tablero;
        this.stage = stage;
        this.observable = new Observable();

        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(25));

        Canvas canvasTablero = new Canvas(540, 540);
        this.inicializarTablero(canvasTablero);
        this.setMechs();

        this.getChildren().setAll(canvasTablero);
    }

    public void inicializarTablero(Canvas canvasTablero) {
        ParserTablero parserTablero = new ParserTablero(new TerrenosFactory());
        tablero = parserTablero.parsear(List.of(
                "AAAASSSS",
                "AAAASSSS",
                "AASSSÁSS",
                "AASSSSSS",
                "SSESESES",
                "SSSSSSSS",
                "SSESESES",
                "MMMMMMMM"
        ));
        Mech mech1 = new MechDeCombate();
        tablero.colocarPieza(mech1, new Coordenada(3, 4));
        Mech mech2 = new MechDeCanon();
        tablero.colocarPieza(mech2, new Coordenada(5, 4));
        Mech mech3 = new MechDeArtilleria();
        tablero.colocarPieza(mech3, new Coordenada(7, 3));
        vistaTablero = new VistaTablero(tablero, canvasTablero, observable);
        observable.agregarObserver(vistaTablero);
        observable.notificarTodos();
    }

    public void setMechs() {
    }

}