/*
JEFFERSON TORRES
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
JEFFERSON TORRES
 */

package MisComponentes;

import java.awt.Color;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Jefferson
 */
public class txtPlaca extends JTextField {
    int i;
    public txtPlaca() {
        this.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPlacaKeyTyped(evt);
            }
        });
        this.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPlacaFocusLost(evt);
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

    private void txtPlacaFocusLost(java.awt.event.FocusEvent evt) {
        // TODO add your handling code here:
    }

    private void txtPlacaKeyTyped(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        this.setBorder(BorderFactory.createLineBorder(Color.lightGray));
      
        char c;
        c = evt.getKeyChar();
        if ((c!=KeyEvent.VK_BACK_SPACE&&!Character.isDigit(c)&&!Character.isLetter(c))) {
            evt.consume();
            getToolkit().beep();
        }
        if (this.getText().length()>=8) {
             evt.consume();
            getToolkit().beep();
        }
        
    }
   
}
