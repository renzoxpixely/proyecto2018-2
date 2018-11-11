package vista;

//@author alexs

import javafx.beans.InvalidationListener;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Flecha extends Group {

    private final Line line;

    public Flecha() {
        this(new Line(), new Line(), new Line());
    }

    private static final double LONGITUD_FLECHA = 20;
    private static final double ANCHO_FLECHA = 7;

    private Flecha(Line linea, Line flechaParte1, Line flechaParte2) {
        
        super(linea, flechaParte1, flechaParte2);
        this.line = linea;
        
        InvalidationListener updater = o -> {
            double finX = getEndX();
            double finY = getEndY();
            double inicioX = getStartX();
            double inicioY = getStartY();

            flechaParte1.setEndX(finX);
            flechaParte1.setEndY(finY);
            flechaParte2.setEndX(finX);
            flechaParte2.setEndY(finY);

            if (finX == inicioX && finY == inicioY) {
                
                flechaParte1.setStartX(finX);
                flechaParte1.setStartY(finY);
                flechaParte2.setStartX(finX);
                flechaParte2.setStartY(finY);
            } else {
                double factor = LONGITUD_FLECHA / Math.hypot(inicioX-finX, inicioY-finY);
                double factorO = ANCHO_FLECHA / Math.hypot(inicioX-finX, inicioY-finY);

                
                double dx = (inicioX - finX) * factor;
                double dy = (inicioY - finY) * factor;

                
                double ox = (inicioX - finX) * factorO;
                double oy = (inicioY - finY) * factorO;

                flechaParte1.setStartX(finX + dx - oy);
                flechaParte1.setStartY(finY + dy + ox);
                flechaParte2.setStartX(finX + dx + oy);
                flechaParte2.setStartY(finY + dy - ox);
            }
        };

        propiedadInicioX().addListener(updater);
        propiedadInicioY().addListener(updater);
        propiedadFinX().addListener(updater);
        propiedadFinY().addListener(updater);
        
        updater.invalidated(null);
    }

    public final void setStartX(double value) {
        line.setStartX(value);
    }

    public final double getStartX() {
        return line.getStartX();
    }

    public final DoubleProperty propiedadInicioX() {
        return line.startXProperty();
    }

    public final void setStartY(double value) {
        line.setStartY(value);
    }

    public final double getStartY() {
        return line.getStartY();
    }

    public final DoubleProperty propiedadInicioY() {
        return line.startYProperty();
    }

    public final void setEndX(double value) {
        line.setEndX(value);
    }

    public final double getEndX() {
        return line.getEndX();
    }

    public final DoubleProperty propiedadFinX() {
        return line.endXProperty();
    }

    public final void setEndY(double value) {
        line.setEndY(value);
    }

    public final double getEndY() {
        return line.getEndY();
    }

    public final DoubleProperty propiedadFinY() {
        return line.endYProperty();
    }
    
    public void setColor (Color color){
        line.setStroke(color);
    }

}