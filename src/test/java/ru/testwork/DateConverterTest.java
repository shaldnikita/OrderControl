/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.testwork;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ShaldNikita
 */
public class DateConverterTest {
    
    public DateConverterTest() {
    }
    @Test
    public void test(){
        Integer day = Integer.parseInt("1");
        Integer month = Integer.parseInt("3");
        Integer year = Integer.parseInt("2000");
        Date date = new Date(new GregorianCalendar(year, month - 1, day).getTimeInMillis());
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("dd MMMM YYYY");
        System.out.println(sdf.format(date));
    }
    
}
