/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bibliotecafusm;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Jhon Alex
 */

@Entity
public class PrestamoBeam implements Serializable {
    
    @Id
    private String serialBeam;
    @Column (length = 80,nullable = false,unique = false)
    private String marca;
    @Column (length = 80,nullable = false,unique = false)
    private String modelo;
    
    @Column (length = 80,nullable = false,unique = false)
    private String nombreUsuario;
    @Column (length = 80,nullable = false,unique = false)
    private String idUsuario;
    @Column (length = 80,nullable = false,unique = false)
    private String tipoUsuario;
    
    
    @Column (length = 20,nullable = false,unique = false)
    private String salonPrestamo;
        
        
    @Column (length = 20,nullable = false,unique = false)
    private String fechaPrestamo;

    @Column (length = 20,nullable = false,unique = false)
    private String estadoPrestamo;

    
    
    public PrestamoBeam() {
    }

    public PrestamoBeam(String serialBeam, String marca, String modelo, String nombreUsuario, String idUsuario, String tipoUsuario, String salonPrestamo) {
        this.serialBeam = serialBeam;
        this.marca = marca;
        this.modelo = modelo;
        this.nombreUsuario = nombreUsuario;
        this.idUsuario = idUsuario;
        this.tipoUsuario = tipoUsuario;
        this.salonPrestamo = salonPrestamo;
        
        this.fechaPrestamo = getFechaActual (); 
        this.estadoPrestamo = "Activo";
    }


    
    //  FORMATO FECHAS 13/07/2016    dia/mes/annio
    
    
    public String getFechaActual (){
        Calendar cal = new GregorianCalendar(); 
        //--
        int annio = Integer.parseInt(""+cal.get(Calendar.YEAR)); 
        int mes = cal.get(Calendar.MONTH);
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        //--
        mes++;
        return  dia+"/"+mes+"/"+annio;
    }

    public String getSerialBeam() {
        return serialBeam;
    }

    public void setSerialBeam(String serialBeam) {
        this.serialBeam = serialBeam;
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

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getSalonPrestamo() {
        return salonPrestamo;
    }

    public void setSalonPrestamo(String salonPrestamo) {
        this.salonPrestamo = salonPrestamo;
    }

    public String getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(String fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public String getEstadoPrestamo() {
        return estadoPrestamo;
    }

    public void setEstadoPrestamo(String estadoPrestamo) {
        this.estadoPrestamo = estadoPrestamo;
    }

    
    
    
    
}
