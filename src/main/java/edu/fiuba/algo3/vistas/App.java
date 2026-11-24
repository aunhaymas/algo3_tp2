package edu.fiuba.algo3.vistas;

import java.util.List;

import edu.fiuba.algo3.SystemInfo;
import edu.fiuba.algo3.modelo.tablero.ParserTablero;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.terrenos.TerrenosFactory;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        // var javaVersion = SystemInfo.javaVersion();
        // var javafxVersion = SystemInfo.javafxVersion();

        ContenedorTablero contenedorTablero = new ContenedorTablero(stage, getTablero());
        var scene = new Scene(contenedorTablero, 640, 640);

        ContenedorBienvenido contenedorBienvenido = new ContenedorBienvenido(stage, scene);
        var bienvenida = new Scene(contenedorBienvenido, 480, 220);

        // var label = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        stage.setScene(bienvenida);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public Tablero getTablero() {
        
        ParserTablero parserTablero = new ParserTablero(new TerrenosFactory());
        Tablero tablero = parserTablero.parsear(List.of(
                "SSSSSSSS",
                "SSSSSSSS",
                "SSSSSSSS",
                "SSSSSSSS",
                "SSSSSSSS",
                "SSSSSSSS",
                "SSSSSSSS",
                "SSSSSSSS"
        ));
        return tablero;
    }

}