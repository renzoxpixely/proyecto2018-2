package vista;

//@author alexs

import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;


public class SceneVistaMochilero {

    public static Scene sceneVistaMochilero() {

        Scene vistaMochilero;
        Pane paneEscenario;

        Rectangle2D screenSize = Screen.getPrimary().getBounds();
        paneEscenario = new Pane();
        BorderPane menuMochilero = new BorderPane();
        vistaMochilero = new Scene(menuMochilero, screenSize.getWidth(), screenSize.getHeight());

        return vistaMochilero;
    }

}
