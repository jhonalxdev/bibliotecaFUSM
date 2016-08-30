/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bibliotecafusm;



import GUI.Principal;
import java.awt.BorderLayout;
import static java.awt.Frame.MAXIMIZED_BOTH;
import javax.persistence.PersistenceException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


/**
 *
 * @author Jhon Alex
 */
public class Main {


    /** Apliacion de escritorio Java embebida java DB (derby)
     *
 * 1. crear base datos
 * pestaña services / Databases  / JavaBD   ---> click derecho crear data base
 * 
 * clic derecho sobre la base de datos creada - conectar
 *   ejemplo EstacionBD  nombre Alex pass 2711
 * 
 * 2. crear unidad persistente
 * archivo nuevo / persistence unit / conexion con base de datos creada
 * 
*/
    
    /**
 * /** ENTIDADES
 * 
 * a. Definir como entidades nuestros atributos de clase
 *   @Id
 *   @GeneratedValue(strategy= GenerationType.IDENTITY)
 * // valor autogenerado
 * 
 * b. Relaciones entre clases cardinalidad
 *   @OneToOne --> caso que sea uno a uno
 *   
 * c. add clases a nuestra unidad persistente
 * 
 * abrimos la clase persistence.xml // add class // seleccionamos y agregamos nuestras clases
 * 
 * 
 * 3. Crear clase manejadora de operaciones de la base de datos DAO: Data Acces Object
 * - creamos folder --> click derecho proyecto //new // java Package // asignamos nombre:: DAO
 * - archivo nuevo / JPA controller Entity class / seleccionar clases con relaciones
 * 
 * 
 * 4. Agrega libreria JavaDB driver
 * Librerias // click derecho Add Library // JavaDB driver
 * 
 * 5. Opcional // agregar libreria para hacer pruebas JUnit - para probar en este caso si la base de datos funciona
 * para no utilizar el main creamos otra clase
 * archivo nuevo / Test for existing class / se selecciona la clase
 * 
 * 6. Crear nuevo paquete con una clase conexion 
 * contendra un metodo getconexion con el cual se realizara la conexion a la base de datos
 *
 * 7. Mmodificar la ruta de la base de datos en la unidad persistente
 *       <property name="javax.persistence.jdbc.url" value="jdbc:derby://localhost:1527/basededatos"/>-->
 *      <property name="javax.persistence.jdbc.url" value="jdbc:derby:.\\basededatos"/>
 
 **/
    
    
    /** INFO DATABASE
     * bibliotecafusmbd
     * user: alex
     * pass: 2711
     */
    
    public static void main(String[] args) {
        
        
        
        try {
        

        Principal ventana = new Principal();
	ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLayout(new BorderLayout());
        ventana.setSize(1340,690);

        ventana.setLocationRelativeTo(null);
       
        ventana.setExtendedState(MAXIMIZED_BOTH);

        
        
       // oculta principal hasta logeo true
        ventana.setVisible(true);
        
 

            
        } catch (PersistenceException pex) {
            // notificacion de error en caso que la conexion con la bd no se realice
            JOptionPane.showMessageDialog(null,"Sin conexion con la base de datos");
            
            pex.printStackTrace();
            System.exit(0);
        }


    }
    
}
