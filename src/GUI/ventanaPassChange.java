/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DAO.UsuarioJpaController;
import bibliotecafusm.Usuario;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

/**
 *
 * @author Jhon Alex
 */
public class ventanaPassChange extends javax.swing.JFrame {

    
    private Usuario userlogin;
    private UsuarioJpaController usuariojpa;  
    
    /**
     * Creates new form ventanaPassChange
     */
    public ventanaPassChange() {
        
        
        
        initComponents();
        
        
        
         // instancia objeto que va guardar la info a la base de datos :: usuariojpa
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BibliotecaFUSMPU");
        usuariojpa = new UsuarioJpaController(emf);
        
        this.setTitle("Cambio de contraseña");
        this.setBounds(500, 180, 410, 220);
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        jPasswordField2 = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblfondo = new javax.swing.JLabel();

        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("Contraseña Actual");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(40, 10, 130, 15);

        jTextField1.setEditable(false);
        jTextField1.setText("actual");
        getContentPane().add(jTextField1);
        jTextField1.setBounds(40, 30, 190, 20);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/updatepassicon.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(270, 50, 80, 70);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("Nueva contraseña *");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(40, 60, 140, 15);
        getContentPane().add(jPasswordField1);
        jPasswordField1.setBounds(40, 80, 190, 20);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setText("Confirmación *");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(40, 110, 170, 15);
        getContentPane().add(jPasswordField2);
        jPasswordField2.setBounds(40, 130, 190, 20);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(153, 204, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Cambiar contraseña");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(250, 130, 130, 15);

        jLabel5.setForeground(new java.awt.Color(204, 255, 255));
        jLabel5.setText("La contraseña es sensible a mayúsculas");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(20, 160, 250, 14);

        lblfondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/backgroudpass.jpg"))); // NOI18N
        lblfondo.setText("   ");
        getContentPane().add(lblfondo);
        lblfondo.setBounds(0, 0, 400, 180);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       
        
        
        if(jPasswordField1.getText().toString().length()==0) {
            JOptionPane.showMessageDialog(null,"Debe completar el campo Nueva contraseña");
               
        }else if (jPasswordField2.getText().toString().length()==0) {
     
            JOptionPane.showMessageDialog(null,"Debe completar el campo Confirmar contraseña");
        }else{
        
        
        if(jPasswordField1.getText().toString().contains(" ")){
            JOptionPane.showMessageDialog(null,"La contraseña no debe contener espacios en blanco");
        }else if(!jPasswordField1.getText().toString().equals(jPasswordField2.getText().toString())){
            JOptionPane.showMessageDialog(null,"Las contraseñas No coinciden","Error",JOptionPane.ERROR_MESSAGE);
        }else{
            
            Usuario encontrado = usuariojpa.findUsuario(userlogin.getIdentificacion());
            encontrado.setPassword(jPasswordField2.getText().toString());
            try {
                usuariojpa.edit(encontrado);
                
                this.setVisible(false);
                JOptionPane.showMessageDialog(null,"Su Contraseña ha sido actualizada con Exito");
                
                
            } catch (Exception ex) {
                Logger.getLogger(ventanaPassChange.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    
    
    
    /**
     * @param args the command line arguments
     */

    public void setUserlogin(Usuario userlogin) {
        this.userlogin = userlogin;
        
        jTextField1.setText(this.userlogin.getPassword());
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblfondo;
    // End of variables declaration//GEN-END:variables
}
