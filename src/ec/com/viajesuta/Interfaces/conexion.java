/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.viajesuta.Interfaces;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author USER-7
 */
public class conexion {
    Connection connect =null ; 
    public Connection conectar(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost/viaje","root",""); 
            //JOptionPane.showMessageDialog(null, "conexión correcta");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "falló conexión intentalo más tarde");
        }
        return connect;
    }
}
