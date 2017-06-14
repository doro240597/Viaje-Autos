/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * viajeCarro.java
 *
 * Created on 03/05/2017, 11:00:44 AM
 */
package ec.com.viajesuta.Interfaces;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USER-7
 */
public class viajeCarro extends javax.swing.JInternalFrame {

    DefaultTableModel model;

    /** Creates new form viajeCarro */
    public viajeCarro() {
        initComponents();
        bloquearTextos();
        bloquearBotonesInicio();
        carTablaVehiculo("");
        tblVehiculos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                int fila = tblVehiculos.getSelectedRow();
                if (tblVehiculos.getSelectedRow() != -1) {
                    char[] cadena = tblVehiculos.getValueAt(fila, 0).toString().toCharArray();
                    String cadena1= String.valueOf(cadena[0])+String.valueOf(cadena[1])+String.valueOf(cadena[2])
                            +"-"+String.valueOf(cadena[3])+String.valueOf(cadena[4])+String.valueOf(cadena[5])+String.valueOf(cadena[6]);               
                    
                    txtCarroPlaca.setText(cadena1);
                    txtCarroMarca.setText(tblVehiculos.getValueAt(fila, 1).toString());
                    txtCarroModelo.setText(tblVehiculos.getValueAt(fila, 2).toString());
                    txtCarroColor.setText(tblVehiculos.getValueAt(fila, 3).toString());
                    txtCarroAnio.setText(tblVehiculos.getValueAt(fila, 4).toString());
                    txtCarroObs.setText(tblVehiculos.getValueAt(fila, 5).toString());
                    desbloquearTextos();
                    bloquearBotonesUpdate();
                    btnBorrar.setEnabled(true);
                    txtCarroPlaca.setEnabled(false);


                }
//                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
//        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
 
//        addWindowListener(new java.awt.event.WindowAdapter() {
//            @Override
//            public void windowClosing(java.awt.event.WindowEvent evt) {
//                close();
//            }
//        });
}
    
    private void close(){
        if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente salir del sistema?",
                "Salir del sistema", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
            System.exit(0);
    }   
    public void bloquearBotonesUpdate() {
        btnNuevo.setEnabled(false);
        btnGuardar.setEnabled(false);
        btnActualizar.setEnabled(true);
        btnCancelar.setEnabled(false);
        btnBorrar.setEnabled(false);
        btnSalir.setEnabled(true);
    }
    
    public void bloquearTextos() {
        txtCarroPlaca.setEnabled(false);
        txtCarroMarca.setEnabled(false);
        txtCarroModelo.setEnabled(false);
        txtCarroColor.setEnabled(false);
        txtCarroAnio.setEnabled(false);
        txtCarroObs.setEnabled(false);
    }

    public void desbloquearTextos() {
        txtCarroPlaca.setEnabled(true);
        txtCarroMarca.setEnabled(true);
        txtCarroModelo.setEnabled(true);
        txtCarroColor.setEnabled(true);
        txtCarroAnio.setEnabled(true);
        txtCarroObs.setEnabled(true);
    }

    public void bloquearBotonesInicio() {
        btnNuevo.setEnabled(true);
        btnGuardar.setEnabled(false);
        btnActualizar.setEnabled(false);
        btnCancelar.setEnabled(false);
        btnBorrar.setEnabled(false);
        btnSalir.setEnabled(true);
    }

    public void bloquearBotonesNuevo() {
        btnNuevo.setEnabled(false);
        btnGuardar.setEnabled(true);
        btnActualizar.setEnabled(false);
        btnCancelar.setEnabled(true);
        btnBorrar.setEnabled(false);
        btnSalir.setEnabled(true);
    }

    public void limpiarTextos() {
        txtCarroPlaca.setText("");
        txtCarroMarca.setText("");
        txtCarroModelo.setText("");
        txtCarroColor.setText("");
        txtCarroAnio.setText("");
        txtCarroObs.setText("");
    }

    public void bloquearBotonesGuardar() {
        btnNuevo.setEnabled(true);
        btnGuardar.setEnabled(false);
        btnActualizar.setEnabled(false);
        btnCancelar.setEnabled(false);
        btnBorrar.setEnabled(false);
        btnSalir.setEnabled(true);
    }
    
    
    public void carTablaVehiculo(String Dato) {
        String[] titulos = {"PLACA", "MARCA", "MODELO", "COLOR", "AÑO", "OBSERVACION"};
        String[] registros = new String[6];
        model = new DefaultTableModel(null, titulos);
        conexion cc = new conexion();
        Connection cn = cc.conectar();
        String sql = "";
        sql = "select *from autos where AUT_PLACA LIKE'%"+Dato+"%' and AUT_ACTIVO='SI'";
        try {
            Statement psd = cn.createStatement(); //clases propias de sql
            ResultSet rs = psd.executeQuery(sql);
            while (rs.next()) {
                registros[0] = rs.getString("AUT_PLACA");
                registros[1] = rs.getString("AUT_MARCA");
                registros[2] = rs.getString("AUT_MODELO");
                registros[3] = rs.getString("AUT_COLOR");
                registros[4] = rs.getString("AUT_ANIO");
                registros[5] = rs.getString("AUT_OBSERVACION");
                model.addRow(registros);


            }
            tblVehiculos.setModel(model);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }


    }

    public void modificar() {
        if (txtCarroPlaca.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar la Placa");
            txtCarroPlaca.requestFocus();
        } else if (txtCarroMarca.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar la Marca");
            txtCarroMarca.requestFocus();
        } else if (txtCarroModelo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar la Modelo");
            txtCarroModelo.requestFocus();
        } else if (txtCarroColor.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar la color");
            txtCarroColor.requestFocus();
        } else if (txtCarroAnio.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar la Año");
            txtCarroAnio.requestFocus();
        } else if (Integer.valueOf(txtCarroAnio.getText())<1980||Integer.valueOf(txtCarroAnio.getText())>2018) {
            
            JOptionPane.showMessageDialog(null, "Ingerese un año entre 1980-2018");
            txtCarroAnio.setText("");
            txtCarroAnio.requestFocus();
            getToolkit().beep();
        }else if (txtCarroObs.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar la observacion");
            txtCarroObs.requestFocus();
        } else {
            
            conexion cc = new conexion();
            Connection cn = cc.conectar();
            char[] cadena = txtCarroPlaca.getText().toCharArray();
            String cadena1= String.valueOf(cadena[0])+String.valueOf(cadena[1])+String.valueOf(cadena[2])
                            +String.valueOf(cadena[4])+String.valueOf(cadena[5])+String.valueOf(cadena[6])+String.valueOf(cadena[7]);               

            String sql = "";
            sql = "update autos set AUT_MARCA='" + txtCarroMarca.getText() + "',"
                    + "  AUT_MODELO='" + txtCarroModelo.getText() + "',"
                    + "  AUT_COLOR='" + txtCarroColor.getText() + "',"
                    + "  AUT_ANIO='" + txtCarroAnio.getText() + "',"
                    + " AUT_OBSERVACION='" + txtCarroObs.getText() + "' "
                    + "where AUT_PLACA='" + cadena1 + "'";
            limpiarTextos();
            bloquearTextos();
            bloquearBotonesGuardar();


            try {
                PreparedStatement psd = cn.prepareStatement(sql);
                int n = psd.executeUpdate();
                if (n > 0) {
                    JOptionPane.showMessageDialog(null, "Se Actualizo correctamente ");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }
    public void darBaja(){
        int botondialogo=JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea dar de baja?","Confirmar Baja",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
        if(botondialogo == JOptionPane.YES_OPTION){
            conexion cc = new conexion();
            Connection cn = cc.conectar();
            String sql = "";
            sql="update autos set AUT_ACTIVO='NO'"
                    + "where AUT_PLACA='"+ txtCarroPlaca.getText() +"'";
        try {
            PreparedStatement psd= cn.prepareStatement(sql);
            int n=psd.executeUpdate();
            if(n>0){
                JOptionPane.showMessageDialog(null, "Base Actualizada");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        }
    }
    public void borrar(){
        int botondialogo=JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea borrar?","Confirmar Borrar",JOptionPane.YES_NO_OPTION,JOptionPane.ERROR_MESSAGE);
        if(botondialogo == JOptionPane.YES_OPTION){
       conexion cc = new conexion();
            Connection cn = cc.conectar();
            String sql = "";
            sql="delete from autos where AUT_PLACA='"+txtCarroPlaca.getText()+"'";
        try {
            PreparedStatement psd= cn.prepareStatement(sql);
            int n=psd.executeUpdate();
            if(n>0){
                JOptionPane.showMessageDialog(null, "Se Elimino correctamente");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
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

        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        txtCarroObs = new javax.swing.JTextField();
        txtCarroColor = new javax.swing.JTextField();
        txtCarroMarca = new javax.swing.JTextField();
        txtCarroModelo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtBuscar = new MisComponentes.txtPlaca();
        txtCarroAnio = new MisComponentes.txtAnio();
        txtCarroPlaca = new MisComponentes.txtPlaca();
        jPanel2 = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblVehiculos = new javax.swing.JTable();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Autos");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setEnabled(false);

        txtCarroObs.setToolTipText("Ingrese la Observacion del auto ");
        txtCarroObs.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCarroObsFocusLost(evt);
            }
        });
        txtCarroObs.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCarroObsKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCarroObsKeyTyped(evt);
            }
        });

        txtCarroColor.setToolTipText("Ingrese el Color del auto");
        txtCarroColor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCarroColorFocusLost(evt);
            }
        });
        txtCarroColor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCarroColorKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCarroColorKeyTyped(evt);
            }
        });

        txtCarroMarca.setToolTipText("Ingrese la Marca del auto");
        txtCarroMarca.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCarroMarcaFocusLost(evt);
            }
        });
        txtCarroMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCarroMarcaActionPerformed(evt);
            }
        });
        txtCarroMarca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCarroMarcaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCarroMarcaKeyTyped(evt);
            }
        });

        txtCarroModelo.setToolTipText("Ingrese el Modelo del auto");
        txtCarroModelo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCarroModeloFocusLost(evt);
            }
        });
        txtCarroModelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCarroModeloActionPerformed(evt);
            }
        });
        txtCarroModelo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCarroModeloKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCarroModeloKeyTyped(evt);
            }
        });

        jLabel4.setText("Color:");

        jLabel3.setText("Modelo:");

        jLabel2.setText("Marca:");

        jLabel1.setText("Placa:");

        jLabel6.setText("Observacion:");

        jLabel5.setText("Año:");

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel7.setText("Buscar por Placa:");

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                    .addComponent(jLabel7))
                .addContainerGap())
        );

        txtCarroAnio.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCarroAnioFocusLost(evt);
            }
        });
        txtCarroAnio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCarroAnioKeyTyped(evt);
            }
        });

        txtCarroPlaca.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCarroPlacaFocusLost(evt);
            }
        });
        txtCarroPlaca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCarroPlacaKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)))
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCarroPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCarroAnio, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCarroModelo, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                            .addComponent(txtCarroColor)
                            .addComponent(txtCarroObs)
                            .addComponent(txtCarroMarca))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(txtCarroPlaca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCarroMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCarroModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCarroColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtCarroAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCarroObs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(29, 29, 29)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/com/viajesuta/IMAGENES/NUEVO.png"))); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/com/viajesuta/IMAGENES/guardar.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/com/viajesuta/IMAGENES/actualizar.png"))); // NOI18N
        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/com/viajesuta/IMAGENES/cancelar.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnBorrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/com/viajesuta/IMAGENES/eliminar.png"))); // NOI18N
        btnBorrar.setText("Borrar");
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });

        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ec/com/viajesuta/IMAGENES/salir.png"))); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                                .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(82, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        tblVehiculos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblVehiculos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(39, 39, 39)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 595, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(29, 29, 29)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void txtCarroModeloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCarroModeloActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_txtCarroModeloActionPerformed

private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed

    desbloquearTextos();// TODO add your handling code here:
    bloquearBotonesNuevo();
    txtCarroPlaca.requestFocus();
}//GEN-LAST:event_btnNuevoActionPerformed

private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
    guardarAuto();
    // TODO add your handling code here:
}//GEN-LAST:event_btnGuardarActionPerformed

private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
// TODO add your handling code here:
    modificar();
    carTablaVehiculo("");
    txtBuscar.setText("");
}//GEN-LAST:event_btnActualizarActionPerformed


public void guardarAuto() {
        if (txtCarroPlaca.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese la Placa");    
        } else if(txtCarroPlaca.getText().length()<7){
            JOptionPane.showMessageDialog(null,"Error:Tipo de placa no permitida");
            txtCarroPlaca.setText("");
            txtCarroPlaca.requestFocus();
        }else if (txtCarroMarca.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese la  Marca");
            
        } else if (txtCarroModelo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el Modelo");
            
        } else if (txtCarroColor.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese un Color");
            
        } else if (txtCarroAnio.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el Año");
        } else if (Integer.valueOf(txtCarroAnio.getText())<1980||Integer.valueOf(txtCarroAnio.getText())>2018) {
            
            JOptionPane.showMessageDialog(null, "Ingerese un año entre 1980-2018");
            txtCarroAnio.setText("");
            txtCarroAnio.requestFocus();
            getToolkit().beep();
        }else if (txtCarroObs.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese la Observacion");
            
        } else {

            String AUT_PLACA, AUT_MARCA, AUT_MODELO, AUT_COLOR, AUT_OBSERVACION,AUT_ACTIVO;
            char[] cadena = txtCarroPlaca.getText().toCharArray();
            String placan="";
            if(txtCarroPlaca.getText().length()==7){
                placan=String.valueOf(cadena[0])+String.valueOf(cadena[1])+String.valueOf(cadena[2])+
                    "0"+String.valueOf(cadena[4])+String.valueOf(cadena[5])+String.valueOf(cadena[6]);
            
            }else{
                placan=String.valueOf(cadena[0])+String.valueOf(cadena[1])+String.valueOf(cadena[2])+
                    String.valueOf(cadena[4])+String.valueOf(cadena[5])+String.valueOf(cadena[6])+String.valueOf(cadena[7]);
            }
            Integer AUT_ANIO;
            conexion cc = new conexion();
            Connection cn = cc.conectar();
            AUT_PLACA = placan;
            AUT_MARCA = txtCarroMarca.getText();
            AUT_MODELO = txtCarroModelo.getText();
            AUT_COLOR = txtCarroColor.getText();
            AUT_ANIO = Integer.valueOf(txtCarroAnio.getText());
            AUT_OBSERVACION = txtCarroObs.getText();
            AUT_ACTIVO="SI";
            String sql="";
            String sql2 = "";
            String placa;
            sql = "insert into autos(AUT_PLACA,AUT_MARCA,AUT_MODELO,AUT_COLOR,AUT_ANIO,AUT_OBSERVACION,AUT_ACTIVO) values(?,?,?,?,?,?,?)";
            sql2 = "SELECT * FROM autos";
            
            try {

                Statement psd2 = cn.createStatement();
                ResultSet rs = psd2.executeQuery(sql2);
                while (rs.next()) {
                    placa = rs.getString("AUT_PLACA");
                    if (placa.equals(placan)) {
                        JOptionPane.showMessageDialog(null, "El auto ya existe");
                        txtCarroPlaca.setText("");
                        txtCarroPlaca.requestFocus();
                    }
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
            try {
                PreparedStatement psd = cn.prepareStatement(sql);
                psd.setString(1, AUT_PLACA);
                psd.setString(2, AUT_MARCA);
                psd.setString(3, AUT_MODELO);
                psd.setString(4, AUT_COLOR);
                psd.setInt(5, AUT_ANIO);
                psd.setString(6, AUT_OBSERVACION);
                psd.setString(7, AUT_ACTIVO);
                int n = psd.executeUpdate();

                if (n > 0) {
//                    JOptionPane.showMessageDialog(null, psd);
                    JOptionPane.showMessageDialog(null, "Se inserto correctamente el dato");
                    limpiarTextos();
                    bloquearBotonesGuardar();
                    bloquearTextos();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "No se Inserto");
            }
        }
        carTablaVehiculo("");
    }

private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
// TODO add your handling code here:
    limpiarTextos();
    bloquearTextos();
    bloquearBotonesGuardar();
}//GEN-LAST:event_btnCancelarActionPerformed

    private void txtCarroMarcaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCarroMarcaKeyTyped
        // TODO add your handling code here:
        controlLetras(evt);
        ValidacionMayusculas(evt);
        if (txtCarroMarca.getText().length()>=20) {
             evt.consume();
            getToolkit().beep();
            if(txtCarroMarca.getText().length()>=20){
//            JOptionPane.showMessageDialog(null,"Error: Maximo 20 caracteres");
        }
        }   
    }//GEN-LAST:event_txtCarroMarcaKeyTyped

    private void txtCarroModeloKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCarroModeloKeyTyped
        // TODO add your handling code here:
        ValidacionMayusculas(evt);
        if (txtCarroModelo.getText().length()>=20) {
             evt.consume();
            getToolkit().beep();
            if(txtCarroModelo.getText().length()>=20){
//            JOptionPane.showMessageDialog(null,"Error: Maximo 20 caracteres");
        }
        } 
    }//GEN-LAST:event_txtCarroModeloKeyTyped

    private void txtCarroColorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCarroColorKeyTyped
        // TODO add your handling code here:
        controlLetras(evt);
        ValidacionMayusculas(evt);
        if (txtCarroColor.getText().length()>=20) {
             evt.consume();
            getToolkit().beep();
            if(txtCarroColor.getText().length()>=20){
//            JOptionPane.showMessageDialog(null,"Error: Maximo 20 caracteres");
        }
        } 
    }//GEN-LAST:event_txtCarroColorKeyTyped

    private void txtCarroObsKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCarroObsKeyTyped
        // TODO add your handling code here:
        controlLetras(evt);
        ValidacionMayusculas(evt);
        if (txtCarroObs.getText().length()>=20) {
             evt.consume();
            getToolkit().beep();
            if(txtCarroObs.getText().length()>=20){
//            JOptionPane.showMessageDialog(null,"Error: Maximo 20 caracteres");
        }
        } 
    }//GEN-LAST:event_txtCarroObsKeyTyped

    private void txtCarroPlacaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCarroPlacaKeyTyped
        // TODO add your handling code here:
        ValidacionMayusculas(evt);
        char[] cadena = txtCarroPlaca.getText().toCharArray();
        char a = evt.getKeyChar();
        
        if (cadena.length <= 2) {
            if (!Character.isLetter(a)) {
                evt.consume();
            }
        }

        if (cadena.length == 3) {
            txtCarroPlaca.setText(String.valueOf(cadena[0]) + String.valueOf(cadena[1]) + String.valueOf(cadena[2]) + "-");
        }

        if (cadena.length >= 3) {
            if (!Character.isDigit(a)) {
                evt.consume();
            }
        }
        if (cadena.length >= 8) {
            evt.consume();
        }
    }//GEN-LAST:event_txtCarroPlacaKeyTyped

    private void txtCarroMarcaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCarroMarcaFocusLost
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtCarroMarcaFocusLost

    private void txtCarroModeloFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCarroModeloFocusLost
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtCarroModeloFocusLost

    private void txtCarroColorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCarroColorFocusLost
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtCarroColorFocusLost

    private void txtCarroObsFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCarroObsFocusLost
        // TODO add your handling code here:
       
    }//GEN-LAST:event_txtCarroObsFocusLost

    private void txtCarroMarcaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCarroMarcaKeyReleased
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtCarroMarcaKeyReleased

    private void txtCarroModeloKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCarroModeloKeyReleased
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtCarroModeloKeyReleased

    private void txtCarroColorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCarroColorKeyReleased
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtCarroColorKeyReleased

    private void txtCarroObsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCarroObsKeyReleased
        // TODO add your handling code here:
        if(txtCarroObs.getText().length()>20){
//            JOptionPane.showMessageDialog(null,"Error: Maximo 20 caracteres");
        }
    }//GEN-LAST:event_txtCarroObsKeyReleased

    private void txtCarroAnioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCarroAnioKeyTyped
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtCarroAnioKeyTyped

    private void txtCarroAnioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCarroAnioFocusLost
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtCarroAnioFocusLost

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
        // TODO add your handling code here:
        darBaja();
        carTablaVehiculo("");
        limpiarTextos();
        bloquearTextos();
        bloquearBotonesGuardar();
    }//GEN-LAST:event_btnBorrarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        int opcion=JOptionPane.showConfirmDialog(null,"¿Estas seguro/a?","Confirmar Salir",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        if(opcion==JOptionPane.YES_OPTION){
        dispose();
        }
    }//GEN-LAST:event_btnSalirActionPerformed

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped
        // TODO add your handling code here:
        ValidacionMayusculas(evt);
        
    }//GEN-LAST:event_txtBuscarKeyTyped

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        // TODO add your handling code here:
        carTablaVehiculo(txtBuscar.getText());
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void txtCarroMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCarroMarcaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCarroMarcaActionPerformed

    private void txtCarroPlacaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCarroPlacaFocusLost
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtCarroPlacaFocusLost

    public void controlLetras(KeyEvent evt) {
        char c;
        c = evt.getKeyChar();
        if (Character.isDigit(c)) {
            evt.consume();
            getToolkit().beep();
        }
    }
    public void ValidacionMayusculas(KeyEvent evt) {
        char caracter = evt.getKeyChar();
        if (Character.isLowerCase(caracter)) {
            String cadena = ("" + caracter);
            caracter = cadena.toUpperCase().charAt(0);
            evt.setKeyChar(caracter);
        }
    }
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
            java.util.logging.Logger.getLogger(viajeCarro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(viajeCarro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(viajeCarro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(viajeCarro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new viajeCarro().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblVehiculos;
    private MisComponentes.txtPlaca txtBuscar;
    private MisComponentes.txtAnio txtCarroAnio;
    private javax.swing.JTextField txtCarroColor;
    private javax.swing.JTextField txtCarroMarca;
    private javax.swing.JTextField txtCarroModelo;
    private javax.swing.JTextField txtCarroObs;
    private MisComponentes.txtPlaca txtCarroPlaca;
    // End of variables declaration//GEN-END:variables
}
