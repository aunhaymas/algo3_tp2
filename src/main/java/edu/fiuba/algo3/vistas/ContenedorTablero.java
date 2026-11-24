package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.tablero.Tablero;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class ContenedorTablero extends VBox {

    private VistaTablero vistaTablero;
    private Canvas canvasTablero;
    Stage stage;

    public ContenedorTablero(Stage stage, Tablero tablero) {
        super();
        // this.tablero = tablero;
        this.stage = stage;

        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(25));

        this.setTablero(tablero);

        this.getChildren().setAll(canvasTablero);
    }

    public void setTablero(Tablero tablero) {
        canvasTablero = new Canvas(540, 540);
        vistaTablero = new VistaTablero(tablero, canvasTablero);
        vistaTablero.dibujar();
    }

}