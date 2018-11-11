package vista;

//@author alexs
import java.util.ArrayList;
import java.util.stream.Collectors;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import modelo.Arista;
import modelo.Grafo;
import modelo.Nodo;

public class SceneVistaDemo {

//    Scene vistaDemo;
//    Pane paneEscenario;
    
    public static Scene sceneVistaDemo() {
        
        Scene vistaDemo;
        BorderPane menuDemo;
        Pane paneEscenarioSimulacion;
        Grafo grafo;
        
        Rectangle2D screenSize = Screen.getPrimary().getBounds();
        
        grafo = new Grafo();
        
        menuDemo = new BorderPane();
        paneEscenarioSimulacion = new Pane();
        VBox menuOpcionesDemo = new VBox(20);
        HBox menuSolucion = new HBox(100);
        VBox menuBottom = new VBox(20);
        
        paneEscenarioSimulacion.setPrefSize(500, 500);
        
        Label lbTitulo = new Label("Modo Demo");
        Label lbAgregarNodo = new Label("Agregar nodo");
        Label lbAgregarCamino = new Label("Agregar camino");
        Label lbEliminarNodo = new Label("Eliminar nodo");
        Label lbEliminarCamino = new Label ("Eliminar camino");
        Label lbSolucion = new Label("Hallar solución");
        
        lbTitulo.setAlignment(Pos.CENTER);
        
        Button btAgregarNodo = new Button("OK");
        Button btAgregarCamino = new Button("OK");
        Button btEliminarNodo = new Button("Seleccionar");
        Button btEliminarCamino = new Button ("Seleccionar");
        Button btCaminoMinimo = new Button("Automatico");
        Button btCaminoPaso = new Button("Paso por paso");        
        
        TextField textNombreNodo = new TextField();
        TextField textPesoCamino = new TextField();
             
        textNombreNodo.setMaxWidth(200);
        textPesoCamino.setMaxWidth(200);
        
        menuOpcionesDemo.getChildren().addAll(lbAgregarNodo,textNombreNodo,btAgregarNodo,
                lbAgregarCamino,textPesoCamino,btAgregarCamino,lbEliminarNodo,
                btEliminarNodo, lbEliminarCamino, btEliminarCamino);
        menuOpcionesDemo.setBorder(new Border(new BorderStroke(Color.GRAY, 
            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        menuOpcionesDemo.setAlignment(Pos.CENTER);
        menuOpcionesDemo.setPrefSize(300, 300);
        
        menuSolucion.getChildren().addAll(btCaminoMinimo, btCaminoPaso);
        menuSolucion.setAlignment(Pos.CENTER);
        
        menuBottom.getChildren().addAll(lbSolucion, menuSolucion);
        menuBottom.setPrefSize(screenSize.getWidth(), 150);
        menuBottom.setAlignment(Pos.CENTER);
        menuBottom.setBorder(new Border(new BorderStroke(Color.GRAY, 
            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        
        // ACCIÓN BOTÓN AGREGAR NODO
        
        btAgregarNodo.setOnAction(e1 -> {

            String nombreNodo = textNombreNodo.getText();
            
            boolean valor = true;
            
            if ("".equals(nombreNodo)){
                
                CajaDialogo.mostrar("Advertencia", "El campo esta vacío");
                valor = false;
                
            } else {

                for (Nodo nodo : grafo.conjuntoNodos) {
                    valor = !nodo.getNombreNodo().equals(nombreNodo);

                    if (!valor) {
                        CajaDialogo.mostrar("Advertencia", "El nombre '"+ nombreNodo +"' ya ha sido usado");
                        textNombreNodo.setText("");
                        break;
                    }

                }

            }

            if (valor) {

                Nodo nodo = new Nodo(nombreNodo);

                paneEscenarioSimulacion.setOnMouseClicked(e2 -> {
                        
                    nodo.setCoordenadas(e2.getX(), e2.getY());

                    grafo.conjuntoNodos.add(nodo);
                    
                    mostrarEscenario(paneEscenarioSimulacion, grafo);
                    
                    paneEscenarioSimulacion.setOnMouseClicked(e3 -> {
                        
                    });

                });
            }
        });
        
        // ACCIÓN BOTÓN AGREGAR CAMINO
        
        btAgregarCamino.setOnAction(e1 -> {

            int i = 0;
            double[] coords = new double[4];          

            float pesoCamino = 0;

            try {
                pesoCamino = Float.parseFloat(textPesoCamino.getText());
            } catch (NumberFormatException ex) {
                CajaDialogo.mostrar("Advertencia", "Solo ingresar numeros reales");
            }

            Arista caminoIngresar = new Arista(pesoCamino);
            
            paneEscenarioSimulacion.setOnMouseClicked(e2 -> {
                
                boolean [] valores = new boolean[2];
                
                grafo.conjuntoNodos.forEach(t -> {
                    if (t.getCirculo().contains(e2.getX(), e2.getY())) {
                        t.setColor(Color.GREEN);
                        coords[0] = t.getCirculo().getCenterX();
                        coords[1] = t.getCirculo().getCenterY();
                        valores[0] = true;
                        caminoIngresar.setNodoInicio(t);
                    }
                });
                
                if (valores[0]) {
                    
                    paneEscenarioSimulacion.setOnMouseClicked(e3 -> {

                        grafo.conjuntoNodos.forEach(t -> {
                            if (t.getCirculo().contains(e3.getX(), e3.getY())) {
                                t.setColor(Color.GREEN);
                                coords[2] = t.getCirculo().getCenterX();
                                coords[3] = t.getCirculo().getCenterY();
                                valores[1] = true;
                                caminoIngresar.setNodoFin(t);
                            }
                        });
                        
                        if (valores [1]){
                            
                            grafo.conjuntoNodos.stream().forEach(e4 -> {
                                e4.setColor(Color.CORNFLOWERBLUE);
                            });
                            
                            caminoIngresar.setCoordenadasFlecha(coords [0], coords[1], coords [2], coords [3]);
                            grafo.conjuntoCaminos.add(caminoIngresar);
                            
                            mostrarEscenario(paneEscenarioSimulacion, grafo);
                            
                            paneEscenarioSimulacion.setOnMouseClicked(e5 -> {
                                
                            });
                        }
                        
                    });
                }

            });

        });
        
        // ACCIÓN BOTÓN ELIMINAR NODO
        btEliminarNodo.setOnAction(e1 -> {

            paneEscenarioSimulacion.setOnMouseClicked(e2 -> {

                for (Nodo nodo : grafo.conjuntoNodos) {
                    if (nodo.getCirculo().contains(e2.getX(), e2.getY())) {
                        
                        ArrayList<Arista> caminosRetirar = new ArrayList<>();

                        grafo.conjuntoCaminos.stream().
                                filter((camino)
                                    -> (camino.getNodoInicio().equals(nodo) || camino.getNodoFin().equals(nodo))).
                                forEachOrdered((camino) -> {
                                    camino.getFlecha().setColor(Color.RED);
                                    caminosRetirar.add(camino);
                                });
                        
                        nodo.setColor(Color.RED);
                        
                        if (CajaConfirmacion.mostrar("Advertencia",
                                "Esta seguro de eliminar este nodo y por consecuencia sus vertices?")) {
                            
                            grafo.conjuntoCaminos.removeAll(caminosRetirar); 
                            grafo.conjuntoNodos.remove(nodo);

                        } else {

                            nodo.setColor(Color.CORNFLOWERBLUE);

                        }

                        paneEscenarioSimulacion.setOnMouseClicked(e3 -> {

                        });

                        break;
                    }
                }

                mostrarEscenario(paneEscenarioSimulacion, grafo);

            });

        });
        
        // ACCIÓN BOTÓN ELIMINAR CAMINO 
        
        btEliminarCamino.setOnAction(e1 -> {
            
            paneEscenarioSimulacion.setOnMouseClicked(e2 -> {
                
            });
            
        });
        
        menuDemo.setPrefSize(1200, 1200);
        menuDemo.setTop(lbTitulo);
        menuDemo.setRight(menuOpcionesDemo);
        menuDemo.setCenter(paneEscenarioSimulacion);
        menuDemo.setBottom(menuBottom);
        
        vistaDemo = new Scene(menuDemo, screenSize.getWidth(), screenSize.getHeight());

        return vistaDemo;

    }
    
    
    public static double ajustarDescripcion (String texto, double coordX){
        int tamano = texto.length();
        return coordX - 3.5*tamano;
    }
    
    public static void mostrarEscenario(Pane paneEscenario, Grafo grafo) {
        
        paneEscenario.getChildren().clear();
        
        paneEscenario.getChildren().
                addAll(grafo.conjuntoNodos.
                        stream().
                        map(t -> t.getCirculo()).
                        collect(Collectors.toList()));
        
        paneEscenario.getChildren().
                addAll(grafo.conjuntoNodos.
                        stream().
                        map(t -> {
                            double coordX = ajustarDescripcion(t.getNombreNodo(),
                                    t.getCirculo().getCenterX());
                            
                            Text texto = new Text(coordX,
                                    t.getCirculo().getCenterY() - t.RADIO - 5,
                                    t.getNombreNodo());
                            texto.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 15));
                            
                            return texto;
                        }).
                        collect(Collectors.toList()));
        
        paneEscenario.getChildren().
                addAll(grafo.conjuntoCaminos.
                        stream().
                        map (t -> t.getFlecha()).
                        collect(Collectors.toList()));
        
        paneEscenario.getChildren().
                addAll(grafo.conjuntoCaminos.
                        stream().
                        map(t -> {
                            
                            String pesoCamino = String.valueOf(t.getPesoCamino());
                            Double puntoMedioX = (t.getNodoInicio().getCirculo().getCenterX() +
                                    t.getNodoFin().getCirculo().getCenterX())/2;
                            Double puntoMedioY = (t.getNodoInicio().getCirculo().getCenterY()
                                    + t.getNodoFin().getCirculo().getCenterY()) / 2;
                            
                            Text texto = new Text(puntoMedioX, puntoMedioY, pesoCamino);
                            texto.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 15));
                            
                            return texto;
                        }).
                        collect(Collectors.toList()));
    }

    

}

