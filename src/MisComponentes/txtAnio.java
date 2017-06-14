/*
JEFFERSON TORRES
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MisComponentes;

import java.awt.Color;
import java.awt.TextField;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Jefferson
 */
public class txtAnio extends JTextField
{
     public txtAnio() {
        this.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAnioKeyTyped(evt);
            }
        });
        this.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                
            }
        });
    }

    public Integer GettextAsInteger() {
        Integer retorno = 0;
        if (this.getText() == null) {
            return 0;
        } else {
            retorno = Integer.valueOf(this.getText());
        }
        return retorno;
    }

    

    private void txtAnioKeyTyped(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        char c;
        c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            evt.consume();
            getToolkit().beep();
//            JOptionPane.showMessageDialog(null,"No se permite ingresar letras");
        }
        if (this.getText().length()>=4) {
             evt.consume();
            getToolkit().beep();
        }
    }

    
}
