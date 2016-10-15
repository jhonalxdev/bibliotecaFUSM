/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bibliotecafusm;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 *
 * @author Jhon Alex
 */
@Entity
public class PrestamoLibro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pk;

    @Column(length = 80, nullable = false, unique = false)
    private String codigoLibro;

    @Column(length = 80, nullable = false, unique = false)
    private String nombreLibro;

    @Column(length = 80, nullable = false, unique = false)
    private String nombreUsuario;

    @Column(length = 80, nullable = false, unique = false)
    private String idUsuario;

    @Column(length = 80, nullable = false, unique = false)
    private String tipoUsuario;
    @Column(length = 20, nullable = true, unique = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaSolicitud; //fecha en que se solicita el prestamo
    @Column(length = 20, nullable = true, unique = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaActivacionPrestamo; //fecha de cuando se le entrega el libro al usuario
    @Column(length = 20, nullable = true, unique = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaMaxDevolucion; //fecha maxima hasta la devolucion
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(length = 20, nullable = true, unique = false)
    private Date fechaDevolucion;
    @Column(length = 20, nullable = false, unique = false)
    private String estadoPrestamo;

    public PrestamoLibro() {
    }

    public PrestamoLibro(String codigoLibro, String nombreLibro, String nombreUsuario, String idUsuario, String tipoUsuario) {
        this.codigoLibro = codigoLibro;
        this.nombreLibro = nombreLibro;
        this.nombreUsuario = nombreUsuario;
        this.idUsuario = idUsuario;
        this.tipoUsuario = tipoUsuario;

        this.fechaActivacionPrestamo = null;
        this.fechaDevolucion = null;
        this.fechaSolicitud = new Date();
        this.estadoPrestamo = EstadoPrestamo.SOLICITUD.toString();
        
    }

    public void renovarPrestamoLibro() {
        activarPrestamoLibro();
        
    }

    public void activarPrestamoLibro() {
        this.fechaActivacionPrestamo = new Date();
        this.fechaMaxDevolucion = Calendario.getcalcFechaDevolucion(fechaActivacionPrestamo, 5);
    }

//     
 
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

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public Date getFechaActivacionPrestamo() {
        return fechaActivacionPrestamo;
    }

    public void setFechaActivacionPrestamo(Date fechaActivacionPrestamo) {
        this.fechaActivacionPrestamo = fechaActivacionPrestamo;
    }

    public Date getFechaMaxDevolucion() {
        return fechaMaxDevolucion;
    }

    public void setFechaMaxDevolucion(Date fechaMaxDevolucion) {
        this.fechaMaxDevolucion = fechaMaxDevolucion;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public EstadoPrestamo getEstadoPrestamo() {
        return EstadoPrestamo.valueOf(estadoPrestamo);
    }

    public void setEstadoPrestamo(EstadoPrestamo estadoPrestamo) {
        this.estadoPrestamo = estadoPrestamo.toString();
    }

   

    public Long getPk() {
        return pk;
    }

    public void setPk(Long pk) {
        this.pk = pk;
    }

}
