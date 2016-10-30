/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bibliotecafusm;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

/**
 *
 * @author Paulker
 */
public class ControladorConsulta {

    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    public static final String PROP_LIBROS_BD = "Libros Actulizados de la BD";
    private List<Libro> librosBD;
    private boolean termino;

    public ControladorConsulta() {
        this.termino = false;
    }

    public List<Libro> getLibrosBD() {
        return librosBD;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.propertyChangeSupport.removePropertyChangeListener(listener);
    }

    public void setLibrosBD(List<Libro> libros) {
        if (!(libros == null || libros.isEmpty())) {
            List<Libro> anterior = this.librosBD;
            this.librosBD = libros;
            propertyChangeSupport.firePropertyChange(PROP_LIBROS_BD, anterior, this.librosBD);
            System.out.println("=========================Actulizacion de libros");
            System.out.println("Tamaño : "+ this.librosBD.size());
        }

    }

}
