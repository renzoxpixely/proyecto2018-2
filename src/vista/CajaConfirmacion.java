package vista;

//@author alexs

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.*;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CajaConfirmacion {
    
    static boolean respuesta;
    
    public static boolean mostrar (String titulo , String mensaje){
        
        Stage ventana = new Stage ();
        
        ventana.initModality(Modality.APPLICATION_MODAL);
        ventana.setTitle(titulo);
        ventana.setMinWidth(400);
        ventana.setMinWidth(400);
        
        Label label = new Label();
        label.setText(mensaje);
        
        Button btContinuar = new Button("Si");
        Button btSalir = new Button ("No");
        
        btContinuar.setOnAction(e -> {
            respuesta = true;
            ventana.close();
        });
        btSalir.setOnAction(e -> {
            respuesta = false;
            ventana.close ();
        });
        
        HBox layoutOpciones = new HBox();
        VBox layout = new VBox();
        
        layoutOpciones.getChildren().addAll(btContinuar, btSalir);
        layoutOpciones.setSpacing(100);
        layoutOpciones.setAlignment(Pos.CENTER);
        
        layout.getChildren().addAll(label, layoutOpciones);
        layout.setAlignment(Pos.CENTER);
        layout.setPrefSize(200, 200);  
        layout.setSpacing(50);
        
        ventana.setScene(new Scene (layout));
        ventana.maxHeightProperty();
        ventana.maxWidthProperty();
        ventana.setResizable(false);
        ventana.showAndWait();
        
        return respuesta;
    }
}
