package Reportes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Paulker
 */
public class Reportes {

    public void reportePrestamos() throws SQLException, JRException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conect;
        conect = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecafusmbd", "root", "toor");
//        String rutaInforme = "C:\\Users\\Paulker\\Documents\\NetBeansProjects\\BibliotecaFUSM\\src\\Reportes\\prestamos.jasper";
        String rutaInforme = "prestamos.jasper";
        
        //****************************
        //parametros
        String fecha = "08/2016";
        Map parametro = new HashMap();
        parametro.put("FechaReporte",fecha);
        
        //****************************
        
        
        JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile(rutaInforme);
       
        
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, conect);
       
        JasperViewer ver = new JasperViewer(jasperPrint,false);
        ver.setTitle("Prestamo");
        ver.setVisible(true);
        
        
        
//        JasperReport report = null;
//        report = (JasperReport) JRLoader.loadObjectFromLocation("C:\\Users\\Paulker\\Documents\\NetBeansProjects\\BibliotecaFUSM\\src\\Reportes\\prestamos.jasper");
//        
//        
//        ver.setTitle("Prestamos");
//        ver.setVisible(true);
    }
}
