/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Usuario.java
 *
 * Created on 15/05/2017, 08:43:22 AM
 */
package ec.com.viajesuta.Interfaces;

import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author USER-16
 */
public class Usuario extends javax.swing.JInternalFrame{
    /** Creates new form Usuario */
    Contraseña g=new Contraseña();
    public Usuario() {
        initComponents();
        cargar();
        inicio();
    }
    public void inicio(){
        btnNuevo.setEnabled(true);
        btnAñadir.setEnabled(false);
        btnSalir.setEnabled(true);
        txtUsuario.setEnabled(false);
        txtLogin.setEnabled(false);
        txtNombre.setEnabled(false);
        txtPerfil.setEnabled(false);
        txtObservacion.setEnabled(false);
    }
    public void nuevo(){
        btnNuevo.setEnabled(false);
        btnAñadir.setEnabled(true);
        btnSalir.setEnabled(true);
        txtUsuario.setEnabled(true);
        txtLogin.setEnabled(true);
        txtNombre.setEnabled(true);
        txtPerfil.setEnabled(true);
        txtObservacion.setEnabled(true);
    }
    public void ValidacionMayusculas(KeyEvent evt) {
        char caracter = evt.getKeyChar();
        if (Character.isLowerCase(caracter)) {
            String cadena = ("" + caracter);
            caracter = cadena.toUpperCase().charAt(0);
            evt.setKeyChar(caracter);
        }
    }
    public void cargar(){
        txtPerfil.addItem("Seleccione una opcion");
        txtPerfil.addItem("ADMINISTRADOR");
        txtPerfil.addItem("SECRETARIA");
    }
    public void limpiar(){
        txtUsuario.setText("");
        txtLogin.setText("");
        txtObservacion.setText("");
        txtNombre.setText("");
        txtPerfil.setSelectedItem("Seleccione una opcion");
    }
    public void adicionar(){
        String USU_USUARIO,USU_CONTRASENIA,USU_NOMBRE,USU_PERFIL,USU_OBSERVACION;
        conexion cc = new conexion();
        Connection cn = cc.conectar();
        USU_USUARIO=txtUsuario.getText();
        USU_CONTRASENIA=txtLogin.getText();
        String encriptado=g.Encriptar(USU_CONTRASENIA);
        USU_NOMBRE=txtNombre.getText();
        USU_PERFIL=String.valueOf(txtPerfil.getSelectedItem());
        USU_OBSERVACION=txtObservacion.getText();
        String sql = "";
        String sql2 = "";
        String usuario;
        sql = "insert into usuario(USU_USUARIO,USU_LOGIN,USU_NOMBRE,USU_PERFIL,USU_OBSERVACION) "
                + "values(?,?,?,?,?)";
        sql2="select * from usuario";
        if(txtUsuario.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Error:Debe Ingresar un Usuario");
        }else if(txtLogin.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Error:Debe Ingresar una Contraseña");    
        }else if(txtLogin.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Error:Debe Ingresar una Contraseña");
        }else if(txtNombre.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Error:Debe Ingresar un Nombre");
        }else if(String.valueOf(txtPerfil.getSelectedItem()).equals("Seleccione una opcion")){
            JOptionPane.showMessageDialog(null, "Error:Seleccione un perfil");
        }else if(txtObservacion.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Error:Debe Ingresar una Observacion");
        }else{
        try {
            Statement psd2=cn.createStatement();
            ResultSet rs=psd2.executeQuery(sql2);
            while (rs.next()) {
                    usuario = rs.getString("USU_USUARIO");
                    if (usuario.equals(txtUsuario.getText())) {
                        JOptionPane.showMessageDialog(null, "El Usuario ya existe");
                        txtUsuario.setText("");
                    }
                }
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
                PreparedStatement psd = cn.prepareStatement(sql);
                psd.setString(1, USU_USUARIO);
                psd.setString(2, encriptado);
                psd.setString(3, USU_NOMBRE);
                psd.setString(4, USU_PERFIL);
                psd.setString(5, USU_OBSERVACION);
                int n = psd.executeUpdate();

                if (n > 0) {
//                    JOptionPane.showMessageDialog(null, psd);
                    JOptionPane.showMessageDialog(null, "Se inserto correctamente el Nuevo Usuario");
                    limpiar();
                    inicio();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "No se inserto el Nuevo Usuario");
            }
            
        }    
            
    
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton3 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        txtLogin = new javax.swing.JPasswordField();
        txtNombre = new javax.swing.JTextField();
        txtObservacion = new javax.swing.JTextField();
        txtPerfil = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        btnAñadir = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();

        jButton3.setFont(new java.awt.Font("Dialog", 0, 14));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/com/viajesuta/IMAGENES/boton-de-adicion.png"))); // NOI18N
        jButton3.setText("Añadir");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Añadir Usuario");

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 14));
        jLabel1.setText("Usuario:");

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 14));
        jLabel2.setText("Contraseña:");

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 14));
        jLabel3.setText("Nombre:");

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 14));
        jLabel4.setText("Perfil:");

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 14));
        jLabel5.setText("Observacion:");

        txtUsuario.setFont(new java.awt.Font("Dialog", 0, 14));
        txtUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioActionPerformed(evt);
            }
        });
        txtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyTyped(evt);
            }
        });

        txtLogin.setFont(new java.awt.Font("Dialog", 0, 14));
        txtLogin.setToolTipText("Tener en cuenta el Bloq Mayus");
        txtLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLoginActionPerformed(evt);
            }
        });
        txtLogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLoginKeyTyped(evt);
            }
        });

        txtNombre.setFont(new java.awt.Font("Dialog", 0, 14));
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        txtObservacion.setFont(new java.awt.Font("Dialog", 0, 14));
        txtObservacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtObservacionKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtObservacion, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtUsuario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                                .addComponent(txtLogin, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtNombre, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(txtPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 32, Short.MAX_VALUE)))
                .addGap(29, 29, 29))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtObservacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        btnAñadir.setFont(new java.awt.Font("Dialog", 0, 14));
        btnAñadir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/com/viajesuta/IMAGENES/boton-de-adicion.png"))); // NOI18N
        btnAñadir.setText("Añadir");
        btnAñadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAñadirActionPerformed(evt);
            }
        });

        btnSalir.setFont(new java.awt.Font("Dialog", 0, 14));
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/com/viajesuta/IMAGENES/salir.png"))); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnNuevo.setFont(new java.awt.Font("Dialog", 0, 14));
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/com/viajesuta/IMAGENES/NUEVO.png"))); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAñadir, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAñadir, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyTyped
        ValidacionMayusculas(evt);
        if (txtUsuario.getText().length()>=10) {
             evt.consume();
            getToolkit().beep();
            if(txtUsuario.getText().length()>=10){
            JOptionPane.showMessageDialog(null,"Error: Maximo 10 caracteres");
        }
        } 
    }//GEN-LAST:event_txtUsuarioKeyTyped

    private void txtUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioActionPerformed

    private void txtLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLoginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLoginActionPerformed

    private void txtLoginKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLoginKeyTyped
        // TODO add your handling code here:
        if (txtLogin.getText().length()>=4) {
             evt.consume();
            getToolkit().beep();
            if(txtLogin.getText().length()>=4){
            JOptionPane.showMessageDialog(null,"Error: Maximo 4 caracteres");
        }
        } 
    }//GEN-LAST:event_txtLoginKeyTyped

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        // TODO add your handling code here:
        ValidacionMayusculas(evt);
        if (txtNombre.getText().length()>=10) {
             evt.consume();
            getToolkit().beep();
            if(txtNombre.getText().length()>=10){
            JOptionPane.showMessageDialog(null,"Error: Maximo 10 caracteres");
        }
        }
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtObservacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtObservacionKeyTyped
        // TODO add your handling code here:
        ValidacionMayusculas(evt);
        if (txtObservacion.getText().length()>=20) {
             evt.consume();
            getToolkit().beep();
            if(txtObservacion.getText().length()>=20){
            JOptionPane.showMessageDialog(null,"Error: Maximo 20 caracteres");
        }
        }
    }//GEN-LAST:event_txtObservacionKeyTyped

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        int opcion=JOptionPane.showConfirmDialog(null,"¿Estas seguro/a?","Confirmar Salir",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        if(opcion==JOptionPane.YES_OPTION){
        dispose();
        }
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnAñadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAñadirActionPerformed
        // TODO add your handling code here:
        adicionar();
    }//GEN-LAST:event_btnAñadirActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        nuevo();
    }//GEN-LAST:event_btnNuevoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Usuario().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAñadir;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField txtLogin;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtObservacion;
    private javax.swing.JComboBox<String> txtPerfil;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
