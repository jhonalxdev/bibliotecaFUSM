/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bibliotecafusm;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.swing.ImageIcon;

/**
 *
 * @author Jhon Alex
 */

@Entity
public class Libro implements Serializable {
    
    
    @Id
    private String codigoLibro;
    
    @Column (length = 80,nullable = false,unique = false)
    private String nombreLibro;
    
    @Column (length = 80,nullable = false,unique = false)
    private String autor_es;
    
    @Column (length = 30,nullable = true,unique = false)
    private String editorial;
    
    @Column (length = 30,nullable = false,unique = false)
    private String procedencia;
     @Column (length = 30,nullable = false,unique = false)
    private String carrera;
    
    @Column (length = 30,nullable = false,unique = false) 
    private String tomo;
    
    @Column (length = 5,nullable = false,unique = false) 
    private String annioPub;
    
    @Column (length = 15,nullable = false,unique = false) 
    private String fechaIngreso;
    
    @Column (nullable = false,unique = false) 
    private ImageIcon imagen;
    
    @Column (length = 15,nullable = false,unique = false) 
    private String estado; // Disponible / No Disponible

    
    
    public Libro() {
    }

    
    public Libro(String codigoLibro, String nombreLibro, String autor_es, String procedencia, String carrera, ImageIcon img,String aniopub,String editorial, String tomo,String fechaIngreso) {
        this.codigoLibro = codigoLibro;
        this.nombreLibro = nombreLibro;
        this.autor_es = autor_es;
        this.procedencia = procedencia;
        this.carrera = carrera;
        this.fechaIngreso = fechaIngreso;
        this.annioPub = aniopub;
        this.editorial= editorial;
        this.imagen = img;
        this.tomo = tomo;
                
        
        this.estado = "Disponible";
    }

    public String getCodigoLibro() {
        return codigoLibro;
    }

    public void setCodigoLibro(String codigoLibro) {
        this.codigoLibro = codigoLibro;
    }

    public String getNombreLibro() {
        return nombreLibro;
    }

    public void setNombreLibro(String nombreLibro) {
        this.nombreLibro = nombreLibro;
    }

    public String getAutor_es() {
        return autor_es;
    }

    public void setAutor_es(String autor_es) {
        this.autor_es = autor_es;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getProcedencia() {
        return procedencia;
    }

    public void setProcedencia(String procedencia) {
        this.procedencia = procedencia;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getTomo() {
        return tomo;
    }

    public void setTomo(String tomo) {
        this.tomo = tomo;
    }

    public String getAnnioPub() {
        return annioPub;
    }

    public void setAnnioPub(String annioPub) {
        this.annioPub = annioPub;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public ImageIcon getImagen() {
        return imagen;
    }

    public void setImagen(ImageIcon imagen) {
        this.imagen = imagen;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Libro other = (Libro) obj;
        if (!Objects.equals(this.codigoLibro, other.codigoLibro)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        
        return "BIBLIOTECA FUSM PALMIRA \n"+
               "Nombre libro: "+  nombreLibro + "\n"+
               "Autor(es): "+  autor_es + "\n"+
               "Codigo libro: "+  codigoLibro + "\n"+
               "Editorial: "+  editorial + "\n"+
               "Tomo: "+  tomo + "  Año publicacion: "+annioPub ;

    }
    
    
    
    
    
}
