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
public class PrestamoLibro implements Serializable  {
    
    @Id
    private String codigoLibro;
    @Column (length = 80,nullable = false,unique = false)
    private String nombreLibro;
    
    @Column (length = 80,nullable = false,unique = false)
    private String nombreUsuario;
    @Column (length = 80,nullable = false,unique = false)
    private String idUsuario;
    @Column (length = 80,nullable = false,unique = false)
    private String tipoUsuario;
    
    @Column (length = 20,nullable = false,unique = false)
    private String fechaPrestamo;
    @Column (length = 20,nullable = false,unique = false)
    private String entregaPrestamo;
    @Column (length = 20,nullable = false,unique = false)
    private String estadoPrestamo;

    public PrestamoLibro() {
    }


    public PrestamoLibro(String codigoLibro, String nombreLibro, String nombreUsuario, String idUsuario, String tipoUsuario) {
        this.codigoLibro = codigoLibro;
        this.nombreLibro = nombreLibro;
        this.nombreUsuario = nombreUsuario;
        this.idUsuario = idUsuario;
        this.tipoUsuario = tipoUsuario;
        
        this.fechaPrestamo = getFechaActual ();
        this.entregaPrestamo = getcalcFechaDevolucion ();
        this.estadoPrestamo = "Solicitud";
    }
    
    
    public void renovarPrestamoLibro(){
        this.fechaPrestamo = getFechaActual ();
        this.entregaPrestamo = getcalcFechaDevolucion ();
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
    
    
    public String getcalcFechaDevolucion (){
        
       Calendar cal = new GregorianCalendar(); 

        //--
        int annio = Integer.parseInt(""+cal.get(Calendar.YEAR)); 
        int mes = cal.get(Calendar.MONTH);
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        mes++;
        //-- 
        
        int diasdelmes = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        boolean avanzomes = false;
        
        for (int i = 0; i < 7; i++) {
            if(dia==diasdelmes){
                dia =0;
                avanzomes = true;
            }
                dia++;      
        }
        
        if(avanzomes){
            if(mes==11){
                mes=0;
                annio++; 
            }else{
                mes++;
            }
        }
        
        return  dia+"/"+mes+"/"+annio;
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

    public String getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(String fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public String getEntregaPrestamo() {
        return entregaPrestamo;
    }

    public void setEntregaPrestamo(String entregaPrestamo) {
        this.entregaPrestamo = entregaPrestamo;
    }

    public String getEstadoPrestamo() {
        return estadoPrestamo;
    }

    public void setEstadoPrestamo(String estadoPrestamo) {
        this.estadoPrestamo = estadoPrestamo;
    }
    
    
    
    
    
}

