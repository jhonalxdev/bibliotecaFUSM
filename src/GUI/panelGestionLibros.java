/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DAO.LibroJpaController;
import DAO.PrestamoLibroJpaController;

import DAO.exceptions.NonexistentEntityException;
import bibliotecafusm.ControladorConsulta;
import bibliotecafusm.Libro;
import bibliotecafusm.PrestamoLibro;
import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import javax.swing.table.TableColumnModel;

/**
 *
 * @author Jhon Alex
 */
public class panelGestionLibros extends javax.swing.JPanel {

    private LibroJpaController librosjpa;

    private List<Libro> listaLibrosdelaBD;

    List<Libro> librosTabla;
    
    
        //----------------------------------------
    private PrestamoLibroJpaController prestamolibrosjpa;
    
    private ControladorConsulta controlador;

    private String fechast = "";

    /**
     * Creates new form panel1
     */
    public panelGestionLibros(ControladorConsulta controlador) {
        initComponents();
        this.controlador = controlador;
        cargarComboAnos();

        lblupdateicon.setVisible(false);

        // instancia objeto que va guardar la info a la base de datos :: usuariojpa
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BibliotecaFUSMPU");
        librosjpa = new LibroJpaController(emf);
        
        prestamolibrosjpa = new PrestamoLibroJpaController(emf);

        // agrega la lista de usuarios de la base de datos a mi atributo lista
        // si la base de datos esta vacia el valor asignado es null
        
//        listaLibrosdelaBD = librosjpa.findLibros(load);
        listaLibrosdelaBD = controlador.getLibrosBD();

        // asignacion de modelo a tabla
        miTabla.setModel(new ModeloTabla());
        organizatamseccionestabla();

        // permite la selecion de solo una celda
        miTabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        jLabel17.setText("" + librosjpa.getLibroCount());

        jTextField1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                jButton5ActionPerformed(ae);

            }
        });

        jDateChooser1.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent e) {

                DateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");

                try {

                    // valida la fecha manda exception null
                    fechast = fecha.format(jDateChooser1.getDate());

                    txtfechapub.setText(fechast);

                } catch (NullPointerException nllex) {

                    // Logger.getLogger(panelGestionLibros.class.getName()).log(Level.SEVERE, null, nllex);
                }
            }
        });

        librosTabla = listaLibrosdelaBD;
        ordenadoxcarrera();              
//        //******************************
//        ImageIcon img = (ImageIcon) jLabel8.getIcon();
//        for(Libro libro: listaLibrosdelaBD){
//            libro.setImagen(img);
//            try {
//                librosjpa.edit(libro);
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//            System.out.println("Agregando portadas: al libro code: "+ libro.getCodigoLibro());
//                    
//        }
    }

    public void organizatamseccionestabla() {
        TableColumnModel cModel = miTabla.getColumnModel();
        cModel.getColumn(2).setPreferredWidth(4);
        cModel.getColumn(3).setPreferredWidth(10);
        cModel.getColumn(4).setPreferredWidth(25);
         DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(CENTER);
        miTabla.getColumnModel().getColumn(2).setCellRenderer(tcr);

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
        lblupdateicon = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtnombrelib = new javax.swing.JTextField();
        txtautores = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblcarrera = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblcodestu = new javax.swing.JLabel();
        txteditoriallib = new javax.swing.JTextField();
        txtcodigolib = new javax.swing.JTextField();
        combocarreras = new javax.swing.JComboBox<String>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        comboannios = new javax.swing.JComboBox<String>();
        comboprocedencia = new javax.swing.JComboBox<String>();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        combotomos = new javax.swing.JComboBox<String>();
        jLabel19 = new javax.swing.JLabel();
        txtfechapub = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        miTabla = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<String>();
        jComboBox2 = new javax.swing.JComboBox<String>();
        jLabel9 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

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

        lblupdateicon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/updateicon.png"))); // NOI18N
        lblupdateicon.setText("          ");
        add(lblupdateicon, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 10, 40, -1));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "   Registrar Nuevo Libro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), java.awt.Color.blue)); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtnombrelib.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel1.add(txtnombrelib, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 240, -1));

        txtautores.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel1.add(txtautores, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 200, -1));

        jLabel3.setText("Nombre del Libro *");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 120, -1));

        jLabel4.setText("Autor(es) *");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 80, -1));

        lblcarrera.setText("Categoria ");
        jPanel1.add(lblcarrera, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 100, -1));

        jLabel7.setText("Editorial *");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 60, -1));

        lblcodestu.setText("Codigo  Libro*");
        jPanel1.add(lblcodestu, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 30, 90, -1));

        txteditoriallib.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel1.add(txteditoriallib, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 200, -1));

        txtcodigolib.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel1.add(txtcodigolib, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 50, 100, -1));

        combocarreras.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "General", "Administracion", "Contaduria", "Ing. en Sistemas" }));
        jPanel1.add(combocarreras, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 120, 20));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/book_reg.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, 90, 50));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/mini_cleanform.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 380, 90, 50));

        jLabel11.setText("Procedencia");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 90, -1));

        jLabel12.setText("Registrar");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 350, -1, -1));

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Limpiar Form");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 430, 90, -1));

        jButton6.setText("Cargar imagen");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 420, 120, 20));

        jLabel8.setBackground(new java.awt.Color(153, 153, 255));
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/portal no disponible.jpg"))); // NOI18N
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 180, 200, 270));

        comboannios.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(comboannios, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 100, 80, -1));

        comboprocedencia.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "FUSM", "COMPRADO", "DONACION", "TESIS GRADO" }));
        jPanel1.add(comboprocedencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 110, 20));
        jPanel1.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 150, 20, -1));

        jLabel5.setText("Fecha Ingreso *");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 130, 90, -1));

        combotomos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25" }));
        jPanel1.add(combotomos, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 100, 50, -1));

        jLabel19.setText("Tomo        Año publicacion");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 80, 150, -1));

        txtfechapub.setEditable(false);
        jPanel1.add(txtfechapub, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 150, 110, -1));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 20, 390, 460));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/books-icon.png"))); // NOI18N
        jLabel1.setText("     ");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 80, 90));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "                 Libros Registrados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), java.awt.Color.blue)); // NOI18N
        jPanel2.setLayout(null);

        miTabla.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(miTabla);

        jPanel2.add(jScrollPane2);
        jScrollPane2.setBounds(8, 80, 590, 300);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/editbook.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3);
        jButton3.setBounds(380, 390, 90, 50);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/removebook.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton4);
        jButton4.setBounds(480, 390, 90, 50);

        jLabel15.setText("Buscar por:");
        jPanel2.add(jLabel15);
        jLabel15.setBounds(260, 20, 80, 14);
        jPanel2.add(jTextField1);
        jTextField1.setBounds(337, 40, 190, 20);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/icono_buscar1.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton5);
        jButton5.setBounds(537, 17, 48, 50);

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 102, 102));
        jPanel2.add(jLabel17);
        jLabel17.setBounds(80, 20, 70, 40);

        jLabel18.setText("Listado por:");
        jPanel2.add(jLabel18);
        jLabel18.setBounds(150, 20, 80, 14);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Categoria", "Procedencia" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jPanel2.add(jComboBox1);
        jComboBox1.setBounds(150, 40, 100, 20);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Codigo", "Nombre", "Autor" }));
        jPanel2.add(jComboBox2);
        jComboBox2.setBounds(260, 40, 70, 20);

        jLabel9.setText("Editar                    Eliminar");
        jPanel2.add(jLabel9);
        jLabel9.setBounds(410, 440, 160, 14);

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 610, 460));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/brown.png"))); // NOI18N
        jLabel2.setText("                       ");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 1050, 300));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/brown.png"))); // NOI18N
        jLabel6.setText("                       ");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1050, 200));
    }// </editor-fold>//GEN-END:initComponents

    Libro libroencontrado = new Libro();

    public boolean casillasenBlanco() {
        boolean salida = false;

        if (txtnombrelib.getText().equals("".trim())) {
            salida = true;
        }
        if (txtautores.getText().equals("".trim())) {
            salida = true;
        }

        if (txtcodigolib.getText().equals("".trim())) {
            salida = true;
        }

        if (txteditoriallib.getText().equals("".trim())) {
            salida = true;
        }

        if (txtfechapub.getText().equals("".trim())) {
            salida = true;
        }

        return salida;
    }

    // ACTUALIZAR // REGISTRAR

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed


        if (casillasenBlanco()) {
            JOptionPane.showMessageDialog(null, "Debe completar todos campos obligatorios");

        } else if (jLabel12.getText().equals("Actualizar")) {

   
            try {

                // crear fecha apartir de un string
                
                
                     DateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
                     Date editdate =null;

                try {
                    editdate = fecha.parse(txtfechapub.getText());

                } catch (ParseException ex) {
                    //ex.printStackTrace();
                }

                jDateChooser1.setDate(editdate);
               
 

                if (jDateChooser1.getDate().after(new Date())) {
                    JOptionPane.showMessageDialog(null, "La fecha de ingreso no puede ser posterior a la fecha actual","Error al procesar solicitud",JOptionPane.ERROR_MESSAGE);

                } else{
                
                libroencontrado.setNombreLibro(txtnombrelib.getText().trim());;
                libroencontrado.setAutor_es(txtautores.getText().trim());
                libroencontrado.setEditorial(txteditoriallib.getText().trim());

                libroencontrado.setTomo("" + combotomos.getSelectedItem().toString());
                libroencontrado.setAnnioPub("" + comboannios.getSelectedItem().toString());
                libroencontrado.setProcedencia("" + comboprocedencia.getSelectedItem().toString());
                libroencontrado.setCarrera("" + combocarreras.getSelectedItem().toString());

                libroencontrado.setImagen((ImageIcon) jLabel8.getIcon());

                // actuliza informacion del libro
                librosjpa.edit(libroencontrado);

                
        
                

                txtcodigolib.setEnabled(true);

                limpiarForm();

                // update al final de la edicion
                updateTable();
                

                //--- seleccion de fila editada------------------------------
                int selc = -1;

                for (int i = 0; i < listaLibrosdelaBD.size(); i++) {
                    if (listaLibrosdelaBD.get(i).getCodigoLibro().equals(libroencontrado.getCodigoLibro())) {
                        selc = i;
                        break;
                    }
                }

                miTabla.getSelectionModel().setSelectionInterval(selc, selc);

                Rectangle r = miTabla.getCellRect(selc, 0, true);

                // Mueve el scroll para que el rectangulo sea visible *
                miTabla.scrollRectToVisible(r);

                
                
                        setNormalPanelSuperior();
                // Notificacion usuario actulizado
                JOptionPane.showMessageDialog(null, "Informacion del libro actualizada");
                
                
                
                
                //----------------------------------------------------------------
                activosBntnsPanelLibros(true);

                

                }
                
            } catch (NumberFormatException nfx) {
                JOptionPane.showMessageDialog(null, "Error al actulizar libro");
                // Logger.getLogger(panel1.class.getName()).log(Level.SEVERE, null, nfx);
            } catch (NullPointerException nx) {
                JOptionPane.showMessageDialog(null, "La fecha de ingreso no puede ser posterior a la fecha actual");
                // Logger.getLogger(panel1.class.getName()).log(Level.SEVERE, null, nfx);

            } catch (NonexistentEntityException ex) {
                //Logger.getLogger(panel1.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(panelRegistroUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {

            miTabla.clearSelection();
            
            
            try {

                if (jDateChooser1.getDate().after(new Date())) {
                    JOptionPane.showMessageDialog(null, "La fecha de ingreso no puede ser posterior a la fecha actual","Error al procesar solicitud",JOptionPane.ERROR_MESSAGE);

                } else {

                    String codlib = "" + txtcodigolib.getText().trim();
                    String nombrelib = "" + txtnombrelib.getText().trim();
                    String autoreslib = "" + txtautores.getText().trim();
                    String editoriallib = "" + txteditoriallib.getText().trim();

                    String tomo = "" + combotomos.getSelectedItem().toString();
                    String annio = "" + comboannios.getSelectedItem().toString();
                    String procedencia = comboprocedencia.getSelectedItem().toString();

                    String carrera = combocarreras.getSelectedItem().toString();

                    ImageIcon img = (ImageIcon) jLabel8.getIcon();

                    //     public Libro(String codigoLibro, String nombreLibro, String autor_es, String procedencia, String carrera, String fechaIngreso) {
                    Libro nuevolibro = new Libro(codlib, nombrelib, autoreslib, procedencia, carrera, annio, editoriallib, tomo, fechast);
                    nuevolibro.setImagen(img);
                    librosjpa.create(nuevolibro);

                    
                    
//                    listaLibrosdelaBD = librosjpa.findLibros(load);
                    listaLibrosdelaBD = controlador.getLibrosBD();
                    librosTabla = listaLibrosdelaBD;

                    ordenadoxcarrera();
//                    updateTable();
                    jComboBox1.setSelectedItem("Categoria");
                    
                    
                    JOptionPane.showMessageDialog(null, "Nuevo libro registrado con Exito");

                    limpiarForm();

            

                }

            } catch (NumberFormatException nfx) {
                JOptionPane.showMessageDialog(null, "Error al agregar libro, Debes seleccionar una fecha de ingreso");
                // Logger.getLogger(panel1.class.getName()).log(Level.SEVERE, null, nfx);
            } catch (NonexistentEntityException ex) {
                //Logger.getLogger(panel1.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al agregar, El libro indicado ya se ecuentra registrado");
                //Logger.getLogger(panel1.class.getName()).log(Level.SEVERE, null, ex);
            }
        } // fin else 

    }//GEN-LAST:event_jButton1ActionPerformed


    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        if (jLabel13.getText().equals("Cancelar")) {

            setNormalPanelSuperior();
            txtcodigolib.setEnabled(true);
        }

        limpiarForm();

    }//GEN-LAST:event_jButton2ActionPerformed

    
    
    public void setNormalPanelSuperior() {
        jPanel1.setBackground(jPanel2.getBackground());

        TitledBorder titulo = new TitledBorder("Agregar Usuario");
        titulo.setTitleColor(Color.BLUE);
        jPanel1.setBorder(titulo);

        lblupdateicon.setVisible(false);

        // reactivar panel inferior
        activosBntnsPanelLibros(true);

        txtcodigolib.setToolTipText("");

        // iconos normales              
        //Añadimos la imagen al boton agregar que ahora sera actulizar
        ImageIcon confirm = new ImageIcon(getClass().getResource("/res/book_reg.png"));
        jButton1.setIcon(confirm);
        // cambiamos texto de la etiqueta 
        jLabel12.setText("Registrar");

        //Añadimos la imagen al boton limpiar que ahora sera cancelar
        ImageIcon cancel = new ImageIcon(getClass().getResource("/res/mini_cleanform.png"));
        jButton2.setIcon(cancel);
        // cambiamos texto de la etiqueta 
        jLabel13.setText("Limpiar Form");
    }


    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

// tomar datos del usuario seleccionado
        if (miTabla.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Debes seleecionar un libro a editar");

        } else {

            String codLibro = String.valueOf(miTabla.getModel().getValueAt(miTabla.getSelectedRow(), 2));

            libroencontrado = librosjpa.findLibro(codLibro);

            pasarInfoPanelUpdateUser(libroencontrado);

            jPanel1.setBorder(new TitledBorder("       Editar Informacion Libro"));
            lblupdateicon.setVisible(true);
            jPanel1.setBackground(Color.lightGray);

            // desactiva txt identificador - este valor no es editable
            txtcodigolib.setEnabled(false);
            // asignando tooltip text
            txtcodigolib.setToolTipText("Para editar este valor debe eliminar el registro del libro y registrarlo nuevamente");

            // desactivando elementos panel inferior
            activosBntnsPanelLibros(false);

            //Añadimos la imagen al boton agregar que ahora sera actulizar
            ImageIcon confirm = new ImageIcon(getClass().getResource("/res/book_update.png"));
            jButton1.setIcon(confirm);
            // cambiamos texto de la etiqueta 
            jLabel12.setText("Actualizar");

            //Añadimos la imagen al boton limpiar que ahora sera cancelar
            ImageIcon cancel = new ImageIcon(getClass().getResource("/res/cancelarmini.png"));
            jButton2.setIcon(cancel);
            // cambiamos texto de la etiqueta 
            jLabel13.setText("Cancelar");

        } // fin else   
    }//GEN-LAST:event_jButton3ActionPerformed

    public void pasarInfoPanelUpdateUser(Libro libro) {

        jLabel8.setIcon(libro.getImagen());

        txtnombrelib.setText(libro.getNombreLibro());
        txtautores.setText(libro.getAutor_es());
        txtcodigolib.setText(libro.getCodigoLibro());
        txteditoriallib.setText(libro.getEditorial());

        combocarreras.setSelectedItem(libro.getCarrera());
        comboprocedencia.setSelectedItem(libro.getProcedencia());
        combotomos.setSelectedItem(libro.getTomo());

        if (libro.getAnnioPub().equals("-")) {
            comboannios.setSelectedItem("-");
        } else {
            comboannios.setSelectedItem(Integer.parseInt(libro.getAnnioPub()));
        }

        txtfechapub.setText(libro.getFechaIngreso());

    }

    public void activosBntnsPanelLibros(Boolean val) {

        jButton3.setEnabled(val);

        jButton4.setEnabled(val);

        jButton5.setEnabled(val);

        miTabla.setEnabled(val);

        jTextField1.setEnabled(val);

    }


    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        
        
        
        //aca capturo el id en la pos 2 de la celda seleccionada 
        if (miTabla.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Debes seleecionar un libro a eliminar");
        } else {

           jButton4.setEnabled(false);
        
            String idlibro = String.valueOf(miTabla.getModel().getValueAt(miTabla.getSelectedRow(), 2));
            String nombrelibro = String.valueOf(miTabla.getModel().getValueAt(miTabla.getSelectedRow(), 0));


            
            try {

                int dialogButton = JOptionPane.YES_NO_OPTION;
                int opc = JOptionPane.showConfirmDialog(null, "Esto causara la eliminacion de los prestamos sobre este libro \n"
                        + "       ¿Esta seguro de eliminar el libro " + idlibro + " - " + nombrelibro + " ?",
                        "AVISO", dialogButton);

                if (opc == 0) {


                     // si el libro ha sido prestado eliminelo de la lista prestamolibros
                if(!prestamolibrosjpa.findPrestamoLibroEntities().isEmpty()){
                    if (!prestamolibrosjpa.findPrestamoLibro(getIdprestamoxCodLibro (idlibro)).equals(null)) {
                        prestamolibrosjpa.destroy(getIdprestamoxCodLibro (idlibro)); 

                    }
                }
                    librosjpa.destroy(idlibro);

                    if (jComboBox1.getSelectedItem().toString().equals("Carrera")) {
                        ordenadoxcarrera();
                    }
                    if (jComboBox1.getSelectedItem().toString().equals("Procedencia")) {
                        ordenadoxproced();               
                    }
                    
                      jButton4.setEnabled(true);
                    JOptionPane.showMessageDialog(null, "Libro eliminado con exito");
                    
    
                }else{
                    jButton4.setEnabled(true);
                }

            } catch (NonexistentEntityException ex) {
                // Logger.getLogger(panelGestionLibros.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }//GEN-LAST:event_jButton4ActionPerformed

    int contposs = 0;
    ArrayList<Integer> vecSel = new ArrayList();

    // ACTION BOTON BUSCAR
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        int selc = -1;

        if (jTextField1.getText().equals("".trim())) {
            JOptionPane.showMessageDialog(null, "Debes ingresar un criterio de busqueda ");

        } else {

            if (jComboBox2.getSelectedItem().equals("Codigo")) {

                Libro libro = librosjpa.findLibro(jTextField1.getText().trim());

                if (libro != null) {

                    for (int i = 0; i < listaLibrosdelaBD.size(); i++) {
                        if (librosTabla.get(i).getCodigoLibro().equals(libro.getCodigoLibro())) {
                            selc = i;
                            break;
                        }
                    }

                    miTabla.getSelectionModel().setSelectionInterval(selc, selc);

                    Rectangle r = miTabla.getCellRect(selc, 0, true);

                    // Mueve el scroll para que el rectangulo sea visible *
                    miTabla.scrollRectToVisible(r);

                } else {
                    JOptionPane.showMessageDialog(null, "No se ha encontrado un libro con el codigo ingresado");

                }
            }

            if (jComboBox2.getSelectedItem().equals("Nombre")) {

                String nombrelibro = jTextField1.getText().trim();

                vecSel.clear();

                for (int i = 0; i < librosTabla.size(); i++) {
                    if (librosTabla.get(i).getNombreLibro().contains(nombrelibro)) {
                        vecSel.add(i);
                    }
                }

                if (vecSel.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No se encontraron resultados de busqueda");

                } else if (vecSel.size() == 1) {
                    // int sel = vecSel.get(0);

                    miTabla.getSelectionModel().setSelectionInterval(vecSel.get(0), vecSel.get(0));
                    Rectangle r = miTabla.getCellRect(vecSel.get(0), 0, true);

                    // Mueve el scroll para que el rectangulo sea visible *
                    miTabla.scrollRectToVisible(r);

                } else if (vecSel.size() > 1) {

                    int sel = vecSel.get(contposs);

                    Rectangle r = miTabla.getCellRect(sel, 0, true);
                    miTabla.getSelectionModel().setSelectionInterval(sel, sel);

                    // Mueve el scroll para que el rectangulo sea visible *
                    miTabla.scrollRectToVisible(r);

                    contposs++;
                    if (contposs >= vecSel.size()) {
                        contposs = 0;
                    }

                }

            }

   //-------------------------
   
               if (jComboBox2.getSelectedItem().equals("Autor")) {

                String autor = jTextField1.getText().trim();

                vecSel.clear();

                for (int i = 0; i < librosTabla.size(); i++) {
                    if (librosTabla.get(i).getAutor_es().contains(autor)) {
                        vecSel.add(i);
                    }
                }

                if (vecSel.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No se encontraron resultados de busqueda");

                } else if (vecSel.size() == 1) {
                    // int sel = vecSel.get(0);

                    miTabla.getSelectionModel().setSelectionInterval(vecSel.get(0), vecSel.get(0));
                    Rectangle r = miTabla.getCellRect(vecSel.get(0), 0, true);

                    // Mueve el scroll para que el rectangulo sea visible *
                    miTabla.scrollRectToVisible(r);

                } else if (vecSel.size() > 1) {

                    int sel = vecSel.get(contposs);

                    Rectangle r = miTabla.getCellRect(sel, 0, true);
                    miTabla.getSelectionModel().setSelectionInterval(sel, sel);

                    // Mueve el scroll para que el rectangulo sea visible *
                    miTabla.scrollRectToVisible(r);

                    contposs++;
                    if (contposs >= vecSel.size()) {
                        contposs = 0;
                    }

                }

            }
            
            
        }
    }//GEN-LAST:event_jButton5ActionPerformed


    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        try {

            JFileChooser selectorImgen = new JFileChooser();
            selectorImgen.setAcceptAllFileFilterUsed(false);

            FileFilter filtro1 = new FileNameExtensionFilter("JPG file", "jpg");
            FileFilter filtro2 = new FileNameExtensionFilter("PNG file", "png");

            selectorImgen.setFileFilter(filtro1);
            selectorImgen.addChoosableFileFilter(filtro2);
            selectorImgen.showOpenDialog(null);

            File imgArchivo = selectorImgen.getSelectedFile();
            String rutafile = imgArchivo.getAbsolutePath();
            rutafile = rutafile.replace("\\", "//");

            ImageIcon img = new ImageIcon(rutafile);

            // ajustar imagen a tamaño del Jlabel
            jLabel8.setIcon(new ImageIcon(img.getImage().getScaledInstance(200, 320, Image.SCALE_SMOOTH)));

        } catch (Exception e) {
        }


    }//GEN-LAST:event_jButton6ActionPerformed

    
    public void ordenadoxproced(){
        librosTabla = listaLibrosdelaBD;
        librosTabla.clear();
        
//        listaLibrosdelaBD = librosjpa.findLibros(load);
        listaLibrosdelaBD = controlador.getLibrosBD();
    
        for (int i = 0; i <= listaLibrosdelaBD.size() - 1; i++) {
                if (listaLibrosdelaBD.get(i).getProcedencia().equals("FUSM")) {
                    librosTabla.add(listaLibrosdelaBD.get(i));
                }
            }
            for (int i = 0; i <= listaLibrosdelaBD.size() - 1; i++) {
                if (listaLibrosdelaBD.get(i).getProcedencia().equals("COMPRADO")) {
                    librosTabla.add(listaLibrosdelaBD.get(i));
                }
            }
            for (int i = 0; i <= listaLibrosdelaBD.size() - 1; i++) {
                if (listaLibrosdelaBD.get(i).getProcedencia().equals("DONACION")) {
                    librosTabla.add(listaLibrosdelaBD.get(i));
                }
            }
            for (int i = 0; i <= listaLibrosdelaBD.size() - 1; i++) {
                if (listaLibrosdelaBD.get(i).getProcedencia().equals("TESIS GRADO")) {
                    librosTabla.add(listaLibrosdelaBD.get(i));
                }
            }

            updateTableNuevo(librosTabla);
        
        
        }
    
    public void ordenadoxcarrera(){
//        librosTabla = listaLibrosdelaBD;
//        librosTabla.clear();
        librosTabla = new ArrayList<>();
//        listaLibrosdelaBD = librosjpa.findLibros(load);
        listaLibrosdelaBD = controlador.getLibrosBD();
        
        for (int i = 0; i <= listaLibrosdelaBD.size() - 1; i++) {
                if (listaLibrosdelaBD.get(i).getCarrera().equals("Administracion")) {
                    librosTabla.add(listaLibrosdelaBD.get(i));
                }
            }
            for (int i = 0; i <= listaLibrosdelaBD.size() - 1; i++) {
                if (listaLibrosdelaBD.get(i).getCarrera().equals("Contaduria")) {
                    librosTabla.add(listaLibrosdelaBD.get(i));
                }
            }
            for (int i = 0; i <= listaLibrosdelaBD.size() - 1; i++) {
                if (listaLibrosdelaBD.get(i).getCarrera().equals("Ing. en Sistemas")) {
                    librosTabla.add(listaLibrosdelaBD.get(i));
                }
            }
            for (int i = 0; i <= listaLibrosdelaBD.size() - 1; i++) {
                if (listaLibrosdelaBD.get(i).getCarrera().equals("General")) {
                    librosTabla.add(listaLibrosdelaBD.get(i));
                }
            }

            updateTableNuevo(librosTabla);
    }
    
    
    
    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed

        librosTabla = listaLibrosdelaBD;
        librosTabla.clear();
        
//        listaLibrosdelaBD = librosjpa.findLibros(load);
        listaLibrosdelaBD = controlador.getLibrosBD();

        if (jComboBox1.getSelectedItem().equals("Ingreso")) {
            updateTable();
            
//            librosTabla = librosjpa.findLibros(load);
            librosTabla = controlador.getLibrosBD();
        }

        if (jComboBox1.getSelectedItem().equals("Categoria")) {

            for (int i = 0; i <= listaLibrosdelaBD.size() - 1; i++) {
                if (listaLibrosdelaBD.get(i).getCarrera().equals("Administracion")) {
                    librosTabla.add(listaLibrosdelaBD.get(i));
                }
            }
            for (int i = 0; i <= listaLibrosdelaBD.size() - 1; i++) {
                if (listaLibrosdelaBD.get(i).getCarrera().equals("Contaduria")) {
                    librosTabla.add(listaLibrosdelaBD.get(i));
                }
            }
            for (int i = 0; i <= listaLibrosdelaBD.size() - 1; i++) {
                if (listaLibrosdelaBD.get(i).getCarrera().equals("Ing. en Sistemas")) {
                    librosTabla.add(listaLibrosdelaBD.get(i));
                }
            }
            for (int i = 0; i <= listaLibrosdelaBD.size() - 1; i++) {
                if (listaLibrosdelaBD.get(i).getCarrera().equals("General")) {
                    librosTabla.add(listaLibrosdelaBD.get(i));
                }
            }

            updateTableNuevo(librosTabla);

        }

        if (jComboBox1.getSelectedItem().equals("Procedencia")) {

            for (int i = 0; i <= listaLibrosdelaBD.size() - 1; i++) {
                if (listaLibrosdelaBD.get(i).getProcedencia().equals("FUSM")) {
                    librosTabla.add(listaLibrosdelaBD.get(i));
                }
            }
            for (int i = 0; i <= listaLibrosdelaBD.size() - 1; i++) {
                if (listaLibrosdelaBD.get(i).getProcedencia().equals("COMPRADO")) {
                    librosTabla.add(listaLibrosdelaBD.get(i));
                }
            }
            for (int i = 0; i <= listaLibrosdelaBD.size() - 1; i++) {
                if (listaLibrosdelaBD.get(i).getProcedencia().equals("DONACION")) {
                    librosTabla.add(listaLibrosdelaBD.get(i));
                }
            }
            for (int i = 0; i <= listaLibrosdelaBD.size() - 1; i++) {
                if (listaLibrosdelaBD.get(i).getProcedencia().equals("TESIS GRADO")) {
                    librosTabla.add(listaLibrosdelaBD.get(i));
                }
            }

            updateTableNuevo(librosTabla);

        }


    }//GEN-LAST:event_jComboBox1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboannios;
    private javax.swing.JComboBox<String> combocarreras;
    private javax.swing.JComboBox<String> comboprocedencia;
    private javax.swing.JComboBox<String> combotomos;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblcarrera;
    private javax.swing.JLabel lblcodestu;
    private javax.swing.JLabel lblupdateicon;
    private javax.swing.JTable miTabla;
    private javax.swing.JTextField txtautores;
    private javax.swing.JTextField txtcodigolib;
    private javax.swing.JTextField txteditoriallib;
    private javax.swing.JTextField txtfechapub;
    private javax.swing.JTextField txtnombrelib;
    // End of variables declaration//GEN-END:variables

    // actulizar tabla con info base de datos
    public void updateTable() {

        // ACTUALIZACION DE LA TABLA
        // volver asignarle el nuevo valor de la lista de la base de datos
        
//        listaLibrosdelaBD = librosjpa.findLibros(load);
        listaLibrosdelaBD = controlador.getLibrosBD();

        // metodo es diferente ya no es setModel
        // actualiza nuestro modelo actual de la tabla
        ((AbstractTableModel) miTabla.getModel()).fireTableDataChanged();

        jLabel17.setText("" + librosjpa.getLibroCount());
    }

    public void updateTableNuevo(List in) {

        // ACTUALIZACION DE LA TABLA
        // volver asignarle el nuevo valor de la lista de la base de datos
        listaLibrosdelaBD = in;

        // metodo es diferente ya no es setModel
        // actualiza nuestro modelo actual de la tabla
        ((AbstractTableModel) miTabla.getModel()).fireTableDataChanged();

        jLabel17.setText("" + librosjpa.getLibroCount());

    }

    public void limpiarForm() {
        txtnombrelib.setText("");
        txtautores.setText("");
        txtcodigolib.setText("");
        txteditoriallib.setText("");

        jLabel8.setIcon(new ImageIcon(getClass().getResource("/res/portal no disponible.jpg")));

        combotomos.setSelectedItem("-");
        comboannios.setSelectedItem("-");

        txtfechapub.setText("");
        

  
    }

        public Long getIdprestamoxCodLibro (String codlibro){
        
        Long id = -11L;
        
        List<PrestamoLibro> listaPrestamos = prestamolibrosjpa.findPrestamoLibroEntities();
        
        for (int i = 0; i < listaPrestamos.size(); i++) {
            if(listaPrestamos.get(i).getCodigoLibro().equals(codlibro)){
                id = listaPrestamos.get(i).getPk();
            }
        }
        
    
        return id;
    }
    
    
    // CREANDO MODELO COMBOBOX
    public void cargarComboAnos() {
        DefaultComboBoxModel mdlCombo = new DefaultComboBoxModel();
        comboannios.setModel(mdlCombo);

        Calendar c = Calendar.getInstance();

        mdlCombo.addElement("-");

        for (int i = 1980; i <= c.get(Calendar.YEAR); i++) {

            mdlCombo.addElement(i);
        }
    }

    // CREANDO MODELO TABLA 
    private class ModeloTabla extends AbstractTableModel {

        // cuantas filas tiene la tabla en la base
        @Override
        public int getRowCount() {
            // devuelve la cant objetos - tantos objetos tenga la lista
            return listaLibrosdelaBD.size();
        }

        // cuantas columnas tiene la tabla en la base
        @Override
        public int getColumnCount() {
            // cuatos items va manejar la lista
            return 5;
        }

        // la informacion de la fila y la informacion de las columnas
        @Override
        public Object getValueAt(int fila, int columna) {

            Libro nuevoLibro = listaLibrosdelaBD.get(fila);

            Object salida = "";

            switch (columna) {
                case 0:

                    salida = nuevoLibro.getNombreLibro();
                    break;

                case 1:
                    salida = nuevoLibro.getAutor_es();
                    break;

                case 2:
                    salida = nuevoLibro.getCodigoLibro();
                    break;

                case 3:
                    salida = nuevoLibro.getCarrera();
                    break;

                case 4:
                    salida = nuevoLibro.getProcedencia();
                    break;
            }

            return salida;

        }

        // nombres de las columnas
        @Override
        public String getColumnName(int columna) {

            switch (columna) {
                case 0:
                    return "NOMBRE";

                case 1:
                    return "AUTOR(ES)";

                case 2:
                    return "CODIGO";

                case 3:
                    return "CATEGORIA";

                case 4:
                    return "PROCEDENCIA";
            }

            return "";

        }

        // ASIGN
        // asigna un valor
        @Override
        public void setValueAt(Object obj, int fila, int columna) {

            Libro libro = listaLibrosdelaBD.get(fila);
            Object anterior = null;

            switch (columna) {
                case 0:
                    anterior = libro.getNombreLibro();
                    libro.setNombreLibro((String) obj);
                    break;
                case 1:
                    anterior = libro.getAutor_es();
                    libro.setAutor_es((String) obj);
                    break;
                case 4:
                    anterior = libro.getCarrera();
                    libro.setCarrera((String) obj);
                    break;
            }

            try {
                librosjpa.edit(libro);
            } catch (Exception ex) {
                Logger.getLogger(panelRegistroUsuarios.class.getName()).log(Level.SEVERE, null, ex);

                switch (columna) {
                    case 0:
                        libro.setNombreLibro((String) anterior);
                        break;
                    case 1:
                        libro.setAutor_es((String) anterior);
                        break;
                    case 4:
                        libro.setCarrera((String) anterior);
                        break;
                }

            }

        }

    }

}
