package modelo;

//@author alexs

import vista.Flecha;


public class Arista {

    private Nodo nodoInicio;
    private Nodo nodoFin;
    private final float pesoCamino;
    private Flecha flecha;

    public Arista(float pesoCamino) {
        this.pesoCamino = pesoCamino;
    }

    public void setCoordenadasFlecha(double inicioX, double inicioY, double finX, double finY) {
        flecha = new Flecha();
        flecha.setStartX(inicioX);
        flecha.setStartY(inicioY);
        flecha.setEndX(finX);
        flecha.setEndY(finY);
    }

    public void setNodoInicio (Nodo nodoInicio) {
        this.nodoInicio = nodoInicio;   
    }
    
    public void setNodoFin (Nodo nodoFin){
        this.nodoFin = nodoFin;
    }

    public float getPesoCamino() {
        return pesoCamino;
    }

    public Nodo getNodoInicio() {
        return nodoInicio;
    }

    public Nodo getNodoFin() {
        return nodoFin;
    }
    
    public Flecha getFlecha (){
        return flecha;
    }

}
