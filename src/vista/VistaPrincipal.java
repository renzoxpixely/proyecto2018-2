package vista;

//@author alexs
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.stage.Screen;

public class VistaPrincipal extends Application {

    Stage ventanaPrincipal;
    
    Scene vistaPrincipal;
    Scene vistaDemo;
    Scene vistaJuego;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        // SCENE VISTA PRINCIPAL
        
        Label nombrePrograma;
        Button btIniciarDemo, btIniciarJuego, btSalirApp;
        VBox paneMenuPrincipal;
        
        // IMPLEMENTACION
        
        Rectangle2D screenSize = Screen.getPrimary().getBounds();
        
        ventanaPrincipal = primaryStage;
        
        nombrePrograma = new Label("Caminos Minimos");

        btIniciarDemo = new Button("Iniciar Demo");
        btIniciarJuego = new Button("Iniciar Juego");
        btSalirApp = new Button("Salir Aplicación");

        paneMenuPrincipal = new VBox();

        paneMenuPrincipal.getChildren().addAll(nombrePrograma, btIniciarDemo, btIniciarJuego, btSalirApp);
        paneMenuPrincipal.setAlignment(Pos.CENTER);
        paneMenuPrincipal.setSpacing(100);
        paneMenuPrincipal.maxHeightProperty();
        paneMenuPrincipal.maxWidthProperty();
        
        vistaPrincipal = new Scene(paneMenuPrincipal, screenSize.getWidth(), screenSize.getHeight());
        
        ventanaPrincipal.setTitle("Proyecto caminos minimos");
        ventanaPrincipal.setScene(vistaPrincipal);
        ventanaPrincipal.setMaximized(true);
        ventanaPrincipal.setResizable(false);
        ventanaPrincipal.setOnCloseRequest(e ->{
            e.consume();
            cerrarPrograma();
        }); 
        ventanaPrincipal.show();
        
        // LÓGICA GUI
        
        btSalirApp.setOnAction(e -> cerrarPrograma());
        
        btIniciarDemo.setOnAction(e -> {  
            
            ventanaPrincipal.setScene(SceneVistaDemo.sceneVistaDemo());
            ventanaPrincipal.setMaximized(true);
            ventanaPrincipal.setResizable(false);
            
        });
        
        btIniciarJuego.setOnAction(e -> {
            
            ventanaPrincipal.setScene(SceneVistaMochilero.sceneVistaMochilero());
            ventanaPrincipal.setMaximized(true);
            ventanaPrincipal.setResizable(false);
            
        });
    }
 
    private void cerrarPrograma() {
        boolean rpta = CajaConfirmacion.mostrar("Caminos Minimos", "¿Está seguro de salir?");
        if (rpta) {
            ventanaPrincipal.close();
        }
    }


}
