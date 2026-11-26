package edu.fiuba.algo3.vistas;

import java.util.List;

import edu.fiuba.algo3.SystemInfo;
import edu.fiuba.algo3.modelo.Coordenada;
import edu.fiuba.algo3.modelo.mechs.Mech;
import edu.fiuba.algo3.modelo.mechs.MechDeArtilleria;
import edu.fiuba.algo3.modelo.mechs.MechDeCanon;
import edu.fiuba.algo3.modelo.mechs.MechDeCombate;
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

        ContenedorTablero contenedorTablero = new ContenedorTablero(stage, inicializarTablero());
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

    public Tablero inicializarTablero() {
        
        ParserTablero parserTablero = new ParserTablero(new TerrenosFactory());
        Tablero tablero = parserTablero.parsear(List.of(
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
        return tablero;
    }

}