/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bibliotecafusm;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Jhon Alex
 */

@Entity
public class SalondeClases implements Serializable{
    
   @Id 
   private  String  numeroSalon;

    public SalondeClases() {
    }

    public SalondeClases(String numeroSalon) {
        this.numeroSalon = numeroSalon;
    }

    
    
    
    public String getNumeroSalon() {
        return numeroSalon;
    }

    public void setNumeroSalon(String numeroSalon) {
        this.numeroSalon = numeroSalon;
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
        final SalondeClases other = (SalondeClases) obj;
        if (!Objects.equals(this.numeroSalon, other.numeroSalon)) {
            return false;
        }
        return true;
    }
    

    
    
    
    
}


