/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.testwork;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ShaldNikita
 */
public class OtherTests {

    public OtherTests() {

    }

    @Test
    public void testText() {
        JLabel label = new JLabel();
        String s = label.getText();
        assertEquals("", s);
    }
    
    @Test
    public void checkReadyButton(){
        JCheckBox check = new JCheckBox();
        check.setSelected(true);
    }
     @Test
     public void statusConvertion(){
         Status a = Status.Ready;
         Object b = (Object)a;
         
         Status s = (Status)b;
     }
     

}
