/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bibliotecafusm;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Jhon Alex
 */

@Entity
public class VideoBeam implements Serializable  {
    @Id
    private String serialNum;
    
    @Column (length = 80,nullable = false,unique = false)
    private String marca;
    @Column (length = 80,nullable = false,unique = false)
    private String modelo;
    
    @Column (length = 80,nullable = false,unique = true)
    private String inventarioNum;

    @Column (length = 80,nullable = false,unique = false)
    private String disponibilidad;
    
    @Column (length = 350,nullable = true,unique = false)
    private String observaciones;

    @Column (nullable = false,unique = true)
    private int proyectornum;

    public VideoBeam(String serialNum, String marca, String modelo, String inventarioNum, String disponibilidad, String obser,int proyecnum ) {
        this.serialNum = serialNum;
        this.marca = marca;
        this.modelo = modelo;
        this.inventarioNum = inventarioNum;
        this.disponibilidad = disponibilidad;
        this.observaciones = obser;
        this.proyectornum = proyecnum;
    }

    
    public VideoBeam() {
    }
    
    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getInventarioNum() {
        return inventarioNum;
    }

    public void setInventarioNum(String inventarioNum) {
        this.inventarioNum = inventarioNum;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getProyectornum() {
        return proyectornum;
    }

    public void setProyectornum(int proyectornum) {
        this.proyectornum = proyectornum;
    }
    
    
    
}