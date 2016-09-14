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
public class Usuario implements Serializable{
    
    @Id
    private String identificacion;
    
    @Column (length = 30,nullable = false,unique = false)
    private String nombres;
    
    @Column (length = 30,nullable = false,unique = false)
    private String apellidos;
    
    @Column (length = 30,nullable = true,unique = false)
    private String carrera;
    
    @Column (length = 30,nullable = false,unique = false)
    private String tipoUsuario;
    
    @Column (length = 10, nullable = true)
    private String numtelefono;
    
    @Column (length = 60,nullable = false,unique = true)
    private String email;
    
    @Column (length = 20,nullable = false,unique = false)
    private String password;


    
    public Usuario() {
    }


    public Usuario(String identificacion, String nombres, String apellidos, String carrera, String tpuser, String numtelefono, String email, String password) {
        this.identificacion = identificacion;
        this.nombres = nombres;
        this.apellidos = apellidos;
        
        
        this.carrera = carrera;
        
        this.tipoUsuario = tpuser;
        this.numtelefono = numtelefono;
        this.email = email;
        this.password = password;
        
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getNumtelefono() {
        return numtelefono;
    }

    public void setNumtelefono(String numtelefono) {
        this.numtelefono = numtelefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
   
    
    
    
}
