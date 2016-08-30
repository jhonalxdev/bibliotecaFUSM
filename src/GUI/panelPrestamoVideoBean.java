/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import DAO.PrestamoBeamJpaController;
import DAO.SalondeClasesJpaController;
import DAO.VideoBeamJpaController;
import DAO.exceptions.NonexistentEntityException;
import bibliotecafusm.PrestamoBeam;
import bibliotecafusm.PrestamoLibro;
import bibliotecafusm.SalondeClases;
import bibliotecafusm.Usuario;
import bibliotecafusm.VideoBeam;


import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
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
public class panelPrestamoVideoBean extends javax.swing.JPanel{

    
    private VideoBeamJpaController proyectoresjpa;      
    private List<VideoBeam> listaProyectoresdelaBD;
    

    private  PrestamoBeamJpaController prestamoBeamsjpa;
    private  List<PrestamoBeam> listaPrestamoBeamsdelaBD;
    
    
    private SalondeClasesJpaController salonesjpa;
    private List<SalondeClases> listaSalonesdelaBD;
    
    
    
    
    //-------------------------------------------
    private Usuario userlogin;
    
    
    private boolean nodisponibles = false;
    
    
    public panelPrestamoVideoBean(Usuario user) {
        
        this.userlogin = user;
        
        initComponents();

        // instancia objeto que va guardar la info a la base de datos 
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BibliotecaFUSMPU");
        proyectoresjpa = new VideoBeamJpaController(emf);
        prestamoBeamsjpa = new PrestamoBeamJpaController(emf);
        
        
         // agrega la lista de proyectores de la base de datos a mi atributo lista
        // si la base de datos esta vacia el valor asignado es null
        listaProyectoresdelaBD = proyectoresjpa.findVideoBeamEntities(); 
        
        
        
        listaPrestamoBeamsdelaBD = prestamoBeamsjpa.findPrestamoBeamEntities();
        listaPrestamoBeamsdelaBD.clear();
        
        
        
        salonesjpa = new SalondeClasesJpaController (emf);
        listaSalonesdelaBD = salonesjpa.findSalondeClasesEntities();
        
        
        for (PrestamoBeam p : prestamoBeamsjpa.findPrestamoBeamEntities()) {
            
            if (p.getIdUsuario().equals(userlogin.getIdentificacion())) {
                listaPrestamoBeamsdelaBD.add(p);
            }
        }
        


        // asignacion de modelo a tabla
        miTabla.setModel(new ModeloTabla());
        
        organizatamseccionestabla();
        
        
        // permite la selecion de solo una celda
        miTabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // asignacion de modelo a tabla prestamosusuario
        miTablaPrestamos.setModel(new ModeloTablaPrestamos());
        
        
        // contador de la interfaz
        jLabel17.setText(""+proyectoresjpa.getVideoBeamCount());
        
        
        if(proyectoresjpa.findVideoBeamEntities().size()!=0){
        for (VideoBeam v : proyectoresjpa.findVideoBeamEntities()) {
            if (v.getDisponibilidad().equals("Disponible")) {
                jLabel12.setVisible(false);
                
            }else{
                jLabel12.setVisible(true);
                nodisponibles = true;
            }         
        }
        }
        
        
        
        
        
        cargarCombosalones();
        
        setInfoUser();
        updateTablePrestamos();
        
        
    }
    
    
    
    public void cargarCombosalones() {
        DefaultComboBoxModel mdlCombo = new DefaultComboBoxModel();
        combosalones.setModel(mdlCombo);
        
        
        mdlCombo.addElement("----------");
        
        if(!listaSalonesdelaBD.isEmpty()){
            
            listaSalonesdelaBD = salonesjpa.findSalondeClasesEntities();
      
            
        for (int i = 0; i < listaSalonesdelaBD.size(); i++) {
            SalondeClases s = listaSalonesdelaBD.get(i);
            mdlCombo.addElement(""+s.getNumeroSalon());
        }
        }
    }
    
    
    
    
    public void setInfoUser(){
        txtnombres.setText(userlogin.getNombres());
        txtapellidos.setText(userlogin.getApellidos());
        txtcodigo.setText(userlogin.getIdentificacion());
        
        // falta el estado
    }
    
    
    public void organizatamseccionestabla(){
           TableColumnModel cModel = miTabla.getColumnModel();
        
        cModel.getColumn(0).setPreferredWidth(100);   
        cModel.getColumn(1).setPreferredWidth(200);   
        cModel.getColumn(2).setPreferredWidth(200);   
        cModel.getColumn(3).setPreferredWidth(150);
        cModel.getColumn(4).setPreferredWidth(150);
        cModel.getColumn(5).setPreferredWidth(150);
        
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(CENTER);
        miTabla.getColumnModel().getColumn(0).setCellRenderer(tcr);
    
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
        jPanel1 = new javax.swing.JPanel();
        txtmarca = new javax.swing.JTextField();
        txtmodelo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblcodestu = new javax.swing.JLabel();
        txtserialnum = new javax.swing.JTextField();
        txtinventarionum = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        miTabla = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        combosalones = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txtcodigo = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtapellidos = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtnombres = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        miTablaPrestamos = new javax.swing.JTable();
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

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/projector.png"))); // NOI18N
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 90, 100));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalle Video Beam", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), java.awt.Color.blue)); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtmarca.setEditable(false);
        txtmarca.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel1.add(txtmarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 100, -1));

        txtmodelo.setEditable(false);
        txtmodelo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel1.add(txtmodelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, 110, -1));

        jLabel3.setText("Marca *");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 100, -1));

        jLabel4.setText("Modelo *");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 30, 80, -1));

        jLabel7.setText("S/N *");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 30, 60, -1));

        lblcodestu.setText("Cod. Inventario *");
        jPanel1.add(lblcodestu, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 30, 110, -1));

        txtserialnum.setEditable(false);
        txtserialnum.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel1.add(txtserialnum, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 50, 100, -1));

        txtinventarionum.setEditable(false);
        txtinventarionum.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel1.add(txtinventarionum, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 50, 110, -1));

        jTextField1.setEditable(false);
        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(51, 51, 255));
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField1.setText("-");
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, 60, 40));

        jLabel8.setText("Proyector  No.");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, 90, -1));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/video-beam-icon.png"))); // NOI18N
        jLabel5.setText("   ");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 80, 280, 70));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 20, 530, 150));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "                 Video Beams Registrados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), java.awt.Color.blue)); // NOI18N
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
        miTabla.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane2.setViewportView(miTabla);

        jPanel2.add(jScrollPane2);
        jScrollPane2.setBounds(20, 70, 960, 180);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/addproyector.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton4);
        jButton4.setBounds(820, 10, 90, 40);

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Solicitar Prestamo");
        jPanel2.add(jLabel14);
        jLabel14.setBounds(810, 50, 110, 14);

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 102, 102));
        jPanel2.add(jLabel17);
        jLabel17.setBounds(79, 17, 84, 37);

        jLabel2.setText("Salon donde se encontrara el equipo");
        jPanel2.add(jLabel2);
        jLabel2.setBounds(580, 10, 210, 20);

        combosalones.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "----------" }));
        jPanel2.add(combosalones);
        combosalones.setBounds(630, 30, 140, 20);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 102, 153));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("No hay videoBeams disponibles");
        jPanel2.add(jLabel12);
        jLabel12.setBounds(200, 30, 350, 20);

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 1010, 280));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mis Prestamos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), java.awt.Color.blue)); // NOI18N
        jPanel3.setLayout(null);

        jLabel11.setText("Nombres  ");
        jPanel3.add(jLabel11);
        jLabel11.setBounds(10, 20, 80, 14);

        txtcodigo.setEditable(false);
        jPanel3.add(txtcodigo);
        txtcodigo.setBounds(350, 40, 100, 20);

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("ID");
        jPanel3.add(jLabel10);
        jLabel10.setBounds(350, 20, 100, 14);

        txtapellidos.setEditable(false);
        jPanel3.add(txtapellidos);
        txtapellidos.setBounds(180, 40, 160, 20);

        jLabel9.setText("Apellidos");
        jPanel3.add(jLabel9);
        jLabel9.setBounds(180, 20, 90, 14);

        txtnombres.setEditable(false);
        jPanel3.add(txtnombres);
        txtnombres.setBounds(10, 40, 160, 20);

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
        jScrollPane3.setBounds(10, 70, 440, 40);

        add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 460, 150));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/pattern_texture1186.jpg"))); // NOI18N
        jLabel6.setText("                       ");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1050, 500));
    }// </editor-fold>//GEN-END:initComponents

      
 // saber si las campos del formulario estan en blanco
 public boolean casillasenBlanco(){
     boolean salida = false;
     
     if(txtmarca.getText().equals("".trim())){
         salida = true;
     }
     if(txtmodelo.getText().equals("".trim())){
         salida = true;
     }

     if(txtserialnum.getText().equals("".trim())){
         salida = true;
     }
     
     if(txtinventarionum.getText().equals("".trim())){
         salida = true;
     }

     return salida;
 }
 
 
 VideoBeam proyectorEncontrado = new VideoBeam();  
 
 
 
    

    
    

    
    public void pasarInfoPanelDetalle (VideoBeam proyect){

        txtmarca.setText(proyect.getMarca());
        txtmodelo.setText(proyect.getModelo());
        txtinventarionum.setText(proyect.getInventarioNum());
        txtserialnum.setText(proyect.getSerialNum());
        jTextField1.setText(""+proyect.getProyectornum());
        

    }
    
    
   
    
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

     int cont =0;
  
     int cont2 =0;
     
        for (PrestamoBeam p : prestamoBeamsjpa.findPrestamoBeamEntities()) {
            if (p.getIdUsuario().equals(userlogin.getIdentificacion())) {
                cont++;
            }         
        }
   
        if(proyectoresjpa.findVideoBeamEntities().size()!=0){
        for (VideoBeam v : proyectoresjpa.findVideoBeamEntities()) {
            if (v.getDisponibilidad().equals("Disponible")) {
                cont2++;
            }         
        }
        }
   
    if(proyectoresjpa.findVideoBeamEntities().size()==0){     
      JOptionPane.showMessageDialog(null, "No se encuentran VideoBeams registrados");
      
    }else if (cont2==0) {
            JOptionPane.showMessageDialog(null, "Lo sentimos, en el momento no hay viedeoBeams disponibles");
    
    }else if (miTabla.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Debes seleecionar un VideoBeam");

    }else if (listaSalonesdelaBD==null) {  
            JOptionPane.showMessageDialog (null, "Lo sentimos, No se encuentran salones habilitados en el sistema","AVISO",JOptionPane.ERROR_MESSAGE);
        
            
    }else if (combosalones.getSelectedItem().toString().equals("----------")) {  
            JOptionPane.showMessageDialog (null, "Debe indicar salon donde se encontrara el equipo para confirmar su solicitud","AVISO",JOptionPane.ERROR_MESSAGE);
        
        
        }else if( cont != 0){
        
            JOptionPane.showMessageDialog (null, "Solo puede tener 1 VideoBeam en prestamo","Error al procesar solicitud",JOptionPane.ERROR_MESSAGE);
      
        
        }else{
            
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int opc = JOptionPane.showConfirmDialog (null, "¿Esta seguro de confirmar su solicitud de prestamo del VideoBeam selecionado?",
                                                           "AVISO", dialogButton);

            if(opc == 0) {
            
             String snBeam = String.valueOf(miTabla.getModel().getValueAt(miTabla.getSelectedRow(), 3)); 
             
                    VideoBeam beam = proyectoresjpa.findVideoBeam(snBeam);
                    String salon = combosalones.getSelectedItem().toString();
                    

                    
       //     public PrestamoBeam(String serialBeam, String marca, String modelo, String nombreUsuario, String idUsuario, String tipoUsuario, String salonPrestamo) 
                    PrestamoBeam p = new PrestamoBeam(beam.getSerialNum(),beam.getMarca(),beam.getModelo(),userlogin.getNombres(),userlogin.getIdentificacion(),userlogin.getTipoUsuario(), salon);
               
                    try {
                        prestamoBeamsjpa.create(p);
                    
                    // asignar estado no disponible a libros en jpa
            
                    
                    beam.setDisponibilidad("No Disponible");
                    proyectoresjpa.edit(beam); 
                    
                     JOptionPane.showMessageDialog(null, "Su solicitud ha sido registrada con exito");
                    
                    } catch (Exception ex) {
                        //Logger.getLogger(panelPrestamoLibros.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
  
             updateTable();  
             updateTablePrestamos();

    } // fin else
   
    }//GEN-LAST:event_jButton4ActionPerformed

    
    // btns aumento - decremento
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> combosalones;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblcodestu;
    private javax.swing.JTable miTabla;
    private javax.swing.JTable miTablaPrestamos;
    private javax.swing.JTextField txtapellidos;
    private javax.swing.JTextField txtcodigo;
    private javax.swing.JTextField txtinventarionum;
    private javax.swing.JTextField txtmarca;
    private javax.swing.JTextField txtmodelo;
    private javax.swing.JTextField txtnombres;
    private javax.swing.JTextField txtserialnum;
    // End of variables declaration//GEN-END:variables


    public void limpiarForm() {
        txtmarca.setText("");
        txtmodelo.setText("");
        txtinventarionum.setText("");
        txtserialnum.setText("");
   
        jTextField1.setText("-");   
    }

    
    
    
    // actulizar tabla prestamos con info base de datos
    public void updateTablePrestamos() {

        // ACTUALIZACION DE LA TABLA
        // volver asignarle el nuevo valor de la lista de la base de datos
//        listaPrestamoLibrosdelaBD = prestamolibrosjpa.findPrestamoLibroEntities();

        listaPrestamoBeamsdelaBD.clear();
        
        for (PrestamoBeam p : prestamoBeamsjpa.findPrestamoBeamEntities()) {
            
            if (p.getIdUsuario().equals(userlogin.getIdentificacion())) {
                listaPrestamoBeamsdelaBD.add(p);
            }
        }
        

        // metodo es diferente ya no es setModel
        // actualiza nuestro modelo actual de la tabla
        ((AbstractTableModel) miTablaPrestamos.getModel()).fireTableDataChanged();

        
        if(!listaPrestamoBeamsdelaBD.isEmpty()&&proyectoresjpa.findVideoBeamEntities().size()!=0){
        
            String serialBeam ="";
            
            for (PrestamoBeam p : prestamoBeamsjpa.findPrestamoBeamEntities()) {
                if (p.getIdUsuario().equals(userlogin.getIdentificacion())) {
                    serialBeam = p.getSerialBeam();
                }
            }
            
 
        VideoBeam v = proyectoresjpa.findVideoBeam(serialBeam);
        
        
        txtmarca.setText(v.getMarca());
        txtmodelo.setText(v.getModelo());
        txtinventarionum.setText(v.getInventarioNum());
        txtserialnum.setText(v.getSerialNum());
        
        jTextField1.setText(""+v.getProyectornum());
        }        
    }
    
    
        // actulizar tabla con info base de datos
    public void updateTable(){
              
        // ACTUALIZACION DE LA TABLA
            // volver asignarle el nuevo valor de la lista de la base de datos
            listaProyectoresdelaBD = proyectoresjpa.findVideoBeamEntities();

            // metodo es diferente ya no es setModel
            // actualiza nuestro modelo actual de la tabla
            ((AbstractTableModel) miTabla.getModel()).fireTableDataChanged();
            
            
            jLabel17.setText(""+proyectoresjpa.getVideoBeamCount());
    }
    
    
    

   // CREANDO MODELO TABLA BEAMS

    private class ModeloTabla extends AbstractTableModel { 


        // cuantas filas tiene la tabla en la base
        @Override
        public int getRowCount() {
            // devuelve la cant objetos - tantos objetos tenga la lista
            return listaProyectoresdelaBD.size();
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

            VideoBeam nuevoProyector = listaProyectoresdelaBD.get(fila);

            Object salida = "";

            switch (columna) {
                case 0:
                    
                    salida = nuevoProyector.getProyectornum();
                    break;

                case 1:
                    salida = nuevoProyector.getMarca();
                    break;

                case 2:
                    salida = nuevoProyector.getModelo();
                    break;
                    
                case 3:
                    salida = nuevoProyector.getSerialNum();
                    break;

                case 4:
                    salida = nuevoProyector.getInventarioNum();
                    break;                   
                case 5:
                    salida = nuevoProyector.getDisponibilidad();
                    break;                   
            }

            return salida;

        }

        // nombres de las columnas
        @Override
        public String getColumnName(int columna) {

            switch (columna) {
                case 0:
                    return "PROYECTOR";

                case 1:
                    return "MARCA";

                case 2:
                    return "MODELO";
                    
                case 3:
                    return "S/N";

                case 4:
                    return "INVENTARIO";   
                    
                case 5:
                    return "DISPONIBILIDAD";    
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
            return listaPrestamoBeamsdelaBD.size(); 
        }

        // cuantas columnas tiene la tabla en la base
        @Override
        public int getColumnCount() {
            // cuatos items va manejar la lista
            return 3;
        }

        // la informacion de la fila y la informacion de las columnas
        @Override
        public Object getValueAt(int fila, int columna) {

            PrestamoBeam nuevoPrestamo = listaPrestamoBeamsdelaBD.get(fila);

            Object salida = "";

            switch (columna) {
         
                    
                case 0:
                    salida = nuevoPrestamo.getFechaPrestamo();
                    break;

                case 1:
                    salida = nuevoPrestamo.getSalonPrestamo();
                    break;
                    
 
                case 2:
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
                    return "PRESTAMO";

                case 1:
                    return "SALON";
                    
                case 2:
                    return "ESTADO";
            }

            return "";

        }

     

    }
    
    
    
}
