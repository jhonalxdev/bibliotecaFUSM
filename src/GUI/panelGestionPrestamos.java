/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DAO.LibroJpaController;
import DAO.PrestamoBeamJpaController;
import DAO.PrestamoLibroJpaController;
import DAO.SalondeClasesJpaController; 
import DAO.VideoBeamJpaController;

import DAO.exceptions.NonexistentEntityException;
import bibliotecafusm.Libro;
import bibliotecafusm.PrestamoBeam;
import bibliotecafusm.PrestamoLibro;
import bibliotecafusm.SalondeClases;
import bibliotecafusm.VideoBeam;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.DefaultComboBoxModel;

import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import static javax.swing.SwingConstants.CENTER;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import javax.swing.table.TableColumnModel;




/**
 *
 * @author Jhon Alex
 */
public class panelGestionPrestamos extends javax.swing.JPanel {

    
    //----------------------------------------
    private LibroJpaController librosjpa;
    //----------------------------------------
    //----------------------------------------
    private VideoBeamJpaController proyectoresjpa;
    //----------------------------------------
    
    private PrestamoLibroJpaController prestamolibrosjpa;
    private List<PrestamoLibro> listaPrestamoLibrosdelaBD;
    
    //-------------------------------------------
    private  PrestamoBeamJpaController prestamoBeamsjpa;
    private List<PrestamoBeam> listaPrestamoBeamsdelaBD;
    
    
    private SalondeClasesJpaController salonesjpa;
    private List<SalondeClases> listaSalonesdelaBD;
    
    /**
     * Creates new form panel1
     */
    public panelGestionPrestamos() {
       

        
        initComponents();
         

        // instancia objeto que va guardar la info en la base de datos 
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BibliotecaFUSMPU");
        prestamolibrosjpa = new PrestamoLibroJpaController(emf);
        librosjpa = new LibroJpaController(emf);
        
        //---
        prestamoBeamsjpa = new PrestamoBeamJpaController(emf);
        proyectoresjpa = new VideoBeamJpaController (emf);
        
        
        listaPrestamoLibrosdelaBD = prestamolibrosjpa.findPrestamoLibroEntities();
        listaPrestamoBeamsdelaBD = prestamoBeamsjpa.findPrestamoBeamEntities();


        //----
        salonesjpa = new SalondeClasesJpaController (emf);
        listaSalonesdelaBD = salonesjpa.findSalondeClasesEntities();

        // asignacion de modelo a tabla libros
        miTablaPrestamolibros.setModel(new ModeloTablaLibros());

        // Define la selecion de solo una celda
        miTablaPrestamolibros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        
        // asignacion de modelo a tabla beams
        miTablaPrestamosBeams.setModel(new ModeloTablaBeams());
        
        // Define la selecion de solo una celda
        miTablaPrestamosBeams.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      
        
     
        // manejador buqueda enter
        jTextField1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                jButton5ActionPerformed(ae);

            }
        });



        
        organizatamseccionestablas();
        
        updateTableLibros();
        
        cargarCombosalones();
  
    }

    public void updateListaPrestamoLibrosBD(){
        listaPrestamoLibrosdelaBD = prestamolibrosjpa.findPrestamoLibroEntities();
    }
    
    public void updateListaPrestamoBeamsBD(){
        listaPrestamoBeamsdelaBD = prestamoBeamsjpa.findPrestamoBeamEntities();
    }
    
    
    public void cargarCombosalones() {
        DefaultComboBoxModel mdlCombo = new DefaultComboBoxModel();
        combosalones.setModel(mdlCombo);
        
        mdlCombo.removeAllElements();
        
        mdlCombo.addElement("----------");
        
        if(!listaSalonesdelaBD.isEmpty()){
            
            listaSalonesdelaBD = salonesjpa.findSalondeClasesEntities();
            
        for (int i = 0; i < listaSalonesdelaBD.size(); i++) {
            SalondeClases s = listaSalonesdelaBD.get(i);
            mdlCombo.addElement(""+s.getNumeroSalon());
        }
        }
    }

    
    
    public void organizatamseccionestablas() {
        TableColumnModel cModel = miTablaPrestamolibros.getColumnModel();
        cModel.getColumn(0).setPreferredWidth(80);
        cModel.getColumn(1).setPreferredWidth(100);
        cModel.getColumn(2).setPreferredWidth(220);
        cModel.getColumn(3).setPreferredWidth(100);
        cModel.getColumn(4).setPreferredWidth(220);
        cModel.getColumn(5).setPreferredWidth(90);
        cModel.getColumn(6).setPreferredWidth(90);
        cModel.getColumn(7).setPreferredWidth(90);
        
        
        TableColumnModel cModel1 =  miTablaPrestamosBeams.getColumnModel();
        cModel1.getColumn(0).setPreferredWidth(80);
        cModel1.getColumn(1).setPreferredWidth(100);
        cModel1.getColumn(2).setPreferredWidth(220);
        cModel1.getColumn(3).setPreferredWidth(80);
        cModel1.getColumn(4).setPreferredWidth(100);
        cModel1.getColumn(5).setPreferredWidth(100);
        cModel1.getColumn(6).setPreferredWidth(80);
        cModel1.getColumn(7).setPreferredWidth(140);
        cModel1.getColumn(8).setPreferredWidth(90);
        
        
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(CENTER);
        miTablaPrestamosBeams.getColumnModel().getColumn(3).setCellRenderer(tcr);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        miTablaPrestamolibros = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        miTablaPrestamosBeams = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        combosalones = new javax.swing.JComboBox<>();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setOpaque(false);
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/projector.png"))); // NOI18N
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, -10, 80, 90));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/books-icon.png"))); // NOI18N
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 90, 100));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "                 Gestion Prestamos Libros ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), java.awt.Color.blue)); // NOI18N
        jPanel2.setLayout(null);

        jLabel15.setText("Buscar por:");
        jPanel2.add(jLabel15);
        jLabel15.setBounds(100, 20, 80, 14);
        jPanel2.add(jTextField1);
        jTextField1.setBounds(230, 40, 220, 20);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/icono_buscar1.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton5);
        jButton5.setBounds(460, 20, 48, 50);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Codigo libro", "Titulo libro", "ID usuario", "Nombre usuario" }));
        jPanel2.add(jComboBox2);
        jComboBox2.setBounds(100, 40, 130, 20);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/active_book.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1);
        jButton1.setBounds(730, 10, 110, 50);

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Activar Prestamo");
        jPanel2.add(jLabel12);
        jLabel12.setBounds(730, 60, 110, 14);

        miTablaPrestamolibros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        miTablaPrestamolibros.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane2.setViewportView(miTablaPrestamolibros);

        jPanel2.add(jScrollPane2);
        jScrollPane2.setBounds(10, 80, 1000, 160);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/book-cancel.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2);
        jButton2.setBounds(600, 10, 100, 50);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/give_book.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3);
        jButton3.setBounds(870, 10, 100, 50);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Confirmar Devolucion");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(860, 60, 130, 14);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Cancelar Prestamo");
        jPanel2.add(jLabel5);
        jLabel5.setBounds(590, 60, 120, 14);

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 1020, 250));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "                 Gestion Prestamos VideoBeams", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), java.awt.Color.blue)); // NOI18N
        jPanel3.setLayout(null);

        miTablaPrestamosBeams.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        miTablaPrestamosBeams.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane3.setViewportView(miTablaPrestamosBeams);

        jPanel3.add(jScrollPane3);
        jScrollPane3.setBounds(10, 80, 1000, 110);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/deleteproyect.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton4);
        jButton4.setBounds(730, 10, 110, 50);

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/okproyector.png"))); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton6);
        jButton6.setBounds(870, 10, 100, 50);

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Cancelar prestamo");
        jPanel3.add(jLabel7);
        jLabel7.setBounds(724, 60, 120, 14);

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Confirmar devolucion");
        jPanel3.add(jLabel8);
        jLabel8.setBounds(850, 60, 140, 14);

        jTextField2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel3.add(jTextField2);
        jTextField2.setBounds(140, 30, 110, 20);

        jButton7.setText("Registrar Salon");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton7);
        jButton7.setBounds(260, 30, 140, 23);

        combosalones.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel3.add(combosalones);
        combosalones.setBounds(430, 30, 80, 20);

        jButton8.setText("Remover Salon");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton8);
        jButton8.setBounds(520, 30, 130, 23);

        jButton9.setText("P");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton9);
        jButton9.setBounds(670, 30, 40, 23);

        add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 1020, 200));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/fondo-azul-transparente.png"))); // NOI18N
        jLabel6.setText("                       ");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1050, 230));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/fondo-azul-transparente.png"))); // NOI18N
        jLabel2.setText("                       ");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 1050, 280));
    }// </editor-fold>//GEN-END:initComponents

    Libro libroencontrado = new Libro();



    

    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
   
   if(prestamolibrosjpa.findPrestamoLibroEntities().size()==0){     
      JOptionPane.showMessageDialog(null, "No se encuentran prestamos registrados");
      
   }else if (miTablaPrestamolibros.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Debes seleecionar un prestamo");

   }else{  

       String codLibro = String.valueOf(miTablaPrestamolibros.getModel().getValueAt(miTablaPrestamolibros.getSelectedRow(), 3));      
       PrestamoLibro p = prestamolibrosjpa.findPrestamoLibro(codLibro);  //*
       
        if(p.getEstadoPrestamo().equals("Activo")){
        
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int opc = JOptionPane.showConfirmDialog (null, "    El prestamo indicado ya se encuentra Activo \n"
                    + " ¿Desea renovar este prestamo del libro "+p.getCodigoLibro()+" "+p.getNombreLibro()+" ?",
                                                           "AVISO", dialogButton);

            if(opc == 0) {
            
                p.renovarPrestamoLibro();
                p.setEstadoPrestamo("Renovado");
                
                try {
                    prestamolibrosjpa.edit(p);
                } catch (Exception ex) {
                    Logger.getLogger(panelGestionPrestamos.class.getName()).log(Level.SEVERE, null, ex);
                }
                updateTableLibros();
            }
            

        
        }else if(p.getEstadoPrestamo().equals("Renovado")){
            JOptionPane.showMessageDialog(null, "El prestamo no puede ser renovado mas de una vez, debe confirmar devolucion \n"
                                              + "    una ves devulto el libro, el usuario puede solicitar nuevamente el prestamo del mismo", "Error",JOptionPane.ERROR_MESSAGE);
        
        }else{
            p.setEstadoPrestamo("Activo");
            JOptionPane.showMessageDialog(null, "El estado del prestamo ahora es Activo");
           try {
               prestamolibrosjpa.edit(p);
               updateTableLibros();
               
           } catch (Exception ex) {
               Logger.getLogger(panelGestionPrestamos.class.getName()).log(Level.SEVERE, null, ex);
           }
        } 
    }

    }//GEN-LAST:event_jButton1ActionPerformed

    


    int contposs = 0;
    ArrayList<Integer> vecSel = new ArrayList();

    // ACTION BOTON BUSCAR LIBRO
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        int selc = -1;

        if (jTextField1.getText().equals("".trim())) {
            JOptionPane.showMessageDialog(null, "Debes ingresar un criterio de busqueda ");

        } else {

            if (jComboBox2.getSelectedItem().equals("Codigo libro")) {

                PrestamoLibro libro = prestamolibrosjpa.findPrestamoLibro(jTextField1.getText().trim());

                if (libro != null) {

                    for (int i = 0; i < listaPrestamoLibrosdelaBD.size(); i++) {
                        if (listaPrestamoLibrosdelaBD.get(i).getCodigoLibro().equals(libro.getCodigoLibro())) {
                            selc = i;
                            break;
                        }
                    }

                    miTablaPrestamolibros.getSelectionModel().setSelectionInterval(selc, selc);

                    Rectangle r = miTablaPrestamolibros.getCellRect(selc, 0, true);

                    // Mueve el scroll para que el rectangulo sea visible *
                    miTablaPrestamolibros.scrollRectToVisible(r);

                } else {
                    JOptionPane.showMessageDialog(null, "No se ha encontrado resultados con el codigo libro ingresado");

                }
            }

            if (jComboBox2.getSelectedItem().equals("Titulo libro")) {

                String nombrelibro = jTextField1.getText().trim();

                vecSel.clear();

                for (int i = 0; i < listaPrestamoLibrosdelaBD.size(); i++) {
                    if (listaPrestamoLibrosdelaBD.get(i).getNombreLibro().contains(nombrelibro)) {
                        vecSel.add(i);
                    }
                }

                if (vecSel.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No se encontraron resultados de busqueda");

                } else if (vecSel.size() == 1) {
                    // int sel = vecSel.get(0);

                    miTablaPrestamolibros.getSelectionModel().setSelectionInterval(vecSel.get(0), vecSel.get(0));
                    Rectangle r = miTablaPrestamolibros.getCellRect(vecSel.get(0), 0, true);

                    // Mueve el scroll para que el rectangulo sea visible *
                    miTablaPrestamolibros.scrollRectToVisible(r);

                } else if (vecSel.size() > 1) {

                    int sel = vecSel.get(contposs);

                    Rectangle r = miTablaPrestamolibros.getCellRect(sel, 0, true);
                    miTablaPrestamolibros.getSelectionModel().setSelectionInterval(sel, sel);

                    // Mueve el scroll para que el rectangulo sea visible *
                    miTablaPrestamolibros.scrollRectToVisible(r);

                    contposs++;
                    if (contposs >= vecSel.size()) {
                        contposs = 0;
                    }

                }

            }

   //-------------------------
       if (jComboBox2.getSelectedItem().equals("ID usuario")) {

                PrestamoLibro libro = prestamolibrosjpa.findPrestamoLibro(jTextField1.getText().trim());

                if (libro != null) {

                    for (int i = 0; i < listaPrestamoLibrosdelaBD.size(); i++) {
                        if (listaPrestamoLibrosdelaBD.get(i).getIdUsuario().equals(libro.getIdUsuario())) {
                            selc = i;
                            break;
                        }
                    }

                    miTablaPrestamolibros.getSelectionModel().setSelectionInterval(selc, selc);

                    Rectangle r = miTablaPrestamolibros.getCellRect(selc, 0, true);

                    // Mueve el scroll para que el rectangulo sea visible *
                    miTablaPrestamolibros.scrollRectToVisible(r);

                } else {
                    JOptionPane.showMessageDialog(null, "No se ha encontrado resultados con el ID usuario indicado");

                }
            }
   
   
   //-------------------------
   
               if (jComboBox2.getSelectedItem().equals("Nombre usuario")) {

                String nameuser = jTextField1.getText().trim();

                vecSel.clear();

                for (int i = 0; i < listaPrestamoLibrosdelaBD.size(); i++) {
                    if (listaPrestamoLibrosdelaBD.get(i).getNombreUsuario().contains(nameuser)) {
                        vecSel.add(i);
                    }
                }

                if (vecSel.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No se encontraron resultados de busqueda");

                } else if (vecSel.size() == 1) {
                    // int sel = vecSel.get(0);

                    miTablaPrestamolibros.getSelectionModel().setSelectionInterval(vecSel.get(0), vecSel.get(0));
                    Rectangle r = miTablaPrestamolibros.getCellRect(vecSel.get(0), 0, true);

                    // Mueve el scroll para que el rectangulo sea visible *
                    miTablaPrestamolibros.scrollRectToVisible(r);

                } else if (vecSel.size() > 1) {

                    int sel = vecSel.get(contposs);

                    Rectangle r = miTablaPrestamolibros.getCellRect(sel, 0, true);
                    miTablaPrestamolibros.getSelectionModel().setSelectionInterval(sel, sel);

                    // Mueve el scroll para que el rectangulo sea visible *
                    miTablaPrestamolibros.scrollRectToVisible(r);

                    contposs++;
                    if (contposs >= vecSel.size()) {
                        contposs = 0;
                    }

                }

            }
            
            
        }

          
    }//GEN-LAST:event_jButton5ActionPerformed

    
    // cancelar prestamo
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        if (prestamolibrosjpa.findPrestamoLibroEntities().size() == 0) {
            JOptionPane.showMessageDialog(null, "No se encuentran prestamos registrados", "Error",JOptionPane.ERROR);

        } else if (miTablaPrestamolibros.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Debes seleecionar un prestamo");

        } else {

            String codLibro = String.valueOf(miTablaPrestamolibros.getModel().getValueAt(miTablaPrestamolibros.getSelectedRow(), 3));
            PrestamoLibro p = prestamolibrosjpa.findPrestamoLibro(codLibro);  //*

            int dialogButton = JOptionPane.YES_NO_OPTION;
            int opc = JOptionPane.showConfirmDialog(null,
                    " ¿Esta seguro de cancelar el prestamo del libro " + p.getCodigoLibro() + " " + p.getNombreLibro() + " ?",
                    "AVISO", dialogButton);

            if (opc == 0) {
                try {
                    Libro lencontrado = librosjpa.findLibro(codLibro);
                    lencontrado.setEstado("Disponible");
                    librosjpa.edit(lencontrado);

                    prestamolibrosjpa.destroy(codLibro);

                    updateTableLibros();
                    JOptionPane.showMessageDialog(null, "Prestamo cancelado");

                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(panelGestionPrestamos.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(panelGestionPrestamos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (prestamolibrosjpa.findPrestamoLibroEntities().size() == 0) {
            JOptionPane.showMessageDialog(null, "No se encuentran prestamos registrados", "Error",JOptionPane.ERROR);

        } else if (miTablaPrestamolibros.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Debes seleecionar un prestamo");

        } else {

            String codLibro = String.valueOf(miTablaPrestamolibros.getModel().getValueAt(miTablaPrestamolibros.getSelectedRow(), 3));
            PrestamoLibro p = prestamolibrosjpa.findPrestamoLibro(codLibro);  //*

            int dialogButton = JOptionPane.YES_NO_OPTION;
            int opc = JOptionPane.showConfirmDialog(null,
                    " ¿Confirma la devolucion del prestamo del libro " + p.getCodigoLibro() + " " + p.getNombreLibro() + " ?",
                    "AVISO", dialogButton);

            if (opc == 0) {
                try {
                    Libro lencontrado = librosjpa.findLibro(codLibro);
                    lencontrado.setEstado("Disponible");
                    librosjpa.edit(lencontrado);

                    prestamolibrosjpa.destroy(codLibro);

                    updateTableLibros();
                    JOptionPane.showMessageDialog(null, "Devolucion del prestamo confirmada");

                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(panelGestionPrestamos.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(panelGestionPrestamos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    
    // cancelar prestamo beams
    
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
         if (proyectoresjpa.findVideoBeamEntities().size() == 0) {
            JOptionPane.showMessageDialog(null, "No se encuentran prestamos registrados", "Error",JOptionPane.ERROR);

        } else if (miTablaPrestamosBeams.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Debes seleecionar un prestamo");

        } else {

            String snbeam = String.valueOf(miTablaPrestamosBeams.getModel().getValueAt(miTablaPrestamosBeams.getSelectedRow(), 5));
            PrestamoBeam p = prestamoBeamsjpa.findPrestamoBeam(snbeam);  //*

            int dialogButton = JOptionPane.YES_NO_OPTION;
            int opc = JOptionPane.showConfirmDialog(null,
                    " ¿Esta seguro de cancelar el prestamo del proyector " + proyectoresjpa.findVideoBeam(p.getSerialBeam()).getProyectornum() + " " + p.getMarca()+ " ?",
                    "AVISO", dialogButton);

            if (opc == 0) {
                try {
                    VideoBeam vbencontrado = proyectoresjpa.findVideoBeam(snbeam);
                    vbencontrado.setDisponibilidad("Disponible");
                    proyectoresjpa.edit(vbencontrado);

                    prestamoBeamsjpa.destroy(snbeam);

                    updateTableBeams(); 
                    JOptionPane.showMessageDialog(null, "Prestamo cancelado");

                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(panelGestionPrestamos.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(panelGestionPrestamos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    
    // DEVOLUCION PRESTAMO BEAMS
    
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
         if (proyectoresjpa.findVideoBeamEntities().size() == 0) {
            JOptionPane.showMessageDialog(null, "No se encuentran prestamos registrados", "Error",JOptionPane.ERROR);

        } else if (miTablaPrestamosBeams.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Debes seleecionar un prestamo");

        } else {

            String snbeam = String.valueOf(miTablaPrestamosBeams.getModel().getValueAt(miTablaPrestamosBeams.getSelectedRow(), 5));
            PrestamoBeam p = prestamoBeamsjpa.findPrestamoBeam(snbeam);  //*

            int dialogButton = JOptionPane.YES_NO_OPTION;
            int opc = JOptionPane.showConfirmDialog(null,
                    " ¿Confirma la devolucion del prestamo del proyector " + proyectoresjpa.findVideoBeam(p.getSerialBeam()).getProyectornum() + " " + p.getMarca() + " ?",
                    "AVISO", dialogButton);

            if (opc == 0) {
                try {
                    VideoBeam vbencontrado = proyectoresjpa.findVideoBeam(snbeam);
                    vbencontrado.setDisponibilidad("Disponible");
                    proyectoresjpa.edit(vbencontrado);

                    prestamoBeamsjpa.destroy(snbeam);

                    updateTableBeams(); 
                    JOptionPane.showMessageDialog(null, "Devolucion del prestamo confirmada");

                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(panelGestionPrestamos.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(panelGestionPrestamos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
       
        try {
            String id = jTextField2.getText().toString();
            SalondeClases s = new SalondeClases(id);
  
            
            if(id.equals("".trim())){
            JOptionPane.showMessageDialog(null,"Debe indicar nombre salon a registrar","Error, espacio en blanco",JOptionPane.ERROR_MESSAGE);

            }else if(salonesjpa.findSalondeClasesEntities().contains(s)){
            JOptionPane.showMessageDialog(null,"Salon "+id+" ya se encuentra registrado");
            
            }else{
             salonesjpa.create(s);
            JOptionPane.showMessageDialog(null,"Salon "+id+" registrado con exito");
             
        
            cargarCombosalones();
            jTextField2.setText("");  
            cargarCombosalones();
            
            this.updateUI();
            
            }
        } catch (Exception ex) {
            Logger.getLogger(panelGestionPrestamos.class.getName()).log(Level.SEVERE, null, ex);
        }
        cargarCombosalones();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        
        String id = combosalones.getSelectedItem().toString();
        
        if(id.equals("----------")){
            JOptionPane.showMessageDialog(null,"Debes selecionar un salon a remover");
        }else{
            try {
                salonesjpa.destroy(id);
                
            } catch (Conexion.exceptions.NonexistentEntityException ex) {
                Logger.getLogger(panelGestionPrestamos.class.getName()).log(Level.SEVERE, null, ex);
            }
            cargarCombosalones();
        }
        cargarCombosalones();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
     
    }//GEN-LAST:event_jButton9ActionPerformed


 
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> combosalones;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTable miTablaPrestamolibros;
    private javax.swing.JTable miTablaPrestamosBeams;
    // End of variables declaration//GEN-END:variables

    
        // actulizar tabla prestamos con info base de datos
    public void updateTableBeams() {

         // ACTUALIZACION DE LA TABLA
        // volver asignarle el nuevo valor de la lista de la base de datos
         updateListaPrestamoBeamsBD();
        // metodo es diferente ya no es setModel
        // actualiza nuestro modelo actual de la tabla
        ((AbstractTableModel) miTablaPrestamosBeams.getModel()).fireTableDataChanged();

    }
    
    
    // actulizar tabla con info base de datos
    public void updateTableLibros() {

        // ACTUALIZACION DE LA TABLA
        // volver asignarle el nuevo valor de la lista de la base de datos
        updateListaPrestamoLibrosBD();
        // metodo es diferente ya no es setModel
        // actualiza nuestro modelo actual de la tabla
        ((AbstractTableModel) miTablaPrestamolibros.getModel()).fireTableDataChanged();

    }
    
    

    // CREANDO MODELO TABLA PRESTAMOS BEAMS 
    private class ModeloTablaBeams extends AbstractTableModel {

        // cuantas filas tiene la tabla en la base
        @Override
        public int getRowCount() {
            // devuelve la cant objetos - tantos objetos tenga la lista
            return prestamoBeamsjpa.findPrestamoBeamEntities().size();
        }

        // cuantas columnas tiene la tabla en la base
        @Override
        public int getColumnCount() {
            // cuatos items va manejar la lista
            return 9;
        }

        // la informacion de la fila y la informacion de las columnas
        @Override
        public Object getValueAt(int fila, int columna) {

           PrestamoBeam p = listaPrestamoBeamsdelaBD.get(fila);

            Object salida = "";

            switch (columna) {
                case 0:
                    salida = p.getTipoUsuario();
                    break;

                case 1:
                    salida = p.getIdUsuario();
                    break;

                case 2:
                    salida = p.getNombreUsuario();
                    break;
                    
                case 3:
                    salida = proyectoresjpa.findVideoBeam(p.getSerialBeam()).getProyectornum();
                    break;

                case 4:
                    salida = p.getMarca();
                    break;

                case 5:
                    salida = p.getSerialBeam();
                    break;
                    
                case 6:
                    salida = p.getFechaPrestamo();
                    break;

                case 7:
                    salida = p.getSalonPrestamo();
                    break;
                    
                case 8:
                    salida = p.getEstadoPrestamo();
                    break;
            }

            return salida;

        }

        // nombres de las columnas
        @Override
        public String getColumnName(int columna) {

            switch (columna) {
                case 0:
                    return "USUARIO";

                case 1:
                    return "ID";
                    
                case 2:
                    return "NOMBRE";

                case 3:
                    return "#";

                case 4:
                    return "MARCA";
                    
                case 5:
                    return "S/N";

                case 6:
                    return "PRESTAMO";
                    
                case 7:
                    return "SALON";

                case 8:
                    return "ESTADO";
            }

            return "";

        }

    }
    
    
    //--------------------------
    
     // CREANDO MODELO TABLA PRESTAMOS LIBROS
        
    private class ModeloTablaLibros extends AbstractTableModel {

        // cuantas filas tiene la tabla en la base
        @Override
        public int getRowCount() {
            // devuelve la cant objetos - tantos objetos tenga la lista
            return listaPrestamoLibrosdelaBD.size();
        }

        // cuantas columnas tiene la tabla en la base
        @Override
        public int getColumnCount() {
            // cuatos items va manejar la lista
            return 8;
        }

        // la informacion de la fila y la informacion de las columnas
        @Override
        public Object getValueAt(int fila, int columna) {

            PrestamoLibro nuevoPrestamo = listaPrestamoLibrosdelaBD.get(fila);

            Object salida = "";

            switch (columna) {
                case 0:
                    salida = nuevoPrestamo.getTipoUsuario();
                    break;      
                    
                case 1:
                    salida = nuevoPrestamo.getIdUsuario();
                    break;
                    
                case 2:
                    salida = nuevoPrestamo.getNombreUsuario();
                    break;
                    
                case 3:
                    salida = nuevoPrestamo.getCodigoLibro();
                    break;

                case 4:
                    salida = nuevoPrestamo.getNombreLibro();
                    break;

                case 5:
                    salida = nuevoPrestamo.getFechaPrestamo();
                    break;
                    
                case 6:
                    salida = nuevoPrestamo.getEntregaPrestamo();
                    break;

                case 7:
                    salida = nuevoPrestamo.getEstadoPrestamo();
                    break;

            }

            return salida;

        }

        // nombres de las columnas
        @Override
        public String getColumnName(int columna) {

            switch (columna) {
                case 0:
                    return "USUARIO";

                case 1:
                    return "ID";

                case 2:
                    return "NOMBRE";

                case 3:
                    return "COD. LIBRO";
                    
                case 4:
                    return "TITULO";
                    
                case 5:
                    return "PRESTAMO";

                case 6:
                    return "ENTREGA";
                    
                case 7:
                    return "ESTADO";
        }

            return "";

        }

     

    }

 
    
}
