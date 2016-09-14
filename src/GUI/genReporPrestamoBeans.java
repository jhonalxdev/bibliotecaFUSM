/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import java.util.Calendar;

import javax.swing.DefaultComboBoxModel;


/**
 *
 * @author Jhon Alex
 */
public class genReporPrestamoBeans extends javax.swing.JFrame {


    /**
     * Creates new form ventanaPassChange
     */
    public genReporPrestamoBeans() {
        
        
        
        initComponents();
        
        
        this.setTitle("Generador Reportes ");
        this.setBounds(500, 180, 400, 120);
        
        
        cargarComboMeses();
        cargarComboAnos(); 
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
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        combomeses = new javax.swing.JComboBox();
        comboannios = new javax.swing.JComboBox();
        jButton2 = new javax.swing.JButton();
        lblfondo = new javax.swing.JLabel();

        setResizable(false);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("Año");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(140, 40, 40, 15);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("Mes");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(40, 40, 70, 15);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(153, 204, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Generador Reporte Prestamos Video Beams");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(40, 10, 290, 15);

        combomeses.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(combomeses);
        combomeses.setBounds(40, 60, 80, 20);

        comboannios.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(comboannios);
        comboannios.setBounds(136, 60, 60, 20);

        jButton2.setText("GENERAR REPORTE");
        getContentPane().add(jButton2);
        jButton2.setBounds(223, 53, 150, 30);

        lblfondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/backgroudpass.jpg"))); // NOI18N
        lblfondo.setText("   ");
        getContentPane().add(lblfondo);
        lblfondo.setBounds(0, 0, 400, 110);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    /**
     * @param args the command line arguments
     */

 


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox comboannios;
    private javax.swing.JComboBox combomeses;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lblfondo;
    // End of variables declaration//GEN-END:variables


    public void cargarComboAnos() {
        DefaultComboBoxModel mdlCombo = new DefaultComboBoxModel();
        comboannios.setModel(mdlCombo);

        Calendar c = Calendar.getInstance();

        mdlCombo.addElement("-");

        for (int i = 2015; i <= c.get(Calendar.YEAR); i++) {

            mdlCombo.addElement(i);
        }
    }

    
    public void cargarComboMeses() {
        DefaultComboBoxModel mdlCombo = new DefaultComboBoxModel();
        combomeses.setModel(mdlCombo);

        Calendar c = Calendar.getInstance();

        mdlCombo.addElement("-");

        for (int i = 1; i <= 12; i++) {

            mdlCombo.addElement(i);
        }
    }

}