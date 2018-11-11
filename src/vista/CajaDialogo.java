package vista;

//@author alexs
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CajaDialogo {

    public static void mostrar(String titulo, String mensaje) {

        Stage ventana = new Stage();

        ventana.initModality(Modality.APPLICATION_MODAL);
        ventana.setTitle(titulo);
        ventana.setMinWidth(400);
        ventana.setMinWidth(400);

        Label label = new Label();
        label.setText(mensaje);

        Button btOK = new Button("OK");

        btOK.setOnAction(e -> {
            ventana.close();
        });


        HBox layoutOpciones = new HBox();
        VBox layout = new VBox();

        layoutOpciones.getChildren().add(btOK);
        layoutOpciones.setSpacing(100);
        layoutOpciones.setAlignment(Pos.CENTER);

        layout.getChildren().addAll(label, layoutOpciones);
        layout.setAlignment(Pos.CENTER);
        layout.setPrefSize(200, 200);
        layout.setSpacing(50);

        ventana.setScene(new Scene(layout));
        ventana.maxHeightProperty();
        ventana.maxWidthProperty();
        ventana.setResizable(false);
        ventana.showAndWait();


    }
}
