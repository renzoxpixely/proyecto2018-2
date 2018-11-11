package modelo;

//@author alexs

import java.util.ArrayList;


public class Grafo {
    
    public ArrayList <Arista> conjuntoCaminos;
    public ArrayList <Nodo> conjuntoNodos;
    
    public Grafo (){
        conjuntoCaminos = new ArrayList<>();
        conjuntoNodos = new ArrayList<>();
    }
    
}
