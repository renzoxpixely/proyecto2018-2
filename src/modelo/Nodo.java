package modelo;

//@author alexs

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;


public class Nodo {
    
    public final double RADIO = 15;
    private Circle circulo;
    private final String nombreNodo;
    
    public Nodo (String nombreNodo){
        this.nombreNodo = nombreNodo;
    }
    
    public void setCoordenadas (double x, double y){        
        circulo = new Circle (x, y, RADIO, Color.CORNFLOWERBLUE);
    }
    
    public String getNombreNodo (){
        return nombreNodo;
    }
    
    public Circle getCirculo (){
        return circulo;
    } 
    
    public void setColor (Paint color){
        circulo.setFill(color);
    }
}
