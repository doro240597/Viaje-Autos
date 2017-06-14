/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author danie
 */
public class Viaje extends javax.swing.JInternalFrame {

    DefaultTableModel model;

    /**
     * Creates new form Viaje
     */
    public Viaje() {
        initComponents();
        cargarMes();
        botonesInicio();
        textosInicio();
        carTablaViaje("");
        tblViaje.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                int fila = tblViaje.getSelectedRow();
                if (tblViaje.getSelectedRow() != -1) {
                    char[] cadena = tblViaje.getValueAt(fila, 1).toString().toCharArray();
                    String cadena1 = String.valueOf(cadena[0]) + String.valueOf(cadena[1]) + String.valueOf(cadena[2])
                            + "-" + String.valueOf(cadena[3]) + String.valueOf(cadena[4]) + String.valueOf(cadena[5]) + String.valueOf(cadena[6]);
                    char[] fecha = tblViaje.getValueAt(fila, 2).toString().toCharArray();
                    txtCodigo.setText(tblViaje.getValueAt(fila, 0).toString());
                    txtPlaca.setText(cadena1);
                    
                    String ames = String.valueOf(fecha[3]) + String.valueOf(fecha[4]);
                    String a = seleccionarMes(ames);
                    if(String.valueOf(fecha[0]).equals("0")){
                    cmbDia.setSelectedItem(String.valueOf(fecha[1]));
                    }else{
                    cmbDia.setSelectedItem(String.valueOf(fecha[0])+String.valueOf(fecha[1]));
                    }
                    cmbMes.setSelectedItem(a);
                    cmbAño.setSelectedItem(String.valueOf(fecha[6]) + String.valueOf(fecha[7]) + String.valueOf(fecha[8]) + String.valueOf(fecha[9]));
                    txtDestino.setText(tblViaje.getValueAt(fila, 3).toString());
                    txtOrigen.setText(tblViaje.getValueAt(fila, 4).toString());
                    txtCosto.setText(tblViaje.getValueAt(fila, 5).toString());
                    txtObservacion.setText(tblViaje.getValueAt(fila, 6).toString());
                    desbloquearBotonesUpdate();
                    btnBorrar.setEnabled(true);
                    desbloquearTextos();
                    txtCodigo.setEnabled(false);

                }
//                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
    }

    public String seleccionarDMes(String x) {
        String mes = "";
        if (x.equals("Enero")) {
            mes = "01";
        } else if (x.equals("Febrero")) {
            mes = "02";
        } else if (x.equals("Marzo")) {
            mes = "03";
        } else if (x.equals("Abril")) {
            mes = "04";
        } else if (x.equals("Mayo")) {
            mes = "05";
        } else if (x.equals("Junio")) {
            mes = "06";
        } else if (x.equals("Julio")) {
            mes = "07";
        } else if (x.equals("Agosto")) {
            mes = "08";
        } else if (x.equals("Septiembre")) {
            mes = "09";
        } else if (x.equals("Octubre")) {
            mes = "10";
        } else if (x.equals("Noviembre")) {
            mes = "11";
        } else if (x.equals("Diciembre")) {
            mes = "12";
        }
        return mes;
    }

    public String seleccionarMes(String x) {
        String mes = "";
        if (x.equals("01")) {
            mes = "Enero";
        } else if (x.equals("02")) {
            mes = "Febrero";
        } else if (x.equals("03")) {
            mes = "Marzo";
        } else if (x.equals("04")) {
            mes = "Abril";
        } else if (x.equals("05")) {
            mes = "Mayo";
        } else if (x.equals("06")) {
            mes = "Junio";
        } else if (x.equals("07")) {
            mes = "Julio";
        } else if (x.equals("08")) {
            mes = "Agosto";
        } else if (x.equals("09")) {
            mes = "Septiembre";
        } else if (x.equals("10")) {
            mes = "Octubre";
        } else if (x.equals("11")) {
            mes = "Noviembre";
        } else if (x.equals("12")) {
            mes = "Diciembre";
        }
        return mes;
    }

    public void carTablaViaje(String Dato) {
        String[] titulos = {"CODIGO", "PLACA", "FECHA", "DESTINO", "ORIGEN", "COSTO", "OBSERVACION"};
        String[] registros = new String[7];
        model = new DefaultTableModel(null, titulos);
        conexion cc = new conexion();
        Connection cn = cc.conectar();
        String sql = "";
        sql = "select *from viajes where VIA_CODIGO LIKE'%" + Dato + "%' and VIA_ESTADO='A'";
        try {
            Statement psd = cn.createStatement(); //clases propias de sql
            ResultSet rs = psd.executeQuery(sql);
            while (rs.next()) {
                registros[0] = rs.getString("VIA_CODIGO");
                registros[1] = rs.getString("AUT_PLACA");
                char[] x = rs.getString("VIA_FECHA").toCharArray();
                String x1 = String.valueOf(x[8]) + String.valueOf(x[9]) + String.valueOf(x[7])
                        + String.valueOf(x[5]) + String.valueOf(x[6]) + String.valueOf(x[4])
                        + String.valueOf(x[0]) + String.valueOf(x[1]) + String.valueOf(x[2])
                        + String.valueOf(x[3]);
                registros[2] = x1;
                registros[3] = rs.getString("VIA_DESTINO");
                registros[4] = rs.getString("VIA_ORIGEN");
                registros[5] = rs.getString("VIA_COSTO");
                registros[6] = rs.getString("VIA_OBSERVACION");
                model.addRow(registros);

            }
            tblViaje.setModel(model);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
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

    public void cargarDia() {
        cmbDia.addItem("Dia");
        String mes = "";

        mes = String.valueOf(cmbMes.getSelectedItem());

        int d = 1;
        String año = "";

        año = String.valueOf(cmbAño.getSelectedItem());

        int a = Integer.valueOf(año);
        int b = 1920;
        while (b < a) {
            b = b + 4;
        }
//        JOptionPane.showMessageDialog(null, b);
        String dia = String.valueOf(cmbDia.getSelectedItem());
        DefaultComboBoxModel modelodia = new DefaultComboBoxModel();
        if (mes.equals("Enero") || mes.equals("Marzo") || mes.equals("Mayo")
                || mes.equals("Julio") || mes.equals("Agosto") || mes.equals("Octubre")
                || mes.equals("Diciembre")) {
            while (d <= 31) {
                modelodia.addElement(String.valueOf(d));
                d++;
            }
        } else if (mes.equals("Abril") || mes.equals("Junio") || mes.equals("Septiembre")
                || mes.equals("Noviembre")) {
            while (d <= 30) {
                modelodia.addElement(String.valueOf(d));
                d++;
            }
        } else if (mes.equals("Febrero")) {
            if (b == a) {
                while (d <= 29) {
                    modelodia.addElement(String.valueOf(d));
                    d++;
                }
            } else {
                while (d <= 28) {
                    modelodia.addElement(String.valueOf(d));
                    d++;
                }
            }
        }
        cmbDia.setModel(modelodia);

        cmbDia.setSelectedItem(dia);
    }

    public void cargarMes() {

        int a = 2017;
        while (a >= 1920) {
            cmbAño.addItem(String.valueOf(a));
            a--;
        }
        int m = 1;

        cmbMes.addItem("Enero");
        cmbMes.addItem("Febrero");
        cmbMes.addItem("Marzo");
        cmbMes.addItem("Abril");
        cmbMes.addItem("Mayo");
        cmbMes.addItem("Junio");
        cmbMes.addItem("Julio");
        cmbMes.addItem("Agosto");
        cmbMes.addItem("Septiembre");
        cmbMes.addItem("Octubre");
        cmbMes.addItem("Noviembre");
        cmbMes.addItem("Diciembre");

    }

    public void limpiarTextos() {
        txtCodigo.setText("");
        txtPlaca.setText("");
        cmbDia.setSelectedItem("1");
        cmbMes.setSelectedItem("Enero");
        cmbAño.setSelectedItem("2017");
        txtDestino.setText("");
        txtOrigen.setText("");
        txtCosto.setText("");
        txtObservacion.setText("");
    }

    public void botonesInicio() {
        btnNuevo.setEnabled(true);
        btnGuardar.setEnabled(false);
        btnActualizar.setEnabled(false);
        btnCancelar.setEnabled(false);
        btnBorrar.setEnabled(false);
        btnSalir.setEnabled(true);
        btnNuevo.requestFocus();
    }

    public void textosInicio() {
        txtCodigo.setEnabled(false);
        txtPlaca.setEnabled(false);
        cmbDia.setEnabled(false);
        cmbMes.setEnabled(false);
        cmbAño.setEnabled(false);
        txtDestino.setEnabled(false);
        txtOrigen.setEnabled(false);
        txtCosto.setEnabled(false);
        txtObservacion.setEnabled(false);

    }

    public void botonNuevo() {
        txtCodigo.setEnabled(true);
        txtPlaca.setEnabled(true);
        cmbDia.setEnabled(true);
        cmbMes.setEnabled(true);
        cmbAño.setEnabled(true);
        txtDestino.setEnabled(true);
        txtOrigen.setEnabled(true);
        txtCosto.setEnabled(true);
        txtObservacion.setEnabled(true);
        btnNuevo.setEnabled(false);
        btnGuardar.setEnabled(true);
        btnActualizar.setEnabled(false);
        btnCancelar.setEnabled(true);
        btnBorrar.setEnabled(false);
        btnSalir.setEnabled(true);
        btnGuardar.requestFocus();
    }

    public void desbloquearTextos() {
        txtCodigo.setEnabled(true);
        txtPlaca.setEnabled(true);
        cmbDia.setEnabled(true);
        cmbMes.setEnabled(true);
        cmbAño.setEnabled(true);
        txtDestino.setEnabled(true);
        txtOrigen.setEnabled(true);
        txtCosto.setEnabled(true);
        txtObservacion.setEnabled(true);

    }

    public void desbloquearBotonesUpdate() {
        btnNuevo.setEnabled(false);
        btnGuardar.setEnabled(false);
        btnActualizar.setEnabled(true);
        btnCancelar.setEnabled(false);
        btnBorrar.setEnabled(true);
        btnSalir.setEnabled(true);
        btnActualizar.requestFocus();
    }

    public void modificar() {
        if (txtCodigo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el Codigo");
        } else if (txtPlaca.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese la Placa");
        } else if (txtDestino.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el Destino");
        } else if (txtOrigen.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el Origen");
        } else if (txtCosto.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el Costo");
        } else if (txtObservacion.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese la Observacion");
        } else {
            if (txtPlaca.getText().length() < 7) {
                JOptionPane.showMessageDialog(null, "Placa no valida");
                txtPlaca.setText("");
                txtPlaca.requestFocus();
            } else {
                char[] cadena = txtPlaca.getText().toCharArray();
                String placan = "";
                if (txtPlaca.getText().length() < 7) {
                    JOptionPane.showMessageDialog(null, "Placa no valida");
                    txtPlaca.setText("");
                    txtPlaca.requestFocus();
                } else {
                    
                     //            bloquearTextos();
//            bloquearBotonesGuardar();
                    String placar = "";
                    try {
                        String VIA_CODIGO, AUT_PLACA, VIA_FECHA, VIA_DESTINO, VIA_ORIGEN, VIA_OBSERVACION, VIA_ESTADO;
                    float VIA_COSTO;
                        conexion cc = new conexion();
                    Connection cn = cc.conectar();
                    
                    String sql = "";
                    String sql3 = "";
                    
                    sql3 = "select * from autos";
                    String placa;
                    
                    if (txtPlaca.getText().length() == 7) {
                        placan = String.valueOf(cadena[0]) + String.valueOf(cadena[1]) + String.valueOf(cadena[2])
                                + "0" + String.valueOf(cadena[4]) + String.valueOf(cadena[5]) + String.valueOf(cadena[6]);

                    } else {
                        placan = String.valueOf(cadena[0]) + String.valueOf(cadena[1]) + String.valueOf(cadena[2])
                                + String.valueOf(cadena[4]) + String.valueOf(cadena[5]) + String.valueOf(cadena[6]) + String.valueOf(cadena[7]);
                    }
                    Statement psd2 = cn.createStatement();
                    ResultSet rs = psd2.executeQuery(sql3);
                    while (rs.next()) {
                        placa = rs.getString("AUT_PLACA");
                        if (placa.equals(placan)) {
                            placar = placa;
                        }
                    }
                    if (!placar.equals(placan)) {
                        JOptionPane.showMessageDialog(null, "El Auto no esta Registrado");
                        txtPlaca.setText("");
                        txtPlaca.requestFocus();
                    } else if (Float.valueOf(txtCosto.getText()) < 0 || Float.valueOf(txtCosto.getText()) >= 100) {
                        JOptionPane.showMessageDialog(null, "Costo no valido");
                        txtCosto.setText("");
                        txtCosto.requestFocus();
                    } else {
                        
                    VIA_CODIGO = txtCodigo.getText();
                    AUT_PLACA = placan;
                    String mesn = seleccionarDMes(String.valueOf(cmbMes.getSelectedItem()));
                    VIA_FECHA = String.valueOf(cmbAño.getSelectedItem()) + "-" + mesn + "-" + String.valueOf(cmbDia.getSelectedItem());
                    VIA_DESTINO = txtDestino.getText();
                    VIA_ORIGEN = txtOrigen.getText();
                    VIA_COSTO = Float.valueOf(txtCosto.getText());
                    VIA_OBSERVACION = txtObservacion.getText();
                    sql = "update viajes set AUT_PLACA='" + placan + "',"
                            + "  VIA_FECHA='" + VIA_FECHA + "',"
                            + "  VIA_ORIGEN='" + txtOrigen.getText() + "',"
                            + "  VIA_DESTINO='" + txtDestino.getText() + "',"
                            + " VIA_COSTO='" + txtCosto.getText() + "',"
                            + " VIA_OBSERVACION='" + txtObservacion.getText() + "'"
                            + "where VIA_CODIGO='" + txtCodigo.getText() + "';";
                    
                    
                        try {                      
                    PreparedStatement psd = cn.prepareStatement(sql);
                    int n = psd.executeUpdate();
                    if (n > 0) {
                    JOptionPane.showMessageDialog(null, "Se Actualizo correctamente ");
                    limpiarTextos();
                    botonesInicio();
                    textosInicio();
                    }
                    } catch (SQLException ex) {
                        Logger.getLogger(Viaje.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
                }
            }
        }
        carTablaViaje("");
    }

    public void guardar() {
        if (txtCodigo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el Codigo");
        } else if (txtPlaca.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese la Placa");
        } else if (txtDestino.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el Destino");
        } else if (txtOrigen.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el Origen");
        } else if (txtCosto.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el Costo");
        } else if (txtObservacion.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese la Observacion");
        } else {
            String VIA_CODIGO, AUT_PLACA, VIA_FECHA, VIA_DESTINO, VIA_ORIGEN, VIA_OBSERVACION, VIA_ESTADO;
            char[] cadena = txtPlaca.getText().toCharArray();
            String placan = "";
            if (txtPlaca.getText().length() < 7) {
                JOptionPane.showMessageDialog(null, "Placa no valida");
                txtPlaca.setText("");
                txtPlaca.requestFocus();
            } else {
                if (txtPlaca.getText().length() == 7) {
                    placan = String.valueOf(cadena[0]) + String.valueOf(cadena[1]) + String.valueOf(cadena[2])
                            + "0" + String.valueOf(cadena[4]) + String.valueOf(cadena[5]) + String.valueOf(cadena[6]);

                } else {
                    placan = String.valueOf(cadena[0]) + String.valueOf(cadena[1]) + String.valueOf(cadena[2])
                            + String.valueOf(cadena[4]) + String.valueOf(cadena[5]) + String.valueOf(cadena[6]) + String.valueOf(cadena[7]);
                }
                float VIA_COSTO;
                conexion cc = new conexion();
                Connection cn = cc.conectar();
                VIA_CODIGO = txtCodigo.getText();
                AUT_PLACA = placan;
                String mesn = seleccionarDMes(String.valueOf(cmbMes.getSelectedItem()));
                VIA_FECHA = String.valueOf(cmbAño.getSelectedItem()) + "-" + mesn + "-" + String.valueOf(cmbDia.getSelectedItem());
                VIA_DESTINO = txtDestino.getText();
                VIA_ORIGEN = txtOrigen.getText();
                VIA_COSTO = Float.valueOf(txtCosto.getText());
                VIA_OBSERVACION = txtObservacion.getText();
                VIA_ESTADO = "A";
                String sql = "";
                String sql2 = "";
                String sql3 = "";
                String placa;
                String placar = "";
                String codigo;
                sql = "insert into viajes(VIA_CODIGO,AUT_PLACA,VIA_FECHA,VIA_DESTINO,VIA_ORIGEN,VIA_COSTO,VIA_OBSERVACION,VIA_ESTADO) values(?,?,?,?,?,?,?,?)";
                sql2 = "select * from viajes";
                sql3 = "select * from autos";

                try {
                    Statement psd = cn.createStatement();
                    ResultSet rs = psd.executeQuery(sql2);
                    while (rs.next()) {
                        codigo = rs.getString("VIA_CODIGO");
                        placa = rs.getString("AUT_PLACA");
                        if (codigo.equals(VIA_CODIGO)) {
                            JOptionPane.showMessageDialog(null, "El Codigo de Viaje ya existe");
                        }
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(Viaje.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                    Statement psd2 = cn.createStatement();
                    ResultSet rs = psd2.executeQuery(sql3);
                    while (rs.next()) {
                        placa = rs.getString("AUT_PLACA");
                        if (placa.equals(placan)) {
                            placar = placa;
                        }
                    }
                    if (!placar.equals(placan)) {
                        JOptionPane.showMessageDialog(null, "El Auto no esta Registrado");
                        txtPlaca.setText("");
                        txtPlaca.requestFocus();
                    } else if (Float.valueOf(txtCosto.getText()) < 0 || Float.valueOf(txtCosto.getText()) >= 100) {
                        JOptionPane.showMessageDialog(null, "Costo no valido");
                        txtCosto.setText("");
                        txtCosto.requestFocus();
                    } else {
                        try {
                            PreparedStatement psd3 = cn.prepareStatement(sql);
                            psd3.setString(1, VIA_CODIGO);
                            psd3.setString(2, AUT_PLACA);
                            psd3.setString(3, VIA_FECHA);
                            psd3.setString(4, VIA_DESTINO);
                            psd3.setString(5, VIA_ORIGEN);
                            psd3.setFloat(6, VIA_COSTO);
                            psd3.setString(7, VIA_OBSERVACION);
                            psd3.setString(8, VIA_ESTADO);
                            int n = psd3.executeUpdate();
                            if (n > 0) {
//                    JOptionPane.showMessageDialog(null, psd);
                                JOptionPane.showMessageDialog(null, "Se inserto correctamente el dato");
                                botonesInicio();
                                limpiarTextos();
                                textosInicio();
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(Viaje.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        }

        carTablaViaje("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        txtDestino = new javax.swing.JTextField();
        txtOrigen = new javax.swing.JTextField();
        txtCosto = new javax.swing.JTextField();
        txtObservacion = new javax.swing.JTextField();
        cmbDia = new javax.swing.JComboBox();
        cmbMes = new javax.swing.JComboBox();
        cmbAño = new javax.swing.JComboBox();
        txtPlaca = new MisComponentes.txtPlaca();
        jPanel2 = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblViaje = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Codigo:");

        jLabel2.setText("Placa del Auto:");

        jLabel3.setText("Fecha:");

        jLabel4.setText("Destino:");

        jLabel5.setText("Origen:");

        jLabel6.setText("Costo:");

        jLabel7.setText("Observación:");

        txtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoActionPerformed(evt);
            }
        });
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoKeyTyped(evt);
            }
        });

        txtDestino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDestinoActionPerformed(evt);
            }
        });
        txtDestino.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDestinoKeyTyped(evt);
            }
        });

        txtOrigen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtOrigenKeyTyped(evt);
            }
        });

        txtCosto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCostoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCostoKeyTyped(evt);
            }
        });

        txtObservacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtObservacionKeyTyped(evt);
            }
        });

        cmbDia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDiaActionPerformed(evt);
            }
        });

        cmbMes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbMesItemStateChanged(evt);
            }
        });
        cmbMes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbMesMouseClicked(evt);
            }
        });

        cmbAño.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbAñoItemStateChanged(evt);
            }
        });

        txtPlaca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPlacaActionPerformed(evt);
            }
        });
        txtPlaca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPlacaKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cmbDia, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbMes, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbAño, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtObservacion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                        .addComponent(txtOrigen, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtDestino, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtPlaca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cmbDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbAño, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtCosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtObservacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
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
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                    .addComponent(btnBorrar, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblViaje.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tblViaje);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 5, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtCodigoActionPerformed

    private void txtCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyTyped
        // TODO add your handling code here:
        ValidacionMayusculas(evt);
        if (txtCodigo.getText().length() >= 4) {
            evt.consume();
            getToolkit().beep();
//            JOptionPane.showMessageDialog(null, "Error:Maximo 4 caracteres");
        }
    }//GEN-LAST:event_txtCodigoKeyTyped

    private void txtPlacaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlacaKeyTyped
        // TODO add your handling code here:
        ValidacionMayusculas(evt);
        char[] cadena = txtPlaca.getText().toCharArray();
        char a = evt.getKeyChar();

        if (cadena.length <= 2) {
            if (!Character.isLetter(a)) {
                evt.consume();
            }
        }

        if (cadena.length == 3) {
            txtPlaca.setText(String.valueOf(cadena[0]) + String.valueOf(cadena[1]) + String.valueOf(cadena[2]) + "-");
        }

        if (cadena.length >= 3) {
            if (!Character.isDigit(a)) {
                evt.consume();
            }
        }
        if (cadena.length >= 8) {
            evt.consume();
        }
    }//GEN-LAST:event_txtPlacaKeyTyped

    private void cmbMesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMesMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbMesMouseClicked

    private void cmbMesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbMesItemStateChanged
        // TODO add your handling code here:
        cargarDia();
    }//GEN-LAST:event_cmbMesItemStateChanged

    private void cmbAñoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbAñoItemStateChanged
        // TODO add your handling code here:
        cargarDia();
    }//GEN-LAST:event_cmbAñoItemStateChanged

    private void txtDestinoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDestinoKeyTyped
        // TODO add your handling code here:
        ValidacionMayusculas(evt);
        if (txtDestino.getText().length() >= 20) {
            evt.consume();
            getToolkit().beep();
//            JOptionPane.showMessageDialog(null, "Error:Maximo 4 caracteres");
        }
    }//GEN-LAST:event_txtDestinoKeyTyped

    private void txtOrigenKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOrigenKeyTyped
        // TODO add your handling code here:
        ValidacionMayusculas(evt);
        if (txtOrigen.getText().length() >= 20) {
            evt.consume();
            getToolkit().beep();
//            JOptionPane.showMessageDialog(null, "Error:Maximo 4 caracteres");
        }
    }//GEN-LAST:event_txtOrigenKeyTyped
    int i = 0;
    private void txtCostoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCostoKeyTyped
        // TODO add your handling code here:
        char c;
        c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            evt.consume();
            getToolkit().beep();
//            JOptionPane.showMessageDialog(null,"No se permite ingresar letras");
        }
        if (c == ',') {
            evt.consume();
            getToolkit().beep();
        }
//        char[]cadena=txtCosto.getText().toCharArray();
//        if(i==1){
//            if(String.valueOf(cadena[i])=="."){
//                txtCosto.setText(""+cadena[i-1]+cadena[i]);
//            }
//        }
//        i++;
        if (txtCosto.getText().length() >= 5) {
            evt.consume();
            getToolkit().beep();
//            JOptionPane.showMessageDialog(null, "Error:Maximo 4 caracteres");
        }
    }//GEN-LAST:event_txtCostoKeyTyped

    private void txtObservacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtObservacionKeyTyped
        // TODO add your handling code here:
        ValidacionMayusculas(evt);
        if (txtObservacion.getText().length() >= 20) {
            evt.consume();
            getToolkit().beep();
//            JOptionPane.showMessageDialog(null, "Error:Maximo 4 caracteres");
        }
    }//GEN-LAST:event_txtObservacionKeyTyped

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        int opcion = JOptionPane.showConfirmDialog(null, "¿Estas seguro/a?", "Confirmar Salir", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (opcion == JOptionPane.YES_OPTION) {
            dispose();
        }
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        limpiarTextos();
        botonesInicio();
        textosInicio();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        guardar();

    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        botonNuevo();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void txtCostoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCostoKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtCostoKeyPressed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // TODO add your handling code here:
        modificar();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
        // TODO add your handling code here:
        darBaja();
    }//GEN-LAST:event_btnBorrarActionPerformed

    private void cmbDiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbDiaActionPerformed

    private void txtPlacaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPlacaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPlacaActionPerformed

    private void txtDestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDestinoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDestinoActionPerformed
    public void darBaja(){
        int botondialogo=JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea dar de baja?","Confirmar Baja",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
        if(botondialogo == JOptionPane.YES_OPTION){
            conexion cc = new conexion();
            Connection cn = cc.conectar();
            String sql = "";
            sql="update viajes set VIA_ESTADO='D'"
                    + "where VIA_CODIGO='"+ txtCodigo.getText() +"'";
        try {
            PreparedStatement psd= cn.prepareStatement(sql);
            int n=psd.executeUpdate();
            if(n>0){
                JOptionPane.showMessageDialog(null, "Base Actualizada");
                limpiarTextos();
                    botonesInicio();
                    textosInicio();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        }
        carTablaViaje("");
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
            java.util.logging.Logger.getLogger(Viaje.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Viaje.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Viaje.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Viaje.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Viaje().setVisible(true);
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
    private javax.swing.JComboBox<String> cmbAño;
    private javax.swing.JComboBox<String> cmbDia;
    private javax.swing.JComboBox<String> cmbMes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblViaje;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtCosto;
    private javax.swing.JTextField txtDestino;
    private javax.swing.JTextField txtObservacion;
    private javax.swing.JTextField txtOrigen;
    private MisComponentes.txtPlaca txtPlaca;
    // End of variables declaration//GEN-END:variables
}
