/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DAO.LibroJpaController;
import DAO.PrestamoLibroJpaController;

import DAO.exceptions.NonexistentEntityException;
import bibliotecafusm.Libro;
import bibliotecafusm.PrestamoLibro;
import bibliotecafusm.Usuario;


import java.awt.Color;
import java.awt.Image;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Array;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import javax.swing.table.TableColumnModel;

/**
 *
 * @author Jhon Alex
 */
public class panelPrestamoLibros extends javax.swing.JPanel {

    //----------------------------------------
    private LibroJpaController librosjpa;
    private List<Libro> listaLibrosdelaBD;

    List<Libro> librosTabla;

    //----------------------------------------
    private PrestamoLibroJpaController prestamolibrosjpa;
    private List<PrestamoLibro> listaPrestamoLibrosdelaBD;
    
    //-------------------------------------------
    private Usuario userlogin;
    private boolean usuarioPresentaDeuda = false;
    /**
     * Creates new form panel1
     */
    public panelPrestamoLibros(Usuario user) {
       
        // usuaario debe ser recibido como parametro de principal a this panel <<<<<<<<<<<<<<<<
        // Usuario(String identificacion, String nombres, String apellidos, String carrera, String tpuser, String numtelefono, String email, String password) {
 //>>>
//        userlogin = new Usuario("123","jhon alex","olave","Ing. Sistemas","Estudiente","3124534314","ola33@hotmail.com","321");
//        
    this.userlogin = user;
        
        
        initComponents();
         
         listalabelscarrito.add(libroimg1);
         listalabelscarrito.add(libroimg2);
         listalabelscarrito.add(libroimg3);


        // instancia objeto que va guardar la info a la base de datos 
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BibliotecaFUSMPU");
        librosjpa = new LibroJpaController(emf);
        prestamolibrosjpa = new PrestamoLibroJpaController(emf);

        // agrega la lista de usuarios de la base de datos a mi atributo lista
        // si la base de datos esta vacia el valor asignado es null
        listaLibrosdelaBD = librosjpa.findLibroEntities();
        
        
        listaPrestamoLibrosdelaBD = prestamolibrosjpa.findPrestamoLibroEntities();
        listaPrestamoLibrosdelaBD.clear();
        
        
        
        for (PrestamoLibro p : prestamolibrosjpa.findPrestamoLibroEntities()) {
            
            if (p.getIdUsuario().equals(userlogin.getIdentificacion())) {
                listaPrestamoLibrosdelaBD.add(p);
            }
        }

        // asignacion de modelo a tabla libros
        miTabla.setModel(new ModeloTabla());
        organizatamseccionestabla();
        // permite la selecion de solo una celda
        miTabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        
        // asignacion de modelo a tabla prestamosusuario
        miTablaPrestamos.setModel(new ModeloTablaPrestamos());
      
        
     
        // manejador buqueda enter
        jTextField1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                jButton5ActionPerformed(ae);

            }
        });


      
        
       // manejador evento al desplazarse o selecionar fila de mi jtable *
        ListSelectionModel rowSM = miTabla.getSelectionModel();
        rowSM.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                //Ignore extra messages.
                if (e.getValueIsAdjusting()) {
                    return;
                }

                ListSelectionModel lsm
                        = (ListSelectionModel) e.getSource();

                if (!lsm.isSelectionEmpty()) {
   
                  String codLibro = String.valueOf(miTabla.getModel().getValueAt(miTabla.getSelectedRow(), 2));
       
//                   jLabel8.setIcon(librosjpa.findLibro(codLibro).getImagen());
                   jLabel8.setIcon(new ImageIcon(librosjpa.findLibro(codLibro).getImagen().getImage().getScaledInstance(jLabel8.getWidth(), jLabel8.getHeight(), Image.SCALE_SMOOTH)));
                   
                
                }
            }
        });
        
        
        
        librosTabla = listaLibrosdelaBD;
        ordenadoxcarrera();   
        
        organizatamseccionestabla();
        
        setInfoUser();
        
        updateTablePrestamos();
    }

    
    public void setInfoUser(){
        txtnombre.setText(userlogin.getApellidos()+" "+userlogin.getNombres());
        txtacarrera.setText(userlogin.getCarrera());
        txtcodigo.setText(userlogin.getIdentificacion());
        
        // falta el estado
    }
    
    
    public void organizatamseccionestabla() {
        TableColumnModel cModel = miTabla.getColumnModel();
        cModel.getColumn(0).setPreferredWidth(200);
        cModel.getColumn(1).setPreferredWidth(200);
        cModel.getColumn(3).setPreferredWidth(80);
        cModel.getColumn(4).setPreferredWidth(130);
        cModel.getColumn(5).setPreferredWidth(100);
        
        
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(CENTER);
        miTabla.getColumnModel().getColumn(3).setCellRenderer(tcr);
        
        
        
        TableColumnModel cModel1 = miTablaPrestamos.getColumnModel();
        cModel1.getColumn(0).setPreferredWidth(60);
        cModel1.getColumn(1).setPreferredWidth(150);
        cModel1.getColumn(2).setPreferredWidth(80);
        cModel1.getColumn(3).setPreferredWidth(100);
     

        miTablaPrestamos.getColumnModel().getColumn(2).setCellRenderer(tcr);
        miTablaPrestamos.getColumnModel().getColumn(3).setCellRenderer(tcr);

  
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
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        miTablaPrestamos = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        txtnombre = new javax.swing.JTextField();
        txtacarrera = new javax.swing.JTextField();
        txtcodigo = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        libroimg1 = new javax.swing.JLabel();
        libroimg2 = new javax.swing.JLabel();
        libroimg3 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<String>();
        jComboBox2 = new javax.swing.JComboBox<String>();
        jButton1 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        miTabla = new javax.swing.JTable();
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

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/books-icon.png"))); // NOI18N
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 90, 80));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/book_car.png"))); // NOI18N
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 0, 60, -1));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mis prestamos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), java.awt.Color.blue)); // NOI18N
        jPanel3.setLayout(null);

        miTablaPrestamos.setModel(new javax.swing.table.DefaultTableModel(
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
        miTablaPrestamos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jScrollPane3.setViewportView(miTablaPrestamos);

        jPanel3.add(jScrollPane3);
        jScrollPane3.setBounds(10, 70, 450, 90);

        jLabel7.setText("Nombre: ");
        jPanel3.add(jLabel7);
        jLabel7.setBounds(10, 20, 80, 14);

        txtnombre.setEditable(false);
        jPanel3.add(txtnombre);
        txtnombre.setBounds(10, 40, 180, 20);

        txtacarrera.setEditable(false);
        jPanel3.add(txtacarrera);
        txtacarrera.setBounds(200, 40, 160, 20);

        txtcodigo.setEditable(false);
        jPanel3.add(txtcodigo);
        txtcodigo.setBounds(370, 40, 90, 20);

        jLabel9.setText("Programa:");
        jPanel3.add(jLabel9);
        jLabel9.setBounds(200, 20, 90, 14);

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("ID");
        jPanel3.add(jLabel10);
        jLabel10.setBounds(370, 20, 90, 14);

        add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 470, 200));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "          Carrito Solicitud Prestamo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), java.awt.Color.blue)); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton3.setText("Confirmar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 60, 100, 40));

        jButton4.setText("Cancelar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 120, 100, 40));

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        libroimg1.setText("     ");
        libroimg1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                libroimg1MouseClicked(evt);
            }
        });
        jPanel4.add(libroimg1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 111, 140));

        libroimg2.setText("     ");
        libroimg2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                libroimg2MouseClicked(evt);
            }
        });
        jPanel4.add(libroimg2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, 115, 140));

        libroimg3.setText("      ");
        libroimg3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                libroimg3MouseClicked(evt);
            }
        });
        jPanel4.add(libroimg3, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, 114, 140));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/library.png"))); // NOI18N
        jLabel3.setText("  ");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 170));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 400, 180));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 10, 530, 200));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "                 Libros Registrados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), java.awt.Color.blue)); // NOI18N
        jPanel2.setLayout(null);

        jLabel15.setText("Buscar por:");
        jPanel2.add(jLabel15);
        jLabel15.setBounds(180, 20, 80, 14);
        jPanel2.add(jTextField1);
        jTextField1.setBounds(270, 40, 270, 20);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/icono_buscar1.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton5);
        jButton5.setBounds(560, 20, 48, 50);

        jLabel18.setText("Ordenado por:");
        jPanel2.add(jLabel18);
        jLabel18.setBounds(90, 20, 80, 14);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Categoria", "Estado" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jPanel2.add(jComboBox1);
        jComboBox1.setBounds(90, 40, 80, 20);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Codigo", "Titulo", "Autor" }));
        jPanel2.add(jComboBox2);
        jComboBox2.setBounds(180, 40, 90, 20);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/book_add.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1);
        jButton1.setBounds(680, 10, 110, 50);

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Agregar a carrito");
        jPanel2.add(jLabel12);
        jLabel12.setBounds(680, 60, 110, 14);

        jLabel8.setBackground(new java.awt.Color(153, 153, 255));
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/portal no disponible.jpg"))); // NOI18N
        jPanel2.add(jLabel8);
        jLabel8.setBounds(820, 10, 180, 230);

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
        miTabla.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane2.setViewportView(miTabla);

        jPanel2.add(jScrollPane2);
        jScrollPane2.setBounds(10, 80, 790, 160);

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 1020, 250));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/fondo-azul-transparente.png"))); // NOI18N
        jLabel2.setText("                       ");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 1050, 280));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/fondo-azul-transparente.png"))); // NOI18N
        jLabel6.setText("                       ");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1050, 230));
    }// </editor-fold>//GEN-END:initComponents

    Libro libroencontrado = new Libro();


    
    
    private LinkedList<Libro> listaLibroscarrito = new LinkedList();
    
    private LinkedList<JLabel> listalabelscarrito = new LinkedList();
    

    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

   if(usuarioPresentaDeuda){     
                       JOptionPane.showMessageDialog(null,"Estimado usuario usted presenta deuda por vencimiento de un préstamo, en caso de alguna \n"
                            + "                   inconsistencia  por favor notifíquelo con el encargado de la biblioteca","Solicitud Denegada",JOptionPane.ERROR_MESSAGE);
                       
   }else if(librosjpa.findLibroEntities().size()==0){     
      JOptionPane.showMessageDialog(null, "No se encuentran libros registrados");
      
   }else if (miTabla.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Debes seleecionar un libro");

       
    }else if(listaLibroscarrito.size()+1>3){
        JOptionPane.showMessageDialog(null, "Completo el numero maximo de libros que puede agregar al carrito");
    }else{    
       
       
       String codLibro = String.valueOf(miTabla.getModel().getValueAt(miTabla.getSelectedRow(), 2));      
       Libro libro1 = librosjpa.findLibro(codLibro);  //*
       
 
        if(libro1.getEstado().equals("No Disponible")){
            JOptionPane.showMessageDialog(null, "El libro indicado No se encuentra disponible");
        }else if(listaLibroscarrito.contains(libro1)){  
            JOptionPane.showMessageDialog(null, "El libro indicado ya ha sido agregado al carrito");
        }else{
            
           listaLibroscarrito.add(libro1);
        }  
        
        updateCarrito();
      } 
    }//GEN-LAST:event_jButton1ActionPerformed

    
public void updateCarrito(){
    
    if(listaLibroscarrito.isEmpty()){
        
           listalabelscarrito.clear();
           
           libroimg1.setIcon(null);
           libroimg2.setIcon(null);
           libroimg3.setIcon(null);
       
           listalabelscarrito.add(libroimg1);
           listalabelscarrito.add(libroimg2);
           listalabelscarrito.add(libroimg3); 
    }
    
       for (int i = 0; i < listaLibroscarrito.size(); i++) {
           listalabelscarrito.get(i).setIcon(new ImageIcon(listaLibroscarrito.get(i).getImagen().getImage().getScaledInstance(111, 156, Image.SCALE_SMOOTH))); 
       }
     
}
    
    

    
    
    


   




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

            if (jComboBox2.getSelectedItem().equals("Titulo")) {

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


  
    public void ordenadoxcarrera(){
        librosTabla = listaLibrosdelaBD;
        librosTabla.clear();
        listaLibrosdelaBD = librosjpa.findLibroEntities();
        
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
        listaLibrosdelaBD = librosjpa.findLibroEntities();



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

        if (jComboBox1.getSelectedItem().equals("Estado")) {

            for (int i = 0; i <= listaLibrosdelaBD.size() - 1; i++) {
                if (listaLibrosdelaBD.get(i).getEstado().equals("Disponible")) {
                    librosTabla.add(listaLibrosdelaBD.get(i));
                }
            }
            for (int i = 0; i <= listaLibrosdelaBD.size() - 1; i++) {
                if (listaLibrosdelaBD.get(i).getEstado().equals("No Disponible")) {
                    librosTabla.add(listaLibrosdelaBD.get(i));
                }
            }


            updateTableNuevo(librosTabla);

        }


    }//GEN-LAST:event_jComboBox1ActionPerformed

   
    
    private void libroimg1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_libroimg1MouseClicked
        List <Libro> nuevalista = listaLibroscarrito;

    try{            
        if(!nuevalista.isEmpty() && !nuevalista.get(0).equals(null) ){
            JOptionPane.showMessageDialog(null, nuevalista.get(0).toString());
        }
    }catch(IndexOutOfBoundsException ex){          
    } 

    }//GEN-LAST:event_libroimg1MouseClicked

    private void libroimg2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_libroimg2MouseClicked
        List <Libro> nuevalista = listaLibroscarrito;

    try{            
        if(!nuevalista.isEmpty() && !nuevalista.get(1).equals(null) ){
            JOptionPane.showMessageDialog(null, nuevalista.get(1).toString());
        }
    }catch(IndexOutOfBoundsException ex){          
    }      
               
    }//GEN-LAST:event_libroimg2MouseClicked

    private void libroimg3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_libroimg3MouseClicked
        List <Libro> nuevalista = listaLibroscarrito;

    try{            
        if(!nuevalista.isEmpty() && !nuevalista.get(2).equals(null) ){
            JOptionPane.showMessageDialog(null, nuevalista.get(2).toString());
        }
    }catch(IndexOutOfBoundsException ex){          
    }    
        
    }//GEN-LAST:event_libroimg3MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
       
        if(listaLibroscarrito.size()!=0){
         int dialogButton = JOptionPane.YES_NO_OPTION;
            int opc = JOptionPane.showConfirmDialog (null, "¿Esta seguro de cancelar su selecion de libros en el carrito?",
                                                           "AVISO", dialogButton);

            if(opc == 0) {
                  listaLibroscarrito.clear();
                  updateCarrito();
            }
        }    
    }//GEN-LAST:event_jButton4ActionPerformed

    
    
    
    
    
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (listaLibroscarrito.size()==0) {
            JOptionPane.showMessageDialog (null, "Debe agregar al menos un libro al carrito para confirmar su solicitud");
        
        
        }else if( (listaLibroscarrito.size() + listaPrestamoLibrosdelaBD.size())>3){
        
            JOptionPane.showMessageDialog (null, "Solo puede solicitar un maximo de 3 libros en prestamo");
            listaLibroscarrito.clear();
            updateCarrito();
        
        }else{
            
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int opc = JOptionPane.showConfirmDialog (null, "¿Esta seguro de confirmar la solicitud de su selecion de libros en el carrito?",
                                                           "AVISO", dialogButton);

            if(opc == 0) {
            

                int cantlibros = listaLibroscarrito.size();
                
                if(cantlibros==1){
                    Libro libro1 = listaLibroscarrito.get(0);
                    PrestamoLibro p = new PrestamoLibro(libro1.getCodigoLibro(),libro1.getNombreLibro(),userlogin.getApellidos()+" "+userlogin.getNombres(),userlogin.getIdentificacion(),userlogin.getTipoUsuario());
               
                    try {
                        prestamolibrosjpa.create(p);
                    
                    // asignar estado no disponible a libros en jpa
                    Libro l = librosjpa.findLibro(libro1.getCodigoLibro());
                    
                    l.setEstado("No Disponible");
                    librosjpa.edit(l); 
                    
                    } catch (Exception ex) {
                        //Logger.getLogger(panelPrestamoLibros.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if(cantlibros==2){
                    Libro libro1 = listaLibroscarrito.get(0);
                    PrestamoLibro p1 = new PrestamoLibro(libro1.getCodigoLibro(),libro1.getNombreLibro(),userlogin.getApellidos()+" "+userlogin.getNombres(),userlogin.getIdentificacion(),userlogin.getTipoUsuario());
               
                    try {
                        prestamolibrosjpa.create(p1);
                        
                    // asignar estado no disponible a libros en jpa
                    Libro l = librosjpa.findLibro(libro1.getCodigoLibro());
                    
                    l.setEstado("No Disponible");
                    librosjpa.edit(l); 
                    
                    } catch (Exception ex) {
                        //Logger.getLogger(panelPrestamoLibros.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                     Libro libro2 = listaLibroscarrito.get(1);
                    PrestamoLibro p2 = new PrestamoLibro(libro2.getCodigoLibro(),libro2.getNombreLibro(),userlogin.getApellidos()+" "+userlogin.getNombres(),userlogin.getIdentificacion(),userlogin.getTipoUsuario());
               
                    try {
                        prestamolibrosjpa.create(p2);
                        
                    // asignar estado no disponible a libros en jpa
                    Libro l = librosjpa.findLibro(libro2.getCodigoLibro());
                    
                    l.setEstado("No Disponible");
                    librosjpa.edit(l); 
                    
                    } catch (Exception ex) {
                        //Logger.getLogger(panelPrestamoLibros.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
                if(cantlibros==3){
                    Libro libro1 = listaLibroscarrito.get(0);
                    PrestamoLibro p1 = new PrestamoLibro(libro1.getCodigoLibro(),libro1.getNombreLibro(),userlogin.getApellidos()+" "+userlogin.getNombres(),userlogin.getIdentificacion(),userlogin.getTipoUsuario());
               
                    try {
                        prestamolibrosjpa.create(p1);
                        
                        
                    // asignar estado no disponible a libros en jpa
                    Libro l = librosjpa.findLibro(libro1.getCodigoLibro());
                    
                    l.setEstado("No Disponible");
                    librosjpa.edit(l); 

   
                    } catch (Exception ex) {
                        //Logger.getLogger(panelPrestamoLibros.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    Libro libro2 = listaLibroscarrito.get(1);
                    PrestamoLibro p2 = new PrestamoLibro(libro2.getCodigoLibro(),libro2.getNombreLibro(),userlogin.getApellidos()+" "+userlogin.getNombres(),userlogin.getIdentificacion(),userlogin.getTipoUsuario());
               
                    try {
                        prestamolibrosjpa.create(p2);
                        
                                            // asignar estado no disponible a libros en jpa
                    Libro l = librosjpa.findLibro(libro2.getCodigoLibro());
                    
                    l.setEstado("No Disponible");
                    librosjpa.edit(l); 
                        
                        
                    } catch (Exception ex) {
                        //Logger.getLogger(panelPrestamoLibros.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    Libro libro3 = listaLibroscarrito.get(2);
                    PrestamoLibro p3 = new PrestamoLibro(libro3.getCodigoLibro(),libro3.getNombreLibro(),userlogin.getApellidos()+" "+userlogin.getNombres(),userlogin.getIdentificacion(),userlogin.getTipoUsuario());
               
                    try {
                        prestamolibrosjpa.create(p3);
                        
                    // asignar estado no disponible a libros en jpa
                    Libro l = librosjpa.findLibro(libro3.getCodigoLibro());
                    
                    l.setEstado("No Disponible");
                    librosjpa.edit(l); 
                    
                    } catch (Exception ex) {
                        //Logger.getLogger(panelPrestamoLibros.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            
             listaLibroscarrito.clear();
             updateCarrito();
             
             updateTable();  
             updateTablePrestamos();
             JOptionPane.showMessageDialog(null, "Su solicitud ha sido registrada con exito");
        }
    } // fin else
                       
    }//GEN-LAST:event_jButton3ActionPerformed

  
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel libroimg1;
    private javax.swing.JLabel libroimg2;
    private javax.swing.JLabel libroimg3;
    private javax.swing.JTable miTabla;
    private javax.swing.JTable miTablaPrestamos;
    private javax.swing.JTextField txtacarrera;
    private javax.swing.JTextField txtcodigo;
    private javax.swing.JTextField txtnombre;
    // End of variables declaration//GEN-END:variables

    
        // actulizar tabla prestamos con info base de datos
    public void updateTablePrestamos() {

        // ACTUALIZACION DE LA TABLA
        // vuelve asignarle el nuevo valor de la lista de la base de datos
//        listaPrestamoLibrosdelaBD = prestamolibrosjpa.findPrestamoLibroEntities();

        listaPrestamoLibrosdelaBD.clear();
        
        for (PrestamoLibro p : prestamolibrosjpa.findPrestamoLibroEntities()) {
            
            if (p.getIdUsuario().equals(userlogin.getIdentificacion())) {
                
                if(p.getEstadoPrestamo().equals("Vencido")){
                    usuarioPresentaDeuda = true;
                }
                
                if(!p.getEstadoPrestamo().equals("Entregado")){
                listaPrestamoLibrosdelaBD.add(p);
                }
            }
        }
        

        // metodo es diferente ya no es setModel
        // actualiza nuestro modelo actual de la tabla
        ((AbstractTableModel) miTablaPrestamos.getModel()).fireTableDataChanged();

    }
    
    
    // actulizar tabla con info base de datos
    public void updateTable() {

        // ACTUALIZACION DE LA TABLA
        // volver asignarle el nuevo valor de la lista de la base de datos
        listaLibrosdelaBD = librosjpa.findLibroEntities();

        // metodo es diferente ya no es setModel
        // actualiza nuestro modelo actual de la tabla
        ((AbstractTableModel) miTabla.getModel()).fireTableDataChanged();

    }
    
    

    public void updateTableNuevo(List in) {

        // ACTUALIZACION DE LA TABLA
        // volver asignarle el nuevo valor de la lista de la base de datos
        listaLibrosdelaBD = in;

        // metodo es diferente ya no es setModel
        // actualiza nuestro modelo actual de la tabla
        ((AbstractTableModel) miTabla.getModel()).fireTableDataChanged();

    }



 

    // CREANDO MODELO TABLA LIBROS 
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
            return 6;
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
                    salida = nuevoLibro.getTomo();
                    break;

                case 4:
                    salida = nuevoLibro.getCarrera();
                    break;

                case 5:
                    salida = nuevoLibro.getEstado();
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
                    return "TOMO";
                    
                case 4:
                    return "CATEGORIA";

                case 5:
                    return "ESTADO";
            }

            return "";

        }

    }
    
    
    //--------------------------
    
     // CREANDO MODELO TABLA PRESTAMOS $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
    // necesito la clase prestamo jpa <<<<<<<<<<<<<<<<<<<
        
    private class ModeloTablaPrestamos extends AbstractTableModel {

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
            return 5;
        }

        // la informacion de la fila y la informacion de las columnas
        @Override
        public Object getValueAt(int fila, int columna) {

            PrestamoLibro nuevoPrestamo = listaPrestamoLibrosdelaBD.get(fila);

            Object salida = "";

            switch (columna) {
                case 0:

                    salida = nuevoPrestamo.getCodigoLibro();
                    break;

                case 1:
                    salida = nuevoPrestamo.getNombreLibro();
                    break;

                case 2:
                    salida = nuevoPrestamo.getFechaPrestamo();
                    break;
                    
                case 3:
                    salida = nuevoPrestamo.getEntregaPrestamo();
                    break;

                case 4:
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
                    return "CODIGO";

                case 1:
                    return "TITULO";

                case 2:
                    return "PRESTAMO";

                case 3:
                    return "DEVOLUCION";
                    
                case 4:
                    return "ESTADO";
            }

            return "";

        }

     

    }

}
